package entities;

import javax.persistence.*;
import java.io.*;
import java.util.Arrays;

/**
 * Object magic square to display the results in a database
 *
 * @author hellnyk
 */
@Entity
@Table(name = "tbl_solutions")
public class MagicSquareEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2446935509015325462L;

    /**
     * number of this result instance
     */
    @Id
    @SequenceGenerator(name="tbl_solutions_solution_id_seq",
                       sequenceName = "tbl_solutions_solution_id_seq",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "tbl_solutions_solution_id_seq")
    @Column(name = "solution_id" , updatable = false)
    private long id;

    /**
     * result such as {@link String} instance
     */
    @Column(name = "solution_result")
    private String solutionResultString;

    /**
     * result such as {@code int[][]} array
     */
    @Transient
    private int[][] solutionResultArray;

    /**
     * Empty constructor
     */
    public MagicSquareEntity() {
    }

    /**
     * Constructor for initialize result
     *
     * @param solutionResultArray
     *      {@code int[][]} result of magic array
     */
    public MagicSquareEntity(int [][] solutionResultArray){
        writeCorrectMatrix(solutionResultArray);
        solutionResultString = getSolutionResultString();
    }

    /**
     * Constructor for initialize result
     *
     * @param id
     *      number of this result instance
     * @param solutionResultString
     *      {@code String} result of magic array
     */
    public MagicSquareEntity(long id, String solutionResultString){
        this.id = id;
        this.solutionResultString = solutionResultString;
    }

    /**
     * init {@code int[][]} array result
     * @param matrix
     *      {@code int[][]} instance of the magic square result
     */
    private void writeCorrectMatrix(int [][] matrix){
        solutionResultArray = new int[matrix.length][];
        for (int row = 0; row < solutionResultArray.length; row++) {
            solutionResultArray[row] = new int[matrix[row].length];
            for (int column = 0; column < solutionResultArray[row].length; column++) {
                solutionResultArray[row][column] = matrix[row][column];
            }
        }
    }

    /**
     * init {@code String} result
     * @return
     *      {@code String} instance of result array
     */
    private String getSolutionResultString(){
        StringBuilder sBuilder = new StringBuilder();
        for (int row = 0; row < solutionResultArray.length; row++) {
            for (int column = 0; column < solutionResultArray[row].length; column++) {
                sBuilder.append(solutionResultArray[row][column] + " ");
            }
        }
        return sBuilder.toString();
    }

    /**
     * init {@code int[][]} result
     * @return
     *      {@code int[][]} instance of result array
     */
    public int[][] getSolutionResultArray() {
        String[] arrayStringValues = solutionResultString.split(" ");
        int capacity = (int) Math.sqrt((double) arrayStringValues.length);
        int [][] arrayValues = new int[capacity][capacity];
        int row = 0;
        int column = 0;
        for (int indexString = 0; indexString < arrayStringValues.length; indexString++) {
            arrayValues[row][column] = Integer.parseInt(arrayStringValues[indexString]);
            if(column == capacity - 1){
                column = 0;
                row++;
                continue;
            }
            column++;
        }
        return arrayValues;
    }

    @Override
    public String toString() {
        return "MagicSquareEntity{" +
                "id=" + id +
                ", solutionResultString='" + solutionResultString + '\'' +
                ", solutionResultArray=" + Arrays.toString(solutionResultArray) +
                '}';
    }
}
