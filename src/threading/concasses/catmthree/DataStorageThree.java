package threading.concasses.catmthree;

import threading.concasses.includes.AbstractDataStorage;

import java.util.concurrent.ConcurrentHashMap;

/**
 *Saver of information in the {@link ConcurrentHashMap} instance
 *
 * @author hellnyk
 */
public class DataStorageThree extends AbstractDataStorage<String, Integer> {

    /**
     * Default constructor for initialize instance
     */
    public DataStorageThree(){
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void getInfoToMap(String name, String key, Integer value) {
        printResult("thread " + name + " (before)");
        map.put(key, value);
        counterWriting++;
        printResult("thread " + name + " (after)");
    }
}
