package threading.concasses;

import threading.concasses.catmone.DataStorageOne;
import threading.concasses.includes.AbstractDataStorage;
import threading.concasses.includes.FactoryDataStorage;
import threading.concasses.includes.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * represents work of {@link DataStorageOne} and {@link Worker} instances
 *
 * @author hellnyk
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        testStorage(FactoryDataStorage.getDataStorage(FactoryDataStorage.DATA_STORAGE_THREE));
    }

    /**
     * test of adding information to some {@link Map}
     *
     * @param dataStore
     *      {@link AbstractDataStorage} instance
     *
     * @throws InterruptedException
     */
    private static void testStorage(AbstractDataStorage dataStore) throws InterruptedException{
        List<Worker> workers = new ArrayList<>();
        for (int creatorId = 1; creatorId <= 5; creatorId++) {
            workers.add(new Worker(dataStore, "" + creatorId));
        }

        for(Worker worker: workers){
            worker.getThread().start();
        }

        for (Worker worker: workers){
            worker.getThread().join();
        }

        if(workers.size() * Worker.DIFFERENT_KEYS.length != dataStore.getCounterWriting()){
            int ione = workers.size() * Worker.DIFFERENT_KEYS.length;
            int itwo = dataStore.getCounterWriting();
            System.out.println("Values: needed to write: " + ione + " were written: " + itwo);
            System.out.println("Incorrect writing. See hashmapWrite.txt and hashmapWriteError.txt  file for details");
        }
    }
}
