

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show PartyDepartment</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">PartyDepartment List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New PartyDepartment</g:link></span>
        </div>
        <div class="body">
            <h1>Show PartyDepartment</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyDepartmentInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Department Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyDepartmentInstance, field:'departmentCode')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyDepartmentInstance, field:'name')}</td>
                            
                        </tr>
                                
                        <tr class="prop">
                            <td valign="top" class="name">Party:</td>
                            
                            <td valign="top" class="value"><g:link controller="party" action="show" id="${partyDepartmentInstance?.party?.code}">${partyDepartmentInstance?.party?.code?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${partyDepartmentInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
        </div>
    </body>
</html>
