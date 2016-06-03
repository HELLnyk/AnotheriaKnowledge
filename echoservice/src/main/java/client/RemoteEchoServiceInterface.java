package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI interface using by client
 *
 * @author hellnyk
 */
public interface RemoteEchoServiceInterface extends Remote {

    /**
     * RMI method for reversing <code>String</code> value
     *
     * @param aString
     *      <code>String</code> value from the client for reversing
     * @return
     *      reversed string
     * @throws RemoteException
     *      if process of calling RMI method have some problem
     */
    String echoString(String aString) throws RemoteException;
}
