public class Element implements Component {
    private String name;

    public Element(String name) {
        this.name = name;
    }

    public void render() {
        System.out.println(this.name);
    }
}
