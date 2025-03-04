package org.iitk.brihaspati.modules.utils.security;
/*
 * @(#)RemoteAuthProperties.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
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

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * This class methods set and return the value of properties 
 * file for configuration parameter.
 *  @author <a href="mailto:chitvesh@yahoo.com">Chitvesh Dutta</a>
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar singh</a>
 */
public class RemoteAuthProperties{
	/**
	 * This method returns the value of configuration parameter
	 * in Properties file
	 * @param path String
	 * @param key String
	 * @return retval String 
	 * @exception a generic exception
	 */
	
	public static String getValue(String path,String key) throws Exception{
	 	InputStream f = new FileInputStream(path);
 		Properties p = new Properties();
		 p.load(f);
		 String val = p.getProperty(key);
	//	 int retval =  Integer.parseInt(val);	
	//	 return(retval);
		 return(val);
	 }
	
	/**
	 * This method sets the value of configuration parameters 
	 * in Properties file
	 * @param path String
	 * @param Value String
	 * @param key String
	 * @exception a generic exception
	 */

	public static void setValue(String path,String Value,String key) throws Exception{
		OutputStream os = new FileOutputStream(path,true);		
		Properties p = new Properties();
		p.setProperty(key,Value);
		p.store(os,"header");
	}
}
