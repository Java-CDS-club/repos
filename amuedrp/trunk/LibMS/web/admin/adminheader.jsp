<%-- 
    Document   : header
    Created on : Nov 13, 2010, 4:45:02 PM
    Author     : System Administrator
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="java.util.*,java.io.*,java.net.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<%
try{
if(session.getAttribute("library_id")!=null){
System.out.println("library_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }

String user=(String)session.getAttribute("username");

 session.setAttribute("pass","t");

%>

<table width="100%"  border="0px" style="margin:0px 0px 0px 0px" dir="<%=rtl%>">

    <tr dir="<%=rtl%>">
          <td align="left" width="350px" valign="bottom" dir="<%=rtl%>">
                       <%-- <span style="color:Blue;font-size: 30px;font-family: arial;font-weight: bold">Lib</span><span style="color:pink;font-size: 30px;font-family: arial;font-weight: bold">MS</span>--%>
                       <img src="<%=request.getContextPath()%>/images/bp.PNG" alt="" height="65px" width="160px" border="0" align="top" id="Image1" style="">
                     </td>
        <td valign="bottom" align="right">





        </td>
        
                     <td valign="bottom"  dir="<%=rtl%>" align="right" width="350px">
                         <span  dir="<%=rtl%>" style="font:8pt Verdana;"><%=resource.getString("login.hello")%> [<%=user%>]&nbsp;|<a href="<%=request.getContextPath()%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></span>
                        <br>
              
                         <img src="<%=request.getContextPath()%>/images/logo.png" alt="" height="65px" width="160px" border="0" align="top" id="Image1" style=""></td>


                  
                </tr>




                </table>

