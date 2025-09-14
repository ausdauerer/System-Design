enum VehicleSize {
    SMALL,
    COMPACT,
    LARGE
}

interface Vehicle {
    getSize(): VehicleSize
    getLicensePlate(): string
}

class Car implements Vehicle {
    licensePlate: string 

    constructor(plate: string) {
        this.licensePlate = plate
    }

    getLicensePlate() {
        return this.licensePlate
    }

    getSize() {
        return VehicleSize.COMPACT
    }
}

class Motorcycle implements Vehicle {
    licensePlate: string 

    constructor(plate: string) {
        this.licensePlate = plate
    }

    getLicensePlate() {
        return this.licensePlate
    }

    getSize() {
        return VehicleSize.SMALL
    }
}

class Truck implements Vehicle {
    licensePlate: string 

    constructor(plate: string) {
        this.licensePlate = plate
    }

    getLicensePlate() {
        return this.licensePlate
    }

    getSize() {
        return VehicleSize.LARGE
    }
}

class ParkingSlot {
    private id: number
    private parkedVehicle: Vehicle | null
    private size: VehicleSize
    
    constructor(id: number, size: VehicleSize) {
        this.id = id
        this.size = size
    }

    isSlotAvailable(): boolean {
        return this.parkedVehicle == null;
    }

    parkVehicle(vehicle: Vehicle) {
        this.parkedVehicle = vehicle;
    }

    removeVehicle(): Vehicle {
        const vehicle: Vehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        return vehicle;
    }

    getId(): number {
        return this.id;
    }

    getSize(): VehicleSize {
        return this.size;
    }
}

class ParkingManager {
    availableParkingSlots: Set<ParkingSlot>;
    vehicleSlotMap: Map<Vehicle, ParkingSlot> = new Map<Vehicle, ParkingSlot>();

    constructor(parkingSlots: ParkingSlot[]) {
        this.availableParkingSlots = new Set<ParkingSlot>(parkingSlots);
    }

    parkVehicle(vehicle: Vehicle, slot: ParkingSlot) {
        slot.parkVehicle(vehicle);
        this.vehicleSlotMap.set(vehicle, slot);
    }

    removeVehicle(vehicle: Vehicle) {
        if (!this.vehicleSlotMap.has(vehicle)) {
            throw new Error("Vehicle not in parking lot");
        }

        const slot = this.vehicleSlotMap.get(vehicle);

        slot?.removeVehicle();

        this.vehicleSlotMap.delete(vehicle);

        this.availableParkingSlots.add(slot);
    }

    findParkingSlot(vehicle: Vehicle): ParkingSlot {
        if (this.availableParkingSlots.size == 0) {
            throw new Error("No parking slots available");
        }

        for (const slot of this.availableParkingSlots) {
            if(slot.getSize() == vehicle.getSize()) {
                this.availableParkingSlots.delete(slot);
                return slot;
            } 
        }

        throw new Error("No parking slots available");
    }
}

class Ticket {
    vehicle: Vehicle;
    entryTime: Date;
    parkingSlot: ParkingSlot;

    constructor(vehicle: Vehicle, entryTime: Date, parkingSlot: ParkingSlot) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.parkingSlot = parkingSlot;
    }

    getParkingDuration(): number {
        return((Date.now() - this.entryTime.getTime()) / 60000)
    }

    getEntryTime(): Date {
        return(this.entryTime);
    }

    getVehicleSize(): VehicleSize {
        return this.vehicle.getSize();
    }

    getVehicle(): Vehicle {
        return this.vehicle;
    }
}

interface FareApplier {
    apply(ticker: Ticket, originalFare: number): number;
}

class NormalFare implements FareApplier {
    rate: Record<VehicleSize,number> = {
        [VehicleSize.SMALL]: 2,
        [VehicleSize.COMPACT]: 3,
        [VehicleSize.LARGE]: 5
    }

    apply(ticket: Ticket, originalFare: number): number {
        return originalFare + ticket.getParkingDuration() * this.rate[ticket.getVehicleSize()];
    }
}

class PeakFare implements FareApplier {
    rate: number = 1;

    apply(ticket: Ticket, originalFare: number): number {
        return originalFare + 10;
    }
}

class GST implements FareApplier {
    rate: number = 0.28;

    apply(ticket: Ticket, originalFare: number): number {
        return originalFare + originalFare * this.rate ;
    }
}


class FareCalculator {
    fareAppliers: FareApplier[] = [];

    constructor(appliers: FareApplier[]) {
        this.fareAppliers = appliers;
    }

    getFare(ticket: Ticket) {
        let fare: number = 0;

        this.fareAppliers.forEach((applier) => {
            fare = applier.apply(ticket, fare);
        });

        return fare;
    }
}

class ParkingLot {
    parkingManager: ParkingManager;
    fareCalculator: FareCalculator;

    constructor(slots: ParkingSlot[], appliers: FareApplier[]) {
        this.parkingManager = new ParkingManager(slots);
        this.fareCalculator = new FareCalculator(appliers);
    }

    parkVehicle(vehicle: Vehicle): Ticket {
        const slot = this.parkingManager.findParkingSlot(vehicle);

        this.parkingManager.parkVehicle(vehicle, slot);

        const ticket: Ticket = new Ticket(vehicle, new Date(), slot);

        return ticket;
    }

    removeVehicle(ticket: Ticket, paymentMethod: IPaymentMethod) {
        this.parkingManager.removeVehicle(ticket.getVehicle());

        const fare: number = this.fareCalculator.getFare(ticket);

        paymentMethod.pay(fare);

        return fare;
    }
}

interface IPaymentMethod {
    pay(amount: number): void;
}

class Card implements IPaymentMethod {
    pay(amount: number) {
        // Pay via card
    }
}

class Cash implements IPaymentMethod {
    pay(amount: number) {
        // Pay via cash
    }
}



const car: Vehicle = new Car('KA51ME8301');
const bike: Vehicle = new Motorcycle('KA51HE9997');
const truck: Vehicle = new Truck('KA51AB9110');

const slots: ParkingSlot[] = [
    new ParkingSlot(1, VehicleSize.COMPACT),
    new ParkingSlot(2, VehicleSize.SMALL),
    new ParkingSlot(3, VehicleSize.LARGE),
];

const fareAppliers: FareApplier[] = [
    new NormalFare(),
    new PeakFare(),
    new GST()
];

const lot = new ParkingLot(slots, fareAppliers);

const carTicket = lot.parkVehicle(car);
const bikeTicket = lot.parkVehicle(bike);
const truckTicket = lot.parkVehicle(truck);

const card = new Card();
const cash = new Cash();

setTimeout(() => {
    const fare1 = lot.removeVehicle(carTicket, card);
    const fare2 = lot.removeVehicle(bikeTicket, card);
    const fare3 = lot.removeVehicle(truckTicket, cash);

    console.log(fare1);
    console.log(fare2);
    console.log(fare3);
}, 10000);