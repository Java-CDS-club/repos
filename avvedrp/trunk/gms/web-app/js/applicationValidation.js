docclick=0;
function checkAll()
{
	if(document.accessPermission.All.checked==true)
	{
		for(var i=0;i<document.accessPermission.projectName.length;i++)
		{
			document.accessPermission.projectName[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<document.accessPermission.projectName.length;i++)
		{
			document.accessPermission.projectName[i].checked=false;
		}
	}
}
function checkAllDelete()
{
	if(document.accessPermission.AllDelete.checked==true)
	{
		for(var i=0;i<document.accessPermission.projectId.length;i++)
		{
			document.accessPermission.projectId[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<document.accessPermission.projectId.length;i++)
		{
			document.accessPermission.projectId[i].checked=false;
		}
	}
}

function validate()
{ 
	var isChecked=0; 
	if(document.getElementById("user.id").value == 'null')
	{
		alert("Please Select a User");
		document.getElementById("user.id").focus();
		return false;
	}
	if(document.accessPermission.projectName.length > 1)  
	{ 
		for(var i=0;i<document.accessPermission.projectName.length;i++)
		{
			if(document.accessPermission.projectName[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert('You didn\'t check a project name. Please check a box.'); 
			return false;
		}
		}
		else
		{
			if(document.accessPermission.projectName.checked!=true)
			{
				alert('You didn\'t check a project name. Please check a box.'); 
			return false;
			}
		}
	docclick++;
	if(docclick>1)
	{
		return false;
	}
	setTimeout('docclick=0', 2000);
		
	//rolePrivi.submit();	
   return true;
}
function validateForDelete()
{ 
		  var isChecked=0;  
		  if(document.getElementById("user.id").value == 'null')
		{
			alert("Please Select a User");
			document.getElementById("user.id").focus();
	   	 	return false;
		}
		if(document.accessPermission.projectId.length > 1)  
		  {
		for(var i=0;i<document.accessPermission.projectId.length;i++)
		{
			if(document.accessPermission.projectId[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert('You didn\'t check a project name for delete. Please check a box.'); 
			return false;
		}
		}
		else
		{
			if(document.accessPermission.projectId.checked!=true)
			{
				alert('You didn\'t check a project name for delete. Please check a box.'); 
				return false;
			}
		}
		
	//rolePrivi.submit();	
   return true;
}
 function checkAllActions()
{
	 
	if(document.rolePrivi.All.checked==true)
	{
		for(var i=0;i<document.rolePrivi.actionName.length;i++)
		{
			document.rolePrivi.actionName[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<document.rolePrivi.actionName.length;i++)
		{
			document.rolePrivi.actionName[i].checked=false;
		}
	}
}
function checkAllActionDelete()
{
	if(document.rolePrivi.AllDel.checked==true)
	{	
	
		for(var i=0;i<document.rolePrivi.deleteAction.length;i++)
		{	
			document.rolePrivi.deleteAction[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<document.rolePrivi.deleteAction.length;i++)
		{
			document.rolePrivi.deleteAction[i].checked=false;
		}
	}
}
function validateActions()
{ 
		  var isChecked=0;
		  if(document.rolePrivi.actionName.length > 1)  
		  {
		for(var i=0;i<document.rolePrivi.actionName.length;i++)
		{
			if(document.rolePrivi.actionName[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked <= 0) 
		{ 
			alert('You didn\'t check a action name. Please check a box.'); 
			return false;
		}
		}
		else
		{
			if(document.rolePrivi.actionName.checked!=true)
			{
				alert('You didn\'t check a action name. Please check a box.'); 
				return false;
			}
		}
	docclick++;
	if(docclick>1)
	{
		return false;
	}
	setTimeout('docclick=0', 2000);
		
	//rolePrivi.submit();	
   return true;
}
function validateActionForDelete()
{ 
		  var isChecked=0;  
		   if(document.rolePrivi.deleteAction.length < 0)
		   {
		   	alert("All actions are deleted");
		   }
		  else if(document.rolePrivi.deleteAction.length > 1)  
		  {
		for(var i=0;i<document.rolePrivi.deleteAction.length;i++)
		{	
			if(document.rolePrivi.deleteAction[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert('You didn\'t check a action name for delete. Please check a box.'); 
			return false;
		}
		}
		else
		{
			if(document.rolePrivi.deleteAction.checked!=true)
			{
				alert('You didn\'t check a action name for delete. Please check a box.'); 
				return false;
			}
		}
		
	//rolePrivi.submit();	
   return true;
}


function validateProject(){
	if(LTrim(document.getElementById('name').value) == "")
	{
		alert("Please Enter Name");
	    document.getElementById("name").focus();
	    return false;
	} 
	 
	if(LTrim(document.getElementById("code").value) == "")
	{
		alert("Please Enter Code");
	    document.getElementById("code").focus();
	    return false;
	}
	
	
	if( ( (document.getElementById("projectType").value) == 'null') || ( (document.getElementById("projectType").value) == '') )
    {
	    alert("Please enter the Project Type");
	    document.getElementById("projectType").focus();
	    return false;
    }
	
	if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if((document.getElementById("amountAllocated").value)==0)
    {
	    alert("Please enter the Amount Allocated  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter the Amount Allocated   ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    } 
    
	
	
    if( ( (document.getElementById("investigator.id").value) == 'null') || ( (document.getElementById("investigator.id").value) == '') )
    {
	    alert("Please enter the Investigator");
	    document.getElementById("investigator.id").focus();
	    return false;
    }
    
   
	
	
	var projectStartDateYear = document.getElementById("projectStartDate_year").value;
	var projectStartDateMonth = document.getElementById("projectStartDate_month").value;
	var projectStartDateDate = document.getElementById("projectStartDate_day").value;
	
	var projectEndDateYear = document.getElementById("projectEndDate_year").value;
	var projectEndDateMonth = document.getElementById("projectEndDate_month").value;
	var projectEndDateDate = document.getElementById("projectEndDate_day").value;
    	
    	var newProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    	var newProjectEndDate = new Date(projectEndDateYear,projectEndDateMonth-1,projectEndDateDate);
    	 
    	
    	if (newProjectStartDate>=newProjectEndDate)
    	{
    		alert("Project End Date should be greater than Start Date");
    		document.getElementById("projectEndDate").focus();
    		return false;
    	}
    
    	return true;
    
    }
    
   function validateSubProject(){
    		
       	if(LTrim(document.getElementById("name").value) == ""){
    		alert("Please Enter Name");
		    document.getElementById("name").focus();
		    return false;
    	}
    	
    	
   
    	if(LTrim(document.getElementById("code").value) == ""){
    		alert("Please Enter Code");
		    document.getElementById("code").focus();
		    return false;
    	}
    	  if( ( (document.getElementById("amountAllocated").value) == 'null') || ( (document.getElementById("amountAllocated").value) == '') )
    	    {
    	       alert("Invalid Amount  ");
    		    document.getElementById("amountAllocated").focus();
    		    return false;
    	    }
    	    if((document.getElementById("amountAllocated").value)==0)
    	    {
    		    alert("Please enter the Amount Allocated  ");
    		    document.getElementById("amountAllocated").focus();
    		    return false;
    	    }
    	    
    	    if(eval(document.getElementById("amountAllocated").value)<=0)
    	    {
    		    alert("Please enter the Amount Allocated   ");
    		    document.getElementById("amountAllocated").focus();
    		    return false;
    	    } 
    	    if( ( (document.getElementById("sanctionOrderNo").value) == 'null') || ( (document.getElementById("sanctionOrderNo").value) == '') )
    	    {
    	        alert("Please enter the Sanction Order No  ");
    		    document.getElementById("sanctionOrderNo").focus();
    		    return false;
    	    }    
    
    	//Sub Project Start Date
    	var projectStartDateYear = document.getElementById("projectStartDate_year").value;
    	var projectStartDateMonth = document.getElementById("projectStartDate_month").value;
    	var projectStartDateDate = document.getElementById("projectStartDate_day").value;
    	
    	//Sub Project End Date
    	
    	var projectEndDateYear = document.getElementById("projectEndDate_year").value;
    	var projectEndDateMonth = document.getElementById("projectEndDate_month").value;
    	var projectEndDateDate = document.getElementById("projectEndDate_day").value;
    	
    	var newProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    	var newProjectEndDate = new Date(projectEndDateYear,projectEndDateMonth-1,projectEndDateDate);
    	
		//Parent Project Start Date
		var parentStartDate = document.getElementById("parentProjectStartDate").value;
    	  		
    	var parentStartDateYear=parentStartDate.substring(0,parentStartDate.indexOf("-"));
    	var parentStartDateMonth=parentStartDate.substring(parentStartDate.indexOf("-")+1,parentStartDate.lastIndexOf("-"));
    	var parentStartDateDate=parentStartDate.substring(parentStartDate.lastIndexOf("-")+1,parentStartDate.lastIndexOf(" "));
    		
    	//Parent Project End Date
    		
    	    var parentEndDate = document.getElementById("parentProjectEndDate").value;
       		var parentEndDateYear=parentEndDate.substring(0,parentEndDate.indexOf("-"));
    		var parentEndDateMonth=parentEndDate.substring(parentEndDate.indexOf("-")+1,parentEndDate.lastIndexOf("-"));
    		var parentEndDateDate=parentEndDate.substring(parentEndDate.lastIndexOf("-")+1,parentEndDate.lastIndexOf(" "));
    		
    		var newParentProjectStartDate = new Date(parentStartDateYear,parentStartDateMonth-1,parentStartDateDate);
    		var newParentProjectEndDate = new Date(parentEndDateYear,parentEndDateMonth-1,parentEndDateDate);
    		
    		if (newProjectStartDate>=newProjectEndDate)
    		{
    			alert("Sub project End Date should be greater than sub project Start Date");
    			document.getElementById("parentProjectEndDate").focus();
    			return false;
    		}
    		
    		if ((newProjectStartDate<newParentProjectStartDate)||(newProjectEndDate>newParentProjectEndDate))
	 	{
		alert("Sub project Date period should be between the Main project date");
		return false;
		}
    		
      //Sub Project Allocation Date
      
    		var projectAllocationDateYear = document.getElementById("dateOfAllocation_year").value;
	        var projectAllocationDateMonth = document.getElementById("dateOfAllocation_month").value;
	        var projectAllocationDateDate = document.getElementById("dateOfAllocation_day").value;
	
	var newProjectAllocationDate = 
			new Date(projectAllocationDateYear,projectAllocationDateMonth-1,projectAllocationDateDate);
	
	
	  	
    		
    		
    		
		
		if (newParentProjectStartDate>=newProjectAllocationDate)
    		{
    			alert("Date of Allocation  should be greater than project Start Date");
    			document.getElementById("dateOfAllocation").focus();
    			return false;
    		}
		
		
	if( ( (document.getElementById("recipient").value) == 'null') || ( (document.getElementById("recipient").value) == '') )
    {
	    alert("Please enter the Recipient");
	     document.getElementById("recipient").focus();
	    return false;
    }
    if( ( (document.getElementById("investigator.id").value) == 'null') || ( (document.getElementById("investigator.id").value) == '') )
    {
	    alert("Please enter the Investigator");
	    document.getElementById("investigator.id").focus();
	    return false;
    }
  
    
   	return true;

}

function validateFundTransffered()
{

var transferDateYear = document.getElementById("dateOfTransfer_year").value;
var transferDateMonth = document.getElementById("dateOfTransfer_month").value;
var transferDateDate = document.getElementById("dateOfTransfer_day").value;
var TransferDate = new Date(transferDateYear,transferDateMonth-1,transferDateDate);
   
 var d = new Date();

var curr_date = d.getDate();

var curr_month = d.getMonth();

var curr_year = d.getFullYear();

var Today = new Date(curr_year,curr_month,curr_date);

var projectStartDate = document.getElementById("ProjectStartDate").value;
    var projectStartDateYear=projectStartDate.substring(0,projectStartDate.indexOf("-"));
    var projectStartDateMonth=projectStartDate.substring(projectStartDate.indexOf("-")+1,projectStartDate.lastIndexOf("-"));
    var projectStartDateDate=projectStartDate.substring(projectStartDate.lastIndexOf("-")+1,projectStartDate.lastIndexOf(" "));
    		
 var ProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);

 if(TransferDate > Today)
    {
        alert("Date of Transfer should be less than current date");
        document.getElementById("ProjectStartDate").focus();
        return false; 
    }
 if(TransferDate < ProjectStartDate)
   {
   alert("Date of Transfer should be greater than or equal to Project Start Date");
   return false;
   }
   
 if(isNaN(document.getElementById("amount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }
 if((document.getElementById("amount").value)== 0)
    {
	    alert("Please enter the Amount ");
	    document.getElementById("amount").focus();
	    return false;
    }
    
 if(eval(document.getElementById("amount").value)<=0)
    {
	    alert("Please enter a valid Amount   ");
	    document.getElementById("amount").focus();
	    return false;
    } 
 }
function validateAccountHead()
{
	if(LTrim(document.getElementById("name").value) == "")
	{
		alert("Please Enter Name");
	    document.getElementById("name").focus();
	    return false;
	}
	if(LTrim(document.getElementById("code").value) == "")
	{
		alert("Please Enter Code");
	    document.getElementById("code").focus();
	    return false;
	}
	return true;
}
function validateExpenseRequest()
{
    if((document.getElementById("requestedAmount").value)==0)
    {
	    alert("Please enter Proper Amount  "); 
	    document.getElementById("requestedAmount").focus();
	    return false;
    }
     
    if(isNaN(document.getElementById("requestedAmount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("requestedAmount").select();
	    return false;
    }
    if(eval(document.getElementById("requestedAmount").value)<=0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("requestedAmount").focus();
		    return false;
	    }
	}
	function validateGrantAllocationCreate()
    {
   
	    if(eval(document.getElementById("amountAllocated").value)+eval(document.getElementById("totAloAmt").value)>=eval(document.getElementById("totAmt").value))
	    {
		    alert("Amount Exceeded ,fund Allocation not possible ");
		     document.getElementById("amountAllocated").focus();
		    return false;
	    }
}
function validateGrantAllocationEdit()
{     
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Please enter a valid Amount");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if((document.getElementById("amountAllocated").value)==0)
    {
	    alert("Please enter Valid Amount");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    
    if(eval(document.getElementById("amountAllocated").value)<0)
    {
	    alert("Please enter a valid Amount");
	    document.getElementById("amountAllocated").focus();
	    return false;
    } 
    var allocationDateYear = document.getElementById("dateOfAllocation_year").value;
    var allocationDateMonth = document.getElementById("dateOfAllocation_month").value;
	var allocationDateDate = document.getElementById("dateOfAllocation_day").value;
	
	var AllocationDate = new Date(allocationDateYear,allocationDateMonth-1,allocationDateDate);
		
	var projectStartDate = document.getElementById("ProjectStartDate").value;
    var projectStartDateYear=projectStartDate.substring(0,projectStartDate.indexOf("-"));
    var projectStartDateMonth=projectStartDate.substring(projectStartDate.indexOf("-")+1,projectStartDate.lastIndexOf("-"));
    var projectStartDateDate=projectStartDate.substring(projectStartDate.lastIndexOf("-")+1,projectStartDate.lastIndexOf(" "));
    		
    var ProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    		
   if(AllocationDate < ProjectStartDate)
   {
	   alert("Allocation Date should be greater than Project Start Date");
	   document.getElementById("ProjectStartDate").focus();
	   return false;
   }  	
   return true;   

}
function validateEditProAllot()
{     
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").select();
	    return false;
    }
    if((document.getElementById("amountAllocated").value)==0)
    {
	    alert("Please enter the Amount Allocated ");
	    document.getElementById("amountAllocated").select();
	    return false;
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter the Amount Allocated ");
	    document.getElementById("amountAllocated").select();
	    return false;
    }    

}
function validateFundAllot()
{     	
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if((document.getElementById("amountAllocated").value)== 0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    	
    var allocationDateYear = document.getElementById("dateOfAllocation_year").value;
    var allocationDateMonth = document.getElementById("dateOfAllocation_month").value;
	var allocationDateDate = document.getElementById("dateOfAllocation_day").value;
	
	var AllocationDate = new Date(allocationDateYear,allocationDateMonth-1,allocationDateDate);
		
	var projectStartDate = document.getElementById("ProjectStartDate").value;
    var projectStartDateYear=projectStartDate.substring(0,projectStartDate.indexOf("-"));
    var projectStartDateMonth=projectStartDate.substring(projectStartDate.indexOf("-")+1,projectStartDate.lastIndexOf("-"));
    var projectStartDateDate=projectStartDate.substring(projectStartDate.lastIndexOf("-")+1,projectStartDate.lastIndexOf(" "));
    		
    var ProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    
    var projectEndDate = document.getElementById("ProjectEndDate").value;
    var projectEndDateYear=projectEndDate.substring(0,projectEndDate.indexOf("-"));
    var projectEndDateMonth=projectEndDate.substring(projectEndDate.indexOf("-")+1,projectEndDate.lastIndexOf("-"));
    var projectEndDateDate=projectEndDate.substring(projectEndDate.lastIndexOf("-")+1,projectEndDate.lastIndexOf(" "));
        		
    var ProjectEndDate = new Date(projectEndDateYear,projectEndDateMonth-1,projectEndDateDate);
    
   if(AllocationDate < ProjectStartDate)
   {
   alert("Allocation Date should be greater than Project Start Date");
   return false;
   }  
   if(AllocationDate > ProjectEndDate)
   {
   	alert("Allocation Date should be less than Project End Date");
   	document.getElementById("ProjectEndDate").focus();
   	return false;
   }  	
   return true;
    

}
function validateReportViewConfirmPrint()
{
    var answer = confirm("Do you want to view report?")
	if (answer)
	{
		document.viewStatementOfAccounts.target="_blank";
		return true;
	}
	else
	{
		return false;
	} 
	document.viewStatementOfAccounts.target="_blank";
  return true;
}

function validateSubmitReportConfirmPrint()
{
    document.viewStatementOfAccounts.target="_self";
	return true;
	
}
function validateSubGrantAllot()
{     


    if( ( (document.getElementById("recipient").value) == 'null') || ( (document.getElementById("recipient").value) == '') )
    {
	    alert("Please enter the Recipient");
	    document.getElementById("recipient").focus();
	    return false;
    }
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if((document.getElementById("amountAllocated").value)==0)
    {
	    alert("Please enter the Amount Allocated  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter the Amount Allocated   ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }    
    
    
}
function validateSubGrantAllotExt()
{ 
	if( ( (document.getElementById("granter").value) == 'null') || ( (document.getElementById("granter").value) == '') )
	{
		alert("Please enter granter");
		document.getElementById("granter").focus();
		return false;
	}
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if((document.getElementById("amountAllocated").value)==0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }    
}

function refreshParentGrantAllocationSplit() 
{
	window.opener.location.href = window.opener.location.href;
	if(window.opener.progressWindow)
	{
		window.opener.progressWindow.close()
	}
	top.close();
}
function validateGrantExpense()
{

if(((document.getElementById("grantAllocation.id").value) == 'null') || ( (document.getElementById("grantAllocation.id").value) == '' ) )
   {
	   alert("Please select Grant Allocation");
	   document.getElementById("grantAllocation.id").focus();
	   return false;
   }
    
   if(((document.getElementById("grantAllocationSplit.id").value) == 'null') || ( (document.getElementById("grantAllocationSplit.id").value) == '' ) )
   {
	   alert("Please select Account Head");
	   document.getElementById("grantAllocationSplit.id").focus();
	   return false;
   }
    if((document.getElementById("expenseAmount").value)==0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("expenseAmount").focus();
	    return false;
    }
    if(((document.getElementById("modeOfPayment").value) == 'null') || ( (document.getElementById("modeOfPayment").value) == '' ) )
    {
	    alert("Please select Mode Of Payment");
	    document.getElementById("modeOfPayment").focus();
	    return false;
    }
 	
     var str = document.getElementById('ddNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the DD/Cheque No: Field.");
            document.getElementById('ddNo').focus();
            return false;
         }
      } 
 	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("bankName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("bankName").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("bankName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters are not allowed in Bank Name");
	  			document.getElementById("bankName").focus();
	  			return false;
  			}
    	}
 
	  }
	  var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("ddBranch").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("ddBranch").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("ddBranch").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters are not allowed in Branch Name");
	  			document.getElementById("ddBranch").focus();
	  			return false;
  			}
    	}
 
	  }
    if(isNaN(document.getElementById("expenseAmount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("expenseAmount").focus();
	    return false;
    }
    if(eval(document.getElementById("expenseAmount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("expenseAmount").focus();
	    return false;
    }
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if(eval(document.getElementById("ddNo").value)<=0)
	    {
		    alert("Please enter Proper DD/Cheque Number  ");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
	     if((document.getElementById("ddNo").value)=='')
	    {
		    alert("Please enter DD/Cheque No");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
    }
    
    
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if((document.getElementById("bankName").value)=='')
	    {
		    alert("Please enter Bank Name  ");
		    document.getElementById("bankName").focus();
		    return false;
	    }
	    
	    if((document.getElementById("ddBranch").value)=='')
	    {
		    alert("Please enter Bank Branch  ");
		    document.getElementById("ddBranch").focus();
		    return false;
	    }
   }
}
function validateGrantPeriod()
{

	if(LTrim(document.getElementById("name").value) == "")
	{
		alert("Please Enter Name");
	    document.getElementById("name").focus();
	    return false;
	}
	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("name").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("name").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("name").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters are not allowed in Name.");
	  			document.getElementById("name").focus();
	  			return false;
  			}
    	}
 
	  }
	var startDateYear = document.getElementById("startDate_year").value;
	var startDateMonth = document.getElementById("startDate_month").value;
	var startDateDate = document.getElementById("startDate_day").value;
	
	var endDateYear = document.getElementById("endDate_year").value;
	var endDateMonth = document.getElementById("endDate_month").value;
	var endDateDate = document.getElementById("endDate_day").value;
    	
    	var newStartDate = new Date(startDateYear,startDateMonth-1,startDateDate);
    	var newEndDate = new Date(endDateYear,endDateMonth-1,endDateDate);
    	 
    	
    	if (newStartDate>=newEndDate)
    	{
    		alert(" End Date should be greater than Start Date");
    		document.getElementById("endDate").focus();
    		return false;
    	}
    
    	
	
	return true;
}
function validateGrantReceipt()
{
 
    var str = document.getElementById('ddNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the DD/Cheque No: Field.");
            document.getElementById('ddNo').focus();
            return false;
         }
      } 
	
    if((LTrim(document.getElementById("referenceId").value))=='')
    {
	    alert("Please enter Funds Received Order No.  ");
	    document.getElementById("referenceId").focus();
	    return false;
    }	
    
    if((document.getElementById("amount").value)==0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }	     
    if((document.getElementById("amount").value)==0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }
    if(isNaN(document.getElementById("amount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amount").select();
	    return false;
    }
    if(eval(document.getElementById("amount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }
    if(((document.getElementById("modeOfPayment").value) == 'null') || ( (document.getElementById("modeOfPayment").value) == '' ) )
    {
	    alert("Please select Mode Of Payment");
	    document.getElementById("modeOfPayment").focus();
	    return false;
    }
    
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if(eval(document.getElementById("ddNo").value)<=0)
	    {
		    alert("Please enter Proper DD/Cheque Number  ");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
	     if((document.getElementById("ddNo").value)=='')
	    {
		    alert("Please enter DD/Cheque No");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
    }
    if(parseFloat(document.getElementById("amount").value) > parseFloat(document.getElementById("balanceAmt").value))
	{
			alert("Please Enter Amount Less Than Or Equal To Balance Amount");
			document.getElementById("amount").focus();
			return false;
	}   
    
   
    
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if((document.getElementById("ddNo").value)=='')
	    {
		    alert("Please enter DD/Cheque No");
		    document.getElementById("ddNo").focus();
		    return false;
	    }	
    }
   if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if((LTrim(document.getElementById("bankName").value))=='')
	    {
		    alert("Please enter Bank Name  ");
		    document.getElementById("bankName").focus();
		    return false;
	    }
	    
	    if((LTrim(document.getElementById("ddBranch").value))=='')
	    {
		    alert("Please enter Bank Branch  ");
		    document.getElementById("ddBranch").focus();
		    return false;
	    }
   }
   
       var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("bankName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("bankName").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("bankName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Bank Name .");
	  			document.getElementById("bankName").focus();
	  			return false;
  			}
    	}
    }
  	
	var speclChars = "!@#$%^&*()+=-[]\\\/{}|\":<>?";
	if(document.getElementById("ddBranch").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("ddBranch").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("ddBranch").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Branch name.");
	  			document.getElementById("ddBranch").focus();
	  			return false;
  			}
    	}
    }
	
	
}
function LTrim( value ) {
	
	var re = /\s*((\S+\s*)*)/;
	return value.replace(re, "$1");
	
}

function validatePI()
{
	if(LTrim(document.getElementById('name').value) == '' || document.getElementById('name').value == null)
	{
		alert("Please Enter the Investigator First Name");
		document.getElementById('name').focus();
		return false;
	}
	
	
	if(LTrim(document.getElementById('userSurName').value) == '' || document.getElementById('userSurName').value == null)
	{
		alert("Please Enter the Investigator Last Name");
		document.getElementById('userSurName').focus();
		return false;
	}
	
	if(((document.getElementById("department.id").value) == 'null') || ( (document.getElementById("department.id").value) == '' ) )
    {
	    alert("Please select Department");
	    document.getElementById("department.id").focus();
	    return false;
    }
	
	 
	 if( ( (LTrim(document.getElementById("aadhaarNo").value)) == 'null') || ( (document.getElementById("aadhaarNo").value) == '') )
	    {
		 	alert("Please enter Aadhaar Number");
		    document.getElementById("aadhaarNo").focus();
		    return false;
	    }
	   var str = document.getElementById('aadhaarNo').value;
	    var oneDecimal = false;
	    var oneChar = 0;
	    str = str.toString();
	   if(str.length>12)  
	      {
	      
	            alert("Please enter Aadhaar Number  less than or equal to 12 digits");
	            str.focus;
	            return false;
	      }
	    
	if(LTrim(document.getElementById('email').value) == '')
	{
		alert("Please Enter the email");
		document.getElementById('email').focus();
		return false;
	}
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus();
			return false;
		}
	}
	
	
	
	
	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("name").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("name").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("name").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Name.");
	  			document.getElementById("name").focus();
	  			return false;
  			}
    	}
    }
    
    var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("userSurName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userSurName").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("userSurName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Last Name.");
	  			document.getElementById("userSurName").focus();
	  			return false;
  			}
    	}
    }
	
	
	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	
	if(document.getElementById("designation").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("designation").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("designation").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Designation .");
	  			document.getElementById("designation").focus();
	  			return false;
  			}
    	}
    }
    
	return true;
}
function validateNotificationCreate()
{     
    if( ( (LTrim(document.getElementById("notificationTitle").value)) == 'null') || ( (document.getElementById("notificationTitle").value) == '') )
    {
	    alert("Please enter Notification Title");
	    document.getElementById("notificationTitle").focus();
	    return false;
    }
    if( ( (LTrim(document.getElementById("notificationCode").value)) == 'null') || ( (document.getElementById("notificationCode").value) == '') )
    {
	    alert("Please enter Notification code");
	     document.getElementById("notificationCode").focus();
	    return false;
    }
  
     if(isNaN(document.getElementById("amount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }
    if(eval(document.getElementById("amount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }
    if( ( (document.getElementById("amount").value) == 'null') || ( (document.getElementById("amount").value) == '') || ( (document.getElementById("amount").value) == '0')  )
    {
	    alert("Please enter an amount");
	    document.getElementById("amount").focus();
	    return false;
    }
    var notificationDateYear = document.getElementById("notificationDate_year").value;
	var notificationDateMonth = document.getElementById("notificationDate_month").value;
	var notificationDateDate = document.getElementById("notificationDate_day").value;
	
	var proposalSubmissionLastDateYear = document.getElementById("proposalSubmissionLastDate_year").value;
	var proposalSubmissionLastDateMonth = document.getElementById("proposalSubmissionLastDate_month").value;
	var proposalSubmissionLastDateDate = document.getElementById("proposalSubmissionLastDate_day").value;
	
	var newnotificationDate = new Date(notificationDateYear,notificationDateMonth-1,notificationDateDate);
	var newproposalSubmissionLastDate = new Date(proposalSubmissionLastDateYear,proposalSubmissionLastDateMonth-1,proposalSubmissionLastDateDate);
	 
	if (newnotificationDate>=newproposalSubmissionLastDate)
	{
		alert("Last Date for ProposalSubmission should be greater than Notification Date")
		document.getElementById("proposalSubmissionLastDate").focus();
		return false;
	}
    	
	var id_value = document.getElementById('myFile').value;
 
	if(id_value != '')
	{ 
		var valid_extensions = /(.html|.htm)$/i;   
  		if(valid_extensions.test(id_value))
  		{ 
  			 return true;
	}
	else
	 {
 		alert('This File Is Not Allowed');
 		document.getElementById("myFile").focus();
 		return false;
		}
	} 
	if( ( (document.getElementById("myFile").value) == 'null') || ( (document.getElementById("myFile").value) == '') )
    {
 		alert("Please upload a application form");
	    document.getElementById("myFile").focus();
	    return false;
    }
		    
	return true;
}
function validateNotificationEdit()
{ 
    var notificationDateYear = document.getElementById("notificationDate_year").value;
	var notificationDateMonth = document.getElementById("notificationDate_month").value;
	var notificationDateDate = document.getElementById("notificationDate_day").value;
	
	var proposalSubmissionLastDateYear = document.getElementById("proposalSubmissionLastDate_year").value;
	var proposalSubmissionLastDateMonth = document.getElementById("proposalSubmissionLastDate_month").value;
	var proposalSubmissionLastDateDate = document.getElementById("proposalSubmissionLastDate_day").value;
	
	var newnotificationDate = new Date(notificationDateYear,notificationDateMonth-1,notificationDateDate);
	var newproposalSubmissionLastDate = new Date(proposalSubmissionLastDateYear,proposalSubmissionLastDateMonth-1,proposalSubmissionLastDateDate);
	 
	
	if (newnotificationDate>=newproposalSubmissionLastDate)
	{
		alert("Last Date for ProposalSubmission should be greater than Notification Date");
		document.getElementById("proposalSubmissionLastDate").focus();
		return false;
	}
	if( ( (document.getElementById("project.id").value) == 'null') || ( (document.getElementById("project.id").value) == '') )
    {
	    alert("Please enter Project");
	    document.getElementById("project.id").focus();
	    return false;
    }
	    
	var id_value = document.getElementById('myFile').value;
 
	if(id_value != '')
	{ 
		var valid_extensions = /(.html|.htm)$/i;   
	if(valid_extensions.test(id_value))
	{ 
		 return true;
	}
	else
	 {
 		alert('This File Is Not Allowed')
 		return false;
 	}
	}
	
    
	return true;
}
function notificationEmailCheckAll()
{
	if(document.demo.All.checked==true)
	{
		for(var i=0;i<document.demo.choices.length;i++)
	{
		document.demo.choices[i].checked=true;
	}
	}
	else
	{
		for(var i=0;i<document.demo.choices.length;i++)
		{
			document.demo.choices[i].checked=false;
		}
	}
}
function validateParty()
{ 


	if(LTrim(document.getElementById("nameOfTheInstitution").value) == "")
	{
		alert("Please Enter Name");
	    document.getElementById("nameOfTheInstitution").focus();
	    return false;
	}
	

	 
	if(LTrim(document.getElementById("code").value) == "")
	{
		alert("Please Enter Code");
	    document.getElementById("code").focus();
	    return false;
	}
	var str = document.getElementById('phone').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the Phone No: Field.");
            document.getElementById('phone').focus();
            return false;
         }
      }   
	
	
   if(LTrim(document.getElementById("email").value) == "")
	{
		alert("Please Enter Email");
	    document.getElementById("email").focus();
	    return false;
	}
	
	
	
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus();
			return false;
		}
	}
	
	return true;
}
function validateProjectTracking(prjctId)
{
	if((document.statusDet.fundAdvnce.checked==false) || (document.statusDet.subPrjt.checked==false) || (document.statusDet.expnseRequst.checked==false) || (document.statusDet.utilizationDet.checked==false))
	{
		alert("You can't go to check the fund details as this projects status details are not checked.");
	    return false;
	}

}
function validateProjectClosure()
{
	if( ( (LTrim(document.getElementById("projectStatus").value)) == 'null') || ( (document.getElementById("projectStatus").value) == '') )
		  
	{
		alert("Please Enter Project Status");
	    document.getElementById("projectStatus").focus();
	    return false;
	}
	else if(isNaN(document.getElementById("percOfCompletion").value) )
	{
		alert("Please Enter Numeric Value For Percentage Of Completion");
	    document.getElementById("percOfCompletion").focus();
	    return false;
	}
	if(eval(document.getElementById("percOfCompletion").value)<=0)
	{
	    alert("Please enter Proper Percentage");
	   	document.getElementById("percOfCompletion").focus(); 
	    return false;
	}
	if(eval(document.getElementById("percOfCompletion").value)>100)
	{
	    alert("Please enter Proper Percentage");
	   	document.getElementById("percOfCompletion").focus(); 
	    return false;
	}
	 	
		return true;   
}
function validateProposal()
{
	if(LTrim(document.getElementById("code").value) == "")
	{
		alert("Please Enter the Proposal Code");
	    document.getElementById("code").focus();
	    return false;
	}
	
	var proposalSubmitteddateYear = document.getElementById("proposalSubmitteddate_year").value;
	var proposalSubmitteddateMonth = document.getElementById("proposalSubmitteddate_month").value;
	var proposalSubmitteddateDate = document.getElementById("proposalSubmitteddate_day").value;
    	
    var proposalSubmissionLastDate = document.getElementById("proposalSubmissionLastDate").value;
	  		
	var proposalSubmissionLastDateYear=proposalSubmissionLastDate.substring(0,proposalSubmissionLastDate.indexOf("-"));
	var proposalSubmissionLastDateMonth=proposalSubmissionLastDate.substring(proposalSubmissionLastDate.indexOf("-")+1,proposalSubmissionLastDate.lastIndexOf("-"));
	var proposalSubmissionLastDateDate=proposalSubmissionLastDate.substring(proposalSubmissionLastDate.lastIndexOf("-")+1,proposalSubmissionLastDate.lastIndexOf(" "));
   	
   	var newproposalSubmitteddate = new Date(proposalSubmitteddateYear,proposalSubmitteddateMonth-1,proposalSubmitteddateDate);
   	var newproposalSubmissionLastDate = new Date(proposalSubmissionLastDateYear,proposalSubmissionLastDateMonth-1,proposalSubmissionLastDateDate); 
    
    if (newproposalSubmitteddate>newproposalSubmissionLastDate)
	{
		alert("Last Date for Proposal submission completed")
		document.getElementById("proposalSubmissionLastDate").focus(); 
		return false;
	}	
    return true;
}
function fitThePage()
{
	//There is no other way than the following to identify whether inbox is open or not.

	//if(document.getElementById("inboxframe").style.height=="199px"){
		//If inbox is open, we will not do any fitting.
	//	return false;
	//}
	
	//First, get the editframe object and set its width and height to some default param.
	//This is to fit the page in worst cases when the calculated page height and width may be very small(very rare case).
	var frame = document.getElementById('editframe');
	var objToResize = (frame.style) ? frame.style : frame;
	
	var defaultH = 650;
	var defaultW = 650;
	
	objToResize.height = defaultH+'px';
	objToResize.width = defaultW+'px';
	
	//Second, get the loading page object (the page which is loading inside the editframe).
	var innerDoc = (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
	
	var mcFrameH = innerDoc.body.scrollHeight;
	mcFrameH = innerDoc.body.scrollHeight;
	var mcFrameW = innerDoc.body.scrollWidth;
	
	//alert("innerDoc.body.scrollHeight:"+innerDoc.body.scrollHeight + " - innerDoc.body.scrollWidth:" + innerDoc.body.scrollWidth);
	
	//Third, set the page height and width to the editframe, after adding a small length to fit
	//the padding, if any. If in any case the page height or width is showing as zero (in some cases firefox do) 
	//then we will set back to the default editframe area.
	mcFrameH = parseInt(mcFrameH);
	mcFrameH=(mcFrameH==0 ? defaultH+50 : mcFrameH+50);
	
	objToResize.height = mcFrameH + 'px';
	
	mcFrameW = parseInt(mcFrameW);
	mcFrameW=(mcFrameW==0 ? defaultW : mcFrameW+10);
	
	objToResize.width = mcFrameW + 'px';
}
function breakOut() 
{
	if (top.frames['right'].length!=0) 
	{
    	if (window.location.href.replace)
        	top.frames['right'].location.replace(self.location.href);
        else
       		top.frames['right'].location.href=self.document.href;
	}
}

function validatePassword()
{
	if(document.getElementById("oldPasswd").value == "" || document.getElementById("oldPasswd").value == null )
	{
		document.getElementById("oldPasswd").focus();
		alert("Please Enter Old Password");
		return false;
	}
	if(document.getElementById("newPasswd").value == "" || document.getElementById("newPasswd").value == null )
	{
		document.getElementById("newPasswd").focus();
		alert("Please Enter New Password");
		return false;
	}
	var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("newPasswd").value) )
          {
             document.getElementById("newPasswd").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             return false;
          }
	if(document.getElementById("confirmNewPasswd").value == "" || document.getElementById("confirmNewPasswd").value == null )
	{
		document.getElementById("confirmNewPasswd").focus();
		alert("Confirm Password");
		return false;
	}
	if(document.getElementById("confirmNewPasswd").value != document.getElementById("newPasswd").value)
	{
		document.getElementById("confirmNewPasswd").focus();
		alert("Incorrect Password");
		return false;
	}
	return true;
}
function validateProjectFundAllocation()
{
if( ( (document.getElementById("grantPeriod.id").value) == 'null') || ( (document.getElementById("grantPeriod.id").value) == '') )
    {
         alert("Please enter the Grant Period ");
         document.getElementById("grantPeriod.id").focus();
         return false;
    }
    
    if( ( (document.getElementById("accountHead.id").value) == 'null') || ( (document.getElementById("accountHead.id").value) == '') )
    {
         alert("Please enter the Account Head ");
         document.getElementById("accountHead.id").focus();
         return false;
    }
   
    if(isNaN(document.getElementById("amount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }
    if((document.getElementById("amount").value)==0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }
    if(eval(document.getElementById("amount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amount").focus();
	    return false;
    }
  }
function validateUser()
{
	 var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	if(LTrim(document.getElementById("userRealName").value) == ""){
		alert("Please Enter First Name");
	    document.getElementById("userRealName").focus();
	    return false;
	}
	if(document.getElementById("userRealName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userRealName").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("userRealName").value.length ; 
    	// alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("userRealName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in FirstName.");
	  			document.getElementById("userRealName").focus(); 
	  			return false;
  			}
    	}
 
	  }
	  
	if(LTrim(document.getElementById("userSurName").value) == ""){
		alert("Please Enter Last Name");
	    document.getElementById("userSurName").focus();
	    return false;
	}
	
	
	
	 if(document.getElementById("userSurName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userSurName").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("userSurName").value.length ; 
    	  // alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("userSurName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in LastName. .");
	  			document.getElementById("userSurName").focus(); 
	  			return false;
  			}
    	}
 
	  }
	 
	if(document.getElementById("password").value == ""){
		alert("Please enter Password");
	    document.getElementById("password").focus();
	    return false;
	}
  if(document.getElementById("confirmPasswd").value == ""){
		alert("Please enter confirm Password");
	    document.getElementById("confirmPasswd").focus();
	    return false;
	}
	if(document.getElementById("confirmPasswd").value != document.getElementById("password").value)
	{
		document.getElementById("password").focus();
		alert("Incorrect Password");
		return false;
	}
	 var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("password").value) )
          {
             document.getElementById("password").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             return false;
          }
	
	
	if(document.getElementById("email").value == ""){
		alert("Please enter email");
	    document.getElementById("email").focus();
	    return false;
	}
	
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus();
			return false;
		}
	}
	var str = document.getElementById('phNumber').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Please Enter valid numbers.");
            document.getElementById('phNumber').focus();
            return false;
         }
      } 
    
	 if( ( (LTrim(document.getElementById("aadhaarNo").value)) == 'null') || ( (document.getElementById("aadhaarNo").value) == '') )
	    {
		 	alert("Please enter Aadhaar Number");
		    document.getElementById("aadhaarNo").focus();
		    return false;
	    }
	   var str = document.getElementById('aadhaarNo').value;
	    var oneDecimal = false;
	    var oneChar = 0;
	    str = str.toString();
	   if(str.length>12)  
	      {
	      
	            alert("Please enter Aadhaar Number  less than or equal to 12 digits");
	            str.focus;
	            return false;
	      }
	    
    
	if(document.getElementById("authorities").value == "Select"){
		alert("Please Select Role");
	    document.getElementById("authorities").focus();
	    return false;
	}
	
    if(document.getElementById("institutions").value == "Select"){
		alert("Please Select an Institution");
	    document.getElementById("institutions").focus();
	    return false;
	}
	return true;

}
function validateEditUser()
{

	if(LTrim(document.getElementById("userRealName").value) == ""){
		alert("Please Enter Full Name");
	    document.getElementById("userRealName").focus();
	    return false;
	}
	
	if(LTrim(document.getElementById("userSurName").value) == ""){
		alert("Please Enter Last Name");
	    document.getElementById("userSurName").focus();
	    return false;
	}
	 var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("userRealName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userRealName").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("userRealName").value.length ; 
    	// alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("userRealName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in FirstName.");
	  			document.getElementById("userRealName").focus();
	  			return false;
  			}
    	}
 
	  }
	  
	   var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("userSurName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userSurName").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("userSurName").value.length ; 
    	// alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("userSurName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in LastName.");
	  			document.getElementById("userSurName").focus();
	  			return false;
  			}
    	}
 
	  }
	 if( ( (LTrim(document.getElementById("aadhaarNo").value)) == 'null') || ( (document.getElementById("aadhaarNo").value) == '') )
	    {
		 	alert("Please enter Aadhaar Number");
		    document.getElementById("aadhaarNo").focus();
		    return false;
	    }
	   var str = document.getElementById('aadhaarNo').value;
	    var oneDecimal = false;
	    var oneChar = 0;
	    str = str.toString();
	   if(str.length>12)  
	      {
	      
	            alert("Please enter Aadhaar Number  less than or equal to 12 digits");
	            str.focus;
	            return false;
	      }
	var str = document.getElementById('phNumber').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Please Enter valid numbers.");
            document.getElementById('phNumber').focus();
            return false;
         }
      }
   
    
	if(LTrim(document.getElementById("email").value) == ""){
		alert("Please enter email");
	    document.getElementById("email").focus();
	    return false;
	}
	

	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus();
			return false;
		}
	}
	
	var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
      if(document.getElementById("Passwd").value)
      {
       if ( !re.test(document.getElementById("Passwd").value) )
          {
             document.getElementById("Passwd").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             return false;
          }
       }
	return true;

}
 function validateProjectType()
{
     if(LTrim(document.getElementById("type").value) == "")
     {
		alert("Please Enter the Project Type");
	    document.getElementById("type").focus();
	    return false;
	}
     var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("type").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("type").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("type").value.length ; 
    	// alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("type").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Type.");
	  			document.getElementById("type").focus();
	  			return false;
  			}
    	}
 
	  }
	
	
	return true;
}
function validateAuthority()
{     
    if( ( (LTrim(document.getElementById("authority").value)) == 'null') || ( (document.getElementById("authority").value) == '') )
    {
	    alert("Please enter the Role");
	    document.getElementById("authority").focus();
	    return false;
    }
    if((document.getElementById("authority").value) == 'ROLE_')
    {
      alert("Please enter a valid Role");
      document.getElementById("authority").focus();
      return false;
      
    }
    var speclChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?0123456789";
	if(document.getElementById("authority").value != "")
	 {
    	for (var i = 5; i < (document.getElementById("authority").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("authority").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Role.");
	  			document.getElementById("authority").focus();
	  			return false;
  			}
    	}
    }
   if( (document.getElementById("authority").value) )
    {
    	var roleName= new String(document.getElementById("authority").value)
		var rolePrefix;
		rolePrefix = new String(roleName.substring(0,5));
		if(rolePrefix == 'ROLE_')
		{
			document.getElementById("authority").value = roleName.toUpperCase()
			return true;
		}
		else
		{
			alert("Incorrect Entry!!!!!!\r\r\Please Start with 'ROLE_' ");
			document.getElementById("authority").focus();
			return false;
		}
	}  
	return true;   
}
function validateDepartment()
{
        var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("name").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("name").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("name").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Department Name.");
	  			document.getElementById("name").focus();
	  			return false;
  			}
    	}
    }
       var speclChars = "!@#$%^&*()+=- []\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("departmentCode").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("departmentCode").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("departmentCode").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Department Code.");
	  			document.getElementById("departmentCode").focus();
	  			return false;
  			}
    	}
    }

   
	if(LTrim(document.getElementById("departmentCode").value) == ""){
		alert("Please Enter Department Code");
	    document.getElementById("departmentCode").focus();
	    return false;
	}
	if(LTrim(document.getElementById("name").value) == ""){
		alert("Please Enter Department Name");
	    document.getElementById("name").focus();
	    return false;
	}
	
	return true;

}

