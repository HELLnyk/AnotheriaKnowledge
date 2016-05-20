package concurassesdatastorage;

import concurassesdatastorage.datastorageimpl.DataStorageSimple;
import concurassesdatastorage.datastorageimpl.DataStorageConcurrent;
import concurassesdatastorage.datastorageimpl.DataStorageSynchronized;

import java.util.Map;

/**
 * Factory for generation what type of {@link Map} will be used
 *
 * @author hellnyk
 */
public class FactoryDataStorage {

    /**
     * get concrete worker for {@link AbstractDataStorage} instance
     *
     * @param typeOfDataStorage
     *      {@link DataStorageType} id of concrete realization {@link AbstractDataStorage} instance
     * @return
     *      concrete realization of {@link AbstractDataStorage} instance
     */
    public static AbstractDataStorage getDataStorage(DataStorageType typeOfDataStorage){
        AbstractDataStorage dataStorage;
        switch (typeOfDataStorage){
            case SIMPLE:
                dataStorage = new DataStorageSimple();
                break;
            case SYNCHRONIZED:
                dataStorage = new DataStorageSynchronized();
                break;
            case CONCURRENT:
                dataStorage = new DataStorageConcurrent();
                break;
            default:
                dataStorage = new DataStorageConcurrent();
        }
        return dataStorage;
    }
}
