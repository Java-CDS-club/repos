

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'budgetMaster.label', default: 'BudgetMaster')}" />
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
                            <td valign="top" class="name"><g:message code="budgetMaster.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetMasterInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetMaster.financialYear.label" default="Financial Year" /></td>
                            
                            <td valign="top" class="value"><g:link controller="financialYear" action="show" id="${budgetMasterInstance?.financialYear?.id}">${budgetMasterInstance?.financialYear?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetMaster.party.label" default="Party" /></td>
                            
                            <td valign="top" class="value"><g:link controller="party" action="show" id="${budgetMasterInstance?.party?.id}">${budgetMasterInstance?.party?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetMaster.budgetCode.label" default="Budget Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetMasterInstance, field: "budgetCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetMaster.budgetDescription.label" default="Budget Description" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetMasterInstance, field: "budgetDescription")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetMaster.totalBudgetAmount.label" default="Total Budget Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetMasterInstance, field: "totalBudgetAmount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetMaster.title.label" default="Title" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetMasterInstance, field: "title")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetMaster.activeYesNo.label" default="Active Yes No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetMasterInstance, field: "activeYesNo")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${budgetMasterInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
