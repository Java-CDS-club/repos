<%-- 
    Document   : MediaPublicationInfo
    Created on : Jan 18, 2012, 12:49:13 PM
    Author     : IGNOU Team
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Media Publications</title>
<link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
 <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
</head>
<body><%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
                response.sendRedirect("../Login.jsp");
            }
        %>
<div class="w100 fl-l">
  <div class="w990p mar0a">
    <!--Header Starts Here-->
    <s:include  value="/Header.jsp"/>
    <!--Header Ends Here-->
    <!--Middle Section Starts Here-->
    <div class="w100 fl-l">
      <div class="middle_bg">
        <!--Left box Starts Here-->
        <s:include value="/Left-Nevigation.jsp"/>
        <!--Left box Ends Here-->
        <!--Right box Starts Here-->
        <div class="right_box">
          <div class="my_account_bg">Media Publications</div>
          <div class="w100 fl-l mart10">
            <div class="w98 mar0a">
              <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> Media Publication </div>
              <div class="w100 fl-l">
                <s:a href="Media-Add.jsp" namespace="/MyWorkspace"> <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Media"/> </s:a>
              </div>
              <div class="w100 fl-l tc fbld fcgreen">
                <s:property value="msg"/>
              </div>
              <div class="w100 fl-l mart10">
                <table width="100%" class="fl-l" cellpadding="0" cellspacing="0" border="1">
                  <s:iterator value="MPubList" var="mp" status="groupStatus">
                    <tr>
                      <td width="5%" align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                      <td width="85%"><s:iterator value="basicListList">
                          <s:property value="lname"/>
                          ,&nbsp;
                          <s:property value="fname"/>
                          &nbsp;
                          <s:property value="mname"/>
                          , </s:iterator>
                        "<i>
                        <s:property value="titleOfArticle"/>
                        </i>",
                        <s:property value="titleOfMedia"/>
                        ,
                        <s:property value="pubDate"/>
                        .</td>
                        <td width="5%" align="center" valign="top"><a href="EditMP?mpId=<s:property value="mpId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a></td>
                        <td width="5%" align="center" valign="top"><a href="DeleteMP?mpId=<s:property value="mpId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a></td>
                    </tr>
                  </s:iterator>
                </table>
              </div>
            </div>
          </div>
          <!--Right box End Here-->
        </div>
      </div>
      <!--Middle Section Ends Here-->
    </div>
  </div>
</div>
<s:include value="/Footer.jsp"/>
</body>
</html>
