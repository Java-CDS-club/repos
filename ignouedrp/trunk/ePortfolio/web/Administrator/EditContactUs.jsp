<%-- 
    Document   : EditContactUs
    Created on : Dec 27, 2012, 3:17:42 PM
    Author     : Amit
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
        <title>Edit Contact Us and About Us</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Edit Contact Us and About Us</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                          <div class="w100 fl-l mart10">
                                            <div class="w100 fl-l tc fbld fcgreen">
                                            <s:property value="msg"/>
                                        </div>
                                        <div class="w100 fl-l mart5">
                                            <fieldset class="w450p mar0a">
                                                <legend class="fbld">Edit Contact Us and About Us</legend>
                                                <s:iterator value="contactList">
                                                <s:form action="UpdateContactUs" method="post"  namespace="/Administrator">
                                                    <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                        <s:hidden name="contactId"/>
                                                        <s:textfield name="contactName" label="Name of Contact Person"/>
                                                        <s:textarea name="contactAddress" label="Contact Address"/>
                                                         <s:textfield name="contactOff" label="Conatct Number(Office)"/>
                                                         <s:textfield name="contactMob" label="Conatct Number(Mobile)"/>
                                                          <s:textfield name="contactEmail" label="Email-Id"/>
                                                         <s:textarea name="aboutUs" cols="30" rows="10" label="About Us"/>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save" theme="simple" />
                                                                <s:reset value="Cancel" theme="simple" onClick="history.go(-1);" /></td>
                                                        </tr>
                                                    </table>
                                                    <br/>
                                                </s:form>
                                                     </s:iterator>
                                            </fieldset>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Right box End Here-->
                    </div>
                </div>
                <!--Middle Section Ends Here-->
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
       
    </body>
</html>
