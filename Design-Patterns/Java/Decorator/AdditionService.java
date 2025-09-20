public class AdditionService implements IAdditionService{
    public int add(int a, int b) {
        System.out.println("Calculating sum");
        return a + b;
    }
}
