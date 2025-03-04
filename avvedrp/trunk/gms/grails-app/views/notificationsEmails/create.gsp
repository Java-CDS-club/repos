

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create NotificationsEmails</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">NotificationsEmails List</g:link></span>
        </div>
        <div class="body">
            <h1>Create NotificationsEmails</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${notificationsEmailsInstance}">
            <div class="errors">
                <g:renderErrors bean="${notificationsEmailsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="notificationId">Notification Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsEmailsInstance,field:'notificationId','errors')}">
                                    <input type="text" id="notificationId" name="notificationId" value="${fieldValue(bean:notificationsEmailsInstance,field:'notificationId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="partyId">Party Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsEmailsInstance,field:'partyId','errors')}">
                                    <g:select optionKey="id" from="${Party.list()}" name="partyId.id" value="${notificationsEmailsInstance?.partyId?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
