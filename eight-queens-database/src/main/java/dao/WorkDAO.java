package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * {@link InterfaceWorkDAO} implementation
 *
 * @author hellnyk
 */
public abstract class WorkDAO<T> implements InterfaceWorkDAO<T> {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkDAO.class);

    /**
     * {@link Connection} instance for connection to the database
     */
    private Connection connection;


    /**
     * Default constructor
     *
     * @param connection
     *      {@link Connection} instance to connect
     */
    public WorkDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(T t) {
        String queryFormat = getConcreteInsertQuery();
        try(PreparedStatement preparedStatement = connection.prepareStatement(queryFormat)){
            concreteStatementInsert(preparedStatement , t);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.warn("Can`t insert element to database: " + e.getMessage());
        }
    }

    @Override
    public T get(long id) {
        String queryFormat = getConcreteGetQuery();
        T t = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(queryFormat)){
            preparedStatement.setLong(1, id);
            ResultSet set = preparedStatement.executeQuery();
            t = getConcreteElement(set);
        }catch (SQLException e){
            LOGGER.warn("Can`t get element from database: " + e.getMessage());
        }
        return t;
    }

    @Override
    public List<T> getAll() {
        String queryFormat = getConcreteGetAllQuery();
        List<T> list = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(queryFormat)){
            ResultSet set = preparedStatement.executeQuery();
            list = getConcreteElements(set);
        }catch (SQLException e){
            LOGGER.warn("Can`t get elements from database: " + e.getMessage());
        }
        return list;
    }

    /**
     * represents string query to the database for adding new data in the database
     *
     * @return
     *      <code>String</code> sql query
     */
    protected abstract String getConcreteInsertQuery();

    /**
     * represents string query to the database for getting one element from the database
     *
     * @return
     *      <code>String</code> sql query
     */
    protected abstract String getConcreteGetQuery();


    /**
     * represents string query to the database for getting all information from the database
     *
     * @return
     *      <code>String</code> sql query
     */
    protected abstract String getConcreteGetAllQuery();

    /**
     * creates special statement base on type of added element
     *
     * @param preparedStatement
     *      {@link PreparedStatement} instance
     * @param t
     *      added element to the database
     *
     * @throws SQLException
     */
    protected abstract void concreteStatementInsert(PreparedStatement preparedStatement, T t) throws SQLException;

    /**
     * receives concrete element from database based on getting query
     *
     * @param set
     *      {@link ResultSet} of {@link PreparedStatement} statement
     * @return
     *      received element
     *
     * @throws SQLException
     */
    protected abstract T getConcreteElement(ResultSet set) throws SQLException;

    /**
     * receives concrete elements from database based on getting query
     *
     * @param set
     *      {@link ResultSet} of {@link PreparedStatement} statement
     * @return
     *      received elements
     *
     * @throws SQLException
     */
    protected abstract List<T> getConcreteElements(ResultSet set) throws SQLException;

}
