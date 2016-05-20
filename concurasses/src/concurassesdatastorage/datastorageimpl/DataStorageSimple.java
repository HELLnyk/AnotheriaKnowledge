package concurassesdatastorage.datastorageimpl;

import concurassesdatastorage.AbstractDataStorage;

import java.util.HashMap;


/**
 * Saver of information in the {@link HashMap} instance
 *
 * @author hellnyk
 */
public class DataStorageSimple extends AbstractDataStorage<Integer, String> {

    /**
     * Default constructor for initialize {@link java.util.Map} instance
     */
    public DataStorageSimple() {
        map = new HashMap<>();
    }

    /**
     * Write information to the {@link HashMap} instance
     *
     * @param name
     *      name of current thread, which writes information
     * @param key
     *      {@link Integer} instance of the key for writing
     * @param value
     *      {@link String} instance value for writing
     */
    @Override
    public void getInfoToMap(String name, Integer key, String value){
        super.getInfoToMap(name, key, value);
    }
}
