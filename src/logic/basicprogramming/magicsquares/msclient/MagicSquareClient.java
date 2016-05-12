package logic.basicprogramming.magicsquares.msclient;

import logic.basicprogramming.magicsquares.mssolution.MSProblem;

/**
 * @author hellnyk
 */
public class MagicSquareClient {

    private static final int DEFAULT_CAPACITY_OF_SQUARE = 5;

    public static void main(String[] args) {
        new MSProblem(DEFAULT_CAPACITY_OF_SQUARE).findResult();
    }
}


