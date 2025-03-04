

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantPeriod.EditGrantPeriod.head"/></title>         
    </head>    
    <body>
        <div class="wrapper">
          <div class="body">
            <h1><g:message code="default.GrantPeriod.EditGrantPeriod.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantPeriodInstance}">
              <div class="errors">
                <g:renderErrors bean="${grantPeriodInstance}" as="list" />
              </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${grantPeriodInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label"/>:</label>
                                    <label for="name" style="color:red;font-weight:bold"> * </label>
                                    
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:grantPeriodInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate"><g:message code="default.StartDate.label"/>:</label>
                                    <label for="startDate" style="color:red;font-weight:bold"> * </label>
                                    
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'startDate','errors')}">
                                <calendar:datePicker id="startDate" name="startDate" defaultValue="${new Date()}" value="${grantPeriodInstance?.startDate}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endDate"><g:message code="default.EndDate.label"/>:</label>
                                    <label for="endDate" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'endDate','errors')}">
                                    <calendar:datePicker id="endDate" name="endDate" value="${grantPeriodInstance?.endDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                            </td>
                           <!--=============== Commented on 12-11-2010,to hide active field ==============
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo"><g:message code="default.Active.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'activeYesNo','errors')}">
                                         <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:grantPeriodInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                            ======================================================================-->
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="defaultYesNo"><g:message code="default.Default.label"/>:</label>
                                    
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'defaultYesNo','errors')}">
                                         <g:select name="defaultYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:grantPeriodInstance,field:'defaultYesNo')}" />
                                </td>
                            </tr> 
                    
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateGrantPeriod()"  /></span>
					<span class="button">
					<g:actionSubmit class="delete"  action="delete" 
					value="${message(code: 'default.Delete.button')}" 
					onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</span>	
                </div>
            </g:form>
          </div>
        </div>
    </body>
</html>
