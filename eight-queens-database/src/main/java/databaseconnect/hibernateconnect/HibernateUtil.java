package databaseconnect.hibernateconnect;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hellnyk
 */
public class HibernateUtil {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class.getName());

    /**
     * {@link SessionFactory} instance
     */
    private static final SessionFactory sessionFactory;

    /**
     * configure {@link SessionFactory} instance of correct hibernate configuration
     */
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (HibernateException e){
            LOGGER.error("Initial SessionFactory creation failed. " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * getter of {@link SessionFactory} instance
     *
     * @return
     *      {@link SessionFactory} instance
     */
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
