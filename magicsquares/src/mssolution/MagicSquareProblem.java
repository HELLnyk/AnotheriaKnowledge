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

    public MagicSquareProblem(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        magicConstant = initMagicConstant();
        baseMatrix = new int[capacityOfSquare][capacityOfSquare];
    }

    public void getSomeResult(){
        fillMatrix(new HashSet<>(), 0, 0);
        System.out.println(totalResult);
    }

    private boolean fillMatrix(Set<Integer> recordedElements, int indexOfRecordingElement, int counterOfRow){

        int rowIndex = indexOfRecordingElement / capacityOfSquare;
        int columnIndex = indexOfRecordingElement % capacityOfSquare;

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
                baseMatrix[rowIndex][columnIndex] = element;
                if(recordedElements.size() % capacityOfSquare == 0){
                    if(isMagicArray(baseMatrix[counterOfRow])){
                            counterOfRow++;
                    }
                    else {
                        recordedElements.remove(element);
                        continue;
                    }
                }
                if (fillMatrix(recordedElements, indexOfRecordingElement + 1, counterOfRow)) {
                    return true;
                }
                baseMatrix[rowIndex][columnIndex] = 0;
                recordedElements.remove(element);
            }
        }
        return false;
    }

    private boolean writeMagicColumn(Set<Integer> recordedElementsInRow, int currentRow, int currentColumn){
        if(currentColumn == capacityOfSquare){
            if(isMagicArray(copyColumn(baseMatrix, currentRow))) {
                return true;
            }
        }
        else {
            for (int element = 1; element <= capacityOfSquare * capacityOfSquare; element++) {
                while (recordedElementsInRow.contains(element)) {
                    element++;
                }
                if (element > capacityOfSquare * capacityOfSquare) {
                    break;
                }
                baseMatrix[currentRow][currentColumn] = element;
                recordedElementsInRow.add(element);
                if(writeMagicColumn(recordedElementsInRow, currentRow, currentColumn + 1)){
                    return true;
                }
                baseMatrix[currentRow][currentColumn] = 0;
                recordedElementsInRow.remove(element);
            }
        }
        return false;
    }

    private int[] copyColumn(int[][] fromMatrix, int indexOfColumn){
        int [] destArray = new int[capacityOfSquare];
        for (int indexOfElementInColumn = 0; indexOfElementInColumn < destArray.length; indexOfElementInColumn++) {
            destArray[indexOfElementInColumn] = fromMatrix[indexOfElementInColumn][indexOfColumn];
        }
        return destArray;
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

    private boolean isMagicArray(int[] array){
        int sum = 0;
        for(int element: array){
            sum += element;
        }
        return magicConstant == sum;
    }

    private int initMagicConstant(){
        return capacityOfSquare * (capacityOfSquare * capacityOfSquare + 1) / 2;
    }

}


