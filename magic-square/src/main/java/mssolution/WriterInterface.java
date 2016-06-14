package mssolution;

/**
 * Interface for writing magic square
 *
 * @author hellnyk
 */
public interface WriterInterface {

    /**
     * write result of magic square
     *
     * @param matrix
     *      magic square instance
     * @param number
     *      the current number of result
     */
    void writeMatrix(int[][] matrix, int number);
}
