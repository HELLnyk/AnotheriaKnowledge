package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Client for RMI
 *
 * @author hellnyk
 */
public class EchoServiceClient {

    /**
     * Default rmi url for connection
     */
    private static final String DEFAULT_RMI_URL = "localhost";

    /**
     * main method in the client
     *
     * @param args
     *      default parameters
     */
    public static void main(String[] args){

        RemoteEchoServiceInterface echoServiceInterface;

        try{
            Registry registry = LocateRegistry.getRegistry(DEFAULT_RMI_URL);
            echoServiceInterface = (RemoteEchoServiceInterface) registry.lookup("EchoService");

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        System.out.println("For work with rmi service enter some string");
        String runExecute;

        do{
            try {
                runExecute = workClient(echoServiceInterface);
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }while (!runExecute.equals("stop"));
    }

    /**
     * work of client
     *
     * @param service
     *      {@link RemoteEchoServiceInterface} instance for rmi method using by this client
     * @return
     *      <code>String</code> message about extension of the client`s work
     * @throws Exception
     *      if remote method can`t be called
     */
    private static String workClient(RemoteEchoServiceInterface service) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter String");
        String clientString = bufferedReader.readLine();
        System.out.println("\nData in client side: " + clientString);
        String responseFromServer = service.echoString(clientString);
        System.out.println("Response from the server: " + responseFromServer);
        System.out.println("\nFor continue press Enter, otherwise - write stop");
        return bufferedReader.readLine();
    }
}
