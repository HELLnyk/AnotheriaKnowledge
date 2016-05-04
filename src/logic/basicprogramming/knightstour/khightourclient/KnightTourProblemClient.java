package logic.basicprogramming.knightstour.khightourclient;

import logic.basicprogramming.knightstour.khightstoursolution.KnightTourProblem;

/**
 * @author hellnyk
 */
public class KnightTourProblemClient {

    public static void main(String[] args) {
        testOnPosition(5,4);
    }

    private static void testAllPositions(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 0; j++) {
                new KnightTourProblem().getResult(i,j);
            }
        }
    }

    private static void testOnPosition(int x, int y){
        new KnightTourProblem().getResult(x, y);
    }
}
