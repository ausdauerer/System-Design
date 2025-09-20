public class CarFeed implements Observer{
    NotificationService service = null;

    public CarFeed (NotificationService service) {
        this.service = service;
        this.service.addObserver("car", this);
    }

    public void notify(String topic, Notification notification) {
        System.out.println("Got car notification " + notification.getMessage());
    }
}
