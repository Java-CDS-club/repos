

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fundTransfer.label', default: 'FundTransfer')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: fundTransferInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.createdDate.label" default="Created Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${fundTransferInstance?.createdDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.modifiedDate.label" default="Modified Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${fundTransferInstance?.modifiedDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.amount.label" default="Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: fundTransferInstance, field: "amount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.createdBy.label" default="Created By" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: fundTransferInstance, field: "createdBy")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.dateOfTransfer.label" default="Date Of Transfer" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${fundTransferInstance?.dateOfTransfer}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.grantAllocation.label" default="Grant Allocation" /></td>
                            
                            <td valign="top" class="value"><g:link controller="grantAllocation" action="show" id="${fundTransferInstance?.grantAllocation?.id}">${fundTransferInstance?.grantAllocation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.modeOfPayment.label" default="Mode Of Payment" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: fundTransferInstance, field: "modeOfPayment")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fundTransfer.modifiedBy.label" default="Modified By" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: fundTransferInstance, field: "modifiedBy")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${fundTransferInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
