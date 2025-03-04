<%--
    Document   : TableViewer
    Created on : Dec 13, 2010, 7:57:28 PM
    Author     : Copyright (c) 2010 - 2011 SMVDU, Katra.
                 Copyright (c) 2015, 2016 ETRG, IITK.
*  All Rights Reserved.
*  Redistribution and use in source and binary forms, with or
*  without modification, are permitted provided that the following
*  conditions are met:
**  Redistributions of source code must retain the above copyright
*  notice, this  list of conditions and the following disclaimer.
*
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in
*  the documentation and/or other materials provided with the
*  distribution.
*
*
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITK.
*  Modified Date: 7 jan 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*  Modified date 27 October 2014, IITK , Om Prakash (omprakashkgp@gmail.com) (GUI Main page)
--%>

<%@page import="org.smvdu.payroll.api.UserOperationBeans.UserBeans"%>
<%@page import="org.smvdu.payroll.beans.setup.SalaryProcessingSetup"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>

            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>Payroll System | Welcome</title>
           <%-- <link rel="stylesheet" type="text/css" href="css/reset.css"/>
            <link rel="stylesheet" type="text/css" href="css/table.css"/>
           <link rel="stylesheet" type="text/css" href="css/subpage.css"/>--%>
           <link rel="stylesheet" type="text/css" href="css/reset.css"/>
            <link rel="stylesheet" type="text/css" href="css/layout.css"/>
            <link rel="stylesheet" type="text/css" href="css/mainpage.css"/>
            
            

            <script type="text/javascript">

                function loadIframe(iframeName, url) {
                    if ( window.frames[iframeName] ) {

                        window.frames[iframeName].location = url;

                        return false;
                    }
                    return true;
                }
                // assign new url to iframe element's src property
                function changeIframeSrc(id, url) {
                    if (!document.getElementById) return;
                    var el = document.getElementById(id);
                    if (el && el.src) {
                        el.src = url;
                        return false;
                    }
                    return true;
                }
 
            </script>
            <style>
                .button{
                    margin-left:25px;
                    
                }
                .contactform{
                    margin-left:18px;
                    margin-top:5%;
                    text-align:center;
                }
            </style>
                
                       
            </head>
            <div style=" margin-left: 10%; margin-right: 10%; margin-top: 1%">
            <body id="" class="mainpage">
            <a4j:keepAlive beanName="userBeans" ajaxOnly="true"/>
            <a4j:keepAlive beanName="SalaryProcessingSetup" ajaxOnly="true"/>
            
            <h:form>
                 <h:graphicImage url="/img/payrollheader.png" width="100%" height="100%" />
                    <%--  <rich:panel style="height:130px; width:100%; padding:0px; border-width:0px;">
                    <%--<h:graphicImage url="/img/pls1.png" width="100%" height="100%"/>--%>
                    <%--<h:graphicImage url="/img/payrollheader.png" width="100%" height="100%" />--%>
                    <%--      <div  align="right">
                    <%--<h:graphicImage url="/img/3_1.PNG" style="margin-top:-100px;"/>--%>
                    <%--    </div>
                    </rich:panel>--%>
                    <rich:toolBar width="100%;" height="10px" >
                    <rich:hotKey  key="alt+k" handler="#{rich:component('em')}.expand()"/>

                    <rich:dropDownMenu  id="se" value="Setup" styleClass="pic" >
                        <rich:menuItem  id="se1"   onclick="return loadIframe('ifrm', 'setup/SessionSetup.jsf')"  value="Financial Years"/>
                        <rich:menuItem  id="se2"  onclick="return loadIframe('ifrm', 'setup/Departments.jsf')"  value="Department"/>
                        <rich:menuItem  id="se3"  onclick="return loadIframe('ifrm', 'setup/Designation.jsf')" value="Designation"/>
                        <rich:menuItem  id="se4"  onclick="return loadIframe('ifrm', 'setup/bankProfile.jsf')" value="Add Bank Profile"/>
                        <rich:menuItem  id="se5"  onclick="return loadIframe('ifrm', 'EmployeeType.jsf')" value="Employee Types"/>
                        <rich:menuItem  id="se6"  onclick="return loadIframe('ifrm', 'setup/SalaryGrade.jsf')" value="Pay Scales"/>
                        <rich:menuItem  id="se7"  onclick="return loadIframe('ifrm', 'setup/SalaryHead.jsf')" value="Salary Heads"/>
			<rich:menuItem  id="se12"  onclick="return loadIframe('ifrm', 'setup/BgasLedgerMap.jsf')" value="Bgas Ledger Map"/>
                        <rich:menuGroup id="se8"  value="Tax Slab Setup" direction="right-bottom">
                            <rich:menuItem  id="se81"  onclick="return loadIframe('ifrm', 'TaxSlabSetup/Gender.jsf')" value="Add Gender Details"/>
                            <rich:menuItem  id="se82"  onclick="return loadIframe('ifrm', 'TaxSlabSetup/TaxSlabName.jsf')" value="Add Slab Details"/>
                            <rich:menuItem  id="se83"  onclick="return loadIframe('ifrm', 'TaxSlabSetup/EmployeeSlabValue.jsf')" value="Employee Slab Details"/>
                            <rich:menuItem  id="se84"  onclick="return loadIframe('ifrm', 'TaxSlabSetup/TaxCalculationType.jsf')" value="Calculation Type Details"/>
                        </rich:menuGroup>
                       <%-- <rich:menuItem  id="se9"  onclick="return loadIframe('ifrm', 'account/AddUser.jsf')" value="System User Accounts"/>--%>
                        <rich:menuItem  id="se10"  onclick="return loadIframe('ifrm', 'setup/SalaryProcessingSetup.jsf')" value="Salary Processing Setup"/>
                        <rich:menuItem  id="se11"  onclick="return loadIframe('ifrm', 'attendance/LeaveTypes.jsf')" value="Leave Types"/>
                       
                    </rich:dropDownMenu>
                    <rich:dropDownMenu id="em"   value="Employee" styleClass="pic" ><%-- rendered="#{readUserTaskList.userTaskId == 'em'}" --%>
                        <rich:menuItem id="em1" onclick="return loadIframe('ifrm', 'employee/EmployeeProfile.jsf')"  value="Add Profile"/>
                        <rich:menuItem id="em2" onclick="return loadIframe('ifrm', 'employee/EditEmployeeProfile.jsf')" value="Edit Profile"/>
                        <rich:menuItem id="em3" onclick="return loadIframe('ifrm', 'employee/SearchEmployee.jsf')" value="Search Profiles"/>
                        <rich:menuItem id="em4" onclick="return loadIframe('ifrm', 'employee/EmployeeAttendance.jsf')" value="Employee Attendance"/>
                    </rich:dropDownMenu> 
                    <rich:dropDownMenu  id="sa"   value="Salary" styleClass="pic" >
                        <rich:menuItem  id="sa6" onclick="return loadIframe('ifrm','salary/ConfigureSalLiability.jsf')" value="Configure Salary Libility"/>
                        <rich:menuItem  id="sa1" onclick="return loadIframe('ifrm','salary/SalaryFormula.jsf')" value="Salary Formula"/>
                        <rich:menuItem  id="sa2" onclick="return loadIframe('ifrm','salary/DefaultSalaryData.jsf')" value="Default Salary values"/>
                        <rich:menuItem  id="sa3" onclick="return loadIframe('ifrm','salary/SalarySettings.jsf')" value="Type wise Salary Head Setting"/>
                       <%--<rich:menuItem  id="sa4" onclick="return loadIframe('ifrm','salary/MonthlySalaryProcessing.jsf')" value="Salary Processing"/>--%>
                        <rich:menuGroup id="sa5"  value="Salary Processing Setup" >
                        <rich:menuItem id="sa51" onclick="return loadIframe('ifrm','salary/MonthlySalaryProcesswithBudget.jsf')" value="Salary Processing with Budget" disabled="#{SalaryProcessingSetup.inactive}"/>
                        <rich:menuItem  id="sa52" onclick="return loadIframe('ifrm','salary/MonthlySalaryProcessing.jsf')" value="Salary Processing" disabled="#{SalaryProcessingSetup.active}" />
                        
                        </rich:menuGroup>
                        <rich:menuItem  id="sa54" onclick="return loadIframe('ifrm','salary/EmpSalaryLiability.jsf')" value="Employee Liability"/>
                        <rich:menuItem  id="sa43" onclick="return loadIframe('ifrm','salary/SalaryPaid.jsf')" value="Salary Paid"/>
                        

                        <rich:separator/>
                        <%-- <rich:menuItem value="Exit"/> --%>
                    </rich:dropDownMenu>
                   <%-- <rich:dropDownMenu  id="ad"  value="Administration " styleClass="pic" >
                            <rich:menuItem  id="ad1"  onclick="return loadIframe('ifrm','adm/Salary.jsf')" value="Lock Salary"/>
                            <rich:menuGroup id="ad2"  value="Module Management" direction="right-bottom">
                                <rich:menuItem id="ad21" onclick="return loadIframe('ifrm','userOperation/createUser.jsf')" value="Create New User"/>
                                <rich:menuItem id="ad22" onclick="return loadIframe('ifrm','userOperation/NewUserTask.jsf')" value="Assign Task"/>
                                <rich:menuItem id="ad23" onclick="return loadIframe('ifrm','userOperation/NewUserTask.jsf')" value="Manage Task"/>
                            </rich:menuGroup>
                    </rich:dropDownMenu>--%>
                    
                    <rich:dropDownMenu  id="tm"  value="Tax Management" styleClass="pic">
                        <rich:menuItem  id="tm1"    onclick="return loadIframe('ifrm', 'investment/InvestmentCategory.jsf')"  value="Main Head Sections"/>
                        <rich:menuItem  id="tm2"   onclick="return loadIframe('ifrm', 'investment/InvestmentHead.jsf')"  value="Investment Heads"/>
                        <rich:menuItem  id="tm3"   onclick="return loadIframe('ifrm', 'investment/EmployeeInvestment.jsf')" value="Investment Form"/>
                        <rich:menuItem  id="tm4"   onclick="return loadIframe('ifrm', 'investment/TaxCalculator.jsf')" value="Annual Tax Calculator"/>
                        <%--          <rich:menuItem  id="m4"   onclick="return loadIframe('ifrm', 'investment/TaxPlanner.jsf')" value="Tax payback Planer"/>
                        <rich:menuItem  id="m5"   onclick="return loadIframe('ifrm', 'investment/TaxSlabs.jsf')" value="Tax Slabs"/>   --%>
                    </rich:dropDownMenu>
                    <%--<rich:dropDownMenu value="User Rights">
                        <rich:menuItem onclick="return loadIframe('ifrm', 'order/EmpOrder.jsf')"  value="User Rights"/>
                        <rich:menuItem onclick="return loadIframe('ifrm', 'PortalACL.jsf')"  value="Portal Access Control"/>

                    </rich:dropDownMenu>--%>
                    <%--<rich:dropDownMenu  value="Loans & Advances">
                        <rich:menuItem onclick="return loadIframe('ifrm', 'loan/LoanMaster.jsf')"  value="Loan Type Setup"/>
                        <rich:menuItem onclick="return loadIframe('ifrm', 'loan/PendingLoan.jsf')"  value="Loan Requests"/>
                    <%--<rich:menuItem onclick="return loadIframe('ifrm', 'attendance/LeaveQuota.jsf')"  value="Loan Payback Planner"/>
                </rich:dropDownMenu>--%>

                 <%--<rich:dropDownMenu  value="Leave">
                                <rich:menuItem onclick="return loadIframe('ifrm', 'attendance/LeaveManager.jsf')"  value="Leave Manager"/>
                                <rich:menuItem onclick="return loadIframe('ifrm', 'attendance/LeaveValue.jsf')"  value="Leave Values Setup"/>
                                <rich:menuItem onclick="return loadIframe('ifrm', 'attendance/LeaveQuota.jsf')"  value="Leave Quota Setup"/>
                  </rich:dropDownMenu> --%>
                    <rich:dropDownMenu  id="pf"   value="PF Management" styleClass="pic">
                        <rich:menuItem  id="pf1"   onclick="return loadIframe('ifrm', 'pf/OpeningBalance.jsf')"  value="Opening Balance"/>
                        <rich:menuItem  id="pf2"  onclick="return loadIframe('ifrm', 'pf/PFWithdrawal.jsf')"  value="PF Withdrawal"/>
                        <rich:menuItem  id="pf3"  onclick="return loadIframe('ifrm', 'pf/PFAccount.jsf')"  value="PF Account"/>
                    </rich:dropDownMenu>
                    <%--- H R Management-----------------%>
                    
                    <rich:dropDownMenu  id="hr"   value="HR Management" styleClass="pic" >
                        <rich:menuItem id="hr1" onclick="return loadIframe('ifrm', 'employee/EmployeefamilyRecord.jsf')" value="Employee Family Record"/>
                        <rich:menuItem id="hr2" onclick="return loadIframe('ifrm', 'employee/EmployeeServiceHistory.jsf')" value="Employee Service History"/>
                        <rich:menuItem id="hr3" onclick="return loadIframe('ifrm', 'employee/EmployeeQualification.jsf')" value="Employee Education Details"/>
                       <rich:menuGroup id="hr4" value="Employee Leave Mangement" direction="right-bottom">
                            <rich:menuItem id="hr41" onclick="return loadIframe('ifrm', 'attendance/LeaveQuota.jsf')"  value="Leave Quota Setup"/>
                            <rich:menuItem id="hr42" onclick="return loadIframe('ifrm', 'attendance/LeaveManager.jsf')"  value="Leave Manager"/>
                            <%--<rich:menuItem onclick="return loadIframe('ifrm', 'attendance/LeaveValue.jsf')"  value="Leave Values Setup"/>--%>
                            <rich:menuItem id="hr43" onclick="return loadIframe('ifrm', 'attendance/LeaveDetail.jsf')"  value="Leave Detial"/>
                            <rich:menuItem id="hr44" onclick="return loadIframe('ifrm', 'attendance/ViewLeaveRequest.jsf')" value="Leave Request" />
                       </rich:menuGroup>   
                        <rich:menuItem id="hr5" onclick="return loadIframe('ifrm', 'employee/EmpPayscaleHistory.jsf')" value="Employee Payscale History" disabled="true"/>
                        <rich:menuItem id="hr6" onclick="return loadIframe('ifrm', 'employee/EmployeePFNotification.jsf')" value="Employee PF notification" disabled="true"/>
                        <rich:menuItem id="hr7" onclick="return loadIframe('ifrm', 'employee/EmployeeSanctions.jsf')" value="Employee LTC, travel,or any other sanctions" disabled="true"/>
                                
                    </rich:dropDownMenu>
                                       
                    <%--- H R Management-----------------%>
                    <rich:dropDownMenu  id="re"   value="Report" styleClass="pic">
                        <rich:menuItem  id="re1"  onclick="return loadIframe('ifrm', 'report/ReportExporterBankStatement.jsf?fwdLink=BankStatement.jsf')"  value="Bank Statement"/>
                        <rich:menuItem  id="re2" onclick="return loadIframe('ifrm', 'report/ReportExporterMonthlySlip.jsf?fwdLink=MonthlySalarySingle.jsf')"  value="Monthly Salary Slip"/>
                        <rich:menuItem  id="re3" onclick="return loadIframe('ifrm','salary/MonthlyIndividualGrossSalary.jsf')" value="Individual Gross Salary"/>
                        <rich:menuItem  id="re4" onclick="return loadIframe('ifrm','report/ReportExporterMonthlyRoll.jsf?fwdLink=MonthlyPayroll.jsf')" value="Monthly Salary Roll"/>
                        <rich:menuItem  id="re5" onclick="return loadIframe('ifrm','report/ReportExporter.jsf?fwdLink=APF.jsf')" value="Annual PF Report"/>
                        <rich:menuItem  id="re6" onclick="return loadIframe('ifrm','report/ReportExporterConcise.jsf?fwdLink=APF_Compact.jsf')" value="Concise PF Report"/>
			<rich:menuItem  id="re7" onclick="return loadIframe('ifrm','report/ReportExporterAttendance.jsf?fwdLink=AttendanceReportMonthly.jsf')" value="Monthly Attendance Report"/>
                        <rich:menuItem  id="re8" onclick="return loadIframe('ifrm','report/IndividualAttendanceReport.jsf?fwdLink=AttendanceReportMonthly.jsf')" value="Individual Attendance Report"/>
                        <rich:menuItem  id="re9" onclick="return loadIframe('ifrm','report/AnnualAttendanceReport.jsf?fwdLink=AttendanceReportMonthly.jsf')" value="Annual Attendance Report"/>
                    </rich:dropDownMenu>

                    <rich:dropDownMenu  id="tr"   value="Tax Reports" styleClass="pic" >
                        <rich:menuItem  id="tr1"   onclick="return loadIframe('ifrm','taxreports/TaxReport.jsf')" value="Employee Wise Investments"/>
                        <rich:menuItem  id="tr2"   onclick="return loadIframe('ifrm','taxreports/AnnualTax.jsf')" value="Annual Payable Tax"/>
                        <rich:menuItem  id="tr3"   onclick="return loadIframe('ifrm','taxreports/FinalTaxPayable.jsf')" value="Form 16"/>
                        <rich:menuItem  id="tr4"   onclick="return loadIframe('ifrm','taxreports/ActualPaidTax.jsf')" value="Actual and Paid Tax"/>
                        <%--<rich:menuItem onlick="return loadIframe('ifrm','investment/FinalTaxPayable.jsf')" value="Form 16"/>--%>

                    </rich:dropDownMenu>

                    <rich:dropDownMenu  id="t"   value="Tools" styleClass="pic" >
                        <%--  <rich:menuItem onclick="return loadIframe('ifrm', 'tool/ArrCalculator.jsf')"  value="Arrear Calculator Tool"/> --%>
                        <rich:menuItem  id="t2"  onclick="return loadIframe('ifrm', 'tool/SalaryGeneration.jsf')"  value="Salary Generation"/>
                        <rich:menuItem  id="t1"  onclick="return loadIframe('ifrm', 'tool/SalaryCopier.jsf')"  value="Salary Copy Tool"/>
                        <%--  <rich:menuItem onclick="return loadIframe('ifrm', 'tool/ReportTool.jsf')"  value="Report Tool"/> --%>
                        <%--    <rich:menuItem action="account/Logout.jsf" value="Logout"/> --%>
                    </rich:dropDownMenu>
                    
                    <rich:dropDownMenu  id="s"   value="Account" styleClass="pic" >
                        <rich:menuItem  id="s1"  action="#{UserBean.switchAccount}"  value="Switch Account"/>
                        <rich:menuItem action="#{UserBean.logout}" value="Logout"/>
                    </rich:dropDownMenu>
                    
                    <%--<h:commandButton id="lout"  image="img/lout.gif" action="#{UserBean.logout}" /> --%>
                    <%--  <h:commandButton id="lout"  value="Logout" action="#{UserBean.logout}" /> --%>
                    <rich:toolTip value="Click to Logout" for="lout"/>
                    <h:graphicImage style="visibility:hidden" id="loaderimg" onclick="return loadIframe('ifrm', 'Home.jsf')" width="170px" height="16px" url="/img/waiter.gif" alt="Image"/>

                </rich:toolBar>
            </h:form>

            <div class="sidebar">
                
                <%-- <rich:panel id="leftpa" >--%>
                    <rich:panel style="height:575px; width:100%; padding:0px; border-width:0px;">
                    <div class="logo">  </div>
                    <rich:panel>
                        <h:form>
                            <rich:contextMenu event="oncontextmenu" attachTo="logoimg" submitMode="ajax">
                                <rich:menuItem value="Edit Logo" onclick="Richfaces.showModalPanel('pnl');" id="zin"/>
                            </rich:contextMenu>
                            <a4j:mediaOutput id="logoimg" element="img" mimeType="#{fileUploadBean.file.mime}"
                                             createContent="#{fileUploadBean.paint}" value="#{row}"
                                             style="width:140px; height:140px;" cacheable="false">
                            </a4j:mediaOutput>
                        </h:form>
                    </rich:panel>
                    <rich:panel>
                        <h:panelGrid columns="2">
                            <h:outputText value=""/>
                            <h:outputText style="font-size:16px;align:center;" value="#{OrgController.currentOrg.name}"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:outputText value=""/>
                            <h:outputText value="#{OrgController.currentOrg.tagLine}"/>
                        </h:panelGrid>
                        <h:form>
                            <h:panelGrid columns="2">
                                <h:outputText value=""/>
                                <h:commandLink value="#{OrgController.currentOrg.web}"/>
                            </h:panelGrid>
                        </h:form>
                        <h:panelGrid columns="2">
                            <h:outputText value=""/>
                            <h:outputText value="#{OrgController.currentOrg.address1}"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:outputText value=""/>
                            <h:outputText value="#{OrgController.currentOrg.address2}"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:outputText value=""/>
                            <h:outputText value="#{OrgController.currentOrg.email}"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:outputText value=""/>
                            <h:outputText value="#{OrgController.currentOrg.phone}"/>
                        </h:panelGrid>
                        <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Upload Logo" />
                        <h:commandButton onclick="Richfaces.showModalPanel('instData');" value="Update"/>
                        <rich:messages>
                            <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                    </rich:panel>

                    <rich:simpleTogglePanel label="User Controls">
                        <h:panelGrid id="usrpnl" columns="2">
                            <h:outputText value=""/>
                            <h:outputText value="#{UserBean.profile.name}"/>
                            <h:outputText style="font-weight:bold;" value="Financial Year"/>
                            <h:selectOneMenu value="#{SessionController.sessionName}">
                                <f:selectItems value="#{SessionController.asItems}"/>
                            </h:selectOneMenu>
                            <h:outputText id="dp" style="font-weight:bold;" value="Date"/>
                            <h:form>
                                <rich:calendar inputSize="10" value="#{UserBean.currentDate}"
                                               datePattern="yyyy-MM-dd" converter="dateConverter"/>
                                
                                <a4j:commandButton reRender="dp" action="#{UserBean.set}" value="Set" />
                            </h:form>
                        </h:panelGrid>
                        <h:panelGrid columns="1">
                            <a4j:commandButton onclick="Richfaces.showModalPanel('cp');" value="Change Password"/>
                            <h:commandButton onclick="Richfaces.showModalPanel('themepanel');" value="Theme"/>
                        </h:panelGrid>
                    </rich:simpleTogglePanel>
                </rich:panel>
            </div>
                    
            <div class="body">
                <%-- <div class="content-area" >
                    <iframe name="ifrm" id="ifrm" src="Home.jsf" style="background-color:#ffffff"
                            width="100%;" height="575px">Your browser doesn't support iframes.</iframe>

                </div>--%>
                 <div class="content-area" >
                    <iframe name="ifrm" id="ifrm" src="setup/SessionSetup.jsf" style="background-color: whitesmoke "
                            width="100%;" height="575px">Your browser doesn't support iframes.</iframe>

                </div>
                             
            </div>
            <rich:modalPanel id="pnl" >
                <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('pnl')" />
                </f:facet>
                <h:form>
                    <rich:fileUpload addControlLabel="Browse Logo" fileUploadListener="#{fileUploadBean.listener}"
                                     id="uploasd"
                                     immediateUpload="false"
                                     acceptedTypes="jpg, gif, png, bmp" allowFlash="false">
                        <a4j:support event="onuploadcomplete" reRender="logoimg" />
                    </rich:fileUpload>
                </h:form>
            </rich:modalPanel>


            <rich:modalPanel id="instData" autosized="true"  domElementAttachment="parent" width="500" height="300">
                
                <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('instData')" />
                </f:facet>
                <f:facet name="header">
                    <h:outputText value="Update Institute Information" />
                </f:facet>
                <h:form>
                   
                    <h:panelGrid columns="3" id="contactform" styleClass="contactform">
                        <h:outputText styleClass="Label" value="Institute Name"/>
                        <h:inputText  size="40"  id="instname" requiredMessage="Name can not be empty" required="true" value="#{OrgController.currentOrg.name}"/>
                        <h:message for="instname" style="color:red" />
                        <h:outputText styleClass="Label" value="Tag Line"/>
                        <h:inputText  size="40" id="tline" requiredMessage="" value="#{OrgController.currentOrg.tagLine}"/>
                        <h:message for="tline" style="color:red" />
                        <h:outputText styleClass="Label" value="Adress"/>
                        <h:inputText size="40" id="add1" requiredMessage="Address can not empty" required="true" value="#{OrgController.currentOrg.address1}"/>
                        <h:message for="add1" style="color:red" />
                        <h:outputText styleClass="Label" value="City"/>
                        <h:inputText size="40" id="city" requiredMessage="City can not empty" required="true" value="#{OrgController.currentOrg.city}"/>
                        <h:message for="city" style="color:red" />
                        <h:outputText styleClass="Label" value="State"/>
                            <h:selectOneMenu id="state" requiredMessage="State can not empty " required="true" value="#{OrgController.currentOrg.state}">
                                <f:selectItem itemLabel="Delhi" itemValue="Delhi"/>
                                <f:selectItem itemLabel="Haryana" itemValue="Haryana" />
                                <f:selectItem itemLabel="Punjab" itemValue="Punjab" />
                                <f:selectItem itemLabel="Chandigarh" itemValue="Chandigarh" />
                                <f:selectItem itemLabel="Himachal Pradesh" itemValue="Himachal Pradesh" />
                                <f:selectItem itemLabel="Jammu and Kashmir" itemValue="Jammu and Kashmir" />
                                <f:selectItem itemLabel="Uttarakhand" itemValue="Uttarakhand" />
                                <f:selectItem itemLabel="Uttar Pradesh" itemValue="Uttar Pradesh" />
                                <f:selectItem itemLabel="Rajasthan" itemValue="Rajasthan" />
                                <f:selectItem itemLabel="Gujarat" itemValue="Gujarat" />
                                <f:selectItem itemLabel="Goa" itemValue="Goa" />
                                <f:selectItem itemLabel="Maharastra" itemValue="Maharastra" />
                                <f:selectItem itemLabel="Madhya Pradesh" itemValue="Madhya Pradesh" />
                                <f:selectItem itemLabel="Chhattisgarh" itemValue="Chhattisgarh" />
                                <f:selectItem itemLabel="Andhra Pradesh" itemValue="Andhra Pradesh" />
                                <f:selectItem itemLabel="Telangana" itemValue="Telangana" />
                                <f:selectItem itemLabel="Karnataka" itemValue="Karnataka" /> 
                                <f:selectItem itemLabel="Tamil Nadu" itemValue="Tamil Nadu" />
                                <f:selectItem itemLabel="Kerala" itemValue="Kerala" />
                                <f:selectItem itemLabel="Lakshadweep (Islands)" itemValue="Lakshadweep (Islands)" />
                                <f:selectItem itemLabel="West Bengal" itemValue="West Bengal" />
                                <f:selectItem itemLabel="Andaman and Nicobar Islands" itemValue="Andaman and Nicobar Islands" />
                                <f:selectItem itemLabel="Odisha" itemValue="Odisha" />
                                <f:selectItem itemLabel="Assam" itemValue="Assam" />
                                <f:selectItem itemLabel="Arunachal Pradesh" itemValue="Arunachal Pradesh" />
                                <f:selectItem itemLabel="Meghalaya" itemValue="Meghalaya" />
                                <f:selectItem itemLabel="Manipur" itemValue="Manipur" />
                                <f:selectItem itemLabel="Mizoram" itemValue="Mizoram" />
                                <f:selectItem itemLabel="Tripura" itemValue="Tripura" />
                                <f:selectItem itemLabel="Bihar " itemValue="Bihar " />
                                <f:selectItem itemLabel="Jharkhand" itemValue="Jharkhand" />
                            </h:selectOneMenu> 
                            <h:message for="state" style="color:red" />
                        <h:outputText styleClass="Label" value="WebSite"/>
                        <h:inputText size="40" id="webs" requiredMessage="Website can not be empty" required="true" value="#{OrgController.currentOrg.web}" label="Website url">
                        <f:validator validatorId="UrlValidator"/>    
                        </h:inputText> 
                        <h:message for="webs" style="color:red" />
                        <h:outputText styleClass="Label" value="Pincode"/>
                        <h:inputText size="40" id="pcode" requiredMessage=" Enter Valid Pincode (e.g. For IIT Kanpur (208016)" required="true" value="#{OrgController.currentOrg.pincode}" label="Pincode">
                        <f:validateLength minimum="6" maximum="6" />
                        <f:convertNumber integerOnly="true" type="number" />
                        </h:inputText>
                        <h:message for="pcode" style="color:red" />
                        <h:outputText styleClass="Label" value="Phone"/>
                        <h:inputText size="40" id="pno" requiredMessage="Please Enter Valid Phone Number ( Area Code + Landline / mobile ) e.g. 512 2597944 / 7000002422 " required="true" value="#{OrgController.currentOrg.phone}" label="Phone">
                        <f:validateLength minimum="10" maximum="10" />
                        <f:convertNumber integerOnly="true" type="number" />    
                        </h:inputText> 
                        <h:message for="pno" style="color:red" />
                    </h:panelGrid> 
                    <br/>
                    <rich:separator styleClass="button"/> 
                    <br/>
                    <h:commandButton styleClass="button" action="#{OrgController.currentOrg.update}" value="Update"/>
                    <a4j:commandButton  onclick="Richfaces.hideModalPanel('instData')" value="Close"/>
                </h:form>
            </rich:modalPanel>

                <rich:modalPanel id="cp" width="500" height="240" autosized="true">
                <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('cp')" />
                </f:facet>
                <f:facet name="header">
                    <h:outputText value="Change Password " />
                </f:facet>
                
                <h:form>
                    <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    <br/    >
                    <%--<h:panelGrid columns="2" id="contact" styleClass="contactform" cellpadding="0" cellspacing="0">--%>
                    <h:panelGrid columns="3" styleClass="data_entry_form" columnClasses="label,field">
                        <h:outputText value="New Password"/>
                        <h:inputSecret size="30" id="spass" label="Password" value="#{UserBean.pass1}"/>
                        <h:message for="npass" style="color:red" />
                        <h:outputText value="Confirm Password "/>
                        <h:inputSecret size="30" id="cpass" label="Password" value="#{UserBean.pass2}"/>
                        <h:message for="cpass" style="color:red" />
                    </h:panelGrid>
                    <br/>
                    <%--<rich:separator/> --%>
                    <h:panelGrid columns="2" id="con" style="padding-left:20%;" cellpadding="0" cellspacing="0">
                    <a4j:commandButton value="Submit" action="#{UserBean.editPass}"/>
                    <a4j:commandButton value="Reset" type="reset"/>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>


            <rich:modalPanel id="sesspanel">
                <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('themepanel')" />
                </f:facet>
                <f:facet name="header">
                    <h:outputText value="Session Chooser" />
                </f:facet>
                <h:form>

                    <h:panelGrid columns="3">
                        <h:outputText value="Select Current Session"/>
                        <h:selectOneMenu value="#{SessionController.currentSession}">
                            <f:selectItems value="#{SessionController.asItems}"/>
                        </h:selectOneMenu>
                        <a4j:commandButton reRender="usrpnl" action="#{SessionController.refresh}" value="Select"/>
                        <h:commandButton onclick="Richfaces.hideModalPanel('themepanel');" value="Close"/>
                    </h:panelGrid>
                    <rich:modalPanel id="pn1l" height="200" width="300">
                        <h:panelGrid columns="1">
                            <h:form>
                                <rich:panel>

                                </rich:panel>
                                <rich:panel>
                                    <h:panelGrid columns="2">
                                        <h:outputText style="font-size:13px;font-weight:bold;" value="User Id : "/>
                                        <h:inputText value="#{userBeans.userId}" style="font-size:14px;font-weight:bold;"/>
                                        <h:outputText value="Password : " style="font-size:13px;font-weight:bold;"/>
                                        <h:inputSecret value="#{userBeans.userPassword}" style="font-size:14px;font-weight:bold;"/>
                                    </h:panelGrid>
                                </rich:panel>
                                <rich:separator/>
                                <a4j:commandButton action="#{userBeans.login}" reRender="ifrm" value="Login" onclick="#{userBeans.logiDailog}" style="font-size:13px;font-weight:bold;">

                                </a4j:commandButton>
                            </h:form>
                        </h:panelGrid>
                    </rich:modalPanel>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="themepanel">
                <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('themepanel')" />
                </f:facet>
                <f:facet name="header">
                    <h:outputText value="Theme Selector" />
                </f:facet>
                <h:form>
                    <h:panelGrid columns="3">
                        <h:outputText value="Select Theme"/>
                        <h:selectOneMenu onchange="submit();" value="#{ThemeBean.theme}">
                            <f:selectItems value="#{ThemeBean.themes}"/>
                        </h:selectOneMenu>
                        <h:commandButton onclick="Richfaces.hideModalPanel('themepanel');" value="Close"/>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
                <rich:toolBar width="100%;" height="25px" > Developed by : SMVDU Team, and IIT KANPUR Team </rich:toolBar>
                                 
        </body>
       </dir>
    </html>
</f:view>
