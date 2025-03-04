<%@page import="java.util.*,com.myapp.struts.*,com.myapp.struts.AdminDAO.*,com.myapp.struts.hbm.*"%>
 <jsp:include page="header.jsp" flush="true" />

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
String staff_id=(String)request.getAttribute("new_staff_id");
String library_id = (String)session.getAttribute("library_id");

String sublibrary_id=(String)request.getAttribute("staff_sub_library");
LoginDAO logindao=new LoginDAO();

Login staffobj=logindao.searchStaffLogin(staff_id, library_id,sublibrary_id);
String staff_role="";
if(staffobj!=null)
   staff_role = staffobj.getRole();

System.out.println("staff_role="+staff_role+"staff_id="+staff_id);
String button=(String)request.getAttribute("button");

if(button==null||staff_id!=null)
    {
button="Assign Privilege";
staff_id=request.getParameter("staff_id");
System.out.println("*****"+staff_id);
   }
String staff_name=(String)request.getAttribute("new_staff_name");


%>

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

<html>


    

    <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

        <p class="mess" style="font-size:14px;"><b>Click corresponding checkboxes and Submit to assign privileges to:&nbsp;</b> <b style="color:brown"><%=staff_name%></b></p>

	<!-- dhtmlXTree.css -->
	<style  rel="STYLESHEET" type="text/css" >
.defaultTreeTable{
			margin : 0;
			padding : 0;
			border : 0;
}
.containerTableStyle { overflow : auto;}
.standartTreeRow{	font-family : Verdana, Geneva, Arial, Helvetica, sans-serif; 	font-size : 12px; -moz-user-select: none; }
.selectedTreeRow{ background-color : navy; color:white; font-family : Verdana, Geneva, Arial, Helvetica, sans-serif; 		font-size : 12px;  -moz-user-select: none;  }
.standartTreeImage{ width:18px; height:18px;  overflow:hidden; border:0; padding:0; margin:0; }
.hiddenRow { width:1px;   overflow:hidden;  }
.dragSpanDiv{ 	font-size : 12px; 	border: thin solid 1 1 1 1; }
	
</style>
	<!-- dhtmlXCommon.js -->
	<script>
 
function dtmlXMLLoaderObject(funcObject,dhtmlObject){
 this.xmlDoc="";
 this.onloadAction=funcObject||null;
 this.mainObject=dhtmlObject||null;
 return this;
};
 
 dtmlXMLLoaderObject.prototype.waitLoadFunction=function(dhtmlObject){
 this.check=function(){
 if(!dhtmlObject.xmlDoc.readyState)dhtmlObject.onloadAction(dhtmlObject.mainObject);
 else{
 if(dhtmlObject.xmlDoc.readyState != 4)return false;
 else dhtmlObject.onloadAction(dhtmlObject.mainObject);}
};
 return this.check;
};
 
 
 dtmlXMLLoaderObject.prototype.getXMLTopNode=function(tagName){
 if(this.xmlDoc.responseXML){var temp=this.xmlDoc.responseXML.getElementsByTagName(tagName);var z=temp[0];}
 else var z=this.xmlDoc.documentElement;
 if(z)return z;
 alert("Incorrect XML");
 return document.createElement("DIV");
};
 
 
 dtmlXMLLoaderObject.prototype.loadXMLString=function(xmlString){
 try 
{
 var parser = new DOMParser();
 this.xmlDoc = parser.parseFromString(xmlString,"text/xml");
}
 catch(e){
 this.xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
 this.xmlDoc.loadXML(xmlString);
}
 this.onloadAction(this.mainObject);
}
 dtmlXMLLoaderObject.prototype.loadXML=function(filePath){
 try 
{
 this.xmlDoc = new XMLHttpRequest();
 this.xmlDoc.open("GET",filePath,true);
 this.xmlDoc.onreadystatechange=new this.waitLoadFunction(this);
 this.xmlDoc.send(null);
}
 catch(e){

 if(document.implementation && document.implementation.createDocument)
{
 this.xmlDoc = document.implementation.createDocument("","",null);
 this.xmlDoc.onload = new this.waitLoadFunction(this);
}
 else
{
 this.xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
 this.xmlDoc.async="true";
 this.xmlDoc.onreadystatechange=new this.waitLoadFunction(this);
}
 this.xmlDoc.load(filePath);
}
};
 
 
function callerFunction(funcObject,dhtmlObject){
 this.handler=function(e){
 if(!e)e=event;
 funcObject(e,dhtmlObject);
 return true;
};
 return this.handler;
};

 
function getAbsoluteLeft(htmlObject){
 var xPos = htmlObject.offsetLeft;
 var temp = htmlObject.offsetParent;
 while(temp != null){
 xPos+= temp.offsetLeft;
 temp = temp.offsetParent;
}
 return xPos;
}
 
function getAbsoluteTop(htmlObject){
 var yPos = htmlObject.offsetTop;
 var temp = htmlObject.offsetParent;
 while(temp != null){
 yPos+= temp.offsetTop;
 temp = temp.offsetParent;
}
 return yPos;
}
 
 
 
function convertStringToBoolean(inputString){if(typeof(inputString)=="string")inputString=inputString.toLowerCase();
 switch(inputString){
 case "1":
 case "true":
 case "yes":
 case "y":
 case 1: 
 case true: 
 return true;
 break;
 default: return false;
}
}

 
function getUrlSymbol(str){
 if(str.indexOf("?")!=-1)
 return "&"
 else
 return "?"
}
 
 
function dhtmlDragAndDropObject(){
 this.lastLanding=0;
 this.dragNode=0;
 this.dragStartNode=0;
 this.dragStartObject=0;
 this.tempDOMU=null;
 this.tempDOMM=null;
 this.waitDrag=0;
 if(window.dhtmlDragAndDrop)return window.dhtmlDragAndDrop;
 window.dhtmlDragAndDrop=this;
 return this;
};
 
 dhtmlDragAndDropObject.prototype.removeDraggableItem=function(htmlNode){
 htmlNode.onmousedown=null;
 htmlNode.dragStarter=null;
 htmlNode.dragLanding=null;
}
 dhtmlDragAndDropObject.prototype.addDraggableItem=function(htmlNode,dhtmlObject){
 htmlNode.onmousedown=this.preCreateDragCopy;
 htmlNode.dragStarter=dhtmlObject;
 this.addDragLanding(htmlNode,dhtmlObject);
}
 dhtmlDragAndDropObject.prototype.addDragLanding=function(htmlNode,dhtmlObject){
 htmlNode.dragLanding=dhtmlObject;
}
 dhtmlDragAndDropObject.prototype.preCreateDragCopy=function(e)
{
 if(window.dhtmlDragAndDrop.waitDrag){
 window.dhtmlDragAndDrop.waitDrag=0;
 document.body.onmouseup=window.dhtmlDragAndDrop.tempDOMU;
 document.body.onmousemove=window.dhtmlDragAndDrop.tempDOMM;
 return;
}
 
 window.dhtmlDragAndDrop.waitDrag=1;
 window.dhtmlDragAndDrop.tempDOMU=document.body.onmouseup;
 window.dhtmlDragAndDrop.tempDOMM=document.body.onmousemove;
 window.dhtmlDragAndDrop.dragStartNode=this;
 window.dhtmlDragAndDrop.dragStartObject=this.dragStarter;
 document.body.onmouseup=window.dhtmlDragAndDrop.preCreateDragCopy;
 document.body.onmousemove=window.dhtmlDragAndDrop.callDrag;
};
 dhtmlDragAndDropObject.prototype.callDrag=function(e){
 if(!e)e=window.event;
 dragger=window.dhtmlDragAndDrop;
 if(!dragger.dragNode){
 dragger.dragNode=dragger.dragStartObject._createDragNode(dragger.dragStartNode);
 document.body.appendChild(dragger.dragNode);
 document.body.onmouseup=dragger.stopDrag;
 dragger.waitDrag=0;
}

 dragger.dragNode.style.left=e.clientX+15+document.body.scrollLeft;dragger.dragNode.style.top=e.clientY+3+document.body.scrollTop;

 if(!e.srcElement)var z=e.target;else z=e.srcElement;
 dragger.checkLanding(z);
}
 dhtmlDragAndDropObject.prototype.checkLanding=function(htmlObject){
 if(htmlObject.dragLanding){if(this.lastLanding)this.lastLanding.dragLanding._dragOut(this.lastLanding);
 this.lastLanding=htmlObject;this.lastLanding=this.lastLanding.dragLanding._dragIn(this.lastLanding,this.dragStartNode);}
 else{
 if(htmlObject.tagName!="BODY")this.checkLanding(htmlObject.parentNode);
 else{if(this.lastLanding)this.lastLanding.dragLanding._dragOut(this.lastLanding);this.lastLanding=0;}
}
}
 dhtmlDragAndDropObject.prototype.stopDrag=function(e){
 dragger=window.dhtmlDragAndDrop;
 if(dragger.lastLanding)dragger.lastLanding.dragLanding._drag(dragger.dragStartNode,dragger.dragStartObject,dragger.lastLanding);
 dragger.lastLanding=0;
 dragger.dragNode.parentNode.removeChild(dragger.dragNode);
 dragger.dragNode=0;
 dragger.dragStartNode=0;
 dragger.dragStartObject=0;
 document.body.onmouseup=dragger.tempDOMU;
 document.body.onmousemove=dragger.tempDOMM;
 dragger.tempDOMU=null;
 dragger.tempDOMM=null;
 dragger.waitDrag=0;
}
	
	</script>
	<!-- dhtmlXTree.js -->
	<script>

