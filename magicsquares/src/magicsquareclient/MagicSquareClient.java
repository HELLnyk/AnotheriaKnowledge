package magicsquareclient;

import mssolution.MagicSquareProblem;

/**
 * @author hellnyk
 */
public class MagicSquareClient {

    private static final int DEFAULT_CAPACITY_OF_SQUARE = 4;

    public static void main(String[] args) {
        new MagicSquareProblem(DEFAULT_CAPACITY_OF_SQUARE).getSomeResult();
    }
}


