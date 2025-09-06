interface Sorter {
    sort(arr: number[]): void;
}

class BubbleSorter implements Sorter {
    sort(arr: number[]): void {
        // Bubble sort
        arr.sort();
    }
}

class SelectionSorter implements Sorter {
    sort(arr: number[]): void {
        // Selection sort
        arr.sort();
    }
}

class QuickSorter implements Sorter {
    sort(arr: number[]): void {
        // Quick sort
        arr.sort();
    }
}

class SorterFactory {
    getSorter(type: string): Sorter {
        switch(type) {
            case "bubble":
                return new BubbleSorter()
            case "selection":
                return new SelectionSorter();
            case "quick":
                return new QuickSorter();
            default:
                throw new Error("No sorter found");
        }
    }
}


const sorterFactory: SorterFactory = new SorterFactory();
const arr: number[] = [1,5,3,4,5,2,45,3];

sorterFactory.getSorter("bubble").sort(arr);

console.log(arr);

