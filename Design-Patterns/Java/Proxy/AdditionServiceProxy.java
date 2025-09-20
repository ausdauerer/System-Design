public class AdditionServiceProxy implements IAdditionService{
    AdditionService service = null;

    public int add(int a, int b) {
        if (this.service == null) {
            this.service = new AdditionService();
        }

        int result = this.service.add(a,b);

        System.out.println(a + " + " + b + " = " + result); 

        return result;
    }
}