function validatePasswordRule()
{
	
	if(document.getElementById("password").value == ""){
		alert("Please enter Password");
	    document.getElementById("password").focus();
	    return false;
	}
  
	var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("password").value) )
          {
             document.getElementById("password").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             document.getElementById("password").focus();
             return false;
          }
}

function validateConfirmPasswordRule()
{
	if(document.getElementById("confirmPasswd").value == "")
	{
		alert("Please enter confirm Password");
	    document.getElementById("confirmPasswd").focus();
	    return false;
	}
	if(document.getElementById("confirmPasswd").value != document.getElementById("password").value)
	{
		document.getElementById("password").focus();
		alert("Please enter same password as above");
		  document.getElementById("confirmPasswd").focus();
		return false;
	}	

}

function validateChangePasswordRule()
{
	
	if(document.getElementById("newPasswd").value == ""){
		alert("Please enter Password");
	    document.getElementById("newPasswd").focus();
	    return false;
	}
  
	var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("newPasswd").value) )
          {
             document.getElementById("newPasswd").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             document.getElementById("newPasswd").focus();
             return false;
          }
}

function validateChangeConfirmPasswordRule()
{
	if(document.getElementById("confirmNewPasswd").value == "")
	{
		alert("Please enter confirm Password");
	    document.getElementById("confirmNewPasswd").focus();
	    return false;
	}
	if(document.getElementById("confirmNewPasswd").value != document.getElementById("newPasswd").value)
	{
		document.getElementById("confirmNewPasswd").focus();
		alert("Please enter same password as above");
		  document.getElementById("confirmNewPasswd").focus();
		return false;
	}	

}



