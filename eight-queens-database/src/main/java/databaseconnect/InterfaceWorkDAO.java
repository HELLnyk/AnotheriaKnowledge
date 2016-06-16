package databaseconnect;

import java.util.List;

/**
 * Interface for add, get , getAll operations with database
 *
 * @author hellnyk
 */
public interface InterfaceWorkDAO<T> {

    /**
     * write value to the database
     *
     * @param t
     *      parameter for write
     */
    void insert(T t);

    /**
     * get record from database
     *
     * @param id
     *      id of record in the database
     * @return
     *      record of the database
     */
    T get(long id);

    /**
     * gea all records in the table of database
     * @return
     *      all records of the database
     */
    List<T> getAll();
}
