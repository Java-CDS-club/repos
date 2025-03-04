  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantExpense.CreateGrantExpense.head"/></title>         
    </head>
    <body> 
       <div class="wrapper"> 
	   <g:subMenuList/>   
         <div class="proptable">    
           <table width="100%" align="left">
             <tr>
             
	            <td ><g:message code="default.ProjectCode.label"/>:</td>
	            <td><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
	            <td ><g:message code="default.AmountAllocated.label"/>:</td>
	            <td ><strong>
	            		<g:message code="default.Rs.label" />
	            		${currencyFormat.ConvertToIndainRS(projectsInstance?.totAllAmount)}
            		</strong>
        		</td>
<td>
      	<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">   
</td> 	        
             </tr> 
             <tr>
             <td><g:message code="default.CurrentBalance.label"/>:</td>
             <td ><strong>
	            		<g:message code="default.Rs.label" />
	            		${currencyFormat.ConvertToIndainRS(grantExpenseInstance.currentBalance)}
            		</strong>
        		</td>
        	<td><g:message code="default.ExternalAmountReceived.label"/>:</td>
             <td ><strong>
	            		<g:message code="default.Rs.label" />
	            		${sum}
            		</strong>
        		</td>

              </tr>
      	   </table> 
      	 </div>  
         <div>
          <g:hasErrors bean="${grantExpenseInstance}">
            <div class="errors">
              <g:renderErrors bean="${grantExpenseInstance}" as="list" />
            </div>
          </g:hasErrors>
         </div>  
         <table>
           <tr>
              <td rowspan="2">
                <table border="0" >
                  <tr>
                     <td rowspan="2"  class="newTable"> 
                       <div>
                         <h1><g:message code="default.GrantExpense.ExpenseEntry.head" /></h1>
                         <g:if test="${flash.message}"> 
                           <div class="message">${flash.message}</div>
                         </g:if> 
                         <g:hasErrors bean="${grantExpenseInstance}"> 
                           <div class="errors"> 
                             <g:renderErrors bean="${grantExpenseInstance}" as="list" /> 
                           </div>
                         </g:hasErrors> 
                         <g:form method="post" > 
                            <div class="dialog"> 
                               <table>
                                  <tbody>
                                  
                                     <tr class="prop"> 
                                         <td valign="top" class="name"> 
                                          <label for="dateOfExpense"><g:message code="default.ExpenseDate.label"/></label>: 
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'dateOfExpense','errors')}"> 
                                            <calendar:datePicker name="dateOfExpense" defaultValue="${new Date()}" value="${grantExpenseInstance?.dateOfExpense}" dateFormat= "%d/%m/%Y"/> 
                                            <g:hiddenField name="projects.id" value="${grantExpenseInstance?.projects?.id}" /> 
                                            <!--<g:hiddenField name="dateFrom" value="${grantExpenseInstance.dateFrom}" />
                                            <g:hiddenField name="dateTo" value="${grantExpenseInstance.dateTo}" />-->
                                         </td>
                                     </tr>
                                     
                                     <tr class="prop">
                                         <td valign="top" class="name">
                                             <label for="grantAllocation"><g:message code="default.GrantAllocation.label"/></label>:
                                               <label for="grantAllocation" style="color:red;font-weight:bold"> * </label>
                                        
                                         </td>
                                         <td valign="top" class="value ${hasErrors(grantExpenseInstance,field:'grantAllocation','errors')}">
                                             <g:select optionKey="id" optionValue="grantCode" from="${grantAllocationInstanceList}" name="grantAllocation.id" value="${grantExpenseInstance?.grantAllocation?.id}"  noSelection="['null':'-Select-']" ></g:select>
                                         </td>
                                     </tr> 
                                     
                                     <tr class="prop"> 
                                         <td valign="top" class="name"> 
                                             <label for="grantAllocationSplit"><g:message code="default.AccountHeads.label"/></label>: 
                                             <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'grantAllocationSplit','errors')}"> 
                                             <g:select optionKey="id" optionValue="accHeadPeriod" from="${accountHeadList}" noSelection="['null':'-Select-']" name="grantAllocationSplit.id" value="${grantExpenseInstance?.grantAllocationSplit?.id}" ></g:select> 
                                         </td>
                                     </tr>
                                     
                                     <tr class="prop"> 
                                     
                                         <td valign="top" class="name"> 
                                             <label for="expenseAmount"><g:message code="default.ExpenseAmount(Rs).label"/></label>: 
                                             <label for="expenseAmount" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'expenseAmount','errors')}"> 
                                             <input type="text" id="expenseAmount" name="expenseAmount" value="${amount}" style="text-align: right" />
                                 
                                         </td>
                                    </tr>
                
                                    <tr class="prop">
                                         <td valign="top" class="name">
                                            <label for="modeOfPayment"><g:message code="default.ModeOfPayment.label"/></label>:
                                            <label for="modeOfPayment" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'modeOfPayment','errors')}">
                                            <g:select name="modeOfPayment" from="${['DD','Cheque','Bank Transfer','Cash']}"  
                                            onchange="${remoteFunction(controller:'grantExpense',action:'updateModeOfPayment',update:'fieldSelect',  params:'\'modeOfPayment=\' + this.value' )}"
                                            value="${fieldValue(bean:grantExpenseInstance,field:'modeOfPayment')}" noSelection="['null':'-Select-']"></g:select>
                                         </td>
                                    </tr>      
               
                                    <tr class="prop">
                                         <td valign="top" class="name">
                                            <label for="ddNo"><g:message code="default.DD/ChequeNo.label"/></label>:
                                            <label for="ddNo" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddNo','errors')}">
                                            <div id="fieldSelect">
                         				       <input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantExpenseInstance,field:'ddNo')}" style="text-align: right" />
										    </div>
										</td>
                                    </tr> 
                        
                                    <tr class="prop">
                                         <td valign="top" class="name">
                                             <label for="ddDate"><g:message code="default.DD/ChequeDate.label"/></label>:
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddDate','errors')}">
                                             <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${grantExpenseInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
                                 		 </td>
                                    </tr> 
                            
                                    <tr class="prop">
                                         <td valign="top" class="name">
                                             <label for="bankName"><g:message code="default.BankName.label"/></label>:
                                             <label for="bankName" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'bankName','errors')}">
                                             <input type="text" id="bankName" name="bankName" value="${fieldValue(bean:grantExpenseInstance,field:'bankName')}" style="text-align: right" />
                                         </td>
                                    </tr> 
                            
                                    <tr class="prop">
                                         <td valign="top" class="name">
                                             <label for="ddBranch"><g:message code="default.Branch.label"/></label>:
                                             <label for="ddBranch" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddBranch','errors')}">
                                             <input type="text" id="ddBranch" name="ddBranch" value="${fieldValue(bean:grantExpenseInstance,field:'ddBranch')}" style="text-align: right" />
                                         </td>
                                    </tr> 
                           
                                    <tr class="prop"> 
                                         <td valign="top" class="name"> 
                                             <label for="description"><g:message code="default.Description.label"/></label>: 
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'description','errors')}"> 
                                             <g:textArea name="description" value="${fieldValue(bean:grantExpenseInstance,field:'description')}" rows="3" cols="30"/> 
                                         </td>
                                    </tr>
                                  </tbody>
                               </table>
                            </div>
                            <div class="buttons"> 
                              <span class="button">
                                <span class="button"><g:actionSubmit value="${message(code: 'default.Create.button')}" onClick="return validateGrantExpense()" class="save" action="save"/></span>
                                <span class="button"><span class="button"><g:actionSubmit class="delete" action="clear" value="${message(code: 'default.Clear.button')}" /></span></span>
                              </span> 
                            </div>
                         </g:form>
                       </div> 
                     </td>
          
                     <td width="51%" height="50%"  class="newTable"> 
                       <g:form action="create" method="post" > 
                         <div class="dialog"> 
                           <table>
                             <h1><g:message code="default.GrantExpense.PeriodWiseExpenses.head" /></h1>
                             <tbody>
                              
                                <tr class="prop"> 
                                    <td class="name"> 
                                         <label for="dateOfExpense"><g:message code="default.DateFrom.label"/></label> 
                                    </td>
                                    <td> 
                                         <calendar:datePicker name="dateFrom" defaultValue="${new Date()}" value="${projectsInstance?.projectStartDate}" dateFormat= "%d/%m/%Y"/> 
                                         <g:hiddenField name="id" value="${grantExpenseInstance?.projects?.id}" /> 
                                    </td>
                                </tr>
                                
                                <tr> 
                                    <td  class="name"> 
                                         <label for="dateOfExpense"><g:message code="default.DateTo.label"/></label> 
                                    </td>
                                    <td> 
                                         <calendar:datePicker name="dateTo" defaultValue="${new Date()}" value="${grantExpenseInstance.dateTo}" dateFormat= "%d/%m/%Y"/> 
                                    </td>
                                    <td> 
                                         <input class="inputbutton" name="create" type="submit" value="${message(code: 'default.List.button')}" /> 
                                    </td>
                                </tr>
                             </tbody>
                           </table>
                         </div>
                       </g:form> 
                     </td>
                  </tr>
                  <tr>
                     <td>
                       <g:if test="${grantExpenseInstanceList}">
                       <g:form action="export" method="post" > 
	                            <div id="ss" class="list" style="overflow:auto ;height:150px; width:100%">
	                             <table width="100%" height="" cellspacing="0">
	                               <thead>
	                               
	                                  <tr> 
	                                     <th><g:message code="default.SINo.label"/></th>
	                   	                 <th><g:message code="default.ExpenseDate.label"/></th>
	               	                     <th><g:message code="default.AccountHeads.label"/></th>
	                                     <th><g:message code="default.ExpenseAmount(Rs).label"/></th>
	                                     <th><g:message code="default.Description.label"/></th>
	                                     <th><g:message code="default.Edit.label"/></th>
	                                  </tr>
	                                  
	                               </thead>
	                               <tbody>
	                                  <% int j=0 %>
	                                  <g:each in="${grantExpenseInstanceList}" status="i" var="grantExpenseInstance"> 
	                                     <% j++ %>
	                                     <tr class="${(i % 2) == 0 ? 'odd' : 'even'}"> 
	                                         <td>${j}</td>
	                                         <td><g:formatDate format="dd/MM/yyyy" date="${grantExpenseInstance.dateOfExpense}"/></td>
	                                         <td>${fieldValue(bean:grantExpenseInstance, field:'grantAllocationSplit.accountHead.code')}</td>
	                                         <td>${currencyFormat.ConvertToIndainRS(grantExpenseInstance.expenseAmount)}</td>
	                                         <td>${fieldValue(bean:grantExpenseInstance, field:'description')}</td>
	                                         <td><g:link action="edit" id="${fieldValue(bean:grantExpenseInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
	                                     </tr>
	                                  </g:each> 
	                               </tbody>
	                             </table>
	                             <div class="buttons" align="left">
			                         <input type=hidden id="dateFrom" name="dateFrom" value="${dateFrom}" >
				    			     <input type=hidden id="dateTo" name="dateTo" value="${dateTo}" >
				    			     <input type=hidden id="grantExpenseId" name="grantExpenseId" value="${grantExpenseId}">
				    			     <input type=hidden id="projectsID" name="projectsID" value="${projectsInstance?.id}">
				    			 </div> 
	                            </div>
                          </g:form> 
                       </g:if>
                     </td>
                  </tr>
                  <tr> 
                     <td height="200" colspan="2"> 
                         
	                      <div class="list" align="center"> 
	                       
		                       <h1><g:message code="default.GrantExpense.AccountHeadWiseExpenseSummary.head"/></h1>
		                       <table width="100%" cellspacing="0" >
		                        <g:if test="${grantExpenseSummaryList}">
		                         <thead>
		                            <tr> 
		                               <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" /> 
		                               <g:sortableColumn property="grantAllocationSplit.accountHead.code" title="${message(code: 'default.AccountHeads.label')}" /> 
		                               <g:sortableColumn property="expenseAmount" title="${message(code: 'default.AllocatedAmount.label')}" /> 
		                               <g:sortableColumn property="expenseAmount" title="${message(code: 'default.ExpenseAmount(Rs).label')}" /> 
		                               <g:sortableColumn property="balanceAmount" title="${message(code: 'default.BalanceAmount.label')}" /> 
		                            </tr>
		                         </thead>
		                         <tbody>
		                           <% int j=0 %>
		                           <g:each in="${grantExpenseSummaryList}" status="i" var="grantExpenseInstance"> 
		                            <% j++ %>
		                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}"> 
		                              <td>${j}</td>
		                              <td>${grantExpenseInstance.accountHeadCode}</td>
		                              <td>${currencyFormat.ConvertToIndainRS((grantExpenseInstance.expenseAmount)+(grantExpenseInstance.balanceAmount))}</td>
		                              <td>${currencyFormat.ConvertToIndainRS(grantExpenseInstance.expenseAmount)}</td>
		                              <td>${currencyFormat.ConvertToIndainRS(grantExpenseInstance.balanceAmount)}</td>
		                            </tr>
		                           </g:each> 
		                         </tbody>
		                        </g:if>
		                        <g:else>
		                          <br><g:message code="default.NoRecordsAvailable.label"/></br>
		                        </g:else>
		                       </table>
		                       
	                     </div>  
                   
                     </td>
                  </tr>
                </table>
              </td>
           </tr>
         </table>  
       </div>     
    </body>
</html>
