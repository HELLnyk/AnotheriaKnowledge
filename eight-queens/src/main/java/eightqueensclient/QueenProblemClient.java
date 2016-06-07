package eightqueensclient;

import eightqueensolution.QueenProblem;

/**
 * Test QueenProblem
 *
 * @author hellnyk
 */
public class QueenProblemClient {

    public static void main(String[] args) {
        new QueenProblem(8, QueenProblem.PRINT_VECTOR).searchResult();
    }
}
