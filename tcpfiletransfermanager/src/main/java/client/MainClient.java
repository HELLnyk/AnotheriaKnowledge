package client;

import constants.MainClientCommands;
import data.DataEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static constants.Constants.*;

/**
 * @author hellnyk
 */
public class MainClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainClient.class);

    private Socket clientCommandSocket;
    private Socket clientDataTransferSocket;
    private MainClientCommands clientCommand;
    private String key;
    private Scanner scanner = new Scanner(System.in);

    public MainClient() {
        try {
            clientCommandSocket = new Socket(DEFAULT_HOST, DEFAULT_COMMAND_PORT);
            clientDataTransferSocket = new Socket(DEFAULT_HOST, DEFAULT_DATA_TRANSFER_PORT);
        }catch (IOException e){
            LOGGER.error("Problem with initialization client sockets: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            workClient();
        }catch (IOException e){
            LOGGER.error("Error in closing process of the sockets: " + e.getMessage());
        }
    }

    private static void workClient() throws IOException{
        MainClient client = new MainClient();

        String stop = "";
        do{
            client.initClientCommand();
            client.createRequestCommand(client.getClientCommand());
            System.out.println("for continue press ENTER for end - write stop");
            Scanner scanner = new Scanner(System.in);
            stop = scanner.nextLine();
        }while (!stop.equals("stop"));

        client.getClientCommandSocket().close();
        client.getClientDataTransferSocket().close();
        client.getScanner().close();

    }

    private void initClientCommand(){
        scanner = new Scanner(System.in);
        System.out.println("Select command: (DIR, PUT, GET) write it into the console");
        String command = scanner.nextLine();
        if (command.equals(MainClientCommands.DIR.toString())) {
            clientCommand = MainClientCommands.DIR;
        }
        else if(command.equals(MainClientCommands.PUT.toString())){
            clientCommand = MainClientCommands.PUT;
        }
        else if(command.equals(MainClientCommands.GET.toString())){
            clientCommand = MainClientCommands.GET;
        }
        else {
            initClientCommand();
        }
    }

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
    private void createRequestCommand(MainClientCommands clientCommand){
        switch (clientCommand){
            case DIR:
                doDIRCommand();
                break;
            case PUT:
                doPUTCommand();
                break;
            case GET:
                doGETCommand();
                break;
            default:
                doResponseWrongData();
                break;
        }
    }

    private void doResponseWrongData() {
        LOGGER.error("No initial data");
        return;
    }

    private void doDIRCommand(){
        try {
            sentCommand(clientCommand.toString());
            sentKey("Hi");

            InputStream inStream = clientDataTransferSocket.getInputStream();
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            Set<Map.Entry<String, DataEvent>> listResult = (Set<Map.Entry<String, DataEvent>>) objectInStream.readObject();
            if(listResult.size() == 0){
                System.out.println("Is empty storage");
            }else {
                System.out.println(listResult.size());
                printList(listResult);
            }

            //flushOutputStream();
            //objectInStream.close();
            //inStream.close();

        }catch (ClassNotFoundException e){
            LOGGER.error("Class not found: " + e.getMessage());
        }catch (IOException e){
            LOGGER.error("Cannot create DIR request: " + e.getMessage());
        }
    }

    private void doPUTCommand(){
        try{
            sentCommand(clientCommand.toString());
            initKey();
            sentKey(key);

            OutputStream outputStream = clientDataTransferSocket.getOutputStream();
            outputStream.flush();

            DataEvent dataEvent = new DataEvent("test", 1);

            ObjectOutputStream objectOutputSteam = new ObjectOutputStream(outputStream);
            objectOutputSteam.writeObject(dataEvent);

        }catch (IOException e){
            LOGGER.error("Cannot create PUT request: " + e.getMessage());
        }
    }


    private void doGETCommand(){
        try{
            sentCommand(clientCommand.toString());
            initKey();
            sentKey(key);

            DataEvent event = null;

            InputStream inputStream = clientDataTransferSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            event = (DataEvent) objectInputStream.readObject();
            System.out.println(event.toString());

        }catch (Exception e){
            LOGGER.error("Cannot create GET request: " + e.getMessage());
        }
    }

    private DataEvent setClientDataEvent(){

        System.out.println("Create DataEvent object.\n Enter String name: ");
        String name = scanner.nextLine();
        System.out.println("Enter id");
        int id = scanner.nextInt();
        return new DataEvent(name, id);
    }


    private void sentCommand(String command) throws IOException {
        clientCommandSocket.getOutputStream().write(command.getBytes());
        //clientCommandSocket.getOutputStream().close();
    }

    private void sentKey(String key) throws IOException{
        clientDataTransferSocket.getOutputStream().write(key.getBytes());
        //clientDataTransferSocket.getOutputStream().close();
    }

    private void flushOutputStream() throws IOException{
        clientDataTransferSocket.getOutputStream().flush();
        clientCommandSocket.getOutputStream().flush();
    }

    private void printList(Set<Map.Entry<String, DataEvent>> list){
        for(Map.Entry entry: list){
            System.out.println(String.format("key: %10s ::: value: %s", entry.getKey(), entry.getValue()));
        }
    }

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
