package pojo.hibernate;
// Generated Jan 19, 2011 11:12:21 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Institutiontype generated by hbm2java
 */
public class InstitutiontypeDelete  implements java.io.Serializable {


     private Byte itTypeId;
     private String itName;
     private Set institutionmasters = new HashSet(0);

    public InstitutiontypeDelete() {
    }

	
    public InstitutiontypeDelete(String itName) {
        this.itName = itName;
    }
    public InstitutiontypeDelete(String itName, Set institutionmasters) {
       this.itName = itName;
       this.institutionmasters = institutionmasters;
    }
   
    public Byte getItTypeId() {
        return this.itTypeId;
    }
    
    public void setItTypeId(Byte itTypeId) {
        this.itTypeId = itTypeId;
    }
    public String getItName() {
        return this.itName;
    }
    
    public void setItName(String itName) {
        this.itName = itName;
    }
    public Set getInstitutionmasters() {
        return this.institutionmasters;
    }
    
    public void setInstitutionmasters(Set institutionmasters) {
        this.institutionmasters = institutionmasters;
    }




}


