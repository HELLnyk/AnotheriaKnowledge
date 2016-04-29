package logic.basicprogramming.magicsquares.mssolution;

import java.util.*;

/**
 * Resolve magic square problem
 *
 * @author hellnyk
 */
public class MagicSquareProblem{

    private int total = 0;

    private int dimension;

    public MagicSquareProblem(int dimension){
        this.dimension = dimension;
    }

    public void getResult(){
        int[] totalArray = initArray(dimension*dimension);
        getResultBasedOnMagicArray(totalArray);
        getResultBasedOnMagicArray(initReverseArray(totalArray));
    }

    private int[] initArray(int dimension){
        int[] numbersArray = new int[dimension];
        for (int numberValue = 0; numberValue < numbersArray.length; numberValue++) {
            numbersArray[numberValue] = numberValue + 1;
        }
        return numbersArray;
    }

    private int[] initReverseArray(int[] arrayFor){
        int[] arrayTo = new int[arrayFor.length];
        int startValue = arrayFor.length-1;
        for (int arrayToIndex = 0; arrayToIndex < arrayTo.length; arrayToIndex++) {
            arrayTo[arrayToIndex] = arrayFor[startValue];
            startValue--;
        }
        return arrayTo;
    }

    public void getResultBasedOnMagicArray(int[] basedArray){
        List<int[]> listOfKeys = Reshuffle.getReshuffleList(initArray(dimension));
        for(int[] listOfKey: listOfKeys){
            List<int[]> constructList = new ArrayList<>(dimension);
            for (int listOfKeyIndex = 0; listOfKeyIndex < listOfKey.length; listOfKeyIndex++){
                constructList.add(listOfKeyIndex, massBasedOnIndex(listOfKey[listOfKeyIndex], basedArray));
            }
            int[] constructArray = getConstructArray(constructList);
            int[][] matrix = setToMagic(constructArray);
            if(isMagicSquare(matrix)){
                total++;
                FileWriter.writeMatrix(matrix, total);
            }
        }
        System.out.println(total);
    }

    private int[] getConstructArray(List<int[]> list){
        int[] result = new int[list.size()*dimension];
        int resultIndex = 0;

        for(int[] arrayFromList: list){
            for(int elementFromArray: arrayFromList){
                result[resultIndex] = elementFromArray;
                resultIndex++;
            }
        }
        return result;
    }

    private int[] massBasedOnIndex(int index, int[] arrayFrom){
        int [] pathMass = new int[dimension];
        for(int pathMassIndex = 0; pathMassIndex < pathMass.length; pathMassIndex++){
            pathMass[pathMassIndex] = arrayFrom[(index-1)*dimension + pathMassIndex];
        }
        return pathMass;
    }

    private int[][] setToMagic(int [] arrayNumbers){
        int capacity = (int)Math.sqrt(arrayNumbers.length);
        int rowIndex = 0;
        int columnIndex = capacity/2;
        int[][] matrix = new int[capacity][capacity];

        for (int elementFromArray: arrayNumbers) {
            matrix[rowIndex][columnIndex] = elementFromArray;
            rowIndex -= 1;
            columnIndex += 1;
            if(isTopOutBoard(rowIndex)){
                if(isRightOut(columnIndex, capacity)){
                    rowIndex += 2;
                    columnIndex -= 1;
                }
                else {
                    rowIndex = capacity-1;
                }
            }
            if(isRightOut(columnIndex, capacity)){
                columnIndex = 0;
            }
            if(matrix[rowIndex][columnIndex] != 0){
                rowIndex += 2;
                columnIndex -= 1;
            }
        }
        return matrix;
    }

    private boolean isTopOutBoard(int rowIndex){
        return rowIndex < 0;
    }

    private boolean isRightOut(int columnIndex, int dimension){
        return columnIndex >= dimension;
    }

    private boolean isMagicSquare(int [][] matrix){

        int sumUpDiagonal = 0;
        int sumDownDiagonal = 0;

        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            int sumInRow = 0;
            int sumInColumn = 0;

            sumDownDiagonal += matrix[rowIndex][rowIndex];
            sumUpDiagonal += matrix[matrix.length-rowIndex-1][rowIndex];

            for (int columnIndex = 0; columnIndex < matrix[rowIndex].length; columnIndex++) {
                sumInRow += matrix[rowIndex][columnIndex];
                sumInColumn += matrix[columnIndex][rowIndex];
            }
            if(sumInRow != sumInColumn)
                return false;
        }
        return sumDownDiagonal == sumUpDiagonal;
    }
}


