
class MusicContext {
    private state: MusicState | null = null;

    changeState(state: MusicState): void {
        this.state = state;
        this.state.setContext(this);
    }

    clickLock() {
        this.state.clickLock();
    }

    clickPlay() { 
        this.state.clickPlay();
    }

    clickNext() {
        this.state.clickNext();
    }

    clickPrevious() {
        this.state.clickPrevious();
    }
}

abstract class MusicState {
    protected context: MusicContext;

    setContext(context: MusicContext) {
        this.context = context;
    }

    abstract clickLock(): void;
    abstract clickPlay(): void;

    clickNext(): void {
        console.log("Going to next song");
    }

    clickPrevious(): void {
        console.log("Going to previous song");
    }
}


class LockedState extends MusicState {

    constructor() {
        super();
        console.log("In Locked State");
    }

    clickLock(): void {
        this.context.changeState(new PlayingState());
    }

    clickPlay(): void {
        console.log("Locked, cannot play");
    }

    clickNext(): void {
        console.log("Locked cannot go to next");
    }

    clickPrevious(): void {
        console.log("Locked, cannot go to previous");
    }
}

class ReadyMusicState extends MusicState {
    constructor() {
        super();
        console.log("In Ready State");
    }

    clickLock(): void {
        this.context.changeState(new LockedState());
    }

    clickPlay(): void {
        this.context.changeState(new PlayingState());
    }
}

class PlayingState extends MusicState {
    constructor() {
        super();
        console.log("In Playing State");
    }

    clickLock(): void {
        this.context.changeState(new LockedState());
    }

    clickPlay(): void {
        this.context.changeState(new ReadyMusicState());
    }
}

const context: MusicContext = new MusicContext();

context.changeState(new ReadyMusicState());

context.clickPlay();
context.clickPlay();
context.clickPlay();
context.clickNext();
context.clickLock();
context.clickPlay();
context.clickLock();
context.clickPlay();

