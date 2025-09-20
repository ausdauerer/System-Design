public class Main {
    public static void main(String[] args) {
        IAdditionService service = new Logger(new Cache(new AdditionService()));
        
        service.add(1,5);
        service.add(1,5);
        service.add(1,5);
    }
}
