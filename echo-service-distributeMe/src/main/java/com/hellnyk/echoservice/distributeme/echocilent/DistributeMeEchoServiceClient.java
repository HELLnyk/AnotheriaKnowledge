package com.hellnyk.echoservice.distributeme.echocilent;

import com.hellnyk.echoservice.distributeme.echointerface.DistributeMeEchoServiceInterface;
import org.distributeme.core.ServiceLocator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author hellnyk
 */
public class DistributeMeEchoServiceClient {

    public static void main(String[] args) {

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
    private static String workClient(DistributeMeEchoServiceInterface service) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String clientString = bufferedReader.readLine();
        System.out.println("\nData in client side: " + clientString);
        String responseFromServer = service.echoString(clientString);
        System.out.println("Response from the server: " + responseFromServer);
        System.out.println("\nFor continue press Enter, otherwise - write stop");
        return bufferedReader.readLine();
    }

}
