package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Erpmmodule generated by hbm2java
 */
public class Erpmmodule  implements java.io.Serializable {


     private Byte erpmmId;
     private Erpmteam erpmteam;
     private String erpmmShortName;
     private String erpmmName;
     private Set genericroleprivilegeses = new HashSet(0);
     private Set erpmsubmodules = new HashSet(0);
     private Set institutionroleprivilegeses = new HashSet(0);

    public Erpmmodule() {
    }

	
    public Erpmmodule(Erpmteam erpmteam, String erpmmShortName, String erpmmName) {
        this.erpmteam = erpmteam;
        this.erpmmShortName = erpmmShortName;
        this.erpmmName = erpmmName;
    }
    public Erpmmodule(Erpmteam erpmteam, String erpmmShortName, String erpmmName, Set genericroleprivilegeses, Set erpmsubmodules, Set institutionroleprivilegeses) {
       this.erpmteam = erpmteam;
       this.erpmmShortName = erpmmShortName;
       this.erpmmName = erpmmName;
       this.genericroleprivilegeses = genericroleprivilegeses;
       this.erpmsubmodules = erpmsubmodules;
       this.institutionroleprivilegeses = institutionroleprivilegeses;
    }
   
    public Byte getErpmmId() {
        return this.erpmmId;
    }
    
    public void setErpmmId(Byte erpmmId) {
        this.erpmmId = erpmmId;
    }
    public Erpmteam getErpmteam() {
        return this.erpmteam;
    }
    
    public void setErpmteam(Erpmteam erpmteam) {
        this.erpmteam = erpmteam;
    }
    public String getErpmmShortName() {
        return this.erpmmShortName;
    }
    
    public void setErpmmShortName(String erpmmShortName) {
        this.erpmmShortName = erpmmShortName;
    }
    public String getErpmmName() {
        return this.erpmmName;
    }
    
    public void setErpmmName(String erpmmName) {
        this.erpmmName = erpmmName;
    }
    public Set getGenericroleprivilegeses() {
        return this.genericroleprivilegeses;
    }
    
    public void setGenericroleprivilegeses(Set genericroleprivilegeses) {
        this.genericroleprivilegeses = genericroleprivilegeses;
    }
    public Set getErpmsubmodules() {
        return this.erpmsubmodules;
    }
    
    public void setErpmsubmodules(Set erpmsubmodules) {
        this.erpmsubmodules = erpmsubmodules;
    }
    public Set getInstitutionroleprivilegeses() {
        return this.institutionroleprivilegeses;
    }
    
    public void setInstitutionroleprivilegeses(Set institutionroleprivilegeses) {
        this.institutionroleprivilegeses = institutionroleprivilegeses;
    }




}


