package logic.basicprogramming.eightqueenproblems;

/**
 * Solution of queens problem
 *
 * @author hellnyk
 */
public class QueenProblem {

    /**
     *value of all results of different types of solutions queens problem
     */
    private int totalResults;

    /**
     *value of chessboard size and number of queens
     */
    private int size;

    /**
     * Default constructor
     * @param size
     */
    public QueenProblem(int size){
        this.size = size;
        totalResults = 0;
    }

    /**
     * create array for work of searching result and start position of queen
     */
    public void searchResult(){
        searchRecResult(new int[size], 0);
    }

    /**
     *
     * @param mass
     *      array for setting positions of queens
     * @param numberOfQueen
     *      number of queen
     * @return
     *
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
                    //printResult(position);
                    printIndex(position);
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
    private void printResult(int[] positions){
        System.out.println("\n**** Result: " + totalResults + " ****");
        for(int i = 0; i < positions.length; i++){
            int queenPosition = positions[i];
            for (int j = 1; j < queenPosition; j++) {
                System.out.print("- ");
            }
            System.out.print("Q ");
            for (int j = queenPosition + 1; j <= positions.length; j++) {
                System.out.print("- ");
            }
            System.out.println("\n");
        }
    }

    private void printIndex(int[] positions){
        System.out.print("[ ");
        for(int i: positions){
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
}
