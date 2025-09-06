interface Vehicle {
    name: string;
    ride(): void;
}

class Car implements Vehicle {
    name: string;
    
    constructor(name: string) {
        this.name = name;
    }

    ride(): void {
        console.log("Riding car : %s", this.name);
    }
}

class Bike implements Vehicle {
    name: string;

    constructor (name: string) {
        this.name = name;
    }
    
    ride(): void {
        console.log("Riding a bike : %s", this.name);
    }
}

class VehicleFactory {
    createVehicle(type: string, name: string): Vehicle {
        switch (type) {
            case "car":
                return new Car(name);
            case "bike":
                return new Bike(name);
            default:
                throw new Error("Invalid vehicle type");
        }
    }
}

const vehicleFactory = new VehicleFactory();
const car = vehicleFactory.createVehicle("car", "Wangonr");
car.ride();

const bike = vehicleFactory.createVehicle("bike", "Hayabuza");
bike.ride();