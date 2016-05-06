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
            for (int row = 0; row < dimension; row++) {
                for (int column = 0; column < dimension; column++) {
                    int[][] matrix = someMatrix(constructArray, row, column);
                    if(isMagicSquare(matrix)){
                        total++;
                        FileWriter.writeMatrix(matrix, total);
                    }
                }
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

    private int[][] someMatrix(int[] arrayNumbers, int x, int y){
        int capasity = (int)Math.sqrt(arrayNumbers.length);
        int rowIndex = x;
        int columnIndex = y;
        int[][] matrix = new int[capasity][capasity];

        matrix[rowIndex][columnIndex] = arrayNumbers[0];

        for(int i = 1; i < arrayNumbers.length; i++){

            rowIndex -= 1;
            columnIndex += 1;

            if(isTopOutBoard(rowIndex)){
                if(isRightOut(columnIndex, capasity)){
                    if(matrix[capasity-1][0] == 0){
                        rowIndex = capasity - 1;
                        columnIndex = 0;
                    }
                    else {
                        rowIndex += 2;
                        columnIndex -=1;
                    }
                }

                ///
                else if(matrix[capasity-1][columnIndex] != 0){
                    rowIndex += 2;
                    columnIndex -= 1;
                }
                else {
                    rowIndex = capasity -1;
                }
                ///
            }

            if(isRightOut(columnIndex, capasity)){
                if(matrix[rowIndex][0] != 0){
                    rowIndex += 2;
                    columnIndex -= 1;
                }
                else {
                    columnIndex = 0;
                }
            }

            if(matrix[rowIndex][columnIndex] != 0){
                rowIndex += 2;
                columnIndex -= 1;
            }
            matrix[rowIndex][columnIndex] = arrayNumbers[i];
        }

        return matrix;
    }

    private int[][] setToMagic(int [] arrayNumbers, int xStartCoordinate, int yStartCoordinate){
        int capacity = (int)Math.sqrt(arrayNumbers.length);
        int rowIndex = xStartCoordinate;
        int columnIndex = yStartCoordinate;
        int[][] matrix = new int[capacity][capacity];

        for (int elementFromArray: arrayNumbers) {
            matrix[rowIndex][columnIndex] = elementFromArray;
            rowIndex -= 1;
            columnIndex += 1;
            if(isTopOutBoard(rowIndex)){
                //if(matrix[capacity-1][columnIndex] == 0){
                    if(isRightOut(columnIndex, capacity)){
                        rowIndex = capacity -1;
                        columnIndex = 0;
                    }
                    else {
                        if(matrix[capacity-1][columnIndex] != 0){
                            rowIndex += 2;
                            columnIndex -= 1;
                        }
                        else {
                            rowIndex = capacity-1;
                        }
                    }
//                }
//                else {
//                    rowIndex += 2;
//                    columnIndex -= 1;
//                }
            }
            if(isRightOut(columnIndex, capacity)){
                //подивитися чи матриця від capasity 0 занята
                if(matrix[rowIndex][0] == 0) {
                    columnIndex = 0;
                }
                else {
                    rowIndex += 2;
                    columnIndex -= 1;
                }
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


