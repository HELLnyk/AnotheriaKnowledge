package threading.concasses.includes;

import threading.concasses.catmone.DataStorageOne;
import threading.concasses.includes.AbstractDataStorage;

import java.util.Random;

/**
 * Worker, which writes information to the {@link DataStorageOne} instance
 *
 * @author hellnyk
 */
public class Worker implements Runnable {

    /**
     * default keys for writing
     */
    public static final String[] DIFFERENT_KEYS = {
            "keyOne",
            "keyTwo",
            "keyThree",
            "keyFour",
            "keyFive",
    };

    /**
     * {@link DataStorageOne} instance
     */
    private AbstractDataStorage dataStorage;

    /**
     * name of worker
     */
    private String name = "Worker_id_";

    /**
     * {@link Thread} instance
     */
    private Thread thread;

    /**
     * Default constructor
     *
     * @param dataStorage
     *      {@link DataStorageOne} instance, which will be used by this worker
     * @param nameId
     *      name of worker
     */
    public Worker(AbstractDataStorage dataStorage, String nameId) {
        this.dataStorage = dataStorage;
        name += nameId;
        thread = new Thread(this, name);
    }

    @Override
    public void run() {
        for(String key: DIFFERENT_KEYS){

            dataStorage.getInfoToMap(name, key, setInfo());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
    }

    /**
     * create example of some unit of the information
     *
     * @return
     *      {@link Integer} random instance
     */
    private int setInfo(){
        Random random = new Random();
        return random.nextInt(1000) + 100;
    }

    /**
     * getter for {@link Thread} instance for wtis worker
     *
     * @return
     *      {@link Thread} instance for wtis worker
     */
    public Thread getThread() {
        return thread;
    }
}
