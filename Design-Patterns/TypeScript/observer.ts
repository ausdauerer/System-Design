interface INotification {
    getName(): string;
}

class EmailNotification implements INotification {
    private name: string;

    constructor (name: string) {
        this.name = name;
    }

    getName(): string {
        return this.name;
    }
}

interface Subscriber {
    onNotification(type: string, event: INotification)
}

class NotificationService {
    private subscribers: Map<string,Set<Subscriber>> = new Map();

    subscribe(type: string, subscriber: Subscriber): void {
        if(!this.subscribers.has(type)) {
            this.subscribers.set(type, new Set<Subscriber>());
        } 

        const res: Set<Subscriber> = this.subscribers.get(type);
        res.add(subscriber);
    }

    unSubscribe(type: string, subscriber: Subscriber): void {
        if(this.subscribers.has(type)) {
            const res: Set<Subscriber> = this.subscribers.get(type);
            res.delete(subscriber);
        }
    }

    notify(type: string, event: INotification) {
        if (this.subscribers.has(type)) {
            for (const subscriber of this.subscribers.get(type)) {
                subscriber.onNotification(type, event);
            }
        }
    }
}

class EmailSubscriber implements Subscriber {
    onNotification(type: string, event: INotification) {
        console.log("Email subscriber got notified with event %s, name %s", type, event.getName());
    }
}

const notificationService = new NotificationService();
const bikeLaunchEmailNotification = new EmailNotification("New Bike Launch");
const carLaunchEmailNotification = new EmailNotification("New Car Launch");

const bikeLaunchEmailSubscriber = new EmailSubscriber();
const carLaunchEmailSubscriber = new EmailSubscriber();

notificationService.subscribe("bikeLaunch", bikeLaunchEmailSubscriber);
notificationService.subscribe("carLaunch", carLaunchEmailSubscriber);

notificationService.notify("carLaunch", carLaunchEmailNotification);
notificationService.notify("bikeLaunch", bikeLaunchEmailNotification);

notificationService.unSubscribe("carLaunch", carLaunchEmailSubscriber);

notificationService.notify("carLaunch", carLaunchEmailNotification);