function newXMLHttpRequest() {
var xmlreq = false;
// Create XMLHttpRequest object in non-Microsoft browsers
if (window.XMLHttpRequest) {
xmlreq = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
// Try to create XMLHttpRequest in later versions
// of Internet Explorer
xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e1) {
// Failed to create required ActiveXObject
try {
// Try version supported by older versions
// of Internet Explorer
xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
} catch (e2) {
// Unable to create an XMLHttpRequest by any means
xmlreq = false;
}
}
}
return xmlreq;
}
/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
// Return an anonymous function that listens to the XMLHttpRequest instance
return function () {
// If the request's status is "complete"
if (req.readyState == 4) {
// Check that we received a successful response from the server
if (req.status == 200) {
// Pass the XML payload of the response to the handler function.
responseXmlHandler(req.responseXML);
} else {
// An HTTP problem has occurred
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}
function search(id,name1) {

var t;
var x=parseInt(id)
switch(x){

   case 100:
      t="<%=resource.getString("admin.header.main3")%>"
      break
   case 101:
      t="<%=resource.getString("admin.header.acquisition71")%>"
      break
   case 102:
      t="<%=resource.getString("admin.header.acquisition16")%>"
      break
   case 103:
      t="<%=resource.getString("admin.header.acquisition17")%>"
      break
   case 104:
      t="<%=resource.getString("admin.header.acquisition18")%>"
      break
   case 105:
      t="<%=resource.getString("admin.header.acquisition19")%>"
      break
   case 106:
      t="<%=resource.getString("admin.header.acquisition72")%>"
      break
   case 107:
      t="<%=resource.getString("admin.header.acquisition20")%>"
      break
   case 108:
      t="<%=resource.getString("admin.header.acquisition21")%>"
      break
   case 109:
      t="<%=resource.getString("admin.header.acquisition22")%>"
      break
   case 110:
      t="<%=resource.getString("admin.header.acquisition1")%>"
      break
   case 111:
      t="<%=resource.getString("admin.header.acquisition2")%>"
      break
   case 112:
      t="<%=resource.getString("admin.header.acquisition23") %>"
      break
    case 113:
      t="<%=resource.getString("admin.header.acquisition3") %>"
      break
     case 114:
      t="<%=resource.getString("admin.header.acquisition2")%>"
      break
      case 115:
      t="<%=resource.getString("admin.header.acquisition24") %>"
      break
      case 116:
      t="<%=resource.getString("admin.header.acquisition5") %>"
      break
      case 117:
      t="<%=resource.getString("admin.header.acquisition6") %>"
      break
      case 118:
      t="<%=resource.getString("admin.header.acquisition7") %>"
      break
      case 119:
      t="<%=resource.getString("admin.header.acquisition8") %>"
      break
      case 120:
      t="<%=resource.getString("admin.header.acquisition9") %>"
      break
      case 121:
      t="<%=resource.getString("admin.header.acquisition73") %>"
      break
      case 122:
      t="<%=resource.getString("admin.header.acquisition25") %>"
      break
      case 123:
      t="<%=resource.getString("admin.header.acquisition1") %>"
      break
      case 124:
      t="<%=resource.getString("admin.header.acquisition11") %> "
      break
      case 125:
      t="<%=resource.getString("admin.header.acquisition26") %>"
      break
      case 126:
      t="<%=resource.getString("admin.header.acquisition27") %>"
      break
      case 127:
      t="<%=resource.getString("admin.header.acquisition28") %>"
      break

      case 128:
      t="<%=resource.getString("admin.header.acquisition29") %>"
      break
      case 129:
      t="<%=resource.getString("admin.header.acquisition74") %>"
      break
      case 130:
      t="<%=resource.getString("admin.header.acquisition75")%>"
      break
      case 131:
      t="<%=resource.getString("admin.header.acquisition30") %>"
      break
      case 132:
      t="<%=resource.getString("admin.header.acquisition31") %>"
      break
      case 133:
      t="<%=resource.getString("admin.header.acquisition32") %>"
      break
      case 134:
      t="<%=resource.getString("admin.header.acquisition33") %>"
      break
      case 135:
      t="<%=resource.getString("admin.header.acquisition12") %>"
      break
      case 136:
      t="<%=resource.getString("admin.header.acquisition13") %>"
      break
      case 137:
      t="<%=resource.getString("admin.header.acquisition14")%>"
      break
      case 138:
      t="<%=resource.getString("admin.header.acquisition15")%>"
      break
      case 139:
      t="<%=resource.getString("admin.header.acquisition76") %>"
      break
      case 140:
      t="<%=resource.getString("admin.header.acquisition34") %>"
      break
      case 141:
      t="<%=resource.getString("admin.header.acquisition35") %>"
      break
      case 142:
      t="<%=resource.getString("admin.header.acquisition77") %>"
      break
      case 143:
      t="<%=resource.getString("admin.header.acquisition78") %>"
      break
      
      case 144:
      t="<%=resource.getString("admin.header.acquisition79")%>"
      break
      case 145:
      t="<%=resource.getString("admin.header.acquisition36") %>"
      break
      case 146:
      t="<%=resource.getString("admin.header.acquisition37")%>"
      break
      case 147:
      t="<%=resource.getString("admin.header.acquisition38")%>"
      break
      case 148:
      t="<%=resource.getString("admin.header.acquisition80") %>"
      break
      case 149:
      t="<%=resource.getString("admin.header.acquisition39") %>"
      break
      case 150:
      t="<%=resource.getString("admin.header.acquisition40")%>"
      break
      case 151:
      t="<%=resource.getString("admin.header.acquisition41")%>"
      break
      case 152:
      t="<%=resource.getString("admin.header.acquisition42") %>"
      break
      case 153:
      t="<%=resource.getString("admin.header.acquisition43") %>"
      break
      case 154:
      t="<%=resource.getString("admin.header.acquisition44") %>"
      break
      case 155:
      t="<%=resource.getString("admin.header.acquisition45") %>"
      break
      case 156:
      t="<%=resource.getString("admin.header.acquisition46") %>"
      break

      case 157:
      t="<%=resource.getString("admin.header.acquisition47")%>"
      break
      case 158:
      t="<%=resource.getString("admin.header.acquisition48") %>"
      break
      case 159:
      t="<%=resource.getString("admin.header.acquisition49")%>"
      break
      case 160:
      t="<%=resource.getString("admin.header.acquisition50")%>"
      break
      case 161:
      t="<%=resource.getString("admin.header.acquisition51") %>"
      break
      case 162:
      t="<%=resource.getString("admin.header.acquisition52") %>"
      break
      case 163:
      t="<%=resource.getString("admin.header.acquisition53")%>"
      break
      case 164:
      t="<%=resource.getString("admin.header.acquisition54")%>"
      break
          case 165:
      t="<%=resource.getString("admin.header.acquisition55") %>"
      break
      case 166:
      t="<%=resource.getString("admin.header.acquisition56") %>"
      break
      case 167:
      t="<%=resource.getString("admin.header.acquisition57") %>"
      break

      case 168:
      t="<%=resource.getString("admin.header.acquisition58")%>"
      break
      case 169:
      t="<%=resource.getString("admin.header.acquisition59") %>"
      break
      case 170:
      t="<%=resource.getString("admin.header.acquisition60")%>"
      break
      case 171:
      t="<%=resource.getString("admin.header.acquisition61")%>"
      break
      case 172:
      t="<%=resource.getString("admin.header.acquisition62") %>"
      break
      case 173:
      t="<%=resource.getString("admin.header.acquisition63") %>"
      break
      case 174:
      t="<%=resource.getString("admin.header.acquisition81") %>"
      break
      case 175:
      t="<%=resource.getString("admin.header.acquisition64") %>"
      break
       case 176:
      t="<%=resource.getString("admin.header.acquisition65") %>"
      break

      case 177:
      t="<%=resource.getString("admin.header.acquisition82") %>"
      break
      case 178:
      t="<%=resource.getString("admin.header.acquisition66") %>"
      break
      case 179:
      t="<%=resource.getString("admin.header.acquisition84")%>"
      break
      case 180:
      t="<%=resource.getString("admin.header.acquisition68") %>"
      break
      case 181:
      t="<%=resource.getString("admin.header.acquisition85")%>"
      break
          case 182:
      t="<%=resource.getString("admin.header.acquisition69") %>"
      break
      case 183:
      t="<%=resource.getString("admin.header.acquisition83") %>"
      break
      case 184:
      t="<%=resource.getString("admin.header.acquisition70") %>"
      break
       case 200:
      t="<%=resource.getString("admin.header.main4") %>"
      break
      case 201:
      t="<%=resource.getString("admin.header.cataloguing42") %>"
      break
       case 202:
      t="<%=resource.getString("admin.header.cataloguing8") %>"
      break
       case 203:
      t="<%=resource.getString("admin.header.cataloguing9") %>"
      break
       case 204:
      t="<%=resource.getString("admin.header.cataloguing37") %>"
      break
       case 205:
      t="<%=resource.getString("admin.header.cataloguing41") %>"
      break
     case 206:
      t="<%=resource.getString("admin.header.cataloguing42") %>"
      break
      case 207:
      t="<%=resource.getString("admin.header.cataloguing43") %>"
      break
      case 208:
      t="<%=resource.getString("admin.header.cataloguing14") %>"
      break
      case 209:
      t="<%=resource.getString("admin.header.cataloguing17") %>"
      break
      case 210:
      t="<%=resource.getString("admin.header.cataloguing31") %>"
      break
      case 211:
      t="<%=resource.getString("admin.header.cataloguing10") %>"
      break
      case 212:
      t="<%=resource.getString("admin.header.cataloguing32") %>"
      break
       case 213:
      t="<%=resource.getString("admin.header.cataloguing38") %>"
      break
      case 214:
      t="<%=resource.getString("admin.header.cataloguing39") %>"
      break
      case 215:
      t="<%=resource.getString("admin.header.cataloguing33") %>"
      break
       case 216:
      t="<%=resource.getString("admin.header.cataloguing21") %>"
      break
       case 217:
      t="<%=resource.getString("admin.header.cataloguing22") %>"
      break
       case 218:
      t="<%=resource.getString("admin.header.cataloguing23") %>"
      break
      case 219:
      t="<%=resource.getString("admin.header.cataloguing24") %>"
      break
       case 220:
      t="<%=resource.getString("admin.header.cataloguing25") %>"
      break
      case 221:
      t="<%=resource.getString("admin.header.cataloguing26") %>"
      break
       case 222:
      t="<%=resource.getString("admin.header.cataloguing27") %>"
      break
       case 223:
      t="<%=resource.getString("admin.header.cataloguing34") %>"
      break
       case 224:
      t="<%=resource.getString("admin.header.cataloguing35") %>"
      break

      
       case 225:
      t="<%=resource.getString("admin.header.cataloguing13") %>"
      break
      case 226:
      t="<%=resource.getString("admin.header.cataloguing30") %>"
      break


      case 300:
      t="<%=resource.getString("admin.header.main5") %>"
      break
      case 301:
      t="<%=resource.getString("admin.header.circulation85") %>"
      break
       case 302:
      t="<%=resource.getString("admin.header.circulation32") %>"
      break
       case 303:
      t="<%=resource.getString("admin.header.circulation1") %>"
      break
       case 304:
      t="<%=resource.getString("admin.header.circulation2") %>"
      break
       case 305:
      t="<%=resource.getString("admin.header.circulation3") %>"
      break
       case 306:
      t="<%=resource.getString("admin.header.circulation4") %>"
      break
       case 307:
      t="<%=resource.getString("admin.header.circulation5") %>"
      break
       case 308:
      t=" <%=resource.getString("admin.header.circulation33") %>"
      break
       case 309:
      t="<%=resource.getString("admin.header.circulation6") %>"
      break
       case 310:
      t="<%=resource.getString("admin.header.circulation7") %>"
      break
       case 311:
      t="<%=resource.getString("admin.header.circulation8") %>"
      break
       case 312:
      t="<%=resource.getString("admin.header.circulation9") %>"
      break
       case 313:
      t="<%=resource.getString("admin.header.circulation10") %>"
      break
       case 314:
      t="<%=resource.getString("admin.header.circulation34") %>"
      break
       case 315:
      t="<%=resource.getString("admin.header.circulation35") %>"
      break
       case 316:
      t="<%=resource.getString("admin.header.circulation36") %>"
      break
       case 317:
      t="<%=resource.getString("admin.header.circulation37") %>"
      break
       case 318:
      t="<%=resource.getString("admin.header.circulation38") %>"
      break
       case 319:
      t="<%=resource.getString("admin.header.circulation39") %>"
      break
       case 320:
      t=" <%=resource.getString("admin.header.circulation40") %>"
      break
       case 321:
      t="<%=resource.getString("admin.header.circulation41") %>"
      break
       case 322:
      t="<%=resource.getString("admin.header.circulation11") %>"
      break
       case 323:
      t="<%=resource.getString("admin.header.circulation12") %>"
      break
       case 324:
      t="<%=resource.getString("admin.header.circulation13") %>"
      break
       case 325:
      t="<%=resource.getString("admin.header.circulation14") %>"
      break
       case 326:
      t="<%=resource.getString("admin.header.circulation42") %>"
      break
       case 327:
      t="<%=resource.getString("admin.header.circulation86") %>"
      break
       case 328:
      t="<%=resource.getString("admin.header.circulation87") %>"
      break
       case 329:
      t="<%=resource.getString("admin.header.circulation43") %>"
      break
       case 330:
      t="<%=resource.getString("admin.header.circulation44") %>"
      break
       case 331:
      t=" <%=resource.getString("admin.header.circulation45") %>"
      break
       case 332:
      t="<%=resource.getString("admin.header.circulation46") %>"
      break
       case 333:
      t="<%=resource.getString("admin.header.circulation88") %>"
      break
       case 334:
      t="<%=resource.getString("admin.header.circulation89") %>"
      break
       case 335:
      t="<%=resource.getString("admin.header.circulation90") %>"
      break
       case 336:
      t="<%=resource.getString("admin.header.circulation47") %>"
      break
      case 337:
      t="<%=resource.getString("admin.header.circulation48") %>"
      break
       case 338:
      t="<%=resource.getString("admin.header.circulation49") %>"
      break
       case 339:
      t="<%=resource.getString("admin.header.circulation91") %>"
      break
       case 340:
      t="<%=resource.getString("admin.header.circulation50") %>"
      break
       case 341:
      t="<%=resource.getString("admin.header.circulation51") %>"
      break
      case 342:
      t="<%=resource.getString("admin.header.circulation52") %>"
      break
       case 343:
      t="<%=resource.getString("admin.header.circulation53") %>"
      break
       case 344:
      t="<%=resource.getString("admin.header.circulation54") %>"
      break
       case 334:
      t="<%=resource.getString("admin.header.circulation89") %>"
      break
       case 345:
      t="<%=resource.getString("admin.header.circulation92") %>"
      break
       case 346:
      t="<%=resource.getString("admin.header.circulation55") %>"
      break
      case 347:
      t="<%=resource.getString("admin.header.circulation56") %>"
      break
       case 348:
      t="<%=resource.getString("admin.header.circulation57") %>"
      break
       case 349:
      t="<%=resource.getString("admin.header.circulation58") %>"
      break
       case 350:
      t="<%=resource.getString("admin.header.circulation59") %>"
      break
       case 351:
      t="<%=resource.getString("admin.header.circulation93") %>"
      break
      case 352:
      t="<%=resource.getString("admin.header.circulation60") %>"
      break
       case 353:
      t="<%=resource.getString("admin.header.circulation61") %>"
      break
       case 354:
      t="<%=resource.getString("admin.header.circulation62") %>"
      break
      case 355:
      t="<%=resource.getString("admin.header.circulation94") %>"
      break
       case 356:
      t="<%=resource.getString("admin.header.circulation63") %>"
      break
       case 357:
      t="<%=resource.getString("admin.header.circulation64") %>"
      break
       case 358:
      t="<%=resource.getString("admin.header.circulation65") %>"
      break
       case 359:
      t="<%=resource.getString("admin.header.circulation66") %>"
      break
       case 360:
      t="<%=resource.getString("admin.header.circulation57") %>"
      break
      case 361:
      t="<%=resource.getString("admin.header.circulation95") %>"
      break
       case 362:
      t="<%=resource.getString("admin.header.circulation68") %>"
      break
       case 363:
      t="<%=resource.getString("admin.header.circulation69") %>"
      break
       case 364:
      t="<%=resource.getString("admin.header.circulation70") %>"
      break
       case 365:
      t="<%=resource.getString("admin.header.circulation15") %> "
      break
      case 366:
      t="<%=resource.getString("admin.header.circulation16") %>"
      break
       case 367:
      t="<%=resource.getString("admin.header.circulation17") %>"
      break
       case 368:
      t="<%=resource.getString("admin.header.circulation71") %>"
      break
       case 369:
      t="<%=resource.getString("admin.header.circulation72") %>"
      break
      case 370:
      t=" <%=resource.getString("admin.header.circulation96") %>"
      break
      case 371:
      t="<%=resource.getString("admin.header.circulation73") %>"
      break
       case 372:
      t="<%=resource.getString("admin.header.circulation18") %>"
      break
       case 373:
      t="<%=resource.getString("admin.header.circulation19") %>"
      break
       case 374:
      t="<%=resource.getString("admin.header.circulation20") %>"
      break
       case 375:
      t="<%=resource.getString("admin.header.circulation74") %> "
      break
      case 376:
      t="<%=resource.getString("admin.header.circulation75") %>"
      break
       case 377:
      t="<%=resource.getString("admin.header.circulation76") %>"
      break
       case 378:
      t="<%=resource.getString("admin.header.circulation77") %>"
      break
       case 379:
      t="<%=resource.getString("admin.header.circulation78") %>"
      break
      case 380:
      t=" <%=resource.getString("admin.header.circulation97") %>"
      break
      case 381:
      t="<%=resource.getString("admin.header.circulation79") %>"
      break
       case 382:
      t="<%=resource.getString("admin.header.circulation80") %>"
      break
       case 383:
      t="<%=resource.getString("admin.header.circulation21") %>"
      break
       case 384:
      t="<%=resource.getString("admin.header.circulation22") %>"
      break
       case 385:
      t="<%=resource.getString("admin.header.circulation98") %> "
      break
      case 386:
      t="<%=resource.getString("admin.header.circulation81") %>"
      break
       case 387:
      t="<%=resource.getString("admin.header.circulation23") %>"
      break
       case 388:
      t="<%=resource.getString("admin.header.circulation24") %>"
      break
       case 389:
      t="<%=resource.getString("admin.header.circulation25") %>"
      break
      case 390:
      t=" <%=resource.getString("admin.header.circulation82") %>"
      break
      case 391:
      t="<%=resource.getString("admin.header.circulation26") %>"
      break
       case 392:
      t="<%=resource.getString("admin.header.circulation27") %>"
      break
       case 393:
      t="<%=resource.getString("admin.header.circulation83") %>"
      break
       case 394:
      t="<%=resource.getString("admin.header.circulation28") %>"
      break
       case 395:
      t="<%=resource.getString("admin.header.circulation29") %> "
      break
      case 396:
      t="<%=resource.getString("admin.header.circulation84") %>"
      break
       case 397:
      t="<%=resource.getString("admin.header.circulation30") %>"
      break
       case 398:
      t="<%=resource.getString("admin.header.circulation31") %>"
      break
       case 399:
      t="<%=resource.getString("admin.header.circulation99") %>"
      break
      case 400:
      t="<%=resource.getString("admin.header.main6") %>"
      break
       case 401:
      t="<%=resource.getString("admin.header.serial70") %>"
      break
       case 402:
      t="<%=resource.getString("admin.header.serial33") %>"
      break
       case 403:
      t="<%=resource.getString("admin.header.serial34") %>"
      break
       case 404:
      t="<%=resource.getString("admin.header.serial35") %>"
      break
       case 405:
      t="<%=resource.getString("admin.header.serial36") %>"
      break
       case 406:
      t="<%=resource.getString("admin.header.serial37") %>"
      break
       case 407:
      t="<%=resource.getString("admin.header.serial71") %>"
      break
       case 408:
      t="<%=resource.getString("admin.header.serial39") %>"
      break
         case 409:
      t="<%=resource.getString("admin.header.serial40") %>"
      break
       case 410:
      t="<%=resource.getString("admin.header.serial41") %>"
      break
       case 411:
      t="<%=resource.getString("admin.header.serial2") %>"
      break
       case 412:
      t="<%=resource.getString("admin.header.serial3") %>"
      break
       case 413:
      t="<%=resource.getString("admin.header.serial42") %>"
      break
       case 414:
      t="<%=resource.getString("admin.header.serial5") %>"
      break
       case 415:
      t="<%=resource.getString("admin.header.serial6") %>"
      break
       case 416:
      t="<%=resource.getString("admin.header.serial43") %>"
      break
       case 417:
      t="<%=resource.getString("admin.header.serial72") %>"
      break
         case 418:
      t="<%=resource.getString("admin.header.serial45") %>"
      break
       case 419:
      t="<%=resource.getString("admin.header.serial8") %>"
      break
       case 420:
      t="<%=resource.getString("admin.header.serial3") %>"
      break
       case 421:
      t="<%=resource.getString("admin.header.serial46") %>"
      break
       case 422:
      t="<%=resource.getString("admin.header.serial47") %>"
      break
       case 423:
      t="<%=resource.getString("admin.header.serial48") %>"
      break
       case 424:
      t="<%=resource.getString("admin.header.serial49") %>"
      break
       case 425:
      t="<%=resource.getString("admin.header.serial50") %>"
      break
       case 426:
      t="<%=resource.getString("admin.header.serial51") %>"
      break
         case 427:
      t="<%=resource.getString("admin.header.serial73") %>"
      break
       case 428:
      t="<%=resource.getString("admin.header.serial74") %>"
      break
       case 429:
      t="<%=resource.getString("admin.header.serial53") %>"
      break
       case 430:
      t="<%=resource.getString("admin.header.serial54") %>"
      break
       case 431:
      t="<%=resource.getString("admin.header.serial75") %>"
      break
       case 432:
      t="<%=resource.getString("admin.header.serial76") %>"
      break
       case 433:
      t="<%=resource.getString("admin.header.serial56") %>"
      break
       case 434:
      t="<%=resource.getString("admin.header.serial57") %>"
      break
       case 435:
      t="<%=resource.getString("admin.header.serial58") %>"
      break
         case 436:
      t="<%=resource.getString("admin.header.serial59") %>"
      break
       case 437:
      t="<%=resource.getString("admin.header.serial77") %>"
      break
       case 438:
      t="<%=resource.getString("admin.header.serial61") %>"
      break
       case 439:
      t="<%=resource.getString("admin.header.serial10") %>"
      break
       case 440:
      t="<%=resource.getString("admin.header.serial11") %>"
      break
       case 441:
      t="<%=resource.getString("admin.header.serial12") %>"
      break
       case 442:
      t="<%=resource.getString("admin.header.serial13") %>"
      break
         case 443:
      t="<%=resource.getString("admin.header.serial14") %>"
      break
       case 444:
      t="<%=resource.getString("admin.header.serial15") %>"
      break
       case 445:
      t="<%=resource.getString("admin.header.serial62") %>"
      break
       case 446:
      t="<%=resource.getString("admin.header.serial17") %>"
      break
       case 447:
      t="<%=resource.getString("admin.header.serial18") %>"
      break
       case 448:
      t="<%=resource.getString("admin.header.serial19") %>"
      break
       case 449:
      t="<%=resource.getString("admin.header.serial78") %>"
      break
       case 450:
      t="<%=resource.getString("admin.header.serial64") %>"
      break
       case 451:
      t="<%=resource.getString("admin.header.serial21") %>"
      break
         case 452:
      t="<%=resource.getString("admin.header.serial22") %>"
      break
       case 453:
      t="<%=resource.getString("admin.header.serial23") %>"
      break
        case 454:
      t="<%=resource.getString("admin.header.serial65") %>"
      break
       case 455:
      t="<%=resource.getString("admin.header.serial66") %>"
      break
       case 456:
      t="<%=resource.getString("admin.header.serial67") %>"
      break
       case 457:
      t="<%=resource.getString("admin.header.serial25") %>"
      break
       case 458:
      t="<%=resource.getString("admin.header.serial26") %>"
      break
       case 459:
      t="<%=resource.getString("admin.header.serial27") %>"
      break
       case 460:
      t="<%=resource.getString("admin.header.serial68") %>"
      break
         case 461:
      t="<%=resource.getString("admin.header.serial29") %>"
      break
       case 462:
      t="<%=resource.getString("admin.header.serial30") %>"
      break

     case 463:
      t="<%=resource.getString("admin.header.serial31") %>"
      break
 case 464:
      t="<%=resource.getString("admin.header.serial79") %>"
      break
    case 465:
      t="<%=resource.getString("admin.header.serial81") %>"
      break
    case 466:
      t="<%=resource.getString("admin.header.serial82") %>"
      break
    case 467:
      t="<%=resource.getString("admin.header.serial83") %>"
      break
    case 468:
{  
    t="<%=resource.getString("admin.header.serial84") %>"
      break
      }
    case 469:
      t="<%=resource.getString("admin.header.serial85") %>"
      break
    case 470:
      t="<%=resource.getString("admin.header.serial86") %>"
      break
    case 471:
      t="<%=resource.getString("admin.header.serial87") %>"
      break








    default:
        t=name1;




}
   

return t;
}





 
function dhtmlXTreeObject(htmlObject,width,height,rootId){
 if(typeof(htmlObject)!="object")
 this.parentObject=document.getElementById(htmlObject);
 else
 this.parentObject=htmlObject;
 this.mytype="tree";
 this.width=width;
 this.height=height;
 this.rootId=rootId;
 
 this.style_pointer="pointer";
 if(navigator.appName == 'Microsoft Internet Explorer')this.style_pointer="hand";
 
 this.hfMode=0;
 this.nodeCut=0;
 this.XMLsource=0;
 this.XMLloadingWarning=0;
 this._globalIdStorage=new Array();
 this.globalNodeStorage=new Array();
 this._globalIdStorageSize=0;
 this.treeLinesOn=true;
 this.checkFuncHandler=0;
 this.openFuncHandler=0;
 this.dblclickFuncHandler=0;
 this.tscheck=false;
 this.timgen=true;
 
 this.imPath="treeGfx/";
 this.checkArray=new Array("iconUnCheckAll.gif","iconCheckAll.gif","iconCheckGray.gif");
 this.lineArray=new Array("line3.gif","line3.gif","line4.gif","blank.gif","blank.gif");
 this.minusArray=new Array("minus3.gif","minus3.gif","minus4.gif","minus.gif","minus5.gif");
 this.plusArray=new Array("plus3.gif","plus3.gif","plus4.gif","plus.gif","plus5.gif");
 this.imageArray=new Array("leaf.gif","folderOpen.gif","folderClosed.gif");
 this.cutImg= new Array(0,0,0);
 this.cutImage="but_cut.gif";
 
 this.dragger= new dhtmlDragAndDropObject();
 this.htmlNode=new dhtmlXTreeItemObject(this.rootId,"",0,this);
 this.htmlNode.htmlNode.childNodes[0].childNodes[0].style.display="none";
 this.htmlNode.htmlNode.childNodes[0].childNodes[0].childNodes[0].className="hiddenRow";
 this.allTree=this._createSelf();
 this.allTree.appendChild(this.htmlNode.htmlNode);
 this.allTree.onselectstart=new Function("return false;");
 this.XMLLoader=new dtmlXMLLoaderObject(this._parseXMLTree,this);
 
 this.dragger.addDragLanding(this.allTree,this);
 return this;
};

 
function dhtmlXTreeItemObject(itemId,itemText,parentObject,treeObject,actionHandler,mode){
 this.htmlNode="";
 this.acolor="";
 this.scolor="";
 this.tr=0;
 this.childsCount=0;
 this.tempDOMM=0;
 this.tempDOMU=0;
 this.dragSpan=0;
 this.dragMove=0;
 this.span=0;
 this.closeble=1;
 this.childNodes=new Array();

 this.checkstate=0;
 this.treeNod=treeObject;
 this.label=itemText;
 this.parentObject=parentObject;
 this.actionHandler=actionHandler;
 this.images=new Array(treeObject.imageArray[0],treeObject.imageArray[1],treeObject.imageArray[2]);


 this.id=treeObject._globalIdStorageAdd(itemId,this);
 if(this.treeNod.checkBoxOff)this.htmlNode=this.treeNod._createItem(1,this,mode);
 else this.htmlNode=this.treeNod._createItem(0,this,mode);
 this.htmlNode.objBelong=this;
 return this;
};
 
 
 
 dhtmlXTreeObject.prototype._globalIdStorageAdd=function(itemId,itemObject){
 if(this._globalIdStorageFind(itemId)){d=new Date();itemId=d.valueOf()+"_"+itemId;return this._globalIdStorageAdd(itemId,itemObject);}
 this._globalIdStorage[this._globalIdStorageSize]=itemId;
 this.globalNodeStorage[this._globalIdStorageSize]=itemObject;
 this._globalIdStorageSize++;
 return itemId;
};
 
 dhtmlXTreeObject.prototype._globalIdStorageSub=function(itemId){
 for(var i=0;i<this._globalIdStorageSize;i++)
 if(this._globalIdStorage[i]==itemId)
{
 this._globalIdStorage[i]=this._globalIdStorage[this._globalIdStorageSize-1];
 this.globalNodeStorage[i]=this.globalNodeStorage[this._globalIdStorageSize-1];
 this._globalIdStorageSize--;
 this._globalIdStorage[this._globalIdStorageSize]=0;
 this.globalNodeStorage[this._globalIdStorageSize]=0;
}
};
 
 
 dhtmlXTreeObject.prototype._globalIdStorageFind=function(itemId){
 for(var i=0;i<this._globalIdStorageSize;i++)
 if(this._globalIdStorage[i]==itemId)
 return this.globalNodeStorage[i];
 return 0;
};


 
 
 dhtmlXTreeObject.prototype._drawNewTr=function(htmlObject)
{
 var tr =document.createElement('tr');
 var td1=document.createElement('td');
 var td2=document.createElement('td');
 td1.appendChild(document.createTextNode(" "));
 td2.colSpan=3;td2.appendChild(htmlObject);tr.appendChild(td1);tr.appendChild(td2);
 return tr;
};
 
 dhtmlXTreeObject.prototype.loadXMLString=function(xmlString){this.XMLLoader.loadXMLString(xmlString);};
 
 dhtmlXTreeObject.prototype.loadXML=function(file){this.XMLLoader.loadXML(file);};
 
 dhtmlXTreeObject.prototype._attachChildNode=function(parentObject,itemId,itemText,itemActionHandler,image1,image2,image3,optionStr,childs,beforeNode){
 if(beforeNode)parentObject=beforeNode.parentObject;
 if(((parentObject.XMLload==0)&&(this.XMLsource))&&(!this.XMLloadingWarning))
{
 parentObject.XMLload=1;this.loadXML(this.XMLsource+getUrlSymbol(this.XMLsource)+"itemId="+escape(parentObject.itemId));
}
 
 var Count=parentObject.childsCount;
 var Nodes=parentObject.childNodes;
 
 if((!itemActionHandler)&&(this.aFunc))itemActionHandler=this.aFunc;
 Nodes[Count]=new dhtmlXTreeItemObject(itemId,itemText,parentObject,this,itemActionHandler,1);
 
 if(image1)Nodes[Count].images[0]=image1;
 if(image2)Nodes[Count].images[1]=image2;
 if(image3)Nodes[Count].images[2]=image3;
 
 parentObject.childsCount++;
 var tr=this._drawNewTr(Nodes[Count].htmlNode);
 if(this.XMLloadingWarning)
 Nodes[Count].htmlNode.parentNode.parentNode.style.display="none";
 
 if(optionStr){
 var tempStr=optionStr.split(",");
 for(var i=0;i<tempStr.length;i++)
{
 switch(tempStr[i])
{
 case "TOP": if(parentObject.childsCount>1)beforeNode=parentObject.htmlNode.childNodes[0].childNodes[1].nodem.previousSibling;break;
}
};
};
 
 if((beforeNode)&&(beforeNode.tr.nextSibling))
 parentObject.htmlNode.childNodes[0].insertBefore(tr,beforeNode.tr.nextSibling);
 else
 parentObject.htmlNode.childNodes[0].appendChild(tr);

 if(this.XMLsource)if((childs)&&(childs!=0))Nodes[Count].XMLload=0;else Nodes[Count].XMLload=1;

 Nodes[Count].tr=tr;
 tr.nodem=Nodes[Count];

 if(parentObject.itemId==0)
 tr.childNodes[0].className="hitemIddenRow";
 
 if(optionStr){
 var tempStr=optionStr.split(",");
 for(var i=0;i<tempStr.length;i++)
{
 switch(tempStr[i])
{
 case "SELECT": this.selectItem(itemId,false);break;
 case "CALL": this.selectItem(itemId,true);break;
 case "CHILD": Nodes[Count].XMLload=0;break;
 case "CHECKED": 
 if(this.XMLloadingWarning)
 this.setCheckList+=itemId;
 else
 this.setCheck(itemId,1);
 break;
 case "OPEN": Nodes[Count].openMe=1;break;
}
};
};

 if(!this.XMLloadingWarning)
{
 if(this._getOpenState(parentObject)<0)
 this.openItem(parentObject.id);
 
 if(beforeNode)
{
 this._correctPlus(beforeNode);
 this._correctLine(beforeNode);
}
 this._correctPlus(parentObject);
 this._correctLine(parentObject);
 this._correctPlus(Nodes[Count]);
 if(parentObject.childsCount>=2)
{
 this._correctPlus(Nodes[parentObject.childsCount-2]);
 this._correctLine(Nodes[parentObject.childsCount-2]);
}
 if(parentObject.childsCount!=2)this._correctPlus(Nodes[0]);
 if(this.tscheck)this._correctCheckStates(parentObject);
}
 
 return Nodes[Count];
};

 
 dhtmlXTreeObject.prototype.insertNewItem=function(parentId,itemId,itemText,itemActionHandler,image1,image2,image3,optionStr,childs){
 var parentObject=this._globalIdStorageFind(parentId);
 if(!parentObject)return(-1);
 return this._attachChildNode(parentObject,itemId,itemText,itemActionHandler,image1,image2,image3,optionStr,childs);
};
 
 dhtmlXTreeObject.prototype._parseXMLTree=function(dhtmlObject,node,parentId,level){
 dhtmlObject.XMLloadingWarning=1;
 var nodeAskingCall="";
 if(!node){
 node=dhtmlObject.XMLLoader.getXMLTopNode("tree");
 parentId=node.getAttribute("id");
 dhtmlObject.setCheckList="";
}

 for(var i=0;i<node.childNodes.length;i++)
{
 if((node.childNodes[i].nodeType==1)&&(node.childNodes[i].tagName == "item"))
{
 var name=node.childNodes[i].getAttribute("text");
 var cId=node.childNodes[i].getAttribute("id");
 
 var im0=node.childNodes[i].getAttribute("im0");
 var im1=node.childNodes[i].getAttribute("im1");
 var im2=node.childNodes[i].getAttribute("im2");
 
 var aColor=node.childNodes[i].getAttribute("aCol");
 var sColor=node.childNodes[i].getAttribute("sCol");
 
 var chd=node.childNodes[i].getAttribute("child");
 
 var atop=node.childNodes[i].getAttribute("top");
 var aopen=node.childNodes[i].getAttribute("open");
 var aselect=node.childNodes[i].getAttribute("select");
 var acall=node.childNodes[i].getAttribute("call");
 var achecked=node.childNodes[i].getAttribute("checked");
 var closeable=node.childNodes[i].getAttribute("closeable");
 
 var zST="";
 if(aselect)zST+=",SELECT";
 if(atop)zST+=",TOP";
 if(acall)nodeAskingCall=cId;
 if(achecked)zST+=",CHECKED";
 if((aopen)&&(aopen!="0"))zST+=",OPEN";
 
 var temp=dhtmlObject._globalIdStorageFind(parentId);
 temp.XMLload=1;
 
name=search(cId,name);
 

 dhtmlObject.insertNewItem(parentId,cId,name,0,im0,im1,im2,zST,chd);
 if(dhtmlObject.parserExtension)dhtmlObject.parserExtension._parseExtension(node.childNodes[i],dhtmlObject.parserExtension,cId,parentId);
 dhtmlObject.setItemColor(cId,aColor,sColor);
 if((closeable=="0")||(closeable=="1"))dhtmlObject.setItemCloseable(cId,closeable);
 var zcall=dhtmlObject._parseXMLTree(dhtmlObject,node.childNodes[i],cId,1);
 if(zcall!="")nodeAskingCall=zcall;
}
 else
 if((node.childNodes[i].nodeType==1)&&(node.childNodes[i].tagName == "userdata"))
{
 var name=node.childNodes[i].getAttribute("name");
 if((name)&&(node.childNodes[i].childNodes[0])){
 dhtmlObject.setUserData(parentId,name,node.childNodes[i].childNodes[0].data);
};
};
};

 if(!level){
 dhtmlObject.lastLoadedXMLId=parentId;
 dhtmlObject._redrawFrom(dhtmlObject);
 dhtmlObject.XMLloadingWarning=0;
 var chArr=dhtmlObject.setCheckList.split(",");
 for(var n=0;n<chArr.length;n++)
 if(chArr[n])dhtmlObject.setCheck(chArr[n],1);
 if(nodeAskingCall!="")dhtmlObject.selectItem(nodeAskingCall,true);
}
 return nodeAskingCall;
};
 
 
 dhtmlXTreeObject.prototype._redrawFrom=function(dhtmlObject,itemObject){
 if(!itemObject){
 var tempx=dhtmlObject._globalIdStorageFind(dhtmlObject.lastLoadedXMLId);
 dhtmlObject.lastLoadedXMLId=-1;
 if(!tempx)return 0;
}
 else tempx=itemObject;
 for(var i=0;i<tempx.childsCount;i++)
{
 if(!itemObject)tempx.childNodes[i].htmlNode.parentNode.parentNode.style.display="";
 if(tempx.childNodes[i].openMe==1)
 for(var zy=0;zy<tempx.childNodes[i].childNodes.length;zy++)
 tempx.childNodes[i].htmlNode.childNodes[0].childNodes[zy+1].style.display="";
 dhtmlObject._redrawFrom(dhtmlObject,tempx.childNodes[i]);
 dhtmlObject._correctLine(tempx.childNodes[i]);
 dhtmlObject._correctPlus(tempx.childNodes[i]);
};
 dhtmlObject._correctLine(tempx);
 dhtmlObject._correctPlus(tempx);
};

 
 dhtmlXTreeObject.prototype._createSelf=function(){
 var div=document.createElement('div');
 div.className="containerTableStyle";
 div.style.width=this.width;
 div.style.height=this.height;
 this.parentObject.appendChild(div);
 return div;
};

 
 dhtmlXTreeObject.prototype._xcloseAll=function(itemObject)
{
 if(this.rootId!=itemObject.id)this._HideShow(itemObject,1);
 for(var i=0;i<itemObject.childsCount;i++)
 this._xcloseAll(itemObject.childNodes[i]);
};
 
 dhtmlXTreeObject.prototype._xopenAll=function(itemObject)
{
 this._HideShow(itemObject,2);
 for(var i=0;i<itemObject.childsCount;i++)
 this._xopenAll(itemObject.childNodes[i]);
};
 
 dhtmlXTreeObject.prototype._correctPlus=function(itemObject){
 var workArray=this.lineArray;
 if((this.XMLsource)&&(!itemObject.XMLload))
{
 var workArray=this.plusArray;
 itemObject.htmlNode.childNodes[0].childNodes[0].childNodes[2].childNodes[0].src=this.imPath+itemObject.images[2];
}
 else
try{
 if(itemObject.childsCount)
{
 if(itemObject.htmlNode.childNodes[0].childNodes[1].style.display!="none")
{
 var workArray=this.minusArray;
 itemObject.htmlNode.childNodes[0].childNodes[0].childNodes[2].childNodes[0].src=this.imPath+itemObject.images[1];
}
 else
{
 var workArray=this.plusArray;
 itemObject.htmlNode.childNodes[0].childNodes[0].childNodes[2].childNodes[0].src=this.imPath+itemObject.images[2];
}
}
 else
{
 itemObject.htmlNode.childNodes[0].childNodes[0].childNodes[2].childNodes[0].src=this.imPath+itemObject.images[0];
}
}
 catch(e){};
 
 var tempNum=2;
 if(!itemObject.treeNod.treeLinesOn)itemObject.htmlNode.childNodes[0].childNodes[0].childNodes[0].childNodes[0].src=this.imPath+workArray[3];
 else{
 if(itemObject.parentObject)tempNum=this._getCountStatus(itemObject.id,itemObject.parentObject);
 itemObject.htmlNode.childNodes[0].childNodes[0].childNodes[0].childNodes[0].src=this.imPath+workArray[tempNum];
}
};
 
 dhtmlXTreeObject.prototype._correctLine=function(itemObject){
 var sNode=itemObject.parentObject;
 try{
 if(sNode)
 if((this._getLineStatus(itemObject.id,sNode)==0)||(!this.treeLinesOn))
{
 for(var i=1;i<=itemObject.childsCount;i++)
{
 itemObject.htmlNode.childNodes[0].childNodes[i].childNodes[0].style.backgroundImage="";
 itemObject.htmlNode.childNodes[0].childNodes[i].childNodes[0].style.backgroundRepeat="";
}
}
 else
 for(var i=1;i<=itemObject.childsCount;i++)
{
 itemObject.htmlNode.childNodes[0].childNodes[i].childNodes[0].style.backgroundImage="url("+this.imPath+"line1.gif)";
 itemObject.htmlNode.childNodes[0].childNodes[i].childNodes[0].style.backgroundRepeat="repeat-y";
}
}
 catch(e){};
};
 
 dhtmlXTreeObject.prototype._getCountStatus=function(itemId,itemObject){
 try{
 if(itemObject.childsCount<=1){if(itemObject.id==this.rootId)return 4;else return 0;}
 
 if(itemObject.htmlNode.childNodes[0].childNodes[1].nodem.id==itemId)if(!itemObject.id)return 2;else return 1;
 if(itemObject.htmlNode.childNodes[0].childNodes[itemObject.childsCount].nodem.id==itemId)return 0;
}
 catch(e){};
 return 1;
};
 
 dhtmlXTreeObject.prototype._getLineStatus =function(itemId,itemObject){
 if(itemObject.htmlNode.childNodes[0].childNodes[itemObject.childsCount].nodem.id==itemId)return 0;
 return 1;
}

 
 dhtmlXTreeObject.prototype._HideShow=function(itemObject,mode){
 if(((this.XMLsource)&&(!itemObject.XMLload))&&(!mode)){itemObject.XMLload=1;this.loadXML(this.XMLsource+getUrlSymbol(this.XMLsource)+"id="+escape(itemObject.id));return;};
 var Nodes=itemObject.htmlNode.childNodes[0].childNodes;var Count=Nodes.length;
 if(Count>1){
 if(((Nodes[1].style.display!="none")||(mode==1))&&(mode!=2))nodestyle="none";else nodestyle="";
 for(var i=1;i<Count;i++)
 Nodes[i].style.display=nodestyle;
}
 this._correctPlus(itemObject);
}
 
 dhtmlXTreeObject.prototype._getOpenState=function(itemObject){
 var z=itemObject.htmlNode.childNodes[0].childNodes;
 if(z.length<=1)return 0;
 if(z[1].style.display!="none")return 1;
 else return -1;
}

 
 
 
 dhtmlXTreeObject.prototype.onRowClick2=function(){
 if(this.parentObject.treeNod.dblclickFuncHandler)if(!this.parentObject.treeNod.dblclickFuncHandler(this.parentObject.id))return 0;
 if((this.parentObject.closeble)&&(this.parentObject.closeble!="0"))
 this.parentObject.treeNod._HideShow(this.parentObject);
 else
 this.parentObject.treeNod._HideShow(this.parentObject,2);
};
 
 dhtmlXTreeObject.prototype.onRowClick=function(){
 if(this.parentObject.treeNod.openFuncHandler)if(!this.parentObject.treeNod.openFuncHandler(this.parentObject.id,this.parentObject.treeNod._getOpenState(this.parentObject)))return 0;
 if((this.parentObject.closeble)&&(this.parentObject.closeble!="0"))
 this.parentObject.treeNod._HideShow(this.parentObject);
 else
 this.parentObject.treeNod._HideShow(this.parentObject,2);
};
 
 
 dhtmlXTreeObject.prototype.onRowSelect=function(e,htmlObject,mode){
 if(!htmlObject)htmlObject=this;
 htmlObject.childNodes[0].className="selectedTreeRow";
 if(htmlObject.parentObject.scolor)htmlObject.parentObject.span.style.color=htmlObject.parentObject.scolor;
 if((htmlObject.parentObject.treeNod.lastSelected)&&(htmlObject.parentObject.treeNod.lastSelected!=htmlObject))
{
 htmlObject.parentObject.treeNod.lastSelected.childNodes[0].className="standartTreeRow";
 if(htmlObject.parentObject.treeNod.lastSelected.parentObject.acolor)htmlObject.parentObject.treeNod.lastSelected.parentObject.span.style.color=htmlObject.parentObject.treeNod.lastSelected.parentObject.acolor;
}
 htmlObject.parentObject.treeNod.lastSelected=htmlObject;
 if(!mode){if(htmlObject.parentObject.actionHandler)htmlObject.parentObject.actionHandler(htmlObject.parentObject.id);}
};
 



 
 
dhtmlXTreeObject.prototype._correctCheckStates=function(dhtmlObject){
 if(!this.tscheck)return;
 if(dhtmlObject.id==this.rootId)return;
 var act=dhtmlObject.htmlNode.childNodes[0].childNodes;
 var flag1=0;var flag2=0;
 if(act.length<2)return;
 for(var i=1;i<act.length;i++)
 if(act[i].nodem.checkstate==0)flag1=1;
 else if(act[i].nodem.checkstate==1)flag2=1;
 else{flag1=1;flag2=1;break;}

 if((flag1)&&(flag2))this._setCheck(dhtmlObject,true);
 else if(flag1)this._setCheck(dhtmlObject,false);
 else this._setCheck(dhtmlObject,true);
 
 this._correctCheckStates(dhtmlObject.parentObject);
}
 
 
 dhtmlXTreeObject.prototype.onCheckBoxClick=function(e){
 if(this.treeNod.tscheck)
 if(this.parentObject.checkstate==1)this.treeNod._setSubChecked(false,this.parentObject);
 else this.treeNod._setSubChecked(true,this.parentObject);
 else
 if(this.parentObject.checkstate==1)this.treeNod._setCheck(this.parentObject,false);
 else this.treeNod._setCheck(this.parentObject,true);
 this.treeNod._correctCheckStates(this.parentObject.parentObject);
 if(this.treeNod.checkFuncHandler)return(this.treeNod.checkFuncHandler(this.parentObject.id,this.parentObject.checkstate));
 else return true;
};
 
 dhtmlXTreeObject.prototype._createItem=function(acheck,itemObject,mode){
 var table=document.createElement('table');
 table.cellSpacing=0;table.cellPadding=0;
 table.border=0;
 if(this.hfMode)table.style.tableLayout="fixed";
 table.style.margin=0;table.style.padding=0;
 
 var tbody=document.createElement('tbody');
 var tr=document.createElement('tr');
 var td1=document.createElement('td');
 td1.className="standartTreeImage";
 var img0=document.createElement("img");
 img0.border="0";td1.appendChild(img0);img0.style.padding=0;
 
 var td11=document.createElement('td');
 var inp=document.createElement("img");inp.checked=0;inp.src=this.imPath+this.checkArray[0];inp.style.width="16px";inp.style.height="16px";

 if(!acheck)inp.style.display="none";
 td11.appendChild(inp);
 td11.width="16px";
 inp.onclick=this.onCheckBoxClick;
 inp.treeNod=this;
 inp.parentObject=itemObject;

 var td12=document.createElement('td');
 td12.className="standartTreeImage";
 var img=document.createElement("img");img.onmousedown=this._preventNsDrag;
 img.border="0";
 if(!mode)img.src=this.imPath+this.imageArray[0];
 td12.appendChild(img);img.style.padding=0;img.style.margin=0;
 if(this.timgen)
{img.style.width="18px";img.style.height="18px";}
 else
{img.style.width="0px";img.style.height="0px";}

 
 var td2=document.createElement('td');
 td2.noWrap=true;
 itemObject.span=document.createElement('span');
 itemObject.span.className="standartTreeRow";
 td2.style.width="100%";
 itemObject.span.appendChild(document.createTextNode(itemObject.label));
 td2.appendChild(itemObject.span);
 td2.parentObject=itemObject;td1.parentObject=itemObject;
 td2.onclick=this.onRowSelect;td1.onclick=this.onRowClick;td2.ondblclick=this.onRowClick2;
 
 if(this.dragAndDropOff)this.dragger.addDraggableItem(td2,this);
 
 td2.style.verticalAlign="";
 td2.style.cursor=this.style_pointer;
 tr.appendChild(td1);tr.appendChild(td11);tr.appendChild(td12);
 tr.appendChild(td2);
 tbody.appendChild(tr);
 table.appendChild(tbody);
 return table;
};
 
 
 
 dhtmlXTreeObject.prototype.setImagePath=function(newPath){this.imPath=newPath;};
 
 
 
 dhtmlXTreeObject.prototype.setOnClickHandler=function(func){if(typeof(func)=="function")this.aFunc=func;else this.aFunc=eval(func);};
 
 
 dhtmlXTreeObject.prototype.setXMLAutoLoading=function(filePath){this.XMLsource=filePath;};
 
 dhtmlXTreeObject.prototype.setOnCheckHandler=function(func){if(typeof(func)=="function")this.checkFuncHandler=func;else this.checkFuncHandler=eval(func);};
 
 
 
 dhtmlXTreeObject.prototype.setOnOpenHandler=function(func){if(typeof(func)=="function")this.openFuncHandler=func;else this.openFuncHandler=eval(func);};
 
 
 dhtmlXTreeObject.prototype.setOnDblClickHandler=function(func){if(typeof(func)=="function")this.dblclickFuncHandler=func;else this.dblclickFuncHandler=eval(func);};
 
 
 
 





 
 dhtmlXTreeObject.prototype.openAllItems=function(itemId)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 this._xopenAll(temp);
};
 
 
 dhtmlXTreeObject.prototype.getOpenState=function(itemId){
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return "";
 return this._getOpenState(temp);
};
 
 
 dhtmlXTreeObject.prototype.closeAllItems=function(itemId)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 this._xcloseAll(temp);
};
 
 
 
 dhtmlXTreeObject.prototype.setUserData=function(itemId,name,value){
 var sNode=this._globalIdStorageFind(itemId);
 if(!sNode)return;
 if(name=="hint")sNode.htmlNode.childNodes[0].childNodes[0].title=value;
 sNode[name]=value;
};
 
 
 dhtmlXTreeObject.prototype.getUserData=function(itemId,name){
 var sNode=this._globalIdStorageFind(itemId);
 if(!sNode)return;
 return eval("sNode."+name);
};
 
 
 dhtmlXTreeObject.prototype.getSelectedItemId=function()
{
   
 if(this.lastSelected)
 if(this._globalIdStorageFind(this.lastSelected.parentObject.id))
 return this.lastSelected.parentObject.id;
 return("");
};
 
 
 dhtmlXTreeObject.prototype.getItemColor=function(itemId)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;

 var res= new Object();
 if(temp.acolor)res.acolor=temp.acolor;
 if(temp.acolor)res.scolor=temp.scolor;
 return res;
};
 
 dhtmlXTreeObject.prototype.setItemColor=function(itemId,defaultColor,selectedColor)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 else{
 if((this.lastSelected)&&(temp.tr==this.lastSelected.parentObject.tr))
{if(selectedColor)temp.span.style.color=selectedColor;}
 else 
{if(defaultColor)temp.span.style.color=defaultColor;}
 
 if(selectedColor)temp.scolor=selectedColor;
 if(defaultColor)temp.acolor=defaultColor;
}
};
 
 
 dhtmlXTreeObject.prototype.getItemText=function(itemId)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 return(temp.htmlNode.childNodes[0].childNodes[0].childNodes[3].childNodes[0].innerHTML);
};
 
 dhtmlXTreeObject.prototype.getParentId=function(itemId)
{
 var temp=this._globalIdStorageFind(itemId);
 if((!temp)||(!temp.parentObject))return "";
 return temp.parentObject.id;
};



 
 dhtmlXTreeObject.prototype.changeItemId=function(itemId,newItemId)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 temp.id=newItemId;
 for(var i=0;i<this._globalIdStorageSize;i++)
 if(this._globalIdStorage[i]==itemId)
{
 this._globalIdStorage[i]=newItemId;
}
};

 
 
 dhtmlXTreeObject.prototype.doCut=function(){
 if(this.nodeCut)this.clearCut();
 this.nodeCut=this.lastSelected;
 if(this.nodeCut)
{
 var tempa=this.nodeCut.parentObject;
 this.cutImg[0]=tempa.images[0];
 this.cutImg[1]=tempa.images[1];
 this.cutImg[2]=tempa.images[2];
 tempa.images[0]=tempa.images[1]=tempa.images[2]=this.cutImage;
 this._correctPlus(tempa);
}
};
 
 
 dhtmlXTreeObject.prototype.doPaste=function(itemId){
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 if(this.nodeCut){
 if((!this._checkParenNodes(this.nodeCut.parentObject.id,temp))&&(id!=this.nodeCut.parentObject.parentObject.id))
 this._moveNode(temp,this.nodeCut.parentObject);
 this.clearCut();
}
};
 
 
 dhtmlXTreeObject.prototype.clearCut=function(){
 if(this.nodeCut)
{
 var tempa=this.nodeCut.parentObject;
 tempa.images[0]=this.cutImg[0];
 tempa.images[1]=this.cutImg[1];
 tempa.images[2]=this.cutImg[2];
 if(tempa.parentObject)this._correctPlus(tempa);
 if(tempa.parentObject)this._correctLine(tempa);
 this.nodeCut=0;
}
};
 


 
 dhtmlXTreeObject.prototype._moveNode=function(itemObject,targetObject){
 if(this.dragFunc)if(!this.dragFunc(itemObject.id,targetObject.id))return false;
 if((targetObject.XMLload==0)&&(this.XMLsource))
{
 targetObject.XMLload=1;this.loadXML(this.XMLsource+getUrlSymbol(this.XMLsource)+"id="+escape(targetObject.id));
}
 this.openItem(targetObject.id);
 var oldTree=itemObject.treeNod;
 var c=itemObject.parentObject.childsCount;
 var z=itemObject.parentObject;
 var Count=targetObject.childsCount;var Nodes=targetObject.childNodes;
 Nodes[Count]=itemObject;
 itemObject.treeNod=targetObject.treeNod;
 targetObject.childsCount++;
 
 var tr=this._drawNewTr(Nodes[Count].htmlNode);
 targetObject.htmlNode.childNodes[0].appendChild(tr);
 
 itemObject.parentObject.htmlNode.childNodes[0].removeChild(itemObject.tr);
 

 for(var i=0;i<z.childsCount;i++)
 if(z.childNodes[i].id==itemObject.id){
 z.childNodes[i]=0;
 break;}
 oldTree._compressChildList(z.childsCount,z.childNodes);
 z.childsCount--;
 itemObject.tr=tr;
 tr.nodem=itemObject;

 itemObject.parentObject=targetObject;
 if(oldTree!=targetObject.treeNod){if(itemObject.treeNod._registerBranch(itemObject,oldTree))return;this._clearStyles(itemObject);};
 
 
 if(c>1){oldTree._correctPlus(z.childNodes[c-2]);
 oldTree._correctLine(z.childNodes[c-2]);}
 this._correctPlus(targetObject);
 this._correctLine(targetObject);
 oldTree._correctPlus(z);
 this._correctLine(itemObject);
 this._correctPlus(Nodes[Count]);
 if(targetObject.childsCount>=2)
{
 this._correctPlus(Nodes[targetObject.childsCount-2]);
 this._correctLine(Nodes[targetObject.childsCount-2]);
}
 if(this.tscheck)this._correctCheckStates(targetObject);
 if(oldTree.tscheck)oldTree._correctCheckStates(z);
 return true;
};
 
 
dhtmlXTreeObject.prototype._checkParenNodes=function(itemId,htmlObject,shtmlObject){

 if (itemId==1 || itemId==2 || itemId==3)
     {
         <%
         System.out.println("staff_id="+staff_role);
         if (staff_role.equals("staff"))
             {%>

             alert("You can not assigned this privilege to staff role");
             return 0;
          <%}%>
     }
 if(shtmlObject){if(shtmlObject.parentObject.id==htmlObject.id)return 1;}
 if(htmlObject.id==itemId)return 1;
 if(htmlObject.parentObject)return this._checkParenNodes(itemId,htmlObject.parentObject);else return 0;
};
 
 
 
 
 dhtmlXTreeObject.prototype._clearStyles=function(itemObject){
 var td1=itemObject.htmlNode.childNodes[0].childNodes[0].childNodes[1];
 var td3=td1.nextSibling.nextSibling;
 
 if(this.checkBoxOff){td1.childNodes[0].style.display="";td1.childNodes[0].onclick=this.onCheckBoxClick;}
 else td1.childNodes[0].style.display="none";
 td1.childNodes[0].treeNod=this;
 
 this.dragger.removeDraggableItem(td3);
 if(this.dragAndDropOff)this.dragger.addDraggableItem(td3,this);
 td3.childNodes[0].className="standartTreeRow";
 td3.onclick=this.onRowSelect;td3.ondblclick=this.onRowClick2;
 td1.previousSibling.onclick=this.onRowClick;


 this._correctLine(itemObject);
 this._correctPlus(itemObject);
 for(var i=0;i<itemObject.childsCount;i++)this._clearStyles(itemObject.childNodes[i]);
};
 
 dhtmlXTreeObject.prototype._registerBranch=function(itemObject,oldTree){
 
 itemObject.id=this._globalIdStorageAdd(itemObject.id,itemObject);
 itemObject.treeNod=this;
 if(oldTree)oldTree._globalIdStorageSub(itemObject.id);
 for(var i=0;i<itemObject.childsCount;i++)
 this._registerBranch(itemObject.childNodes[i],oldTree);
 return 0;
};
 
 
 
 dhtmlXTreeObject.prototype.enableThreeStateCheckboxes=function(mode){this.tscheck=convertStringToBoolean(mode);};
 
 
 dhtmlXTreeObject.prototype.enableTreeImages=function(mode){this.timgen=convertStringToBoolean(mode);};
 
 
 
 dhtmlXTreeObject.prototype.enableFixedMode=function(mode){this.hfMode=convertStringToBoolean(mode);};
 
 
 dhtmlXTreeObject.prototype.enableCheckBoxes=function(mode){this.checkBoxOff=convertStringToBoolean(mode);};
 
 dhtmlXTreeObject.prototype.setStdImages=function(image1,image2,image3){
 this.imageArray[0]=image1;this.imageArray[1]=image2;this.imageArray[2]=image3;};

 
 dhtmlXTreeObject.prototype.enableTreeLines=function(mode){
 this.treeLinesOn=convertStringToBoolean(mode);
}

 
 dhtmlXTreeObject.prototype.setImageArrays=function(arrayName,image1,image2,image3,image4,image5){
 switch(arrayName){
 case "plus": this.plusArray[0]=image1;this.plusArray[1]=image2;this.plusArray[2]=image3;this.plusArray[3]=image4;this.plusArray[4]=image5;break;
 case "minus": this.minusArray[0]=image1;this.minusArray[1]=image2;this.minusArray[2]=image3;this.minusArray[3]=image4;this.minusArray[4]=image5;break;
}
};
 
 
 dhtmlXTreeObject.prototype.openItem=function(itemId){
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 this._HideShow(temp,2);
 if((temp.parentObject)&&(this._getOpenState(temp.parentObject)<0))
 this.openItem(temp.parentObject.id);
};
 
 
 dhtmlXTreeObject.prototype.closeItem=function(itemId){
 if(this.rootId==itemId)return 0;
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 if(temp.closeble)
 this._HideShow(temp,1);
};

 dhtmlXTreeObject.prototype.getLevel=function(itemId){
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 return this._getNodeLevel(temp,0);
};
 
 

 
 dhtmlXTreeObject.prototype.setItemCloseable=function(itemId,flag)
{
 flag=convertStringToBoolean(flag);
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 temp.closeble=flag;
};
 
 
 dhtmlXTreeObject.prototype._getNodeLevel=function(itemObject,count){
 if(itemObject.parentObject)return this._getNodeLevel(itemObject.parentObject,count+1);
 return(count);
};
 
 
 dhtmlXTreeObject.prototype.hasChildren=function(itemId){
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 else 
{
 if((this.XMLsource)&&(!temp.XMLload))return true;
 else 
 return temp.childsCount;
};
};
 
 
 
 dhtmlXTreeObject.prototype.setItemText=function(itemId,newLabel)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 temp.label=newLabel;
 temp.htmlNode.childNodes[0].childNodes[0].childNodes[3].childNodes[0].innerHTML=newLabel;
};
 
 dhtmlXTreeObject.prototype.refreshItem=function(itemId){
 if(!itemId)itemId=this.rootId;
 var temp=this._globalIdStorageFind(itemId);
 this.deleteChildItems(itemId);
 this.loadXML(this.XMLsource+getUrlSymbol(this.XMLsource)+"id="+escape(itemId));
};
 
 
 dhtmlXTreeObject.prototype.setItemImage2=function(itemId,image1,image2,image3){
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 temp.images[1]=image2;
 temp.images[2]=image3;
 temp.images[0]=image1;
 this._correctPlus(temp);
};
 
 dhtmlXTreeObject.prototype.setItemImage=function(itemId,image1,image2)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 if(image2)
{
 temp.images[1]=image1;
 temp.images[2]=image2;
}
 else temp.images[0]=image1;
 this._correctPlus(temp);
};
 
 
 
 dhtmlXTreeObject.prototype.getSubItems =function(itemId)
{
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;

 var z="";
 for(i=0;i<temp.childsCount;i++)
 if(!z)z=temp.childNodes[i].id;
 else z+=","+temp.childNodes[i].id;
 return z;
};
 
 dhtmlXTreeObject.prototype.getAllSubItems =function(itemId){
 return this._getAllSubItems(itemId);
}
 
 
 dhtmlXTreeObject.prototype._getAllSubItems =function(itemId,z,node)
{
 if(node)temp=node;
 else{
 var temp=this._globalIdStorageFind(itemId);
};
 if(!temp)return 0;
 
 z="";
 for(var i=0;i<temp.childsCount;i++)
{
 if(!z)z=temp.childNodes[i].id;
 else z+=","+temp.childNodes[i].id;
 var zb=this._getAllSubItems(0,z,temp.childNodes[i])
 if(zb)z+=","+zb;
}
 return z;
};
 

 
 
 dhtmlXTreeObject.prototype.selectItem=function(itemId,mode){
 mode=convertStringToBoolean(mode);
 var temp=this._globalIdStorageFind(itemId);
 if(!temp)return 0;
 if(this._getOpenState(temp.parentObject)==-1)
 {
 this.openItem(itemId);
 this.selectItem(temp.parentObject,false);
}
 if(mode)
 this.onRowSelect(0,temp.htmlNode.childNodes[0].childNodes[0].childNodes[3],false);
 else
 this.onRowSelect(0,temp.htmlNode.childNodes[0].childNodes[0].childNodes[3],true);
};
 
 
 dhtmlXTreeObject.prototype.getSelectedItemText=function()
{
 if(this.lastSelected)
 return this.lastSelected.parentObject.htmlNode.childNodes[0].childNodes[0].childNodes[3].childNodes[0].innerHTML;
 else return("");
};




 
 dhtmlXTreeObject.prototype._compressChildList=function(Count,Nodes)
{
 Count--;
 for(var i=0;i<Count;i++)
{
 if(Nodes[i]==0){Nodes[i]=Nodes[i+1];Nodes[i+1]=0;}
};
};
 
 dhtmlXTreeObject.prototype._deleteNode=function(itemId,htmlObject,skip){

 if(!skip){
 this._globalIdStorageRecSub(htmlObject);
}
 
 if((!htmlObject)||(!htmlObject.parentObject))return 0;
 var tempos=0;var tempos2=0;
 if(htmlObject.tr.nextSibling)tempos=htmlObject.tr.nextSibling.nodem;
 if(htmlObject.tr.previousSibling)tempos2=htmlObject.tr.previousSibling.nodem;
 
 var sN=htmlObject.parentObject;
 var Count=sN.childsCount;
 var Nodes=sN.childNodes;
 for(var i=0;i<Count;i++)
{
 if(Nodes[i].id==itemId){
 if(!skip)sN.htmlNode.childNodes[0].removeChild(Nodes[i].tr);
 Nodes[i]=0;
 break;
}
}
 this._compressChildList(Count,Nodes);
 if(!skip){
 sN.childsCount--;
}

 if(tempos){
 this._correctPlus(tempos);
 this._correctLine(tempos);
}
 if(tempos2){
 this._correctPlus(tempos2);
 this._correctLine(tempos2);
}
 if(this.tscheck)this._correctCheckStates(sN);
};
 
 dhtmlXTreeObject.prototype.setCheck=function(itemId,state){
 
 state=convertStringToBoolean(state);
 var sNode=this._globalIdStorageFind(itemId);

 
 if(!sNode)return;
 if(!this.tscheck)return this._setSubChecked(state,sNode);
 else this._setCheck(sNode,state);
 this._correctCheckStates(sNode.parentObject);
};
 
         dhtmlXTreeObject.prototype._setCheck=function(sNode,state){
         var sNode1=this._globalIdStorageFind(1);
         var sNode2=this._globalIdStorageFind(2);
         var sNode3=this._globalIdStorageFind(3);
      
 var z=sNode.htmlNode.childNodes[0].childNodes[0].childNodes[1].childNodes[0];

 if(state=="notsure")sNode.checkstate=2;
 else if(state)sNode.checkstate=1;else sNode.checkstate=0;
 
 z.src=this.imPath+this.checkArray[sNode.checkstate];
};
 
 
dhtmlXTreeObject.prototype.setSubChecked=function(itemId,state){
 var sNode=this._globalIdStorageFind(itemId);
 this._setSubChecked(state,sNode);
 this._correctCheckStates(sNode.parentObject);
}
 
 dhtmlXTreeObject.prototype._setSubChecked=function(state,sNode){
 state=convertStringToBoolean(state);
 if(!sNode)return;
 for(var i=0;i<sNode.childsCount;i++)
{
 this._setSubChecked(state,sNode.childNodes[i]);
};
 var z=sNode.htmlNode.childNodes[0].childNodes[0].childNodes[1].childNodes[0];
 if(state)sNode.checkstate=1;
 else sNode.checkstate=0;
 z.src=this.imPath+this.checkArray[sNode.checkstate];
};

 
 dhtmlXTreeObject.prototype.isItemChecked=function(itemId){
 var sNode=this._globalIdStorageFind(itemId);
 if(!sNode)return;
 return sNode.checkstate;
};
 



