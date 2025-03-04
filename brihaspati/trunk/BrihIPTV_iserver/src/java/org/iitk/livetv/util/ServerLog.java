package org.iitk.livetv.util;

/*@(#)ServerLog.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2012-2013 ETRG,IIT Kanpur. 
 */

import java.util.Date;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;
import java.io.DataOutputStream;
import javax.servlet.ServletContext;

/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class ServerLog {

	private static ServerLog log=null;
	private static File existingFile =null;
        private static DataOutputStream dos = null;
	private ServletContext context=null;
	FileOutputStream flog=null;
	
	/**
	 * ServerLog controller 
	 */
	public static ServerLog getController(){
                if (log==null){
                        log=new ServerLog();
                }
                return log;
        }
	
	public void setContext(ServletContext context1) throws Exception {
		context=context1;
		existingFile=new File(context.getRealPath("logs/ServerLog.txt"));
		dos = new DataOutputStream(new FileOutputStream(existingFile,true));
		flog=new FileOutputStream(existingFile,true);
	}
	
	private void createFile(){
		try {
                        dos = new DataOutputStream(new FileOutputStream(existingFile,true));
		}catch(Exception e){ }	
	}

	
        /**
        * In this method, Dump error message in logfile
        * @param msg String
        * @return
        */
        public void Log(String msg)
        {
                try
                {
			if(!(existingFile.isFile()) ||(existingFile == null) ){
				createFile();
			}
			Date Errordate=new Date();
			flog=new FileOutputStream(existingFile,true);
	        	flog.write((Errordate+"---"+msg+"\n").getBytes());
			flog.close();                        
                }catch(Exception e) { }
        }
}
 
