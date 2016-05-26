package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static constants.Constants.*;

/**
 * UDP Client
 *
 * @author hellnyk
 */
public class UDPSimpleClient {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UDPSimpleClient.class);

    /**
     * main method
     *
     * @param args
     *      default parameter: args[0] - is message to the server
     */
    public static void main(String[] args) {

        if(args.length == 0){
            LOGGER.error("No initial data");
        }

        try {
            DatagramSocket socket = new DatagramSocket();
            String userClientStr = rewriteString(args[0], socket);

            byte[] dataFromUser = userClientStr.getBytes();
            DatagramPacket packetFromClient = writeDataToPacket(dataFromUser, HOST, PORT);
            socket.send(packetFromClient);

            String result = dataFromServer(socket);
            socket.close();
            System.out.println(result);
        }catch (Exception e){
            LOGGER.error("Exception in client: " + e.getMessage());
        }
    }

    /**
     * add client info for {@link String} message
     *
     * @param stringForRewrite
     *      {@link String} parameter from main method to rewriting
     * @param socket
     *      client {@link DatagramSocket} socket
     * @return
     *      rewriting {@link String} message
     */
    private static String rewriteString(String stringForRewrite, DatagramSocket socket){
        return  "<<Client side>>\n" +
                "data: " + stringForRewrite + "\n" +
                "client port: " + socket.getLocalPort() + "\n";
    }

    /**
     * create {@link DatagramPacket} instance for sending
     *
     * @param data
     *      message is represented such as array of bytes
     * @param host
     *      {@link String} name of host
     * @param port
     *      port number
     * @return
     *      {@link DatagramPacket} instance for sending
     * @throws UnknownHostException
     *      if is wong host name
     */
    private static DatagramPacket writeDataToPacket(byte[] data, String host, int port) throws UnknownHostException{
        return new DatagramPacket(data, data.length, InetAddress.getByName(host), port);
    }

    /**
     * get message from the server
     * @param socket
     *      client {@link DatagramSocket} socket
     * @return
     *      {@link String} message
     * @throws IOException
     *      if is problem with receiving data
     *
     */
    private static String dataFromServer(DatagramSocket socket) throws IOException{
        byte[] dataFromServer = new byte[BUFFER_SIZE];
        DatagramPacket packetFromServer = new DatagramPacket(dataFromServer, dataFromServer.length);
        socket.receive(packetFromServer);
        return new String(packetFromServer.getData(), 0, packetFromServer.getLength());
    }
}
