package logic.basicprogramming.magicsquares.mssolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hellnyk
 */
public class Reshuffle {

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

    private static int[] excludingCopy(int[] sourceArray, int index) {
        int[] result = new int[sourceArray.length - 1];
        for (int sourceArrayIndex = 0, j = 0; sourceArrayIndex < sourceArray.length; sourceArrayIndex++) {
            if (sourceArrayIndex != index) {
                result[j++] = sourceArray[sourceArrayIndex];
            }
        }
        return result;
    }

    private static int[] insert(int value, int[] sourceArray) {
        int[] resultArray = new int[sourceArray.length + 1];
        resultArray[0] = value;
        for (int i = 0; i < sourceArray.length; i++) {
            resultArray[i+1] = sourceArray[i];
        }
        return resultArray;
    }
}
