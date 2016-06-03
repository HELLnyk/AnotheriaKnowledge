package service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server remote object implementation and registration
 *
 * @author hellnyk
 */
public class RemoteEchoServiceServer{

    /**
     * main method for creating {@link RemoteEchoServiceInterface} instance and adding this to Java RMI Registry
     *
     * @param args
     *      default arguments of main method
     */
    public static void main(String[] args){

        try{
            RemoteEchoServiceImpl echoService = new RemoteEchoServiceImpl();
            RemoteEchoServiceInterface echoServiceInterface = (RemoteEchoServiceInterface) UnicastRemoteObject.exportObject(echoService, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("EchoService", echoServiceInterface);

            System.out.println("Server bound...");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


}
