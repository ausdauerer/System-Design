import java.util.ArrayList;

public class Container implements Component {
    ArrayList<Component> list = new ArrayList<Component>();

    public void addComponent(Component component) {
        this.list.add(component);
    }

    public void render() {
        System.out.println("<---------->");
        for(Component component: this.list) {
            component.render();
        }
        System.out.println("|----------|");
    }
}
