package in.ac.dei.edrp.pms.role;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class AddNewRoleAction extends Action {
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		RoleForm newroleform = (RoleForm) form;
		HttpSession session=request.getSession();
		
		
		Connection con=null;
		String forwardmsg="rolefail";
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		request.setAttribute("message","New Role has not been added.");
		con=MyDataSource.getConnection();
		if(checkRecord.twoFieldDuplicacyChecker("Role_ID","role","Role_Name",newroleform.getRolename().trim(),"ValidOrgPortal",(String)session.getAttribute("validOrgInPortal"))!=null)
		{
			System.out.println("role already exist.");
			return mapping.findForward("rolesuccess");
		}
		/*
		 * Inserting the record into role table.
		 */
		PreparedStatement ps=con.prepareStatement("insert into role values(?,?,?,?,NOW(),NOW(),?)");
		ps.setInt(1,0);
		ps.setString(2,newroleform.getRolename().trim());
		ps.setString(3,newroleform.getRoledescription().trim());
		ps.setString(4,(String)session.getAttribute("uid"));
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
		ps.setString(5,(String)session.getAttribute("validOrgInPortal"));
		}
		else
			ps.setString(5,null);//in case of super admin
		int x=ps.executeUpdate();
		if(x>0) /*if x is greater than zero it means insertion operation is successful.*/
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("msg.role.added");
			errors.add("rolemsg",error);
			saveErrors(request,errors);
			/*
			 * Inserting the record into default_authority table.
			 */
			String authority[]=newroleform.getAuthorities();
			if(authority!=null){
			PreparedStatement pstmt = null;
			String roleid=checkRecord.twoFieldDuplicacyChecker("Role_ID","role","Role_Name",newroleform.getRolename().trim(),"ValidOrgPortal",(String)session.getAttribute("validOrgInPortal"));
			con.setAutoCommit(false);
		      String query = "insert into default_authority(role_id, authorities) values(?, ?)";
		      pstmt = con.prepareStatement(query);
		      for(int i=0;i<authority.length;i++)
				{
		       	    pstmt.setInt(1, Integer.parseInt(roleid));
				    pstmt.setString(2, authority[i]);
				    pstmt.addBatch();
				}
		      int[] updateCounts = pstmt.executeBatch();
		      System.out.println("Successfully executed; totalInsertion=" + updateCounts.length);
		      con.commit();
			}
			forwardmsg="rolesuccess"; 
		}
			
		}
		catch(Exception e)
		{
			System.out.println("error in add new role action="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}

		return mapping.findForward(forwardmsg);
	}
}
