


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'eligibilityCheck.label', default: 'EligibilityCheck')}" />
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
            <g:hasErrors bean="${eligibilityCheckInstance}">
            <div class="errors">
                <g:renderErrors bean="${eligibilityCheckInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${eligibilityCheckInstance?.id}" />
                <g:hiddenField name="version" value="${eligibilityCheckInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="eligibilityCheck.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: eligibilityCheckInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${eligibilityCheckInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="eligibilityCriteria"><g:message code="eligibilityCheck.eligibilityCriteria.label" default="Eligibility Criteria" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: eligibilityCheckInstance, field: 'eligibilityCriteria', 'errors')}">
                                    <g:select name="eligibilityCriteria.id" from="${EligibilityCriteria.list()}" optionKey="id" value="${eligibilityCheckInstance?.eligibilityCriteria?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proposal"><g:message code="eligibilityCheck.proposal.label" default="Proposal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: eligibilityCheckInstance, field: 'proposal', 'errors')}">
                                    <g:select name="proposal.id" from="${Proposal.list()}" optionKey="id" value="${eligibilityCheckInstance?.proposal?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="qualifiedYesNo"><g:message code="eligibilityCheck.qualifiedYesNo.label" default="Qualified Yes No" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: eligibilityCheckInstance, field: 'qualifiedYesNo', 'errors')}">
                                    <g:textField name="qualifiedYesNo" value="${eligibilityCheckInstance?.qualifiedYesNo}" />
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
