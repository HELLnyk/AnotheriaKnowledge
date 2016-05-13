package threading.concasses.includes;

import java.util.ConcurrentModificationException;
import java.util.Map;

/**
 * Main class for realization of adding elements to {@link Map}
 *
 * @author hellnyk
 */
public abstract class AbstractDataStorage<K,V> {

    /**
     * name of txt file where information about all records are saved
     */
    protected static final String FILE_WRITE = "/home/hellnyk/HELLnyk/gitRepositories/AnotheriaKnowledge/hashmapWrite.txt";

    /**
     * name of txt file, where are written information about trying of several thread create record in hashmap at the same time
     */
    protected static final String FILE_ERROR = "/home/hellnyk/HELLnyk/gitRepositories/AnotheriaKnowledge/hashmapWriteError.txt";

    /**
     * counter of the attempts to writing information to a {@link Map} instance
     */
    protected int counterWriting = 0;

    /**
     * {@link Map} instance/ where the information are stored
     */
    protected Map<K, V> map;

    /**
     * getter for counter of attempts
     *
     * @return
     *      value of counter
     */
    public int getCounterWriting() {
        return counterWriting;
    }

    /**
     * Write information to the {@link Map} instance
     *
     * @param name
     *      name of current thread, which writes information
     * @param key
     *      key for writing
     * @param value
     *      value for writing
     */
    public abstract void getInfoToMap(String name, K key, V value);


    /**
     * print result of adding elements to {@link Map} to txt file
     *
     * @param when
     *      "name of thread with "before" or "after" parameter of adding
     */
    protected void printResult(String when){
        StringBuilder sb = new StringBuilder(when + " ");
        try {
            for(Map.Entry entry: map.entrySet()){
                sb.append("key: " + entry.getKey() + " value: " + entry.getValue());
            }
        }catch (ConcurrentModificationException e){
            InformationToFile.write(FILE_ERROR, "Concurrent modification exeption");
            Runtime.getRuntime().exit(-100);
        }
        sb.append("\n");
        InformationToFile.write(FILE_WRITE, sb.toString());
    }


}
