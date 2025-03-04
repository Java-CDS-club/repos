 /*
  * To change this template, choose Tools | Templates
  * and open the template in the editor.
  */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.smvdu.payroll.Admin.ServerDetails;
import org.smvdu.payroll.api.Administrator.CollegeList;
import org.smvdu.payroll.beans.composite.OrgController;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;

/**
 *
 *  Copyright (c) 2010 - 2011 - 2014 SMVDU, Katra.
 *  Copyright (c) 2014 - 2016 ETRG, IITK.
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
 *  EQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 * 
 * 
 *  Contributors: Members of ERP Team @ SMVDU, Katra
 *  Modified : August 13, 2016, Om Prakash (omprakashkgp@gmail.com), IITK
 *  Last Modified : November 24, 2016, Om
 */
 public class Org implements Serializable{
   
    private String adUserId;
    private ArrayList<Org> adminList;
    private String adPassword;
    private String oldPassword;
    private String adRePassword;
    private Date date;
    private PreparedStatement ps;
    private ResultSet rs;
    private String email;
    private String emailId;
    private String phone;
    private String name;
    private String web;
    private String address1;
    private String address2;
    private String tagLine;
    private int id;
    private String masterPassword;
    private String recoveryEMailId;
    private String tanno;
    private boolean status;
    private int notificationDay;
    private String city;
    private int pincode;
    private String state;
   // private int ll=0;
    private String countryCode = "+91";
  //  private int regionCode = 512;
    private String instDomain;
    private String toi;
    private String affiliation;
    private String adminfn;
    private String adminln;
    private String adminDesig;
    private String requestUrl;
    private String imgUrl;
    private int totalNoCollege;
    private int totalNoAcCollege;
    private int totalNoDeAcCollege;
    private String ipAddress;
    private String port;
    private String contextName;
    private boolean ipStatus;
    private int srNo;
    private Blob logo;
    
       
    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    public boolean isIpStatus() {
        return ipStatus;
    }

    public void setIpStatus(boolean ipStatus) {
        this.ipStatus = ipStatus;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }
    private ArrayList<Org> pendingList;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public int getTotalNoCollege() {
        totalNoCollege = new CollegeList().getTotalNoCollege();
        return totalNoCollege;
    }

    public void setTotalNoCollege(int totalNoCollege) {
        this.totalNoCollege = totalNoCollege;
    }

    public int getTotalNoAcCollege() {
        totalNoAcCollege = new CollegeList().getTotalNoAcCollege();
        return totalNoAcCollege;
    }

    public void setTotalNoAcCollege(int totalNoAcCollege) {
        this.totalNoAcCollege = totalNoAcCollege;
    }

    public int getTotalNoDeAcCollege() {
        totalNoDeAcCollege = new CollegeList().getTotalDeAcNoCollege();
        return totalNoDeAcCollege;
    }

    public void setTotalNoDeAcCollege(int totalNoDeAcCollege) {
        this.totalNoDeAcCollege = totalNoDeAcCollege;
    }
    
    
    private ArrayList<Org> collegeList;
    private ArrayList<Org> adminEmailIdList;
    public ArrayList<Org> getAdminEmailIdList() {
        adminEmailIdList = new CollegeList().adminEmaiIdList();
        dataGrid2.setValue(adminEmailIdList); 
        return adminEmailIdList;
    }

    public void setAdminEmailIdList(ArrayList<Org> adminEmailIdList) {
        this.adminEmailIdList = adminEmailIdList;
    }
    
     private UIData dataGrid;
     private UIData dataGrid1;
     private UIData dataGrid3;

    public UIData getDataGrid3() {
        return dataGrid3;
    }

    public void setDataGrid3(UIData dataGrid3) {
        this.dataGrid3 = dataGrid3;
    }
     
    public UIData getDataGrid1() {
        return dataGrid1;
    }

    public void setDataGrid1(UIData dataGrid1) {
        this.dataGrid1 = dataGrid1;
    }
     
      
    private UIData dataGrid4;

    public UIData getDataGrid4() {
        return dataGrid4;
    }

    public void setDataGrid4(UIData dataGrid4) {
        this.dataGrid4 = dataGrid4;
    }
    private UIData dataGrid5;
    
    public UIData getDataGrid5() {
        return dataGrid5;
    }

    public void setDataGrid5(UIData dataGrid5) {
        this.dataGrid5 = dataGrid5;
    }
    
    private UIData dataGrid9;

    public UIData getDataGrid9() {
        return dataGrid9;
    }

    public void setDataGrid9(UIData dataGrid9) {
        this.dataGrid9 = dataGrid9;
    }
    
    private ArrayList<Org> serverDetails;
    private ArrayList<Org> serverIpDetails;

    public ArrayList<Org> getServerIpDetails() {
        serverIpDetails = new ServerDetails().getIpServerDetails();
        dataGrid5.setValue(serverIpDetails); 
        return serverIpDetails;
    }

    public void setServerIpDetails(ArrayList<Org> serverIpDetails) {
        this.serverIpDetails = serverIpDetails;
    }
    

    public ArrayList<Org> getServerDetails() {
        serverDetails = new ServerDetails().getServerDetails();
        dataGrid4.setValue(serverDetails); 
        return serverDetails;
    }

    public void setServerDetails(ArrayList<Org> serverDetails) {
        this.serverDetails = serverDetails;
    }
    
    
    public ArrayList<Org> getPendingList() {
        pendingList = new CollegeList().getPendingCollegeList();
        dataGrid3.setValue(pendingList); 
        return pendingList;
    }

    public void setPendingList(ArrayList<Org> pendingList) {
        this.pendingList = pendingList;
    }
    
// String ip;
     public Org() throws UnknownHostException
     {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
         ipAddress = request.getRemoteAddr();
         //ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
         System.out.println("Ip : "+ipAddress+" : "+request.getRemoteHost());
         port = String.valueOf(request.getServerPort()); 
         
     }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
     
     private UIData dataGrid2;

    public UIData getDataGrid2() {
        return dataGrid2;
    }

    public void setDataGrid2(UIData dataGrid2) {
        this.dataGrid2 = dataGrid2;
    }
     
    public ArrayList<Org> getAdminList() {
       adminList = new CollegeList().activeAdminList();
       dataGrid.setValue(adminList); 
       return adminList;
    }

    public void setAdminList(ArrayList<Org> adminList) {
        this.adminList = adminList;
    }
    
    private ArrayList<Org> deactivateList;
    
    public ArrayList<Org> getDeactivateList() {
        deactivateList = new CollegeList().getDeactivateCollegeList();
        dataGrid9.setValue(deactivateList);
        return deactivateList;
    }

    public void setDeactivateList(ArrayList<Org> deactivateList) {
        this.deactivateList = deactivateList;
    }

    public String getAdUserId() {
        return adUserId;
    }

    public void setAdUserId(String adUserId) {
        this.adUserId = adUserId;
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    public String getAdRePassword() {
        return adRePassword;
    }

    public void setAdRePassword(String adRePassword) {
        this.adRePassword = adRePassword;
    }
     
     
    public UIData getDataGrid() {
        return dataGrid;
    }
    public void setDataGrid(UIData dataGrid)
    {
        this.dataGrid = dataGrid;
    }
    public ArrayList<Org> getCollegeList() throws SQLException {
        collegeList = new CollegeList().getCollegeList();
        dataGrid1.setValue(collegeList);  
        return collegeList;
    }

    public void setCollegeList(ArrayList<Org> collegeList) {
        this.collegeList = collegeList;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public boolean isStatus() {
          return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNotificationDay() {
        return notificationDay;
    }

    public void setNotificationDay(int notificationDay) {
       if(notificationDay != 0)
        this.notificationDay = notificationDay;
       else
           notificationDay = -1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRequestUrl() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        requestUrl = request.getRequestURL().toString()+"?emailid='"+this.getEmail()+"'";
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    
    public String getAdminDesig() {
        return adminDesig;
    }

    public void setAdminDesig(String adminDesig) {
        this.adminDesig = adminDesig;
    }

    public String getAdminfn() {
        return adminfn;
    }

    public void setAdminfn(String adminfn) {
        this.adminfn = adminfn;
    }

    public String getAdminln() {
        return adminln;
    }

    public void setAdminln(String adminln) {
        this.adminln = adminln;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getInstDomain() {
        return instDomain;
    }

    public void setInstDomain(String instDomain) {
        this.instDomain = instDomain;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
       
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToi() {
        return toi;
    }

    public void setToi(String toi) {
        this.toi = toi;
    }

    public String getTanno() {
        return tanno;
    }

    public void setTanno(String tanno) {
        this.tanno = tanno;
    }

    public String getMasterPassword() {
        return masterPassword;
    }
   
 
    private void copy()
    {
        OrgController ui = (OrgController)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("OrgController");
        Org o = ui.getCurrentOrg();
        this.id = o.id;
        this.name = o.name;
        
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public String getRecoveryEMailId() {
        return recoveryEMailId;
    }

    public void setRecoveryEMailId(String recoveryEMailId) {
        this.recoveryEMailId = recoveryEMailId;
    }

    private String vpass;

    public String getVpass() {
        return vpass;
    }

    public void setVpass(String vpass) {
        this.vpass = vpass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }
    
    public void getProfile()   {
        Org o = new OrgProfileDB().loadOrgProfile(id);
        this.name = o.getName();
        tagLine = o.getTagLine();
        phone = o.getPhone();
        email = o.getEmail();
        address1 = o.getAddress1();
        address2 = o.getAddress2();
        web = o.getWeb();
    }


    public void save()  {
        
        try{
        FacesContext fc=FacesContext.getCurrentInstance();
        /*if(new ServerDetails().getIpList(this.getIpAddress()) == true)
        {
               FacesMessage message = new FacesMessage();
               message.setSeverity(FacesMessage.SEVERITY_ERROR);
               message.setSummary("IP Address Of Your Server Is Blocked By Payroll Administrator");
               //message.setDetail("First Name Must Be At Least Three Charecter ");
               fc.addMessage("", message);
               return ;
        }*/
        if(!masterPassword.equals(vpass))
        {
            FacesContext.getCurrentInstance().addMessage("instpass2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords doesnot match", ""));
            return ;
        }
        if(this.getName().matches("^[a-zA-Z\\s]*$") == false)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid College Name");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        if(this.getAddress1().matches("^[a-zA-Z\\s]*$") == false)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Institute Address");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        if(this.getCity().matches("^[a-zA-Z\\s]*$") == false)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid City Name");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        /*if((this.getPincode().matches(".*[0-9].*") == false) || this.getPincode().length()<5)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Pincode");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }*/
        
        if((this.getPhone().matches(".*[0-9].*") == false) || this.getPhone().length()<10)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Phone Number");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        
      
        if(this.getWeb().matches("[www]+(\\.[a-z0-9-]+)+") == false)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Web Address");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        if(this.getAdminfn().matches("^[a-zA-Z\\s]*$") == false)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Institute Admin First Name");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        if(this.getAdminln().matches("^[a-zA-Z\\s]*$") == false)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Institute Admin Last Name");
            fc.addMessage("", message);
            return;
        }
        if(this.getAdminDesig().matches("^[a-zA-Z\\s]*$") == false)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Institute Admin Designation ");
            fc.addMessage("", message);
            return;
        }
          
        if(this.getEmail().trim().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false)
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Email Address");
            fc.addMessage("", message);
            return;
        }
        
        //if((String.valueOf(this.getLl()).matches(".*[0-9]{7}.*") == false) || String.valueOf(this.getLl()).length()!=7)
       /*
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Phone Number");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
           }
       */
        Exception e = new OrgProfileDB().save(this);
        if(e==null)
        {
            //FacesContext facesContext = FacesContext.getCurrentInstance();
            //Flash flash = facesContext.getExternalContext().getFlash();
            //flash.setKeepMessages(true);
            //flash.setRedirect(true);
            //facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sample info message", "richFaces rocks!"));
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Master user Created. User Name :"+this.getEmail()+",Password :"+masterPassword, ""));
            
            
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            return;  
        }
        }//  
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void update()    {
        try
        {
           
            Connection c = new CommonDB().getConnection();
            ps= c.prepareStatement("update org_profile set org_tagline=?, org_web=?, org_phone=?,"
                    + "org_address1=?, org_name=?, org_city=?, org_state=?, org_pincode=?  where org_id=?");
            ps.setString(1, tagLine);
            ps.setString(2, web);
            ps.setString(3, phone);
            ps.setString(4, address1);
            ps.setString(5, name);
            ps.setString(6, city);
            ps.setString(7, state);
            ps.setInt(8, pincode);
            ps.setInt(9, id);
            ps.executeUpdate();
            ps.close();
            c.close();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Institute information updated successfully", ""));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateRequest()
    {
        try
        {
            ArrayList<Org> orgProf = (ArrayList<Org>) dataGrid3.getValue();
            for(Org o : orgProf)
            {
                System.out.println(o.getName()+" : "+o.getWeb()+" : "+o.isStatus()+" "+o.getEmail());
            
            }            
            Exception ex = new CollegeList().updateRequest(orgProf); 
            if(ex == null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "College Are Updated", ""));
               
            }
                
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void updateAciveInActive()
    {
        
        try
        {
            ArrayList<Org> orgProf = (ArrayList<Org>) dataGrid1.getValue();
            for(Org o : orgProf)
            {
                System.out.println(o.getId()+":"+o.getName()+" : "+o.getWeb()+" : "+o.isStatus());
            }
            Exception ex = new CollegeList().update(orgProf);
            if(ex == null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "College/Institute Are Deactivated.", ""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void updateDeactivate()
    {
        try
        {
            ArrayList<Org> orgProfile = (ArrayList<Org>) dataGrid9.getValue();
            for(Org org : orgProfile)
            {    
                System.out.println(org.getId()+":"+org.getName()+":"+org.getWeb()+":"+org.isStatus());
            }
            Exception ex = new CollegeList().updateDeactivate(orgProfile);
            if(ex == null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "College/Institute Are Activated", ""));
                
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    
   public void deleteDeactivate()
   {
        try
        {
            ArrayList<Org> orgProfile = (ArrayList<Org>) dataGrid9.getValue();
            for(Org org : orgProfile)
            {    
                System.out.println(org.getId()+":"+org.getName()+":"+org.getWeb()+":"+org.isStatus());
            }
            Exception ex = new CollegeList().deleteDeactivate(orgProfile);
            if(ex == null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "College/Institute Are deleted. ", ""));
                
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    
    public void changePassword()
    {
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            if(this.getAdPassword().equals(this.getAdRePassword()) == false)
            {
                 message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("Password Are Not Matching.....Please Try Again.");
                 fc.addMessage("", message);
                 return;
            }
            if(new CollegeList().changePass(this) == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Password Are Updated");
                fc.addMessage("", message);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Error...");
                fc.addMessage(""+new CollegeList().changePass(this), message); 
            }
        }
        catch(Exception ex)
        {
            
        }
    }
    
    
    public void changeAdPassword()
    {
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            if(this.getAdPassword().equals(this.getAdRePassword()) == false)
            {
                 message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("Password Are Not Matching.....Please Try Again.");
                 fc.addMessage("", message);
                 return;
            }
            if(new CollegeList().changePass(this) == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                 message.setSummary("Password updated successfully");
                 fc.addMessage("", message);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Error...");
                fc.addMessage(""+new CollegeList().changePass(this), message); 
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void saveAdmin()
    {
        try
        {
            Exception ex = new CollegeList().adminDB(this);
            if(ex == null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "New Admin Is Added : "+this.getAdUserId(), ""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void updateAdmin()
    {
        try
        {
            System.out.println("Up Datong....");
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            
            ArrayList<Org> admin = (ArrayList<Org>) dataGrid.getValue();
            int active = 0;
            for(Org ad : admin)
            {
                if(ad.isStatus() == true)
                {
                    active++;
                }
            }
            System.out.println("Active Admin : "+active);
            if(active > 1)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("More Than One Admin Can't Be Activated...");
                 fc.addMessage("", message);
                 return;
            }
            Exception ex = new CollegeList().updateAdminStatus(admin);
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Status Updated...");
                fc.addMessage("", message);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Status Not Updated.....PleaseTry Again");
                fc.addMessage(""+ex , message);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateAdminEmail()
    {
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            
            ArrayList<Org> admin = (ArrayList<Org>) dataGrid2.getValue();
            int active = 0;
            for(Org ad : admin)
            {
                if(ad.isStatus() == true)
                {
                    active++;
                }
            }
            System.out.println("Active Admin : "+active);
            if(active > 1)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("More Than One Email ID  Can't Be Activated...");
                fc.addMessage("", message);
                return;
            }
            Exception ex = new CollegeList().updateAdminEmailStatus(admin);
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                 message.setSummary("Status Updated...");
                 fc.addMessage("", message);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("Status Not Activated.....PleaseTry Again");
                 fc.addMessage(""+ex , message);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public String admin()
    {
        try
        {
               FacesContext fc = FacesContext.getCurrentInstance();
               FacesMessage message = new FacesMessage();
            if(new CollegeList().featchDetails(this) == true)
            {
                return "Admin.jsf";
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Wrong User Id Or Password");
                fc.addMessage("", message);
                return "adminLogin.jsf";
            }
        }
        catch(Exception ex)
        {
            return "adminLogin.jsf";
        }
    }
    public void saveAdminEmailConfig()
    {
        try
        {
            
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            if (this.getEmail().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false) {

                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter EmailID In Correct Format ");
                fc.addMessage("", message);
                return;
            }
            if(this.getAdPassword().equals(this.getAdRePassword()) == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Password Are Not Matching...Please Try Again..");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }
            Exception ex = new CollegeList().saveEmailConfig(this);
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Administrator EmailId Configration Added Successfuly");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
     public void updateRow()
    {
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            Org o = new Org();
            o = (Org) dataGrid1.getRowData();
           Exception ex = new CollegeList().updateRow(o); 
               
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("College Updated Successfuly");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
     
    /**Method for update Password for the Institute Admin */
    
    public void updatePassword()
    {
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            if(!editedRecord.getAdPassword().equals(editedRecord.getAdRePassword()))
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Password Are Not Matching.....Please Try Again.");
                fc.addMessage("", message);
                return;
             
            }
            
            Exception ex = new CollegeList().updatePassword(editedRecord); 
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Password updated successfully Mail has been  sent successfuly to : "+editedRecord.getEmail());
               //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }
    }
     
     public void updateIpList()
     {
         try
         {
             FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            Org o = new Org();
            o = (Org) dataGrid5.getRowData();
             System.out.println(o.getIpAddress()+o.isIpStatus());
            Exception ex = new CollegeList().updateIpList(o);
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("IP Status Updated Successfuly");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
            }
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
     }


    Org editedRecord;

    public Org getEditedRecord() {
       return editedRecord;
    }

    public void setEditedRecord(Org editedRecord) {
        this.editedRecord = editedRecord;
    }

    private int currentRecordindex;

    public int getCurrentRecordindex() {
        return currentRecordindex;
    }

    public void setCurrentRecordindex(int currentRecordindex) {
        this.currentRecordindex = currentRecordindex;
    }
           
}
