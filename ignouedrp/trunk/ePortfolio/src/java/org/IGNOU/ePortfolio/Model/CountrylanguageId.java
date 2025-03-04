package org.IGNOU.ePortfolio.Model;
// Generated Sep 17, 2012 3:57:43 PM by Hibernate Tools 3.2.1.GA



/**
 * CountrylanguageId generated by hbm2java
 */
public class CountrylanguageId  implements java.io.Serializable {


     private String countryCode;
     private String language;

    public CountrylanguageId() {
    }

    public CountrylanguageId(String countryCode, String language) {
       this.countryCode = countryCode;
       this.language = language;
    }
   
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }


    @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) {
           return true;
       }
		 if ( (other == null ) ) {
           return false;
       }
		 if ( !(other instanceof CountrylanguageId) ) {
           return false;
       }
		 CountrylanguageId castOther = ( CountrylanguageId ) other; 
         
		 return ( (this.getCountryCode().equals(castOther.getCountryCode())) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) )
 && ( (this.getLanguage().equals(castOther.getLanguage())) || ( this.getLanguage()!=null && castOther.getLanguage()!=null && this.getLanguage().equals(castOther.getLanguage()) ) );
   }
   
    @Override
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         result = 37 * result + ( getLanguage() == null ? 0 : this.getLanguage().hashCode() );
         return result;
   }   


}


