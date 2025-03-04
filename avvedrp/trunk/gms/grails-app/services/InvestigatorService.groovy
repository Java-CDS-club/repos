class InvestigatorService {

	public List getAllInvestigators(String subQuery)
	{  
		def investigatorInstanceList=Investigator.findAll("from Investigator I where I.activeYesNo='Y'"+subQuery)
		return investigatorInstanceList
	}
	   
	public List getInvestigatorsWithParty(def partyId,String subQuery)
	{	   
		def investigatorInstanceList=Investigator.findAll("from Investigator I where I.party.id="+partyId+"and I.activeYesNo='Y'"+subQuery)
		return investigatorInstanceList
	}
	
	public List getInvestigatorsWithParty(def partyId)
	{	
		def invertigatrList = []
		def investigatorList=Investigator.findAll("from Investigator I where I.party.id="+partyId+"and I.activeYesNo='Y'")
		for(investigator in investigatorList)
		 {
			if(investigator.userSurName == null)
			{
				investigator.fullName = investigator.name
				invertigatrList.add(investigator)
			}
			else{
				investigator.fullName = investigator.name+" "+investigator.userSurName
				invertigatrList.add(investigator)
			}
		
		 }
		return invertigatrList
	}
	
	public List getInvestigatorsWithPartyAndPIMap(def partyId , def params)
	{	
		def invertigatrList = []
		def investigatorList=Investigator.findAll("from Investigator I where I.party.id="+partyId+"and I.activeYesNo='Y'")
		for(int i=0;i<investigatorList.size();i++)
    	{
    		investigatorList=Investigator.findAll("from Investigator I where I.party.id="+partyId+"and I.activeYesNo='Y' and   I.id NOT IN (SELECT PM.investigator.id from ProjectsPIMap PM where PM.projects="+params.id+" and PM.role = 'PI' and PM.activeYesNo='Y')")
		}
    	for(investigator in investigatorList)
		 {
			if(investigator.userSurName == null)
			{
				investigator.fullName = investigator.name
				invertigatrList.add(investigator)
			}
			else{
				investigator.fullName = investigator.name+" "+investigator.userSurName
				invertigatrList.add(investigator)
			}
		
		 }
		return invertigatrList
	}
   
   /**
	 * Function to get investigator by id.
	 */
   public getInvestigatorById(def investigatorId)
   {
	   def investigatorInstance = Investigator.get(investigatorId)
	   return investigatorInstance
   }
   
   public List getUniqueEmail(def params)
   {
	   def chkUniqueEmailInstance = Investigator.findAll("from Investigator I where I.email= '" + params.email + "'")
	   return chkUniqueEmailInstance
   }
   
   public List getUniqueName(def params)
   {
	   def chkUniqueNameInstance = Investigator.findAll("from Investigator I where I.name= '" + params.name + "'")
	   return chkUniqueNameInstance
   }
   
    public List getUniqueaadar(def params)
   {
	   def chkUniqueaadhaarNoInstance = Investigator.findAll("from Investigator I where I.aadhaarNo= '" + params.aadhaarNo + "'")
	   return chkUniqueaadhaarNoInstance
   }
   
   /**
    * Function to get investigator by department
    */
    public getinvestigatorByDepartment(def department)
   {
    	def investigatorInstance=Investigator.findAll("from Investigator IT where IT.department="+department + " AND IT.activeYesNo='Y'")
    	return investigatorInstance
   }
    /**
	 * Function to get Project Pi Map by id.
	 */
   public getProjectsPIMapById(def investigatorMapId)
   {
	   def projectsPIMapInstance = ProjectsPIMap.get( investigatorMapId )
	   return projectsPIMapInstance
   }
   /*
 	 * Function to save PI Map
 	 */
 	 public savePIMap(def projectsPIMapInstance)
 	{
 		if(projectsPIMapInstance)
 		{
 			projectsPIMapInstance.activeYesNo="Y"
 			def projectPIMapSaveInstance = updatePIMap(projectsPIMapInstance)
	        return projectPIMapSaveInstance
 		}
 	}
 	/*
 	 * Function to update PI Map
 	 */
 	 public updatePIMap(def projectsPIMapInstance)
 	{
 		if(projectsPIMapInstance)
 		{
 			projectsPIMapInstance.save()
	        return projectsPIMapInstance
 		}
 	}
 	
 	 /*
 	 * Function to save CO-PI Map
 	 */
 	 public saveCOPIMap(def projectsCOPIMapInstance)
 	{
 		if(projectsCOPIMapInstance)
 		{
 			def projectCOPIMapInstanceSave = updatePIMap(projectsCOPIMapInstance)
	        return projectCOPIMapInstanceSave
 		}
 	}
 	/*
 	 * Function to update CO-PI Map
 	 */
 	 public updateCOPIMap(def projectsCOPIMapInstance)
 	{
 		if(projectsCOPIMapInstance)
 		{
 			projectsCOPIMapInstance.save()
	        return projectsCOPIMapInstance
 		}
 	}
 	/*
  	 * Function to delete PI Map
  	 */
  	 public deletePIMap(def projectsPIMapInstance)
  	{
  		if(projectsPIMapInstance)
  		{
  			projectsPIMapInstance.activeYesNo="N"
  			projectsPIMapInstance=updatePIMap(projectsPIMapInstance)
 	        return projectsPIMapInstance
  		}
  	}
  	/**
 	 * Function to get Project Pi Map by id.
 	 */
    public ProjectsPIMap getProjectsPIMapByProjectsId(def projectsId)
    {
 	   def projectsPIMapInstance = ProjectsPIMap.find("from ProjectsPIMap PM where PM.role='PI' and PM.projects="+projectsId)
 	   return projectsPIMapInstance
    }
    
  	/**
  	 * Getting Investigator Instance by email.
  	 */
  	 public getInvestigatorByemail (def email)
  	 {
  		def investigatorInstance = Investigator.find("from Investigator I where I.email='"+email+"'")
		return investigatorInstance
  	 }
  	
  	/*
  	 * Getting All Investigator List
  	 */
  	 
  	public List getAllInvestigators()
 	{  
  		def invertigatrList = []
 		def investigatorInstanceList=Investigator.findAll("from Investigator I where I.activeYesNo='Y'")
 		for(investigator in investigatorInstanceList)
		 {
 			if(investigator.userSurName == null)
			{
 				investigator.fullName = investigator.name
 				invertigatrList.add(investigator)
			}
			else{
				investigator.fullName = investigator.name+" "+investigator.userSurName
				invertigatrList.add(investigator)
			}
			
		
		 }
		return invertigatrList
 	}
}
