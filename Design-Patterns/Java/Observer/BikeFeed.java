public class BikeFeed implements Observer{
    NotificationService service = null;

    public BikeFeed (NotificationService service) {
        this.service = service;
        this.service.addObserver("bike", this);
    }

    public void notify(String topic, Notification notification) {
        System.out.println("Got bike notification " + notification.getMessage());
    }
}