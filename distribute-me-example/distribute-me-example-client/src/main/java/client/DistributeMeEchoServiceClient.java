package client;

import dmexample.DistributeMeEchoServiceInterface;
import org.distributeme.core.ServiceLocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.LogManager;

/**
 * Remote interface {@link DistributeMeEchoServiceInterface} client
 *
 * @author hellnyk
 */
public class DistributeMeEchoServiceClient {

    /**
     * Start client
     *
     * @param args
     *      default parameters
     */
    public static void main(String[] args) {

        //setLogging();

        DistributeMeEchoServiceInterface serviceInterface = ServiceLocator.getRemote(DistributeMeEchoServiceInterface.class);

        System.out.println("For work with rmi service enter some string");
        String stringDataClientWork;

        do{
            try {
                System.out.println("enter String");
                stringDataClientWork = workClient(serviceInterface);
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }while (!stringDataClientWork.equals("stop"));

    }

//    private static void setLogging(){
//        try {
//            LogManager.getLogManager().readConfiguration(DistributeMeEchoServiceClient.class.getResourceAsStream("/log4j.properties"));
//        } catch (IOException e) {
//            System.err.print("Could not setup logger configuration: " + e.getMessage());
//        }
//    }

    /**
     * Dialog with the user
     *
     * @param service
     *      {@link DistributeMeEchoServiceInterface} instance
     * @return
     *      {@code String} instance from user
     * @throws Exception
     *      if there are errors in the data entry
     */
    private static String workClient(DistributeMeEchoServiceInterface service) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String clientString = bufferedReader.readLine();
        System.out.println("\nData in client side: " + clientString);
        String responseFromServer = service.echoString(clientString);
        System.out.println("Response from the server: " + responseFromServer);
        System.out.println("\nFor continue press Enter, otherwise - write stop");
        return bufferedReader.readLine();
    }

}
