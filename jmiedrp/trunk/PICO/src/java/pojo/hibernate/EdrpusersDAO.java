
package pojo.hibernate;

/**
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2016
 *
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import utils.ExceptionLogUtil;

public class EdrpusersDAO  {



    
    public void save(Edrpusers edrpuser) {
        Session session = HibernateUtil.getSessionLoginFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(edrpuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(edrpuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public void update(Edrpusers edrpuser) {
        Session session = HibernateUtil.getSessionLoginFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(edrpuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(edrpuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public void delete(Edrpusers edrpuser) {
        Session session = HibernateUtil.getSessionLoginFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(edrpuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(edrpuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public List<Edrpusers> findAll() {
        Session session = HibernateUtil.getSessionLoginFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Edrpusers> list = session.createQuery("select u from Edrpusers u").list();
		Hibernate.initialize(list);
            return list;
        }
        catch (RuntimeException re) {
                throw re;
        }
        finally {
            session.close();
            }
        }
      public List<Edrpusers> RetrieveUser(String username, String password){

		Session session = HibernateUtil.getSessionLoginFactory();
            	try {
			session.beginTransaction();
                	List<Edrpusers> list  = session.createQuery("select u from Edrpusers u where u.edrpuName=:username and u.edrpuPassword=:password ").
                                               setParameter("username",username).setParameter("password",password).list();
                	return list;
            	}
            	finally {
			session.close();
            	}
     }
}
