package threading.concasses.catmtwo;

import threading.concasses.includes.AbstractDataStorage;
import threading.concasses.includes.InformationToFile;

import java.util.*;

/**
 * @author hellnyk
 */
public class DataStorageTwo extends AbstractDataStorage<String, Integer> {

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
