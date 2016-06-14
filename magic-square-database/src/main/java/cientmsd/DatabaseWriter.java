package cientmsd;

import dao.MainDAO;
import entities.MagicSquareEntity;
import mssolution.WriterInterface;

/**
 * Realization of {@link WriterInterface} for writing result to the database
 *
 * @author hellnyk
 */
public class DatabaseWriter implements WriterInterface {

    /**
     * {@link MainDAO} instance for DAO realization for some (insert, get, get all) operations with database
    */
    private MainDAO mainDAO;

    /**
     * Default constructor
     *
     * @param mainDAO
     *      {@link MainDAO} instance
     */
    public DatabaseWriter(MainDAO mainDAO) {
        this.mainDAO = mainDAO;
    }

    @Override
    public void writeMatrix(int[][] matrix, int number) {
        MagicSquareEntity msEntity = new MagicSquareEntity(matrix);
        mainDAO.insert(msEntity);
    }
}
