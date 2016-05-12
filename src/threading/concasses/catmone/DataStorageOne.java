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
public class DataStorageOne extends AbstractDataStorage<String, Integer> {

    /**
     * {@link Set} instance of unique writing
     */
    private Set<Integer> counters;

    /**
     * Default constructor for initialize instances
     */
    public DataStorageOne() {
        map = new HashMap<>();
        counters = Collections.synchronizedSet(new HashSet<>());
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

    public void getInfoToMap(String name, String key, Integer value){
        printResult("thread " + name + " (before)");
        map.put(key, value);
        printResult("thread " + name + " (after)");
        counterWriting++;
        if(!counters.add(counterWriting)){
            InformationToFile.write(FILE_ERROR, name + " and another thread trying to put elements in the map at the same time");
        }
    }
}
