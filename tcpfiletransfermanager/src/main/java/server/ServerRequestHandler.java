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
 * @author hellnyk
 */
public class ServerRequestHandler extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerRequestHandler.class);

    private Socket clientSocketCommand;
    private Socket clientSocketTransfer;
    private DataStorage dataStorage;


    public ServerRequestHandler(Socket clientSocketCommand, Socket clientSocketTransfer, DataStorage dataStorage) {
        this.clientSocketCommand = clientSocketCommand;
        this.clientSocketTransfer = clientSocketTransfer;
        this.dataStorage = dataStorage;
        setDaemon(true);
        setPriority(NORM_PRIORITY);

    }

    public void startHandler(){
        start();
    }

    @Override
    public void run() {
        String command = getCommand();
        selectOperation(command);

        try {
            clientSocketCommand.close();
            clientSocketTransfer.close();
        }catch (IOException e){
            LOGGER.error("Cannot close sockets: " + e.getMessage());
        }
    }

    private String getCommand(){
        String resultCommand = "";
        try {
            resultCommand = getMainInfoFromClient(clientSocketCommand);
        }catch (IOException e){
            LOGGER.error("Cannot read the command: " + e.getMessage());
        }
        return resultCommand;
    }

    private String getMainInfoFromClient(Socket socket) throws IOException{
        byte[] array = new byte[BYTES];
        InputStream inputStream = socket.getInputStream();
        int result = inputStream.read(array);
        return new String(array, 0, result);
    }

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


    private void doDIR(){
        Set<Map.Entry<String, DataEvent>> listData = dataStorage.getAllData();
        try {
            OutputStream outStream = clientSocketTransfer.getOutputStream();
            outStream.flush();
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(listData);

            //objectOutStream.close();
            //outStream.close();

        }catch (IOException e){
            LOGGER.error("Error in the server on DIR response" + e.getMessage());
        }
    }

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
