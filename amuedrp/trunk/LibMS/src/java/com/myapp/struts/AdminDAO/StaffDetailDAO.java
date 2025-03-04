/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AdminDAO;
import com.myapp.struts.admin.StaffExport;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Admin Registration Login_ID in Staff Detail Table Duplication
 */
public class StaffDetailDAO {


     public  List ViewAllTable(String library_id,String sublibrary) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        
        try {
            session.beginTransaction();


              Query        query = session.createSQLQuery("select a.*,b.*,c1.* from staff_detail a, sub_library b,login c1 where a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id and a.sublibrary_id=c1.sublibrary_id and a.library_id=c1.library_id and a.staff_id=c1.staff_id and  a.library_id=:library_id and a.sublibrary_id=:sublibrary_id")
                .addEntity(StaffDetail.class)
                             .addEntity(SubLibrary.class)
                             .addEntity(Login.class)

                        .setResultTransformer(Transformers.aliasToBean(StaffExport.class));
                      query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary);
                    obj=query.list();

            


            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }

public  boolean DeleteStaff(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  AcqPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

             Query query1 = session.createQuery("Delete From  CatPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query1.setString("staff_id", staff_id);
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);

            Query query2 = session.createQuery("Delete From  CirPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query2.setString("staff_id", staff_id);
            query2.setString("library_id", library_id);
            query2.setString("sublibrary_id", sublibrary_id);

            Query query3 = session.createQuery("Delete From  SerPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query3.setString("staff_id", staff_id);
            query3.setString("library_id", library_id);
            query3.setString("sublibrary_id", sublibrary_id);

            Query query4 = session.createQuery("Delete From  Privilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query4.setString("staff_id", staff_id);
            query4.setString("library_id", library_id);
            query4.setString("sublibrary_id", sublibrary_id);



            Query query5 = session.createQuery("Delete From  StaffDetail where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query5.setString("staff_id", staff_id);
            query5.setString("library_id", library_id);
            query5.setString("sublibrary_id", sublibrary_id);

            query.executeUpdate();
            query1.executeUpdate();
            query2.executeUpdate();
            query3.executeUpdate();
            query4.executeUpdate();
            query5.executeUpdate();
            tx.commit();



        }
        catch (HibernateException e) {
	                tx.rollback();
	                e.printStackTrace();
	            }
        finally
        {
    session.close();
        }
   return true;


}
public  StaffDetail searchEmail_id(String login_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query=null;
        StaffDetail staffobj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  StaffDetail  WHERE email_id =:login_id  ");
            query.setString("login_id", login_id);
            staffobj=( StaffDetail) query.uniqueResult();
            session.getTransaction().commit();
        }
         catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
        session.close();
        }
 return staffobj;
}
public  StaffDetail searchLibraryID(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
 Query query=null;
 StaffDetail staffobj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  StaffDetail  WHERE id.libraryId =:library_id");
            query.setString("library_id", library_id);
staffobj=( StaffDetail) query.uniqueResult();
        session.getTransaction().commit();
        }
         catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
          session.close();
        }
 return staffobj;
}
public  StaffDetail searchStaffId(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
 Query query=null;
 StaffDetail staffobj=null;
        try {
            session.beginTransaction();
           query= session.createQuery("FROM  StaffDetail  WHERE id.libraryId =:library_id and  id.staffId=:staff_id ");
            query.setString("library_id", library_id);
           
            query.setString("staff_id", staff_id);
           staffobj=( StaffDetail) query.uniqueResult();
           session.getTransaction().commit();
        }
        catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
        session.close();
        }
 return staffobj;
}
public  List<staffsubLib> searchAllStaff(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
List<staffsubLib> obj=null;
        try {
            session.beginTransaction();
        query = session.createSQLQuery("select a.*,b.* from staff_detail a inner join sub_library b on a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id where  a.library_id=:library_id")
                .addEntity(StaffDetail.class)
                .addEntity(SubLibrary.class)
                .setResultTransformer(Transformers.aliasToBean(staffsubLib.class));

            query.setString("library_id", library_id);
    obj=(List<staffsubLib>) query.list();
    session.getTransaction().commit();
           
        }
          catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
       session.close();
        }
 return obj;
}

/*
 METHOD TO GET LIST OF ALL STAFF SPECIFIC TO LIBRARY & SUBLIB
 */
public  List<staffsubLib1> searchAllStaffRole(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
List<staffsubLib1> obj=null;
       try {
            session.beginTransaction();
         query = session.createSQLQuery("select a.*,b.*,d.* from staff_detail a , sub_library b,login d where a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id and b.library_id=d.library_id and b.sublibrary_id=d.sublibrary_id and  a.email_id=d.login_id and  a.library_id=:library_id and a.sublibrary_id=:sublibrary_id")
                .addEntity(StaffDetail.class)
                .addEntity(SubLibrary.class)
                .addEntity(Login.class)
                .setResultTransformer(Transformers.aliasToBean(staffsubLib1.class));

            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
    obj=(List<staffsubLib1>)query.list();
           session.getTransaction().commit();
        }
       catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
           session.close();
        }
 return obj;
}

