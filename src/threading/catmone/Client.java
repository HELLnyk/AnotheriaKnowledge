package threading.catmone;

import java.util.List;
import java.util.Arrays;

/**
 * represents work of {@link DataStorage} and {@link Worker} instances
 *
 * @author hellnyk
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        DataStorage dataStorage = new DataStorage();

        List<Worker> workers = Arrays.asList(
                new Worker(dataStorage, "Worker 1"),
                new Worker(dataStorage, "Worker 2"),
                new Worker(dataStorage, "Worker 3"),
                new Worker(dataStorage, "Worker 4"),
                new Worker(dataStorage, "Worker 5"));

        for(Worker worker: workers){
            worker.getThread().start();
        }

        for (Worker worker: workers){
            worker.getThread().join();
        }

        if(workers.size() * Worker.DIFFERENT_KEYS.length != dataStorage.getCounterWriting()){
            int ione = workers.size() * Worker.DIFFERENT_KEYS.length;
            int itwo = dataStorage.getCounterWriting();
            System.out.println("Values: needed to write: " + ione + " were written: " + itwo);
            System.out.println("Incorrect writing. See hashmapWrite.txt and hashmapWriteError.txt  file for details");
        }
    }
}
