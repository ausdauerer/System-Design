public class Logger implements IAdditionService {
    IAdditionService service;

    public Logger(IAdditionService service) {
        this.service = service;
    }
    
    public int add(int a, int b) {
        int result = this.service.add(a,b);

        System.out.println(a + " + " + b + " = " + result);

        return result;
    }
}