/*
 METHOD TO GET LIST OF ALL STAFF SPECIFIC TO LIBRARY 
 */
public  List<staffsubLib1> searchAllStaffRole1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
List<staffsubLib1> obj=null;
       try {
            session.beginTransaction();
         query = session.createSQLQuery("select a.*,b.*,d.* from staff_detail a , sub_library b,login d where a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id and b.library_id=d.library_id and b.sublibrary_id=d.sublibrary_id and  a.email_id=d.login_id and  a.library_id=:library_id")
                .addEntity(StaffDetail.class)
                .addEntity(SubLibrary.class)
                .addEntity(Login.class)
                .setResultTransformer(Transformers.aliasToBean(staffsubLib1.class));

            query.setString("library_id", library_id);
           
    obj=(List<staffsubLib1>)query.list();
           session.getTransaction().commit();
        }
       catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
           session.close();
        }
 return obj;
}

public  List<staffsubLib> searchAllStaff(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
List<staffsubLib> obj=null;
       try {
            session.beginTransaction();
         query = session.createSQLQuery("select a.*,b.* from staff_detail a inner join sub_library b on a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id where a.library_id=:library_id and a.sublibrary_id=:sublibrary_id")
                .addEntity(StaffDetail.class)
                .addEntity(SubLibrary.class)
                .setResultTransformer(Transformers.aliasToBean(staffsubLib.class));

            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
    obj=(List<staffsubLib>)query.list();
           session.getTransaction().commit();
        }
       catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
           session.close();
        }
 return obj;
}
public  StaffDetail searchStaffId(String staff_id,String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
StaffDetail staffobj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  StaffDetail  WHERE id.libraryId =:library_id and  id.staffId=:staff_id and sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("staff_id", staff_id);
            query.setString("sublibrary_id",sublibrary_id);
            staffobj=(StaffDetail)query.uniqueResult();
            session.getTransaction().commit();
        }
        catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
     session.close();
        }
return staffobj;
}
public   boolean insert1(StaffDetail obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }
        catch (HibernateException ex)
        {
         tx.rollback();
            System.out.println(ex.toString());
             return false;

       

        }
        finally
        {
       session.close();
        }
   return true;

}

public   boolean update1(StaffDetail obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.update(obj);
            tx.commit();



        }
        catch (HibernateException ex)
        {
         tx.rollback();
         ex.printStackTrace();
             return false;



        }
        finally
        {
     session.close();
        }
   return true;

}

   public void insert(StaffDetail staffDetails){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(staffDetails);
            tx.commit();
        }
        catch (HibernateException e) {
           
                tx.rollback();
         e.printStackTrace();
        }
        finally {
      session.close();
        }
    }
public void update(StaffDetail staffDetails) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(staffDetails);
            tx.commit();
        }
        catch (HibernateException e) {
         
                tx.rollback();
            e.printStackTrace();

        }
        finally {
       session.close();
        }
    }

public void delete(int user_id,String institute_id) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM StaffDetail  WHERE  id.staffId = :userId and id.library_id=:libraryId");
            query.setInteger("userId",user_id );
            query.setString("libraryId",institute_id );
            query.executeUpdate();
            tx.commit();
         
        }
catch (HibernateException e) {

                tx.rollback();
            e.printStackTrace();

        }
        finally {
        session.close();
        }
    }

public List getStaffDetails(){
  Session session =null;
    Query query=null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        query = session.createQuery("FROM StaffDetail");
        obj=query.list();
        session.getTransaction().commit();
           
        }
    catch (HibernateException e) {


            e.printStackTrace();

        }
        finally {
            session.close();
        }
         return obj;
}
public StaffDetail getStaffDetails1(String staffId,String libraryId){
 Session session =null;
    Query query=null;
    StaffDetail obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
             query= session.createQuery("FROM StaffDetail where id.staffId=:staffId and id.libraryId=:libraryId");
             query.setString("staffId", staffId);
             query.setString("libraryId", libraryId);
         obj= (StaffDetail) query.uniqueResult();
         session.getTransaction().commit();
        }
 catch (HibernateException e) {


            e.printStackTrace();

        }
        finally {
    session.close();
        }
         return obj;
}
public List getStaffDetails(String staffId,String libraryId){
 Session session =null;
    Query query=null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
             query= session.createQuery("FROM StaffDetail where id.staffId=:staffId and id.libraryId=:libraryId");
             query.setString("staffId", staffId);
             query.setString("libraryId", libraryId);
         obj=  query.list();
         session.getTransaction().commit();
        }
 catch (HibernateException e) {


            e.printStackTrace();

        }
        finally {
    session.close();
        }
         return obj;
}


}
