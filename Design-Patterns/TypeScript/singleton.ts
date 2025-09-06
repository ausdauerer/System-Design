class FileManager {
    private static manager: Promise<FileManager> | null = null; 

    static async getInstance(): Promise<FileManager> {
        if (FileManager.manager == null) {
            FileManager.manager = new Promise((resolve, reject) => {
                setTimeout(() => {
                    resolve(new FileManager());
                }, 1000);
            })
        }

        return FileManager.manager;
    }
}

const run = async () => {
    const manager1: FileManager = FileManager.getInstance();
    const manager2: FileManager = await FileManager.getInstance();
}

run();