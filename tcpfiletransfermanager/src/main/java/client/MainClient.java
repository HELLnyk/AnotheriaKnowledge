package client;

import constants.MainClientCommands;
import data.DataEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static constants.Constants.*;

/**
 * Simple client
 *
 * @author hellnyk
 */
public class MainClient {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainClient.class);

    /**
     * {@link Socket} instance for command
     */
    private Socket clientCommandSocket;

    /**
     * {@link Socket} instance for data transfer
     */
    private Socket clientDataTransferSocket;

    /**
     * {@link MainClientCommands} type of client command
     */
    private MainClientCommands clientCommand;

    /**
     * <code>String</code> key for add or get value from the storage
     */
    private String key;

    /**
     * Get user data
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor with initialization of the sockets
     */
    public MainClient() {
        try {
            clientCommandSocket = new Socket(DEFAULT_HOST, DEFAULT_COMMAND_PORT);
            clientDataTransferSocket = new Socket(DEFAULT_HOST, DEFAULT_DATA_TRANSFER_PORT);
        }catch (IOException e){
            LOGGER.error("Problem with initialization client sockets: " + e.getMessage());
            Runtime.getRuntime().exit(-1);
        }
    }

    /**
     * main method
     *
     * @param args
     *      default args
     */
    public static void main(String[] args) {
        try {
            workClient();
        }catch (IOException e){
            LOGGER.error("Error in closing process of the sockets: " + e.getMessage());
        }
    }

    /**
     * start client work
     *
     * @throws IOException
     *      if it`s can not to close sockets
     */
    private static void workClient() throws IOException{
        MainClient client = new MainClient();
        String shutdownClientWork;
        do{
            client.initClientCommand();
            client.createRequestCommand(client.getClientCommand());
            System.out.println("for continue press ENTER for end - write stop");
            shutdownClientWork = client.getScanner().nextLine();

        }while (!shutdownClientWork.equals("stop"));
        client.getScanner().close();
        client.getClientCommandSocket().close();
        client.getClientDataTransferSocket().close();
    }

    /**
     * type of user entering information about command (select command)
     */
    private void initClientCommand(){
        System.out.println("Select command: (DIR, PUT, GET) write it into the console");
        String command = scanner.nextLine();
        if (command.equals(MainClientCommands.DIR.toString()) || command.equals("dir")) {
            clientCommand = MainClientCommands.DIR;
        }
        else if(command.equals(MainClientCommands.PUT.toString()) || command.equals("put")){
            clientCommand = MainClientCommands.PUT;
        }
        else if(command.equals(MainClientCommands.GET.toString()) || command.equals("get")){
            clientCommand = MainClientCommands.GET;
        }
        else {
            initClientCommand();
        }
    }

    /**
     * init user`s key
     */
    private void initKey(){
        System.out.println("Enter key");
        scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if(value.length() == 0){
            System.out.println("wrong key: try again");
            initKey();
        }
        key = value;
    }

    /**
     * create command for the server
     *
     * @param clientCommand
     *      {@link MainClientCommands} type of command
     */
    private void createRequestCommand(MainClientCommands clientCommand){
        switch (clientCommand) {
            case DIR:
                doDIRCommand();
                break;
            case PUT:
                doPUTCommand();
                break;
            case GET:
                doGETCommand();
                break;
        }
    }

    /**
     * create client request for DIR command and get result
     */
    private void doDIRCommand(){
        try {
            sentInfo(clientCommand.toString(), clientCommandSocket);

            InputStream inStream = clientDataTransferSocket.getInputStream();
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            Set<Map.Entry<String, DataEvent>> listResult = (Set<Map.Entry<String, DataEvent>>) objectInStream.readObject();
            if(listResult.size() == 0){
                System.out.println("Is empty storage");
            }else {
                System.out.println(listResult.size());
                printList(listResult);
            }

        }catch (ClassNotFoundException e){
            LOGGER.error("Class not found: " + e.getMessage());
        }catch (IOException e){
            LOGGER.error("Cannot create DIR request: " + e.getMessage());
        }
    }

    /**
     * create client request for PUT command and get result
     */
    private void doPUTCommand(){
        try{
            sentInfo(clientCommand.toString(), clientCommandSocket);
            initKey();
            sentInfo(key, clientDataTransferSocket);

            OutputStream outputStream = clientDataTransferSocket.getOutputStream();
            outputStream.flush();
            DataEvent dataEvent = setClientDataEvent();

            ObjectOutputStream objectOutputSteam = new ObjectOutputStream(outputStream);
            objectOutputSteam.writeObject(dataEvent);
            outputStream.flush();

        }catch (IOException e){
            LOGGER.error("Cannot create PUT request: " + e.getMessage());
        }
    }


    /**
     * create client request for GET command and get result
     */
    private void doGETCommand(){
        try{

            sentInfo(clientCommand.toString(), clientCommandSocket);
            initKey();
            sentInfo(key, clientDataTransferSocket);

            DataEvent event;

            InputStream inputStream = clientDataTransferSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            event = (DataEvent) objectInputStream.readObject();
            System.out.println(event.toString());

        }catch (Exception e){
            LOGGER.error("Cannot create GET request: " + e.getMessage());
        }
    }

    /**
     * create new {@link DataEvent} instance by user
     *
     * @return
     *      {@link DataEvent} instance
     */
    private DataEvent setClientDataEvent(){
        System.out.println("Create DataEvent object.\nEnter String name: ");
        String name = scanner.nextLine();
        System.out.println("Enter id");
        int id = scanner.nextInt();
        return new DataEvent(name, id);
    }


    /**
     * sent command or key to the server
     *
     * @param info
     *      <code>String</code> parameter for the server
     * @param socket
     *      which {@link Socket} insance will be used
     */
    private void sentInfo(String info, Socket socket){
        try{
            socket.getOutputStream().write(info.getBytes());
            socket.getOutputStream().flush();
        }catch (IOException e){
            LOGGER.error("Error with message: " + info + " : " + e.getMessage());
        }
    }

    /**
     * show all data from the server response
     *
     * @param list
     *      {@link Set} instance with {@link Map.Entry} values
     */
    private void printList(Set<Map.Entry<String, DataEvent>> list){
        for(Map.Entry entry: list){
            System.out.println(String.format("key: %10s ::: value: %s", entry.getKey(), entry.getValue()));
        }
    }

    /**
     * getters for {@link MainClient} parameters
     */

    public MainClientCommands getClientCommand() {
        return clientCommand;
    }

    public Socket getClientCommandSocket() {
        return clientCommandSocket;
    }

    public Socket getClientDataTransferSocket() {
        return clientDataTransferSocket;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
