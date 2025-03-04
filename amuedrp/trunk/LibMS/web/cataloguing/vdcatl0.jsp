<%@page import="com.myapp.struts.hbm.Biblio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <title> Bibliographic Cataloguing According to MARC21</title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-1.4.2.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">

<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/

</script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 0) //initialize Tab Menu #3 with 1st tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('043', 'fade=1,height=30px')
animatedcollapse.addDiv('082', 'fade=1,height=30px')
animatedcollapse.addDiv('020', 'fade=1,height=30px')
animatedcollapse.addDiv('022', 'fade=1,height=30px')
animatedcollapse.addDiv('041', 'fade=1,height=30px')
animatedcollapse.addDiv('020c', 'fade=1,height=30px')
animatedcollapse.addDiv('020z', 'fade=1,height=30px')
animatedcollapse.addDiv('022y', 'fade=1,height=30px')
animatedcollapse.addDiv('022z', 'fade=1,height=30px')
animatedcollapse.addDiv('082b', 'fade=1,height=30px')
animatedcollapse.addDiv('0822', 'fade=1,height=30px')

animatedcollapse.addDiv('041b', 'fade=1,height=30px')
animatedcollapse.addDiv('041d', 'fade=1,height=30px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()

</script>
<script type="text/javascript">
    var description=new Array()

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/tooltip.js">

/***********************************************
* ToolTip layer - (c) muhammad zeeshan
***********************************************/

</script>

</head>

 <jsp:include page="/admin/header.jsp"></jsp:include>
    <body><div
   style="  top:15%;
   left:10%;
   right:10%;border: solid 1px black;
      position: absolute;

      visibility: show;">
        <layer name="nsviewer" bgcolor="#FDF5E6" style="border-width:thin;z-index:1"></layer>
<script type="text/javascript">
if (iens6){
document.write("<div id='viewer' style='background-color:#FDF5E6;visibility:hidden;position:absolute;left:0;width:0;height:0;z-index:1;overflow:hidden;border:0px ridge white'></div>")
}
if (ns4){
	hideobj = eval("document.nsviewer")
	hideobj.visibility="hidden"
}
c=0;

function func1(t){

     document.getElementById("zclick").value= t;

     func2(t);

    }

function func2(t){
  //  alert(t);
    if(t.value!=0){

        document.getElementById("cat0").submit();
       // alert("submitted! ");
}

}
</script>
                                           <h2 align="center"  class="headerStyle" style="height: 25px;">Delete MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="header1" style="background-color: cyan;line-height: 26px;font-size: 13px;vertical-align: bottom" >

 
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl0.jsp" style="text-decoration:none" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl1.jsp" style="text-decoration:none" onclick="func1(1)" rel="sb1">1 (1XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl2.jsp"  style="text-decoration:none" onclick="func1(2)" rel="sb2">2 (20X-28X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl3.jsp"  style="text-decoration:none" onclick="func1(3)" rel="sb3">3 (3XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl4.jsp" style="text-decoration:none" onclick="func1(4)" rel="sb4">4 (4XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl5.jsp" style="text-decoration:none" onclick="func1(5)" rel="sb5">5 (5XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl6.jsp" style="text-decoration:none" onclick="func1(6)" rel="sb6">6 (6XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl7.jsp" style="text-decoration:none" onclick="func1(7)" rel="sb7">7 (70X-78X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl8.jsp" style="text-decoration:none" onclick="func1(8)" rel="sb8">8 (80X-88X)</a>&nbsp;|&nbsp;

<a href="<%=request.getContextPath()%>/cataloguing/marchome.do" style="text-decoration:none" rel="home">Cancel</a>&nbsp;|&nbsp;

</div>

<DIV class="tabcontainer ieclass">
<FONT color="#8B008B">
<div id="sb0" class="tabcontent">
Number and Code Fields.
</div>


<div id="sb1" class="tabcontent">
Main Entry Fields.
</div>

<div id="sb2" class="tabcontent">
Title, Edition, Imprints etc Fields .
</div>

<div id="sb3" class="tabcontent">
Physical Description, etc Fields.
</div>


<div id="sb4" class="tabcontent">
Series Statements Field.
</div>


<div id="sb5" class="tabcontent">
Note Fields.
</div>


<div id="sb6" class="tabcontent">
Subject Access Fields.
</div>


<div id="sb7" class="tabcontent">
Added Entry and Linking Entry Fields.
</div>


<div id="sb8" class="tabcontent">
Series added Entry, Holding, Location, Alternate Graphics Fields.
</div>


<div id="sb9" class="tabcontent">
Local Fields 900 and onwards.
</div>

    <div id="home" class="tabcontent">
Go BACK to Manage MARC Bibliography.
</div>
</FONT>
</DIV>

                                      <!-- Marc entries Starts from here . -->
  <%! Biblio marc20=new Biblio();
  Biblio marc22=new Biblio();
  Biblio marc41=new Biblio();
  Biblio marc43=new Biblio();
  Biblio marc82 =new Biblio();   %>
  <%
     if(request.getAttribute("020")!=null){
             marc20=(Biblio)request.getAttribute("020");}
     if(request.getAttribute("022")!=null){
             marc22=(Biblio)request.getAttribute("022");}
     if(request.getAttribute("041")!=null){
             marc41=(Biblio)request.getAttribute("041");}
     if(request.getAttribute("043")!=null){
             marc43=(Biblio)request.getAttribute("043");}
      if(request.getAttribute("082")!=null){
             marc82=(Biblio)request.getAttribute("082");}

     %>
<div>
<html:form styleId="cat0" action="/vdaction.do" method="post">
    <table height="400px"><tr><td valign="top" >&nbsp;&nbsp;&nbsp;
                <table width="100%"  cellspacing="5" >

    <tr><input type="hidden"  name="zclick" id="zclick" /></tr><tr>
    <td>&nbsp;&nbsp;&nbsp;International Standard Book Number (020) : <a href="javascript:animatedcollapse.toggle('020')">ind</a> <div id="020" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" readonly value="<%=marc20.getIndicator1()==null?"":marc20.getIndicator1() %>" name="in0201" maxlength="1" size="1" /> ind2<input type="text" readonly value="<%=marc20.getIndicator2()==null?"":marc20.getIndicator2() %>" name="in0202"  maxlength="1" size="1"  /></div></td>
<td>

    $a ISBN (NR) <input type="text" readonly value="<%=marc20.get$a()==null?"":marc20.get$a() %>" name="z020" id="020" onFocus="setObj(description[1],'override',550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('020c')">$c </a>

<div id="020c" style=" background: #FDF5E6; display:none">
    Term of Availability(NR) <input type="text" readonly value="<%=marc20.get$c()==null?"":marc20.get$c() %>" name="z020c" id="020c" onFocus="setObj(description[2],'override',750,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('020z')">$z </a>
<div id="020z" style=" background: #FDF5E6; display:none">
    Canceled or Invalid number(R) <input type="text" readonly value="<%=marc20.get$z()==null?"":marc20.get$z() %>" name="z020z" id="020z" onFocus="setObj(description[3],'override',750,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>&nbsp;&nbsp;&nbsp;International Stnadard  Serial Number(022) : <a href="javascript:animatedcollapse.toggle('022')">ind</a> <div id="022" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" readonly value="<%=marc22.getIndicator1()==null?"":marc22.getIndicator1() %>" name="in0221" maxlength="1" size="1" /> ind2<input type="text" readonly value="#" name="in0222" maxlength="1" size="1" onFocus="setObj(description[4],'override',550,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
    $a ISSN (NR)<input type="text" readonly value="<%=marc22.get$a()==null?"":marc22.get$a() %>" name="z022" id="022" onFocus="setObj(description[5],'override',550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('022y')">$y</a>
<div id="022y" style=" background: #FDF5E6; display:none">
    Incorrect ISSN(R) <input type="text" readonly value="<%=marc22.get$y()==null?"":marc22.get$y() %>" name="z022y" id="022y" onFocus="setObj(description[6],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('022z')"> $z</a>
<div id="022z" style=" background: #FDF5E6; display:none">
    canceled ISSN(R) <input type="text" readonly value="<%=marc22.get$z()==null?"":marc22.get$z() %>" name="z022z" id="022z" onFocus="setObj(description[7],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr><td>&nbsp;&nbsp;&nbsp;Language Code (NR)(041) : <a href="javascript:animatedcollapse.toggle('041')">ind</a> <div id="041" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" readonly value="<%=marc41.getIndicator1()==null?"":marc41.getIndicator1() %>" maxlength="1" name="in0411" id="041i2" size="1" /> ind2<input type="text" readonly value="<%=marc41.getIndicator2()==null?"":marc41.getIndicator2() %>" maxlength="1" name="in0412" id="041i2" size="1" /></div></td>
<td>
$a Language code of text/sound track or
separate title(NR)<input type="text" readonly value="<%=marc41.get$a()==null?"":marc41.get$a() %>" name="z041" id="041" onFocus="setObj(description[8],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('041b')">$b</a>

<div id="041b" style=" background: #FDF5E6; display:none">
Language code of summary or abstract/overprinted
title or subtitle
(NR) <input type="text" readonly value="<%=marc41.get$b()==null?"":marc41.get$b() %>" name="z041b" id="041b" onFocus="setObj(description[9],'override',750,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('041d')"> $d</a>
<div id="041d" style=" background: #FDF5E6; display:none">
    Language code of sung or spoken text (NR) <input type="text" readonly value="<%=marc41.get$d()==null?"":marc41.get$d() %>" name="z041d" id="041d" onFocus="setObj(description[10],'override',750,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr><td>&nbsp;&nbsp;&nbsp;Geographic Area Code(NR) (043) : <a href="javascript:animatedcollapse.toggle('043')">ind</a> <div id="043" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" readonly value="#" name="in0431" maxlength="1"  size="1" onFocus="setObj(description[11],'override',750,30)" onBlur="clearTimeout(openTimer);stopIt()"  /> ind2<input type="text" readonly value="#" name="in0432" id="in0432" maxlength="1" size="1" onFocus="setObj(description[11],'override',750,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
    <td>$a Geographic Area Code(R)<input type="text" readonly value="<%=marc43.get$a()==null?"":marc43.get$a() %>" name="z043" id="043" onFocus="setObj(description[12],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr><td>&nbsp;&nbsp;&nbsp;Dewey Decimal Classification Number(R)(082) : <a href="javascript:animatedcollapse.toggle('082')">ind</a> <div id="082" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" readonly value="<%=marc82.getIndicator1()==null?"":marc82.getIndicator1() %>" name="in0821" maxlength="1"  size="1" onFocus="setObj(description[13],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" readonly value="<%=marc82.getIndicator2() %>" name="in0822" maxlength="1" size="1" onFocus="setObj(description[14],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
    <td>$a Classification number(R)<input type="text" readonly value="<%=marc82.get$a()==null?"":marc82.get$a() %>" name="z082" id="082" onFocus="setObj(description[15],'override',550,30)" onBlur="clearTimeout(openTimer);stopIt()"  />
<font size="2">
<a href="javascript:animatedcollapse.toggle('082b')">$b</a>
<div id="082b" style=" background: #FDF5E6; display:none">
    Item number (NR) <input type="text" readonly value="<%=marc82.get$b()==null?"":marc82.get$b() %>" name="z082b" id="082b" onFocus="setObj(description[16],'override',550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('0822')"> $2</a>
<div id="0822" style=" background: #FDF5E6; display:none">
    Edition number (NR) <input type="text" readonly value="<%=marc82.get$2()==null?"":marc82.get$2() %>" name="z0822" id="0822" onFocus="setObj(description[17],'override',550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>
    </table> <br/><br> &nbsp;&nbsp;&nbsp;You are on MARC Page : Tag 0
    <br>&nbsp; <br> &nbsp;&nbsp;&nbsp;<%

     if(session.getAttribute("marcbutton").equals("Delete") || session.getAttribute("marcbutton1").equals("Delete")){
    %>
    <a style="text-decoration: none;" href="<%=request.getContextPath() %>/marcdelete.do"><input type="button" value="Delete" /></a>
    <% }
    %>
        </td></tr></table>
</html:form>
     
</div>
        </div>
    </body>
</html>
