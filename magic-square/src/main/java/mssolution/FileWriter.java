package mssolution;

import java.io.*;

/**
 * Realization of {@link WriterInterface} for writing result to the file
 *
 * @author hellnyk
 */
public class FileWriter implements WriterInterface {

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
     */
    @Override
    public void writeMatrix(int [][] matrix, int number){
        int firstValueForNameFile = matrix[0][0];
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
        printData(stringBuffer, fileName + String.format("_%d.txt", firstValueForNameFile));
    }

    /**
     * writes {@link StringBuffer} instance into the file
     *
     * @param stringBuffer
     *      {@link StringBuffer} instance for writing
     * @param fileName
     *      concrete name of output file
     */
    private void printData(StringBuffer stringBuffer, String fileName){
        try(RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            file.skipBytes((int) file.length());
            file.writeBytes(stringBuffer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String getFileName() {
        return fileName;
    }
}