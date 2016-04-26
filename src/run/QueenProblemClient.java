package run;

import logic.basicprogramming.eightqueenproblems.QueenProblem;

/**
 * @author hellnyk
 */
public class QueenProblemClient {

    public static void main(String[] args) {
        QueenProblem queenProblem = new QueenProblem(8);
        queenProblem.searchResult();
    }
}
