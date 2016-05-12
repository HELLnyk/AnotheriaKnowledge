package threading.concasses.includes;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Writer of text message for different files
 *
 * @author hellnyk
 */
public class InformationToFile {

    /**
     * write information to the file
     *
     * @param fileName
     *      name of the file, where information will be written
     * @param info
     *      name of the message, where information will be written
     */
    public static void write(String fileName, String info){
        try(RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            file.skipBytes((int) file.length());
            file.writeBytes(info + "\n");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
