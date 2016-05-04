package logic.basicprogramming.knightstour.khightourclient;

import logic.basicprogramming.knightstour.khightstoursolution.KnightTourProblem;

/**
 * @author hellnyk
 */
public class KnightTourProblemClient {

    public static void main(String[] args) {
        //KnightTourProblemClient.onPosition(0,0);
        KnightTourProblemClient.allResults();
    }

    public static void  allResults(){
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                counter++;
                if(i == 5 && j == 4){
                    continue;
                }
                System.out.println(String.format("Stage: %d Position (X: %d; Y:%d)", counter, i, j));
                new KnightTourProblem().getResult(i, j);
            }
        }
    }

    public static void onPosition(int x, int y){
        new KnightTourProblem().getResult(x, y);
    }
}
