
    <%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*"%>
    <jsp:include page="/admin/header.jsp" flush="true" />
    <%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.admin.Traker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>LibMS : Manage Staff Details</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" >
function b1click()
{
location.href="<%=request.getContextPath()%>/admin/main.jsp";
}
function b2click()
{
f.action="<%=request.getContextPath()%>/admin/main.jsp";
f.method="post";
f.target="_self";
f.submit();
}

</script>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 
</head>

<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

<body>
 <table border="1" class="table" width="700px" align="center">



       <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("admin.viewstaff.heading")%></td></tr>
                <tr><td valign="top" align="center"> <br/>
               



<%!
   
   ArrayList staffList;
   StaffDoc obj;
   List<staffsubLib> staffList1;
   int fromIndex=0, toIndex;
%>
 <%

      

   staffList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 


 staffList1=(List<staffsubLib>)session.getAttribute("viewallstaff");
 Iterator it = staffList1.iterator();
 while(it.hasNext())
     {
        obj = new StaffDoc();

        obj.setStaff_id(staffList1.get(tcount).getStaffDetail().getId().getStaffId());
        obj.setfirst_name(staffList1.get(tcount).getStaffDetail().getFirstName());
        obj.setlast_name(staffList1.get(tcount).getStaffDetail().getLastName());
      obj.setSublibName(staffList1.get(tcount).getSubLibrary().getSublibName());
     staffList.add(obj);
     it.next();
     tcount++;
     }
System.out.println("staffList="+staffList.size());
tcount = staffList.size();
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= staffList.size())
   toIndex = staffList.size();
   request.setAttribute ("staffList", staffList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);

String staffId=resource.getString("staffid");
pageContext.setAttribute("staffId", staffId);
String fname=resource.getString("opac.newmemberentry.firstname");
pageContext.setAttribute("fname",fname);
String lastname=resource.getString("opac.newmemberentry.lastname");
pageContext.setAttribute("lname",lastname);
String library=resource.getString("opac.simplesearch.library");
pageContext.setAttribute("library",library);



%>
       
<%
 
%>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>


<ui:dataGrid items="${staffList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="50">
      <header value="" hAlign="left" styleClass="admingridheader"/>
    </column>

    <column width="100">
      <header value="${staffId}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.staff_id}" hyperLink="./showstaff.do?id=${doc.staff_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="150">
      <header value="${fname}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.first_name}" hAlign="left" hyperLink="./showstaff.do?id=${doc.staff_id}"  styleClass="item"/>
    </column>

    <column width="150">
      <header value="${lname}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.last_name}" hyperLink="./showstaff.do?id=${doc.staff_id}"  hAlign="left" styleClass="item"/>
    </column>
   
   <column width="200">
      <header value="${library}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.sublibName}" hyperLink="./showstaff.do?id=${doc.staff_id}"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="700" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("opac.accountdetails.previous")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>"><%=resource.getString("opac.accountdetails.next")%></a>
</c:if>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<c:forEach items="${pages}" var="page">
<c:choose>
  <c:when test="${page.current}">
    <b><a href="<c:out value="${page.url}"/>"><c:out value="${page.index}"/></a></b>
  </c:when>
  <c:otherwise>
    <a href="<c:out value="${page.url}"/>"><c:out value="${page.index}"/></a>
  </c:otherwise>
</c:choose>
</c:forEach>
</td>
<td align="right">
     Export : <a href="<%=request.getContextPath()%>/xlsexport.do"><img src="<%=request.getContextPath()%>/images/excel.jpeg" border="1" height="25" width="25"></a>

    <a href="<%=request.getContextPath()%>/xmlexport.do"> <img src="<%=request.getContextPath()%>/images/xml.jpeg" border="1" height="25" width="25" ></a>
    <img src="<%=request.getContextPath()%>/images/pdf.jpeg" height="25"border="1" width="25">
    <a href="<%=request.getContextPath()%>/csvexport.do?format=csv"><img src="<%=request.getContextPath()%>/images/csv.jpg" height="25"border="1" width="25"></a>
</td>
</tr>
</table>
  <%}%>
  <%
String msg=(String)request.getAttribute("msg");
if(msg!=null){out.println(msg);
%>

<%
String login=(String)session.getAttribute("login_id");
String filename=(String)request.getAttribute("filename");
 if(login.indexOf(".")>0)
            login=login.substring(0,login.indexOf("."));
%>
<a href="<%=request.getContextPath()%>/export/<%=filename%>" target="_blank">Download it</a>
<%}%>

  </td></tr>
  <tr><td align="center" width="400px">
<form name="f">

    <input type="button" name="b1" value="<%=resource.getString("opac.accountdetails.back")%>" onclick="b1click()" class="txt2">
  
</form>

      </td></tr></table>

 
    </body>
</div>
  
</html>
