package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static constants.Constants.*;

/**
 * @author hellnyk
 */
public class SimpleServer extends Thread {

    /**
     * {@link Socket} instance
     */
    Socket socket;

    /**
     * sequence number
     */
    int number;

    /**
     * executing server
     */
    private static boolean work = true;

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServer.class);

    /**
     * default constructor for
     * @param socket
     *      {@link Socket} instance for work
     * @param number
     *      sequence number
     *
     */
    public SimpleServer(Socket socket, int number) {
        this.socket = socket;
        this.number = number;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    /**
     * read {@link InputStream} instance into {@link Socket} instance from the client,
     * change this data and write {@link OutputStream} response
     */
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer = new byte[KB_BYTES * BYTES];

            int result = inputStream.read(buffer);

            String data = new String(buffer, 0, result);
            data = number + ": " + data;
            outputStream.write(data.getBytes());
            socket.close();
        }catch (Exception e){
            LOGGER.warn("Init error: " + e.getMessage());
        }
    }

    /**
     * start server executing
     */
    public static void runServer(){
        try {
            int connectionsCounter = 0;
            ServerSocket serverSocket = new ServerSocket(PORT, TIMEOUT, InetAddress.getByName(NAME_HOST));
            System.out.println("server is started");

            while (work){
                connectionsCounter++;
                new SimpleServer(serverSocket.accept(), connectionsCounter);
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
