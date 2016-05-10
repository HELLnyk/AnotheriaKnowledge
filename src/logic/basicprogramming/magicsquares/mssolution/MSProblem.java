package logic.basicprogramming.magicsquares.mssolution;

import java.util.List;
import java.util.Arrays;

/**
 * @author hellnyk
 */
public class MSProblem {

    private int capacityOfSquare;
    private int totalResultOfMagicSquare = 0;
    private int magicConstant;
    private int capacityOfMass;
    private int[] someArray;

    public MSProblem(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        magicConstant = setMagicConstant();
        capacityOfMass = capacityOfSquare * capacityOfSquare;
        someArray = new int[capacityOfMass];
    }


    public void findResult(){

    }

    private int[] initArray(){
        int [] array = new int[capacityOfSquare*capacityOfSquare];
        for (int index = 0; index < capacityOfSquare * capacityOfSquare; index++) {
            array[index] = index + 1;
        }
        return array;
    }

    private int[][] fillMagicSquare(int[][] matrixForFill, int[] arrayFromFill, int counterOfRowAndColumn, int startIndex){
        if(counterOfRowAndColumn == capacityOfSquare){
            if(isSameDiagonals(matrixForFill)) {
                return matrixForFill;
            }else
                return new int[1][1];
        }else {
            int valueOfNeededElements = (2*capacityOfSquare - 1) - 2*counterOfRowAndColumn;
            int[] currentColumnArray = new int[matrixForFill[counterOfRowAndColumn].length];


            matrixForFill[counterOfRowAndColumn][counterOfRowAndColumn] = arrayFromFill[startIndex];

            int index = counterOfRowAndColumn + 1;
            for (int counterForCopy = 1; counterForCopy < valueOfNeededElements; counterForCopy++){
                matrixForFill[counterOfRowAndColumn][index] = arrayFromFill[startIndex + counterForCopy++];
                matrixForFill[index][counterOfRowAndColumn] = arrayFromFill[startIndex + counterForCopy];
                index++;
            }

            for (int indexInColumn = 0; indexInColumn < currentColumnArray.length; indexInColumn++) {
                currentColumnArray[indexInColumn] = matrixForFill[indexInColumn][counterOfRowAndColumn];
            }


            if(isMagicArray(matrixForFill[counterOfRowAndColumn]) && isMagicArray(currentColumnArray)) {
                startIndex += valueOfNeededElements;
                return fillMagicSquare(matrixForFill, arrayFromFill, counterOfRowAndColumn + 1, startIndex);
            }else {
                return new int[1][1];
            }
        }
    }

    private int setMagicConstant(){
        return (capacityOfSquare *(capacityOfSquare*capacityOfSquare + 1)) / 2;
    }

    private boolean isSameDiagonals(int[][] matrixRes){
        int sumDOne = 0;
        int sumDTwo = 0;
        for (int index = 0; index < capacityOfSquare; index++) {
            sumDOne += matrixRes[index][index];
            sumDTwo += matrixRes[matrixRes.length - 1 - index][index];
        }
        return sumDOne == sumDTwo;
    }

    private boolean isMagicArray(int[] array){
        int sum = 0;
        for(int element: array){
            sum += element;
        }
        return magicConstant == sum;
    }
}
