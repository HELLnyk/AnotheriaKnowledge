package cientmsd;

import client.ClientMain;
import dao.MainDAO;
import entities.MagicSquareEntity;
import magicsquareclient.MagicSquareClient;

import java.util.List;

/**
 * Client for work with magic square database
 *
 * @author hellnyk
 */
public class ClientForMSDatabase extends ClientMain{

    /**
     * DAO realization for some (insert, get, get all) operations with database
     */
    private MainDAO mainDAO;

    /**
     * main method of {@link ClientForMSDatabase} instance. Starts program
     * @param args
     */
    public static void main(String[] args) {
        ClientForMSDatabase clientForMSDatabase = new ClientForMSDatabase();
        clientForMSDatabase.chooseTypeOfWork();
    }

    public ClientForMSDatabase() {
        mainDAO = new MainDAO();
    }

    @Override
    protected void clientFillDatabase() {
        String dialogWithClient = "Enter size of magic square";
        int capacityOfSquare = enterValue(dialogWithClient);
        MagicSquareClient.runSimple(capacityOfSquare, new DatabaseWriter(mainDAO));
        setOptionContinueOfEnd();
    }

    @Override
    protected void getAll() {
        List<MagicSquareEntity> list = mainDAO.getAll();
        for(MagicSquareEntity elem: list){
            printMatrix(elem.getSolutionResultArray());
        }
    }

    @Override
    protected void getIndex() {
        String dialogWithClient = "Enter index:";
        int index = enterValue(dialogWithClient);
        int[][] result = mainDAO.get(index).getSolutionResultArray();
        printMatrix(result);
        setOptionContinueOfEnd();
    }

    /**
     * print result of {@code int[][]} array
     *
     * @param matrix
     *      {@code int[][]} array for printing
     */
    private void printMatrix(int[][] matrix){
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                System.out.print(matrix[row][column] + " ");
            }
            System.out.println();
        }
    }
}
