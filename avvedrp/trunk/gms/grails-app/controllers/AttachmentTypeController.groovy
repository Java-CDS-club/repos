import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class AttachmentTypeController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ attachmentTypeInstanceList: AttachmentType.list( params ) ]
    }

    def show = {
    	def attachmentsService = new AttachmentsService()
        def attachmentTypeInstance = attachmentsService.getattachmentTypes(params.id)

        if(!attachmentTypeInstance) {
        	flash.error = "${message(code: 'default.AttachmentTypenotfound.label')}"
            redirect(action:create)
        }
        else { return [ attachmentTypeInstance : attachmentTypeInstance ] }
    }

    def delete = {
    	def attachmentsService = new AttachmentsService()	
   		def NotificationsAttachmentsInstance = attachmentsService.getnotificationById(params.id)  	
   		if (!NotificationsAttachmentsInstance)
   		{
		        def attachmentTypeInstance = attachmentsService.getattachmentTypes(params.id)
		        if(attachmentTypeInstance) 
		        {
		        	attachmentTypeInstance.activeYesNo="N" //15-11-2010
		            //attachmentTypeInstance.delete() 15-11-2010
		            if(!attachmentTypeInstance.hasErrors() && attachmentTypeInstance.save()) //15-11-2010 
		            {
		            flash.message = "${message(code: 'default.deleted.label')}"
		            redirect(action:create)
		        }        
		        else 
		        {
		        	flash.error = "${message(code: 'default.AttachmentTypenotfound.label')}"
		            redirect(action:create)
		        }
		    }
   		}
   		else
   		{
   			flash.error = "${message(code: 'default.usedinProjects.label')}"
            redirect(action:edit,id:params.id)   			
   		}   		
        }

    def edit = {
    	def attachmentsService = new AttachmentsService()	
        def attachmentTypeInstance = attachmentsService.getattachmentTypes(params.id)

        if(!attachmentTypeInstance) {
        	flash.error = "${message(code: 'default.AttachmentTypenotfound.label')}"
            redirect(action:create)
        }
        else {
            return [ attachmentTypeInstance : attachmentTypeInstance ]
        }
    }

    def update = {
    	def attachmentsService = new AttachmentsService()	
    	/*Getting Attachment Type based on id*/
    	println "params "+params
    	def attachmentTypeInstance = attachmentsService.getattachmentTypes(params.id)
       if(attachmentTypeInstance) {
            def attachmentInstanceList = attachmentsService.getattachmentTypesByDocTypeAndType(params.type,attachmentTypeInstance.documentType)
	        if(attachmentInstanceList)
	        {
	        	if((attachmentInstanceList[0].id).equals(attachmentTypeInstance.id))
		        {
	        		attachmentTypeInstance.properties = params
	        		saveAttachmentType(attachmentTypeInstance)
		        }
	        	else
	        	{
	        		flash.error = "${message(code: 'default.AlreadyExists.label')}"
			        redirect(action:edit,id:attachmentTypeInstance.id) 
	        	}
	        }       
	        
	        else
	        {
	        	attachmentTypeInstance.properties = params
	        	saveAttachmentType(attachmentTypeInstance)
	        }
        }
        else {
        	flash.error = "${message(code: 'default.AttachmentTypenotfound.label')}"
            redirect(action:edit,id:params.id)
        }
    }
    public saveAttachmentType(def attachmentTypeInstance)
    {
    	 if(!attachmentTypeInstance.hasErrors() && attachmentTypeInstance.save()) {
             flash.message = "${message(code: 'default.updated.label')}"
             redirect(action:create,id:attachmentTypeInstance.id)
         }
         else {
             render(view:'edit',model:[attachmentTypeInstance:attachmentTypeInstance])
         }
    }
    def create = {
	GrailsHttpSession gh=getSession()
	gh.removeValue("Help")
	gh.putValue("Help","Create_AttachmentType.htm")//putting help pages in session
    	def attachmentsService = new AttachmentsService()	
        def attachmentTypeInstance = new AttachmentType()
        attachmentTypeInstance.properties = params
        //def attachmentTypeInstanceList= AttachmentType.list( params ) 16-11-2010
        def attachmentTypeInstanceList=attachmentsService.getattachmentTypes()			
        return ['attachmentTypeInstance':attachmentTypeInstance,
                'attachmentTypeInstanceList': attachmentTypeInstanceList]
    }

    def save = 
    {
    	def attachmentsService = new AttachmentsService()
    	def attachmentTypeInstance = new AttachmentType(params)
        if(attachmentTypeInstance)
        {
	        def attachmentInstanceList = attachmentsService.getattachmentTypesByDocTypeAndType(attachmentTypeInstance.type,attachmentTypeInstance.documentType)
	        if(attachmentInstanceList)
	        {
	        	flash.error = "${message(code: 'default.AlreadyExists.label')}"
		        redirect(action:create,id:attachmentTypeInstance.id) 
	        }
	        else 
	        {
	        	
	        	attachmentTypeInstance.activeYesNo="Y" //15-11-2010
		        if(!attachmentTypeInstance.hasErrors() && attachmentTypeInstance.save()) 
		        {
		        	flash.message = "${message(code: 'default.created.label')}"
		            redirect(action:create,id:attachmentTypeInstance.id)
		        }
		        else {
		            render(view:'create',model:[attachmentTypeInstance:attachmentTypeInstance])
		        }
	        }
        }
    }
}
