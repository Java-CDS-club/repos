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
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.hibernate.Query;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.api.Administrator.CollegeList;
import org.smvdu.payroll.api.BankDetails.BankProfileDetails;
import org.smvdu.payroll.api.EncryptionUtil;
import org.smvdu.payroll.api.email.MassageProperties;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.beans.setup.Designation;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.EmployeeSearchBean;
import org.smvdu.payroll.beans.GenderDetails;
import org.smvdu.payroll.beans.SimpleEmployee;
import org.smvdu.payroll.beans.setup.EmployeeType;
import org.smvdu.payroll.beans.setup.SalaryGrade;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.upload.UploadFile;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
import org.smvdu.payroll.user.UserRegistration;
import org.smvdu.payroll.user.changePassword;

/**
 *
 *  Copyright (c) 2010 - 2011 SMVDU, Katra.
 *  Copyright (c) 2014 - 2017 ETRG, IITK. 
 *  All Rights Reserved.
 ** Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 ** Redistributions of source code must retain the above copyright
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
 *  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
 *  Modified Date: 4 AUG 2014, 12 FEB 2015, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
 *  GUI Modification : 20 June 2015, Om Prakash(omprakashkgp@gmail.com)
 *  Last Modification :(Change Password of Employee +Hibernate conversion), January 2017, Om Prakash
 */

public class EmployeeDB {

    private int orgCode;
    //private UserInfo uf = null;
    private UserInfo uf;
    private int status;
    private String url;
    private HibernateUtil helper;
    private org.hibernate.Session sess;
    private org.hibernate.Session seslogin;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public EmployeeDB() {
        //changes for the orgcode because by uf object not get the orgcode
        uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
        if(orgCode==0){
        LoggedEmployee le =(LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        orgCode = le.getUserOrgCode();
        }
        //uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        //orgCode = uf.getUserOrgCode();
        
    }
    private PreparedStatement ps;
    private ResultSet rs;

   
    public ArrayList<SimpleEmployee> loadPendingEmployee() {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_code,emp_name,emp_active from employee_master where "
                    + " emp_code not in (select es_code from employee_salary_summery where "
                    + "es_month=  ? and es_year=?) and emp_org_code=?");
            ps.setInt(1, uf.getCurrentMonth());
            ps.setInt(2, uf.getCurrentYear());
            ps.setInt(3, orgCode);
            rs = ps.executeQuery();
            ArrayList<SimpleEmployee> data = new ArrayList<SimpleEmployee>();
            //NewSalaryProcessing ns = new NewSalaryProcessing();
            while (rs.next())
            {
                SimpleEmployee se = new SimpleEmployee();
                se.setCode(rs.getString(1));
                se.setName(rs.getString(2));
                se.setSalaryStatus(rs.getBoolean(3));
                if(se.isSalaryStatus() == true)
                {
                    se.setImageStatus("/img/Active.png");
                }
                else
                {
                    se.setImageStatus("/img/InActive.png");
                }
                data.add(se);
            }
            rs.close();
            ps.close();
            c.close();
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deactivate(String s) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("update employee_master set emp_active=0 where emp_code=?");
            ps.setString(1, s);
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTypeCode(String empCode) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_type_code from employee_master "
                    + "where emp_code=?");
            ps.setString(1, empCode);
            rs = ps.executeQuery();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Employee loadProfile(String empCode, int orgId) {
    try{
        sess = helper.getSessionFactory().openSession();
        sess.beginTransaction();
        Query query = sess.createQuery("select ee.code, ee.name, dpt.name, ds.name, et.name, ee.empId, ee.bankAccNo, ee.pfAccNo, ee.panNo,"
                + " ee.type, ee.grade, sg.name, ee.email, ee.dob, ee.doj, ee.phone, ee.fatherName, ee.dept, ee.desig, ee.title, ee.experience,"
                + " ee.qualification, ee.yearOfPassing, ee.previousEmployer, ee.address, ee.currentBasic, sg.gradePay, ee.ststus, ee.tgender, ee.bankIFSCcode, "
                + " ee.bankStatus, ee.dateOfResig, ee.empLeaDate, ee.empNotDay, ee.genDetailCode, ee.aadhaarNo from Employee ee, Designation ds, Department dpt, EmployeeType et, SalaryGrade sg "
                + " where ds.code=ee.desig and dpt.code=ee.dept and et.code=ee.type and sg.code=ee.grade and ee.code='"+empCode+"' and ee.orgCode='"+orgId+"'");
        List<Object[]> pending = query.list();
        for(Object[] empd: pending)
        {
           Employee emp = new Employee();
           emp.setCode(empd[0].toString());
           emp.setName(empd[1].toString());
           Department dept = new Department();
           dept.setName(empd[2].toString());
           emp.setDept(dept.getCode());
           emp.setDeptName(dept.getName());
           Designation desig = new Designation();
           desig.setName(empd[3].toString());
           emp.setDesig(desig.getCode());
           emp.setDesigName(desig.getName());
           EmployeeType et = new EmployeeType();
           et.setName(empd[4].toString());
           et.setCode((Integer)empd[9]);
           emp.setType(et.getCode());
           emp.setTypeName(et.getName());
           emp.setEmpId((Integer)empd[5]);                   
           if(empd[6].equals("") == true)
           {
                emp.setBankAccNo("Enter Bank A/c Number");
           }
           else
           {
                emp.setBankAccNo(empd[6].toString());
           }
           emp.setPfAccNo(empd[7].toString());
           emp.setPanNo(empd[8].toString());
           SalaryGrade sg = new SalaryGrade();
           sg.setCode((Integer)empd[10]);
           sg.setName(empd[11].toString());
           emp.setGrade(sg.getCode());
           emp.setBandName(sg.toString());
           emp.setEmail(empd[12].toString());
           emp.setDob(empd[13].toString());
           emp.setDoj(empd[14].toString());
           emp.setPhone(empd[15].toString());
           emp.setFatherName(empd[16].toString());
           emp.setDept((Integer)empd[17]);
           emp.setDesig((Integer)empd[18]);
           emp.setTitle(empd[19].toString());
           emp.setExperience((Integer)empd[20]);
           emp.setQualification(empd[21].toString());
           emp.setYearOfPassing((Integer)empd[22]);
           emp.setPreviousEmployer(empd[23].toString());
           emp.setAddress(empd[24].toString());
           emp.setCurrentBasic((Integer)empd[25]);
           emp.setGradePay((Integer)empd[26]);
           emp.setStstus(true);
           boolean b = new GenderDetails().gen(emp.getDob(), emp.getDoj());
           if(b == true)
           {
                emp.setGenDetails("Employee is Senior Citizen");
           }
           else 
           {
               emp.setGenDetails("");
           }
           emp.setTgender((Integer)empd[28]);
           if(empd[29] == null) 
           {
                emp.setBankIFSCcode("Enter Bank IFSC Code");
           }
           else
           {
                emp.setBankIFSCcode(empd[29].toString());
           }
           if(empd[31]==null)
           {
               emp.setDateOfResig(" ");
           }
           else{
                emp.setDateOfResig(empd[31].toString());
            }
           if(empd[32]==null)
           {
               emp.setEmpLeaDate(" ");
           }   
           else{
               emp.setEmpLeaDate(empd[32].toString());
           }
           if(empd[33]==null)
           {
               emp.setEmpNotDay(0);
           }    
           else{
               emp.setEmpNotDay((Integer)empd[33]);
           }
           if(empd[34]==null)
           {
               emp.setGenDetails("Enter the citizen");
           }   
           else{
                emp.setGenDetails(Integer.toString((Integer)empd[34]));
           }
           emp.setAadhaarNo(empd[35].toString());
           return emp;
        }
    
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
        return null;
    }
    return null;
 }

