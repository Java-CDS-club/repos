<meta name="layout" content="main" />	
<!-- ##################################  Layout body starts here  ###########################################-->
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		       <g:menu/>
		</div>

	<div id="content">    
	<div align="right" style="padding-right:30px;height:40px;"><strong>DATABASE UPDATED ON : <font color="#B27115">${session.last_update}</font></strong></div>
	
<!-- Middle area starts here -->	
		
		<div>	
		                      <g:javascript src="swfobject.js"/>
                        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="600">
                        <param name="movie" value="${resource(dir:'charts',file:'superpanel_staff.swf')}" />
                        <param name="quality" value="high" />
                        <param name="bgcolor" value="#ffffff" />
                        <param name="allowScriptAccess" value="sameDomain" />
                        <param name="allowFullScreen" value="true" />
                        <!--[if !IE]>-->
                        <object type="application/x-shockwave-flash" data="${resource(dir:'charts',file:'superpanel_staff.swf')}" width="100%" height="600">
                        <param name="quality" value="high" />
                        <param name="bgcolor" value="#ffffff" />
                        <param name="allowScriptAccess" value="sameDomain" />
                        <param name="allowFullScreen" value="true" />
                        <!--<![endif]-->
                        <p>
                        Either scripts and active content are not permitted to run or Adobe Flash Player version
                        9.0.28 or greater is not installed.
                        </p>
                        <a href="http://www.adobe.com/go/getflashplayer">
                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
                        </a>
                        <!--[if !IE]>-->
                        </object>
                        <!--<![endif]-->
                        </object>
          
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br /><br /><br />
<!-- Middle area ends here -->		
   </div> <!-- End of content div -->
	</div>
	 <g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->