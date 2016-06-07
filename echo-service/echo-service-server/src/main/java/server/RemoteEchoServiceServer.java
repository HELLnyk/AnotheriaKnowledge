package server;

import remoteinterface.*;

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
            RemoteEchoServiceInterfaceImpl echoService = new RemoteEchoServiceInterfaceImpl();
            RemoteEchoServiceInterface echoServiceInterface = (RemoteEchoServiceInterface) UnicastRemoteObject.exportObject(echoService, 0);
            Registry registry = LocateRegistry.createRegistry(RemoteEchoServiceParameter.DEFAULT_SERVER_PORT);
            registry.bind("EchoService", echoServiceInterface);

            System.out.println("Server bound...");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


}