function validateRegisterUser()
{
   var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
  
    if(LTrim(document.getElementById("email").value) == ""){
		alert("Please enter email");
	    document.getElementById("email").focus();
	    return false;
	}    
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus();
			return false;
		}
	}
	
	if(document.getElementById("password").value == ""){
		alert("Please enter Password");
	    document.getElementById("password").focus();
	    return false;
	}
  	if(document.getElementById("confirmPasswd").value == ""){
		alert("Please enter confirm Password");
	    document.getElementById("confirmPasswd").focus();
	    return false;
	}
	if(document.getElementById("confirmPasswd").value != document.getElementById("password").value)
	{
		document.getElementById("password").focus();
		alert("Incorrect Password");
		return false;
	}
	var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("password").value) )
          {
             document.getElementById("password").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             return false;
          }
          
	
	if(LTrim(document.getElementById("userRealName").value) == ""){
		alert("Please Enter First Name");
	    document.getElementById("userRealName").focus();
	    return false;
	}
	
		if(document.getElementById("userRealName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userRealName").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("userRealName").value.length ; 
    	// alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("userRealName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in FirstName.");
	  			document.getElementById("userRealName").focus(); 
	  			return false;
  			}
    	}
 
	  }
	
	if(LTrim(document.getElementById("userSurName").value) == ""){
		alert("Please Enter last Name");
	    document.getElementById("userSurName").focus();
	    return false;
	}
	
	 if(document.getElementById("userSurName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userSurName").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("userSurName").value.length ; 
    	  // alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("userSurName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in LastName. .");
	  			document.getElementById("userSurName").focus(); 
	  			return false;
  			}
    	}
 
	  }
	  
	var str = document.getElementById('phNumber').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Please Enter valid numbers.");
            document.getElementById('phNumber').focus();
            return false;
         }
      } 
      
	if(LTrim(document.getElementById("code").value) == ""){
		alert("Please enter Institution code");
	    document.getElementById("code").focus();
	    return false;
	}
	
		return true;

}



