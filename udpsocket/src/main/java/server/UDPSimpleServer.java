package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramSocket;

import static constants.Constants.PORT;

/**
 * UDP listener server
 *
 * @author hellnyk
 */
public class UDPSimpleServer {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UDPSimpleServer.class);


    /**
     * start server
     *
     * @param args
     *      default parameter for main method
     */
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            serverSocket.setSoTimeout(0);
            while (true){
                new UDPSimpleHandler(serverSocket);

            }
        }catch (Exception e){
            LOGGER.error("Exception is server: " + e.getMessage());
        }
    }
}
