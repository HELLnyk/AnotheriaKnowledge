package includes;

import catmone.DataStorageOne;
import catmthree.DataStorageThree;
import catmtwo.DataStorageTwo;

import java.util.Map;

/**
 * Factory for generation what type of {@link Map} will be used
 *
 * @author hellnyk
 */
public class FactoryDataStorage {

    /**
     * List types supported generator
     */
    public static final int DATA_STORAGE_ONE = 1;
    public static final int DATA_STORAGE_TWO = 2;
    public static final int DATA_STORAGE_THREE = 3;

    /**
     * get concrete worker for {@link AbstractDataStorage} instance
     *
     * @param typeOfDataStorage
     *      id of concrete realization {@link AbstractDataStorage} instance
     * @return
     *      concrete realization of {@link AbstractDataStorage} instance
     */
    public static AbstractDataStorage getDataStorage(int typeOfDataStorage){
        AbstractDataStorage dataStorage;
        switch (typeOfDataStorage){
            case DATA_STORAGE_ONE:
                dataStorage = new DataStorageOne();
                break;
            case DATA_STORAGE_TWO:
                dataStorage = new DataStorageTwo();
                break;
            case DATA_STORAGE_THREE:
                dataStorage = new DataStorageThree();
                break;
            default:
                dataStorage = new DataStorageThree();
        }
        return dataStorage;
    }
}
