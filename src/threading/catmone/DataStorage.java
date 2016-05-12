package threading.catmone;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;


/**
 * Saver of information in the {@link HashMap} instance
 *
 * @author hellnyk
 */
public class DataStorage {

    /**
     * name of txt file where information about all records are saved
     */
    private static final String FILE_WRITE = "/home/hellnyk/HELLnyk/gitRepositories/AnotheriaKnowledge/hashmapWrite.txt";

    /**
     * name of txt file, where are written information about trying of several thread create record in hashmap at the same time
     */
    private static final String FILE_ERROR = "/home/hellnyk/HELLnyk/gitRepositories/AnotheriaKnowledge/hashmapWriteError.txt";

    /**
     * counter of the attempts to writing information to a {@link HashMap} instance
     */
    private int counterWriting = 0;

    /**
     * {@link Set} instance of unique writing
     */
    private Set<Integer> counters;

    /**
     * {@link Map} instance/ where the information are stored
     */
    private Map<String, Integer> someMap;

    /**
     * Default constructor for initialize instances
     */
    public DataStorage() {
        someMap = new HashMap<>();
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
    public void setInformationToMap(String name, String key, Integer value){
        InformationToFile.write(FILE_WRITE, name + " writing info: k:" + key + " v:" + value);
        counterWriting++;
        someMap.put(key, value);
        if(!counters.add(counterWriting)){
            InformationToFile.write(FILE_ERROR, name + " and another thread trying to put elements in the map at the same time");
        }
    }

    /**
     * getter for counter of attempts
     *
     * @return
     *      value of counter
     */
    public int getCounterWriting() {
        return counterWriting;
    }
}
