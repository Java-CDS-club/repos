package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)AnnounceExam_Manage.java	
 *
 *  Copyright (c) 2010, 2013 MHRD, DEI Agra, IITK. 
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
 *  Contributors: Members of MHRD, DEI Agra. 
 * 
 */

//Jdk
import java.io.File;
import java.util.Vector;
import java.util.Calendar;
//Turbine
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//brihaspati
import org.iitk.brihaspati.om.Quiz;
import org.iitk.brihaspati.om.QuizPeer;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.YearListUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;

/**
* This class announce a quiz
* @author <a href="mailto:aayushi.sr@gmail.com">Aayushi Sr</a>
*/

public class Announce_Exam extends SecureScreen{
	public void doBuildTemplate(RunData data,Context context){
	/**
        *Retrieve the Parameters by using the Parameter Parser
        *Get the UserName and put it in the context
        *for template use
        */
        ParameterParser pp=data.getParameters();
	String LangFile=data.getUser().getTemp("LangFile").toString();
        try{
        	User user=data.getUser();
        	String courseid=(String)user.getTemp("course_id");   
        	String username=user.getName();
        	String mode = pp.getString("mode","");
        	String quizID = pp.getString("quizID","");
        	context.put("quizID",quizID);
        	String quizName = pp.getString("quizName","");
        	String maxTime = pp.getString("maxTime","");
        	String maxMarks = pp.getString("maxMarks","");
        	String noQuestions = pp.getString("noQuestions","");
        	String creationDate = pp.getString("creationDate","");
        	String type="";
           	String check = "y";
           	String filePath1=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID);
          	String quizSettingPath=quizID+"_QuestionSetting.xml";
		File file1=new File(filePath1+"/"+quizSettingPath);
           	QuizMetaDataXmlReader quizmetadata1=null;
		Vector collect=new Vector();
		boolean flag=false;
		if(file1.exists()){
			quizmetadata1=new QuizMetaDataXmlReader(filePath1+"/"+quizSettingPath);	
			collect=quizmetadata1.getQuizQuestionDetail(quizID);		
			if(collect!=null && collect.size()!=0){
				for(int i=0;i<collect.size();i++){
					type=((QuizFileEntry)collect.elementAt(i)).getQuestionType();
						if((type.equals("sat")) ||(type.equals("lat"))){
					 	   check = "n";
					 	   flag=true;
					 	   break;
						}
				}
				if(check.equals("y")){
					context.put("setVisible","hidden");
					context.put("flag",flag);
				}
				else{
					context.put("setVisible","visible");
				}		

			}
		}
		
        	if(quizName!="")
        		context.put("quizName",quizName);
        	if(maxTime!="")
        		context.put("maxTime",maxTime);
        	if(maxMarks!="")
        		context.put("maxMarks",maxMarks);
        	if(noQuestions!="")
        		context.put("noQuestions",noQuestions);
        	if(creationDate!="")
        		context.put("creationDate",creationDate);
        	        	
        	if(mode.equals("new")){
        		context.put("mode",mode);
        		context.put("tdcolor","1");
        	}
        	if(mode.equals("update")){
        		context.put("mode",mode);
        		context.put("tdcolor","2");
        		String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
                	String quizPath="/Quiz.xml";       
                	String startDate = "",startTime = "",endDate = "",endTime = "",allowPractice = "",resDate = "";
                	File file=new File(filePath+"/"+quizPath);
    			Vector quizDetail=new Vector();
    			QuizMetaDataXmlReader quizmetadata=null;
    			if(file.exists()){
    				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
    				quizDetail=quizmetadata.getQuiz_Detail(quizID);
    				if(quizDetail!=null){
    					if(quizDetail.size()!=0){

    						for(int i = 0; i<quizDetail.size(); i++){
    							startDate = ((QuizFileEntry) quizDetail.elementAt(i)).getExamDate();
    							startTime = ((QuizFileEntry) quizDetail.elementAt(i)).getStartTime();
    							endDate = ((QuizFileEntry) quizDetail.elementAt(i)).getExpiryDate();
    							endTime = ((QuizFileEntry) quizDetail.elementAt(i)).getEndTime();
    							allowPractice = ((QuizFileEntry) quizDetail.elementAt(i)).getAllowPractice();
							if(check.equals("n")){
								resDate = ((QuizFileEntry) quizDetail.elementAt(i)).getResDate();
							}
							else{
								resDate = null;
							}
    						}    						         
    					}
    				}
    			}
    			String[] temp = startDate.split("-");
			context.put("Start_year",temp[0]);
			context.put("Start_month",temp[1]);
			context.put("Start_day",temp[2]);
				
			String[] temp1 = startTime.split(":");
			context.put("Start_hr",temp1[0]);
			context.put("Start_min",temp1[1]);
			
			String[] temp2 = endDate.split("-");
			context.put("End_year",temp2[0]);
			context.put("End_month",temp2[1]);
			context.put("End_day",temp2[2]);
				
			String[] temp3 = endTime.split(":");
			context.put("End_hr",temp3[0]);
			context.put("End_min",temp3[1]);
			context.put("allowPractice",allowPractice);  
			if(check.equals("n")){	
				String[] temp4 = resDate.split("-");
				context.put("Res_year",temp4[0]);
				context.put("Res_month",temp4[1]);
				context.put("Res_day",temp4[2]);
			}
        	}
        
        	context.put("course",(String)user.getTemp("course_name"));
        	String currentdate=ExpiryUtil.getCurrentDate("-");
        	String[] temp4 = currentdate.split("-");
        	String month = temp4[1];
        	context.put("cmonth",month);
        	String date = temp4[2];
        	context.put("cday",date);
        	String strdatetype=currentdate.replaceAll("-","");
            	int currentdate1=Integer.parseInt(strdatetype);
            	int cyear1=currentdate1/10000;
            	String cyear=Integer.toString(cyear1);
            	context.put("year",cyear);
            	Vector year=YearListUtil.getYearList();
            	context.put("year_list",year);
            	Calendar cld = Calendar.getInstance();
        	//cld.clear();
        	cld.add(Calendar.DAY_OF_MONTH, 30);
        	cld.add(Calendar.MINUTE, 5);
        	String eYear = Integer.toString(cld.get(cld.YEAR));
        	int eMon = (cld.get(cld.MONTH))+1;
        	String eMonth = Integer.toString(eMon);
        	int eDat = cld.get(cld.DAY_OF_MONTH);
        	String eDate = Integer.toString(eDat);
        	int eHou = cld.get(cld.HOUR_OF_DAY);
        	String eHour = Integer.toString(eHou);
        	int eMin = cld.get(cld.MINUTE);
        	String eMinute = Integer.toString(eMin);
        	if(eMon<10)
        		eMonth="0"+eMonth;
			if(eDat<10)
				eDate="0"+eDate;
			if(eHou<10)
				eHour="0"+eHour;
			if(eMin<10)
				eMinute="0"+eMinute;
        	context.put("eYear",eYear);
        	context.put("eMonth",eMonth);
        	context.put("eDate",eDate);
        	context.put("eHour",eHour);
        	context.put("eMinute",eMinute);
		/**
                  *Time calculaion for how long user use this page.
                  */
		String Role = (String)user.getTemp("role");
		int uid=UserUtil.getUID(user.getName());
                if((Role.equals("student")) || (Role.equals("instructor")))
                {
                	//CourseTimeUtil.getCalculation(uid);
                	//ModuleTimeUtil.getModuleCalculation(uid);
			int eid=0;
			ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                }

        }catch(Exception e) {
        	ErrorDumpUtil.ErrorLog("The exception in Announce_Exam screen::"+e);
		data.setMessage(MultilingualUtil.ConvertedString("brih_exception",LangFile));
        }
    }
}
