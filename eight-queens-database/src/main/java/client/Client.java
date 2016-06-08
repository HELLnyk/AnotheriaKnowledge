package client;

import dao.InterfaceWorkDAO;
import dao.SimpleFactory;
import eightqueensolution.QueenProblem;
import eightqueensolution.SolutionEvent;
import jdbcconnector.JDBCConnector;
import org.configureme.ConfigurationManager;

import java.util.List;
import java.util.Arrays;

/**
 * @author hellnyk
 */
public class Client {

    public static void main(String[] args) {
        JDBCConnector connector = JDBCConnector.getINSTANCE();
        ConfigurationManager.INSTANCE.configure(connector);

        InterfaceWorkDAO interfaceWorkDAO = SimpleFactory.getWorkDAO(connector.getConnection());

//        QueenProblem queenProblem = new QueenProblem(8, QueenProblem.PRINT_VECTOR);
//        queenProblem.searchResult();
//        List<SolutionEvent> list = queenProblem.getResults();
//
//        System.out.println("in list");
//
//        for(SolutionEvent resultUnit: list){
//            interfaceWorkDAO.insert(resultUnit.getArray());
//        }

        int[] inResultForGetOne = (int[]) interfaceWorkDAO.get(94);
        System.out.println(Arrays.toString(inResultForGetOne));

        List<Object> rrr =  interfaceWorkDAO.getAll();

        int[] ResultForNameAll = (int[])rrr.get(0);
        System.out.println(Arrays.toString(ResultForNameAll));
    }
}
