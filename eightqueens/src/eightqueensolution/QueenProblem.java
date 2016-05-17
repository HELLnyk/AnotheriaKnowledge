package eightqueensolution;

/**
 * Solution of queens problem
 *
 * @author hellnyk
 */
public class QueenProblem {

    /**
     * value, which represents print result such as matrix
     */
    public static final int PRINT_MATRIX = 0;

    /**
     * value, which represents print result such as vector
     */
    public static final int PRINT_VECTOR = 1;
    /**
     *value of all results of different types of solutions queens problem
     */
    private int totalResults;

    /**
     *value of chessboard size and number of queens
     */
    private int size;

    /**
     * kind how result will be print
     */
    private int kindOfPrintResult;

    /**
     * Default constructor
     *
     * @param size
     *      capasity of chessboard and also number of queens
     * @param kindOfPrintResult
     *      type of printing result
     *      if 0 - print such as matrix and if another value, print such as vector
     */
    public QueenProblem(int size, int kindOfPrintResult){
        this.size = size;
        totalResults = 0;
        this.kindOfPrintResult = kindOfPrintResult;
    }

    /**
     * create array for work of searching result and start position of queen
     */
    public void searchResult(){
        searchRecResult(new int[size], 0);
    }

    /**
     *  find concrete result vector with special arrangement for each queen
     *
     * @param mass
     *      array for setting positions of queens
     * @param numberOfQueen
     *      number of queen
     * @return
     *      null, if this arrangement does not exist
     *
     */
    private int[] searchRecResult(int[] mass, int numberOfQueen){
        int capacityOfQueens = mass.length;
        if(capacityOfQueens == numberOfQueen){
            return mass;
        }
        for (int i = 1; i <= capacityOfQueens; i++){
            boolean findPlace = true;
            for (int j = 0; j < numberOfQueen; j++){
                if(mass[j] == i || numberOfQueen - j == Math.abs(i - mass[j])){
                    findPlace = false;
                    break;
                }
            }
            if(findPlace){
                mass[numberOfQueen] = i;
                int[] position = searchRecResult(mass, numberOfQueen+1);
                if(position != null){
                    totalResults++;
//                    if(kindOfPrintResult == PRINT_MATRIX)
//                        printResultMatrix(position);
//                    else
//                        printResultIndex(position);
                    switch (kindOfPrintResult){
                        case PRINT_MATRIX:
                            printResultMatrix(position);
                            break;
                        case PRINT_VECTOR:
                            printResultIndex(position);
                            break;
                    }
                }
            }
        }
        return null;
    }

    /**
     * print result matrix
     *
     * @param positions
     *      array with result of positions for queens
     */
    private void printResultMatrix(int[] positions){
        System.out.println("**** Result: " + totalResults + " ****");
        for(int i = 0; i < positions.length; i++){
            int queenPosition = positions[i];
            for (int j = 1; j < queenPosition; j++) {
                System.out.print("- ");
            }
            System.out.print("Q ");
            for (int j = queenPosition + 1; j <= positions.length; j++) {
                System.out.print("- ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    /**
     * print result array
     *
     * @param positions
     *      array with result of positions for queens
     */
    private void printResultIndex(int[] positions){
        System.out.print(totalResults + " [ ");
        for(int i: positions){
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
}
