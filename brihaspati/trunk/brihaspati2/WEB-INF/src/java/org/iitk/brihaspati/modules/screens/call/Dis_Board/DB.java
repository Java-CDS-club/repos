package org.iitk.brihaspati.modules.screens.call.Dis_Board;

/*
 * @(#)DB.java	
 *
 *  Copyright (c) 2005-2006, 2010, 2011 ETRG,IIT Kanpur. 
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import java.io.File;
import org.iitk.brihaspati.om.DbSendPeer;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.SystemIndependentUtil;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import com.workingdogs.village.Record;
//import com.workingdogs.village.Value;
import org.iitk.brihaspati.modules.utils.UserUtil; 
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil; 
//import org.iitk.brihaspati.modules.utils.CourseUtil; 
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.NoticeUnreadMsg;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
/**
 *   This class contains code for all discussions in workgroup
 *   Compose a discussion and reply.
 *   @author  <a href="aktri@iitk.ac.in">Awadhesh Kumar Trivedi</a>
 *   @author  <a href="sumanrjpt@yahoo.co.in">Suman Rajput</a>
 *   @author  <a href="rekha_20july@yahoo.co.in">Rekha Pal</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @author <a href="mailto:dewanshu.sisaudiya17@gmail.com">Dewanshu Singh Sisaudiya</a>
 * @ modified date: 13-Oct-2010 (Shaista)
 * @ modified date: 24-Aug-2011 (Sunil Kumar)
 * @ modified date: 30-jan-2012 (Dewanshu Singh Sisaudiya)
 */

public class DB extends SecureScreen
{
	/**
	 * @param data RunData instance
	 * @param context Context instance
	 * try and catch Identifies statements that user want to monitor
	 * and catch for exceptoin.
	 */
	
