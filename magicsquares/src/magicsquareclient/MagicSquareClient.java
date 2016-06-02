package magicsquareclient;

import mssolution.MSProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Client for magic square problem
 *
 * @author hellnyk
 */
public class MagicSquareClient {

    /**
     * default capacity of magic square
     */
    private static final int DEFAULT_CAPACITY_OF_SQUARE = 5;

    /**
     * main method for testing work
     *
     * @param args
     *      default arguments
     */
    public static void main(String[] args) {
        testingSimple(DEFAULT_CAPACITY_OF_SQUARE);
        testingMultiple(DEFAULT_CAPACITY_OF_SQUARE);
    }

    /**
     * sequential search all magic squares
     */
    private static void testingSimple(int param){
        long start, end;
        start = System.nanoTime();

        new MSProblem(param).getSomeResult();

        end = System.nanoTime();
        double processedTime = ((end - start) / 1000000000.0) / 60.0;
        System.out.println(String.format("%2.2f %s", processedTime, "minutes"));
    }

    /**
     * parallel search of all magic squares
     */
    private static void testingMultiple(int param){
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Runnable> tasks = new ArrayList<>();

        for (int element = 1; element <= param * param; element++) {
            tasks.add(new MSProblem(param, element));
        }

        long start, end;
        start = System.nanoTime();

        tasks.forEach(executorService::execute);
        executorService.shutdown();
        while (!executorService.isTerminated());

        System.out.println("All results: " + MSProblem.getTotalResultMultiple());
        end = System.nanoTime();
        double processedTime = ((end - start) / 1000000000.0) / 60.0;
        System.out.println(String.format("%2.2f %s", processedTime, "minutes"));
    }
}


