abstract class Vehicle {
    abstract start(): void;
    abstract accelarate(): void;
    abstract stop(): void;
    
    refuel(): void {
        console.log("Filling Petrol");
    }

    drive(): void {
        console.log("Riding");
        this.start();
        this.refuel();
        this.accelarate();
        this.stop();
        console.log("Done Riding, vehicle stopped")
    }
}

class Scooter extends Vehicle {
    start(): void {
        console.log("Kututututututututtttttt");
    }

    accelarate(): void {
        console.log("Kutterrrrrrrnnnnnnnrrrrrrrr");
    }

    stop(): void {
        console.log("KiiiiKiiiiiiKiiiiiiiiiiiiii");
    }
}

class SuperBike extends Vehicle {
    start(): void {
        console.log("ChuChuChuchcuchcuchcuchcuchuuuuuuu");
    }

    accelarate(): void {
        console.log("Brrrrrbrrrrbrbrbrbbbrrrrrrr");
    }

    stop(): void {
        console.log("Schschshchscsschsch");
    }

    refuel(): void {
        console.log("Fill Petrol 95");
    }
}


const scooter: Scooter = new Scooter();
const superBike: SuperBike = new SuperBike();

scooter.drive();
superBike.drive();