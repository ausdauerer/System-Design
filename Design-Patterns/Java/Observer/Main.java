public class Main {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        CarFeed carFeed = new CarFeed(service);
        BikeFeed bikeFeed = new BikeFeed(service);

        service.sendNotification("car", new Notification("Hyundai i20 launched"));
        service.sendNotification("bike", new Notification("BMW G500RR launched"));
    }
}
