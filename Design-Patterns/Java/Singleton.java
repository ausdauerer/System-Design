public class Singleton {
    private static Singleton instance = null;

    public static Singleton getInstance() {
        if (Singleton.instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance1 == instance2);
    }
}