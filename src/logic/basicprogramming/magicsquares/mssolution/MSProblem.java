package logic.basicprogramming.magicsquares.mssolution;

/**
 * @author hellnyk
 */
public class MSProblem {

    private int capacityOfSquare;
    private int totalResultOfMagicSquare = 0;
    private int magicConstant;
    private int[][] matrix;

    public MSProblem(){
        this.capacityOfSquare = 5;
        magicConstant = setMagicConstant();
        matrix = new int[5][5];
    }
    public MSProblem(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        magicConstant = setMagicConstant();
        matrix = new int[capacityOfSquare][capacityOfSquare];
    }

    public int[][] fillMagicSquare(int[] arrayForFill, int counterOfRowAndColumn, int startIndex){
        if(counterOfRowAndColumn == capacityOfSquare){
            if(isSameDiagonals()) {
                return matrix;
            }else
                return new int[1][1];
        }else {
            int valueOfNeededElements = (2*capacityOfSquare - 1) - 2*counterOfRowAndColumn;
            int[] currentColumnArray = new int[matrix[counterOfRowAndColumn].length];


            matrix[counterOfRowAndColumn][counterOfRowAndColumn] = arrayForFill[startIndex];

            int index = counterOfRowAndColumn + 1;
            for (int counterForCopy = 1; counterForCopy <= (valueOfNeededElements -1) / 2; counterForCopy++){
                matrix[counterOfRowAndColumn][index] = arrayForFill[startIndex + counterForCopy];
                matrix[index][counterOfRowAndColumn] = arrayForFill[startIndex + (valueOfNeededElements -1)/2 + counterForCopy];
                index++;
            }

            for (int indexInColumn = 0; indexInColumn < currentColumnArray.length; indexInColumn++) {
                currentColumnArray[indexInColumn] = matrix[indexInColumn][counterOfRowAndColumn];
            }


            if(isMagicArray(matrix[counterOfRowAndColumn]) && isMagicArray(currentColumnArray)) {
                startIndex += valueOfNeededElements;
                return fillMagicSquare(arrayForFill, counterOfRowAndColumn + 1, startIndex);
            }else {
                return new int[1][1];
            }
        }
    }

    private int setMagicConstant(){
        return (capacityOfSquare *(capacityOfSquare*capacityOfSquare + 1)) / 2;
    }

    private boolean isSameDiagonals(){
        int sumDOne = 0;
        int sumDTwo = 0;
        for (int index = 0; index < capacityOfSquare; index++) {
            sumDOne += matrix[index][index];
            sumDTwo += matrix[matrix.length - 1 - index][index];
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
