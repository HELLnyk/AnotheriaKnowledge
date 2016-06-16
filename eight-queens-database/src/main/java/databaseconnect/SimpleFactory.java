package databaseconnect;

import databaseconnect.hibernateconnect.WorkDAOhibernateImpl;
import databaseconnect.jdbcconnect.dao.daoiml.WorkDAOjdbcImpl;

/**
 * Get concrete realization of {@link InterfaceWorkDAO}
 *
 * @author hellnyk
 */
public class SimpleFactory {

    /**
     * Set realization of {@link InterfaceWorkDAO} instance
     *
     * @return
     *      realization of {@link WorkDAOjdbcImpl} instance
     */
    public static InterfaceWorkDAO getWorkDAO(DatabaseConnect connect){
        switch (connect){
            case JDBC:
                return new WorkDAOjdbcImpl();
            case HIBERNATE:
                return new WorkDAOhibernateImpl();
            default:
                return null;
        }
    }
}
