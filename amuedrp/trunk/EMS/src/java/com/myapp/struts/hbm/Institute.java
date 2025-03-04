package com.myapp.struts.hbm;
// Generated Mar 10, 2011 10:00:00 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Institute generated by hbm2java
 */
public class Institute  implements java.io.Serializable {


     private String instituteId;
     private Integer registrationId;
     private String instituteName;
     private String workingStatus;
     private Set staffDetails = new HashSet(0);

    public Institute() {
    }

	
    public Institute(String instituteId, String workingStatus) {
        this.instituteId = instituteId;
        this.workingStatus = workingStatus;
    }
    public Institute(String instituteId, Integer registrationId, String instituteName, String workingStatus, Set staffDetails) {
       this.instituteId = instituteId;
       this.registrationId = registrationId;
       this.instituteName = instituteName;
       this.workingStatus = workingStatus;
       this.staffDetails = staffDetails;
    }
   
    public String getInstituteId() {
        return this.instituteId;
    }
    
    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }
    public Integer getRegistrationId() {
        return this.registrationId;
    }
    
    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }
    public String getInstituteName() {
        return this.instituteName;
    }
    
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
    public String getWorkingStatus() {
        return this.workingStatus;
    }
    
    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }
    public Set getStaffDetails() {
        return this.staffDetails;
    }
    
    public void setStaffDetails(Set staffDetails) {
        this.staffDetails = staffDetails;
    }




}