    public String getStatusImage(int status) {
        try {
            if (status == 1) {
                return "/img/success.png";
            } else {
                return "/img/err.png";
            }
        } catch (Exception ex) {
            //System.out.println("Error : " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public boolean codeExist(String code) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_name from employee_master where "
                    + "emp_code=? and emp_org_code=?");
            ps.setString(1, code);
            ps.setInt(2, orgCode);
            rs = ps.executeQuery();
            rs.next();
            String s = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Updating Employee Salary Status

    public boolean updateEmployeeSalaryStatus(ArrayList<SimpleEmployee> simpleEmployee)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            for(SimpleEmployee sim:simpleEmployee)
            {
                if(sim.isSalaryStatus() == true)
                {
                    pst = cn.prepareStatement("update employee_master set emp_active = '"+1+"' where emp_code = '"+sim.getCode()+"' and emp_org_code = '"+orgCode+"'");
                }
                else
                {
                    pst = cn.prepareStatement("update employee_master set emp_active = '"+0+"' where emp_code = '"+sim.getCode()+"' and emp_org_code = '"+orgCode+"'");
                }
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Employee> loadProfiles(String s) {
        try {
            Connection c = new CommonDB().getConnection();
            String q = "select emp_code,emp_name,"
                    + " dept_name,desig_name,emp_type_name,emp_id,grd_name,grd_max,"
                    + "grd_min,emp_phone,emp_email,emp_org_code,emp_father,emp_basic,"
                    + "EMP_TITLE,date_format(emp_doj,'%M-%y'),emp_active from employee_master left join "
                    + "designation_master on desig_code= emp_desig_code left join"
                    + " department_master on dept_code = emp_dept_code  left join"
                    + " employee_type_master on emp_type_id = emp_type_code left "
                    + "join salary_grade_master on grd_code=emp_salary_grade "
                    + "" + s + " ";
            //System.out.println("QUARY : " + q);
            ps = c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<Employee> data = new ArrayList<Employee>();
            int k = 1;
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setCode(rs.getString(1));
                emp.setName(rs.getString(2));
                Department dept = new Department();
                dept.setName(rs.getString(3));
                emp.setDept(dept.getCode());
                emp.setDeptName(dept.getName());
                Designation desig = new Designation();
                desig.setName(rs.getString(4));
                emp.setDesig(desig.getCode());
                emp.setDesigName(desig.getName());
                EmployeeType et = new EmployeeType();
                et.setName(rs.getString(5));
                emp.setType(et.getCode());
                emp.setTypeName(et.getName());
                emp.setEmpId(rs.getInt(6));
                emp.setSrNo(k);
                SalaryGrade sg = new SalaryGrade();
                sg.setName(rs.getString(7));
                sg.setMaxValue(rs.getInt(8));
                sg.setMinValue(rs.getInt(9));
                emp.setBandName(sg.toString());
                emp.setPhone(rs.getString(10));
                emp.setEmail(rs.getString(11));
                emp.setFatherName(rs.getString(13));
                emp.setCurrentBasic(rs.getInt(14));
                emp.setTitle(rs.getString(15));
                emp.setDoj(rs.getString(16));
                if (rs.getInt(17) == 1) {
                    emp.setButtonValue("Active");
                    emp.setStatusI("/img/Active.png");
                    emp.setEvent(true);
                } else {
                    emp.setButtonValue("Delete");
                    emp.setStatusI("/img/InActive.png");
                    emp.setEvent(false);
                }
                data.add(emp);
                k++;
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    /* This method has been developed for update emplyee profile recard.
    *
    */
    public boolean update(Employee emp) {
          try{
            Employee employee = new Employee().bankDetails(emp);
            sess = helper.getSessionFactory().openSession();
            seslogin = helper.getLoginSF().openSession();
            sess.beginTransaction();
            int empstatus;
            if (emp.getStstus() == true) {
                empstatus = 1;
            }
            else {
                empstatus = 0;
            }
            
            Query query1 = sess.createQuery("update Employee set name='"+emp.getName()+"', dept='"+emp.getDept()+"',desig='"+emp.getDesig()+"',type='"+emp.getType()+"',phone='"+emp.getPhone()+"', email='"+emp.getEmail()+"',"
                    + "dob='"+emp.getDob()+"',doj='"+emp.getDoj()+"',bankAccNo='"+emp.getBankAccNo()+"',pfAccNo='"+emp.getPfAccNo()+"',panNo='"+emp.getPanNo()+"'," 
                    + "grade='"+emp.getGrade()+"',currentBasic='"+emp.getCurrentBasic()+"',fatherName='"+emp.getFatherName()+"',title='"+emp.getTitle()+"',experience='"+emp.getExperience()+"',qualification='"+emp.getQualification()+"',"
                    + "yearOfPassing='"+emp.getYearOfPassing()+"',previousEmployer='"+emp.getYearOfPassing()+"',tgender='"+emp.getTgender()+"',address='"+emp.getAddress()+"',ststus ='"+empstatus+"'," 
                    + "bankIFSCcode='"+employee.getBankIFSCcode().trim()+"',bankStatus='"+empstatus+"',dateOfResig ='"+emp.getDateOfResig()+"',empLeaDate ='"+emp.getEmpLeaDate()+"',empNotDay='"+emp.getEmpNotDay()+"',"
                    + "genDetailCode='"+emp.getGenDetails()+"',aadhaarNo='"+emp.getAadhaarNo()+"' where code='"+emp.getCode().trim()+"' and orgCode='"+orgCode+"'");
            query1.executeUpdate();
            sess.getTransaction().commit();
            
            boolean dbExist = new CommonDB().checkLoginDBExists();
            if(dbExist){
            seslogin.beginTransaction();
            Query query2 = seslogin.createQuery("update EdrpUser set catrytype='"+emp.getCategoryT()+"' where username='"+emp.getEmail()+"'");
            query2.executeUpdate();
            seslogin.getTransaction().commit();
            seslogin.close();
            }
        }
        catch(Exception e)
        {
            sess.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            sess.close();
        }
    
    return false;
    }



    public Exception delete(int empId) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("delete from employee_master where emp_id=?");
            ps.setInt(1, empId);
            ps.executeUpdate();
            ps.close();
            c.close();
            return new Exception("Record Deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    public void bankDetails(Employee emp) {
        try {
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            //System.out.println("Code IFSC : " + emp.getBankIFSCcode());
            pst = cn.prepareStatement("select bank_name,branch_name,bank_ifsc_code from bankprofile where bank_ifsc_code = '" + emp.getBankIFSCcode() + "'");
            rst = pst.executeQuery();
            if (rst.next()) {
                emp.setBankName(rst.getString(1));
                emp.setBankBranchName(rst.getString(2));
                emp.setBankIFSCcode(rst.getString(3));
                //System.out.println(emp.getBankIFSCcode());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    public Exception save(Employee emp) {
        try{
            
            int d1=getDepartmentcode(emp.getDeptdcode());
            int dd1=getDesignationcode(emp.getDesigdcode());
            int t1=getEmpTypecode(emp.getEmptcode());
            int g1=getSalGradecode(emp.getSGcode());
            //System.out.println("\nsdin direct==="+emp.getDept());
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("insert into employee_master(emp_code,emp_name,"
                    + "emp_dept_code,emp_desig_code,emp_type_code,emp_phone,"
                    + "emp_email,emp_dob,emp_doj,emp_bank_accno,emp_pf_accno,emp_pan_no,"
                    + "emp_salary_grade,emp_gender,emp_org_code,emp_father,emp_basic,emp_title,"
                    + "emp_exp,emp_qual,emp_yop,emp_prev_emp,emp_address,emp_active,bank_ifsc_code,emp_bank_status,emp_aadhaar_no) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getName());
            if(emp.getDept()!=0)
                ps.setInt(3, emp.getDept());
            else
                ps.setInt(3, d1);
            if(emp.getDesig()!=0)
                ps.setInt(4, emp.getDesig());
            else
                ps.setInt(4, dd1);
            if(emp.getType()!=0)
                ps.setInt(5, emp.getType());
            else
                ps.setInt(5, t1);
            ps.setString(6, emp.getPhone());
            ps.setString(7, emp.getEmail());
            ps.setString(8, emp.getDob());
            ps.setString(9, emp.getDoj());
            ps.setString(10, emp.getBankAccNo());
            ps.setString(11, emp.getPfAccNo());
            ps.setString(12, emp.getPanNo());
            if(emp.getGrade()!=0)
                ps.setInt(13, emp.getGrade());
            else
                ps.setInt(13, g1);
            ps.setBoolean(14, emp.isMale());
            ps.setInt(15, orgCode);
            ps.setString(16, emp.getFatherName());
            ps.setInt(17, emp.getCurrentBasic());
            ps.setString(18, emp.getTitle());
            ps.setInt(19, emp.getExperience());
            ps.setString(20, emp.getQualification());
            ps.setInt(21, emp.getYearOfPassing());
            ps.setString(22, emp.getPreviousEmployer());
            ps.setString(23, emp.getAddress());
            ps.setInt(24, 1);
            ps.setString(25, emp.getBankIFSCcode().trim());
            ps.setInt(26, 1);
            ps.setString(27, emp.getAadhaarNo());
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
        
    }
   

    public ArrayList<Employee> selectNonBankedEmployee() {
        try {
            ArrayList<Employee> employee = new ArrayList<Employee>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select emp_code,emp_name,emp_bank_status from employee_master where emp_active = '" + 1 + "' and emp_org_code = '" + orgCode + "' and emp_bank_status = '" + false + "'");
            rst = pst.executeQuery();
            while (rst.next()) {
                Employee empl = new Employee();
                empl.setCode(rst.getString(1));
                empl.setName(rst.getString(2));
                empl.setBankStatus(rst.getBoolean(3));
                //System.out.println(empl.getName() + " : " + empl.getCode() + " : " + empl.getDesigName() + " : " + empl.getCurrentBasic());
                employee.add(empl);
            }
            pst.close();
            cn.close();
            return employee;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Updation For Non Employeed Bank
    public BankProfileDetails getParticularBankDetail(String bankIfsc) {
        try {
            BankProfileDetails bpd = new BankProfileDetails();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select bank_name,bank_ifsc_code,branch_name from bankprofile where bank_ifsc_code = '" + bankIfsc + "'");
            rst = pst.executeQuery();
            if (rst.next()) {
                bpd.setBankName(rst.getString(1));
                bpd.setBankIFSCCode(rst.getString(2));
                bpd.setBankBranch(rst.getString(3));
            }
            return bpd;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean updateNonBankedEmployee(ArrayList<Employee> noBankedEmployee, EmployeeSearchBean emps) {
        try {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            for (Employee emp : noBankedEmployee) {
                if (emp.isBankStatus() == true) {
                    //System.out.println("update employee_master set emp_bank_name = '" + bn + "',bank_branch_name = '" + bb + "',bank_ifsc_code = '" + emps.getBfsc() + "',emp_bank_status = '" + 1 + "' where emp_code = '" + emp.getCode() + "' and emp_org_code = '" + orgCode + "'");
                    pst = cn.prepareStatement("update employee_master set bank_ifsc_code = '" + emps.getBfsc() + "',emp_bank_status = '" + 1 + "' where emp_code = '" + emp.getCode() + "' and emp_org_code = '" + orgCode + "'");
                    pst.executeUpdate();
                    pst.clearParameters();
                }
            }
            cn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    
    public Exception saveFamilyRecord(Employee emp) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("insert into employee_family_record(efr_emp_code,efr_membername,"
                    + "efr_relation,efr_dob,efr_dependent,efr_whetheremployed,efr_department,efr_org_id)"
                    + "values(?,?,?,?,?,?,?,?)");
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getMemberName());
            ps.setString(3, emp.getRelation());
            ps.setString(4, emp.getDob());
            ps.setString(5, emp.getDependent());
            ps.setString(6, emp.getWhetherEmployed());
            ps.setString(7, emp.getDeptName());
            ps.setInt(8, orgCode);
            //System.out.println("insavefamilyR empdb=====");
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
    
     public ArrayList<Employee> loadfamilyrecord(String empCode) {
        try {
            
            Connection c = new CommonDB().getConnection();
            String q = "select * from employee_family_record"
                    + " where efr_emp_code='"+empCode+"' and efr_org_id='" + orgCode + "'";
            
            ps = c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<Employee> data = new ArrayList<Employee>();
            Employee emp = null;
            int k = 1;
            while (rs.next()) {
                emp = new Employee();
                
                emp.setRecordId(rs.getInt(1));
                emp.setCode(rs.getString(2).trim());
                emp.setMemberName(rs.getString(3).trim());
                emp.setRelation(rs.getString(4));
                emp.setDob(rs.getString(5));
                emp.setDependent(rs.getString(6));
                emp.setWhetherEmployed(rs.getString(7));
                emp.setDeptName(rs.getString(8));
                emp.setSrNo(k);
                data.add(emp);
                k++;
               
            }
            rs.close();
            ps.close();
            c.close();
           
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
     
       
     public Exception DeleteFamilyRecord(int currentIndex, ArrayList<Employee> empfmlydata){
              try{
                Connection connection = new CommonDB().getConnection();
                
                for(Employee efr : empfmlydata)
                {
                    if(efr.getRecordId()== currentIndex){
                        
                        ps = connection.prepareStatement("delete from employee_family_record where efr_id= '"+efr.getRecordId()+"' and efr_emp_code = '"+efr.getCode()+"' and efr_membername= '"+efr.getMemberName()+"' and efr_org_id='" + orgCode + "' ");
                        ps.executeUpdate();
                        ps.clearParameters();
                    
                   }
                }    
                
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
          
      }
   
      public Exception UpdateFamilyRecord(Employee editedRecord){
        try{
                Connection connection = new CommonDB().getConnection();
                ps = connection.prepareStatement("update employee_family_record set efr_membername= ?, efr_relation= ?,"
                            + "efr_dob= ?, efr_dependent=?, efr_whetheremployed= ?, efr_department= ?"
                            + "where efr_emp_code= ? and efr_id=? and efr_org_id='" + orgCode + "' ");
                                        
                        ps.setString(1, editedRecord.getMemberName());
                        ps.setString(2, editedRecord.getRelation());
                        ps.setString(3, editedRecord.getDob());
                        ps.setString(4, editedRecord.getDependent());
                        ps.setString(5, editedRecord.getWhetherEmployed());
                        ps.setString(6, editedRecord.getDeptName());
                        ps.setString(7, editedRecord.getCode().toUpperCase());
                        ps.setInt(8, editedRecord.getRecordId());
                        ps.executeUpdate();
                        ps.clearParameters();
                                  
                    
                //}    
                
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
          
      }
       
    public Exception saveEmpHistory(Employee emp) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("insert into employee_service_history(esh_emp_code, esh_transactiontype,"
                    + "esh_tooffice, esh_towhichpost, esh_class, esh_ordernumber, esh_orderdate, esh_dofincrement,"
                    + "esh_payscale, esh_dept_deputation, esh_areatype, esh_org_id)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getTransactiontype());
            ps.setString(3, emp.getTooffice());
            ps.setInt(4, emp.getDesig());
            ps.setString(5, emp.getEmpservclass());
            ps.setInt(6, emp.getOrdernum());
            ps.setString(7, emp.getOrderdate());
            ps.setString(8, emp.getDateofincrement());
            ps.setInt(9, emp.getGrade());
            ps.setInt(10, emp.getDept());
            ps.setString(11, emp.getAreatype());
            ps.setInt(12, orgCode);
            //System.out.println("\n in save history R empdb==desig==="+emp.getDesig()+"\ndept===="+emp.getDept()+"\ngrade==="+emp.getGrade());
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
    
     public ArrayList<Employee> loadEmpHitory(String empCode) {
        try {
            
            Connection c = new CommonDB().getConnection();
            String q ="select esh_emp_id, esh_emp_code, esh_transactiontype,"
                     + "esh_tooffice, desig_name, esh_class, esh_ordernumber,"
                     + "esh_orderdate, esh_dofincrement, grd_name, dept_name,"
                     + "esh_areatype, grd_max, grd_min, grd_gp from employee_service_history "
                     + "left join department_master on esh_dept_deputation=dept_code "
                     + "left join designation_master on esh_towhichpost=desig_code "
                     + "left join salary_grade_master on esh_payscale=grd_code "                    
                     + "where esh_emp_code='"+empCode+"' and esh_org_id='" +orgCode+ "'";
            
            //System.out.println("QUARY : " + q);
            ps = c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<Employee> data = new ArrayList<Employee>();
            int k = 1;
            while (rs.next()) {
                
                Employee emp = new Employee();
              
                emp.setRecordId(rs.getInt(1));
                emp.setCode(rs.getString(2).trim());
                emp.setTransactiontype(rs.getString(3).trim());
                emp.setTooffice(rs.getString(4).trim());
                emp.setTowhichpost(rs.getString(5));
                emp.setEmpservclass(rs.getString(6));
                emp.setOrdernum(rs.getInt(7));
                emp.setOrderdate(rs.getString(8));
                emp.setDateofincrement(rs.getString(9));
                SalaryGrade sg = new SalaryGrade();
                sg.setName(rs.getString(10));
                sg.setMaxValue(rs.getInt(13));
                sg.setMinValue(rs.getInt(14));
                sg.setGradePay(rs.getInt(15));
                emp.setPayscale(sg.toString());
                emp.setDeputationdept(rs.getString(11));
                emp.setAreatype(rs.getString(12));
                // emp.setCurrentrecordindex(rs.getInt(1));
                emp.setSrNo(k);
                data.add(emp);
                k++;
                                   
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }
     }    
   
       
       public Exception DeleteServicehistoryRecord(int currentIndex, ArrayList<Employee> servicerecord){
          try{
                Connection connection = new CommonDB().getConnection();
                
                for(Employee esh : servicerecord)
                {
                    if(esh.getRecordId()== currentIndex){
                                          
                        //System.out.println("\n in line 774==deleteRecord=="+Currentindex);
                        ps = connection.prepareStatement("delete from employee_service_history where esh_emp_id= '"+esh.getRecordId()+"' and esh_emp_code = '"+esh.getCode()+"' and esh_org_id='" +orgCode+ "' ");
                        ps.executeUpdate();
                        ps.clearParameters();
                    }
                }    
               
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
          
      }
       
      public Exception UpdateServicehistoryRecord(Employee editRec){
          try{
                Connection connection = new CommonDB().getConnection();
                ps = connection.prepareStatement("update  employee_service_history set esh_transactiontype=?, esh_tooffice= ?,"
                            + "esh_towhichpost= ?, esh_class=?, esh_ordernumber= ?, esh_orderdate= ?,"
                            + "esh_dofincrement=?, esh_payscale= ?, esh_dept_deputation= ?, esh_areatype= ?"
                            + " where esh_emp_code= ? and esh_emp_id=? and esh_org_id='" +orgCode+ "'");
                          
                ps.setString(1, editRec.getTransactiontype());
                ps.setString(2, editRec.getTooffice());
                ps.setString(3, editRec.getTowhichpost());
                ps.setString(4, editRec.getEmpservclass());
                ps.setInt(5, editRec.getOrdernum());
                ps.setString(6, editRec.getOrderdate());
                ps.setString(7, editRec.getDateofincrement());
                ps.setString(8, editRec.getPayscale());
                ps.setString(9, editRec.getDeputationdept());
                ps.setString(10, editRec.getAreatype());
                ps.setString(11, editRec.getCode());
                ps.setInt(12, editRec.getRecordId());
                ps.executeUpdate();
                ps.clearParameters();
                   
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
          
      }
        
      /**
       * This method is used for Add Employee (insert employee data) 
       * @param emp
       * @return Exception
       */  
      public Exception saveEmpSupportData(Employee emp){
         try{
            int sd1=getDepartmentcode(emp.getSaldeptdcode());
            int jd1=getDepartmentcode(emp.getJoindeptdcode());
            int jdd1=getDesignationcode(emp.getJoindesigdcode());
            Connection connection = new CommonDB().getConnection();
            ps = connection.prepareStatement("insert into employee_master_support(code, entitled_cat, status_emp,working_type,sal_dept_code,"
                       + "joining_dept,joined_desig,gpf_no, nps_no,dps_no, house_type, house_no, ecr_no, page_no, posting_id,"
                       + "lic_policy_no,lic_doa, lic_dom, gi_policy_no, gi_doa, gi_dom, nextincrement_date, probation_date,"
                       + "confirmation_date, extention_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
               
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getEntitledCategory());
            ps.setString(3, emp.getEmployeeStatus());
            ps.setString(4, emp.getWorkingType());
            if(emp.getSaldept()!=0)
                ps.setInt(5, emp.getSaldept()); 
            else
                ps.setInt(5,sd1); 
            if(emp.getJoindept()!=0)
                ps.setInt(6, emp.getJoindept());
            else
                ps.setInt(6,jd1);
            if(emp.getJoindesig()!=0)
               ps.setInt(7, emp.getJoindesig());
            else
                ps.setInt(7,jdd1);
            ps.setString(8, emp.getGpfNo());
            ps.setString(9, emp.getNpsNo());
            ps.setString(10, emp.getDpsNo());
            ps.setString(11, emp.getHouseType());
            ps.setString(12, emp.getHouseNo());
            ps.setString(13, emp.getEcrNo());
            ps.setString(14, emp.getEcrPageNo());
            ps.setString(15, emp.getPostingId());
            ps.setString(16, emp.getPolicyNo());
            if(emp.getDoAcceptance().equals(""))
                ps.setString(17, null);
            else    
                ps.setString(17, emp.getDoAcceptance());
            if(emp.getDoMaturity().equals(""))
              ps.setString(18, null); 
            else
              ps.setString(18, emp.getDoMaturity());
            ps.setString(19, emp.getPolicyNo());
            if(emp.getDoAcceptance().equals(""))
                ps.setString(20, null); 
            else
                ps.setString(20, emp.getDoAcceptance());
            if(emp.getDoMaturity().equals(""))
                ps.setString(21, null);
            else
                ps.setString(21, emp.getDoMaturity());
            if(emp.getDoNextIncrement().equals(""))
                ps.setString(22, null);
            else
                ps.setString(22, emp.getDoNextIncrement());
            if(emp.getProbationDate().equals(""))
                ps.setString(23, null);
            else
                ps.setString(23, emp.getProbationDate());
            if(emp.getConfirmationDate().equals(""))
                ps.setString(24, null);
            else
                ps.setString(24, emp.getConfirmationDate());
            if(emp.getExtentionDate().equals(""))
                ps.setString(25, null);
            else
                ps.setString(25, emp.getExtentionDate());
            //System.out.println("doa==="+emp.getDoAcceptance());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
            connection.close();
            return null;
        }
        catch(Exception ex)
            {
                ex.printStackTrace();
                return ex;
            }
  }
      
      /**
       * This method is used for read the csv file 
       * used for display the information of csv file(for Add employee profile) 
       * @param file
       * @return boolean
       */
       public boolean loadData(UploadFile file){
            try{
                ArrayList<Employee> emps = new ArrayList<Employee>();
                boolean emems=false;
                Connection c = new CommonDB().getConnection();
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/tmp");
                CSVReader reader = new CSVReader(new FileReader(path+"/"+file.getName()), ',', '\"', 1);
            
                ColumnPositionMappingStrategy<Employee> mappingStrategy
                    = new ColumnPositionMappingStrategy<Employee>();
                mappingStrategy.setType(Employee.class);
                String[] columns = new String[] {"Code","Name","Deptdcode","Desigdcode","Emptcode","Phone","Email","Dob","Doj","SGcode","CurrentBasic","EntitledCategory","EmployeeStatus","WorkingType","Saldeptdcode","Joindeptdcode","Joindesigdcode"};
                mappingStrategy.setColumnMapping(columns);
                CsvToBean<Employee> csv = new CsvToBean<Employee>();
                List<Employee> EmployeeList = csv.parse(mappingStrategy, reader);
                for (int i = 0; i < EmployeeList.size(); i++)
                {
                    Employee empDetail = EmployeeList.get(i);
                    /*FacesContext fc = FacesContext.getCurrentInstance();
                    FacesMessage message = new FacesMessage();
                    String mess="Plz Enter EmailID In Correct Format ";
                    if (empDetail.getEmail().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false) {

                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary(mess);
                    fc.addMessage("", message);
                    return mess;
                    }*/
                    emems=InsertAllEmpData(empDetail);
                                        
                }
                reader.close();
                return emems;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
        /**
        * This method is used to load data of Employee for Edit employee profile
        * @param empCode
        * @return Employee
        */ 
        public Employee loadEmpsupportProfile(String empCode){
        try {
            
            Connection c = new CommonDB().getConnection();
            String q="select entitled_cat, status_emp, working_type, sal_dept_code,joining_dept, joined_desig,"
                    +"gpf_no, nps_no, dps_no, house_type, house_no, ecr_no, page_no, posting_id, lic_policy_no, lic_doa,"
                    +"lic_dom, gi_policy_no, gi_doa, gi_dom, nextincrement_date, probation_date, confirmation_date, extention_date "
                    +"from employee_master_support "
                    +"left join employee_master on emp_code = code where code= ?";
                    //System.out.println("Employeecode===="+empCode);
            ps = c.prepareStatement(q);
            ps.setString(1, empCode.trim());
            rs = ps.executeQuery();
            Employee emp = null;
            Department dept = new Department();
            while(rs.next()){
                emp = new Employee();
                emp.setEntitledCategory(rs.getString(1));
                emp.setEmployeeStatus(rs.getString(2));
                emp.setWorkingType(rs.getString(3));
                dept = new Department();
                dept.setCode(rs.getInt(4));
                emp.setDeptName(dept.getName());
                emp.setSaldept(rs.getInt(4));
                dept = new Department();
                dept.setCode(rs.getInt(5));
                emp.setDeptName(dept.getName());
                emp.setJoindept(rs.getInt(5));
                Designation desig = new Designation();
                desig.setCode(rs.getInt(6));
                emp.setDesigName(desig.getName());
                emp.setJoindesig(rs.getInt(6));
                emp.setGpfNo(rs.getString(7));
                emp.setNpsNo(rs.getString(8));
                emp.setDpsNo(rs.getString(9));
                emp.setHouseType(rs.getString(10));
                emp.setHouseNo(rs.getString(11));
                emp.setEcrNo(rs.getString(12));
                emp.setEcrPageNo(rs.getString(13));
                emp.setPostingId(rs.getString(14));
                emp.setPolicyNo(rs.getString(15));
                emp.setDoAcceptance(rs.getString(16));
                emp.setDoMaturity(rs.getString(17));
                emp.setPolicyNo(rs.getString(18));
                emp.setDoAcceptance(rs.getString(19));
                emp.setDoMaturity(rs.getString(20));
                emp.setDoNextIncrement(rs.getString(21));
                emp.setProbationDate(rs.getString(22));
                emp.setConfirmationDate(rs.getString(23));
                emp.setExtentionDate(rs.getString(24));
            }
            rs.close();
            ps.close();
            c.close();
            return emp;

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
         }
    }
      
    /**
     * This method is check that employee code exists or not 
     * @param empcode
     * @return boolean
     */  
    public boolean codeExistinsupport(String empcode) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select code from employee_master_support where "
                    + "code= ? ");
            ps.setString(1, empcode);
            rs = ps.executeQuery();
            rs.next();
            String s = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * This method is used for update the employee profile 
     * @param empsupp
     * @return 
     */
    public boolean updateEmpSupport(Employee empsupp) {
        try {
                Connection c = new CommonDB().getConnection();
                FacesContext fc=FacesContext.getCurrentInstance();

                ps=c.prepareStatement("update employee_master_support set  entitled_cat=?, status_emp=?, working_type=?,"
                        +"sal_dept_code=?, joining_dept=?,  joined_desig=?, gpf_no=?, nps_no=?, dps_no=?,  house_type=?, house_no=?,"
                        +"ecr_no=?,  page_no=?, posting_id=?, lic_policy_no=?, lic_doa=?, lic_dom=?, gi_policy_no=?, gi_doa=?, gi_dom=?," 
                        +"nextincrement_date=?, probation_date=?, confirmation_date=?, extention_date=? "
                        +"where code = '"+empsupp.getCode().trim()+"' ");
                ps.setString(1, empsupp.getEntitledCategory());
                ps.setString(2, empsupp.getEmployeeStatus());
                ps.setString(3, empsupp.getWorkingType());
                ps.setInt(4, empsupp.getSaldept());
                ps.setInt(5, empsupp.getJoindept());
                ps.setInt(6, empsupp.getJoindesig());
                ps.setString(7, empsupp.getGpfNo());
                ps.setString(8, empsupp.getNpsNo());
                ps.setString(9, empsupp.getDpsNo());
                ps.setString(10, empsupp.getHouseType());
                ps.setString(11, empsupp.getHouseNo());
                ps.setString(12, empsupp.getEcrNo());
                ps.setString(13,empsupp.getEcrPageNo());
                ps.setString(14, empsupp.getPostingId());
                ps.setString(15, empsupp.getPolicyNo());
                if(empsupp.getDoAcceptance().equals(""))
                    ps.setString(16, null);
                else    
                    ps.setString(16, empsupp.getDoAcceptance());
                if(empsupp.getDoMaturity().equals(""))
                    ps.setString(17, null); 
                else
                    ps.setString(17, empsupp.getDoMaturity());
                ps.setString(18, empsupp.getPolicyNo());
                if(empsupp.getDoAcceptance().equals(""))
                    ps.setString(19, null); 
                else
                    ps.setString(19, empsupp.getDoAcceptance());
                if(empsupp.getDoMaturity().equals(""))
                    ps.setString(20, null);
                else
                    ps.setString(20, empsupp.getDoMaturity());
                if(empsupp.getDoNextIncrement().equals(""))
                    ps.setString(21, null);
                else
                    ps.setString(21, empsupp.getDoNextIncrement());
                if(empsupp.getProbationDate().equals(""))
                    ps.setString(22, null);
                else
                    ps.setString(22, empsupp.getProbationDate());
                if(empsupp.getConfirmationDate().equals(""))
                    ps.setString(23, null);
                else
                    ps.setString(23, empsupp.getConfirmationDate());
                if(empsupp.getExtentionDate().equals(""))
                    ps.setString(24, null);
                else
                    ps.setString(24, empsupp.getExtentionDate());
                ps.executeUpdate();
                ps.clearParameters();
                ps.close();
                c.close();
                return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * This method is used for add employee profile
     * @param emp
     * @return boolean
     */
    public boolean InsertAllEmpData(Employee emp){
      
        try{
            boolean em = false;
            if(codeExist(emp.getCode())){
                System.out.println("Employee already exists in employee_master for "+emp.getCode());
                return em;
            }
            String password=new EncryptionUtil().createDigest("MD5", emp.getCode());
            Exception eloginmachanism =new UserRegistration().EmployeeRegistration(emp.getEmail(), password, emp.getPhone(),emp.getName(),"",emp.getAddress(),emp.getCategoryT(),orgCode,"EmpReg");
            if(eloginmachanism == null) {
                        
                Exception ee =save(emp);
                Exception ex =saveEmpSupportData(emp);
               
                if(ex!=null){
                    sess = helper.getSessionFactory().openSession();    
                    sess.beginTransaction();
                    Query query1 = sess.createQuery("delete from Employee where code = '"+emp.getCode()+"' and orgCode='" +orgCode+ "'");
                    query1.executeUpdate();
                    sess.getTransaction().commit();
                    }
                    else
                    {
                        System.out.println("Employee registration entry insert in  both tables for : "+emp.getCode());
                        em=true;
                    }
                    if(ee!=null) {
                        sess.beginTransaction();
                        Query query2 = sess.createQuery("delete from EmployeeMasterSuport where emsId= '"+emp.getEmpId()+"' emsCode = '"+emp.getCode()+"' ");
                        query2.executeUpdate();
                        sess.getTransaction().commit();
                    
                    }
                    else{
                        System.out.println("employee registration entry insert in  both tables for : "+emp.getCode());
                        em=true;
                    }
                
            }
            
            else{
                System.out.println(" problem in employee registration for  : "+emp.getCode());
                return em;
            }
            
            UserDB ud = new UserDB();
            String emailId = emp.getEmail();
            int orgId = orgCode;
            int UserId = ud.getUserId(emailId);
            int roleId=ud.getRoleExists(emailId,orgId);
            Exception adduser_role;
            /** Add employee role if employee role not exists in institute */ 
            if(roleId!= 6 ){
                adduser_role = new UserRegistration().AddUserRole(emailId, orgId,"EmpReg",UserId );
            }
            
            return em;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
  }
    
    /**
     * This method is used for to get Department Code
     * @param Dcode
     * @return Integer 
     */
    public int getDepartmentcode(String Dcode ) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select dept_code from department_master "
                    + "where dept_dcode='"+Dcode+"' ");
            rs = ps.executeQuery();
            rs.next();
            int dc = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return dc;
        } catch (Exception e) {
            return -1;
        }
    }
    
    /**
     * This method is used for to get Designation Code
     * @param Desigdcode
     * @return Integer 
     */
    public int getDesignationcode(String Desigdcode ) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select desig_code from designation_master "
                    + "where desig_dcode='"+Desigdcode+"' ");
            rs = ps.executeQuery();
            rs.next();
            int desigcode = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return desigcode;
        } catch (Exception e) {
            return -1;
        }
    }
    
    /**
     * This method is used for to get Employee type Code
     * @param emptypecode
     * @return Integer
     */
    public int getEmpTypecode(String emp_tcode ) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_type_id from employee_type_master "
                    + "where emp_tcode='"+emp_tcode+"' ");
            rs = ps.executeQuery();
            rs.next();
            int empcode = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return empcode;
        } catch (Exception e) {
            return -1;
        }
    }
    
    /**
     * This method is used for to get Salary Grade Code
     * @param grd_name
     * @return Integer
     */    
    public int getSalGradecode(String grd_name) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select grd_code from salary_grade_master "
                    + "where grd_name='"+grd_name+"' ");
            rs = ps.executeQuery();
            rs.next();
            int grdcode = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return grdcode;
        } catch (Exception e) {
            return -1;
        }
    }

   /**
     * update the Password for user for Specific organization
     * @param 
     * @return 
     * 
     */
    public Exception updateEmpPassword(Employee editedRecord)
    {
        try{
                sess = helper.getSessionFactory().openSession();
                String pass= new EncryptionUtil().createDigest("MD5",editedRecord.getPassword());
                String changepassinDb=new changePassword().changePaswordInLoginDB(pass, editedRecord.getEmail());
                sess.beginTransaction();
                Query query = sess.createQuery("update UserMaster set uspass = '"+pass+"' where usname = '"+editedRecord.getEmail()+"'");
                query.executeUpdate();
                sess.getTransaction().commit();
                sendChangePasswordMail(editedRecord);
        }
        catch(Exception e)
        {
            sess.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            sess.close();  
        }
        return null;
    
 }

    
    public boolean sendChangePasswordMail(Employee emp)  {
              try
              {
                  String fromEmail = new String();
                  String fromPassword = new String();
                  String smtpHostName;
                  int port;
                  final String[] f = new CollegeList().getSMTPAuthDetails().split("-");
                  port = Integer.parseInt(f[0]);
                  fromEmail = f[1];
                  fromPassword = f[2];
                  smtpHostName = f[3];
                Properties props = new Properties();
                props.put("mail.smtp.host", smtpHostName); 
                props.put("mail.smtp.user", fromEmail);
                //To use TLS
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.password", fromPassword);
                props.put("mail.smtp.port",String.valueOf(port)); 
                Session session = Session.getDefaultInstance(props, new Authenticator() {
                                     @Override
                                     protected PasswordAuthentication getPasswordAuthentication() {
                                             String username = f[1];
                                             String password = f[2];
                                               return new PasswordAuthentication(username, password);
                                     }
                                  });
                                 String to = emp.getEmail();
                    String from = f[4];
                    MassageProperties msgprop = new MassageProperties();
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(from));
                        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                        //   msg.setSubject(subject);
                        msg.setSubject(msgprop.getPropertieValue("reg"));
                        msg.setContent("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>"+msgprop.getPropertieValue("reg11")+"<hr>"
                          + emp.getPassword()+"<hr><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'><br>"+msgprop.getPropertieValue("reg")+"</font></font><br><hr>" 
                          + "</html>","text/html"); 
                      Transport transport = session.getTransport("smtp");
                      transport.send(msg);
                   }  catch(Exception exc) 
                   {
                        System.out.println(exc);
                   }
                  return true;
              }
              catch(Exception e)
              {
                  e.printStackTrace();
                  return false;
              }
    }

   /*
    * this method is used for get category code.  
    */   
    public String getCategoryType(String email)
    {
        try{
            seslogin = helper.getLoginSF().openSession();
            seslogin.beginTransaction();
            Query query = seslogin.createQuery("select catrytype from EdrpUser where username='"+email+"'");
            List ctype = query.list();
            seslogin.getTransaction().commit();
            return ctype.get(0).toString();            
        }
        catch(Exception e)
        {
            seslogin.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            seslogin.close();  
        }
        
    }
}
