package advanced;

import java.util.ConcurrentModificationException;
import java.util.Map;

/**
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
    protected static final String FILE_ERROR = "/home/hellnyk/HELLnyk/gitRepositories/AnotheriaKnowledge/hashmapError.txt";

    /**
     * {@link Map} instance/ where the information are stored
     */
    protected Map<K, V> map;

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
    public void getInfoToMap(String name, K key, V value){
        printResult("thread " + name + " (before)");
        map.put(key, value);
        printResult("thread " + name + " (after)");
    }


    /**
     * print contents of {@link Map} concrete instance to the file
     *
     * @param whoAndWhen
     *      name of thread and time of operation of the writing
     */
    protected void printResult(String whoAndWhen){
        StringBuilder sb = new StringBuilder();
        sb.append(whoAndWhen + " ");
        try {
            for(Map.Entry entry: map.entrySet()){
                sb.append("key: " + entry.getKey() + " value: " + entry.getValue() + " ");
            }
        }catch (ConcurrentModificationException e){
            InformationToFile.write(FILE_ERROR, "Several threads trying to read information to the file");
            System.out.println("Error: exit code 121. See hashmapError.txt for details");
            System.exit(121);
        }
        sb.append("\n");
        InformationToFile.write(FILE_WRITE, sb.toString());
    }
}
