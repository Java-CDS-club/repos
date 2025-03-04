import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.ControllerArtefactHandler
import org.springframework.beans.BeanWrapper
import org.springframework.beans.PropertyAccessorFactory
import org.apache.commons.validator.EmailValidator

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.*;
import jxl.CellType;
import jxl.biff.formula.FormulaException;

/**
 * User controller.
 */
 

class UserController extends GmsController {
	def authenticateService
	def grantAllocationService
	def dataSource 
    def dataSecurityService
    def notificationsEmailsService
    def userService
    def gmsSettingsService
    def projectsService
    def partyDepartmentService
    def employeeDesignationService
    def proposalService
	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	def index = {
		redirect action: list, params: params
	}

	 def list = {
		
		GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","User_List.htm")	
       	 
		if (!params.max) {
			params.max = 100
		}
		def userMapList
		def total
		def userService = new UserService()
		def personRoleInstance = userService.getUserRoleByUserId(session.UserId)
		 String subQuery ="";
		def authorityList = []
         if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by UM."+params.sort
         if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
        	if(getUserRole().equals('ROLE_ADMIN'))
        		userMapList = userService.getAllUsers(subQuery)
	
		  else
		  {
			  //if(getUserRole().equals('ROLE_SUPERADMIN'))
			 if(personRoleInstance[0].authority == 'ROLE_SUPERADMIN')
			 {
				  userMapList =  userService.getAllUserMapWithActiveParty()
				  }
			  else
			  {
				  userMapList = userService.getAllUsersFromSite(getUserPartyID(),subQuery)
				  }
					if(userMapList)
					  {
						for(int i=0;i<userMapList.size();i++)		 
						  {				 				 
							 /*
							  * 1.get userRoleInstance based on the user id
							  * 2. get authorityInstsnce based on the role
							  */
							 
							  def userRoleInstance = userService.getUserRoleByUserId(userMapList[i].user.id)
							  
							  if(userRoleInstance)
							  {
								  //def authorityInstsnce = userService.getauthority(userRoleInstance.role.id)
								  authorityList.add(userRoleInstance[0].authority)
							  }
						  }
					  }
			 
		  }  
		[userMapList:userMapList,authorityList:authorityList,personRoleInstance:personRoleInstance[0]]	  
 
	}
	
	def show = {
		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		if (!person) {
			flash.message = "${message(code: 'default.notfond.label')}"
			redirect action: list
			return
		}
		
		def roleNames = userService.getRolesOfUser(person)
		[person: person, roleNames: roleNames]
	}

	/**
	 * Person delete action. Before removing an existing person,
	 * he should be removed from those authorities which he is involved.
	 */
	 

	 
	 
