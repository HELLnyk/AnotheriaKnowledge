package logic.basicprogramming.magicsquares.mssolution;

import java.io.*;

/**
 * @author hellnyk
 */
public class FileWriter {

    private static final String FILE_NAME = "/home/hellnyk/HELLnyk/gitRepositories/AnotheriaKnowledge/matrixoutput.txt";

    public static void writeMatrix(int [][] matrix, int number){

        StringBuffer stringBuffer = new StringBuffer();
        String title = "**** " + number + " matrix ****\n";
        stringBuffer.append(title);
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                String element = matrix[row][column] + " ";
                stringBuffer.append(element);
            }
            stringBuffer.append("\n");
        }
        stringBuffer.append("\n");

        try(RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.skipBytes((int) file.length());
            file.writeBytes(stringBuffer.toString());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
