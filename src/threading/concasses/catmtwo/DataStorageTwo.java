package threading.concasses.catmtwo;

import threading.concasses.includes.AbstractDataStorage;

import java.util.*;

/**
 * Saver of information in the {@link HashMap} instance with synchronization
 *
 * @author hellnyk
 */
public class DataStorageTwo extends AbstractDataStorage<String, Integer> {

    /**
     * Default constructor for special initialize instances
     */
    public DataStorageTwo(){
        Map<String, Integer> hashMap = new HashMap<>();
        map = Collections.synchronizedMap(hashMap);
    }

    @Override
    public synchronized void getInfoToMap(String name, String key, Integer value) {
        printResult("thread " + name + " (before)");
        map.put(key, value);
        counterWriting++;
        printResult("thread " + name + " (after)");
        notifyAll();
    }
}