function validateAttachmentType()
{

      var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("type").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("type").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("type").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Type.");
	  			document.getElementById("type").focus();
	  			return false;
  			}
    	}
    }
   if( ( (document.getElementById("documentType").value) == 'null') || ( (document.getElementById("documentType").value) == '') )
	{
		alert("Please Enter the Document Type");
	    document.getElementById("documentType").focus();
	    return false;
	}
    
	if( ( (LTrim(document.getElementById("type").value)) == 'null') || ( (document.getElementById("type").value) == '') )
	{
		alert("Please Enter the Type");
	    document.getElementById("type").focus();
	    return false;
	}
	

	return true;
}
function validateAttachmentTypeEdit()
{

      var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("type").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("type").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("type").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Type.");
	  			document.getElementById("type").focus();
	  			return false;
  			}
    	}
    }

	if( ( (LTrim(document.getElementById("type").value)) == 'null') || ( (document.getElementById("type").value) == '') )
	{
		alert("Please Enter the Type");
	    document.getElementById("type").focus();
	    return false;
	}
	
	return true;
}
function validateItemPurchase()
{
	if(LTrim(document.getElementById("name").value) == "")
	{
		alert("Please Enter item name");
	    document.getElementById("name").focus();
	    return false;
	}
	
	
	if(LTrim(document.getElementById("assetCode").value) == "")
	{
		alert("Please Enter asset code");
	    document.getElementById("assetCode").focus();
	    return false;
	}
	
	if(LTrim(document.getElementById("orderNo").value) == 0)
	{
        alert("Please Enter order No");
	    document.getElementById("orderNo").focus();
	    return false;
	}
    if(document.getElementById("cost").value ==0)
    {
		alert("Please Enter cost");
	    document.getElementById("cost").focus();
	    return false;
    }
	if(document.getElementById("cost").value <= 0)
	{
		alert("Please Enter Proper cost");
	    document.getElementById("cost").focus();
	    return false;
	}
	if(isNaN(document.getElementById("cost").value))
    {
	    alert("Invalid Cost  ");
	    document.getElementById("cost").focus();
	    return false;
    }
	
	return true;
}
            
function validateProjectEmployee()
{

	if(LTrim(document.getElementById("empName").value) == "")
	{
		alert("Please Enter Employee Name");
	    document.getElementById("empName").focus();
	    return false;
	}
	
	
	 var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("empNo").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("empNo").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("empNo").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in the Employee Number Field.");
	  			document.getElementById("empNo").focus();
	  			return false;
  			}
    	}
    	}
	
    
	if(LTrim(document.getElementById("empNo").value) == "")
	{
		alert("Please Enter Employee Number");
	    document.getElementById("empNo").focus();
	    return false;
	}
	
	
     
	 if(eval(document.getElementById("empNo").value)<=0)
    {
	    alert("Please enter a valid Number ");
	    document.getElementById("empNo").focus();
	    return false;
    }
    
   
  
	if( ( (document.getElementById("employeeDesignation.id").value) == 'null')  )
	{
	    alert("Please enter Designation");
	    document.getElementById("employeeDesignation.id").focus();
	    return false;
	}
	
	var employeeJoiningDateYear = document.getElementById("joiningDate_year").value;
	var employeeJoiningDateMonth = document.getElementById("joiningDate_month").value;
	var employeeJoiningDateDate = document.getElementById("joiningDate_day").value;
	
	var employeeRelievingDateYear = document.getElementById("relievingDate_year").value;
	var employeeRelievingDateMonth = document.getElementById("relievingDate_month").value;
	var employeeRelievingDateDate = document.getElementById("relievingDate_day").value;
	
	var newEmployeeJoiningDate = new Date(employeeJoiningDateYear,employeeJoiningDateMonth-1,employeeJoiningDateDate);
	var newEmployeeRelievingDate = new Date(employeeRelievingDateYear,employeeRelievingDateMonth-1,employeeRelievingDateDate);
 
	if(document.getElementById("relievingDate").value != 'null')
	{
    	if (newEmployeeJoiningDate > newEmployeeRelievingDate)
		{
			alert("Joining Date should be less than Relieving Date");
			document.getElementById("relievingDate").focus();
			return false;
		}
	}
	
	var employeeDateOfBirthYear = document.getElementById("dOB_year").value;
	var employeeDateOfBirthMonth = document.getElementById("dOB_month").value;
	var employeeDateOfBirthDate = document.getElementById("dOB_day").value;
	
	
	var newEmployeeDateOfBirth = new Date(employeeDateOfBirthYear,employeeDateOfBirthMonth-1,employeeDateOfBirthDate);
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	var curr_year = d.getFullYear();
	var Today = new Date(curr_year,curr_month,curr_date);
		
	if(newEmployeeDateOfBirth >= Today)
	{
			alert("Date of Birth sholud be less than Current Date");
			document.getElementById("dOB_day").focus();
			return false;
	}
	if(newEmployeeDateOfBirth >= newEmployeeJoiningDate)
	{
			alert("Date of Birth sholud be less than Joining Date")
			document.getElementById("dOB_day").focus();
			return false;
	}
	
    return true;
}

function validateProjectEmployeeExperience()
{
	if(LTrim(document.getElementById("organizationName").value) == "")
	{
		alert("Please Enter organization name");
	    document.getElementById("organizationName").focus();
	    return false;
	}
	

	if(document.getElementById("joiningDate").value == 'null')
	{
		alert("Please Enter joining date");
	    document.getElementById("joiningDate").focus();
	    return false;
	}
	if(document.getElementById("relievingDate").value == 'null')
	{
		alert("Please Enter relieving date");
	    document.getElementById("relievingDate").focus();
	    return false;
	}
	if(LTrim(document.getElementById("designation").value) == "")
	{
		alert("Please Enter designation");
	    document.getElementById("designation").focus();
	    return false;
	}
	
	var employeeJoiningDateYear = document.getElementById("joiningDate_year").value;
	var employeeJoiningDateMonth = document.getElementById("joiningDate_month").value;
	var employeeJoiningDateDate = document.getElementById("joiningDate_day").value;
	
	var employeeRelievingDateYear = document.getElementById("relievingDate_year").value;
	var employeeRelievingDateMonth = document.getElementById("relievingDate_month").value;
	var employeeRelievingDateDate = document.getElementById("relievingDate_day").value;
	
	var newEmployeeJoiningDate = 
			new Date(employeeJoiningDateYear,employeeJoiningDateMonth-1,employeeJoiningDateDate);
	var newEmployeeRelievingDate = 
			new Date(employeeRelievingDateYear,employeeRelievingDateMonth-1,employeeRelievingDateDate);
			    	
	if (newEmployeeJoiningDate>newEmployeeRelievingDate)
	{
		alert("Joining Date should be less than Relieving Date");
		document.getElementById("relievingDate").focus();
		return false;
	}
	
	return true;
}

function validateProjectEmployeeQualification()
{
	if(LTrim(document.getElementById("examname").value) == "")
	{
		alert("Please Enter exam name");
	    document.getElementById("examname").focus();
	    return false;
	}
	
	
	 var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("examname").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("examname").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("examname").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed for Exam name.");
	  			document.getElementById("examname").focus();
	  			return false;
  			}
    	}
    	}
	
	if(LTrim(document.getElementById("university").value) == "")
	{
		alert("Please Enter university");
	    document.getElementById("university").focus();
	    return false;
	}
	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("university").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("university").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("university").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed for University.");
	  			document.getElementById("university").focus();
	  			return false;
  			}
    	}
    	}
	if(document.getElementById("passoutYear").value == 'null')
	{
		alert("Please Enter passout year");
	    document.getElementById("passoutYear").focus();
	    return false;
	}
	if(document.getElementById("percMark").value == 0)
	{
		alert("Please Enter perc mark");
	    document.getElementById("percMark").focus();
	    return false;
	}
	if(isNaN(document.getElementById("percMark").value))
    {
	    alert("Invalid Percentage");
	    document.getElementById("percMark").focus();
	    return false;
    }
      if(eval(document.getElementById("percMark").value)<=0)
    {
	    alert("Please enter a valid Percentage ");
	    document.getElementById("percMark").focus();
	    return false;
    }
    
	return true;
}

function validateProjectEmployeeSalaryDetails()
{
	if(document.getElementById("salaryAmount").value == 0)
	{
		alert("Please Enter salary amount");
	    document.getElementById("salaryAmount").focus();
	    return false;
	}
	if(document.getElementById("salaryAmount").value <= 0)
	{
		alert("Please Enter Proper salary amount");
	    document.getElementById("salaryAmount").focus();
	    return false;
	}
	if(isNaN(document.getElementById("salaryAmount").value))
	{
	    alert("Invalid Salary Amount  ");
	    document.getElementById("salaryAmount").focus();
	    return false;
    }
	if( ( (document.getElementById("salaryComponent.id").value) == 'null') || 
			( (document.getElementById("salaryComponent.id").value) == '') )
    {
	    alert("Please enter the Salary Component");
	    document.getElementById("salaryComponent.id").focus();
	    return false;
    }
	if(document.getElementById("withEffectFrom").value == 'null')
	{
		alert("Please Enter With Effect From Date");
	    document.getElementById("withEffectFrom").focus();
	    return false;
	}
	
	return true;
}
function validateSalaryComponent()
{

        var speclChars = "!@#$%^&*()+=[]\\\';:,./{}|\"<>?0123456789";
	
	if(document.getElementById("name").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("name").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("name").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Component Name.");
	  			document.getElementById("name").focus();
	  			return false;
  			}
    	}
    }
	if(LTrim(document.getElementById("name").value) == 'null' || document.getElementById("name").value == '' )
	{
		alert("Please Enter the Component Name");
	    document.getElementById("name").focus();
	    return false;
	}
	 
	return true;
}
function validateEmployeeDesignation()
{


   var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("designation").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("designation").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("designation").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Designation.");
	  			document.getElementById("designation").focus();
	  			return false;
  			}
    	}
    }
    
    
	if(LTrim(document.getElementById("designation").value) == 'null' || document.getElementById("designation").value == '' )
	{
		alert("Please Enter the Designation");
	    document.getElementById("designation").focus();
	    return false;
	}
	
	
	return true;
}


function validateProposalfilter()
{


  if(isNaN(document.getElementById("proposalFilter").value))
    {
	    alert("Invalid Number  ");
	    document.getElementById("proposalFilter").focus();
	    return false;
    }
    
	if(LTrim(document.getElementById("proposalFilter").value) == 'null' || document.getElementById("proposalFilter").value == '' )
	{
		alert("Please Enter No.Of Days ");
	    document.getElementById("proposalFilter").focus();
	    return false;
	}
	
	
	return true;
}









  function Redirect()
        {
       
         	 
         window.location="../login";
        }
  function RedirectToLogin()
    {
   
     	 
     window.location="/gms/login";
    }
    

function validateFundTransfer()
{
if(document.getElementById("fundTransfer.id").value == 'null' || document.getElementById("fundTransfer.id").value == '' )
	{
		alert("Please Select a Fund Transfer");
	    document.getElementById("fundTransfer.id").focus();
	    return false;
	}
	
	return true;
}

function displayAlertMessage(message) 
{
	jQuery('submit').attr('disabled', 'disabled')
	var timeOut = 2000;
	jQuery('#messageBox').text(message).fadeIn()
	jQuery('#messageBox').css({'display' : 'block', 'padding' : '10px','border': '1px solid #B0B0B0','width': '25%'})
	setTimeout(function() 
	{
	jQuery('#messageBox').fadeOut()
	jQuery('#messageBox').css("display", "none")
	}, timeOut * 1000);
}

