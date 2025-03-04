<div class="dialog">
                    <table height="30%">
                        <tbody>
                        <input type="hidden" name="proposalApplication.id" value="${proposalApplicationInstance?.id}">
                        <input type="hidden" name="actionName" value="${params.action}">
                         <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PartIPersonalDetails.head"/></div>
                                </td>
                                
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.Name.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.name}
                                    
                                    </label>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.Designation.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.designation}
                                    
                                    </label>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.Organisation.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.organisation}
                                    
                                    </label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.PostalAddress.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'postalAddress','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.postalAddress}
                                    
                                    </label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.City.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'City','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.city}
                                    
                                    </label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.State.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'State','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.state}
                                    
                                    </label>
                                </td>
                            </tr>
                            
                               <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.ZipCode.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'zipCode','errors')}">
                                
                                    <label for="name">
                                     ${proposalApplicationInstance?.zipCode}
                                    
                                    </label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.Phone.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'phone','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.phone}
                                    
                                    </label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.Fax.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'fax','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.fax}
                                    
                                    </label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.Email.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'email','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.email}
                                    
                                    </label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.Mobile.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'mobile','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.mobile}
                                    
                                    </label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="default.ProposalCategory.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(proposalApplicationInstance,field:'proposalCategory.name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationInstance?.proposalCategory?.name}
                                    
                                    </label>
                                </td>
                            </tr>
                                                     
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PARTIInformationRelatingDepartment.head"/></div>
                                </td>
                           </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==2}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;" >
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PARTIIInformationRelatingDepartment.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==3}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PARTFiveAboutResearchProject.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==4}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PartFiveDetailProjectReport.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==5}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PartSixUploadDocuments.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==6}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                             <tr class="prop">
                                <td valign="top" class="prname">
                                    <g:link action="downloadAttachments" controller="attachments" id="${attachmentsInstanceGetCV?.id}" target="_blank"><label for="name"><g:message code="default.ClickHereforCV.label"/></label></g:link>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                &nbsp;
                                    
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <g:link action="downloadAttachments" controller="attachments" id="${attachmentsInstanceGetDPR?.id}" target="_blank"><label for="name"><g:message code="default.ClickHereforDPR.label"/></label></g:link>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                &nbsp;
                                    
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PartSevenDPRSummary.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==7}">
                              <tr class="prop">
                                <td valign="top" style="width:50%;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            
                        </g:each>
                            
                         
                        </tbody>
                    </table>
                     
                </div>