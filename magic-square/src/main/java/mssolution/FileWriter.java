package mssolution;

import java.io.*;

/**
 * @author hellnyk
 */
public class FileWriter {

    /**
     * filename, where information will be recorded
     */
    private static String fileName = "/home/hellnyk/HELLnyk/gitRepositories/AnotheriaKnowledge/magic-square/result/matrixoutput";

    /**
     * constructs {@link StringBuffer} instance for writing into the file
     *
     * @param matrix
     *      matrix which will be written into the file
     * @param number
     *      current number of result matrix
     * @param value
     *      the name of the output file, which will be recorded
     *      specific values of the magic square according to the first element
     */
    public static void writeMatrix(int [][] matrix, int number, int value){
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
        printData(stringBuffer, fileName + String.format("_%d.txt",value));
    }

    /**
     * writes {@link StringBuffer} instance into the file
     *
     * @param stringBuffer
     *      {@link StringBuffer} instance for writing
     * @param fileName
     *      concrete name of output file
     */
    private static void printData(StringBuffer stringBuffer, String fileName){
        try(RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            file.skipBytes((int) file.length());
            file.writeBytes(stringBuffer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}