function combineAlertAndRegister() 
{
	var retvalue;
	retvalue = validateRegisterUser();
	if(retvalue == true) { return displayAlertMessage("Please wait"); }
	return retvalue
}
function isDate(txtDate){  
  var objDate;  // date object initialized from the txtDate string  
  var mSeconds; // milliseconds from txtDate  
  
  // date length should be 10 characters - no more, no less  
  if (txtDate.length != 10) return false;  
  
  // extract day, month and year from the txtDate string  
  // expected format is mm/dd/yyyy  
  // subtraction will cast variables to integer implicitly  
  var month   = txtDate.substring(3,5)  - 1;  
  var day = txtDate.substring(0,2)  - 0; // because months in JS start with 0  
  var year  = txtDate.substring(6,10) - 0;  
  
  // third and sixth character should be /  
  if (txtDate.substring(2,3) != '/') return false;  
  if (txtDate.substring(5,6) != '/') return false;  
  
  // test year range  
  if (year < 999 || year > 3000) return false;  
  
  // convert txtDate to the milliseconds  
  mSeconds = (new Date(year, month, day)).getTime();  
  
  // initialize Date() object from calculated milliseconds  
  objDate = new Date();  
  objDate.setTime(mSeconds);  
  
  // compare input parameter date and created Date() object  
  // if difference exists then date isn't valid  
  if (objDate.getFullYear() != year)  return false;  
  if (objDate.getMonth()    != month) return false;  
  if (objDate.getDate()     != day)   return false;  
  
  // otherwise return true  
  return true;  
}
function checkDate(dateValue)
{
	Field=dateValue;
	if(isDate(dateValue.value) || dateValue.value=="")
	{
		return true;
	}
	else
	{
		alert("Please enter a date in the format dd/mm/yyyy");
		setTimeout("Field.focus();",0);
		return false;
	}
}
function validAuthority(authority)
{
    
    if( (document.getElementById("authority").value) )
    {
        
    	var roleName= new String(document.getElementById("authority").value)
		var rolePrefix;
		rolePrefix = new String(roleName.substring(0,5));
		
		if(rolePrefix != 'ROLE_')
		{
		    document.getElementById("authority").value = "ROLE_"+roleName;
			return true;
		}
	}
}

function validateEligibilityCriteria()
{
	if(LTrim(document.getElementById("eligibilityCriteria").value) == 'null' || document.getElementById("eligibilityCriteria").value == '' )
	{
		alert("Please Enter the Criteria Name");
	    document.getElementById("eligibilityCriteria").focus();
	    return false;
	}
	
	
	 var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("eligibilityCriteria").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("eligibilityCriteria").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("eligibilityCriteria").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Eligibility Criteria .");
	  			document.getElementById("eligibilityCriteria").focus();
	  			return false;
  			}
    	}
    }
    
	return true;
}
 
 function checkAllMembers()
    {
      
 
    	if(document.appAuthority.AllMembers.checked==true)
    	{
    		for(var i=0;i<document.appAuthority.person.length;i++)
    		{
    			document.appAuthority.person[i].checked=true;
    		}
    	}
    	else
    	{
    		for(var i=0;i<document.appAuthority.person.length;i++)
    		{
    			document.appAuthority.person[i].checked=false;
    		}
    	}
    }
    
    
   function checkAllApprovalAuthorityDetails()
    {
      
 
    	if(document.appAuthority.AllapprovalAuthorityDetail.checked==true)
    	{
    		for(var i=0;i<document.appAuthority.approvalAuthority.length;i++)
    		{
    			document.appAuthority.approvalAuthority[i].checked=true;
    		}
    	}
    	else
    	{
    		for(var i=0;i<document.appAuthority.approvalAuthority.length;i++)
    		{
    			document.appAuthority.approvalAuthority[i].checked=false;
    		}
    	}
    } 
 
 	function validateAppAuthorityMembers()
	{ 
		var isChecked=0;
		if(document.appAuthority.person.length > 1)  
		{
			for(var i=0;i<document.appAuthority.person.length;i++)
			{
				if(document.appAuthority.person[i].checked==true)
				{
					isChecked++;
				}
				if (isChecked > 0) 
				{ 
					break; 
				}
			}
			if (isChecked <= 0) 
			{ 
				alert('You didn\'t check a person name. Please check a box.'); 
				return false;
			}
		}
		else
		{
	       if(document.appAuthority.person.checked!=true)
			{
				alert('You didn\'t check a person name. Please check a box.'); 
				return false;
			}
		}
		
	docclick++;
		if(docclick>1)
		{
			return false;
		}
			setTimeout('docclick=0', 2000);
	  
  		return true;
	}
	
	
function validatePreProposal()
  {
  	if(LTrim(document.getElementById("projectTitle").value) == "")
	{
		alert("Please Enter the Proposal Title");
	    document.getElementById("projectTitle").focus();
	    return false;
	}
	  if( ( (document.getElementById("proposalCategory").value) == 'null') || ( (document.getElementById("proposalCategory").value) == '') )
      {
		alert("Please Enter the Proposal Category");
	    document.getElementById("proposalCategory").focus();
	    return false;
	}
	return true;
 }
 
 function validateProposalCategory()
{

var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("name").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("name").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("name").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Category Name.");
	  			document.getElementById("name").focus();
	  			return false;
  			}
    	}
    }
   if(LTrim(document.getElementById("name").value) == "")
	{
		alert("Please Enter the Name");
	    document.getElementById("name").focus();
	    return false;
	}
	return true;
}

function validateApprovalAuthority()
{   
          
    var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("name").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("name").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("name").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Name.");
	  			document.getElementById("name").focus();
	  			return false;
  			}
    	}
    }
           if(LTrim(document.getElementById("name").value) == "")
	{
		alert("Please Enter the Name");
	    document.getElementById("name").focus();
	    return false;
	}

   	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus();
			return false;
		}
	}
	
   
	return true;
}

function validateApprovalAuthorityMap()
{  
   if( ( (document.getElementById("proposalType").value) == 'null') || ( (document.getElementById("proposalType").value) == '') )
	 {
	    alert("Please enter a Proposal Type");
	    document.getElementById("proposalType").focus();
	    return false;
    } 
    
    if( ( (document.getElementById("proposalId").value) == 'null') || ( (document.getElementById("proposalId").value) == '') )
	 {
	    alert("Please enter a Proposal Title");
	    document.getElementById("proposalId").focus();
	    return false;
    } 
    if( ( (document.getElementById("approvalAuthority.id").value) == 'null') || ( (document.getElementById("approvalAuthority.id").value) == '') )
	  {
	    alert("Please enter a Approval Authority");
	    document.getElementById("approvalAuthority.id").focus();
	    return false;
    } 
    
    var str = document.getElementById("approveOrder").value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only numbers into the Approve Order Field.");
            document.getElementById('approveOrder').focus();
            return false;
         }
      }   
   if((document.getElementById("approveOrder").value)== 0)
    {
	    alert("Please enter a Approve Order  ");
	    document.getElementById("approveOrder").focus();
	    return false;
    }
    
    if(LTrim(document.getElementById("approveOrder").value) =="")
    {
	    alert("Please enter a Approve Order");
	    document.getElementById("approveOrder").focus();
	    return false;
    } 
     
    
     var str = document.getElementById('processRestartOrder').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only numbers into the Process Restart Order Field.");
            document.getElementById('processRestartOrder').focus();
            return false;
         }
      }
       if((document.getElementById("processRestartOrder").value)== 0)
    {
	    alert("Please enter a Process RestartOrder  ");
	    document.getElementById("processRestartOrder").focus();
	    return false;
    }   
    
    if(LTrim(document.getElementById("processRestartOrder").value) =="")
    {
	    alert("Please enter a Process RestartOrder");
	    document.getElementById("processRestartOrder").focus();
	    return false;
    } 
     return true;
 }
 	
 	function validateEditApprovalAuthorityMap()
{  

    if(LTrim(document.getElementById("approvalAuthority.id").value) =="")
    {
	    alert("Please enter a Approval Authority");
	    document.getElementById("approvalAuthority.id").focus();
	    return false;
    } 
    
    var str = document.getElementById('approveOrder').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only numbers into the Approve Order Field.");
            document.getElementById('approveOrder').focus();
            return false;
         }
      }   
 
    if(LTrim(document.getElementById("approveOrder").value) =="")
    {
	    alert("Please enter a Approve Order");
	    document.getElementById("approvalAuthority.id").focus();
	    return false;
    } 
    
     var str = document.getElementById('processRestartOrder').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only numbers into the Process Restart Order Field.");
            document.getElementById('processRestartOrder').focus();
            return false;
         }
      }   
    
    if(LTrim(document.getElementById("processRestartOrder").value) =="")
    {
	    alert("Please enter a Process RestartOrder");
	    document.getElementById("processRestartOrder").focus();
	    return false;
    } 
    
   
     return true;
 	}
 	
 	
  	function validateUploadProposalForm()
	{     
 
	var id_value = document.getElementById('attachmentPath').value;
 
	if(id_value != '')
	{ 
		var valid_extensions = /(.html|.htm)$/i;   
  		if(valid_extensions.test(id_value))
  		{ 
  			 return true;
	}
	else
	 {
 		alert('This File Is Not Allowed')
 		document.getElementById("attachmentPath").focus();
 		return false;
		}
	} 	
	if( ( (document.getElementById("attachmentPath").value) == 'null') || ( (document.getElementById("attachmentPath").value) == '') )
    {
 		alert("Please upload a application form");
	    document.getElementById("attachmentPath").focus();
	    return false;
    }
		    
	return true;
}
	
	
 
  function validateAppAuthorityPersonForDelete()
	{ 
	
	
	var isChecked=0;  
		   if(document.appAuthority.approvalAuthority.length < 0)
		   {
		   	alert("All actions are deleted")
		   }
		  else if(document.appAuthority.approvalAuthority.length > 1)  
		  {
		for(var i=0;i<document.appAuthority.approvalAuthority.length;i++)
		{	
			if(document.appAuthority.approvalAuthority[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert('You didn\'t Check an approvalAuthorityDetail  for delete. Please check a box.'); 
			return false;
		}
	}
		else
		{
			if(document.appAuthority.approvalAuthority.checked!=true)
			{
				alert('You didn\'t Check an approvalAuthorityDetail  for delete. Please check a box.'); 
				return false;
			}
		}
		

   return true;
		  
	 
	}
	function checkAllApprovalAuthorityMap()
	{
	
	 if(document.proposalApprovalAuthorityMap.AllApprovalAuthority.checked==true)
    	{
    		for(var i=0;i<document.proposalApprovalAuthorityMap.approvalAuthority.length;i++)
    		{
    			document.proposalApprovalAuthorityMap.approvalAuthority[i].checked=true;
    		}
    	}
    	else
    	{
    		for(var i=0;i<document.proposalApprovalAuthorityMap.approvalAuthority.length;i++)
    		{
    			document.proposalApprovalAuthorityMap.approvalAuthority[i].checked=false;
    		}
    	}

	}
	

	
	function checkAllApprovalAuthorityMapDetails()
	{
	
	 if(document.proposalApprovalAuthorityMap.AllapprovalAuthorityMapDetail.checked==true)
    	{
    		for(var i=0;i<document.proposalApprovalAuthorityMap.proposalApprovalAuthority.length;i++)
    		{
    			document.proposalApprovalAuthorityMap.proposalApprovalAuthority[i].checked=true;
    		}
    	}
    	else
    	{
    		for(var i=0;i<document.proposalApprovalAuthorityMap.proposalApprovalAuthority.length;i++)
    		{
    			document.proposalApprovalAuthorityMap.proposalApprovalAuthority[i].checked=false;
    		}
    	}

	}
	
	function validateProposalsubmit(dateValue)
	{	
		
		
		var elem = dateValue.split(" ");
		var ldate = elem[0];
		var lastDate = new Date(ldate);
		
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth();
		var curr_year = d.getFullYear();
		var Today = new Date(curr_year,curr_month,curr_date);
		
		if(Today >= lastDate)
    	{
	        alert("Last Date for Proposal Submission is Over");
	        return false; 
    	}
		return true;
	}
		
	
	function validateExpenseRequestEntry()
	{
		if( ( (document.getElementById("projects.id").value) == 'null') || ( (document.getElementById("projects.id").value) == '') )
	    {
		    alert("Please select a project");
		    document.getElementById("projects.id").focus();
		    return false;
	    }
	    
	    var speclChars = "!@#$%^&*+='[];,{}<>?";
	
		if(document.getElementById("purchaseOrderNo").value != "")
		 {
	    	
	    	for (var i = 0; i < (document.getElementById("purchaseOrderNo").value.length); i++) 
	    	{
	   	 		if (speclChars.indexOf(document.getElementById("purchaseOrderNo").value.charAt(i)) != -1) 
	  			{
		  			alert ("Special characters are not allowed in Purchase Order No.");
		  			document.getElementById("purchaseOrderNo").focus();
		  			return false;
	  			}
	    	}
	    }
    
		if( ( (LTrim(document.getElementById("expenseDescription").value)) == 'null') || ( (document.getElementById("expenseDescription").value) == '') )
	    {
		    alert("Please enter the Expense Description");
		    document.getElementById("expenseDescription").focus();
		    return false;
	    }
	    
	    if(document.getElementById("expenseAmount").value == 0)
		{
			alert("Please Enter Expense amount");
		    document.getElementById("expenseAmount").focus();
		    return false;
		}
		if(document.getElementById("expenseAmount").value <= 1)
		{
			alert("Please Enter Proper Expense amount");
		    document.getElementById("expenseAmount").focus();
		    return false;
		}
		if(isNaN(document.getElementById("expenseAmount").value))
		{
		    alert("Invalid Expense Amount  ");
		    document.getElementById("expenseAmount").focus();
		    return false;
	    }
	    if(document.getElementById("invoiceNo").value != "")
		 {
	    	
	    	for (var i = 0; i < (document.getElementById("invoiceNo").value.length); i++) 
	    	{
	   	 		if (speclChars.indexOf(document.getElementById("invoiceNo").value.charAt(i)) != -1) 
	  			{
		  			alert ("Special characters are not allowed in Invoice No.");
		  			document.getElementById("invoiceNo").focus();
		  			return false;
	  			}
	    	}
	    }
	    if(document.getElementById("invoiceAmount").value < 0)
		{
			alert("Please Enter Proper Invoice amount");
		    document.getElementById("invoiceAmount").focus();
		    return false;
		}
		if(isNaN(document.getElementById("invoiceAmount").value))
		{
		    alert("Invalid Invoice Amount  ");
		    document.getElementById("invoiceAmount").focus();
		    return false;
	    }
	    
	return true;
	
	} 	
	
	function validateExpenseRequestDetails()
	{
		if(document.getElementById("invoiceAmount").value < 0)
		{
			alert("Please Enter Proper Invoice amount");
		    document.getElementById("invoiceAmount").focus();
		    return false;
		}
		if(isNaN(document.getElementById("invoiceAmount").value))
		{
		    alert("Invalid Invoice Amount  ");
		    document.getElementById("invoiceAmount").focus();
		    return false;
	    }
	    
	    if((document.getElementById("approvalAuthority.id").value) == 'null')
	    {
		    alert("Please select an ApprovalAuthority to send request");
		    document.getElementById("approvalAuthority.id").focus();
		    return false;
	    }
    
		    
		return true;
    }
    function validateApprovalAuthorityApproveReject()
    {
	    if((document.getElementById("approvalAuthority.id").value) == 'null')
	    {
		    alert("Please select an ApprovalAuthority to send request");
		    document.getElementById("approvalAuthority.id").focus();
		    return false;
	    }
	    
		return true;
	    
    }
    
    function validateStatusApproveReject()
    {
    
    myOption = -1;
    
		for (i=document.expenseApRej.status.length-1; i > -1; i--) 
		{
			if (document.expenseApRej.status[i].checked) 
			{
				myOption = i; i = -1;
			}
		}
		if (myOption == -1) 
		{
		alert("Please select status of your evaluation");
		return false;
		}
    
    
    
	return true;
    
    }
    function validateAttachments()
    {
    	if( ( (document.getElementById("attachmentType.id").value) == 'null') || ( (document.getElementById("attachmentType.id").value) == '') )
	    {
	 		alert("Please select attachment type");
		    document.getElementById("attachmentType.id").focus();
		    return false;
	    }	
	    return true;
    }
	function validateSearchProjects()
	 { 
	    if((document.getElementById("name").value =="") && (document.getElementById("code").value =="") && (document.getElementById("selectAll").checked!=true))
	    {
		    alert("Please select any search criteria");
		    document.getElementById("name").focus();
		    return false;
	    }  
	   
	     return true;
	 }
	
	 function validateadvancedSearchProjects()
	 {
	 	if((document.getElementById("name").value =="") && (document.getElementById("code").value =="") && (document.getElementById("selectAll").checked!=true) && (document.getElementById("projectType").selectedIndex=="0") && (document.getElementById("investigator.id").selectedIndex == "0") && (document.getElementById("projectStatus").selectedIndex == "0") && (document.getElementById("datepicker1").value=="") && (document.getElementById("datepickerTo").value=="") && (document.getElementById("datepicker2").value=="") && (document.getElementById("datepickerEndTo").value==""))
	    {
		    alert("Please select any search criteria");
		    document.getElementById("name").focus();
		    return false;
	    }  
	   
	     return true;
	 }
 	function grantSearchProjects()
	 {
	 	if((document.getElementById("minimumAmount").value =="") && (document.getElementById("maximumAmount").value =="") && (document.getElementById("selectAll").checked!=true) && (document.getElementById("granter").selectedIndex=="0") && (document.getElementById("investigator.id").selectedIndex=="0") && (document.getElementById("partyDepartment").selectedIndex=="0")&& (document.getElementById("datepicker1").value=="") && (document.getElementById("datepickerTo").value==""))
		    {
			    alert("Please select any search criteria");
			    document.getElementById("minimumAmount").focus();
			    return false;
		    }  
		   
		     return true;
	 
	 }
	 function validateNotificationsAttachments()
	 {
		 if((document.getElementById("attachmentType.id").value =='null') || (document.getElementById("attachmentType.id").value ==''))
		    {
			    alert("Please Enter the Type");
			    document.getElementById("attachmentType.id").focus();
			    return false;
		    }  
		   
		     return true;
	 
	 }
 function validateProjectPIMap()
 {
 if( ( (document.getElementById("investigator.id").value) == 'null') || ( (document.getElementById("investigator.id").value) == '') )
	    {
	 		alert("Please select investigator");
		    document.getElementById("investigator.id").focus();
		    return false;
	    }	
	    if( ( (document.getElementById("role").value) == 'null') || ( (document.getElementById("role").value) == '') )
	    {
	 		alert("Please select role");
		    document.getElementById("role").focus();
		    return false;
	    }
	    return true;
    }
    
function validateEvalScale()
 {
  
   if( ( (LTrim(document.getElementById("scaleTitle").value)) == 'null') || ( (document.getElementById("scaleTitle").value) == '') )
	    {
		    alert("Please enter a scaleTitle");
		    document.getElementById("scaleTitle").focus();
		    return false;
	    }
 }
 
 function validateEvalScaleOptions()
 {
    if( ( (document.getElementById("evalScale.id").value) == 'null') || ( (document.getElementById("evalScale.id").value) == '') )
	    {
		    alert("Please select an evalScale");
		    document.getElementById("evalScale.id").focus();
		    return false;
	    }
	if( ( (LTrim(document.getElementById("scaleOption").value)) == 'null') || ( (document.getElementById("scaleOption").value) == '') )
	    {
		    alert("Please enter a scaleOption");
		    document.getElementById("scaleOption").focus();
		    return false;
	    }
	if( ( (LTrim(document.getElementById("scaleOptionIndex").value)) == 'null') || ( (document.getElementById("scaleOptionIndex").value) == '') )
	   {
		    alert("Please enter an Index");
		    document.getElementById("scaleOptionIndex").focus();
		    return false;
	  }
	  if(document.getElementById("scaleOptionIndex").value <= 0)
		{
			alert("Please Enter Proper ScaleOption Index");
		    document.getElementById("scaleOptionIndex").focus();
		    return false;
		}
		 var str = document.getElementById('scaleOptionIndex').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the Scale Option Index Field.");
            document.getElementById('scaleOptionIndex').focus();
            return false;
         }
      }   
		
	  
	    var speclChars = "!@#$%^&*+='[];,{}<>?";
	
		if(document.getElementById("scaleOptionIndex").value != "")
		 {
	    	
	    	for (var i = 0; i < (document.getElementById("scaleOptionIndex").value.length); i++) 
	    	{
	   	 		if (speclChars.indexOf(document.getElementById("scaleOptionIndex").value.charAt(i)) != -1) 
	  			{
		  			alert ("Special characters are not allowed in Scale Option Index");
		  			document.getElementById("scaleOptionIndex").focus();
		  			return false;
	  			}
	    	}
	    }
	    
	    if(isNaN(document.getElementById("scaleOptionIndex").value))
    	{
	            alert("Invalid scaleOptionIndex  ");
		    document.getElementById("scaleOptionIndex").focus();
		    return false;
    	}
 }
 
 function validateEvalItem()
 {
     if( ( (LTrim(document.getElementById("item").value)) == 'null') || ( (document.getElementById("item").value) == '') )
	    {
		    alert("Please enter an Question");
		    document.getElementById("item").focus();
		    return false;
	    }
	 
	  
	   if( ( (document.getElementById("evalScale.id").value) == 'null') || ( (document.getElementById("evalScale.id").value) == '') )
	    {
		    alert("Please select an Evaluation Scale");
		    document.getElementById("evalScale.id").focus();
		    return false;
	    }
	      
	    
  }
  
   function validateDepartmentMap()
 {
  if( ( (LTrim(document.getElementById("partyDepartment.id").value)) == 'null') || ( (document.getElementById("partyDepartment.id").value) == '') )
	    {
		    alert("Please enter a Department");
		    document.getElementById("partyDepartment.id").focus();
		    return false;
	    }
 }
  
   function validateRolePrivilege()
 {
  if( ( (document.getElementById("authority").value) == 'null') || ( (document.getElementById("authority").value) == '') )
	    {
		    alert("Please enter a Authority Name");
		    document.getElementById("authority").focus();
		    return false;
	    }
 }
 
  function validateRolePrivilegeForSuperAdmin()
 {
  
  if( ( (document.getElementById("authority").value) == 'null') || ( (document.getElementById("authority").value) == '') )
	    {
		    alert("Please enter a Authority Name");
		    document.getElementById("authority").focus();
		    return false;
	    }
 if( ( (document.getElementById("institutions").value) == 'null') || ( (document.getElementById("institutions").value) == '') )
   	    {
		    alert("Please enter an institution Name");
		    document.getElementById("institutions").focus();
		    return false;
	    }
	    
 }
  
