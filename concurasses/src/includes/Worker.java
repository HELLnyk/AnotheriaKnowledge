package includes;

import java.util.Random;

/**
 * Worker, which writes information to the {@link AbstractDataStorage} instance
 *
 * @author hellnyk
 */
public class Worker implements Runnable {

    /**
     * capacity of how mach values will be put
     */
    private static final int DIFFERENT_VALUES_AMOUNT = 100000;

    /**
     * default length of every value
     */
    private static final int DIFFERENT_VALUE_LENGTH = 100;

    /**
     * array of {@link String} values
     */
    private static final String[] DIFFERENT_VALUES;

    /**
     *initialization of values
     */
    static {
        DIFFERENT_VALUES = generateString();
    }

    /**
     * {@link AbstractDataStorage} instance
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
     *      {@link AbstractDataStorage} instance, which will be used by this worker
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
        int counter = 1;
        for(String key: DIFFERENT_VALUES){
            dataStorage.getInfoToMap(name, counter, key);
            counter++;
        }
    }

    /**
     * generate different {@link String} values
     *
     * @return
     *      array of {@link String} values
     */
    private static String[] generateString(){
        String[] mass = new String[DIFFERENT_VALUES_AMOUNT];
        Random random = new Random();
        String elements = "ABCDEFGHIJKLMNOPQRSTUYWXVZ";
        for (int key = 0; key < DIFFERENT_VALUES_AMOUNT; key++) {
            char massChar[] = new char[DIFFERENT_VALUE_LENGTH];
            for (int letter = 0; letter < DIFFERENT_VALUE_LENGTH; letter++) {
                massChar[letter] = elements.charAt(random.nextInt(elements.length()));
            }
            mass[key] = new String(massChar);
        }
        return mass;
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
