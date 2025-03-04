package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FileMaster generated by hbm2java
 */
public class FileMaster  implements java.io.Serializable {


     private Integer fileId;
     private Erpmusers erpmusers;
     private Institutionmaster institutionmaster;
     private Departmentmaster departmentmaster;
     private Subinstitutionmaster subinstitutionmaster;
     private ErpmGenMaster erpmGenMaster;
     private Employeemaster employeemaster;
     private String fileNumber;
     private String fileSubject;
     private Integer filePages;
     private Date fileDispatchDate;
     private Boolean fileConfidential;
     private Set fileDetails = new HashSet(0);

    public FileMaster() {
    }

    public FileMaster(Erpmusers erpmusers, Institutionmaster institutionmaster, Departmentmaster departmentmaster, Subinstitutionmaster subinstitutionmaster, ErpmGenMaster erpmGenMaster, Employeemaster employeemaster, String fileNumber, String fileSubject, Integer filePages, Date fileDispatchDate, Boolean fileConfidential, Set fileDetails) {
       this.erpmusers = erpmusers;
       this.institutionmaster = institutionmaster;
       this.departmentmaster = departmentmaster;
       this.subinstitutionmaster = subinstitutionmaster;
       this.erpmGenMaster = erpmGenMaster;
       this.employeemaster = employeemaster;
       this.fileNumber = fileNumber;
       this.fileSubject = fileSubject;
       this.filePages = filePages;
       this.fileDispatchDate = fileDispatchDate;
       this.fileConfidential = fileConfidential;
       this.fileDetails = fileDetails;
    }
   
    public Integer getFileId() {
        return this.fileId;
    }
    
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public Erpmusers getErpmusers() {
        return this.erpmusers;
    }
    
    public void setErpmusers(Erpmusers erpmusers) {
        this.erpmusers = erpmusers;
    }
    public Institutionmaster getInstitutionmaster() {
        return this.institutionmaster;
    }
    
    public void setInstitutionmaster(Institutionmaster institutionmaster) {
        this.institutionmaster = institutionmaster;
    }
    public Departmentmaster getDepartmentmaster() {
        return this.departmentmaster;
    }
    
    public void setDepartmentmaster(Departmentmaster departmentmaster) {
        this.departmentmaster = departmentmaster;
    }
    public Subinstitutionmaster getSubinstitutionmaster() {
        return this.subinstitutionmaster;
    }
    
    public void setSubinstitutionmaster(Subinstitutionmaster subinstitutionmaster) {
        this.subinstitutionmaster = subinstitutionmaster;
    }
    public ErpmGenMaster getErpmGenMaster() {
        return this.erpmGenMaster;
    }
    
    public void setErpmGenMaster(ErpmGenMaster erpmGenMaster) {
        this.erpmGenMaster = erpmGenMaster;
    }
    public Employeemaster getEmployeemaster() {
        return this.employeemaster;
    }
    
    public void setEmployeemaster(Employeemaster employeemaster) {
        this.employeemaster = employeemaster;
    }
    public String getFileNumber() {
        return this.fileNumber;
    }
    
    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }
    public String getFileSubject() {
        return this.fileSubject;
    }
    
    public void setFileSubject(String fileSubject) {
        this.fileSubject = fileSubject;
    }
    public Integer getFilePages() {
        return this.filePages;
    }
    
    public void setFilePages(Integer filePages) {
        this.filePages = filePages;
    }
    public Date getFileDispatchDate() {
        return this.fileDispatchDate;
    }
    
    public void setFileDispatchDate(Date fileDispatchDate) {
        this.fileDispatchDate = fileDispatchDate;
    }
    public Boolean getFileConfidential() {
        return this.fileConfidential;
    }
    
    public void setFileConfidential(Boolean fileConfidential) {
        this.fileConfidential = fileConfidential;
    }
    public Set getFileDetails() {
        return this.fileDetails;
    }
    
    public void setFileDetails(Set fileDetails) {
        this.fileDetails = fileDetails;
    }




}


