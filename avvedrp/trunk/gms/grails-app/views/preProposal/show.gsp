

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposal.label', default: 'PreProposal')}" />
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
                            <td valign="top" class="name"><g:message code="preProposal.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: preProposalInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.projectTitle.label" default="Project Title" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: preProposalInstance, field: "projectTitle")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.remarks.label" default="Remarks" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: preProposalInstance, field: "remarks")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.preProposalSavedDate.label" default="Pre Proposal Saved Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${preProposalInstance?.preProposalSavedDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.preProposalCancelDate.label" default="Pre Proposal Cancel Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${preProposalInstance?.preProposalCancelDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.preProposalStatus.label" default="Pre Proposal Status" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: preProposalInstance, field: "preProposalStatus")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.preProposalLevel.label" default="Pre Proposal Level" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: preProposalInstance, field: "preProposalLevel")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.preProposalForm.label" default="Pre Proposal Form" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: preProposalInstance, field: "preProposalForm")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.activeYesNo.label" default="Active Yes No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: preProposalInstance, field: "activeYesNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.department.label" default="Department" /></td>
                            
                            <td valign="top" class="value"><g:link controller="department" action="show" id="${preProposalInstance?.department?.id}">${preProposalInstance?.department?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.faculty.label" default="Faculty" /></td>
                            
                            <td valign="top" class="value"><g:link controller="faculty" action="show" id="${preProposalInstance?.faculty?.id}">${preProposalInstance?.faculty?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.institution.label" default="Institution" /></td>
                            
                            <td valign="top" class="value"><g:link controller="institution" action="show" id="${preProposalInstance?.institution?.id}">${preProposalInstance?.institution?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="preProposal.proposalCategory.label" default="Proposal Category" /></td>
                            
                            <td valign="top" class="value"><g:link controller="proposalCategory" action="show" id="${preProposalInstance?.proposalCategory?.id}">${preProposalInstance?.proposalCategory?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${preProposalInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
