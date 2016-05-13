package threading.concasses.catmone;

import threading.concasses.includes.AbstractDataStorage;
import threading.concasses.includes.InformationToFile;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;


/**
 * Saver of information in the {@link HashMap} instance
 *
 * @author hellnyk
 */
public class DataStorageOne extends AbstractDataStorage<Integer, String> {

    /**
     * Default constructor for initialize instances
     */
    public DataStorageOne() {
        map = new HashMap<>();
    }

    /**
     * Write information to the {@link HashMap} instance
     *
     * @param name
     *      name of current thread, which writes information
     * @param key
     *      key for writing
     * @param value
     *      value for writing
     */
    @Override
    public void getInfoToMap(String name, Integer key, String value){
        super.getInfoToMap(name, key, value);
    }
}
