<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
       <jsp:include page="/admin/header.jsp"/>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS</title>
        <script type="text/javascript">
function send()
{
    top.location="<%= request.getContextPath() %>/viewMarcRepos.do";
    return false;
}
</script>
    </head>
    <body>

        <table  style="position:absolute; left: 5%;height: 500px; top: 20%; font-size: 12px;">

            <tr><td colspan="4" class="headerStyle">BIBLIOGRAPHIC DETAILS IN MARC-21 FORMAT OF TITLE</td></tr>
            <logic:iterate id="BiblioTemp" name="opacList">
                <tr>
                    <td style="font-weight: bold;">
                        <bean:write name="BiblioTemp" property="id.marctag"/>&nbsp;
                    </td>                       
                    <td>
                        <logic:present name="BiblioTemp" property="indicator1">
                        <bean:write name="BiblioTemp" property="indicator1"/>
                        </logic:present>
                        <logic:notPresent name="BiblioTemp" property="indicator1">
                        #
                        </logic:notPresent>&nbsp;
                    </td>
                    <td>
                     &nbsp;   <logic:notPresent name="BiblioTemp" property="indicator2">
                        #
                        </logic:notPresent>
                        <logic:present name="BiblioTemp" property="indicator2">
                        <bean:write name="BiblioTemp" property="indicator2"/>
                        </logic:present>       &nbsp;
                    </td>                       
                    <td style="text-align: justify">
                      &nbsp;  <logic:lessEqual value="010" name="BiblioTemp" property="id.marctag">
                        <bean:write name="BiblioTemp" property="$a"/>
                        </logic:lessEqual>
                        <logic:greaterEqual value="011" name="BiblioTemp" property="id.marctag">
                        $a<bean:write name="BiblioTemp" property="$a"/>
                        </logic:greaterEqual>
                        <logic:present name="BiblioTemp" property="$b">
                        $b<bean:write name="BiblioTemp" property="$b"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$c">
                        $c<bean:write name="BiblioTemp" property="$c"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$d">
                        $d<bean:write name="BiblioTemp" property="$d"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$e">
                        $e<bean:write name="BiblioTemp" property="$e"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$f">
                        $f<bean:write name="BiblioTemp" property="$f"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$g">
                        $g<bean:write name="BiblioTemp" property="$g"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$h">
                        $h<bean:write name="BiblioTemp" property="$h"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$i">
                        $i<bean:write name="BiblioTemp" property="$i"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$j">
                        $j<bean:write name="BiblioTemp" property="$j"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$k">
                        $k<bean:write name="BiblioTemp" property="$k"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$l">
                        $l<bean:write name="BiblioTemp" property="$l"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$m">
                        $m<bean:write name="BiblioTemp" property="$m"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$n">
                        $n<bean:write name="BiblioTemp" property="$n"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$o">
                        $o<bean:write name="BiblioTemp" property="$o"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$p">
                        $p<bean:write name="BiblioTemp" property="$p"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$q">
                        $q<bean:write name="BiblioTemp" property="$q"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$r">
                        $r<bean:write name="BiblioTemp" property="$r"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$s">
                        $s<bean:write name="BiblioTemp" property="$s"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$t">
                        $t<bean:write name="BiblioTemp" property="$t"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$u">
                        $u<bean:write name="BiblioTemp" property="$u"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$v">
                        $v<bean:write name="BiblioTemp" property="$v"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$w">
                        $w<bean:write name="BiblioTemp" property="$w"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$x">
                        $x<bean:write name="BiblioTemp" property="$x"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$y">
                        $y<bean:write name="BiblioTemp" property="$y"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$z">
                        $z<bean:write name="BiblioTemp" property="$z"/>
                        </logic:present>
                    </td>
                </tr>
            </logic:iterate>
                <tr><td> <input type="button" onclick="return send()" name="button" value="Back" Class="txt1"/></td></tr>
        </table>
       
    </body>
</html>
