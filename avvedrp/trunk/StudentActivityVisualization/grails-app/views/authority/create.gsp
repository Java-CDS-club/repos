<meta name="layout" content="main" />	
<!-- ##################################  Layout body starts here  ###########################################-->
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		    <g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN'}">
		<g:menu/>
		</g:if >	
		</div>

	<div id="content"> <!-- Start of content div -->	
<!-- Middle area starts here -->	
	<br />
       <div align="center"><h3>New Authority</h3></div>
		<br />	<br />
		<div align="center">					
				<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
				</g:if>
				<g:hasErrors bean="${authority}">
				<div class="errors">
				<g:renderErrors bean="${authority}" as="list" />
				</div>
				</g:hasErrors>

				<g:form action="save">
				<table align="center">
				<tbody>
				<tr class="prop">
				<td valign="top" class="name"><label for="authority">Authority Name:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'authority','errors')}">
				<input type="text" id="authority" name="authority" value="${authority?.authority?.encodeAsHTML()}"/>
				</td>
				</tr>

				<tr class="prop">
				<td valign="top" class="name"><label for="description">Description:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'description','errors')}">
				<input type="text" id="description" name="description" value="${authority?.description?.encodeAsHTML()}"/>
				</td>
				</tr>
				<tr class="prop">
				<td valign="top" class="name" colspan="3">
				<div class="buttons">
				<span><input class="save" type="submit" value="Create" /></span>
				</div>
				</td>

				</tr>
				</tbody>
				</table>
				</g:form>     
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br />
<!-- Middle area ends here -->		
  </div> <!-- End of content div -->
	</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->