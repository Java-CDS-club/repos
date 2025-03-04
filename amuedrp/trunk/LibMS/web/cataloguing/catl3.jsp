<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.myapp.struts.hbm.Biblio"%>

<html>
    <head>
        <title> Bibliographic Cataloguing According to MARC21 -- 3XX</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>



<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/

</script>
<% session.setAttribute("tag3", "1");%>
<% HashMap hm1 = new HashMap();
    Biblio bib1=new Biblio();
    Biblio bib2=new Biblio();
    Biblio bib3=new Biblio();
    Biblio bib4=new Biblio();
    Biblio bib5=new Biblio();
%>
<%
 hm1 = (HashMap)session.getAttribute("hsmp");
if(hm1!=null){
  if(hm1.containsKey("15")){
       bib1=(Biblio)hm1.get("15");
        }
   if(hm1.containsKey("16")){
       bib2=(Biblio)hm1.get("16");
        }
   if(hm1.containsKey("18")){
       bib3=(Biblio)hm1.get("18");
        }
  }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 4) //initialize Tab Menu #3 with 2nd tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('300', 'fade=1,height=30px')
animatedcollapse.addDiv('306', 'fade=1,height=30px')
animatedcollapse.addDiv('336', 'fade=1,height=30px')


animatedcollapse.addDiv('300b', 'fade=1,height=30px')
animatedcollapse.addDiv('300c', 'fade=1,height=30px')
animatedcollapse.addDiv('300e', 'fade=1,height=30px')
animatedcollapse.addDiv('300f', 'fade=1,height=30px')
animatedcollapse.addDiv('3003', 'fade=1,height=30px')
animatedcollapse.addDiv('300g', 'fade=1,height=30px')
animatedcollapse.addDiv('336b', 'fade=1,height=30px')
animatedcollapse.addDiv('3362', 'fade=1,height=30px')
animatedcollapse.addDiv('3363', 'fade=1,height=30px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()

</script>
<script type="text/javascript">
var description=new Array()
description[0]='Both indicator positions are undefined; each contains a blank (#).'
description[1]='Number of physical pages, volumes, cassettes, total playing time, etc. ex- <b>1 score (16 p.)</b>'
description[2]='Physical characteristics such as illustrative matter, coloration, playing speed, groove characteristics, presence and kind of sound etc. ex- ill.'
description[3]='Expressed in centimeters, millimeters, or inches.ex- <b>108 cm. x 34.5 cm. </b>'
description[4]='May include a parenthetical physical description of the accompanying material.ex- <b>1 answer book.</b>'
description[5]='as page, volumes, boxes, cu. ft., linear ft., etc. ex- <b>file drawers</b>. '
description[6]='Size of a type of unit given in the preceding subfield $f.ex- <b>2 x 4 x 3 1/2 ft.</b> '
description[7]='Part of the described materials to which the field applies.ex- <b>dupe neg. </b>'

description[8]='Repeatable to allow the recording of the playing time of two or more parts.ex- <b>002016 [for 20 min., 16 sec.]</b>'
description[9]='Content type of the work being described. ex- <b>performed music</b>.'
description[10]='Code representing the content type of the work being described.ex- <b>txt</b>'
description[11]='MARC code that identifies the source of the term or code used to record the content type information.ex- <b>rdacontent</b>'
description[12]='Part of the described materials to which the field applies. ex- <b>liner notes</b>'
</script>
<script type="text/javascript">
   function loadHelp()
    {
        window.status="Press F10 for Help";

    }

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/tooltip.js">
/***********************************************
* ToolTip layer - (c) muhammad zeeshan
***********************************************/

</script>

    </head>
     <jsp:include page="/admin/header.jsp"></jsp:include>
<body onload="loadHelp()">
    <div
   style="  top:20%;
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


function func1(t){

     document.getElementById("zclick").value= t;

     func2(t);

    }

function func2(t){
  //  alert(t);
    if(t.value!=3){

        document.getElementById("cat3").submit();
  //   alert("submitted! ");
}
}


function statwords(message,x,y)
{
document.onkeypress = keyHit
function keyHit(event) {
  if (event.keyCode == event.DOM_VK_F10) {
     setObj(message,'override',x,y);
   //  JavaScript:window.status=message;

    event.stopPropagation()
    event.preventDefault()
  }
}
}
</script>
                                               <h2 align="center"  class="headerStyle" style="height: 25px;">MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="header1" style="background-color: cyan;line-height: 26px;font-size: 13px;vertical-align: bottom" >

    <a href="<%=request.getContextPath()%>/cataloguing/catlcontrol.jsp" style="text-decoration:none" onclick="func1(10)"  rel="sb10">Control Fields</a><font color="blue">&nbsp;|&nbsp;</font>
<a href="<%=request.getContextPath()%>/cataloguing/catl0.jsp" style="text-decoration:none" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl1.jsp" style="text-decoration:none" onclick="func1(1)" rel="sb1">1 (1XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl2.jsp"  style="text-decoration:none" onclick="func1(2)" rel="sb2">2 (20X-28X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl3.jsp"  style="text-decoration:none" onclick="func1(3)" rel="sb3">3 (3XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl4.jsp" style="text-decoration:none" onclick="func1(4)" rel="sb4">4 (4XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl5.jsp" style="text-decoration:none" onclick="func1(5)" rel="sb5">5 (5XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl6.jsp" style="text-decoration:none" onclick="func1(6)" rel="sb6">6 (6XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl7.jsp" style="text-decoration:none" onclick="func1(7)" rel="sb7">7 (70X-78X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl8.jsp" style="text-decoration:none" onclick="func1(8)" rel="sb8">8 (80X-88X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl9.jsp" style="text-decoration:none" onclick="func1(9)" rel="sb9">9</a>&nbsp;|&nbsp;
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
    <div id="sb10" class="tabcontent">
Control Field Entry
</div>
</FONT>
</DIV>
You are on MARC Page : 3 Tag Page
                                      <!-- Marc entries Starts from here . -->

<div>
     <html:form styleId="cat3" action="/cataction3.do" method="post">
           <table height="400px"><tr><td valign="top" >&nbsp;&nbsp;&nbsp;
<table width="100%" cellspacing="5" >
    <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
<tr><td>&nbsp;&nbsp;&nbsp;Physical Description (R)(300) : <a href="javascript:animatedcollapse.toggle('300')">ind</a> <div id="300" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="<% if(bib1.getIndicator1()!=null){%><%= bib1.getIndicator1() %><%}%>" name="in3001"  maxlength="1" size="1" onFocus="statwords(description[0],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in3002" value="<% if(bib1.getIndicator2()!=null){%><%= bib1.getIndicator2() %><%}%>"  maxlength="1" size="1" onFocus="statwords(description[0],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Extent (R) <input type="text" value="<% if(bib1.get$a()!=null){%><%= bib1.get$a() %><%}%>" name="z300a" id="300a" onFocus="statwords(description[1],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('300b')">$b </a>

<div id="300b" style="width: 400px; background: #FDF5E6; display:none">
Other physical details (NR) <input type="text" value="<% if(bib1.get$b()!=null){%><%= bib1.get$b() %><%}%>" name="z300b" id="300b" onFocus="statwords(description[2],900,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('300c')">$c </a>
<div id="300c" style="width: 400px; background: #FDF5E6; display:none">
Dimensions (R) <input type="text" value="<% if(bib1.get$c()!=null){%><%= bib1.get$c() %><%}%>" name="z300c" id="300c" onFocus="statwords(description[3],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('300e')">$e </a>
<div id="300e" style="width: 400px; background: #FDF5E6; display:none">
Accompanying material (NR) <input type="text" value="<% if(bib1.get$e()!=null){%><%= bib1.get$e() %><%}%>" name="z300e" id="300e" onFocus="statwords(description[4],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('300f')">$f </a>

<div id="300f" style="width: 400px; background: #FDF5E6; display:none">
Type of unit (R) <input type="text" value="<% if(bib1.get$f()!=null){%><%= bib1.get$f() %><%}%>" name="z300f" id="300f" onFocus="statwords(description[5],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('300g')">$g </a>
<div id="300g" style="width: 400px; background: #FDF5E6; display:none">
Size of unit (R) <input type="text" value="<% if(bib1.get$g()!=null){%><%= bib1.get$g() %><%}%>" name="z300g" id="300g" onFocus="statwords(description[6],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('3003')">$3 </a>
<div id="3003" style="width: 400px; background: #FDF5E6; display:none">
Materials specified (NR)  <input type="text" value="<% if(bib1.get$3()!=null){%><%= bib1.get$3() %><%}%>" name="z3003" id="3003" onFocus="statwords(description[7],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>&nbsp;&nbsp;&nbsp;Playing Time (NR)(306) : <a href="javascript:animatedcollapse.toggle('306')">ind</a> <div id="306" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" name="in3061" value="<% if(bib2.getIndicator1()!=null){%><%= bib2.getIndicator1() %><%}%>"  maxlength="1" size="1" onFocus="statwords(description[0],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text"  value="<% if(bib2.getIndicator2()!=null){%><%= bib2.getIndicator2() %><%}%>" name="in3602" maxlength="1" size="1" onFocus="statwords(description[0],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Playing time (R)<input type="text" value="<% if(bib2.get$a()!=null){%><%= bib2.get$a() %><%}%>" name="z306a" id="306a" onFocus="statwords(description[8],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>&nbsp;&nbsp;&nbsp;Content Type (R)(336) : <a href="javascript:animatedcollapse.toggle('336')">ind</a> <div id="336" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="<% if(bib3.getIndicator1()!=null){%><%= bib3.getIndicator1() %><%}%>" name="in3361"  maxlength="1" size="1" onFocus="statwords(description[0],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<% if(bib3.getIndicator2()!=null){%><%= bib3.getIndicator2() %><%}%>" name="in3362" maxlength="1" size="1" onFocus="statwords(description[0],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Content type term (R)<input type="text" value="<% if(bib3.get$a()!=null){%><%= bib3.get$a() %><%}%>" name="z336a" id="336a" onFocus="statwords(description[9],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />

<a href="javascript:animatedcollapse.toggle('336b')">$b </a>

<div id="336b" style="width: 400px; background: #FDF5E6; display:none">
Content type code (R)<input type="text" value="<% if(bib3.get$b()!=null){%><%= bib3.get$b() %><%}%>" name="z336b" id="336b" onFocus="statwords(description[10],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('3362')">$2 </a>

<div id="3362" style="width: 400px; background: #FDF5E6; display:none" >
Source (NR)<input type="text" value="<% if(bib3.get$2()!=null){%><%= bib3.get$2() %><%}%>" name="z3362" id="3362" onFocus="statwords(description[11],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('3363')">$3 </a>

<div id="3363" style="width: 400px; background: #FDF5E6; display:none">
Materials specified (NR)<input type="text" value="<% if(bib3.get$3()!=null){%><%= bib3.get$3() %><%}%>" name="z3363" id="3363" onFocus="statwords(description[12],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div></td> </tr>

           </table></td></tr></table></html:form>
</div></div>
    </body>
</html>
