import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import server.MainServer;

/**
 * @author hellnyk
 */
public class TestServer extends Assert{

    @Test
    public void testStartServer(){
        new Thread(()->{
            MainServer.main(new String[]{});
        }).start();

        assertNotNull(MainServer.getServerSocketServiceCommand());
        assertNotNull(MainServer.getServerSocketServiceDataTransfer());
    }

    //@After
    public void stopServer(){
        new Thread(()->{
            MainServer.stop();
        }).start();
    }

    @Test
    public void testSimpleRun(){
        new TestServerDataStorage().addDataToStorage();
        MainServer.main(new String[]{});
    }

}
