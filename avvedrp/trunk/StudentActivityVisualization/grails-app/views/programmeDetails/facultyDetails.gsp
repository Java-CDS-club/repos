<div id="head">
		<meta name="layout" content="main" />
</div>
<div class="body">
		<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${facultyInstance}">
				<div class="errors">
						<g:renderErrors bean="${facultyInstance}" as="list" />
				</div>
		</g:hasErrors>
		<g:form controller="programmeDetails" >
				<center>
						<div class="dialog">
								<table cellpadding="0">
										<br /><br /><h3><g:message code="Faculty Details"/></h3> <br />
										<tbody>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<input type="hidden" name="InstId" value="${session.InstId}" />
														<td valign="top" class="name" >
																<label for="name" ><g:message code="Faculty Name"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:facultyInstance,field:'facultyName','errors')}">
																<input type="text" size="25" id="facultyName" name="facultyName" value="${fieldValue(bean:facultyInstance,field:'facultyName')}"/>
														</td>
														<td colspan="2">&nbsp;</td>
														<td valign="top" class="name" >
																<label for="name" ><g:message code="Faculty code"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:facultyInstance,field:'facultyCode','errors')}">
																<input type="text" size="25" id="facultyCode" name="facultyCode" value="${fieldValue(bean:facultyInstance,field:'facultyCode')}"/>
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
										</tbody>      
								</table>
						</div>
						<div>
								<g:actionSubmit class="pushbutton" value="Save" action="saveFaculty" />
								<input class="pushbutton" type='reset' value='Reset' />
						</div>
				</center>
		</g:form>
</div>
<div class="body">
		<div class="list">
				<center>
				<fieldset style="width:50%; border-color:transparent; padding:0;">
						<table frame="box">
								<thead>
										<tr>
												<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
												
												<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="facultyName" title="${message(code: 'Category')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="facultyCode" title="${message(code: 'Number Of Quarters')}" style="font-size:12px;"/>
												
												
												<th><g:message code="Edit" style="font-size:12px;"/></th>
										   
										</tr>
								</thead>
										    
								<tbody>
								
										<g:each in="${facultyInstanceList}" status="i" var="facultyInstance">
												<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
												
														<td class="listing">${i+1}</td>
														
														<td class="listing">${fieldValue(bean:facultyInstance, field:'institutionDetails.institutionName')}</td>
														    
														<td class="listing">${fieldValue(bean:facultyInstance, field:'facultyName')}</td>
														    
														<td class="listing">${fieldValue(bean:facultyInstance, field:'facultyCode')}</td>
														 
														<td class="listing"><g:link action="editFaculty" id="${facultyInstance.id}"><g:message code="Edit"/></g:link></td>
													
												</tr>
										</g:each>
								</tbody>
						</table>
				</fieldset>
				</center>
		</div>
</div>