dhtmlXTreeObject.prototype.getAllCheckedNames=function(){
 return this._getAllCheckedNames("","",1);
}
 
 dhtmlXTreeObject.prototype.getAllChecked=function(){
 return this._getAllChecked("","",1);
}
 
 dhtmlXTreeObject.prototype.getAllCheckedBranches=function(){
 return this._getAllChecked("","",0);
}
 
 
 dhtmlXTreeObject.prototype._getAllChecked=function(htmlNode,list,mode){
 if(!htmlNode)htmlNode=this.htmlNode;
 if(((mode)&&(htmlNode.checkstate==1))||((!mode)&&(htmlNode.checkstate>0))){if(list)list+=","+htmlNode.id;else list=htmlNode.id;}
 var j=htmlNode.childsCount;
 for(var i=0;i<j;i++)
{
 list=this._getAllChecked(htmlNode.childNodes[i],list,mode);
};

 if(list){
     document.getElementById("list11").value =  list;
     
return list;
}else return "";
};
dhtmlXTreeObject.prototype._getAllCheckedNames=function(htmlNode,list1,mode){
 if(!htmlNode)htmlNode=this.htmlNode;
 if(((mode)&&(htmlNode.checkstate==1))||((!mode)&&(htmlNode.checkstate>0))){if(list1)list1+=","+htmlNode.label;else list1=htmlNode.label;}
 var j=htmlNode.childsCount;
 for(var i=0;i<j;i++)
{
 list1=this._getAllCheckedNames(htmlNode.childNodes[i],list1,mode);
};
 if(list1){
     document.getElementById("list12").value =  list1;
return list1;
}else return "";
};
 
 dhtmlXTreeObject.prototype.deleteChildItems=function(itemId)
{
 var sNode=this._globalIdStorageFind(itemId);
 if(!sNode)return;
 var j=sNode.childsCount;
 for(var i=0;i<j;i++)
{
 this._deleteNode(sNode.childNodes[0].id,sNode.childNodes[0]);
};
};
 
 
dhtmlXTreeObject.prototype.deleteItem=function(itemId,selectParent){
 this._deleteItem(itemId,selectParent);
}
 