	public void doBuildTemplate(RunData data, Context context)
    	{
		try{
			/**
		  	* Create the instance of user
		  	* Getting the CourseId
			*/
			User user=data.getUser();
			String dir=(String)user.getTemp("course_id","testing");
			/*
                        * stats=data.getParameters().getString("stats","");
                        * mode2=data.getParameters().getString("mode2","");
                        * stats and mode2 use for general and institute wise discussion group
                        */
			String stats=data.getParameters().getString("stats","");
			String grpName=data.getParameters().getString("grpname","");
	                context.put("grpName",grpName);
	                context.put("stats",stats);
			String mode2=data.getParameters().getString("mode2","");
                        context.put("mode2",mode2);

                        /** Using institute name(grpName) we can fetch Institute Id from 'INSTITUTE_ADMIN_REGISTRATION' table and
                          * store this InstituteId in a String.
                          * Now we check if institute id is not null or empty then set it as "user" temp variable.  
                          */ 
                        String strInstid = null;
                        Criteria cri=new Criteria();
                        cri.add(InstituteAdminRegistrationPeer.INSTITUTE_NAME,grpName);
                        List list=InstituteAdminRegistrationPeer.doSelect(cri);
                        for(int m=0;m<list.size();m++)
                        {
                           InstituteAdminRegistration instuser=(InstituteAdminRegistration)(list.get(m));
                           int instituteid=instuser.getInstituteId();
                           strInstid = String.valueOf(instituteid);
                        }
                        if (strInstid != null && !strInstid.isEmpty())
                        {
                           data.getUser().setTemp("Institute_id",strInstid);
                        }

			int gid=0;
			if(stats.equals("fromIndex")){
                                gid=4;
			}else if(mode2.equals("instituteWise")){
				gid=5;
                	}else
				gid=GroupUtil.getGID(dir);
				String filePath=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/DisBoard";
				File dirHandle=new File(filePath);
				context.put("mode1",data.getParameters().getString("mode1",""));
				context.put("tdcolor",data.getParameters().getString("count",""));
				context.put("tdcolor1",data.getParameters().getString("countTemp",""));
				if(!(dirHandle.exists())){
					dirHandle.mkdirs();
				}
				String file[]=dirHandle.list();
				Vector v=new Vector();
				for(int i=0;i<file.length;i++)
				{
				/**
		  		* Add file in the Vector and vector put into context for further uses 
		  		*/
		 		v.addElement(file[i]);
		 		}
				for(int c=0;c<v.size();c++)
				{
					String Dname=(String)v.elementAt(c);
					Criteria crit=new Criteria();
					crit.add(DbSendPeer.MSG_SUBJECT,Dname);
					List l=DbSendPeer.doSelect(crit);
					if(l.size()==0)
					{
					/**
				 	* If no entry in the topic then Remove the topic(directory)
				 	*/ 
						String fileName=filePath+"/"+Dname;
						File f=new File(fileName);
						SystemIndependentUtil.deleteFile(f);
					}
				}//for
			
			/**
		  	* Getting the userId of logged user from Turbine_User table
		  	* @see UserUtil in Utils
		  	*/
			String user_name = user.getName();
			int user_id = UserUtil.getUID(user_name);
			int unread_flag=0;
			String unreads=new String();
		   	// Count the UnRead messages according to userId
			String unread_msg="SELECT COUNT(DB_RECEIVE.MSG_ID) UNREAD FROM DB_RECEIVE, DB_SEND WHERE DB_SEND.MSG_ID=DB_RECEIVE.MSG_ID AND DB_SEND.GROUP_ID="+gid+" AND RECEIVER_ID="+user_id+" AND READ_FLAG="+unread_flag;
		 	List u=DbReceivePeer.executeQuery(unread_msg);
			context.put("groupid",Integer.toString(gid)); 
			context.put("userid",Integer.toString(user_id)); 
			for(Iterator j=u.iterator();j.hasNext();)
			{
				Record item1=(Record)j.next();
				unreads=item1.getValue("UNREAD").asString();
			}		  
		   	// Count the Total messages according to userId
			String totalMsg=new String();
			if(!stats.equals("fromIndex")){
				String total_msg="SELECT COUNT(MSG_ID)TOTAL FROM DB_RECEIVE WHERE RECEIVER_ID = "+user_id+" AND GROUP_ID="+gid;
		 		List totalmsg=DbReceivePeer.executeQuery(total_msg);
			for(Iterator k=totalmsg.iterator(); k.hasNext();)
			{
				Record item=(Record)k.next();
				totalMsg=item.getValue("TOTAL").asString();
			}
			}else{
				String total_msg="SELECT COUNT(MSG_ID)TOTAL FROM DB_RECEIVE WHERE GROUP_ID="+gid;
		 		List totalmsg=DbReceivePeer.executeQuery(total_msg);
			for(Iterator k=totalmsg.iterator(); k.hasNext();)
			{
				Record item=(Record)k.next();
				totalMsg=item.getValue("TOTAL").asString();
			}
			}
			//ErrorDumpUtil.ErrorLog("mailunread=======================>>>>>>>>>>>>>>>.   "+unreads);
		   	//Adds the information to context
			context.put("unread",unreads);
			context.put("total",totalMsg);
			context.put("courseid",dir);
			context.put("cname",(String)user.getTemp("course_name"));
			 /*
                         *method for how much time user spend in this page.
                         */
			if((!dir.equals("instituteWise")) || (!dir.equals("general")) || (!dir.equals(" ")))
			{
				String Role = (String)user.getTemp("role");
				if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                        	{
					int eid=0;
					ModuleTimeThread.getController().CourseTimeSystem(user_id,eid);
                        	}
				
				///////////////////////////////////////////////
				int role_id=0;
                        if(Role.equals("instructor"))
                                role_id=2;
                        else if(Role.equals("student"))
                                role_id=3;
                        Vector unreadMsg=NoticeUnreadMsg.getUnreadNotice(user_id,role_id,dir);
                        context.put("unreadMsg",unreadMsg);
                        ErrorDumpUtil.ErrorLog("===============================unread"+unreadMsg);
				/////////////////////////////////////////////////
			}
		}//try
		catch(Exception e){data.setMessage("The error in DB screen"+e);}
    	}//method
}//class

