package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static constants.Constants.BUFFER_SIZE;

/**
 * Handler of UDP requests
 *
 * @author hellnyk
 */
public class UDPSimpleHandler extends Thread {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UDPSimpleHandler.class);

    /**
     * {@link DatagramSocket} instance of the server
     */
    DatagramSocket serverSocket;

    /**
     * {@link InetAddress} instance of socket
     */
    InetAddress inetAddress;

    /**
     * port number of socket
     */
    int port;

    /**
     * default constructor
     *
     * @param serverSocket
     *      {@link DatagramSocket} instance of the server
     */
    public UDPSimpleHandler(DatagramSocket serverSocket){
        this.serverSocket = serverSocket;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }


    /**
     * rewrite data from the client and creatng response
     */
    @Override
    public void run() {
        try{
            byte[] dataToSend = initDataFromClient();
            sendData(dataToSend);
        }catch (IOException e){
            LOGGER.error("Exception in handler: " + e.getMessage());
        }
    }

    /**
     * get data from client request as array of bytes
     * 
     * @return
     *      array of bytes from the request
     * @throws IOException 
     *      if an I/O error occurs
     */
    private byte[] initDataFromClient() throws IOException{
        String strFromClient = getDataFromClient();
        String strForSending = addServerInfo(strFromClient);
        return strForSending.getBytes();

    }

    /**
     * get data from client request as {@link String} value
     * @return
     *      {@link String} value
     * @throws IOException
     *      get data from client request
     */
    private String getDataFromClient() throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(packet);
        getClientAddress(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    /**
     * init client address for response
     *
     * @param packet
     *      {@link DatagramPacket} instance from server
     */
    private void getClientAddress(DatagramPacket packet){
        inetAddress = packet.getAddress();
        port = packet.getPort();
    }

    /**
     * add information to server response
     *
     * @param strForAdding
     *      {@link String} data from client request
     * @return
     *      {@link String} value with server information
     */
    private String addServerInfo(String strForAdding) {
        return strForAdding + "<<Server side>>\n" +
                "server Port: " + serverSocket.getLocalPort() + "\n";
    }

    /**
     * send  response
     *
     * @param data
     *      array of bytes such as message from the server to client
     * @throws IOException
     *      if an I/O error occurs.
     */
    private void sendData(byte[] data) throws IOException {
        DatagramPacket packetSend = new DatagramPacket(data, data.length, inetAddress, port);
        serverSocket.send(packetSend);
    }
}
