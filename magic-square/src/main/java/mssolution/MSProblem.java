package mssolution;

import java.util.HashSet;
import java.util.Set;

/**
 * Realization of magic square problem
 *
 * @author hellnyk
 */
public class MSProblem implements Runnable {

    /**
     * the dimension of the magic square
     */
    private int capacityOfSquare;

    /**
     * the dimension of the array for magic square
     */
    private int capacityOfElements;

    /**
     * magical constant value for the magic square
     */
    private int magicConstant;

    /**
     * the matrix, which represents magic square
     */
    private int[][] baseMatrix;

    /**
     * number of all  magic squares, if is program without multiple processes
     * and number of all magic squares for first element in the matrix when is
     * program with multiple processes
     */
    private int totalResult = 0;

    /**
     * number of all magic square for mulpitle program
     */
    private static volatile int totalResultMultiple = 0;

    /**
     * first element in the matrix
     */
    private int element;

    /**
     * Default constructor for initialize the base properties of the magic square
     *
     * @param capacityOfSquare
     *      the dimension of the magic square
     */
    public MSProblem(int capacityOfSquare){
        setProperties(capacityOfSquare);
    }

    /**
     * Constructor for initialize the base properties of the magic square with the
     * first set value
     *
     * @param capacityOfSquare
     *      the dimension of the magic square
     * @param element
     *      first element in the matrix
     */
    public MSProblem(int capacityOfSquare, int element){
        setProperties(capacityOfSquare);
        this.element = element;
    }

    /**
     * initialize the base properties of the magic square
     *
     * @param capacityOfSquare
     *      the dimension of the magic square
     */
    private void setProperties(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        magicConstant = initMagicConstant();
        baseMatrix = new int[capacityOfSquare][capacityOfSquare];
        capacityOfElements = capacityOfSquare * capacityOfSquare;
    }

    /**
     * determine the value of magic constants
     *
     * @return
     *      value of magic constant
     */
    private int initMagicConstant(){
        return capacityOfSquare * (capacityOfSquare * capacityOfSquare + 1) / 2;
    }


    /**
     * multiple searching of the magic squares
     */
    @Override
    public void run() {
        HashSet<Integer> set = new HashSet<>();
        baseMatrix[0][0] = element;
        set.add(element);
        fillMatrix(set, 0, 1, 0, false);
        //System.out.println("All results for start value in magic array" + element + " :" + totalResult);
        totalResultMultiple += totalResult;
    }

    /**
     * simple searching of the magic square
     */
    public void getSomeResult(){
        HashSet<Integer> set = new HashSet<>();
        for (int element = 1; element <=capacityOfElements ; element++) {
            baseMatrix[0][0] = element;
            set.add(element);
            fillMatrix(set, 0, 1, 0, false);
            set.remove(element);
        }
        System.out.println("All results: " + totalResult);
    }

    /**
     * recursive construction of the magic square
     *
     * @param recordedElements
     *      {@link Set} of elements, which already set into the matrix
     * @param rowIndex
     *      the index of row for set possible element
     * @param columnIndex
     *      the index of column for set possible element
     * @param baseRowAndColumn
     *      the value of current row and column, in which data is recorded
     * @param writeColumn
     *      <code>boolean</code> value which means where data is recorded (in row or in column)
     * @return
     *      <code>true</code>, if all magic squares is found<br>
     *      and <code>false</code> - otherwise
     */
    private boolean fillMatrix(Set<Integer> recordedElements, int rowIndex, int columnIndex, int baseRowAndColumn, boolean writeColumn){
        if(recordedElements.size() == capacityOfElements){
            if(checkDiagonals(baseMatrix)){
                totalResult++;
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


//                if(isLargeSum(baseMatrix[baseRowAndColumn]) || isLargeSum(copyColumn(baseMatrix, baseRowAndColumn))){
//                    return false;
//                }



                baseMatrix[rowIndex][columnIndex] = element;
                recordedElements.add(element);
                if(columnIndex == capacityOfSquare - 1){
                    if(isMagicArray(baseMatrix[baseRowAndColumn])){
                        writeColumn = true;
                    }else {
                        removeElementFromMatrixAndSet(recordedElements, element, rowIndex, columnIndex);
                        continue;
                    }
                }
                if(writeColumn){
                    if(rowIndex != capacityOfSquare - 1) {
                        if (fillMatrix(recordedElements, rowIndex + 1, baseRowAndColumn, baseRowAndColumn, true)) {
                            return true;
                        }
                        removeElementFromMatrixAndSet(recordedElements, element, rowIndex, columnIndex);
                        continue;
                    }else {
                        if(isMagicArray(copyColumn(baseMatrix, baseRowAndColumn))){
                            if(baseRowAndColumn != capacityOfSquare - 1) {
                                baseRowAndColumn++;
                                rowIndex = baseRowAndColumn;
                                columnIndex = rowIndex - 1;
                            }
                        }
                        else {
                            removeElementFromMatrixAndSet(recordedElements, element, rowIndex, columnIndex);
                            continue;
                        }
                    }
                }
                if(fillMatrix(recordedElements, rowIndex, columnIndex + 1, baseRowAndColumn, false)){
                    return true;
                }
                rowIndex = getCorrectRowIndex(element, rowIndex ,columnIndex);
                removeElementFromMatrixAndSet(recordedElements, element, rowIndex, columnIndex);
            }
        }
        return false;
    }

