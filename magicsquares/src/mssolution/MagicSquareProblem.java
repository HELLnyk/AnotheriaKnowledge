package mssolution;

import java.util.*;

/**
 * Resolve magic square problem
 *
 * @author hellnyk
 */
public class MagicSquareProblem{

    private int capacityOfSquare;
    private int magicConstant;
    private int[][] baseMatrix;
    private int totalResult = 0;
    private int t = 0;


    public MagicSquareProblem(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        magicConstant = initMagicConstant();
        baseMatrix = new int[capacityOfSquare][capacityOfSquare];
    }

    public void getSomeResult(){
        fillMatrix(new HashSet<>(), 0);
        System.out.println(totalResult);
    }


    private boolean fillMatrix(Set<Integer> recordedElements, int indexOfRecordingElement){

        if(indexOfRecordingElement == capacityOfSquare*capacityOfSquare){
            if(checkMatrix(baseMatrix)) {
                totalResult++;
                FileWriter.writeMatrix(baseMatrix, totalResult, baseMatrix[0][0]);
            }
        }else {
            for (int element = 1; element <= capacityOfSquare * capacityOfSquare; element++) {
                while (recordedElements.contains(element)) {
                    element++;
                }
                if(element > capacityOfSquare*capacityOfSquare)
                    break;
                recordedElements.add(element);
                int rowIndex = indexOfRecordingElement / capacityOfSquare;
                int columnIndex = indexOfRecordingElement % capacityOfSquare;
                baseMatrix[rowIndex][columnIndex] = element;

                if (fillMatrix(recordedElements, indexOfRecordingElement + 1))
                    return true;

                baseMatrix[rowIndex][columnIndex] = 0;
                recordedElements.remove(element);
            }
        }
        return false;
    }


    public boolean checkMatrix(int[][] matrix){
        int sumDownDiagonal = 0;
        int sumUpDiagonal = 0;
        for (int row = 0; row < matrix.length; row++) {
            int sumCurrentRow = 0;
            int sumCurrentColumn = 0;
            sumUpDiagonal += matrix[row][row];
            sumDownDiagonal += matrix[(matrix.length - 1) - row][row];
            for (int column = 0; column < matrix.length; column++) {
                sumCurrentRow += matrix[row][column];
                sumCurrentColumn += matrix[column][row];
            }
            if((sumCurrentColumn != magicConstant) || (sumCurrentRow != magicConstant)){
                return false;
            }
        }
        return sumDownDiagonal == magicConstant && sumUpDiagonal == magicConstant;
    }

    private int initMagicConstant(){
        return capacityOfSquare * (capacityOfSquare * capacityOfSquare + 1) / 2;
    }

}


