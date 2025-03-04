package org.iitk.brihaspati.modules.utils.security;

/*(#)RandPasswordUtil.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */

import java.util.Random;
import java.util.Properties;

/**
 * This is a util class used to getting the random password
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */
public class RandPasswordUtil{
	
	/**
 	 * Method to generate eight byte random number
 	 */
	public static String randmPass(){
		byte[] pass=new byte[8];

               /** 
               * Random is a function for 
               * generating random numbers. 
               */

               	Random rnd=new Random();
               	for(int i=0;i<8;i++)
               	{
  	        	int inPass=rnd.nextInt(26);
                        int p1;
                        if((inPass%2)==0)
                     	{
                                p1=inPass%10;
                                pass[i]=(byte)(p1+48);
                        }
                        else
                        {
                        	pass[i]=(byte)(inPass+97);
                        }
                }
		String password=new String(pass);
		return password;
	}

	}