function checkAllBox(all,instance)
{	
	if(all.checked==true)
	{
		if(instance)
		{
		   instance.checked=true;
		}
		for(var i=0;i<instance.length;i++)
		{
			instance[i].checked=true;
		}
	}
	else
	{
		if(instance)
		 {
		  instance.checked=false;
		 }
		for(var i=0;i<instance.length;i++)
		{
			instance[i].checked=false;
		}
	}
}
function validateCheckBox(instance,msg)
{ 
	var isChecked=0; 
	if(instance.length > 1)  
	{ 
		for(var i=0;i<instance.length;i++)
		{
			if(instance[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert(msg); 
			return false;
		}
		}
		else
		{
			if(instance.checked!=true)
			{
				alert(msg); 
				return false;
			}
		}
		
	//rolePrivi.submit();	
   return true;
}

function validateEvalItemNotificationMap(firstInstance,ms,instance,msg)
{ 
	if(firstInstance.value == 'null')
	{
		alert(ms);
		firstInstance.focus();
		return false;
	}
	if(!validateCheckBox(instance,msg))
	{
		return false;
	}
	docclick++;
		if(docclick>1)
		{
			return false;
		}
			setTimeout('docclick=0', 2000);
	
   return true;
}



   function validateNotificationAuthorityMap()
 {
  if ((document.getElementById("approvalAuthorityId").value) == 'null') 
	    {
		    alert("Please Select a Approval Authority");
		    document.getElementById("approvalAuthorityId").focus();
		    return false;
	    }
 }
 
 
 function validateFinancialYear()
 {
    if( ( (LTrim(document.getElementById("financialPeriod").value)) == 'null') || ( (document.getElementById("financialPeriod").value) == '') )
    {
	    alert("Please enter the Financial Period");
	    document.getElementById("financialPeriod").focus();
	    return false;
    }
 	
    var startDateYear = document.getElementById("financialStartDate_year").value;
	var startDateMonth = document.getElementById("financialStartDate_month").value;
	var startDateDate = document.getElementById("financialStartDate_day").value;
	
	var endDateYear = document.getElementById("financialEndDate_year").value;
	var endDateMonth = document.getElementById("financialEndDate_month").value;
	var endDateDate = document.getElementById("financialEndDate_day").value;
    	
    	var newStartDate = new Date(startDateYear,startDateMonth-1,startDateDate);
    	var newEndDate = new Date(endDateYear,endDateMonth-1,endDateDate);
    	 
    	
    	if (newStartDate>=newEndDate)
    	{
    		alert(" End Date should be greater than Start Date");
    		document.getElementById("financialEndDate").focus();
    		return false;
    	}
   
 }
 
  function validateBudgetModuleMap()
  {
  if( ( (document.getElementById("budgetMaster.id").value) == 'null') || ( (document.getElementById("budgetMaster.id").value) == '') )
    {
	    alert("Please enter the Budget Master Title");
	    document.getElementById("budgetMaster.id").focus();
	    return false;
    }
    
     if( ( (document.getElementById("moduleType").value) == 'null') || ( (document.getElementById("moduleType").value) == '') )
    {
	    alert("Please enter the module Type");
	    document.getElementById("moduleType").focus();
	    return false;
    }
    
      if( ( (document.getElementById("moduleTypeId").value) == 'null') || ( (document.getElementById("moduleTypeId").value) == '') )
    {
	    alert("Please enter the Module Title");
	    document.getElementById("moduleTypeId").focus();
	    return false;
    }
  }
 function validateBudgetAllocate()
 {
  if(isNaN(document.getElementById("allocatedAmount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("allocatedAmount").focus();
	    return false;
    }
    if(eval(document.getElementById("allocatedAmount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("allocatedAmount").focus();
	    return false;
    }
    if( ( (document.getElementById("allocatedAmount").value) == 'null') || ( (document.getElementById("allocatedAmount").value) == '') || ( (document.getElementById("allocatedAmount").value) == '0')  )
    {
	    alert("Please enter an amount");
	    document.getElementById("allocatedAmount").focus();
	    return false;
    }
    
 }
 
  function validateBudgetDetails()
  {
  
       if( ( (document.getElementById("accountHead.id").value) == 'null') || ( (document.getElementById("accountHead.id").value) == '') )
    {
         alert("Please enter the Account Head ");
         document.getElementById("accountHead.id").focus();
         return false;
    }
    
     if(isNaN(document.getElementById("allocatedAmount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("allocatedAmount").focus();
	    return false;
    }
    if(eval(document.getElementById("allocatedAmount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("allocatedAmount").focus();
	    return false;
    }
    if( ( (document.getElementById("allocatedAmount").value) == 'null') || ( (document.getElementById("allocatedAmount").value) == '') || ( (document.getElementById("allocatedAmount").value) == '0')  )
    {
	    alert("Please enter an amount");
	    document.getElementById("allocatedAmount").focus();
	    return false;
    }
  }
  function returnResult(e)
  {
	  var result = eval("("+e.responseText+")"); 
	  if(result)
	  {  
		  alert ("Fund has been transferred to your project(s)."+result+".Please check.");
         
  	  }
  }
  function validateConfirm(budgetId)
  {
      var userCheck=true;
	  jQuery.ajax({
      url:'/gms/budgetMaster/confirmAssignedBudget',
      data: {id: budgetId},
      dataType: "json",
      async: false,
      success: function(data){
      var result = eval(data);
      if(result.id != null)
      {
      	userCheck=confirm('This budget is already assigned. Any changes made now will modify the assigned budget also. Do you wish to continue?');
      }
      else
      {
      }
      },
      error: function(errorThrown) {
	}
	});
	if(!userCheck)
	{
		return userCheck;
	}
	else
	{
		return userCheck;
	}
 }
  
  function validateApproved(detailId)
  {
     var userCheck=true;
	  jQuery.ajax({
      url:'/gms/proposalApprovalDetail/commentDetails',
      data: {id: detailId},
      dataType: "json",
      async: false,
      success: function(data){
      var result = eval(data);
      if(result.id != null)
      {
      	alert (""+result.remarks);
      }
      else
      {
      }
      },
      error: function(errorThrown)
      {
	  }
	  });
	  if(!userCheck)
	  {
		return userCheck;
	  }
	  else
	  {
		return userCheck;
	  }
 }
  function validateSubPrjt(prjctId) 
	{
	  var userCheck=true;
	  jQuery.ajax({
      url:'/gms/projectTracking/trackingdetails',
      data: {id: prjctId},
      dataType: "json",
      async: false,
      success: function(data){
      var result = eval(data);
      if(result != null)
      {
    	  alert("Sub-Project "+result+" is not yet withdrawal or surrendered,so this can't checked");
    	  if(document.statusDet.subPrjt.checked==true)
      	  {
    		document.statusDet.subPrjt.checked=false;
	  	  }
      }
      else
      {
      }
      },
      error: function(errorThrown)
      {
	  }
	  });
	  if(!userCheck)
	  {
		return userCheck;
	  }
	  else
	  {
		return userCheck;
	  }
	}

	function validateAdvnceDetails(prjctId) 
	{
	  var userCheck=true;
	  jQuery.ajax({
      url:'/gms/projectTracking/advanceDetails',
      data: {id: prjctId},
      dataType: "json",
      async: false,
      success: function(data){
      var result = eval(data);
      if(result != null)
      {
    	  alert("Fund Advance is not yet closed,so this can't checked");
    	  if(document.statusDet.fundAdvnce.checked==true)
      	  {
    		document.statusDet.fundAdvnce.checked=false;
	  	  }
      }
      else
      {
      }
      },
      error: function(errorThrown)
      {
	  }
	  });
	  if(!userCheck)
	  {
		return userCheck;
	  }
	  else
	  {
		return userCheck;
	  }
	}
	
	function validateExpenseReqstDetails(prjctId) 
	{
	  var userCheck=true;
	  jQuery.ajax({
      url:'/gms/projectTracking/expenseDetails',
      data: {id: prjctId},
      dataType: "json",
      async: false,
      success: function(data){
      var result = eval(data);
      if(result != null)
      {
    	  alert("Expense Requst Status is not yet Approved,so this can't checked");
    	  if(document.statusDet.expnseRequst.checked==true)
      	  {
    		document.statusDet.expnseRequst.checked=false;
	  	  }
      }
      else
      {
      }
      },
      error: function(errorThrown)
      {
	  }
	  });
	  if(!userCheck)
	  {
		return userCheck;
	  }
	  else
	  {
		return userCheck;
	  }
	}
	
	function validateUtilizationDetails(prjctId) 
	{
	  var userCheck=true;
	  jQuery.ajax({
      url:'/gms/projectTracking/utilizedDetails',
      data: {id: prjctId},
      dataType: "json",
      async: false,
      success: function(data){
      var result = eval(data);
      if(result != null)
      {
    	  alert("Utilization report is not yet submitted,so this can't checked");
    	  if(document.statusDet.utilizationDet.checked==true)
      	  {
    		document.statusDet.utilizationDet.checked=false;
	  	  }
      }
      else
      {
      }
      },
      error: function(errorThrown)
      {
	  }
	  });
	  if(!userCheck)
	  {
		return userCheck;
	  }
	  else
	  {
		return userCheck;
	  }
	}
	
  function validateMaster()
  {
       if( ( (document.getElementById("financialYear.id").value) == 'null') || ( (document.getElementById("financialYear.id").value) == '') )
    {
         alert("Please enter the Financial Period ");
         document.getElementById("financialYear.id").focus();
         return false;
    }
  
      if( ( (LTrim(document.getElementById("budgetCode").value)) == 'null') || ( (document.getElementById("budgetCode").value) == '') )
    {
         alert("Please enter the Budget Master Code ");
         document.getElementById("budgetCode").focus();
         return false;
    }
    
        if( ( (LTrim(document.getElementById("title").value)) == 'null') || ( (document.getElementById("title").value) == '') )
    {
         alert("Please enter the Budget Master Title ");
         document.getElementById("title").focus();
         return false;
    }
    
        if( ( ((document.getElementById("totalBudgetAmount").value)) == 'null') || ( (document.getElementById("totalBudgetAmount").value) == '') )
    {
         alert("Please enter the Total Budget Amount ");
         document.getElementById("totalBudgetAmount").focus();
         return false;
    }
    
       if(isNaN(document.getElementById("totalBudgetAmount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("totalBudgetAmount").focus();
	    return false;
    }
    if(eval(document.getElementById("totalBudgetAmount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("totalBudgetAmount").focus();
	    return false;
    }
  
    
   
  }

 function validateAwardProposal()
{
	if(LTrim(document.getElementById("name").value) == "")
	{
		alert("Please Enter Project Title");
	    document.getElementById("name").focus();
	    return false;
	}
	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("name").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("name").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("name").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Name.");
	  			document.getElementById("name").focus();
	  			return false;
  			}
    	}
    	}

	if(document.getElementById("code").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("code").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("code").value.charAt(i)) != -1) 
  			{
  			alert ("Special characters and numbers are not allowed in Code.");
  			document.getElementById("code").focus();
  			return false;
  			}
    	}
    }
    
    	if(LTrim(document.getElementById("code").value) == ""){
    		alert("Please Enter Code");
		    document.getElementById("code").focus();
		    return false;
    	}
    if( ( (document.getElementById("projectType").value) == 'null') || ( (document.getElementById("projectType").value) == '') )
    {
	    alert("Please enter the Project Type");
	    document.getElementById("projectType").focus();
	    return false;
    }
    	var projectStartDateYear = document.getElementById("projectStartDate_year").value;
	var projectStartDateMonth = document.getElementById("projectStartDate_month").value;
	var projectStartDateDate = document.getElementById("projectStartDate_day").value;
	
	var projectEndDateYear = document.getElementById("projectEndDate_year").value;
	var projectEndDateMonth = document.getElementById("projectEndDate_month").value;
	var projectEndDateDate = document.getElementById("projectEndDate_day").value;
    	
    	var newProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    	var newProjectEndDate = new Date(projectEndDateYear,projectEndDateMonth-1,projectEndDateDate);
    	    	
    	if((document.getElementById("parentProjectStartDate").value != "") || (document.getElementById("parentProjectEndDate").value != ""))
    	{
		//Parent Project Start Date
		
    	var parentStartDate = document.getElementById("parentProjectStartDate").value;  		
    	var parentStartDateYear=parentStartDate.substring(0,parentStartDate.indexOf("-"));
    	var parentStartDateMonth=parentStartDate.substring(parentStartDate.indexOf("-")+1,parentStartDate.lastIndexOf("-"));
    	var parentStartDateDate=parentStartDate.substring(parentStartDate.lastIndexOf("-")+1,parentStartDate.lastIndexOf(" "));
    		
    	//Parent Project End Date
    		
    	var parentEndDate = document.getElementById("parentProjectEndDate").value;
       		var parentEndDateYear=parentEndDate.substring(0,parentEndDate.indexOf("-"));
    		var parentEndDateMonth=parentEndDate.substring(parentEndDate.indexOf("-")+1,parentEndDate.lastIndexOf("-"));
    		var parentEndDateDate=parentEndDate.substring(parentEndDate.lastIndexOf("-")+1,parentEndDate.lastIndexOf(" "));
    		
    		var newParentProjectStartDate = new Date(parentStartDateYear,parentStartDateMonth-1,parentStartDateDate);
    		var newParentProjectEndDate = new Date(parentEndDateYear,parentEndDateMonth-1,parentEndDateDate);
    		
    		
    		
    		if ((newProjectStartDate<newParentProjectStartDate)||(newProjectEndDate>newParentProjectEndDate))
	 	{
		alert("Sub project Date period should be between the Main project date");
		return false;
		}
    
    	}
    	    	if (newProjectStartDate>=newProjectEndDate)
    	{
    		alert("Project End Date should be greater than Start Date");
    		document.getElementById("projectEndDate").focus();
    		return false;
    	}
	
    	
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
    if((document.getElementById("amountAllocated").value)== 0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
       if( (parseFloat(document.getElementById("amountAllocated").value)) > (parseFloat(document.getElementById("balanceAmount").value)) )
       {
		    alert("Enter an amount less than  BalanceAmount ");
		    document.getElementById("amountAllocated").focus();
		    return false;
       }
   
   if(document.getElementById("notificationAmount").value)
    {
	    if( (parseFloat(document.getElementById("notificationAmount").value)) < (parseFloat(document.getElementById("amountAllocated").value)) )
	      {
		    alert("Enter an amount less than the amount allocated for main project ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	      }
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("amountAllocated").focus();
	    return false;
    }
  
    if(document.getElementById("sanctionOrderNo").value == ""){
    		alert("Please Enter sanctionOrderNo");
		    document.getElementById("sanctionOrderNo").focus();
		    return false;
    	}
    	
      
   
    return true;
}

function validateFundAdvance()
{
       if(LTrim(document.getElementById("advanceAmount").value) == '') 
	    {
	         alert("Please enter the Advance Amount ");
	         document.getElementById("advanceAmount").focus();
	         return false;
	    }
	   if(isNaN(document.getElementById("advanceAmount").value))
	    {
		    alert("Invalid Amount  ");
		    document.getElementById("advanceAmount").focus();
		    return false;
	    }
	    if((document.getElementById("advanceAmount").value)== 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("advanceAmount").focus();
		    return false;
	    }
	   if((document.getElementById("advanceAmount").value)<= 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("advanceAmount").focus();
		    return false;
	    }
	    
       if( ( (document.getElementById("grantAllocation.id").value) == 'null') || ( (document.getElementById("grantAllocation.id").value) == '') )
	    {
	         alert("Please enter the Grant Allocation ");
	         document.getElementById("grantAllocation.id").focus();
	         return false;
	    }
	    
	    if( ( (LTrim(document.getElementById("advanceDescription").value)) == 'null') || ( (document.getElementById("advanceDescription").value) == '') )
	    {
	         alert("Please enter the Advance Description ");
	         document.getElementById("advanceDescription").focus();
	         return false;
	    }
	    
	
	    if(((document.getElementById("modeOfPayment").value) == 'null') || ( (document.getElementById("modeOfPayment").value) == '' ) )
	    {
		    alert("Please select Mode Of Payment");
		    document.getElementById("modeOfPayment").focus();
		    return false;
	    }
	    
	      var str = document.getElementById('ddNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the DD/Cheque No: Field.");
            document.getElementById('ddNo').focus();
            return false;
         }
      } 
 	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("bankName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("bankName").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("bankName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters are not allowed in Bank Name");
	  			document.getElementById("bankName").focus();
	  			return false;
  			}
    	}
 
	  }
	  var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("ddBranch").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("ddBranch").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("ddBranch").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters are not allowed in Branch Name");
	  			document.getElementById("ddBranch").focus();
	  			return false;
  			}
    	}
 
	  }
	    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
	    {
		    if(eval(document.getElementById("ddNo").value)<=0)
		    {
			    alert("Please enter Proper DD/Cheque Number  ");
			    document.getElementById("ddNo").focus();
			    return false;
		    }
		     if((document.getElementById("ddNo").value)=='')
		    {
			    alert("Please enter DD/Cheque No");
			    document.getElementById("ddNo").focus();
			    return false;
		    }
	    }
	  
 	
    return true;
}

function validateExpenseDetails()
{

	 if(((document.getElementById("grantAllocationSplit.id").value) == 'null') || ( (document.getElementById("grantAllocationSplit.id").value) == '' ) )
		 {
			 alert("Please select Account Head");
			 document.getElementById("grantAllocationSplit.id").focus();
			 return false;
		 }
	  if((document.getElementById("expenseAmount").value)==0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("expenseAmount").focus();
		    return false;
	    }
	        if( ( (LTrim(document.getElementById("description").value)) == 'null') || ( (document.getElementById("description").value) == '') )
	    {
	         alert("Please enter the Description ");
	         document.getElementById("description").focus();
	         return false;
	    }
    
     var str = document.getElementById('ddNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the DD/Cheque No: Field.");
            document.getElementById('ddNo').focus();
            return false;
         }
      } 
 	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("bankName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("bankName").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("bankName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters are not allowed in Bank Name");
	  			document.getElementById("bankName").focus();
	  			return false;
  			}
    	}
 
	  }
	  var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	
	if(document.getElementById("ddBranch").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("ddBranch").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("ddBranch").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters are not allowed in Branch Name");
	  			document.getElementById("ddBranch").focus();
	  			return false;
  			}
    	}
 
	  }
    if(isNaN(document.getElementById("expenseAmount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("expenseAmount").focus();
	    return false;
    }
    if(eval(document.getElementById("expenseAmount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    document.getElementById("expenseAmount").focus();
	    return false;
    }
      if(((document.getElementById("modeOfPayment").value) == 'null') || ( (document.getElementById("modeOfPayment").value) == '' ) )
    {
	    alert("Please select Mode Of Payment");
	    document.getElementById("modeOfPayment").focus();
	    return false;
    }
 	
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if(eval(document.getElementById("ddNo").value)<=0)
	    {
		    alert("Please enter Proper DD/Cheque Number  ");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
	     if((document.getElementById("ddNo").value)=='')
	    {
		    alert("Please enter DD/Cheque No");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
    }
    
  
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if((LTrim(document.getElementById("bankName").value))=='')
	    {
		    alert("Please enter Bank Name  ");
		    document.getElementById("bankName").focus();
		    return false;
	    }
	    
	    if((LTrim(document.getElementById("ddBranch").value))=='')
	    {
		    alert("Please enter Bank Branch  ");
		    document.getElementById("ddBranch").focus();
		    return false;
	    }
   }
}

function validateGrantFund()
{
   if( ( (document.getElementById("recipient").value) == 'null') || ( (document.getElementById("recipient").value) == '') )
    {
         alert("Please enter the Recipient ");
         document.getElementById("recipient").focus();
         return false;
    }
  
    if( ( (document.getElementById("projects").value) == 'null') || ( (document.getElementById("projects").value) == '') )
    {
         alert("Please enter the Projects ");
         document.getElementById("projects").focus();
         return false;
    }
    
    if( ( (document.getElementById("sanctionOrderNo").value) == 'null') || ( (document.getElementById("sanctionOrderNo").value) == '') )
    {
         alert("Please enter the Sanction Order No ");
         document.getElementById("sanctionOrderNo").focus();
         return false;
    }
    
     var str = document.getElementById('sanctionOrderNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the Sanction Order No: Field.");
            document.getElementById('sanctionOrderNo').focus();
            return false;
         }
      } 
      
    if( ( (document.getElementById("refundable").value) == 'null') || ( (document.getElementById("refundable").value) == '') )
    {
         alert("Please enter the Type of Grant ");
         document.getElementById("refundable").focus();
         return false;
    }
    
        if(document.getElementById("amountAllocated").value == '') 
	    {
	         alert("Please enter the  Amount Allocated");
	         document.getElementById("amountAllocated").focus();
	         return false;
	    }
	   if(isNaN(document.getElementById("amountAllocated").value))
	    {
		    alert("Invalid Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }
	    if((document.getElementById("amountAllocated").value)== 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }
	   if((document.getElementById("amountAllocated").value)<= 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }
    
}

function validateExternalFund()
{
   if( ( (document.getElementById("recipient").value) == 'null') || ( (document.getElementById("recipient").value) == '') )
    {
         alert("Please enter the External Agency ");
         document.getElementById("recipient").focus();
         return false;
    }
  
   
    if( ( (document.getElementById("sanctionOrderNo").value) == 'null') || ( (document.getElementById("sanctionOrderNo").value) == '') )
    {
         alert("Please enter the Sanction Order No ");
         document.getElementById("sanctionOrderNo").focus();
         return false;
    }
    
     var str = document.getElementById('sanctionOrderNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the Sanction Order No: Field.");
            document.getElementById('sanctionOrderNo').focus();
            return false;
         }
      } 
      
    if( ( (document.getElementById("refundable").value) == 'null') || ( (document.getElementById("refundable").value) == '') )
    {
         alert("Please enter the Type of Grant ");
         document.getElementById("refundable").focus();
         return false;
    }
    
        if(document.getElementById("amountAllocated").value == '') 
	    {
	         alert("Please enter the  Amount Allocated");
	         document.getElementById("amountAllocated").focus();
	         return false;
	    }
	   if(isNaN(document.getElementById("amountAllocated").value))
	    {
		    alert("Invalid Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }
	    if((document.getElementById("amountAllocated").value)== 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }
	   if((document.getElementById("amountAllocated").value)<= 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }
    
}





function validateEditGrantFund()
{
    if( ( (document.getElementById("sanctionOrderNo").value) == 'null') || ( (document.getElementById("sanctionOrderNo").value) == '') )
    {
         alert("Please enter the Sanction Order No ");
         document.getElementById("sanctionOrderNo").focus();
         return false;
    }
    
     var str = document.getElementById('sanctionOrderNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the Sanction Order No: Field.");
            document.getElementById('sanctionOrderNo').focus();
            return false;
         }
      } 
      
    if( ( (document.getElementById("refundable").value) == 'null') || ( (document.getElementById("refundable").value) == '') )
    {
         alert("Please enter the Type of Grant ");
         document.getElementById("refundable").focus();
         return false;
    }
    
        if(document.getElementById("amountAllocated").value == '') 
	    {
	         alert("Please enter the  Amount Allocated");
	         document.getElementById("amountAllocated").focus();
	         return false;
	    }
	   if(isNaN(document.getElementById("amountAllocated").value))
	    {
		    alert("Invalid Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }
	    if((document.getElementById("amountAllocated").value)== 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }
	   if((document.getElementById("amountAllocated").value)<= 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("amountAllocated").focus();
		    return false;
	    }


}

function validateTransferFund()
{
        if(LTrim(document.getElementById("amount").value) == '') 
	    {
	         alert("Please enter the  Amount Transferred");
	         document.getElementById("amount").focus();
	         return false;
	    }
	   if(isNaN(document.getElementById("amount").value))
	    {
		    alert("Invalid Amount  ");
		    document.getElementById("amount").focus();
		    return false;
	    }
	    if((document.getElementById("amount").value)== 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("amount").focus();
		    return false;
	    }
	   if((document.getElementById("amount").value)<= 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("amount").focus();
		    return false;
	    }
}

function validateReceiveExternlFund()
{

 var str = document.getElementById('ddNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the DD/Cheque No: Field.");
            document.getElementById('ddNo').focus();
            return false;
         }
      } 
	
    if((LTrim(document.getElementById("referenceId").value))=='')
    {
	    alert("Please enter Funds Received Order No.  ");
	    document.getElementById("referenceId").focus();
	    return false;
    }	
    
    if( ( (document.getElementById("grantAllocation.id").value) == 'null') || ( (document.getElementById("grantAllocation.id").value) == '') )
    {
         alert("Please enter Grant Allocation ");
         document.getElementById("grantAllocation.id").focus();
         return false;
    }
  
    if( ( (document.getElementById("fundTransfer.id").value) == 'null') || ( (document.getElementById("fundTransfer.id").value) == '') )
    {
         alert("Please enter Fund Transferred ");
         document.getElementById("fundTransfer.id").focus();
         return false;
    }
    
    if(((document.getElementById("modeOfPayment").value) == 'null') || ( (document.getElementById("modeOfPayment").value) == '' ) )
    {
	    alert("Please select Mode Of Payment");
	    document.getElementById("modeOfPayment").focus();
	    return false;
    }
    
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if(eval(document.getElementById("ddNo").value)<=0)
	    {
		    alert("Please enter Proper DD/Cheque Number  ");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
	     if((document.getElementById("ddNo").value)=='')
	    {
		    alert("Please enter DD/Cheque No");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
    }
  
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if((document.getElementById("ddNo").value)=='')
	    {
		    alert("Please enter DD/Cheque No");
		    document.getElementById("ddNo").focus();
		    return false;
	    }	
    }
   if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if((document.getElementById("bankName").value)=='')
	    {
		    alert("Please enter Bank Name  ");
		    document.getElementById("bankName").focus();
		    return false;
	    }
	    
	    if((document.getElementById("ddBranch").value)=='')
	    {
		    alert("Please enter Bank Branch  ");
		    document.getElementById("ddBranch").focus();
		    return false;
	    }
   }
   
       var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("bankName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("bankName").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("bankName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Bank Name .");
	  			document.getElementById("bankName").focus();
	  			return false;
  			}
    	}
    }
  	
	var speclChars = "!@#$%^&*()+=-[]\\\/{}|\":<>?";
	if(document.getElementById("ddBranch").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("ddBranch").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("ddBranch").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Branch name.");
	  			document.getElementById("ddBranch").focus();
	  			return false;
  			}
    	}
    }

}

function validateEditReceiveExternlFund()
{

    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if(eval(document.getElementById("ddNo").value)<=0)
	    {
		    alert("Please enter Proper DD/Cheque Number  ");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
	     if((document.getElementById("ddNo").value)=='')
	    {
		    alert("Please enter DD/Cheque No");
		    document.getElementById("ddNo").focus();
		    return false;
	    }
    }
  
    if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if((document.getElementById("ddNo").value)=='')
	    {
		    alert("Please enter DD/Cheque No");
		    document.getElementById("ddNo").focus();
		    return false;
	    }	
    }
   if(((document.getElementById("modeOfPayment").value) != 'Bank Transfer') && ( (document.getElementById("modeOfPayment").value) != 'Cash' ) )
    {
	    if((document.getElementById("bankName").value)=='')
	    {
		    alert("Please enter Bank Name  ");
		    document.getElementById("bankName").focus();
		    return false;
	    }
	    
	    if((document.getElementById("ddBranch").value)=='')
	    {
		    alert("Please enter Bank Branch  ");
		    document.getElementById("ddBranch").focus();
		    return false;
	    }
   }
   
       var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	
	if(document.getElementById("bankName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("bankName").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("bankName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Bank Name .");
	  			document.getElementById("bankName").focus();
	  			return false;
  			}
    	}
    }
  	
	var speclChars = "!@#$%^&*()+=-[]\\\/{}|\":<>?";
	if(document.getElementById("ddBranch").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("ddBranch").value.length); i++) 
    	{
   	 		if (speclChars.indexOf(document.getElementById("ddBranch").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Branch name.");
	  			document.getElementById("ddBranch").focus();
	  			return false;
  			}
    	}
    }

}

