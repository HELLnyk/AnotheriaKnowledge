package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

import static constants.Constants.*;

/**
 * Sample of client for tcp socket
 *
 * @author hellnyk
 */
public class SimpleClient{

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    /**
     * main method
     *
     * @param args
     *      argument for construct
     */
    public static void main(String[] args) {

        if(args.length == 0){
            LOGGER.error("There are no initial data");
            return;
        }

        try {
            Socket socket = new Socket("localhost", PORT);
            System.out.println("<<Client data>>");
            System.out.println("client socket: " + socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort());
            socket.getOutputStream().write(args[0].getBytes());

            byte[] buffer = new byte[KB_BYTES * BYTES];
            int result = socket.getInputStream().read(buffer);
            String data = new String(buffer, 0, result);

            System.out.println(data);

        }catch (IOException e){
            LOGGER.warn("Init error: " + e.getMessage());
        }
    }
}
