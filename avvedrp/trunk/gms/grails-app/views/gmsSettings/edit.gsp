


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gmsSettings.label', default: 'GmsSettings')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${gmsSettingsInstance}">
            <div class="errors">
                <g:renderErrors bean="${gmsSettingsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${gmsSettingsInstance?.id}" />
                <g:hiddenField name="version" value="${gmsSettingsInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                         
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="gmsSettings.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gmsSettingsInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${gmsSettingsInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="value"><g:message code="gmsSettings.value.label" default="Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gmsSettingsInstance, field: 'value', 'errors')}">
                                    <g:textField name="value" value="${gmsSettingsInstance?.value}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
