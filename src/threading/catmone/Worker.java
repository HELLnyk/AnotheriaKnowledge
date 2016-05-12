package threading.catmone;

import java.util.Random;

/**
 * Worker, which writes information to the {@link DataStorage} instance
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
     * {@link DataStorage} instance
     */
    private DataStorage dataStorage;

    /**
     * name of worker
     */
    private String name;

    /**
     * {@link Thread} instance
     */
    private Thread thread;

    /**
     * Default constructor
     *
     * @param dataStorage
     *      {@link DataStorage} instance, which will be used by this worker
     * @param name
     *      name of worker
     */
    public Worker(DataStorage dataStorage, String name) {
        this.dataStorage = dataStorage;
        this.name = name;
        thread = new Thread(this, name);
    }

    @Override
    public void run() {
        for(String key: DIFFERENT_KEYS){
            int value = setInfo();
            dataStorage.setInformationToMap(name, key, value);
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
