package concurassesdatastorage.datastorageimpl;

import concurassesdatastorage.AbstractDataStorage;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Saver of information in the {@link ConcurrentHashMap} instance
 *
 * @author hellnyk
 */
public class DataStorageConcurrent extends AbstractDataStorage<Integer, String> {

    /**
     * Default constructor for initialize {@link java.util.Map} instance
     */
    public DataStorageConcurrent(){
        map = new ConcurrentHashMap<>();
    }

    /**
     * Write information to the {@link ConcurrentHashMap} instance
     *
     * @param name
     *      name of current thread, which writes information
     * @param key
     *      {@link Integer} instance of the key for writing
     * @param value
     *      {@link String} instance value for writing
     */
    @Override
    public void getInfoToMap(String name, Integer key, String value) {
        super.getInfoToMap(name, key, value);
    }
}
