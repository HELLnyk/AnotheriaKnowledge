import org.junit.Assert;
import org.junit.Test;
import data.DataEvent;

import java.io.*;

/**
 * @author hellnyk
 */
public class TestSerialize extends Assert {


    @Test
    public void testSerializableData(){
        DataEvent firstDataEvent = new DataEvent("name of this event", 90);

        try {
            FileOutputStream fileOutStream = new FileOutputStream("name.ser");
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(firstDataEvent);
            objectOutStream.close();
        }catch (Exception e){
            fail("Exception with Output: " + e.toString());
        }

        try {
            FileInputStream fileInStream = new FileInputStream("name.ser");
            ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
            DataEvent testDataEvent = (DataEvent) objectInStream.readObject();
            objectInStream.close();

            assertEquals("name of this event", testDataEvent.getName());
            assertEquals(90, testDataEvent.getId());

            new File("name.ser").delete();
        }catch (Exception e){
            fail("Exception with Input: "  + e.toString());
        }
    }

}
