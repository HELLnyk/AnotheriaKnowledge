package databaseconnect.hibernateconnect;

import databaseconnect.InterfaceWorkDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of {@link InterfaceWorkDAO} for concrete work with mapping result of eight queens
 * to the database
 *
 * @author hellnyk
 */
public class WorkDAOhibernateImpl implements InterfaceWorkDAO<EightQueenEntity> {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkDAOhibernateImpl.class);

    @Override
    public void insert(EightQueenEntity eightQueenEntity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(eightQueenEntity);
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error("Can get item: " + e.getMessage());
        }
    }

    @Override
    public EightQueenEntity get(long id) {
        EightQueenEntity findItem = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            findItem = session.get(EightQueenEntity.class, id);
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error("Cannot find this item: " + e.getMessage());
        }
        return findItem;
    }

    @Override
    public List<EightQueenEntity> getAll() {
        List<EightQueenEntity> listResult = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(EightQueenEntity.class);
            listResult = (List<EightQueenEntity>) criteria.list();
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error("Cannot get info from \"tbl_magic_squares\": " + e.getMessage());
        }
        return listResult;
    }
}
