import client.SimpleClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.SimpleServer;

/**
 * @author hellnyk
 */
public class ClientServerTest {

    @Before
    public void testServerStart(){
        new Thread(new Runnable() {
            public void run() {
                SimpleServer.main(new String[]{});
            }
        }).start();
    }

    @After
    public void testServerSop(){
       new Thread(new Runnable() {
           public void run() {
               SimpleServer.stopServer();
           }
       }).start();
    }

    @Test
    public void testWithoutParameter(){
        SimpleClient.main(new String[]{});
    }


    @Test
    public void testWork(){
        String[] elements  = {"test", "element", "value", "string", "anotheria"};
        for(String element: elements){
            SimpleClient.main(new String[]{element});
        }
    }
}
