package org.iitk.brihaspati.modules.screens.call.Backups;

/*
 * @(#)InstBackups.java	
 *
 *  Copyright (c) 2011 ETRG,IIT Kanpur. 
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
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
/**
 *
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 */

public class InstBackups extends SecureScreen
{
    public void doBuildTemplate( RunData data, Context context )
    {
	try{
		context.put("tdcolor",data.getParameters().getString("count",""));
		Criteria crit=new Criteria();
                int addnot[]={0,2};
                crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_NAME);
                crit.addNotIn(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,addnot);
                List list=InstituteAdminRegistrationPeer.doSelect(crit);
                context.put("instList",list);
           }
           catch(Exception e){data.setMessage("The error in Institute Backup !!"+e);}

    }
}