dhtmlXTreeObject.prototype._deleteItem=function(itemId,selectParent,skip){
 selectParent=convertStringToBoolean(selectParent);
 var sNode=this._globalIdStorageFind(itemId);
 if(!sNode)return;
 if(selectParent)this.selectItem(this.getParentId(this.getSelectedItemId()),1);
 if(!skip){
 this._globalIdStorageRecSub(sNode);
};
 var zTemp=sNode.parentObject;
 this._deleteNode(itemId,sNode,skip);
 this._correctPlus(zTemp);
 this._correctLine(zTemp);
};
 
 
 dhtmlXTreeObject.prototype._globalIdStorageRecSub=function(itemObject){
 for(var i=0;i<itemObject.childsCount;i++)
{
 this._globalIdStorageRecSub(itemObject.childNodes[i]);
 this._globalIdStorageSub(itemObject.childNodes[i].id);
};
 this._globalIdStorageSub(itemObject.id);
};
 
 
 dhtmlXTreeObject.prototype.insertNewNext=function(parentItemId,itemId,itemName,itemActionHandler,image1,image2,image3,optionStr,childs){
 var sNode=this._globalIdStorageFind(parentItemId);
 if(!sNode)return(0);
 this._attachChildNode(0,itemId,itemName,itemActionHandler,image1,image2,image3,optionStr,childs,sNode);
};

 
 
 
 dhtmlXTreeObject.prototype.getItemIdByIndex=function(itemId,index){
 var z=this._globalIdStorageFind(itemId);
 if(!z)return 0;
 var temp=z.htmlNode.childNodes[0].childNodes[0];
 while(index>0)
{
 temp=temp.nextSibling;
 if((!temp)||(!temp.nodem))return 0;
 index--;
}
 return temp.nodem.id;
};
 
 dhtmlXTreeObject.prototype.getChildItemIdByIndex=function(itemId,index){
 var sNode=this._globalIdStorageFind(itemId);
 if(!sNode)return(0);
 if(this.hasChildren(itemId)<index)return 0;
 return sNode.htmlNode.childNodes[0].childNodes[index].nodem.id;
};


 
 

 
 dhtmlXTreeObject.prototype.setDragHandler=function(func){if(typeof(func)=="function")this.dragFunc=func;else this.dragFunc=eval(func);};
 
 
 dhtmlXTreeObject.prototype._clearMove=function(htmlNode){
 if(htmlNode.parentObject.span){
 htmlNode.parentObject.span.className='standartTreeRow';
 if(htmlNode.parentObject.acolor)htmlNode.parentObject.span.style.color=htmlNode.parentObject.acolor;
}
};
 
 
 dhtmlXTreeObject.prototype.enableDragAndDrop=function(mode){this.dragAndDropOff=convertStringToBoolean(mode);};
 
 
 dhtmlXTreeObject.prototype._setMove=function(htmlNode){
 if(htmlNode.parentObject.span){
 htmlNode.parentObject.span.className='selectedTreeRow';
 if(htmlNode.parentObject.scolor)htmlNode.parentObject.span.style.color=htmlNode.parentObject.scolor;
}
};
 
 
 
