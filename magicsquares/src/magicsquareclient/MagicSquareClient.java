package magicsquareclient;

import mssolution.MagicSquareProblem;
import mssolution.Reshuffle;
import mssolution.MSProblem;

import java.util.Arrays;

/**
 * @author hellnyk
 */
public class MagicSquareClient {

    private static final int DEFAULT_CAPACITY_OF_SQUARE = 5;

    public static void main(String[] args) {
       new MSProblem(DEFAULT_CAPACITY_OF_SQUARE).findResult();
    }
}


