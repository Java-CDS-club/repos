/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import com.myapp.struts.systemsetupDAO.*;

public class OpacLibResultSetAction extends org.apache.struts.action.Action
{
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    MemberCategoryDAO memcatdao=new MemberCategoryDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.gc();
        HttpSession session = request.getSession();
        session.removeAttribute("libRs");
       
        List rs = null;
        String formname="";
        String lib_id = (String)session.getAttribute("library_id");


        try{



        formname = request.getParameter("name");

        OpacSearchDAO opacDao = new OpacSearchDAO();
        rs =(List)opacDao.LibrarySearch(lib_id);

        session.setAttribute("libRs", rs);
       List rs1 = (List)opacDao.subLibrarySearch(lib_id);
        session.setAttribute("sublib", rs1);
        session.removeAttribute("p");
        String head=(String)request.getParameter("p");
        if(head!=null)
            session.setAttribute("p", "p");






        if (formname.equals("simple"))
            return mapping.findForward("simple");
        if (formname.equals("browse"))
            return mapping.findForward("browse");
        if (formname.equals("additional"))
            return mapping.findForward("additional");
        if (formname.equals("advance"))
            return mapping.findForward("advance");
        if (formname.equals("isbn"))
            return mapping.findForward("isbn");
        if (formname.equals("callno"))
            return mapping.findForward("callno");
        if (formname.equals("accno"))
            return mapping.findForward("accno");
        if (formname.equals("journal"))
            return mapping.findForward("journal");
      if (formname.equals("newarrival"))
        {
             List lib=opacDao.LibrarySearch(lib_id);
           // if(!lib.isEmpty())
            //{
                 session.setAttribute("lib",lib);


            // }
            return mapping.findForward("newarrival");
        }

          if (formname.equals("myaccount")){
             String type = (String)request.getParameter("type");
             if(type!=null)session.setAttribute("type", type);
            return mapping.findForward("myaccount");}
        if(formname.equals("changepassword"))
            return mapping.findForward("changepassword");

        if (formname.equals("feedback"))
         {
            List lib=opacDao.LibrarySearch(lib_id);
           // if(!lib.isEmpty())
            // {
                 session.setAttribute("lib",lib);


            // }
            return mapping.findForward("feedback");
         }
         if(formname.equals("newmember"))
        {
            List list1;

                list1=(List)memcatdao.searchEmpType(lib_id);
            //    list2=(List)MemberCategoryDAO.searchSubEmpType(lib_id);
               // list3=(List)FacultyDAO.searchFaculty(lib_id);
             //   list4=(List)DeptDAO.getDeptRecord(lib_id);
              //  list5=(List)CourseDAO.getCourse(lib_id);

               

                 //if((!list1.isEmpty())&& !(list2.isEmpty()))
                    {
                     session.setAttribute("list1",list1);
                  //   session.setAttribute("list2",list2);
                    }

                   // if((!list3.isEmpty())&& !(list4.isEmpty()) && !list5.isEmpty())
                    {
                    //    session.setAttribute("list3",list3);
                    //    session.setAttribute("list4",list4);
                    //    session.setAttribute("list5",list5);
                    }

System.out.println(list1.size());

            return mapping.findForward("newmember");

        }

        
        if(formname.equals("location"))
        {
            List lib=opacDao.LibrarySearch(lib_id);
            session.setAttribute("lib",lib);
            return mapping.findForward("location");}
        if(formname.equals("notice"))
            return mapping.findForward("notice");

if(formname.equals("reservationrequest"))
            return mapping.findForward("reservation");

if(formname.equals("newdemand"))
            return mapping.findForward("demandlist");


return mapping.findForward(SUCCESS);
}catch(Exception e){
e.printStackTrace();
    request.setAttribute("msg","Database Connectivity Problem,Contact WebAdmin");
                return mapping.findForward("failure");

}
    }
}
