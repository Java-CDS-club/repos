/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.setup.Designation;
import org.smvdu.payroll.beans.db.DesignationDB;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class DesignationController {

    public DesignationController()
    {
        designations = new DesignationDB().loadDesignations();
    }
    private ArrayList<Designation> designations;
    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<Designation> getDesignations() {
        return designations;
    }

  // This method is used to UPDATE the values of the department setup  

    public void update()
    {
        ArrayList<Designation> data = (ArrayList<Designation>) dataGrid.getValue();
        for(Designation d: data)
        {
            System.out.println(d.getName());
            
                    FacesContext fc = FacesContext.getCurrentInstance();
                if (d.getDcode().matches("[a-zA-Z0-9]*") == false) {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Plz Enter Valid Designation Code.No speacial characters allowed.");
                    fc.addMessage("", message);
                    return;
                }
                if (d.getName().matches("^[a-zA-Z\\s]*$") == false) {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Plz Enter Valid Designation Name.No speacial characters allowed.");
                    fc.addMessage("", message);
                    return;
                }
                if (d.getNickName().matches("^[a-zA-Z\\s]*$") == false) {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Plz Enter Valid Nick Name No speacial characters allowed");
                    fc.addMessage("", message);
                    return;
                }
        }
      Exception e = new DesignationDB().update(data);
      if (e == null) {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", ""));
      }
      else {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Designation Already Exist", ""));   
      }
      
   }
    public void setDesignations(ArrayList<Designation> salaryData) {
        this.designations = salaryData;
    }
    

}
