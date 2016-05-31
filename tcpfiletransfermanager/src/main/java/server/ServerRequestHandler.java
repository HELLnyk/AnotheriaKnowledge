package server;

import data.DataEvent;
import data.DataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

import static constants.Constants.*;

/**
 * Client`s request handler
 *
 * @author hellnyk
 */
public class ServerRequestHandler extends Thread implements Runnable{

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerRequestHandler.class);

    /**
     * {@link Socket} instance for commands
     */
    private Socket clientSocketCommand;

    /**
     * {@link Socket} instance for responses
     */
    private Socket clientSocketTransfer;

    /**
     * {@link DataStorage} instance for saving information (in this case - POJOs)
     */
    private DataStorage dataStorage;


    /**
     * Default constructor for initialization handler`s parameters
     *
     * @param clientSocketCommand
     *      {@link Socket} instance for commands;
     * @param clientSocketTransfer
     *      {@link Socket} instance for transfer data
     * @param dataStorage
     *      {@link DataStorage} instance for saving information
     */
    public ServerRequestHandler(Socket clientSocketCommand, Socket clientSocketTransfer, DataStorage dataStorage) {
        this.clientSocketCommand = clientSocketCommand;
        this.clientSocketTransfer = clientSocketTransfer;
        this.dataStorage = dataStorage;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
    }

    @Override
    public void run() {
        do {
            String command = getCommand();
            selectOperation(command);
        } while (!clientSocketTransfer.isClosed() && !clientSocketCommand.isClosed());
    }

    /**
     * get client`s command
     *
     * @return
     *      <code>String</code> instance, which represents command from client
     */
    private String getCommand(){
        String resultCommand = "";
        try {
            resultCommand = getMainInfoFromClient(clientSocketCommand);
        }catch (IOException e){
            LOGGER.error("Cannot read the command: " + e.getMessage());
        }
        return resultCommand;
    }

    /**
     * get parameters from client
     *
     * @param socket
     *      {@link Socket} instance for getting parameters
     * @return
     *      <code>String</code> result of getting parameters
     * @throws IOException
     *      if cannot get parameter
     */
    private String getMainInfoFromClient(Socket socket) throws IOException{
        byte[] array = new byte[BYTES];
        InputStream inputStream = socket.getInputStream();
        int result = inputStream.read(array);
        return new String(array, 0, result);
    }

    /**
     * select type of the operation
     *
     * @param operation
     *      <code>String</code> representation of client command
     */
    private void selectOperation(String operation){
        switch (operation){
            case "DIR":
                doDIR();
                break;
            case "PUT":
                doPUT();
                break;
            case "GET":
                doGET();
                break;
        }
    }


    /**
     * return to client all information in {@link DataStorage} instance
     */
    private void doDIR(){
        Set<Map.Entry<String, DataEvent>> listData = dataStorage.getAllData();
        try {
            OutputStream outStream = clientSocketTransfer.getOutputStream();

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(listData);
            outStream.flush();
        }catch (IOException e){
            LOGGER.error("Error in the server on DIR response" + e.getMessage());
        }
    }

    /**
     * put some {@link DataEvent} instance from the client to {@link DataStorage} instance in the server
     */
    private void doPUT(){

        try {
            String key = getMainInfoFromClient(clientSocketTransfer);

            DataEvent event;

            InputStream inStream = clientSocketTransfer.getInputStream();
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);

            event = (DataEvent) objectInStream.readObject();
            dataStorage.putData(key, event);


        }catch (ClassNotFoundException e){
            LOGGER.error("Class not found" + e.getMessage());

        } catch (IOException e) {
            LOGGER.error("Error in the server on PUT response" + e.getMessage());
        }
    }

    /**
     * get data from {@link DataStorage} server instance for the client by using <code>String</code> key
     */
    private void doGET() {
        try {
            String key = getMainInfoFromClient(clientSocketTransfer);
            DataEvent eventForSent = dataStorage.getData(key);
            OutputStream outStream = clientSocketTransfer.getOutputStream();
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(eventForSent);
        }catch (IOException e){
            LOGGER.error("Error in the server on GET response");
        }
    }
}
