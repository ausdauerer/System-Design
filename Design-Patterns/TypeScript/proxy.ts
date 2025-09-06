class Logger {
    logMessage(message: string): void {
        console.log("log : %s", message);
    }
}

interface IAdditionService {
    add(a: number, b: number): number;
}

class AdditionService implements IAdditionService {
    constructor() {

    }

    add(a: number, b: number): number {
        return a + b;
    }
}

// For readonly objects initialize them in constructor

class AdditionServiceProxy implements IAdditionService {
    private service: AdditionService | null = null;
    private initialized: Boolean;

    constructor(private readonly logger: Logger) {
        this.initialized = false;
    }

    private initialize() {
        this.service = new AdditionService();
        this.initialized = true;
    }

    add(a: number, b: number): number {
        if(this.initialized != true) {
            this.initialize();
        }

        const c: number = this.service.add(a, b);
        
        this.logger.logMessage(`${a} + ${b} = ${c}`);
        
        return c;
    }
}


const logger: Logger = new Logger();
const proxy: IAdditionService = new AdditionServiceProxy(logger);

proxy.add(1,2);