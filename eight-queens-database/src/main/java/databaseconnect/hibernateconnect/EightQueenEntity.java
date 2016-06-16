package databaseconnect.hibernateconnect;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Object eight queen result representation to display the results in a database
 *
 * @author hellnyk
 */
@Entity
@Table(name = "tbl_result_element")
public class EightQueenEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5289171614903334110L;

    /**
     * number of this result instance
     */
    @Id
    @SequenceGenerator(name= "tbl_result_element_id_seq",
            sequenceName = "tbl_result_element_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tbl_result_element_id_seq")
    @Column(name = "id" , updatable = false)
    private long id;

    /**
     * result such as {@link String} instance
     */
    @Column(name = "result_element")
    private String solutionResultString;

    /**
     * result such as {@code int[]} array
     */
    @Transient
    private int[] solutionResultArray;

    /**
     * Empty constructor
     */
    public EightQueenEntity() {
    }

    /**
     * Constructor for initialize result
     *
     * @param solutionResultArray
     *      {@code int[]} result of eight queen problem
     */
    public EightQueenEntity(int[] solutionResultArray) {
        initResultArray(solutionResultArray);
        solutionResultString = initResultString();
    }

    /**
     * init this entity with one result of the eight queen solution
     *
     * @param solutionArray
     *      {@code int[]} instance of one eight queen result
     */
    private void initResultArray(int[] solutionArray) {
        solutionResultArray = new int[solutionArray.length];
        System.arraycopy(solutionArray, 0, solutionResultArray, 0, solutionArray.length);
    }

    /**
     * represents {@code int[]} result array such as {@code String} instance
     *
     * @return
     *      {@code String} instance - presenting this result of eight queen problem as a string
     */
    private String initResultString(){
        StringBuilder sBuilder = new StringBuilder();
        for(int elem: solutionResultArray){
            sBuilder.append(elem);
            sBuilder.append(" ");
        }
        return sBuilder.toString();
    }

    /**
     * id getter
     *
     * @return
     *  {@code this} instance id
     */
    public long getId() {
        return id;
    }

    /**
     * result of eight queen problem getter
     *
     * @return
     *      {@code this} result
     */
    public int[] getSolutionResultArray() {
        String[] stringValues = solutionResultString.split(" ");
        int[] numberValues = new int[stringValues.length];
        for (int index = 0; index < numberValues.length; index++) {
            numberValues[index] = Integer.parseInt(stringValues[index]);
        }
        return numberValues;
    }

    @Override
    public String toString() {
        return "EightQueenEntity{" +
                "id=" + id +
                ", solutionResultArray=" + Arrays.toString(solutionResultArray) +
                '}';
    }
}
