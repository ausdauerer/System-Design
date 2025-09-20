import java.util.HashMap;
import java.util.HashSet;

public class NotificationService {
    HashMap<String, HashSet<Observer>> observers = new HashMap<String, HashSet<Observer>>();

    public void addObserver(String topic, Observer observer) {
        if (!observers.containsKey(topic)) {
            observers.put(topic, new HashSet<Observer>());
        }

        HashSet<Observer> set = observers.get(topic);

        set.add(observer);
    }

    public void removeObserver(String topic, Observer observer) {
        if (observers.containsKey(topic)) {
            HashSet<Observer> set = observers.get(topic);
            set.remove(observer);
        }
    }

    public void sendNotification(String topic, Notification notification) {
        if (observers.containsKey(topic)) {
            for (Observer observer: observers.get(topic)) {
                observer.notify(topic, notification);
            }
        }
    }
}
