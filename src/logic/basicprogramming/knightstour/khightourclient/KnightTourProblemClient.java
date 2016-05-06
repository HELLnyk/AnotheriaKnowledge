package logic.basicprogramming.knightstour.khightourclient;

import logic.basicprogramming.knightstour.khightstoursolution.KnightTourProblem;

/**
 * @author hellnyk
 */
public class KnightTourProblemClient {

    public static void main(String[] args) {
        testPosition(0, 7);
    }

    public static void testAll(){
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                counter++;
                System.out.println(String.format("Step: %d and position: X: %d; Y: %d", counter, i,j));
                new KnightTourProblem().getResult(i, j);
            }
        }
    }

    public static void testPosition(int x, int y){

        new KnightTourProblem().getResult(x, y);
    }
}