	def delete = {

		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		if (person) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal.username == person.username) {
				flash.message = "${message(code: 'default.cannotdeleteyourself.label')}"
			}
			else {
				
				//first, delete this person from People_Authorities table.
				Integer userId = userService.deleteUser(person)
				flash.message = "${message(code: 'default.deleted.label')}"
				}
		}
		else {
			flash.message = "${message(code: 'default.notfond.label')}"
		}

		redirect action: list
	}


	def edit = {
		GrailsHttpSession gh=getSession()
		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		def party = userService.getParty(person)
		def grantAllocationInstance = new GrantAllocation();
    	def personRoleInstance = userService.getUserRoleByUserId(session.UserId)
		grantAllocationInstance.party=party;
		if (!person) {
			flash.message = "${message(code: 'default.notfond.label')}"
			redirect action: list
			return
		}
		//get active roles
		def authorityInstance = userService.getActiveRoles()
		def userMapInstance   = userService.getUserByIdAndParty(params.id,gh.getValue("Party"))
		//get role of current user 
		def userRoleInstance  = userService.getUserRoleByUserId(params.id)	
		//get current role of a user
		def authorityPersonInstance = Authority.find("from Authority AA where AA.id ="+userRoleInstance[0].id)
		//return buildPersonModel(person)
		return [person:person, party:party,grantAllocationInstance : grantAllocationInstance,
		        authorityInstance:authorityInstance, authorityPersonInstance:authorityPersonInstance ,personRoleInstance:personRoleInstance[0]]
	}

	/**
	 * Person update action.
	 */
	def update =
	{
		def person = userService.getUserById(new Integer(params.id))
		EmailValidator emailValidator = EmailValidator.getInstance()
		if (emailValidator.isValid(params.email))
		{
			def userService = new UserService()
			def personRoleInstance = userService.getUserRoleByUserId(session.UserId)
			def ctx = AH.application.mainContext
			def springSecurityService=ctx.springSecurityService
			Integer userId  = userService.getUserByUserName(params.email)
			Integer userEmailId = userService.getUserByEmail(params.email)
			Integer aadhaarId  = userService.getUserByaadaarno(params.aadhaarNo)
	        if((userId && userId != new Integer(params.id)) || (userEmailId && userEmailId != new Integer(params.id)))
	        {
	               	flash.message = "${message(code: 'default.UserNamealreadyexists.label')}"
	        		redirect action: edit, id: params.id
	        }
	        else
	        {
		        if(aadhaarId && aadhaarId != new Integer(params.id))
		        {
		               	flash.message = "${message(code: 'default.aadhaarIdalreadyexists.label')}"
		        		redirect action: edit, id: params.id
		        }
		        else
		        {
					long version = params.version.toLong()
					if (person.version > version) {
						person.errors.rejectValue 'version', "person.optimistic.locking.failure",
							"Another user has updated this User while you were editing."
							render view: 'edit', model: buildPersonModel(person)
						return
					}
					//def oldPassword = person.password
					person.properties = params
					if(params.Passwd)
						person.password = params.Passwd
						//person.password = springSecurityService.encodePassword(params.Passwd)
					//if (!params.password.equals(oldPassword)) {
					//	person.password = springSecurityService.encodePassword(params.password)
					//}
					
					Integer personId = userService.updateUser(person,params)
					if (!personId) {
						flash.message ="${message(code: 'default.notfond.label')}"
						redirect action: edit, id: params.id
					}
						else
						{
							if(personRoleInstance.authority == 'ROLE_SUPERADMIN')
							{
								if(params.Passwd)
								{
									
									def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
							    	String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
							    	//mail content
							    	String mailMessage="";
							        mailMessage="Dear "+params.userRealName+" "+params.userSurName+", \n \n Your GMS user account has been updated with following details.";
							        mailMessage+="\n \n LoginName    : "+params.email;
							        mailMessage+="\n Password     : "+params.Passwd;
							        // mailMessage+="\n \n \n To activate your account,click on the following link   \t:"+urlPath+personId+" \n";
							    	def emailId = notificationsEmailsService.sendMessage(params.email,mailMessage,"text/plain")
								    flash.message = "${message(code: 'default.UpdatedUser.label')}"
									redirect action:list,id:params.id
								}
								else
								{
									flash.message = "${message(code: 'default.updated.label')}"
									redirect action:list,id:params.id
								}
							}
							else
							{
								flash.message = "${message(code: 'default.updated.label')}"
								redirect action:list,id:params.id
							}
						}
					}
				}
		 }
		else
		{
			    flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
				def authorityInstance = userService.getActiveRoles() 
				redirect action: edit, id: person.id
		}
    }

	def create = {
		GrailsHttpSession gh=getSession()
		def userService = new UserService()
		def partyService = new PartyService()
		def authorityList = userService.getRoles()
		def authorityInstance = userService.getActiveRoles()
		def institutionList = partyService.getAllActiveParties()
    	def personRoleInstance = userService.getUserRoleByUserId(session.UserId)
    	 def partyinstance=Party.get(gh.getValue("Party"))
    	def departmentList = PartyDepartment.findAll("from PartyDepartment PD where PD.party.id="+partyinstance.id+"and PD.activeYesNo='Y'")
    	 def employeeDesignationInstanceList =employeeDesignationService.getemployeeDesignationList()
		gh.removeValue("Help")
				//putting help pages in session
			gh.putValue("Help","New_User.htm")	
		[person: new Person(params), authorityList: authorityList,authorityInstance:authorityInstance ,institutionList:institutionList,
		personRoleInstance:personRoleInstance[0],departmentList:departmentList,employeeDesignationInstanceList:employeeDesignationInstanceList]

	}
	

	/**
	 * Person save action.
	 */
	def save = {
	println"---------------"+params
		EmailValidator emailValidator = EmailValidator.getInstance()
		if (emailValidator.isValid(params.email))
		{
			def userService = new UserService()
			def ctx = AH.application.mainContext
			def springSecurityService=ctx.springSecurityService
			Integer userId  = userService.getUserByUserName(params.email)
			Integer aadhaarId  = userService.getUserByaadaarno(params.aadhaarNo)
			if(userId)
			{
				println"-----userId---------"
				flash.message = "${message(code: 'default.UserNamealreadyexists.label')}"
				redirect(action:create)
				
			}
			else
			{
				if(aadhaarId)
				{
					flash.message = "${message(code: 'default.aadhaarIdalreadyexists.label')}"
					redirect(action:create)
					
				}
				else
				{
					def person = new Person()
					//person.properties = params
					def departmentInstance = PartyDepartment.get(params.department.id)
					person.username=params.email
					person.userRealName=params.userRealName
					person.userSurName=params.userSurName
					person.email=params.email
					person.password=params.password
					//person.password = springSecurityService.encodePassword(params.password)
					person.password = params.password
					person.enabled=false
					person.activeYesNo='Y'
					person.userDesignation = params.userDesignation
					person.phNumber = params.phNumber
					person.aadhaarNo = params.aadhaarNo
					person.department =departmentInstance
					if(params.get("party.id")==null)
					params.put("party.id",getUserPartyID())
				    Integer personId = userService.saveUser(person,params)
				    if(personId != null)
				    {
				        def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
				    	String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
				    	//mail content
				    	String mailMessage="";
				        mailMessage="Dear "+params.userRealName+" "+params.userSurName+", \n \n "+mailContent+".";
				        mailMessage+="\n \n LoginName    : "+params.email;
				        mailMessage+="\n Password     : "+params.password;
				        mailMessage+="\n \n \n To activate your account,click on the following link   \t:"+urlPath+personId+" \n";
				    	def emailId = notificationsEmailsService.sendMessage(params.email,mailMessage,"text/plain")
				    	flash.message = "${message(code: 'default.UserCreated.label')}"
						redirect action: list, id: personId
					}
					else
				    {
						def authorityList = userService.getRoles()
						render view: 'create', model: [authorityList: authorityList, person: person]
					}
				}
			}
		}
		else
		{
			flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
			def authorityInstance = userService.getActiveRoles() 
			println"authorityInstance"+authorityInstance
			render view: 'create', model: [authorityInstance:authorityInstance, person: params]
		}
	}
	
	/**
	 * Person update password action.
	 */
	def updatePassword={
		def userService = new UserService()
		def ctx = AH.application.mainContext
		def springSecurityService=ctx.springSecurityService
		def personInstance = new Person()
		def person = userService.getUserById(new Integer(params.id))
		if (!person) {
			flash.message ="${message(code: 'default.notfond.label')}"
			redirect action: changePassword, id: params.id
			return
		}

		long version = params.version.toLong()
		if (person.version > version) {
			person.errors.rejectValue 'version', "person.optimistic.locking.failure",
				"Another user has updated this User while you were editing."
				render view: 'changePassword', model: [person: person]
			return
		}
		
		/* Check whether entered old password is correct or not. */
		def oldPasswd = springSecurityService.encodePassword(params.oldPasswd)
		//def oldPasswd = params.oldPasswd
		def oldPassword = person.password
		if(!oldPasswd.equals(oldPassword)){
			flash.message ="${message(code: 'default.Oldpasswordnotcorrect.label')}"
			redirect action: changePassword, id: person.id
		}
		else{
			person.properties = params
			if (!params.newPasswd.equals(oldPassword)) {
				//person.password = springSecurityService.encodePassword(params.newPasswd)
				person.password = params.newPasswd
				Integer personId = userService.updatePassword(person)
				if(personId != null){
					flash.message = "${message(code: 'default.Passwordchanged.label')}"
					redirect action: changePassword, id: personId
				}
				else{
					flash.message = "${message(code: 'default.Passwordnotchanged.label')}"
					render view: 'changePassword', model: [person: person]
				}
			}
		}
		
		
	}
	
	/**
	 * Person change password action
	 */
	def changePassword = {
		GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Change_Password.htm")//putting help pages in session
		def userId = gh.getValue("UserId")
		
		def dataSecurityService = new DataSecurityService()
		def person = dataSecurityService.getUserOfLoginUser(userId)
		if (!person) {
			flash.message = "${message(code: 'default.notfond.label')}"
			
		}
				
		[person:person] 
	}

	

	private Map buildPersonModel(person) {

		def userService = new UserService()
		List roles  = userService.getRoles()
//		List roles = Role.list()
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}
		Set userRoleNames = []
		for (role in person.authorities) {
			userRoleNames << role.authority
		}
		LinkedHashMap<Authority, Boolean> roleMap = [:]
		for (role in roles) {
			roleMap[(role)] = userRoleNames.contains(role.authority)
		}

		return [person: person, roleMap: roleMap]
	}
	def newUserCreate = {
			def userService = new UserService()
			def authorityList = userService.getRoles()
			[person: new UserMap(params), authorityList: authorityList]
		
	}
	def saveNewUser = {
			EmailValidator emailValidator = EmailValidator.getInstance()
			if (emailValidator.isValid(params.email))
			{
			def ctx = AH.application.mainContext
			def springSecurityService=ctx.springSecurityService
			def userService = new UserService()
			def personRoleInstance = userService.getUserRoleByUserId(session.UserId)
			def person = new UserMap()
			def user = new Person()
			def newParty = new Party()
			def partyInstance = new Party()
			person.properties = params
			
			user.username= params.email
			
			user.userRealName = params.userRealName
			user.userSurName = params.userSurName
			user.password = params.password
			user.email = params.email
		//	user.password = springSecurityService.encodePassword(params.password)
			user.enabled=false
			user.activeYesNo='Y'
			user.userDesignation = params.userDesignation
			user.phNumber = params.phNumber
			Integer userId  = userService.getUserByUserName(params.email)
			
			if(userId)
			{
			
				person.user = user
				flash.message = "${message(code: 'default.UserNamealreadyexists.label')}"
				render(view: 'newUserCreate', model: [person:person,user:user,partyInstance:partyInstance])
				 
			}
			else
			{
				
		        partyInstance.createdBy="admin"
		        partyInstance.createdDate = new Date();
		        partyInstance.modifiedBy="admin"
		        partyInstance.modifiedDate = new Date();
		        partyInstance.nameOfTheInstitution = params.get("party.nameOfTheInstitution")
		        partyInstance.code = params.get("party.code")
		        partyInstance.address = ""
		        partyInstance.activeYesNo = 'Y'
		       
		        partyInstance.email = ""
		        partyInstance.phone = ""
		       
			def party = userService.addParty(partyInstance)
			 
			if((partyInstance.saveMode != null) && (partyInstance.saveMode.equals("Duplicate")))
	       		 {
	       		 person.user = user
	       			flash.message = "${message(code: 'default.InstitutionAlreadyExists.label')}"
	                render(view: 'newUserCreate', model: [person:person,user:user,partyInstance:partyInstance])
	       		}
			else
			{
				
				def userInstance = userService.saveNewUser(user,params)
				
				person.user = userInstance
				person.user.id = userInstance.id;
				
				person.party = party
				person.party.id = party.id
				
				Integer personId = userService.saveNewUserMap(person,params)
			  
				
		       	//creating role privileges for each role in the new party ending
			    if(personId != null){
			    	String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
			    	def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
			    	def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
			    	//mail content
			    	String mailMessage="";
			        mailMessage="Dear "+params.userRealName+params.userSurName+", \n \n "+mailContent+".";
			        mailMessage+="\n \n LoginName    : "+params.email;
			        mailMessage+="\n Password     : "+params.password;
			        mailMessage+="\n \n \n To activate your account,click on the following link   \t: "+urlPath+personId+" \n";
			        mailMessage=mailMessage+" \n\n" 
			    	mailMessage+=mailFooter
			        def emailId = notificationsEmailsService.sendMessage(params.email,mailMessage,"text/plain")
					//redirect uri: '/user/newUserConfirm'
					if(personRoleInstance.authority!='ROLE_SUPERADMIN')
						render(view: "newUserConfirm")
					else
					{
						render(view:"newUserCreate")
						flash.message = "${message(code: 'default.CreatedSiteAdministrator.label')}"
					}
				}
		       		 }
			}
			}
			else
			{
				flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
				
				render(view: "newUserCreate", model: [person: params])
			}
			
		}
	def newUserConfirm={}
	
	/**
	 * Action to activate a user
	 */
	def userActivation = {
		   
			def userInstance = userService.getUserById(Integer.parseInt(params.id))
			if(userInstance.enabled==false)
			{
				userInstance.enabled=true
				userInstance.save()
				GrailsHttpSession gh=getSession()
				def userMap=UserMap.find("from UserMap UM where UM.user.id="+params.id);
				def party=userMap.party
				//creating accespermission for all roles in new party
				 def webRootDir
				if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
				{
					webRootDir = servletContext.getRealPath("/")+"WEB-INF/grails-app/views/"
					//folder = webRootDir+"WEB-INF/grails-app/views"
				}
		       	if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
		       	{
		       		webRootDir = "grails-app/views/"
		       		//folder = new File("grails-app/views")
		       	}
		       //	def roleId=null
		       //	def rolePrivilegesService = new RolePrivilegesService()
	    		//def rolePrivilegesSaveStatus = rolePrivilegesService.saveRolePrivilegesForParty(party,webRootDir,roleId)
	    		//creating role privileges for each role in the new party starting
	    		 
	    		if(!params.passwd)
	    		{
	    			 
	    		def roleInstance = Authority.findAll("from Authority A")
	    		def data = []
	       		for (controller in grailsApplication.controllerClasses) {
	       			def controllerInfo = [:]
	       			controllerInfo.controller = controller.logicalPropertyName
	       			controllerInfo.controllerName = controller.fullName
	       			List actions = []
	       			BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(controller.newInstance())
	       			for (pd in beanWrapper.propertyDescriptors) {
	       				String closureClassName = controller.getPropertyOrStaticPropertyOrFieldValue(pd.name, Closure)?.class?.name
	       						if (closureClassName) 
	       							{
	       							actions << pd.name
	       								for(authorityInstance in roleInstance)
	       								{
	       									def rolePrivilegesInstance=new RolePrivileges()
	       									rolePrivilegesInstance.controllerName=controller.logicalPropertyName
	       									rolePrivilegesInstance.actionName=pd.name
	       									rolePrivilegesInstance.role=authorityInstance
	       									rolePrivilegesInstance.party=party
	       									rolePrivilegesInstance.save()
	       								}
	       							}
	       			}
	       			controllerInfo.actions = actions.sort()
	       			data << controllerInfo
	       		}
	       		
	    		}
	       		
				redirect uri: '/user/userConfirmation'
			}
			else
			{
				redirect(controller:'login',action:'auth')
			}
		}
	
	/**
	 * Action to change password
	 */
	def sendNewPassword = {
			if(params.email)
			{
			def userId = userService.getUserByUserName(params.email)
			def ctx = AH.application.mainContext
			def springSecurityService=ctx.springSecurityService
			
			if(userId)
			{
				def userInstance = userService.getUserById(userId)
				def subName=userInstance.username
				println userInstance.email
				println "params.email"+params.email
				String newPassword 
				println "subName.indexOf('@') "+subName.indexOf('@')
				if(subName.indexOf('@') < 0)
				{
				newPassword = subName.substring(0,1)
				println "newPassword for invalid"+newPassword
				
				}
				else
				{
					newPassword = subName.substring(0,subName.indexOf('@'))
					println "newPassword"+newPassword
					
				}
				EmailValidator emailValidator = EmailValidator.getInstance()
					if(userInstance.email)
					{
						if (emailValidator.isValid(userInstance.email))
						{
							// userInstance.password=springSecurityService.encodePassword(newPassword)
							userInstance.password=newPassword
							userInstance.enabled=false
							userInstance.save()
							String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
							def emailId = notificationsEmailsService.sendChangePassword(userInstance.email,newPassword,userInstance.username,userInstance.id,urlPath)
							redirect uri: '/user/forgotPasswordConfirm'
						}
						else
						{
							flash.message = "${message(code: 'default.noEmailAddressAssociated.label')}" 
							render(view: "forgotPassword", model: [person: params])
						}
					}
					else
					{
						flash.message = "${message(code: 'default.noEmailAddressAssociated.label')}" 
						render(view: "forgotPassword", model: [person: params])
					}
				}
				else
				{
					flash.message = "${message(code: 'default.enterValidUserName.label')}"
					render(view: "forgotPassword", model: [person: params])
				}
			}
			else
			{
				flash.message = "${message(code: 'default.enterValidUserName.label')}"
				render(view: "forgotPassword", model: [person: params])
			}
	}
	def forgotPassword = {
			
	}
	def accessControlEntry = {
			
			List<GrantAllocation> grantAllocationInstance 	
		
	    
	    	
	    	
	    	try{
	    		grantAllocationInstance = grantAllocationService.getGrantAllocationByActiveProjects()
		
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
	    	//def projectInstance = dataSecurityService.getAccessPermissionProjects()
			GrailsHttpSession gh=getSession()
			gh.removeValue("Help")
			gh.putValue("Help","Create_Project_Permission.htm")//putting help pages in session
			def userMapInstance = UserMap.findAll("from UserMap UM where UM.party.id="+gh.getValue("PartyID")+" and UM.user.id != "+gh.UserId+" and UM.user.id in(select PR.id from Person PR where PR.activeYesNo='Y')")
			[grantAllocationInstance:grantAllocationInstance,userMapInstance:userMapInstance]
	}
	def getandSaveAccessPermission = {
			//def grantAllocationId = params.projectName
			//checking more than one value selected
			if(params.user.id){
			def grantAllocationId = params.projectName
			def grantList=params.projectName.toString();
    		def grantSplit=grantList.split(',')
    		if(grantSplit.length==1)
    		{
    			def grantAllot=[]
    			grantAllot.add(params.projectName)
    			grantAllocationId=grantAllot
    		}
			
			def aclInstance = dataSecurityService.saveAccessPermisionDetails(grantAllocationId,params.user.id)
			redirect(action:getProjectName,params:[user:params.user.id])
			}
			else
			{
				
			}
	}
	
	//for listing project details of each user
	def getProjectName = {
			GrailsHttpSession gh=getSession()
			if(params.user)
			{
			
			List<GrantAllocation> grantAllocationInstance 	
		
	    
	    	
	    	
	    	try{
	    		grantAllocationInstance = grantAllocationService.getGrantAllocationByActiveProjects()
		
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
	    	if(params.user != "null")
			{
	    	//def projectsInstance = dataSecurityService.getAccessPermissionProjects()
			
			def userMapInstance = UserMap.findAll("from UserMap UM where UM.party.id="+gh.getValue("PartyID"))
			def projectInstance = dataSecurityService.getAccessPermissionProjects(params.user)
			def grantAllocationList=[]
			def grantCollection=[]
			
			
			if(projectInstance)
			{
				grantCollection = new ArrayList(grantAllocationInstance.projects.id)
				grantCollection.removeAll(projectInstance.id)
				for(id in grantCollection)
				{
					def projectId = GrantAllocation.find("from GrantAllocation GA where GA.projects.id="+id)
					grantAllocationList.add(projectId)
				}
			}
			else
			{
				grantAllocationList=grantAllocationInstance
			}
			
			render (template:"projectNameSelect", model : ['grantAllocationInstance' : grantAllocationList,
			                                               'userMapInstance':userMapInstance,
			                                               'projectInstance':projectInstance])
			}
			else
			{
				render (template:"projectNameSelect", model : ['grantAllocationInstance' : grantAllocationInstance])
			}
			}
			else
			{
				redirect action: accessControlEntry
			}
	}
	def deleteProjectFromAccespermision = {
			def projectInstance = params.projectId
			def projectList=params.projectId.toString();
    		def projectSplit=projectList.split(',')
    		if(projectSplit.length==1)
    		{
    			def projectArray=[]
    			projectArray.add(params.projectId)
    			projectInstance=projectArray
    		}
			def aclInstance = dataSecurityService.deleteAccessPermission(projectInstance,params.user.id)
			redirect(action:getProjectName,params:[user:params.user.id])
			
	}
	
	def changeUserName = {
			GrailsHttpSession gh=getSession()
			def userId = gh.getValue("UserId")
			def dataSecurityService = new DataSecurityService()
			def person = dataSecurityService.getUserOfLoginUser(userId)
			if (!person) {
				flash.message = "${message(code: 'default.notfond.label')}"
				
			}
					
			[person:person] 
		}
	
	
	def updateUserName=
	{
			def userService = new UserService()
			// def dataSecurityService = new DataSecurityService()
			//def ctx = AH.application.mainContext
			// def springSecurityService=ctx.springSecurityService
			def personInstance = new Person()
			def person = userService.getUserName(params)
			if (!person) 
			{
				flash.message ="No user with this user name exists"
				redirect action:changeUserName, id: params.id
			}
			else
			{
				// person.properties = params
				//person.username = chkUserNameForUser[0].username
				if (!params.newUsrName.equals(params.oldUsrName)) 
				{
					person.username = params.newUsrName
					Integer personId = userService.updateUserName(person)
					if(personId != null)
					{
						def accessPermissionInstance = dataSecurityService.changeAccessPermission(personId,params)
						flash.message = "UserName changed successfully"
						redirect (action: "changeUserName", id :  personId)
					}
					else
					{
						flash.message = "UserName not changed"
						render view: 'changeUserName', model: ['person': person]
					}
				}
				else
				{
					flash.message = "The new username is same as old username"
					render view: 'changeUserName', model: ['person': person]
						
				}
				
			}
			
			
		}
	
	def newUserBySuperAdmin = 
	{
		GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","InstitutionBySuperAdmin.htm")//putting help pages in session
	}
	def saveNewUserBySuperAdmin =
	{
				def ctx = AH.application.mainContext
				def springSecurityService=ctx.springSecurityService
				def userService = new UserService()
				def person = new UserMap()
				def user = new Person()
				def newParty = new Party()
				def partyInstance = new Party()
				
				person.properties = params
				
				user.username= params.email
				user.activeYesNo = "Y"
				user.userRealName = params.userRealName
				user.userSurName = params.userSurName
				user.password = params.password
				user.email = params.email
				//user.password = springSecurityService.encodePassword(params.password)
				user.password = params.password
				user.enabled=false
				user.userDesignation = params.userDesignation
				user.phNumber = params.phNumber
				Integer userId  = userService.getUserByUserName(params.email)
				
				if(userId)
				{
					flash.error = "${message(code: 'default.UserNameOfSiteadminalreadyexists.label')}"
					redirect uri: '/user/newUserBySuperAdmin.gsp'
				}
				else
				{
					
			        partyInstance.createdBy="admin"
			        partyInstance.createdDate = new Date();
			        partyInstance.modifiedBy="admin"
			        partyInstance.modifiedDate = new Date();
			        partyInstance.nameOfTheInstitution = params.nameOfTheInstitution
			        partyInstance.code = params.code
			        partyInstance.address = params.address
			        partyInstance.activeYesNo = 'Y'
			       
			        partyInstance.email = params.institutionemail
			        partyInstance.phone = params.phone
			       
				    def party = userService.addParty(partyInstance)
					if((partyInstance.saveMode != null) && (partyInstance.saveMode.equals("Duplicate")))
			       	{
						
		       			flash.error = "${message(code: 'default.InstitutionAlreadyExists.label')}"
		                redirect uri: '/user/newUserBySuperAdmin.gsp'
			       	}
					else	
					{
						
						def userInstance = userService.saveNewUser(user,params)
						person.user = userInstance
						person.user.id = userInstance.id;
						person.party = party
						person.party.id = party.id
						Integer personId = userService.saveNewUserMap(person,params)
					  
						
				       	//creating role privileges for each role in the new party ending
					    if(personId != null)
					    {
					    	String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
					    	def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
					    	def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
					    	//mail content
					    	String mailMessage="";
					        mailMessage="Dear "+params.userRealName+params.userSurName+", \n \n "+mailContent+".";
					        mailMessage+="\n \n LoginName    : "+params.email;
					        mailMessage+="\n Password     : "+params.password;
					        mailMessage+="\n \n \n To activate your account,click on the following link   \t: "+urlPath+personId+" \n";
					        mailMessage=mailMessage+" \n\n" 
					    	mailMessage+=mailFooter
					        def emailId = notificationsEmailsService.sendMessage(params.email,mailMessage,"text/plain")
							//redirect uri: '/user/newUserConfirm'
								
									render(view:"newUserBySuperAdmin")
									flash.message = "${message(code: 'default.CreatedSiteAdministrator.label')}"
							
						}
				     }
				}
	}
			

	
	def forgotPasswordConfirm = {}
	def userConfirmation = {}
	
	def deleteUser = {
			def person = userService.getUserById(new Integer(params.id))	
			if(person)
			{
				person.enabled = false
  	        	person.activeYesNo = 'N'
 	        	person.save()
 	        }
 	        if(person.save())
 	        {
 	        def investigatorInstance = Investigator.find("from Investigator I where I.email='"+params.email+"'")
 	        	if(investigatorInstance) 
	            {
	             	investigatorInstance.activeYesNo="N"
		    		investigatorInstance.save()
		         }
			def userMapInstance = UserMap.find("from UserMap UM where UM.user.id="+params.id)
			if(userMapInstance)
			{
				userMapInstance.delete()
			}
			 flash.message = "${message(code: 'default.deleted.label')}"
		     redirect(action:list)
	     }
	}
	
	def uploadxls = 
	{
	GrailsHttpSession gh=getSession()
	def partyInstance = Party.find("from Party P where P.id="+gh.getValue("Party"))
		def partyProposalFormOldInstanceList = proposalService.getAllFormByPartyAndFormType('Excel',partyInstance.id)
		return['partyProposalFormOldInstanceList':partyProposalFormOldInstanceList]
	}
	
	def saveUploadedxls = 
	{
		GrailsHttpSession gh=getSession()
		def proposalApplicationPath = gmsSettingsService.getGmsSettingsValue("ProposalApplicationPath")
		def partyProposalFormInstance = new PartyProposalForm()
		def partyInstance = Party.find("from Party P where P.id="+gh.getValue("Party")) 
		def webRootDir
	     if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
	     {
	     	webRootDir = proposalApplicationPath
	     }
	     if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
	     {
	     	webRootDir = proposalApplicationPath
	     }
	     	def downloadedfile = request.getFile("attachmentPath");
	     	def contentType = downloadedfile.contentType 
	     if(!downloadedfile.empty) 
	     {
	     		//String fileName=downloadedfile.getOriginalFilename()
	     		String fileName=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().indexOf("."))
	     	  	downloadedfile.transferTo( new File( webRootDir + File.separatorChar+"Excel-"+fileName+".xls") )
	     	  		partyProposalFormInstance.name="Excel-"+fileName+".xls"
		     		partyProposalFormInstance.value=fileName+".xls"
		     		partyProposalFormInstance.formType="Excel"
		     		partyProposalFormInstance.notification=null
		     		partyProposalFormInstance.activeYesNo='Y'
		     		partyProposalFormInstance.party=partyInstance
		     		if (partyProposalFormInstance.save(flush: true))
		     		{
				     	flash.message = "File uploaded successfully"
				     	redirect(action: "uploadxls")
		     		}
		     	 else {
	                	//redirect(action: "create", id: attachmentsInstance.domainId)//, model: [attachmentsInstance: attachmentsInstance])
	                }
		}
		}
		
		def importExcel =
		{
			String filePath = gmsSettingsService.getGmsSettingsValue("ProposalApplicationPath")
			def partyExcelInstance = PartyProposalForm.get(params.id)
			//String fileName = "StudentAdmissionData.xls"
			def fileName =  new File(filePath+partyExcelInstance.name)
			File excelFile = new File(filePath+System.getProperty("file.separator")+partyExcelInstance.name);
			/* Get details from excel. */
			Workbook  workbook = Workbook.getWorkbook(excelFile);
			def sheet = workbook.getSheet(0) 
			def  rows = sheet.getRows()
			for(int i=1;i<rows;i++)
			{
				def proposalCategoryInstance = new ProposalCategory()
				proposalCategoryInstance.name = (sheet.getCell(0,i).getContents());
				println"--------getCell(0,i)-------"+proposalCategoryInstance.name 
				proposalCategoryInstance.remarks = (sheet.getCell(1,i).getContents());
				proposalCategoryInstance.activeYesNo = 'Y'
				proposalCategoryInstance.save()
				
			}
			redirect(action: "uploadxls")
		}
			
}
