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
        for (int values = 1; values <= capacityOfMass; values++) {
            for (int increment = 1; increment <= capacityOfMass; increment++) {
                int[][] matrix = fillMagicSquare(new int[capacityOfSquare][capacityOfSquare], values, 0,0, increment);
                if(matrix.length == capacityOfSquare){
                    totalResultOfMagicSquare++;
                    FileWriter.writeMatrix(matrix, totalResultOfMagicSquare);
                }
            }
        }
    }

    private int getNextStartValue(int value){
        if(value >= capacityOfMass + 1){
            value++;
        }
        return value % (capacityOfMass + 1);
    }

    private int[][] fillMagicSquare(int[][] matrixForFill, int startValue, int counterOfRowAndColumn, int startIndex,
                                    int incrementForValues){
        if(counterOfRowAndColumn == capacityOfSquare){
            if(isSameDiagonals(matrixForFill)) {
                return matrixForFill;
            }else
                return new int[1][1];
        }else {
            int valueOfNeededElements = (2*capacityOfSquare - 1) - 2*counterOfRowAndColumn;
            int[] currentColumnArray = new int[matrixForFill[counterOfRowAndColumn].length];


            matrixForFill[counterOfRowAndColumn][counterOfRowAndColumn] = getNextStartValue(startValue);
            startValue += incrementForValues;

            int index = counterOfRowAndColumn + 1;
            for (int counterForCopy = 1; counterForCopy < valueOfNeededElements; counterForCopy += 2){
                matrixForFill[counterOfRowAndColumn][index] = getNextStartValue(startValue);
                startValue += incrementForValues;
                matrixForFill[index][counterOfRowAndColumn] = getNextStartValue(startValue);
                startValue += incrementForValues;
                index++;
            }

            for (int indexInColumn = 0; indexInColumn < currentColumnArray.length; indexInColumn++) {
                currentColumnArray[indexInColumn] = matrixForFill[indexInColumn][counterOfRowAndColumn];
            }

            if(isMagicArray(matrixForFill[counterOfRowAndColumn]) && isMagicArray(currentColumnArray)) {
                startIndex += valueOfNeededElements;
                return fillMagicSquare(matrixForFill, startValue, counterOfRowAndColumn + 1, startIndex, incrementForValues);
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
