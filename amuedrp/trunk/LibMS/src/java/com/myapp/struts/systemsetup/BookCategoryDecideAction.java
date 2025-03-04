/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.*;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.BookCategoryDAO;
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author edrp01
 */
public class BookCategoryDecideAction extends org.apache.struts.action.Action {
    
    
    String library_id,button,book_type,emptype_id,subemptype_id,emptype_fullname,subemptype_name;
    private String sublibrary_id;
    Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
BookCategoryDAO bookdao=new BookCategoryDAO();
DocumentCategoryDAO docdao=new DocumentCategoryDAO();
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
     BookCategoryDecideActionForm bcda  =(BookCategoryDecideActionForm)form;
    
        button=bcda.getButton();
        book_type=bcda.getBook_type();
        emptype_id=bcda.getEmptype_id();
        subemptype_id=bcda.getSub_emptype_id();


        library_id=(String)session.getAttribute("library_id");
sublibrary_id=(String)session.getAttribute("sublibrary_id");


/*get the id of member and sub member type*/

EmployeeType memtype=bookdao.searchMemTypeId(library_id,emptype_id);
SubEmployeeType submemtype=bookdao.searchSubMemTypeId(library_id,emptype_id,subemptype_id);

if(memtype!=null)
{ emptype_id=memtype.getId().getEmptypeId();
emptype_fullname=memtype.getEmptypeFullName();
}

if(submemtype!=null)
{ subemptype_id=submemtype.getId().getSubEmptypeId();
subemptype_name=submemtype.getSubEmptypeFullName();
}
DocumentCategory doc=(DocumentCategory)docdao.searchDocumentCategory(library_id, sublibrary_id, book_type);
if(doc!=null)
  request.setAttribute("book_typefullname",doc.getDocumentCategoryName());

request.setAttribute("emptype_name",emptype_fullname);
 request.setAttribute("subemptype_name",subemptype_name);


System.out.println(emptype_fullname+"   "+subemptype_name+ "  "+doc.getDocumentCategoryName()+"  "+emptype_id+"  "+subemptype_id);


        if(button.equalsIgnoreCase("Add"))
        {

           BookCategory book=(BookCategory)bookdao.searchBookTypeDetails(library_id, emptype_id, subemptype_id, book_type);
           System.out.println(book);
           if(book==null)
           {
           request.setAttribute("book_type",book_type);
            
           request.setAttribute("emptype_id",bcda.getEmptype_id());
           
           request.setAttribute("subemptype_id",bcda.getSub_emptype_id());
          
           return mapping.findForward("success");
           }
           else{

               //request.setAttribute("msg1",book_type + "Already Exists");
               request.setAttribute("msg1",book_type + resource.getString("systemsetup.manage_notice.alreadyexists"));
           return mapping.findForward("failure");

           }
           
        }
        if(button.equalsIgnoreCase("Update") || button.equalsIgnoreCase("View") || button.equalsIgnoreCase("Delete"))
        {
//BookCategoryDecideActionForm1 obj=new BookCategoryDecideActionForm1();
           BookCategory book=(BookCategory)bookdao.searchBookTypeDetails(library_id, emptype_id, subemptype_id, book_type);
           System.out.println(book);
           if(book!=null)
           {
                request.setAttribute("book_type",book_type);

           request.setAttribute("emptype_id",emptype_id);
System.out.println(emptype_id+"In Update");
           request.setAttribute("subemptype_id",subemptype_id);
            request.setAttribute("limit",book.getIssueDaysLimit().toString());
            request.setAttribute("fine", book.getFine().toString());
           
          // bcda.setBook_type(book.getId().getBookType());
         //  bcda.setEmptype_id(book.getId().getEmptypeId());
          // bcda.setSub_emptype_id(book.getId().getSubEmptypeId());
          //obj.setPermitday();

          // System.out.println(book.getIssueDaysLimit().toString());
          // bcda.setFull_name(book.getDetail());
         //  String fineamt=
          // System.out.println(fineamt);
          // obj.setFineRs(fineamt.substring(0,fineamt.indexOf(".")));
          // obj.setFinePs(fineamt.substring(fineamt.indexOf(".")+1,fineamt.length()));

           request.setAttribute("button",button);
           return mapping.findForward("update/view/delete");
           }
           else{

               // request.setAttribute("msg1",book_type + "not Found");
               request.setAttribute("msg1",book_type + resource.getString("systemsetup.bookcategorydecideaction.notfond"));
           return mapping.findForward("failure");

           }

        }
     return null;
    }
}
