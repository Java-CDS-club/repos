<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%
try{
if(session.getAttribute("library_id")!=null){
System.out.println("library_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>

<%
String id=request.getParameter("id");
session.setAttribute("reg", id);
String institute_id=(String)session.getAttribute("library_id");
if(institute_id!=null){

%>
<jsp:forward page="./../view6.do">
    <jsp:param name="id" value="<%=id%>"/>
</jsp:forward>

<%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>