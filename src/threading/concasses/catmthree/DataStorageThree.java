package threading.concasses.catmthree;

import threading.concasses.includes.AbstractDataStorage;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hellnyk
 */
public class DataStorageThree extends AbstractDataStorage<Integer, String> {

    public DataStorageThree(){
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void getInfoToMap(String name, Integer key, String value) {
        super.getInfoToMap(name, key, value);
    }
}
