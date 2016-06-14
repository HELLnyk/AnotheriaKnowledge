package client;

import dao.InterfaceWorkDAO;
import dao.SimpleFactory;
import eightqueensolution.QueenProblem;
import eightqueensolution.SolutionEvent;
import jdbcconnector.JDBCConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Arrays;

/**
 * Simple client for work with  8 queen problem and database for results of 8 queen problem
 *
 * @author hellnyk
 */
public class Client extends ClientMain {

    /**
     * {@link JDBCConnector} instance for connection to the database
     */
    JDBCConnector connector;

    /**
     * {@link InterfaceWorkDAO} instance for work with database
     */
    InterfaceWorkDAO interfaceWorkDAO;

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
    }

    /**
     * main function of the client, which starts his work
     *
     * @param args
     *      default {@code Sting} parameters of {@code main} method
     */
    public static void main(String[] args) {
        ClientMain client = new Client();
        client.chooseTypeOfWork();
    }

    /**
     * add, based on the client's work, data into the database
     */
    protected void clientFillDatabase(){
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
     * read all data from the database
     */
    protected void getAll(){
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
    protected void getIndex(){
        String dialogWithClient = "Enter index:";
        int index = enterValue(dialogWithClient);
        int[] inResultForGetOne = (int[]) interfaceWorkDAO.get(index);
        System.out.println(Arrays.toString(inResultForGetOne));
        setOptionContinueOfEnd();
    }

}
