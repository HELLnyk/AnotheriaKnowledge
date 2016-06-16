import client.UDPSimpleClient;
import org.junit.Before;
import org.junit.Test;
import server.UDPSimpleServer;

/**
 * @author hellnyk
 */
public class UDPTest {


    @Before
    public void testStartUPDServer(){
        new Thread(()-> {
            UDPSimpleServer.main(new String[]{});
        }).start();
    }

    @Test
    public void testConnection(){
        String[] ss = {"anotheria", "test", "Lorem ipsum"};
        //UDPSimpleClient.main(new String[]{});
        for (String s: ss) {
            UDPSimpleClient.main(new String[]{s});
        }
    }
}
