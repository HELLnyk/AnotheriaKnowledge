import client.MainClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author hellnyk
 */
public class TestClient {


    @Before
    public void startServer(){
        new TestServer().testStartServer();
    }

    @After
    public void stopServer(){
        new TestServer().stopServer();
    }

    @Test
    public void testSome(){
        MainClient.main(new String[]{});
    }
}
