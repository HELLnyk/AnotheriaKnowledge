package magicsquareclient;

import mssolution.FileWriter;
import mssolution.MSProblem;
import mssolution.WriterInterface;

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
    private static final int DEFAULT_CAPACITY_OF_SQUARE = 3;

    /**
     * main method for testing work
     *
     * @param args
     *      default arguments
     */
    public static void main(String[] args) {
        //runSimple(DEFAULT_CAPACITY_OF_SQUARE, new FileWriter());
        runMultiple(DEFAULT_CAPACITY_OF_SQUARE, new FileWriter());
    }

    /**
     * sequential search all magic squares
     */
    public static void runSimple(int param, WriterInterface writerInterface){
        new MSProblem(param, writerInterface).getSomeResult();
    }

    /**
     * parallel search of all magic squares
     */
    public static void runMultiple(int param, WriterInterface writerInterface){
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Runnable> tasks = new ArrayList<>();

        for (int element = 1; element <= param * param; element++) {
            tasks.add(new MSProblem(param, element, writerInterface));
        }
        tasks.forEach(executorService::execute);
        executorService.shutdown();
    }
}

