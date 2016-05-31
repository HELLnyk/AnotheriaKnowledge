package data;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class, which represents simple storage for {@link DataEvent} elements in {@link Map} with <code>String</code> key
 *
 * @author hellnyk
 */
public class DataStorage {

    /**
     * {@link Map} storage
     */
    private Map<String, DataEvent> mapData = new ConcurrentHashMap<>();

    /**
     * add data to storage
     *
     * @param key
     *      <code>String</code> key for value
     * @param value
     *      {@link DataEvent} value
     */
    public void putData(String key, DataEvent value){
        mapData.put(key, value);
    }

    /**
     * get value by using key
     *
     * @param key
     *      <code>String</code> key
     * @return
     *      {@link DataEvent} value assoсiated with this key
     */
    public DataEvent getData(String key){
        return mapData.get(key);
    }

    /**
     * get all information in this storage
     *
     * @return
     *      {@link Set} of {@link Map.Entry} elements in the storage
     */
    public Set<Map.Entry<String, DataEvent>> getAllData(){
        return mapData.entrySet();
    }

    /**
     * get size of this storage
     *
     * @return
     *      size of the storage
     */
    public int size(){
        return mapData.size();
    }
}
