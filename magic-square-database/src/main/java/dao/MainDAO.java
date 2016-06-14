package dao;

import cientmsd.ClientForMSDatabase;
import entities.MagicSquareEntity;
import hbutil.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class for realization DAO operations with {@link MagicSquareEntity} instance
 *
 * @author hellnyk
 */
public class MainDAO{

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainDAO.class.getName());

    /**
     * add {@link MagicSquareEntity} instance to the database
     *
     * @param magicSquareEntity
     *      {@link MagicSquareEntity} instance for adding
     */
    public void insert(MagicSquareEntity magicSquareEntity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(magicSquareEntity);
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error("Cannot add element to database: " + e.getMessage());
        }
    }

    /**
     * get {@link MagicSquareEntity} instance from the database
     *
     * @param id
     *      id of this {@link MagicSquareEntity} instance in the database
     * @return
     *      {@link MagicSquareEntity} instance
     */
    public MagicSquareEntity get(long id) {
        MagicSquareEntity findItem = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            findItem = session.get(MagicSquareEntity.class, id);
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error("Cannot find this item: " + e.getMessage());
        }
        return findItem;
    }

    /**
     * get all record in the database
     *
     * @return
     *      {@link List} instance with list of {@link MagicSquareEntity} records in the database
     */
    public List<MagicSquareEntity> getAll() {
        List<MagicSquareEntity> listResult = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(ClientForMSDatabase.class);
            listResult = (List<MagicSquareEntity>) criteria.list();
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error("Cannot get info from \"tbl_magic_squares\": " + e.getMessage());
        }
        return listResult;
    }
}