dhtmlXTreeObject.prototype._createDragNode=function(htmlObject){
 dhtmlObject=htmlObject.parentObject;
 if(this.lastSelected)this._clearMove(this.lastSelected);
 var dragSpan=document.createElement('div');
 dragSpan.appendChild(document.createTextNode(dhtmlObject.label));
 dragSpan.style.position="absolute";
 dragSpan.className="dragSpanDiv";
 return dragSpan;
}




 
dhtmlXTreeObject.prototype._preventNsDrag=function(e){
 if((e)&&(e.preventDefault)){e.preventDefault();return false;}
}

dhtmlXTreeObject.prototype._drag=function(sourceHtmlObject,dhtmlObject,targetHtmlObject){
 if(!targetHtmlObject.parentObject){targetHtmlObject=this.htmlNode.htmlNode.childNodes[0].childNodes[0].childNodes[1].childNodes[0];}
 else this._clearMove(targetHtmlObject);
 if(dhtmlObject.lastSelected)dhtmlObject._setMove(dhtmlObject.lastSelected);
 if((!this.dragMove)||(this.dragMove()))this._moveNode(sourceHtmlObject.parentObject,targetHtmlObject.parentObject);

}

dhtmlXTreeObject.prototype._dragIn=function(htmlObject,shtmlObject){
 if(!htmlObject.parentObject)
{
 
 return htmlObject;
}
 if((!this._checkParenNodes(shtmlObject.parentObject.id,htmlObject.parentObject,shtmlObject.parentObject))&&(htmlObject.parentObject.id!=shtmlObject.parentObject.id))
{
 this._setMove(htmlObject);
 if(this._getOpenState(htmlObject.parentObject)<0)
 this._autoOpenTimer=window.setTimeout(new callerFunction(this._autoOpenItem,this),1000);
 this._autoOpenId=htmlObject.parentObject.id;
 return htmlObject;
}
 else return 0;
}
dhtmlXTreeObject.prototype._autoOpenItem=function(e,treeObject){
 treeObject.openItem(treeObject._autoOpenId);
};
dhtmlXTreeObject.prototype._dragOut=function(htmlObject){
 if(!htmlObject.parentObject)return 0;
this._clearMove(htmlObject);if(this._autoOpenTimer)clearTimeout(this._autoOpenTimer);}



	</script>

        <body onload="treeload()">
	<html:form action="/assign_privilege1"  method="post">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
	<table>
		<input type="hidden" id="mlitext"/>
		<tr>
                    <td valign="top" align="center" width="400px">
				&nbsp;&nbsp;&nbsp;&nbsp;<div id="treeboxbox_tree2" style="width:300; height:618;background-color:#f5f5f5;border :1px solid Silver;; overflow:auto;"></div>

			</td>
			<td  style="padding-left:25" valign="top" align="center" width="800px">
			
						<br>
						<br>
                                                <table width="400px"   valign="top" align="center" border="1px">
                                                    <tr><td class="headerStyle" height="25px" align="center">Assign/Change Privileges</td></tr>
        <tr><td   width="400px" height="150px" valign="top" style="" class="txt2" align="center">
                
                    
						<br>
						<br>
	

                                                <%if (request.getAttribute("msg")!=null){
                                                    String msg = (String)request.getAttribute("msg") ;
                                                   // out.println("<script language=\"javascript\">alert(\""+msg+"\");</script>");
                                                       }%>

			<a href="javascript:void(0);"  onclick="alert('You Assigned priveleges to the following activities:\n' + tree.getAllCheckedNames());">View list of checked Privilege</a><br><br>
                        <a href="javascript:void(0);"  onclick="show_priveleges();">Show previously assigned privileges</a><br><br>
                        <input name="privilege_list" id="list11" type="hidden" />
                       <!--<input type="checkbox" value="" onchange="return setAllUncheck();"/>Uncheck All Privileges&nbsp;&nbsp;-->
                        <input name="privilege_list1" id="list12" type="hidden" />
                        <input name="button" id="list12" type="hidden" value="<%=button%>" />
                        <input name="staff_id" id="staff_id" type="hidden" value="<%=staff_id%>" />
                        <input name="staff_name" id="staff_name" type="hidden" value="<%=staff_name%>" />
                        <input name="submit_button" id="submit_button" type="submit" class="txt2"  value="Submit" onclick="return validate();" />
                       <input name="back" id="back" type="button" class="txt2"  value="Back" onclick="send();"/>
                        

                        

                    </td></tr></table>
                        </td></tr></table>
