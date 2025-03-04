/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirDAO.CirculationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpSession;
import com.myapp.struts.utility.DateCalculation;
import java.util.Locale;
import java.util.ResourceBundle;


public class CirculationEnquiryTitle extends org.apache.struts.action.Action {
    
   
    private static final String SUCCESS = "success";
    String memid;
    String library_id;
    String sublibrary_id;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
CirculationDAO cirdao=new CirculationDAO();
        CirculationCheckoutActionForm ccaf=(CirculationCheckoutActionForm)form;
        memid=ccaf.getMemid();
        HttpSession session=request.getSession();
       
         try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");

        CirMemberDetail  cirmemberdetail=cirdao.getMemid(library_id,memid);
        
        if(cirmemberdetail!=null)
        {
          CirMemberAccount cma=cirdao.getAccount(library_id,sublibrary_id, memid);
          if(cma!=null)
          {

              if(cma.getStatus().equals("Active")){

          String date_reg=cma.getReqDate();
          String date_ex=cma.getExpiryDate();
          String system_date=DateCalculation.now();
          long days1=DateCalculation.getDifference(date_ex, system_date);
          if(days1<=0){

             //request.setAttribute("msg1", "Member's account is expired");
              request.setAttribute("msg1", resource.getString("circulation.circhkout.memaccisexpired"));
               session.removeAttribute("cma");
          session.removeAttribute("cirmemberdetail");
          session.setAttribute("cma",cma);
          session.setAttribute("cirmemberdetail",cirmemberdetail);
          session.setAttribute("memid",memid);
          return mapping.findForward("failure");
          }
          long days=DateCalculation.getDifference( date_ex,system_date);
          System.out.println("GHHHHHHHHHHHHHHHHHHH"+days);
          if(days<30){

           //   String msg="Reminder:After "+days+" days the member's account will be expired.";
           String msg=resource.getString("circulation.circhkout.remainder")+days+resource.getString("circulation.circhkout.accwillexpire");
          request.setAttribute("msg2", msg);
          }
          session.removeAttribute("cma");
          session.removeAttribute("cirmemberdetail");
          session.setAttribute("cma",cma);
          session.setAttribute("cirmemberdetail",cirmemberdetail);
          session.setAttribute("memid",memid);
          return mapping.findForward("success");
              }
              else{

               // request.setAttribute("msg1", "Member Account is not Active");
              request.setAttribute("msg1", resource.getString("circulation.circhkout.memaccnotactive"));
             return mapping.findForward("failure");


              }


          }else{

           //  request.setAttribute("msg1", "Member account does not exists");
           request.setAttribute("msg1", resource.getString("circulation.circhkout.memaccdoesnotexit"));
          return mapping.findForward("failure");
          }
        }
        else
        {

           //request.setAttribute("msg1", "Member account does not exists");
          request.setAttribute("msg1", resource.getString("circulation.circhkout.memaccdoesnotexit"));
          return mapping.findForward("failure");
        }
    }
}
