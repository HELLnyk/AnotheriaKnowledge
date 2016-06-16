package databaseconnect.jdbcconnect.dao.daoiml;

import databaseconnect.jdbcconnect.dao.WorkDAOjdbc;
import databaseconnect.InterfaceWorkDAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * {@link InterfaceWorkDAO} implementation for simple <code>int[]</code> entity
 *
 * @author hellnyk
 */
public class WorkDAOjdbcImpl extends WorkDAOjdbc<int[]> {

    public WorkDAOjdbcImpl() {
        super();
    }

    @Override
    protected String getConcreteInsertQuery() {
        return "INSERT INTO tbl_result_element (result_element) VALUES (?)";
    }

    @Override
    protected String getConcreteGetQuery() {
        return "SELECT * FROM tbl_result_element WHERE id=?";
    }

    @Override
    protected String getConcreteGetAllQuery() {
        return "SELECT * FROM tbl_result_element";
    }

    @Override
    protected void concreteStatementInsert(PreparedStatement preparedStatement, int[] ints) throws SQLException {
        preparedStatement.setString(1, Arrays.toString(ints));
    }

    @Override
    protected int[] getConcreteElement(ResultSet set) throws SQLException {
        int[] resultMass = null;
        if(set.next()){
            resultMass = parseString(set.getString("result_element"));
        }
        return resultMass;
    }

    @Override
    protected List<int[]> getConcreteElements(ResultSet set) throws SQLException {
        List<int[]> list = new ArrayList<>();
        while (!set.isLast()){
            list.add(getConcreteElement(set));
        }
        return list;
    }

    /**
     * parse input <code>Sting</code> instance type "[1, 2, 3, 4, 5]" to <code>int</code> array with this values
     *
     * @param forPars
     *      parsed <code>String</code>
     * @return
     *      <code>int</code> array based on this string
     */
    private int[] parseString(String forPars){
        String[] arr = forPars.split(", ");
        String firstElement = arr[0].substring(1, arr[0].length());
        String lastElement = arr[arr.length - 1].substring(0, arr[arr.length - 1].length() - 1);
        arr[0] = firstElement;
        arr[arr.length - 1] = lastElement;

        int[] resultNumberArr = new int[arr.length];
        for (int index = 0; index < resultNumberArr.length; index++) {
            resultNumberArr[index] = Integer.parseInt(arr[index]);
        }

        return resultNumberArr;
    }
}
