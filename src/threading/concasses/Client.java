package threading.concasses;

import threading.concasses.includes.AbstractDataStorage;
import threading.concasses.includes.FactoryDataStorage;
import threading.concasses.includes.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * represents work of {@link AbstractDataStorage} and {@link Worker} instances
 *
 * @author hellnyk
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        testStorage(FactoryDataStorage.getDataStorage(FactoryDataStorage.DATA_STORAGE_ONE));
    }

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

        System.out.println("See files hashmapWrite.txt for details");
    }
}
