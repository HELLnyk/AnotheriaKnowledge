package threading.concasses.includes;

import threading.concasses.catmone.DataStorageOne;
import threading.concasses.catmthree.DataStorageThree;
import threading.concasses.catmtwo.DataStorageTwo;
import threading.concasses.includes.AbstractDataStorage;

/**
 * @author hellnyk
 */
public class FactoryDataStorage {

    public static final int DATA_STORAGE_ONE = 1;
    public static final int DATA_STORAGE_TWO = 2;
    public static final int DATA_STORAGE_THREE = 3;


    public static AbstractDataStorage getDataStorage(int typeOfDataStorege){
        AbstractDataStorage dataStorage = null;
        switch (typeOfDataStorege){
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
