package server;

import data.DataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static constants.Constants.*;

/**
 * Main server for registration socket connection with client
 *
 * @author hellnyk
 */
public class MainServer {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainServer.class);

    /**
     * {@link ServerSocket} instance for getting client commands
     */
    private static ServerSocket serverSocketServiceCommand;

    /**
     * {@link ServerSocket} instance for  client`s response
     */
    private static ServerSocket serverSocketServiceDataTransfer;

    /**
     * {@link DataStorage} instance for saving client information
     */
    private static final DataStorage dataStorage = new DataStorage();

    /**
     * {@link ExecutorService} instance for requests from the different clients
     */
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * work of service
     */
    private static boolean work = true;

    /**
     * initialization of {@link ServerSocket} server instances
     */
    static {
        try {
            serverSocketServiceCommand = new ServerSocket(DEFAULT_COMMAND_PORT);
            serverSocketServiceDataTransfer = new ServerSocket(DEFAULT_DATA_TRANSFER_PORT);
        }catch (IOException ex){
            LOGGER.error("Can`t create server socket: " + ex.getMessage());
        }
    }

    /**
     * start of the server
     *
     * @param args
     *      default parameters
     */
    public static void main(String[] args) {
        running();
    }

    /**
     * work of the server
     */
    private static void running(){
        try {
            while (work){
                Socket clientSocketCommand = serverSocketServiceCommand.accept();
                Socket clientSocketTransfer = serverSocketServiceDataTransfer.accept();

                if(clientSocketCommand.getInetAddress().equals(clientSocketTransfer.getInetAddress())) {
                    executorService.execute(new ServerRequestHandler(clientSocketCommand, clientSocketTransfer, dataStorage));
                }
            }
        }catch (IOException e){
            LOGGER.error("Problem with client socket: " + e.getMessage());
            stop();
        }
    }

    /**
     *stop work of the server
     */
    public static void stop(){
        work = false;
        try {
            serverSocketServiceCommand.close();
            serverSocketServiceDataTransfer.close();
        } catch (IOException e) {
            LOGGER.error("Problem with stopping server: " + e.getMessage());
        }
    }

    /**
     *getters for {@link MainServer} instance
     */

    public static ServerSocket getServerSocketServiceCommand() {
        return serverSocketServiceCommand;
    }

    public static ServerSocket getServerSocketServiceDataTransfer() {
        return serverSocketServiceDataTransfer;
    }

    public static DataStorage getDataStorage() {
        return dataStorage;
    }
}
