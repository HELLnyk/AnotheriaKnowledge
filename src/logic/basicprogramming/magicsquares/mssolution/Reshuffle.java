package logic.basicprogramming.magicsquares.mssolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents reshuffle of all combinations numbers of input array
 *
 * @author hellnyk
 */
public class Reshuffle {


    /**
     * Create all reshuffles based on input array
     *
     * @param sourceArray
     *      array for creating reshuffles for this array
     * @return
     *      {@link List}<int[]> reshuffles
     */
    public static List<int[]> getReshuffleList(int[] sourceArray) {
        if (sourceArray.length == 2) {
            int[] arrayOne = {sourceArray[0], sourceArray[1]};
            int[] arrayTwo = {sourceArray[1], sourceArray[0]};
            return Arrays.asList(arrayOne, arrayTwo);
        }
        else {
            List<int[]> resultList = new ArrayList<>();
            for (int sourceArrayIndex = 0; sourceArrayIndex < sourceArray.length; sourceArrayIndex++) {
                for (int[] a : getReshuffleList(excludingCopy(sourceArray, sourceArrayIndex))) {
                    resultList.add(insert(sourceArray[sourceArrayIndex], a));
                }
            }
            return resultList;
        }
    }

    /**
     * delete copies of arrays
     *
     * @param sourceArray
     *      based array for creating another smaller array
     * @param index
     *      delete copies for this index
     * @return
     *      array which dont have a copies
     */
    private static int[] excludingCopy(int[] sourceArray, int index) {
        int[] result = new int[sourceArray.length - 1];
        for (int sourceArrayIndex = 0, resultIndex = 0; sourceArrayIndex < sourceArray.length; sourceArrayIndex++) {
            if (sourceArrayIndex != index) {
                result[resultIndex++] = sourceArray[sourceArrayIndex];
            }
        }
        return result;
    }

    /**
     * move elements in array on one position
     * @param value
     *      first value in array
     * @param sourceArray
     *      array for moving
     * @return
     *      changed array
     */
    private static int[] insert(int value, int[] sourceArray) {
        int[] resultArray = new int[sourceArray.length + 1];
        resultArray[0] = value;
        for (int i = 0; i < sourceArray.length; i++) {
            resultArray[i+1] = sourceArray[i];
        }
        return resultArray;
    }
}
