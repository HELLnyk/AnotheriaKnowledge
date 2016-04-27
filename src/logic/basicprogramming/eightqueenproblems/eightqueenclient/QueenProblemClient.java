package logic.basicprogramming.eightqueenproblems.eightqueenclient;

import logic.basicprogramming.eightqueenproblems.eightqueensolution.QueenProblem;

/**
 * @author hellnyk
 */
public class QueenProblemClient {

    public static void main(String[] args) {
        QueenProblem queenProblem = new QueenProblem(8, 0);
        queenProblem.searchResult();
    }
}
