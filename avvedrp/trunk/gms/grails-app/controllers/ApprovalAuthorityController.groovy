import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.apache.commons.validator.EmailValidator

class ApprovalAuthorityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
    	GrailsHttpSession gh=getSession()
    	def partyInstance = Party.get(gh.getValue("Party"))
    	println"partyInstance"+partyInstance
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        //def approvalAuthorityList = ApprovalAuthority.findAll("from ApprovalAuthority A where A.activeYesNo = 'Y'")
        [approvalAuthorityInstanceList: ApprovalAuthority.list(params), approvalAuthorityInstanceTotal: ApprovalAuthority.count(),'approvalAuthorityList':approvalAuthorityList]
    }

    def create = {
    	
    	println"params***************"+params.id
        def approvalAuthorityInstance = new ApprovalAuthority(params)
    	approvalAuthorityInstance.save()
    	def approvalAuthorityService = new ApprovalAuthorityService()
       // approvalAuthorityInstance.properties = params
        
        GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Create_approval_Authority.htm")//putting help pages in session
    	def partyInstance = Party.get(gh.getValue("Party"))
    	println"partyInstance"+partyInstance
    	 def approvalAuthorityList = approvalAuthorityService.getActiveApprovalAuthority(gh.getValue("PartyID"))
    		 
        return [approvalAuthorityInstance: approvalAuthorityInstance,'partyInstance':partyInstance,'approvalAuthorityList':approvalAuthorityList]

    }

    def save = 
    {
    	 def approvalAuthorityInstance = new ApprovalAuthority(params)
    	 def approvalAuthorityService = new ApprovalAuthorityService()
    	 GrailsHttpSession gh=getSession()
    	 EmailValidator emailValidator = EmailValidator.getInstance()
    	if(approvalAuthorityInstance.email != null)
    	{
		if (emailValidator.isValid(params.email))
		{
    	
       
    	println"params"+params
    	def chkDefaultApprovalAuthority = approvalAuthorityService.chkDefaultAuthority(gh.getValue("PartyID"))
    	println"chkDefaultApprovalAuthority"+chkDefaultApprovalAuthority
    	if(chkDefaultApprovalAuthority && params.defaultYesNo == 'Y')
    	{
    		flash.error ="${message(code: 'default.defaultApprovalAuthority.label')}"
	    		redirect(action: "create", id: approvalAuthorityInstance.id)
    	}
    	else
    	{
    	def chkApprovalAuthorityInstance = approvalAuthorityService.checkDuplicateApprovalAuthority(params)
		if(chkApprovalAuthorityInstance)
	    {
	    	flash.error ="${message(code: 'default.AlreadyExists.label')}"
	    		redirect(action: "create", id: approvalAuthorityInstance.id)
	    }
		else
	   {
    	approvalAuthorityInstance = approvalAuthorityService.saveApprovalAuthority(params)
    	
    	  if (approvalAuthorityInstance) 
        {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action: "create", id: approvalAuthorityInstance.id)
        }
        else
        {
            render(view: "create", id: approvalAuthorityInstance.id)
        }
	   }
    }
		}
		else
		{
			
			def partyInstance = Party.get(gh.getValue("Party"))
			def approvalAuthorityList = approvalAuthorityService.getActiveApprovalAuthority(gh.getValue("PartyID"))
			flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
				render(view: "create", model:['approvalAuthorityInstance':approvalAuthorityInstance,'partyInstance':partyInstance,'approvalAuthorityList':approvalAuthorityList])
		}
    	}
    	else
 	   {
    		def chkDefaultApprovalAuthority = approvalAuthorityService.chkDefaultAuthority(gh.getValue("PartyID"))
        	println"chkDefaultApprovalAuthority"+chkDefaultApprovalAuthority
        	if(chkDefaultApprovalAuthority && params.defaultYesNo == 'Y')
        	{
        		flash.error ="${message(code: 'default.defaultApprovalAuthority.label')}"
    	    		redirect(action: "create", id: approvalAuthorityInstance.id)
        	}
        	else
        	{
        	def chkApprovalAuthorityInstance = approvalAuthorityService.checkDuplicateApprovalAuthority(params)
    		if(chkApprovalAuthorityInstance)
    	    {
    	    	flash.error ="${message(code: 'default.AlreadyExists.label')}"
    	    		redirect(action: "create", id: approvalAuthorityInstance.id)
    	    }
    		else
    		{
     	approvalAuthorityInstance = approvalAuthorityService.saveApprovalAuthority(params)
     	
     	  if (approvalAuthorityInstance) 
         {
             flash.message = "${message(code: 'default.created.label')}"
             redirect(action: "create", id: approvalAuthorityInstance.id)
         }
         else
         {
             render(view: "create", id: approvalAuthorityInstance.id)
         }
 	   }
    }
 	   }
 	   }

    def show = {
    	def approvalAuthorityService = new ApprovalAuthorityService()
    	def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityById(new Integer(params.id ))
       
        if (!approvalAuthorityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthority.label', default: 'ApprovalAuthority'), params.id])}"
            redirect(action: "list")
        }
        else {
            [approvalAuthorityInstance: approvalAuthorityInstance]
        }
    }

    def edit = {
    	def approvalAuthorityService = new ApprovalAuthorityService()
    	def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityById(new Integer( params.id ))
       
        if (!approvalAuthorityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthority.label', default: 'ApprovalAuthority'), params.id])}"
            redirect(action: "create")
        }
        else {
            return [approvalAuthorityInstance: approvalAuthorityInstance]
        }
    }

    def update = 
    {
    	def approvalAuthorityService = new ApprovalAuthorityService()
    	def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityById(new Integer( params.id ))
   	   GrailsHttpSession gh=getSession()
    	EmailValidator emailValidator = EmailValidator.getInstance()
    	println"params"+params
    	println"params.email"+params.email
    	if(params.email)
    	{
		if (emailValidator.isValid(params.email))
		{
      if (approvalAuthorityInstance) 
        {
            if (params.version) 
            {
                def version = params.version.toLong()
                if (approvalAuthorityInstance.version > version) 
                {
                    
                    approvalAuthorityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'approvalAuthority.label', default: 'ApprovalAuthority')] as Object[], "Another user has updated this ApprovalAuthority while you were editing")
                    render(view: "edit", model: [approvalAuthorityInstance: approvalAuthorityInstance])
                    return
                }
            }
            
            def chkDefaultApprovalAuthority = approvalAuthorityService.chkDefaultAuthority(gh.getValue("PartyID"))
        	println"chkDefaultApprovalAuthority"+chkDefaultApprovalAuthority
        	if(chkDefaultApprovalAuthority && chkDefaultApprovalAuthority[0].id != Long.parseLong(params.id) 
        			&& params.defaultYesNo == 'Y')
        	{
        		flash.error ="${message(code: 'default.defaultApprovalAuthority.label')}"
    	    		redirect(action: "edit", id: approvalAuthorityInstance.id)
        	}
        	else
        	{
            def chkApprovalAuthorityInstance = approvalAuthorityService.checkDuplicateApprovalAuthority(params)
            println"chkApprovalAuthorityInstance"+chkApprovalAuthorityInstance
    		println"params"+params
    		if(chkApprovalAuthorityInstance && (chkApprovalAuthorityInstance[0].id!= Long.parseLong(params.id)))
    	    {
    	    println"sucess"
    	    	flash.error ="${message(code: 'default.AlreadyExists.label')}"
    	    		redirect(action: "edit", id: approvalAuthorityInstance.id)
    	    }
    	    else
    	    {
                
             approvalAuthorityInstance = approvalAuthorityService.updateApprovalAuthority(params,approvalAuthorityInstance)
            if( approvalAuthorityInstance.saveMode.equals( "Updated"))
            {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: approvalAuthorityInstance.id)
            }
            else 
            {
                render(view: "edit", model: [approvalAuthorityInstance: approvalAuthorityInstance])
            }
    	    }
        	}
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthority.label', default: 'ApprovalAuthority'), params.id])}"
            redirect(action: "list")
        }
    }
		else
		{
			
			def partyInstance = Party.get(gh.getValue("Party"))
			
			flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
				render(view: "edit", model:['approvalAuthorityInstance':approvalAuthorityInstance,'partyInstance':partyInstance])
		}
    	}
    	else
	    {
    		def chkDefaultApprovalAuthority = approvalAuthorityService.chkDefaultAuthority(gh.getValue("PartyID"))
        	println"chkDefaultApprovalAuthority"+chkDefaultApprovalAuthority
        	if(chkDefaultApprovalAuthority && chkDefaultApprovalAuthority[0].id != Long.parseLong(params.id)
        			&& params.defaultYesNo == 'Y'  )
        	{
        		flash.error ="${message(code: 'default.defaultApprovalAuthority.label')}"
    	    		redirect(action: "edit", id: approvalAuthorityInstance.id)
        	}
        	else
        	{
            def chkApprovalAuthorityInstance = approvalAuthorityService.checkDuplicateApprovalAuthority(params)
            println"chkApprovalAuthorityInstance"+chkApprovalAuthorityInstance
    		println"params"+params
    		if(chkApprovalAuthorityInstance && (chkApprovalAuthorityInstance[0].id!= Long.parseLong(params.id)))
    	    {
    	    println"sucess"
    	    	flash.error ="${message(code: 'default.AlreadyExists.label')}"
    	    		redirect(action: "edit", id: approvalAuthorityInstance.id)
    	    }
    	    else
    	    {   
         approvalAuthorityInstance = approvalAuthorityService.updateApprovalAuthority(params,approvalAuthorityInstance)
        if( approvalAuthorityInstance.saveMode.equals( "Updated"))
        {
            flash.message = "${message(code: 'default.updated.label')}"
            redirect(action: "create", id: approvalAuthorityInstance.id)
        }
        else 
        {
            render(view: "edit", model: [approvalAuthorityInstance: approvalAuthorityInstance])
        }
	    }
        	}
    }
    }
    
    def delete = 
    {
    	
    	def approvalAuthorityService = new ApprovalAuthorityService()
    	def approvalAuthorityInstance = new ApprovalAuthority()
    	approvalAuthorityInstance.properties =params
    	
    		
            Integer approvalAuthorityId = null
            
            /* Delete the account head details */
    		approvalAuthorityId = approvalAuthorityService.deleteApprovalAuthority(params)
			if (approvalAuthorityId == 0)
			{
				/* Shows the following message if the account head is already in use. */
				 flash.message = "${message(code: 'default.approvalauthorityusedinapprovalauthoritydeatil.label')}"
				 redirect(action:edit,id:params.id)
			}
			else
			{	
				if(approvalAuthorityId != null)
				{				
					flash.message = "${message(code: 'default.deleted.label')}"			
							    		
						   redirect(action:create,id:approvalAuthorityInstance.id)
						
				}    
				else 
				{
		            flash.message = "${message(code: 'default.notbedeletedApprovalAuthority.label')}"
		            redirect(action:edit,id:params.id)
		        }
			}
    }

}   

