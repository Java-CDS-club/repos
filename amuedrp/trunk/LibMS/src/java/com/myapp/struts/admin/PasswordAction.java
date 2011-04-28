/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.utility.PasswordEncruptionUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class PasswordAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         EmailDAO emailDAO = new EmailDAO();
        String searchText = request.getParameter("getEmail_Id");
        String searchText1=request.getParameter("getPassword");
        System.out.println(searchText+""+searchText1);
        String password1 = PasswordEncruptionUtility.password_encrupt(searchText1);
        String emails = emailDAO.getPassword(searchText,password1);
        //if(emails.isEmpty()) emails = emailDAO.getPassword(searchText,searchText1);
        if (!emails.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(emails);

        }
        return null;
    }
}