function validateGrantFundReFund()
{

		if( ( (document.getElementById("refundAmount").value) == 'null') || ( (document.getElementById("refundAmount").value) == '') )
   		 {
         alert("Please enter the Refunding Amount ");
         document.getElementById("refundAmount").focus();
         return false;
    	 }
    	 
        if(document.getElementById("refundAmount").value == '') 
	    {
	         alert("Please enter the  Refunding Amount ");
	         document.getElementById("refundAmount").focus();
	         return false;
	    }
	   if(isNaN(document.getElementById("refundAmount").value))
	    {
		    alert("Invalid Amount  ");
		    document.getElementById("refundAmount").focus();
		    return false;
	    }
	    if((document.getElementById("refundAmount").value)== 0)
	    {
		    alert("Please enter Refunding Amount  ");
		    document.getElementById("refundAmount").focus();
		    return false;
	    }
	   if((document.getElementById("refundAmount").value)<= 0)
	    {
		    alert("Please enter Proper Amount  ");
		    document.getElementById("refundAmount").focus();
		    return false;
	    }
	    
	    if( ( (document.getElementById("refundingStatus").value) == 'null') || ( (document.getElementById("refundingStatus").value) == '') )
   		 {
         alert("Please enter the Refunding Status ");
         document.getElementById("refundingStatus").focus();
         return false;
    	 }
    

}


