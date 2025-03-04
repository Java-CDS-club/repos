/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;


import com.myapp.struts.AcquisitionDao.AcqOrderDao;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqApproval;
import com.myapp.struts.hbm.AcqBibliographyDetails;
import com.myapp.struts.hbm.AcqOrder1;
import com.myapp.struts.hbm.AcqOrderHeader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author EdRP-05
 */
public class AcqOrderAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao acqdao=new AcquisitionDao();
    AcqOrderDao acqorderdao=new AcqOrderDao();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        AcqOrderManagementActionForm aomd=new AcqOrderManagementActionForm();

           String vendor=aomd.getVendor();
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String t=request.getParameter("orderno");
        if(t!=null)
        {
            AcqOrderHeader acq= acqdao.searchOrderHeaderByOrderNo(library_id, sub_library_id, t);

           if(acq!=null)
           {



             List<AcqOrder1> acq1=acqdao.searchOrderByOrderNo(library_id, sub_library_id, t);
             System.out.println("The List Size in Order no"+acq1.size());
            if(!acq1.isEmpty()){

            for(int i=0;i<acq1.size();i++)
            {
                    int con=acq1.get(i).getControlNo();
                     System.out.println("The List control no in Order no"+con +"....");

                    AcqApproval app=acqdao.searchApprovalStatus1(library_id, sub_library_id, con,acq1.get(i).getApprovalItemId());
                    System.out.println("Control No....List"+app);
                    if(app!=null)
                    {
                           System.out.println("The control no is of Approval type"+con +"....");
                    //For On Approval or Approved Deletion
                        AcqBibliographyDetails st=acqdao.searchAcqBibByControlNo(library_id, sub_library_id, con);
                        if(st!=null)
                        {
                            if(st.getNoOfCopies()==0)
                            {    st.setAcqMode("Approved");
                                    st.setStatus("Fully Approved");
                              }
                              else{
                                    st.setAcqMode("On Approval");
                                    st.setStatus("Partially Approved");

                                    }

                            }
              boolean   result=acqdao.updateAcqBib(st);
              app.setStatus("pending");
              boolean result1=acqdao.updateApproval(app);


       }
       else{
       //for Firm Order Deletion
                          System.out.println("The control is of Firm Order "+con +"....");
              AcqBibliographyDetails st=acqdao.searchAcqBibByControlNo(library_id, sub_library_id, con);
           if(st!=null){
            st.setAcqMode("Firm Order");
            st.setStatus("");
           }
            boolean   result=acqdao.updateAcqBib(st);

       }

       }



       }
           }
 boolean result=   acqorderdao.delete1(library_id,sub_library_id, t);


        }


        List<String> acqvendor=acqdao.searchDoc6(library_id, sub_library_id);
        
ArrayList list1=new ArrayList();
for(int i=0;i<acqvendor.size();i++){
String v=acqvendor.get(i);
AcqBibliographyDetails acqv=new AcqBibliographyDetails();
acqv.setVendor(v);
list1.add(acqv);
}
         session.setAttribute("vendors", list1);
         
        return mapping.findForward("order");
    }
}
