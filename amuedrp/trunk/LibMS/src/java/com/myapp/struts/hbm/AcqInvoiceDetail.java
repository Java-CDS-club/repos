package com.myapp.struts.hbm;
// Generated May 24, 2012 5:54:59 PM by Hibernate Tools 3.2.1.GA



/**
 * AcqInvoiceDetail generated by hbm2java
 */
public class AcqInvoiceDetail  implements java.io.Serializable {


     private AcqInvoiceDetailId id;
     private Library library;
     private String orderNo;
     private String vendorId;
     private String orderDate;
//     private String invoiceDate;
//     private String overallDiscount;
//     private String totalNetAmount;
//     private String miscCharges;
//     private String grandTotal;
     private String totalAmount;
     private String discount;
     private String netTotal;
     private String status;
    // private int recievingItemId;
    public AcqInvoiceDetail() {
    }

	
    public AcqInvoiceDetail(AcqInvoiceDetailId id, Library library) {
        this.id = id;
        this.library = library;
    }
    public AcqInvoiceDetail(AcqInvoiceDetailId id, Library library, String orderNo, String vendorId, String orderDate, String totalAmount, String discount, String netTotal, String status) {
       this.id = id;
       this.library = library;
       this.orderNo = orderNo;
       this.vendorId = vendorId;
       this.orderDate = orderDate;
//       this.invoiceDate = invoiceDate;
//       this.overallDiscount = overallDiscount;
//       this.totalNetAmount = totalNetAmount;
//       this.miscCharges = miscCharges;
//       this.grandTotal = grandTotal;
       this.totalAmount=totalAmount;
       this.discount=discount;
       this.netTotal=netTotal;
       this.status=status;
    //   this.recievingItemId=recievingItemId;

    }
   
    public AcqInvoiceDetailId getId() {
        return this.id;
    }
    
    public void setId(AcqInvoiceDetailId id) {
        this.id = id;
    }
    public Library getLibrary() {
        return this.library;
    }
    
    public void setLibrary(Library library) {
        this.library = library;
    }
    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getVendorId() {
        return this.vendorId;
    }
    
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
    public String getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
   
    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(String netTotal) {
        this.netTotal = netTotal;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public int getRecievingItemId() {
//        return recievingItemId;
//    }
//
//    public void setRecievingItemId(int recievingItemId) {
//        this.recievingItemId = recievingItemId;
//    }

    
    
//    public String getOverallDiscount() {
//        return this.overallDiscount;
//    }
//
//    public void setOverallDiscount(String overallDiscount) {
//        this.overallDiscount = overallDiscount;
//    }
//    public String getTotalNetAmount() {
//        return this.totalNetAmount;
//    }
//
//    public void setTotalNetAmount(String totalNetAmount) {
//        this.totalNetAmount = totalNetAmount;
//    }
//    public String getMiscCharges() {
//        return this.miscCharges;
//    }
//
//    public void setMiscCharges(String miscCharges) {
//        this.miscCharges = miscCharges;
//    }
//    public String getGrandTotal() {
//        return this.grandTotal;
//    }
//
//    public void setGrandTotal(String grandTotal) {
//        this.grandTotal = grandTotal;
//    }




}


