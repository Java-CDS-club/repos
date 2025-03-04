

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap')}" />
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
                            <td valign="top" class="name"><g:message code="proposalApprovalAuthorityMap.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalAuthorityMap.proposalType.label" default="Proposal Type" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "proposalType")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalAuthorityMap.proposalId.label" default="Proposal Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "proposalId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalAuthorityMap.approveOrder.label" default="Approve Order" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "approveOrder")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalAuthorityMap.processRestartOrder.label" default="Process Restart Order" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "processRestartOrder")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalAuthorityMap.remarks.label" default="Remarks" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "remarks")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalAuthorityMap.activeYesNo.label" default="Active Yes No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "activeYesNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalAuthorityMap.approvalAuthority.label" default="Approval Authority" /></td>
                            
                            <td valign="top" class="value"><g:link controller="approvalAuthority" action="show" id="${proposalApprovalAuthorityMapInstance?.approvalAuthority?.id}">${proposalApprovalAuthorityMapInstance?.approvalAuthority?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${proposalApprovalAuthorityMapInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
