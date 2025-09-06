interface UIElement {
    render(): void;
    add?(e: UIElement): void;
    remove?(e: UIElement): void;
}

class TextBox implements UIElement {
    private text: string;

    constructor(text: string) {
        this.text = text;
    }

    render() {
        console.log(this.text);
    }
}

class Container implements UIElement {
    private elements: UIElement[] = [];

    add (e: UIElement): void {
        this.elements.push(e);
    }

    remove (e: UIElement): void {
        for (var i = 0; i < this.elements.length; i++) {
            if (this.elements[i] == e) {
                this.elements.splice(i,1);
                break;
            }
        }
    }

    render () {
        for (const e of this.elements) {
            e.render();
            console.log("\n");
        }
    }
}

const t1: UIElement = new TextBox("Hello World");
const t2: UIElement = new TextBox("Hi there");

const c1: UIElement = new Container();
const c2: UIElement = new Container();

c1.add?.(t1);
c2.add?.(c1);
c2.add?.(t2);

c2.render();


