/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class DepartmentActionForm extends org.apache.struts.action.ActionForm {
    
    private String dept_id;
    private String button;
    private String faculty_name;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    /**
     * @return
     */
   
    /**
     *
     */
    public DepartmentActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }

    /**
     * @return the faculty_name
     */
    public String getFaculty_name() {
        return faculty_name;
    }

    /**
     * @param faculty_name the faculty_name to set
     */
    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }
}
