interface Bike {
    ride(): void;
}

class SuzukiBike implements Bike {
    ride() {
        console.log("Riding a Suzuki bike")
    }
}

class HondaBike implements Bike {
    ride() {
        console.log("Riding a Honda bike")
    }
}

interface Car {
    ride(): void;
}

class SuzukiCar implements Car {
    ride() {
        console.log("Riding a Suzuki car")
    }
}

class HondaCar implements Car {
    ride() {
        console.log("Riding a Honda car")
    }
}

interface Factory {
    createBike(): Bike;
    createCar(): Car;
}

class SuzukiFactory implements Factory {
    createBike(): Bike {
        return new SuzukiBike();
    }

    createCar(): Car {
        return new SuzukiCar();
    }
}

class HondaFactory implements Factory {
    createBike(): Bike {
        return new HondaBike();
    }

    createCar(): Car {
        return new HondaCar();
    }
}

class FactoryCreator {
    createFactory(type: string): Factory {
        switch(type) {
            case "Suzuki":
                return new SuzukiFactory();
            case "Honda":
                return new HondaFactory();
            default:
                throw new Error("Factory not found");
        }
    }
}

const creator: FactoryCreator = new FactoryCreator();

const factory: Factory = creator.createFactory("Suzuki");

const bike: Bike = factory.createBike();
const car: Car = factory.createCar();

bike.ride();
car.ride();