package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * GfrMaster generated by hbm2java
 */
public class GfrMaster  implements java.io.Serializable {

     private Institutionmaster institutionmaster;
     private char gfrorInstituteRule;
     private Integer gfrGfrId;
     private String gfrSection;
     private int gfrChapterNo;
     private String gfrChapterName;
     private String gfrRuleNo;
     private String gfrDescription;
     private Set gfrProgramMappings = new HashSet(0);

    public GfrMaster() {
    }

	
    public GfrMaster(String gfrSection, int gfrChapterNo, String gfrChapterName, String gfrRuleNo, String gfrDescription, char gfrorInstituteRule,Institutionmaster institutionmaster) {
        this.gfrSection = gfrSection;
        this.gfrChapterNo = gfrChapterNo;
        this.gfrChapterName = gfrChapterName;
        this.gfrRuleNo = gfrRuleNo;
        this.gfrDescription = gfrDescription;
        this.gfrorInstituteRule = gfrorInstituteRule;
        this.institutionmaster = institutionmaster;
    }
    public GfrMaster(String gfrSection, int gfrChapterNo, String gfrChapterName, String gfrRuleNo, String gfrDescription, char gfrorInstituteRule, Institutionmaster institutionmaster, Set gfrProgramMappings) {
       this.gfrSection = gfrSection;
       this.gfrChapterNo = gfrChapterNo;
       this.gfrChapterName = gfrChapterName;
       this.gfrRuleNo = gfrRuleNo;
       this.gfrDescription = gfrDescription;
       this.gfrProgramMappings = gfrProgramMappings;
       this.gfrorInstituteRule = gfrorInstituteRule;
       this.institutionmaster = institutionmaster;
    }
   

      public void setInstitutionmaster(Institutionmaster institutionmaster) {
        this.institutionmaster = institutionmaster;
    }

    public void setGfrorInstituteRule(char gfrorInstituteRule) {
        this.gfrorInstituteRule = gfrorInstituteRule;
    }

    public Institutionmaster getInstitutionmaster() {
        return institutionmaster;
    }

    public char getGfrorInstituteRule() {
        return gfrorInstituteRule;
    }
    public Integer getGfrGfrId() {
        return this.gfrGfrId;
    }
    
    public void setGfrGfrId(Integer gfrGfrId) {
        this.gfrGfrId = gfrGfrId;
    }
    public String getGfrSection() {
        return this.gfrSection;
    }
    
    public void setGfrSection(String gfrSection) {
        this.gfrSection = gfrSection;
    }
    public int getGfrChapterNo() {
        return this.gfrChapterNo;
    }
    
    public void setGfrChapterNo(int gfrChapterNo) {
        this.gfrChapterNo = gfrChapterNo;
    }
    public String getGfrChapterName() {
        return this.gfrChapterName;
    }
    
    public void setGfrChapterName(String gfrChapterName) {
        this.gfrChapterName = gfrChapterName;
    }
    public String getGfrRuleNo() {
        return this.gfrRuleNo;
    }
    
    public void setGfrRuleNo(String gfrRuleNo) {
        this.gfrRuleNo = gfrRuleNo;
    }
    public String getGfrDescription() {
        return this.gfrDescription;
    }
    
    public void setGfrDescription(String gfrDescription) {
        this.gfrDescription = gfrDescription;
    }
    public Set getGfrProgramMappings() {
        return this.gfrProgramMappings;
    }
    
    public void setGfrProgramMappings(Set gfrProgramMappings) {
        this.gfrProgramMappings = gfrProgramMappings;
    }

}


