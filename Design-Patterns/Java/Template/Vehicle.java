public abstract class Vehicle {
    public abstract void accelerate();
    public abstract void brake();

    public void drive() {
        System.out.println("Engine starting - Kikikikikikikikikik");
        accelerate();
        brake();
        System.out.println("Engine stopping - Bubbubbbuuuuuuuuuu");
    }
}
