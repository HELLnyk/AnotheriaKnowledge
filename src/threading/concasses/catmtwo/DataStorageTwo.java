package threading.concasses.catmtwo;

import threading.concasses.includes.AbstractDataStorage;

import java.util.*;

/**
 * Saver of information in the synchronized {@link HashMap} instance
 *
 * @author hellnyk
 */
public class DataStorageTwo extends AbstractDataStorage<Integer, String> {

    /**
     * Default constructor for initialize {@link java.util.Map} instance
     */
    public DataStorageTwo(){
        Map<Integer, String> hashMap = new HashMap<>();
        map = Collections.synchronizedMap(hashMap);
    }

    /**
     * Write information to the synchronized {@link HashMap} instance
     *
     * @param name
     *      name of current thread, which writes information
     * @param key
     *      {@link Integer} instance of the key for writing
     * @param value
     *      {@link String} instance value for writing
     */
    @Override
    public synchronized void getInfoToMap(String name, Integer key, String value) {
        printResult("thread " + name + " (before)");
        map.put(key, value);
        printResult("thread " + name + " (after)");
        notifyAll();
    }
}
