package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.ServerSocket;

import static constants.Constants.*;

/**
 * server for
 *
 * @author hellnyk
 */
public class SimpleServer extends Thread {

    /**
     * executing server
     */
    private static boolean work = true;

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServer.class);

    /**
     * main method
     */

    public static void main(String[] args) {
        runServer();
    }

    /**
     * start server executing
     */
    private static void runServer(){
        try {
            int connectionsCounter = 0;
            ServerSocket serverSocket = new ServerSocket(PORT, TIMEOUT, InetAddress.getByName(NAME_HOST));
            System.out.println("server is started");

            while (work){
                connectionsCounter++;
                new SimpleHandler(serverSocket.accept(), connectionsCounter);
            }
        }catch (Exception e){
            LOGGER.warn("Init error: " + e.getMessage());
        }
    }

    /**
     * stop server executing
     */
    public static void stopServer(){
            work = false;
    }
}
