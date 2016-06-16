import client.MainClient;
import data.DataEvent;
import data.DataStorage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.MainServer;

import java.util.Scanner;

/**
 * @author hellnyk
 */
public class TestServerDataStorage extends Assert {

    public static DataEvent[] storages = {
            new DataEvent("nameOne", 1),
            new DataEvent("nameTwo", 2),
            new DataEvent("nameThree" , 3),
            new DataEvent("nameFour", 4)
    };

    public static String[] keys = {
            "key One",
            "key Two",
            "key Three",
            "key Four"
    };

    @Before
    public void startThisServer(){
        new TestServer().testStartServer();
        addDataToStorage();
    }

    public void addDataToStorage(){
        DataStorage storage = MainServer.getDataStorage();
        for(int i = 0; i < storages.length; i++){
            storage.putData(keys[i], storages[i]);
        }
    }

//    @After
//    public void stopThisServer(){
//        new TestServer().stopServer();
//    }

    @Test
    public void testStorage(){
        assertEquals(MainServer.getDataStorage().getData(keys[1]), storages[1]);
    }

    @Test
    public void testDIR(){
        MainClient.main(new String[]{});
    }
}
