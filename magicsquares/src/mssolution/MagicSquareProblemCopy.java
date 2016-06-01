package mssolution;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hellnyk
 */
public class MagicSquareProblemCopy {

    private int capacityOfSquare;
    private int capacityOfElements;
    private int magicConstant;
    private int[][] baseMatrix;
    private int totalResult = 0;


    public MagicSquareProblemCopy(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        magicConstant = initMagicConstant();
        baseMatrix = new int[capacityOfSquare][capacityOfSquare];
        capacityOfElements = capacityOfSquare * capacityOfSquare;
    }

    private int initMagicConstant(){
        return capacityOfSquare * (capacityOfSquare * capacityOfSquare + 1) / 2;
    }


    public void getSomeResultForOneElement(int firstValue){
        HashSet<Integer> set = new HashSet<>();
        baseMatrix[0][0] = firstValue;
        set.add(firstValue);
        fillMatrix(set, 0, 1, 0, false);
        System.out.println(totalResult);
    }

    public void getSomeResult(){
        HashSet<Integer> set = new HashSet<>();
        for (int element = 1; element <=capacityOfElements ; element++) {
            baseMatrix[0][0] = element;
            set.add(element);
            fillMatrix(set, 0, 1, 0, false);
            set.remove(element);
        }
            System.out.println(totalResult);
    }

    private boolean fillMatrix(Set<Integer> recordedElements, int rowIndex, int columnIndex, int baseRowAndColumn, boolean writeColumn){
        if(recordedElements.size() == capacityOfElements){
            if(checkDiagonals(baseMatrix)){
                totalResult++;
                System.out.println(totalResult);
                FileWriter.writeMatrix(baseMatrix, totalResult, baseMatrix[0][0]);
            }
        }
        else {
            for (int element = 1; element <= capacityOfElements; element++) {
                while (recordedElements.contains(element)) {
                    element++;
                }
                if(element > capacityOfElements) {
                    return false;
                }
                baseMatrix[rowIndex][columnIndex] = element;
                recordedElements.add(element);

                if(columnIndex == capacityOfSquare - 1){
                    if(isMagicArray(baseMatrix[baseRowAndColumn])) {
                        writeColumn = true;
                    }else {
                        removeElementFromMatrixAndSet(recordedElements, element, rowIndex, columnIndex);
                        continue;
                    }
                }
                if(writeColumn) {
                    if (rowIndex != capacityOfSquare - 1) {
                        if(!fillMatrix(recordedElements, rowIndex + 1, baseRowAndColumn, baseRowAndColumn, writeColumn)){
                            removeElementFromMatrixAndSet(recordedElements, element, rowIndex, columnIndex);
                            continue;
                        }
                    } else {
                        if (isMagicArray(copyColumn(baseMatrix, baseRowAndColumn))) {
                            if(baseRowAndColumn != capacityOfSquare - 1){
                                writeColumn = false;
                                baseRowAndColumn++;
                                rowIndex = baseRowAndColumn;
                                columnIndex = baseRowAndColumn - 1;
                            }
                        } else {
                            //writeColumn = true;
                            removeElementFromMatrixAndSet(recordedElements, element, rowIndex, columnIndex);
                            continue;
                        }
                    }
                }
                if (fillMatrix(recordedElements, rowIndex, columnIndex + 1, baseRowAndColumn, writeColumn)){
                    return true;
                }

                if(rowIndex == capacityOfSquare - 1){
                    writeColumn = true;
                }
                //removeElementFromMatrixAndSet(recordedElements, element, rowIndex, columnIndex);
                removeElementFromMatrix(element, rowIndex, columnIndex);
                recordedElements.remove(element);
            }
        }
        return false;
    }

    private void removeElementFromMatrixAndSet(Set set, int element, int rowIndex, int columnIndex){
        baseMatrix[rowIndex][columnIndex] = 0;
        set.remove(element);
    }

    private void removeElementFromMatrix(int element, int rowIndex, int columnIndex){
        if(element != baseMatrix[rowIndex][columnIndex])
            baseMatrix[capacityOfSquare - 1][columnIndex] = 0;
        else
            baseMatrix[rowIndex][columnIndex] = 0;
    }

    private int[] copyColumn(int[][] fromMatrix, int indexOfColumn){
        int [] destArray = new int[capacityOfSquare];
        for (int indexOfElementInColumn = 0; indexOfElementInColumn < destArray.length; indexOfElementInColumn++) {
            destArray[indexOfElementInColumn] = fromMatrix[indexOfElementInColumn][indexOfColumn];
        }
        return destArray;
    }

    public boolean checkDiagonals(int[][] matrix){
        int sumDownDiagonal = 0;
        int sumUpDiagonal = 0;
        for (int row = 0; row < matrix.length; row++) {
            sumUpDiagonal += matrix[row][row];
            sumDownDiagonal += matrix[(matrix.length - 1) - row][row];
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
}
