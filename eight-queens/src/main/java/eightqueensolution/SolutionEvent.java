package eightqueensolution;

import java.util.Arrays;

/**
 * unit of proper solution for the queen problem
 *
 * @author hellnyk
 */
public class SolutionEvent {

    /**
     * result <code>int</code> array of positions of the queens on the chessboard
     */
    private int[] array;

    /**
     * getter for {@link SolutionEvent} instance
     *
     * @return
     *      <code>int</code> array of positions of the current {@link SolutionEvent} instance
     */
    public int[] getArray() {
        return array;
    }

    /**
     * setter for {@link SolutionEvent} instance
     *
     * @param array
     *      <code>int</code> array of correct queen`s positions
     */
    public void setArray(int[] array) {
        this.array = initArray(array);
    }

    /**
     * array initialization of current {@link SolutionEvent} instance correct queen`s positions
     *
     * @param array
     *      <code>int</code> array, which are set value
     * @return
     */
    private int[] initArray(int[] array) {
        int[] arr = new int[array.length];
        System.arraycopy(array, 0, arr, 0, array.length);
        return arr;
    }

    @Override
    public String toString() {
        return "SolutionEvent{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
