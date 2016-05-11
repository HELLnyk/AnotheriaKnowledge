package logic.basicprogramming.magicsquares.anotherms;

import logic.basicprogramming.magicsquares.mssolution.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hellnyk
 */
public class MSProblem {

    private List<Cellule> cellulesList;
    private int capacityOfSquare;
    private int capacityOfElements;
    private int[][] baseMatrix;
    private int totalResult = 0;

    public MSProblem(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        cellulesList = new ArrayList<>(capacityOfSquare*capacityOfSquare);
        capacityOfElements = capacityOfSquare*capacityOfSquare;
        baseMatrix = new int[capacityOfSquare][capacityOfSquare];
        initList();
    }

    private void initList(){
        for (int elenent = 0; elenent < capacityOfElements; elenent++) {
            int rowIndex = elenent / capacityOfSquare;
            int columnIndex = elenent % capacityOfSquare;
            baseMatrix[rowIndex][columnIndex] = elenent + 1;
            cellulesList.add(new Cellule(
                    (elenent + 1),
                    elenent,
                    capacityOfElements - (elenent + 1),
                    columnIndex,
                    rowIndex)
            );
        }
    }


    public void getRes(){
        int peres = 0;
        for(Cellule celluleForSwap: cellulesList){
            for (Cellule celluleSwap: cellulesList){
                if(celluleSwap == celluleForSwap){
                    continue;
                }
                int[][] changeMatrix = copyMatrix();
                changeMatrix[celluleForSwap.getPositionHorizontal()][celluleForSwap.getPositionVertical()] =
                        celluleSwap.getStateValue();
                changeMatrix[celluleSwap.getPositionHorizontal()][celluleSwap.getPositionVertical()] =
                        celluleForSwap.getStateValue();

                if(isMagicSquare(changeMatrix)){
                    totalResult++;
                    FileWriter.writeMatrix(changeMatrix, totalResult);
                }
                peres++;
            }
        }
        System.out.println(peres);
    }

    private int[][] copyMatrix(){
        int[][] matrix = new int[capacityOfSquare][capacityOfSquare];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = baseMatrix[row][column];
            }
        }
        return matrix;
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
