package com.myapp.struts.hbm;
// Generated Oct 20, 2012 11:20:56 AM by Hibernate Tools 3.2.1.GA



/**
 * SerBiolioDetailsId generated by hbm2java
 */
public class SerBiolioDetailsId  implements java.io.Serializable {


     private int serControlNo;
     private String sublibraryId;
     private String libraryId;

    public SerBiolioDetailsId() {
    }

    public SerBiolioDetailsId(int serControlNo, String sublibraryId, String libraryId) {
       this.serControlNo = serControlNo;
       this.sublibraryId = sublibraryId;
       this.libraryId = libraryId;
    }
   
    public int getSerControlNo() {
        return this.serControlNo;
    }
    
    public void setSerControlNo(int serControlNo) {
        this.serControlNo = serControlNo;
    }
    public String getSublibraryId() {
        return this.sublibraryId;
    }
    
    public void setSublibraryId(String sublibraryId) {
        this.sublibraryId = sublibraryId;
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
		 if ( !(other instanceof SerBiolioDetailsId) ) return false;
		 SerBiolioDetailsId castOther = ( SerBiolioDetailsId ) other;

		 return ( (String.valueOf(this.getSerControlNo())==String.valueOf(castOther.getSerControlNo())) || ( String.valueOf(this.getSerControlNo())!=null &&String.valueOf( castOther.getSerControlNo())!=null && String.valueOf(this.getSerControlNo()).equals(castOther.getSerControlNo()) ) )
 && ( (this.getSublibraryId()==castOther.getSublibraryId()) || ( this.getSublibraryId()!=null && castOther.getSublibraryId()!=null && this.getSublibraryId().equals(castOther.getSublibraryId()) ) )
 && ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) );
   }

   public int hashCode() {
         int result = 17;

         result = 37 * result + ( String.valueOf(getSerControlNo()) == null ? 0 : String.valueOf(this.getSerControlNo()).hashCode() );
         result = 37 * result + ( getSublibraryId() == null ? 0 : this.getSublibraryId().hashCode() );
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         return result;
   }


}

