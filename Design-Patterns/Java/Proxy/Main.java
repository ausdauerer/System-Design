public class Main {
    public static void main(String[] args) {
        IAdditionService service = new AdditionServiceProxy();

        service.add(1,6);
    }
}
