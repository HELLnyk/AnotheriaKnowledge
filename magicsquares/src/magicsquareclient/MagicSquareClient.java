package magicsquareclient;

import anotherms.MSProblem;
import mssolution.MagicSquareProblem;
//import mssolution.MSProblem;

/**
 * @author hellnyk
 */
public class MagicSquareClient {

    private static final int DEFAULT_CAPACITY_OF_SQUARE = 5;

    public static void main(String[] args) {
        new MagicSquareProblem(DEFAULT_CAPACITY_OF_SQUARE).getResult();
    }
}


