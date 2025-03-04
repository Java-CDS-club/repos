<%-- 
    Document   : index
    Created on : 16 Jan, 2012, 9:24:48 AM
    Author     : ERP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Payroll Administration | Login</title>
        </head>
        <body>
            <rich:panel style="margin-left:35%;margin-right:35%;width:30%;margin-top:100px;height:100px;">
                <h:form>
                    <h:graphicImage  url="/img/admin.png"/>
                    <h:panelGrid columns="3">
                        <h:outputText value="Master Password"/>
                        <h:inputSecret id="pass" value="#{MasterPassword.pass}"/>
                        <h:commandButton value="Login" action="#{MasterPassword.validate}"/>
                        <h:messages/>
                    </h:panelGrid>
                </h:form>
            </rich:panel>
        </body>
    </html>
</f:view>
