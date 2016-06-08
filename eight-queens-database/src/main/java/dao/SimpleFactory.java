package dao;

import dao.daoiml.WorkDAOImpl;

import java.sql.Connection;

/**
 * Get concrete realization of {@link InterfaceWorkDAO}
 *
 * @author hellnyk
 */
public class SimpleFactory {

    /**
     * Set realization of {@link InterfaceWorkDAO} instance
     *
     * @param connection
     *      {@link Connection} instance to connect the database
     * @return
     *      in this case, only {@link WorkDAOImpl} instance
     */
    public static InterfaceWorkDAO getWorkDAO(Connection connection){
        return new WorkDAOImpl(connection);
    }
}
