<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>

            <table border="0" width="90%" >
                <tbody   >
                    <tr>
                        <td> <br><br> </td>
                    </tr>
                    <tr>
                        <td> <br><br> </td>
                    </tr>
                    <tr>
                        <td> <br><br> </td>
                    </tr>
                    <tr>
                        <td> <br><br> </td>
                    </tr>
                    <tr>
                        <td> <br><br> </td>
                    </tr> <tr>
                        <td align="center">The system has encountered error(s):<br><br>
                            <s:property value="message"/></td>
                    </tr>
                    <tr>
                        <td> <br><br> </td>
                    </tr>
                    <tr>
                        <td> <br><br> </td>
                    </tr>
                    <tr>
                        <td> <br><br> </td>
                    </tr>
                    <tr>
                        <td> <br><br> </td>
                    </tr>
                </tbody>
            </table>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
