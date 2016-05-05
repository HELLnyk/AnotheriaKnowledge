package logic.basicprogramming.magicsquares.mssolution;

import java.io.*;

/**
 * @author hellnyk
 */
public class FileWriter {

    /**
     * filename, where information will be recorded
     */
    private static final String FILE_NAME = "/home/hellnyk/HELLnyk/gitRepositories/AnotheriaKnowledge/matrixoutput.txt";

    /**
     * constructs {@link StringBuffer} instance for writing into the file
     *
     * @param matrix
     *      matrix which will be written into the file
     * @param number
     *      current number of result matrix
     */
    public static void writeMatrix(int [][] matrix, int number){
        StringBuffer stringBuffer = new StringBuffer();
        String title = "**** " + number + " matrix ****\n";
        stringBuffer.append(title);
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                String element;
                if(matrix[row][column] < 10)
                   element = matrix[row][column] + "  ";
                else
                   element = matrix[row][column] + " ";

                stringBuffer.append(element);
            }
            stringBuffer.append("\n");
        }
        stringBuffer.append("\n");
        printData(stringBuffer);
    }

    /**
     * writes {@link StringBuffer} instance into the file
     *
     * @param stringBuffer
     *      {@link StringBuffer} instance for writing
     */
    private static void printData(StringBuffer stringBuffer){
        try(RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.skipBytes((int) file.length());
            file.writeBytes(stringBuffer.toString());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
