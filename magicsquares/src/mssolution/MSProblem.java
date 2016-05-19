package mssolution;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hellnyk
 */
public class MSProblem {

    private int capacityOfSquare;
    private int totalResultOfMagicSquare = 0;
    private int magicConstant;
    private int capacityOfMass;

    public MSProblem(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        magicConstant = setMagicConstant();
        capacityOfMass = capacityOfSquare * capacityOfSquare;
    }


    public void findResult(){
        for (int values = 1; values <= capacityOfMass; values++) {
            fillMagicSquare(new HashSet<>(), new int[capacityOfSquare][capacityOfSquare], values, 0);
        }
    }

    private int getNextStartValue(int value){
        if(value >= capacityOfMass + 1){
            value++;
        }
        return value % (capacityOfMass + 1);
    }

    private boolean fillMagicSquare(Set<Integer> recordedElements, int[][] matrixForFill, int startValue, int counterOfRowAndColumn){

        recordedElements.add(startValue);

        if(counterOfRowAndColumn == capacityOfSquare && isSameDiagonals(matrixForFill)) {
            totalResultOfMagicSquare++;
            FileWriter.writeMatrix(matrixForFill, totalResultOfMagicSquare, matrixForFill[0][0]);
            return true;
        }
        else {
            for (int element = 1; element <= capacityOfMass; element++) {
                if(element == startValue)
                    continue;
                int currentElement = element;
                int valueOfNeededElements = (2*capacityOfSquare - 1) - 2*counterOfRowAndColumn;
                int[] currentColumnArray = new int[matrixForFill[counterOfRowAndColumn].length];


                matrixForFill[counterOfRowAndColumn][counterOfRowAndColumn] = getNextStartValue(startValue);
                currentElement = setExcludingValue(startValue, element);

                int index = counterOfRowAndColumn + 1;
                for (int counterForCopy = 1; counterForCopy < valueOfNeededElements; counterForCopy += 2){
                    matrixForFill[counterOfRowAndColumn][index] = getNextStartValue(setExcludingValue(startValue, currentElement));
                    currentElement++;
                    matrixForFill[index][counterOfRowAndColumn] = getNextStartValue(setExcludingValue(startValue, currentElement));
                    currentElement++;
                    index++;
                }

                for (int indexInColumn = 0; indexInColumn < currentColumnArray.length; indexInColumn++) {
                    currentColumnArray[indexInColumn] = matrixForFill[indexInColumn][counterOfRowAndColumn];
                }

                if(isMagicArray(matrixForFill[counterOfRowAndColumn]) && isMagicArray(currentColumnArray)) {
                    if(fillMagicSquare(recordedElements, matrixForFill, currentElement, counterOfRowAndColumn + 1)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int setExcludingValue(int firstValue, int currentValue){
        if(firstValue == currentValue)
            currentValue++;
        return currentValue;
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