    private boolean isLargeSum(int[] arrayForSum){
        int sum = 0;
        for (int index = 0; index < arrayForSum.length; index++) {
            sum += arrayForSum[index];
        }
        return sum > magicConstant;
    }

    /**
     * get correct row index when is needed to return to the previous calculation
     *
     * @param element
     *      the value of current element
     * @param rowIndex
     *      the row index of this element
     * @param columnIndex
     *      the column index of this element
     * @return
     *      previous and correct row index
     */
    private int getCorrectRowIndex(int element, int rowIndex, int columnIndex) {
        if(element != baseMatrix[rowIndex][columnIndex]){
            return (capacityOfSquare - 1);
        }
        else return rowIndex;
    }

    /**
     * delete wrong element from the matrix
     *
     * @param set
     *      {@link Set} of recorded elements, which already consists in the matrix
     *
     * @param element
     *      the element for delete from the set
     * @param rowIndex
     *      the row index ot this element
     * @param columnIndex
     *      the column index of this element
     */
    private void removeElementFromMatrixAndSet(Set set, int element, int rowIndex, int columnIndex){
        baseMatrix[rowIndex][columnIndex] = 0;
        set.remove(element);
    }

    /**
     * create array, which is based on the column of the matrix
     *
     * @param fromMatrix
     *      the matrix
     * @param indexOfColumn
     *      column of matrix
     * @return
     *      created array
     */
    private int[] copyColumn(int[][] fromMatrix, int indexOfColumn){
        int [] destArray = new int[capacityOfSquare];
        for (int indexOfElementInColumn = 0; indexOfElementInColumn < destArray.length; indexOfElementInColumn++) {
            destArray[indexOfElementInColumn] = fromMatrix[indexOfElementInColumn][indexOfColumn];
        }
        return destArray;
    }

    /**
     * check diagonals of the matrix if is correct sum of values diagonals and magic constant
     *
     * @param matrix
     *      matrix, which will be checked
     * @return
     *      {@code true}, if the sum of the elements of the diagonals is equal to the magic constant,
     *      and <br>{@code false} - otherwise
     */
    public boolean checkDiagonals(int[][] matrix){
        int sumDownDiagonal = 0;
        int sumUpDiagonal = 0;
        for (int row = 0; row < matrix.length; row++) {
            sumUpDiagonal += matrix[row][row];
            sumDownDiagonal += matrix[(matrix.length - 1) - row][row];
        }
        return sumDownDiagonal == magicConstant && sumUpDiagonal == magicConstant;
    }

    /**
     * check if sum of the element of input {@code int[]} array is equals to magic constant
     *
     * @param array
     *      {@code int[]} array, which will be checked
     * @return
     *      {@code int[]}, if sum of element is equals to magic constant
     *      and <br>{@code int[]} - otherwise
     */
    private boolean isMagicArray(int[] array){
        int sum = 0;
        for(int element: array){
            sum += element;
        }
        return magicConstant == sum;
    }

    /**
     * return number of the all magic squares, if was used multiple processes for searching results
     *
     * @return
     *      all number of magic squares
     */
    public static int getTotalResultMultiple() {
        return totalResultMultiple;
    }
}