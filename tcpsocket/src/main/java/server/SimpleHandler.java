package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static constants.Constants.BYTES;
import static constants.Constants.KB_BYTES;

/**
 * Handler the request on the server
 *
 * @author hellnyk
 */
public class SimpleHandler extends Thread{

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleHandler.class);

    /**
     * {@link Socket} instance
     */
    Socket socket;

    /**
     * sequence number
     */
    int number;

    /**
     * default constructor for
     * @param socket
     *      {@link Socket} instance for work
     * @param number
     *      sequence number
     *
     */
    public SimpleHandler(Socket socket, int number) {
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
            data ="<<Data from server>>\n" +
                    "Request: " + number +
                    "\ndata from client: " + data + "\n" +
            socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort() + "\n";

            outputStream.write(data.getBytes());
            socket.close();
        }catch (Exception e){
            LOGGER.error("Init error: " + e.getMessage());
        }
    }

}
