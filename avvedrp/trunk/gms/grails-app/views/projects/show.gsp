

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Projects</title>
    </head>
    <body>
        <div class="body">
            <h1>Show Projects</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table width="950">
                    <tbody>                   
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectsInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Parent:</td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${projectsInstance?.parent?.id}">${projectsInstance?.parent?.code}</g:link></td>
                            
                        </tr>
                      <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectsInstance, field:'name')}</td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectsInstance, field:'code')}</td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Active:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectsInstance, field:'activeYesNo')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${projectsInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
