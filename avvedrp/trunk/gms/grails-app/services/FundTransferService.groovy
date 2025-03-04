class FundTransferService
{
	public getFundTransferDetailsByProject(def projectId)
	{
		def fundTransferInstanceList=FundTransfer.findAll("from FundTransfer FT where FT.grantAllocation.projects = " +projectId)
		return fundTransferInstanceList
	}
	
	public List chkAmntValidation(def fundTransferInstance)
	{
		def chkFundTransferAmnt = GrantAllocation.findAll("from GrantAllocation GA where GA.id = "+fundTransferInstance.grantAllocationId)
		return chkFundTransferAmnt
	}
	
	public List getGrantAllocationByProjects(def fundTransferInstance)
	{
		def grantAllocationParentInstance= GrantAllocation.findAll("from GrantAllocation GA where GA.projects ="+fundTransferInstance.grantAllocation.projects.parent.id)
		return grantAllocationParentInstance
	}
	
	public List getFundTransferTotal(def grantExpenseInstance)
	{
	def fundTransferAmnt = FundTransfer.executeQuery("select sum(FT.amount) from FundTransfer FT where FT.grantAllocation.projects.parent="+grantExpenseInstance.projects.id)
    return fundTransferAmnt
	}
	
	public sumTransferInstanceByProject(def projectId)
	{
		
		double totalTransferAmt = 0.0
		def sumTransferAmt = FundTransfer.executeQuery("select sum(FU.amount) as SumAmt from FundTransfer FU where FU.grantAllocation.projects.parent.id ="+projectId)
		if(sumTransferAmt[0]!=null)
			totalTransferAmt = new Double(sumTransferAmt[0]).doubleValue()
			
		return totalTransferAmt
		
	}
	
	 public getFundTransferByGrantAllotId(def grantAllotId)
	  {
	  		def fundTransferInstance=FundTransfer.findAll("from FundTransfer FT where FT.grantAllocation.id = "+grantAllotId)
	  		return fundTransferInstance
	  }
}