</html:form>


	<script>
                       function setAllUncheck(){
                        tree.selectItem(100, 1);
                        tree.setCheck(tree.getSelectedItemId(),0);
                    
                        for(var acq_i=101;acq_i<200;acq_i++)
                            {
                             tree.selectItem(acq_i,1 );
                             tree.setCheck(tree.getSelectedItemId(),0);
                            }
                         
                        tree.selectItem(200, 1);
                        tree.setCheck(tree.getSelectedItemId(),0);
                    
                        for(var cat_i=201;cat_i<300;cat_i++)
                            {
                            
                             tree.selectItem(cat_i,1 );
                             tree.setCheck(tree.getSelectedItemId(),0);
                            }

                 // show privileges for circulation
                         
                         
                        tree.selectItem(300, 1);
                        tree.setCheck(tree.getSelectedItemId(),0);
                    
                        for(var cir_i=301;cir_i<400;cir_i++)
                            {
                             tree.selectItem(cir_i,1 );
                             tree.setCheck(tree.getSelectedItemId(),0);
                            }
                            

                  // show privileges for serial
                         
                        tree.selectItem(400, 1);
                        tree.setCheck(tree.getSelectedItemId(),0);
                    
                        for(var ser_i=401;ser_i<500;ser_i++)
                            {
                             tree.selectItem(ser_i,1);
                             tree.setCheck(tree.getSelectedItemId(),0);
                            }
        //Check the Role of User whom privilege has to Modified and willnot display these options if staff role is selected
             
                         
                         // show privileges for Administrator

                        tree.selectItem(1, 1);
                        tree.setCheck(tree.getSelectedItemId(),0);
                    
                        tree.selectItem(3, 1);
                        tree.setCheck(tree.getSelectedItemId(),0);

                        
                        tree.selectItem(2, 1);
                        tree.setCheck(tree.getSelectedItemId(),0);

                       }
                       function validate()
                       {
                           
                           var list= tree.getAllCheckedNames();
                            tree.getAllChecked();
                           if (list.length==0)
                               {
                               alert("No Specific Privilege Yet been selected");
                               return false;
                               }
                               <%
                                       if(staff_role.equals("admin")==true || staff_role.equals("dept-admin")==true){ %>
                           var x = list.match(/administrator/gi);
                           if(x==null){
                               
                               var t = confirm("Admin not selected:\n User Role Converts into staff\nPrivileges from system utilities and System setup are also removed?\n Do you want to continue");
                               if (t==true){
                                tree.selectItem(2, 0);
                                tree.setCheck(tree.getSelectedItemId(),0);
                                 tree.selectItem(3, 0);
                                tree.setCheck(tree.getSelectedItemId(),0);
                                tree.getAllChecked();
                                   return true;
                               }
                                   return false;
                           }<%}%>
                           return true;
                       }
                       function selectedTree()
                       {
                           
                       }
                       function send()
                        {
                        window.location="<%=request.getContextPath()%>/admin/assign_privilege.jsp";
                        return false;
                        }
			
                        function treeload()
                        {

                       treeload1();
                       //             <%-- int ii=0;while(ii<100){ii++;}--%>
                       //show_priveleges();
                        }
                       function treeload1()
                       {
                          
			tree=new dhtmlXTreeObject("treeboxbox_tree2","100%","100%",0);
			tree.setImagePath("<%=request.getContextPath()%>/images/");
			tree.enableCheckBoxes(1);
                        
			tree.enableThreeStateCheckboxes(true);
                                       <%
                                       System.out.println(staff_role + "  staffId="+staff_id);
                        if(staff_role.equalsIgnoreCase("staff")==true){ %>
                        tree.loadXML("<%=request.getContextPath()%>/staff.xml");
                        <%}if(staff_role.equalsIgnoreCase("admin")){%>
                        tree.loadXML("<%=request.getContextPath()%>/admin1.xml");
                        <%}if(staff_role.equalsIgnoreCase("dept-admin")==true){%>
                            
                            tree.loadXML("<%=request.getContextPath()%>/dept_admin.xml");
                            <%} if(staff_role.equalsIgnoreCase("dept-staff")==true){%>
                                
                       tree.loadXML("<%=request.getContextPath()%>/dept_staff.xml");

                            <%}%>
                       }
                       function show_priveleges()
                       {
                           // show privileges for acquisition
                         <%

//Privilege pri =(Privilege)session.getAttribute("privilege");
//AcqPrivilege acq =(AcqPrivilege)session.getAttribute("acq_privilege");

//CatPrivilege cat =(CatPrivilege)session.getAttribute("cat_privilege");
//CirPrivilege cir =(CirPrivilege)session.getAttribute("cir_privilege");
//SerPrivilege ser =(SerPrivilege)session.getAttribute("ser_privilege");
PrivilegeDAO privdao=new PrivilegeDAO();
AcqPrivilegeDAO acqprivdao=new AcqPrivilegeDAO();
CatPrivilegeDAO catprivdao=new CatPrivilegeDAO();
CirPrivilegeDAO cirprivdao=new CirPrivilegeDAO();
SerPrivilegeDAO serprivdao=new SerPrivilegeDAO();




 List pri=privdao.getPrivilege1(library_id, sublibrary_id, staff_id);
 List acq=acqprivdao.getPrivilege1(library_id, sublibrary_id, staff_id);
 List cat=catprivdao.getPrivilege1(library_id,sublibrary_id, staff_id);
 List cir=cirprivdao.getPrivilege1(library_id, sublibrary_id, staff_id);
 List ser=serprivdao.getPrivilege1(library_id, sublibrary_id, staff_id);
System.out.println("privelege"+pri.toString());
System.out.println("acquisition"+acq.toString());
                         int iii=0;
                         List acq_pri = (List)pri.get(0);
                         String acq_pri1 = (String)acq_pri.get(3);
                         System.out.println("acq_pri1"+acq_pri1);
                         if(acq_pri1.equalsIgnoreCase("false"))
                            {
                            iii=1;
                            %>
                        tree.selectItem(100, <%=iii%>);
                        tree.setCheck(tree.getSelectedItemId(),<%=iii%>);
                    <%








                        for(int acq_i=101;acq_i<200;acq_i++)
                            {
                            int acq_check=0;
                            List acq1 = (List)acq.get(0);
                            String acq2 = (String)acq1.get(acq_i-98);
        if(acq2.equalsIgnoreCase("false")){acq_check=1;}else acq_check=0;%>
                             tree.selectItem(<%=acq_i%>,1 );
                             tree.setCheck(tree.getSelectedItemId(),<%=acq_check%>);<%
                             //String acqs = "acq_"+acq_i;
                          //  System.out.println("acq_" + acq_i + "=" + acq1);
                          }
                         }
                         // show privileges for catloging
                         iii=0;
                         String pricat = (String)acq_pri.get(4);
                         if(pricat.equalsIgnoreCase("false"))
                            {
                            iii=1;
                            %>
                        tree.selectItem(200, <%=iii%>);
                        tree.setCheck(tree.getSelectedItemId(),<%=iii%>);
                    <%
                    List cat_lst = (List)cat.get(0);
                    for(int cat_i=201;cat_i<300;cat_i++)
                            {
                            int cat_check=0;
                            String cat_val = (String)cat_lst.get(cat_i-198);
                            if(cat_val.equalsIgnoreCase("false"))cat_check=1;else cat_check=0;%>
                             tree.selectItem(<%=cat_i%>,1 );
                             tree.setCheck(tree.getSelectedItemId(),<%=cat_check%>);<%
                           //   System.out.println("cat_" + cat_i + "=" + CatPrivilegeDAO.getValue(cat, cat_i));
                            }
                            }

                 // show privileges for circulation
                         iii=0;
                         String pricir = (String)acq_pri.get(5);
                        if(pricir.equalsIgnoreCase("false"))
                            {
                            iii=1;
                            %>
                        tree.selectItem(300, <%=iii%>);
                        tree.setCheck(tree.getSelectedItemId(),<%=iii%>);
                    <%
                        List cir_pri = (List)cir.get(0);
                        for(int cir_i=301;cir_i<400;cir_i++)
                            {
                            int cir_check=0;
                            String cir_val = (String)cir_pri.get(cir_i-298);

                        if(cir_val.equalsIgnoreCase("false")) cir_check=1; else cir_check=0;%>
                             tree.selectItem(<%=cir_i%>,1 );
                             tree.setCheck(tree.getSelectedItemId(),<%=cir_check%>);<%
                          //   System.out.println("cir_" + cir_i + "=" + CirPrivilegeDAO.getValue(cir, cir_i));
                            }
                            }

                  // show privileges for serial
                         iii=0;
                         String priser = (String)acq_pri.get(6);
                         if(priser.equalsIgnoreCase("false"))
                            {
                            iii=1;
                            %>
                        tree.selectItem(400, <%=iii%>);
                        tree.setCheck(tree.getSelectedItemId(),<%=iii%>);
                    <%
                        List ser_lst = (List)ser.get(0);
                        for(int ser_i=401;ser_i<500;ser_i++)
                            {
                            int ser_check=0;
                            String ser_val = (String)ser_lst.get(ser_i-398);
                            if(ser_val.equalsIgnoreCase("false")) ser_check=1; else ser_check=0;%>
                             tree.selectItem(<%=ser_i%>,1 );
                             tree.setCheck(tree.getSelectedItemId(),<%=ser_check%>);<%
                           //    System.out.println("ser_" + ser_i + "=" + SerPrivilegeDAO.getValue(ser, ser_i));
                            }
                            }
                         
        //Check the Role of User whom privilege has to Modified and willnot display these options if staff role is selected
             
                         
                         // show privileges for Administrator

                         iii=0;
                         String priadm = (String)acq_pri.get(7);
                         if(priadm.equalsIgnoreCase("false"))
                            {
                            iii=1;
                            %>
                        tree.selectItem(1, 1);
                        tree.setCheck(tree.getSelectedItemId(),<%=iii%>);
                    <%}
                    // show privileges for Utilities
                         iii=0;
                         String priutl = (String)acq_pri.get(9);
                         if(priutl.equalsIgnoreCase("false"))
                            {
                             iii=1;
                      %>
                        tree.selectItem(3, 1);
                        tree.setCheck(tree.getSelectedItemId(),<%=iii%>);

                        <%}
                    // show privileges for System Setup
                         iii=0;
                         String priset = (String)acq_pri.get(8);
                         if(priset.equalsIgnoreCase("false"))
                            {
                            iii=1;
                            %>
                        tree.selectItem(2, 1);
                        tree.setCheck(tree.getSelectedItemId(),<%=iii%>);

                        <%}
                    // show privileges for OPAC
                      //   iii=0;
                       //  if(pri.getString("opac").equalsIgnoreCase("false"))
                      //      {
                      //      iii=1;
                      //      %>
                     //   tree.selectItem(4, 1);
                    //    tree.setCheck(tree.getSelectedItemId(),<%=iii%>);
                     //  
                       }
	</script>

    </div>
  
</body>

</html>


           
       
