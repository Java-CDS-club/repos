/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.beans.setup.SalaryGrade;
import org.smvdu.payroll.beans.upload.UploadFile;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
* Modified Date: 03 Nov 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
*/
public class SalaryGradeDB {

    private PreparedStatement ps;
    private ResultSet rs;

    private UserInfo userBean;

    private HibernateUtil helper;
    private Session session;
    
    public SalaryGradeDB()
    {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }

    public Exception update(ArrayList<SalaryGrade> grades)
    {
        try
        {
            session = helper.getSessionFactory().openSession();
            
            for(SalaryGrade sg : grades)
            {
                session.beginTransaction();

                SalaryGrade data = (SalaryGrade)session.get(SalaryGrade.class, sg.getCode());

                data.setName(sg.getName().toUpperCase());
                data.setMinValue(sg.getMinValue());
                data.setMaxValue(sg.getMaxValue());
                data.setGradePay(sg.getGradePay());
                data.setOrgcode(userBean.getUserOrgCode());
                session.update(data);
                session.getTransaction().commit();
            }
            return null;
        }   
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return e;
        }
        finally {
            session.close();
        }
            
            
        /*    Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update salary_grade_master set grd_name=?"
                    + ",grd_max=?,grd_min=?,grd_gp=? where grd_code=? and grd_org_id = ?");
            for(SalaryGrade sg : grades)
            {
                ps.setString(1, sg.getName());
                ps.setInt(2, sg.getMaxValue());
                ps.setInt(3, sg.getMinValue());
                ps.setInt(4, sg.getGradePay());
                ps.setInt(5, sg.getCode());
                ps.setInt(6, userBean.getUserOrgCode());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();      
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }           */
    }

    public ArrayList<SalaryGrade> load()    {
        try
        {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from SalaryGrade where orgcode = '"+userBean.getUserOrgCode()+"'");
            ArrayList<SalaryGrade> grades = (ArrayList<SalaryGrade>)query.list();
            session.getTransaction().commit();
            return grades;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
            
            /*      Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select * from salary_grade_master where grd_org_id = '"+userBean.getUserOrgCode()+"'");
            rs=ps.executeQuery();
            ArrayList<SalaryGrade> grades = new ArrayList<SalaryGrade>();
            while(rs.next())
            {
                SalaryGrade sg = new SalaryGrade();
                sg.setCode(rs.getInt(1));
                sg.setName(rs.getString(2));
                sg.setMaxValue(rs.getInt(3));
                sg.setMinValue(rs.getInt(4));
                sg.setGradePay(rs.getInt(5));
                grades.add(sg);
            }
            rs.close();
            ps.close();
            c.close();
            return grades;      
        }          
        catch(Exception e)
        {
            return null;
        }           */
    }
    
    public Exception save(SalaryGrade sg)   {
        try
        {
          SalaryGrade grade = new SalaryGrade();
          
          grade.setName(sg.getName().toUpperCase());
          grade.setMinValue(sg.getMinValue());
          grade.setMaxValue(sg.getMaxValue());
          grade.setGradePay(sg.getGradePay());
          grade.setOrgcode(userBean.getUserOrgCode());
          
          session = helper.getSessionFactory().openSession();
          session.beginTransaction();
          session.save(grade);
          session.getTransaction().commit();
          return null;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return e;
        }
        finally {
            session.close();
        }
      
        /*      Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into salary_grade_master(grd_name,"
                    + "grd_max,grd_min,grd_gp,grd_org_id) values(?,?,?,?,?)");
            ps.setString(1, sg.getName());
            ps.setInt(2, sg.getMaxValue());
            ps.setInt(3, sg.getMinValue());
            ps.setInt(4, sg.getGradePay());
            ps.setInt(5, userBean.getUserOrgCode());
            ps.executeUpdate();
          //  rs=ps.getGeneratedKeys();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return code;        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }           */
    }
    
    public Exception saveFile(UploadFile file)   {
        try
        {
          
/*            SalaryGrade grade = new SalaryGrade();
            
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/tmp");
            CSVReader reader = new CSVReader(new FileReader(path+"/"+file.getName()), ',', '\"', 1);
            ColumnPositionMappingStrategy<SalaryGrade> mappingStrategy 
                                 = new ColumnPositionMappingStrategy<SalaryGrade>();
            mappingStrategy.setType(SalaryGrade.class);
            String[] columns = new String[] {"Name","MaxValue","MinValue","GradePay"};
            mappingStrategy.setColumnMapping(columns);
        
            CsvToBean<SalaryGrade> csv = new CsvToBean<SalaryGrade>();
            List<SalaryGrade> SalaryGradeList = csv.parse(mappingStrategy, reader);
            
            for (int i = 0; i < SalaryGradeList.size(); i++) 
            {
                
                SalaryGrade SGDetail = SalaryGradeList.get(i);
                // display CSV values
                grade.setName(SGDetail.getName());
                grade.setMaxValue(SGDetail.getMaxValue());
                grade.setMinValue(SGDetail.getMinValue());
                grade.setGradePay(SGDetail.getGradePay());
                grade.setOrgcode(userBean.getUserOrgCode());
                
                session = helper.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(grade);
                session.getTransaction().commit();
                                      
            }
            
            reader.close();
            return null;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return e;
        }
        finally {
            session.close();
        }
            
  */          
              Connection c = new CommonDB().getConnection();
            
            ps=c.prepareStatement("insert into salary_grade_master(grd_name, grd_max, grd_min, grd_gp, grd_org_id) values(?,?,?,?,?)");
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/tmp");
            CSVReader reader = new CSVReader(new FileReader(path+"/"+file.getName()), ',', '\"', 1);
            ColumnPositionMappingStrategy<SalaryGrade> mappingStrategy 
                                 = new ColumnPositionMappingStrategy<SalaryGrade>();
            mappingStrategy.setType(SalaryGrade.class);
            String[] columns = new String[] {"Name","MaxValue","MinValue","GradePay"};
            mappingStrategy.setColumnMapping(columns);
        
            CsvToBean<SalaryGrade> csv = new CsvToBean<SalaryGrade>();
            List<SalaryGrade> SalaryGradeList = csv.parse(mappingStrategy, reader);

            for (int i = 0; i < SalaryGradeList.size(); i++) 
            {
                
                SalaryGrade SGDetail = SalaryGradeList.get(i);
                // display CSV values
                ps.setString(1,SGDetail.getName());
                ps.setInt(2, SGDetail.getMaxValue());
                ps.setInt(3, SGDetail.getMinValue());
                ps.setInt(4, SGDetail.getGradePay());
                ps.setInt(5, userBean.getUserOrgCode());
                ps.executeUpdate();
                
              
            }
            reader.close();
            ps.close();
            c.close();
            return null;        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }               

    }       
    

}
