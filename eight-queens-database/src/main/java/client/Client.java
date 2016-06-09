package client;

import dao.InterfaceWorkDAO;
import dao.SimpleFactory;
import eightqueensolution.QueenProblem;
import eightqueensolution.SolutionEvent;
import jdbcconnector.JDBCConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Arrays;

/**
 * Simple client for work with  8 queen problem and database for results of 8 queen problem
 *
 * @author hellnyk
 */
public class Client {

    /**
     * Default value for checking clients input
     */
    private static final int ZERO = 0;

    /**
     * default value which represents client choice of the operation which will be chosen first
     */
    private static final int FIRST_OPTION = 1;

    /**
     * default value which represents client choice of the operation which will be chosen second (it also means last)
     */
    private static final int SECOND_OPTION = 2;

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    /**
     * {@link JDBCConnector} instance for connection to the database
     */
    JDBCConnector connector;

    /**
     * {@link InterfaceWorkDAO} instance for work with database
     */
    InterfaceWorkDAO interfaceWorkDAO;

    /**
     * {@link BufferedReader} instance, which reads the data received from the client
     */
    BufferedReader br;

    /**
     * Default constructor
     */
    public Client(){
       initParameters();
    }

    /**
     * Initialization parameters
     */
    private void initParameters(){
        connector = JDBCConnector.getINSTANCE();
        interfaceWorkDAO = SimpleFactory.getWorkDAO(connector.getConnection());
        br = new BufferedReader(new InputStreamReader(System.in));
    }


    /**
     * main function of the client, which starts his work
     *
     * @param args
     *      default {@code Sting} parameters of {@code main} method
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.chooseTypeOfWork();
    }

    /**
     * get client response for writing or getting info from the database
     */
    private void chooseTypeOfWork(){
        String dialogWithClient = "If you want calculate and write results into the database write 1, if" +
                "you want work with database - type 2";
        switch (enterOption(dialogWithClient)){
            case FIRST_OPTION:
                clientFillDatabase();
                break;
            case SECOND_OPTION:
                clientWorkDatabase();
                break;
        }
    }

    /**
     * get client`s choice between two variants
     *
     * @param forWhat
     *       name of options for the client
     * @return
     *      value of the selected option
     */
    private int enterOption(String forWhat){
        int option;
        do{
            System.out.println(forWhat);
            option = correctValue();
            if(option != FIRST_OPTION && option != SECOND_OPTION){
                System.out.println("wrong input");
            }
        }while (option != FIRST_OPTION && option != SECOND_OPTION);

        return option;
    }

    /**
     * read client`s data ({@code int} value)
     *
     * @return
     *      value entered by the user, or {@code -1} if
     *      entered value does not contain a parsable integer.
     */
    private int correctValue(){
        int correctInteger;
        try {
            correctInteger = Integer.parseInt(br.readLine());
        }catch (NumberFormatException e){
            correctInteger = -1;
        }catch (IOException e){
            LOGGER.error("error with process of reading value: " + e.getMessage());
            correctInteger = -1;
        }
        return correctInteger;
    }

    /**
     * add, based on the client's work, data into the database
     */
    private void clientFillDatabase(){
        String dialogWithClient = "Enter size of chessboard:";
        int capacityChessboard = enterValue(dialogWithClient);
        QueenProblem queenProblem = new QueenProblem(capacityChessboard, QueenProblem.PRINT_VECTOR);
        queenProblem.searchResult();
        List<SolutionEvent> list = queenProblem.getResults();
        for(SolutionEvent resultUnit: list){
            interfaceWorkDAO.insert(resultUnit.getArray());
        }
        setOptionContinueOfEnd();
    }

    /**
     * read client`s value for request
     *
     * @param forWhat
     *      name of request
     * @return
     *      selected value
     */
    private int enterValue(String forWhat){
        int value;
        do{
            System.out.println(forWhat);
            value = correctValue();
            if (value <= ZERO){
                System.out.println("Wrong input");
            }
        }while (value <= ZERO);
        return value;
    }

    /**
     * get client choice between operations with database
     */
    private void clientWorkDatabase(){
        String dialogClient = "Work with database, choice variant\n" +
                "1 - get all info from the database\n" +
                "2 - get info according to record in the database";

        switch (enterOption(dialogClient)){
            case FIRST_OPTION:
                getAll();
                break;
            case SECOND_OPTION:
                getIndex();
                break;
        }
    }

    /**
     * read all data from the database
     */
    private void getAll(){
        List resultFromDatabaseList =  interfaceWorkDAO.getAll();
        System.out.println("Database data:");
        for(Object object: resultFromDatabaseList){
            int[] array = (int[]) object;
            System.out.println(Arrays.toString(array));
        }
        setOptionContinueOfEnd();
    }

    /**
     * read data from database based on client`s input value
     */
    private void getIndex(){
        String dialogWithClient = "Enter index:";
        int index = enterValue(dialogWithClient);
        int[] inResultForGetOne = (int[]) interfaceWorkDAO.get(index);
        System.out.println(Arrays.toString(inResultForGetOne));
        setOptionContinueOfEnd();
    }

    /**
     * determines the client's wish to continue or quit
     */
    private void setOptionContinueOfEnd(){
        String dialogClient = "Write 1 for continue and 2 for end";
        switch (enterOption(dialogClient)){
            case FIRST_OPTION:
                chooseTypeOfWork();
                break;
            case SECOND_OPTION:
                closeBuffer();
                break;
        }
    }

    /**
     * close {@link BufferedReader} instance
     */
    private void closeBuffer(){
        try {
            br.close();
        } catch (IOException e) {
            LOGGER.error("Error with process of closing BufferReader instance: " + e.getMessage());
        }
    }
}
