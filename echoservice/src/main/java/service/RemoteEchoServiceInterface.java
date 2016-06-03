package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI interface
 *
 * @author hellnyk
 */
public interface RemoteEchoServiceInterface extends Remote {

    /**
     * remote method for reversing string
     *
     * @param aString
     *      string for reversing
     * @return
     *      reversed string
     * @throws RemoteException
     *      if, can`t correct call remote method
     */
    String echoString(String aString) throws RemoteException;
}
