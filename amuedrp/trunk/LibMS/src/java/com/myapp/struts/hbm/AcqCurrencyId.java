package com.myapp.struts.hbm;
// Generated May 18, 2011 8:57:32 PM by Hibernate Tools 3.2.1.GA



/**
 * AcqCurrencyId generated by hbm2java
 */
public class AcqCurrencyId  implements java.io.Serializable {


     private int conversionId;
     private String libraryId;

    public AcqCurrencyId() {
    }

    public AcqCurrencyId(int conversionId, String libraryId) {
       this.conversionId = conversionId;
       this.libraryId = libraryId;
    }
   
    public int getConversionId() {
        return this.conversionId;
    }
    
    public void setConversionId(int conversionId) {
        this.conversionId = conversionId;
    }
    public String getLibraryId() {
        return this.libraryId;
    }
    
    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AcqCurrencyId) ) return false;
		 AcqCurrencyId castOther = ( AcqCurrencyId ) other; 
         
		 return (this.getConversionId()==castOther.getConversionId())
 && ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getConversionId();
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         return result;
   }   


}


