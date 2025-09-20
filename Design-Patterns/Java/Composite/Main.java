public class Main {
    public static void main(String[] args) {
        Element e1 = new Element("Button 1");
        Element e2 = new Element("Dialog Box 1");
        Element e3 = new Element("Button 2");
        Element e4 = new Element("Dialog Box 2");

        Container c = new Container();

        c.addComponent(e1);
        c.addComponent(e2);

        Container c2 = new Container();

        c2.addComponent(c);
        c2.addComponent(e3);
        c2.addComponent(e4);

        c2.render();
    }
}
