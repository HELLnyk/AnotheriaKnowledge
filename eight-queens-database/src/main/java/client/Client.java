package client;

import databaseconnect.DatabaseConnect;
import databaseconnect.InterfaceWorkDAO;
import databaseconnect.SimpleFactory;
import databaseconnect.hibernateconnect.EightQueenEntity;
import eightqueensolution.QueenProblem;
import eightqueensolution.SolutionEvent;

import java.util.List;
import java.util.Arrays;

/**
 * Simple client for work with  8 queen problem and database for results of 8 queen problem
 *
 * @author hellnyk
 */
public class Client extends ClientMain {


    /**
     * type for choose connection with JDBC
     */
    private static final int ONE = 1;

    /**
     * type for choose connection with Hibernate
     */
    private static final int TWO = 2;

    /**
     * type of c
     */
    DatabaseConnect databaseConnect = null;

    /**
     * {@link InterfaceWorkDAO} instance for work with database
     */
    InterfaceWorkDAO interfaceWorkDAO;


    /**
     * start client
     *
     * @param args
     *      default arguments for {@code main} method
     */
    public static void main(String[] args) {
        ClientMain client = new Client();
        client.initConnect();
        client.chooseTypeOfWork();
    }

    @Override
    protected void initConnect(){
        String dialogWithClient = "Enter type for working database\n" +
                "1 - JDBC\n2 - Hibernate";
        int option = enterOption(dialogWithClient);
        switch (option){
            case ONE:
                databaseConnect = DatabaseConnect.JDBC;
                break;
            case TWO:
                databaseConnect = DatabaseConnect.HIBERNATE;
                break;
        }
        interfaceWorkDAO = SimpleFactory.getWorkDAO(databaseConnect);
    }

    protected void clientFillDatabase(){
        String dialogWithClient = "Enter size of chessboard:";
        int capacityChessboard = enterValue(dialogWithClient);
        QueenProblem queenProblem = new QueenProblem(capacityChessboard, QueenProblem.PRINT_VECTOR);
        queenProblem.searchResult();

        List<SolutionEvent> list = queenProblem.getResults();
        for(SolutionEvent resultUnit: list){
            switch (databaseConnect){
                case JDBC:
                    interfaceWorkDAO.insert(resultUnit.getArray());
                    break;
                case HIBERNATE:
                    interfaceWorkDAO.insert(new EightQueenEntity(resultUnit.getArray()));
                    break;
            }
        }
        setOptionContinueOfEnd();
    }

    protected void getAll(){
        List resultFromDatabaseList =  interfaceWorkDAO.getAll();
        System.out.println("Database data:");
        int[] array = null;
        for(Object object: resultFromDatabaseList){
            switch (databaseConnect){
                case JDBC:
                     array = (int[]) object;
                    break;
                case HIBERNATE:
                    EightQueenEntity entity = (EightQueenEntity) object;
                    array = entity.getSolutionResultArray();
                    break;
            }

            System.out.println(Arrays.toString(array));
        }
        setOptionContinueOfEnd();
    }

    protected void getIndex(){
        String dialogWithClient = "Enter index:";
        int index = enterValue(dialogWithClient);
        switch (databaseConnect){
            case JDBC:
                showOutputAfterJDBC(index);
                break;
            case HIBERNATE:
                showOutputAfterHibernate(index);
                break;
        }
        setOptionContinueOfEnd();
    }

    /**
     * output one record in the database after using jdbc
     *
     * @param index
     *      index of record in the database
     */
    private void showOutputAfterJDBC(int index) {
        int[] inResultForGetOne = (int[]) interfaceWorkDAO.get(index);
        System.out.println(Arrays.toString(inResultForGetOne));
    }


    /**
     * output one record in the database after using hibernate
     *
     * @param index
     *      index of record in the database
     */
    private void showOutputAfterHibernate(int index) {
        EightQueenEntity entity = (EightQueenEntity) interfaceWorkDAO.get(index);
        System.out.println(Arrays.toString(entity.getSolutionResultArray()));
    }
}