function validateUniversityMaster()
{
	
	if( ( (LTrim(document.getElementById("nameOfUniversity").value)) == 'null') || ( (document.getElementById("nameOfUniversity").value) == '') )
	{
		alert("Please enter the Name Of University ");
		document.getElementById("nameOfUniversity").focus();
		return false;
	}
	
	if( ( (LTrim(document.getElementById("code").value)) == 'null') || ( (document.getElementById("code").value) == '') )
	{
		alert("Please enter the Code ");
		document.getElementById("code").focus();
		return false;

	}
	
	if( ( (LTrim(document.getElementById("email").value)) == 'null') || ( (document.getElementById("email").value) == '') )
	{
		alert("Please enter the Email ");
		document.getElementById("email").focus();
		return false;
	}
	var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus();
			return false;
		}
	}

	var str = document.getElementById('phoneNo').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the Phone No: Field.");
            document.getElementById('phoneNo').focus();
            return false;
         }
      }    
    
}

function validateUniversityInstitutionMap()
{
	
	if( ( (LTrim(document.getElementById("university.id").value)) == 'null') || ( (document.getElementById("university.id").value) == '') )
	{
		alert("Please enter the University ");
		document.getElementById("university.id").focus();
		return false;
	}
	
	if( ( (LTrim(document.getElementById("party.id").value)) == 'null') || ( (document.getElementById("party.id").value) == '') )
	{
		alert("Please enter the Institution ");
		document.getElementById("party.id").focus();
		return false;
	}
}
function superAdminUservalidation()
{

var speclChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";
  
    if(document.getElementById("email").value == ""){
		alert("Please enter email");
	    document.getElementById("email").focus();
	    return false;
	}    
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus();
			return false;
		}
	}
	
	if(document.getElementById("password").value == ""){
		alert("Please enter Password");
	    document.getElementById("password").focus();
	    return false;
	}
  	if(document.getElementById("confirmPasswd").value == ""){
		alert("Please enter confirm Password");
	    document.getElementById("confirmPasswd").focus();
	    return false;
	}
	if(document.getElementById("confirmPasswd").value != document.getElementById("password").value)
	{
		document.getElementById("password").focus();
		alert("Incorrect Password");
		return false;
	}
	var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("password").value) )
          {
             document.getElementById("password").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             return false;
          }
          
	if(LTrim(document.getElementById("userRealName").value) == ""){
		alert("Please Enter First Name");
	    document.getElementById("userRealName").focus();
	    return false;
	}
	
	if(document.getElementById("userRealName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userRealName").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("userRealName").value.length ; 
    	// alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("userRealName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in FirstName.");
	  			document.getElementById("userRealName").focus(); 
	  			return false;
  			}
    	}
 
	  }
	

	if(LTrim(document.getElementById("userSurName").value) == ""){
		alert("Please Enter last Name");
	    document.getElementById("userSurName").focus();
	    return false;
	}
	
	 if(document.getElementById("userSurName").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("userSurName").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("userSurName").value.length ; 
    	  // alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("userSurName").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in LastName. .");
	  			document.getElementById("userSurName").focus(); 
	  			return false;
  			}
    	}
 
	  }
	
	
	var str = document.getElementById('phNumber').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the Phone No: Field.");
            document.getElementById('phNumber').focus();
            return false;
         }
      }   
      
	if(LTrim(document.getElementById("nameOfTheInstitution").value) == ""){
		alert("Please enter an Institution name");
	    document.getElementById("nameOfTheInstitution").focus();
	    return false;
	}
	
	 if(document.getElementById("nameOfTheInstitution").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("nameOfTheInstitution").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("nameOfTheInstitution").value.length ; 
    	  // alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("nameOfTheInstitution").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Name. .");
	  			document.getElementById("nameOfTheInstitution").focus(); 
	  			return false;
  			}
    	}
 
	  }


 if(LTrim(document.getElementById("code").value) == ""){
		alert("Please enter Institution code");
	    document.getElementById("code").focus();
	    return false;
	}
	
	 if(document.getElementById("code").value != "")
	 {
    	
    	for (var i = 0; i < (document.getElementById("code").value.length); i++) 
    	{
    	 
          //  var temp = document.getElementById("code").value.length ; 
    	  // alert(temp);
  	 		if (speclChars.indexOf(document.getElementById("code").value.charAt(i)) != -1) 
  			{
	  			alert ("Special characters and numbers are not allowed in Code. .");
	  			document.getElementById("code").focus(); 
	  			return false;
  			}
    	}
 
	  }
	
	 
	var str = document.getElementById('phone').value;
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();

    for (var i = 0; i < str.length; i++)
     {

        oneChar = str.charAt(i).charCodeAt(0);
       // characters outside of 0 through 9 not OK

        if (oneChar < 48 || oneChar > 57)
         {

            alert("Enter only valid numbers into the Phone No: Field.");
            document.getElementById('phone').focus();
            return false;
         }
      }   
	


 if(LTrim(document.getElementById("institutionemail").value) == ""){
		alert("Please enter institutionemail");
	    document.getElementById("institutionemail").focus();
	    return false;
	}    
	var institutionemail = document.getElementById('institutionemail');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(institutionemail.value))
		{
			alert('Please provide a valid email address');
			institutionemail.focus();
			return false;
		}
	}

	
	return true;
 
}
function validateCoPi(instance,msg)
{ 
	
	if(!validateCheckBox(instance,msg))
	{
		return false;
	}
	docclick++;
		if(docclick>1)
		{
			return false;
		}
			setTimeout('docclick=0', 2000);
	
   return true;
}


function validateMenuRoleMap(role,instance,msg)
{ 
	if(role.value == 'null')
	{
		alert("Please Select a Role");
		role.focus();
		return false;
	}
	if(!validateCheckBox(instance,msg))
	{
		return false;
	}
	docclick++;
		if(docclick>1)
		{
			return false;
		}
			setTimeout('docclick=0', 2000);
	
   return true;
}

function validateCheckList(projects,instance,msg)
{ 
	
	if(projects.value == 'null')
	{
		alert("Please Select a Projects");
		projects.focus();
		return false;
	}
	
	if(!validateCheckBox(instance,msg))
	{
		return false;
	}
	docclick++;
		if(docclick>1)
		{
			return false;
		}
			setTimeout('docclick=0', 2000);
	
   return true;
}

function combineAlertAndValidateAuthority() 
{
	var retvalue;
	retvalue = validateAuthority();
	if(retvalue == true) {
	return displayAlertMessage("Please wait..."); }
	return retvalue
}

function msgForreviewer()
{
	alert("Review can be started only after proposal submission last date.");
	return false;
}

function validateGrantAllocationTracking()
{
	if( ( (LTrim(document.getElementById("grantAllocationStatus").value)) == 'null') || ( (document.getElementById("grantAllocationStatus").value) == '') )
		  
	{
		alert("Please Enter Grant Allocation Status");
	    document.getElementById("grantAllocationStatus").focus();
	    return false;
	}
}

function breakOutofFrame() 
{
	if (top.frames['top'].length!=0) 
	{
    	if (window.location.href.replace)
        	top.frames['top'].location.replace(self.location.href);
        else
       		top.frames['top'].location.href=self.document.href;
	}
}

function validateAssetTracking()
{
	if( ( (LTrim(document.getElementById("assetName").value)) == 'null') || ( (document.getElementById("assetName").value) == '') )
		  
	{
		alert("Please Enter Asset Name");
	    document.getElementById("assetName").focus();
	    return false;
	}
	
	if( ( (LTrim(document.getElementById("assetCode").value)) == 'null') || ( (document.getElementById("assetCode").value) == '') )
		  
	{
		alert("Please Enter Asset Code");
	    document.getElementById("assetCode").focus();
	    return false;
	}
	
	if( ( (LTrim(document.getElementById("cost").value)) == 'null') || ( (document.getElementById("cost").value) == '') )
		  
	{
		alert("Please Enter Cost");
	    document.getElementById("cost").focus();
	    return false;
	}

	if(isNaN(document.getElementById("cost").value))
    {
	    alert("Invalid Cost  ");
	    document.getElementById("cost").focus();
	    return false;
    }
    if((document.getElementById("cost").value)==0)
    {
	    alert("Please enter the Cost  ");
	    document.getElementById("cost").focus();
	    return false;
    }
    
    if(eval(document.getElementById("cost").value)<=0)
    {
	    alert("Please enter the Cost   ");
	    document.getElementById("cost").focus();
	    return false;
    } 
    
}

function validateAssetDepreciation()
{
	if(LTrim(document.getElementById("depreciateValue").value) == 'null'){
		alert("Please enter Depreciate Value");
	    document.getElementById("depreciateValue").focus();
	    return false;
	}
	
	else if(isNaN(document.getElementById("depreciateValue").value) )
	{
		alert("Please Enter Numeric Value For Depreciate Value");
	    document.getElementById("depreciateValue").focus();
	    return false;
	}
	if(eval(document.getElementById("depreciateValue").value)<=0)
	{
	    alert("Please enter a valid Depreciate Value");
	   	document.getElementById("depreciateValue").focus(); 
	    return false;
	}
	if(eval(document.getElementById("depreciateValue").value)>100)
	{
	    alert("Please enter a valid Depreciate Value");
	   	document.getElementById("depreciateValue").focus(); 
	    return false;
	}
	return true;
}
function validateCheckLists()
{

	if( ( (LTrim(document.getElementById("field").value)) == 'null') || ( (document.getElementById("field").value) == '') )
		  
	{
		alert("Please Enter Field");
	    document.getElementById("field").focus();
	    return false;
	}
}


function validateSalaryReportViewConfirmPrint()
{
    var answer = confirm("Do you want to view report?")
	if (answer)
	{
		document.viewStatementOfAccounts.target="_blank";
		return true;
	}
	else
	{
		return false;
	} 
	document.viewStatementOfAccounts.target="_blank";
  return true;
}

function validateSendMessage()
{
	  if( ( (document.getElementById("fromEmail").value) == 'null') || ( (document.getElementById("fromEmail").value) == '') )
	    {
		    alert("Please enter the From address");
		    document.getElementById("fromEmail").focus();
		    return false;
	    }

	  var email = document.getElementById('fromEmail');
		var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
		if(email.value != "")
		{
			if (!filter.test(email.value))
			{
				alert('Please provide a valid email address in From address');
				email.focus();
				return false;
			}
		}
		
		  var toEmail = document.getElementById('email');
			var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
			if(toEmail.value != "")
			{
				if (!filter.test(toEmail.value))
				{
					alert('Invalid email address in To address');
					email.focus();
					return false;
				}
			}
		
		

}