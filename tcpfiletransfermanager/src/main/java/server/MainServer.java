package server;

import data.DataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static constants.Constants.*;

/**
 * @author hellnyk
 */
public class MainServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServer.class);

    private static ServerSocket serverSocketServiceCommand;
    private static ServerSocket serverSocketServiceDataTransfer;
    private static final DataStorage dataStorage = new DataStorage();

    private static boolean work = true;

    static {
        try {
            serverSocketServiceCommand = new ServerSocket(DEFAULT_COMMAND_PORT);
            serverSocketServiceDataTransfer = new ServerSocket(DEFAULT_DATA_TRANSFER_PORT);
        }catch (IOException ex){
            LOGGER.error("Can`t create server socket: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        running();
    }

    private static void running(){
        try {
            while (work){
                Socket clientSocketCommand = serverSocketServiceCommand.accept();
                Socket clientSocketTransfer = serverSocketServiceDataTransfer.accept();

                new ServerRequestHandler(clientSocketCommand, clientSocketTransfer, dataStorage).startHandler();
            }
        }catch (IOException e){
            LOGGER.error("Problem with client socket: " + e.getMessage());
            stop();
        }
    }

    public static void stop(){
        work = false;
        try {
            serverSocketServiceCommand.close();
            serverSocketServiceDataTransfer.close();
        } catch (IOException e) {
            LOGGER.error("Problem with stopping server: " + e.getMessage());
        }
    }

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
