package logic.basicprogramming.magicsquares.mssolution;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Arrays;

/**
 * @author hellnyk
 */
public class MagicSquareProblem{

    private int total = 0;

    private int[] revAr = {25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};

    private int dimension;

    public MagicSquareProblem(int dimension){
        this.dimension = dimension;
    }

    public int[] initArray(){
        int[] numbersArray = new int[dimension * dimension];
        for (int numberValue = 0; numberValue < numbersArray.length; numberValue++) {
            numbersArray[numberValue] = numberValue + 1;
        }
        return numbersArray;
    }

    /**
     *
     */
    public void getResult(){
        runOnArray(initArray());
        runOnArray(revAr);
        System.out.println(total);
    }

    private void runOnArray(int[] array){
        int pointer = 0;
        while (pointer != dimension){
            int [] constructArray = array;

            for (int pathOfArray = pointer; pathOfArray < dimension -1; pathOfArray++) {
                int [][] matrix = setToMagic(constructArray);
                if(isMagicSquare(matrix)){
                    //printMatrix(matrix);
                    //printSum(matrix);
                    total++;
                }
                constructArray = swapElementsInMass(constructArray, pathOfArray);
            }
            pointer++;
        }
    }

    private int[] swapElementsInMass(int[] whichArray, int positionStartForMoving){
        for (int counter = 0; counter < dimension; counter++) {
            int temp = whichArray[positionStartForMoving* dimension + counter];
            whichArray[positionStartForMoving* dimension + counter] = whichArray[positionStartForMoving*dimension + dimension + counter];
            whichArray[positionStartForMoving*dimension + dimension + counter] = temp;
        }
        return whichArray;
    }

    private int[][] setToMagic(int [] arrayNumbers){
        int capacity = (int)Math.sqrt(arrayNumbers.length);
        int rowIndex = 0;
        int columnIndex = capacity/2;
        int[][] matrix = new int[capacity][capacity];

        for (int settedNumber = 0; settedNumber < arrayNumbers.length; settedNumber++) {
            matrix[rowIndex][columnIndex] = arrayNumbers[settedNumber];
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
        if(sumDownDiagonal != sumUpDiagonal)
            return false;
        else
            return true;
    }






    private void printMatrix(int[][] matrix){
        System.out.println("********************* Result matrix *******************");
        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < matrix[rowIndex].length; columnIndex++){
                if(matrix[rowIndex][columnIndex] >= 10)
                    System.out.print(matrix[rowIndex][columnIndex] + " ");
                else
                    System.out.print(matrix[rowIndex][columnIndex] + "  ");
            }
            System.out.println();
        }
    }

    private void printSum(int[][] matrix){
        System.out.println("********************* sum of matrix *******************");
        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            int sumInRow = 0;
            int sumInColumn = 0;
            StringBuffer stringBufferRow = new StringBuffer();
            StringBuffer stringBufferColumn = new StringBuffer();
            for (int columnIndex = 0; columnIndex < matrix[rowIndex].length; columnIndex++) {
                sumInRow += matrix[rowIndex][columnIndex];
                sumInColumn += matrix[columnIndex][rowIndex];
                stringBufferRow.append(matrix[rowIndex][columnIndex] + " ");
                stringBufferColumn.append(matrix[columnIndex][rowIndex] + " ");
            }
            System.out.println("Sum in " + (rowIndex+1) + " row " + stringBufferRow + " = " + sumInRow);
            System.out.println("Sum in " + (rowIndex+1) + " column " + stringBufferColumn + " = " + sumInColumn);
        }

        int sumUpDiagonal = 0;
        int sumDownDiagonal = 0;
        StringBuffer stringBufferUpDiagonal = new StringBuffer();
        StringBuffer stringBufferDownDiagonal = new StringBuffer();
        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
             sumDownDiagonal += matrix[rowIndex][rowIndex];
             sumUpDiagonal += matrix[matrix.length-rowIndex-1][rowIndex];
            stringBufferDownDiagonal.append(matrix[rowIndex][rowIndex] + " ");
            stringBufferUpDiagonal.append(matrix[matrix.length-rowIndex-1][rowIndex] + " ");
        }
        System.out.println("Sum in down diagonal: " + stringBufferDownDiagonal + " = " + sumDownDiagonal);
        System.out.println("Sun in up diagonal: " + stringBufferUpDiagonal +" = " + sumUpDiagonal);
    }
}


