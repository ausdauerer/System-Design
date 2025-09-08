class Item {
    name: string
    price: number
    quantity: number

    constructor(name: string, price: number, quantity: number) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class Coin {
    denomination: number
    quantity: number

    constructor(denomination: number, quantity: number) {
        this.denomination = denomination;
        this.quantity = quantity;
    }
}

class Inventory {
    private items: Map<string, Item> = new Map<string, Item>();

    constructor() {

    }

    addItem(name: string, price: number) {
        this.items.set(name, new Item(name, price, 0));
    }

    refillItem(name: string, quantity: number) {
        if(this.items.has(name)) {
            this.items.get(name).quantity += quantity;
        } else {
            throw new Error("Item not found");
        }
    }

    getItemQuantity(name: string) {
        let count: number = 0;

        if (this.items.has(name)) {
            count = this.items.get(name).quantity
        }

        return count
    }

    removeItem(name: string) {
        this.items.delete(name);
    }

    dispenseItem(name: string, quantity: number) {
        if(this.items.has(name)) {
            if (this.items.get(name).quantity > 0) {
                this.items.get(name).quantity -= quantity;
            } else {
                throw new Error("Insufficient quanitty");
            }
        } else {
            throw new Error("Item not found");
        }
    }

    getItemPrice(name: string) {
        if(this.items.has(name)) {
            return this.items.get(name).price
        } else {
            throw new Error("Item not found");
        }
    }
}

class CoinManager {
    private coins: Map<number, Coin> = new Map<number, Coin>;

    insertCoins(denominations: Map<number, number>) {
        for (const [denomination, count] of denominations.entries()) {
            const coin: Coin | undefined = this.coins.get(denomination);

            if (coin) {
                coin.quantity += count;
            }
        }
    }

    isChangeAvailable(amount: number): boolean {
        const denominations: number[] = Array.from(this.coins.keys());

        denominations.sort((a, b) => b - a);
        
        denominations.forEach((denomination) => {
            const coin: Coin | undefined = this.coins.get(denomination);

            let q: number = coin.quantity;

            if (coin) {
                while (q > 0 && amount >= coin.denomination) {
                    q--;
                    amount -= denomination
                } 
            }
        });

        return amount == 0;
    }

    dispenseChange(amount: number): Map<number, number> {
        console.log("Dispensing amount %s", amount);
        const denominations: number[] = Array.from(this.coins.keys());

        denominations.sort((a, b) => b - a);

        const change: Map<number,number> = new Map<number,number>();
        
        denominations.forEach((denomination) => {
            const coin: Coin | undefined = this.coins.get(denomination);

            if (coin) {
                while (coin.quantity > 0 && amount >= coin.denomination) {
                    coin.quantity--;
                    amount -= denomination
                    
                    if (!change.has(denomination)) {
                        change.set(denomination, 0);
                    }

                    change.set(denomination, change.get(denomination) + 1);
                } 
            }
        });

        return change;
    }

    addDenomination(denomination: number) {
        this.coins.set(denomination, new Coin(denomination, 0));
    }

    isDenominationAvailable(denomination: number) {
        return this.coins.has(denomination);
    }
}

abstract class MachineState {
    context: Machine;
    
    setContext(context: Machine) {
        this.context = context;
    }

    insertCoins(denominations: Map<number, number>): void {
        throw new Error("Cannot insert coins in the current state");
    };

    selectItem(name: string): Map<number,number> {
        throw new Error("Cannot select an item in the current state");
    };

    cancelTransaction(): Map<number,number> {
        throw new Error("Cannot cancel the transaction in the current state");
    }

    setOutOfService(): void {
        throw new Error("Cannot set out of service in the current state");
    }

    setInService(): void {
        throw new Error("Cannot set in service in the current state");
    }

    refillItem(name: string, quantity: number): void {
        throw new Error("Cannot refill in current state");
    }
}

class CoinsInserted extends MachineState {
    selectItem(name: string): Map<number,number> {
        const inventory: Inventory = this.context.getInventory();
        const manager: CoinManager = this.context.getCoinManager();

        if (inventory.getItemQuantity(name) < 1) {
            throw new Error("Selected item is not available, please select another item");
        }

        let totalAmount: number = 0;

        for (const [denomination, count] of this.context.getInsertedCoins().entries()) {
            totalAmount += denomination * count;
        }

        if (inventory.getItemPrice(name) > totalAmount) {
            console.log(inventory.getItemPrice(name), totalAmount)
            throw new Error("Insufficient amount inserted");
        }

        if (!manager.isChangeAvailable(totalAmount - inventory.getItemPrice(name))) {
            throw new Error("Change not available, please select another item");
        }

        const change: Map<number,number> = this.context.dispenseItem(name);

        this.context.updateState(new Idle());

        return change;
    }

    cancelTransaction(): Map<number,number> {
        const coins: Map<number,number> = this.context.dispenseInsertedCoins();

        this.context.updateState(new Idle());

        return coins;
    }
}

class Idle extends MachineState {
    insertCoins(denominations: Map<number, number>) {
        const coinManager: CoinManager = this.context.getCoinManager();

        for (const denomination of denominations.keys()) {
            if (!coinManager.isDenominationAvailable(denomination)) {
                throw new Error(`Denomination ${denomination} is currently is not supported by the machine`);
            }
        }

        this.context.storeInsertedCoins(denominations);

        this.context.updateState(new CoinsInserted());
    }

    setOutOfService(): void {
        this.context.updateState(new OutOfService());
    }

    refillItem(name: string, quantity: number): void {
        this.context.getInventory().refillItem(name, quantity);
    }
}

class OutOfService extends MachineState {
    setInService(): void {
        this.context.updateState(new Idle());
    }
}

class Machine {
    private state: MachineState;
    private coinManager: CoinManager;
    private inventory: Inventory;
    private insertedCoins: Map<number,number>;

    constructor() {
        this.state = new Idle();
        this.state.setContext(this);

        this.coinManager = new CoinManager();
        this.inventory = new Inventory();
        this.insertedCoins = new Map<number,number>;
    }

    updateState(state: MachineState) {
        this.state = state;
        this.state.setContext(this);
    }
    
    insertCoins(coins: Map<number,number>): void {
        this.state.insertCoins(coins);
    }

    selectItem(name: string): Map<number,number> {
        return this.state.selectItem(name);
    }

    getCoinManager(): CoinManager {
        return this.coinManager;
    }

    getInventory(): Inventory {
        return this.inventory;
    }

    storeInsertedCoins(coins: Map<number,number>) {
        this.insertedCoins = coins;
    }

    cancelTransaction(): Map<number,number> {
        return this.state.cancelTransaction();
    }

    setOutOfService(): void{
        this.state.setOutOfService();
    }

    setInService(): void{
        this.state.setInService();
    }

    refillItem(name: string, quantity: number): void {
        this.state.refillItem(name, quantity);
    }

    addItem(name: string, price: number) {
        this.inventory.addItem(name, price);
    }

    addDenomination(denomination: number) {
        this.coinManager.addDenomination(denomination);
    }

    dispenseItem(name: string) {
        let totalAmount: number = 0;

        for (const [denomination, count] of this.insertedCoins.entries()) {
            totalAmount += denomination * count;
        }

        this.inventory.dispenseItem(name, 1);

        this.coinManager.insertCoins(this.insertedCoins);

        this.insertedCoins = new Map<number,number>;

        console.log(totalAmount, this.inventory.getItemPrice(name));

        const change: Map<number,number> = this.coinManager.dispenseChange(totalAmount - this.inventory.getItemPrice(name));

        return change;
    }

    dispenseInsertedCoins() {
        const coins: Map<number,number> = this.insertedCoins;
        this.insertedCoins = new Map<number,number>;
        return coins;
    }

    getInsertedCoins(): Map<number,number> {
        return this.insertedCoins;
    }
}

class User {
    insertCoins(machine: Machine, coins: Map<number,number>): void {
        machine.insertCoins(coins);
    }

    selectItem(machine: Machine, name: string): Map<number,number> {
        return machine.selectItem(name);
    }

    cancelTransaction(machine: Machine): Map<number,number> {
        return machine.cancelTransaction();
    }
}

class Administrator {
    setOutOfService(machine: Machine): void {
        machine.setOutOfService();
    }

    setInService(machine: Machine): void {
        machine.setInService();
    }

    refillItem(machine: Machine, name:string, quantity: number): void {
        machine.refillItem(name, quantity);
    }
}


const machine = new Machine();
machine.addItem("Sprite", 110);
machine.addItem("Bourbon", 25);

machine.addDenomination(100);
machine.addDenomination(20);
machine.addDenomination(10);
machine.addDenomination(30);
machine.addDenomination(50);
machine.addDenomination(5);

const user: User = new User();
const admin: Administrator = new Administrator();

admin.refillItem(machine, "Sprite", 10);
admin.refillItem(machine, "Bourbon", 10);

const coins: Map<number,number> = new Map();

coins.set(5,5);

user.insertCoins(machine, coins);

console.log(user.selectItem(machine, "Bourbon"));

coins.clear();

coins.set(30,1)

user.insertCoins(machine, coins);

console.log(user.selectItem(machine, "Bourbon"));

coins.clear();

coins.set(20,2)

user.insertCoins(machine, coins);

console.log(user.selectItem(machine, "Bourbon"));

admin.setOutOfService(machine);

admin.setInService(machine);

coins.clear();

coins.set(100,1)
coins.set(30,1)


user.insertCoins(machine,coins);

console.log(user.selectItem(machine, "Sprite"));