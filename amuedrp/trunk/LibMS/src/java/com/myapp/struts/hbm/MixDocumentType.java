/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

/**
 *
 * @author System Administrator
 */
public class MixDocumentType {
    private CirCheckout cirCheckout;
    private DocumentDetails documentDetails;

    public CirCheckout getCirCheckout() {
        return cirCheckout;
    }

    public void setCirCheckout(CirCheckout cirCheckout) {
        this.cirCheckout = cirCheckout;
    }


   
    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }

    
}
