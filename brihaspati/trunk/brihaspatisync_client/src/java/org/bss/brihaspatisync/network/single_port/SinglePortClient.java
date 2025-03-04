package org.bss.brihaspatisync.network.singleport;

/**
 * SinglePortClient.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013,2015 ETRG, IIT Kanpur.
 */

import  java.util.Vector;
import  java.util.LinkedList;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.network.util.UtilObject;
import org.bss.brihaspatisync.tools.audio.AudioClient;
import org.bss.brihaspatisync.gui.HandRaiseThreadController;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: pradeepmca30@gmail.com" > Pradeep Kumar Pal </a>
 */

public class SinglePortClient implements Runnable {
	
	private Thread runner=null;
	private HttpClient client = null;	
	private UtilObject utilobject=UtilObject.getController();
	private int port=RuntimeDataObject.getController().client_single_port();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();

	private static SinglePortClient post_screen=null;
	
	/**
 	 * Controller for the class.
 	 */ 
	public static SinglePortClient getController(){
		if(post_screen==null)
			post_screen=new SinglePortClient();
		return post_screen;
	}

	public SinglePortClient(){ }
	
	/**
 	 * Start Thread
 	 */  
	public void start(){
                if (runner == null) {
                        runner = new Thread(this);
                        runner.start();
			System.out.println("Single Port Client start successfully !!");
		}
        }

	public void stop() {
		if (runner != null) {
                        //runner = null;
                       	System.out.println("Single Port Client stop successfully !!");
                }
        }
	
	/**
	 * This method is used to get data from Send Queue .
	 * and rechive data from reflector and put data to rechive queue .
	 */

	public void run() {
		Vector v=new Vector();
		while(ThreadController.getThreadFlag()) {
			try {
				if(ThreadController.getReflectorStatusThreadFlag()) {
					v.clear(); v.addAll(utilobject.getTypeVector());	
					while((v.size()) !=0){
						String type=v.get(0).toString(); v.remove(0);
						if(type.equals("Chat_Wb_Data")) {
                                        	        try {
                                                	        LinkedList sendqueue=utilobject.getSendQueue("Chat_Wb_Data");
                                                        	byte[] send_data=null;
	                                                        if(sendqueue.size()>0) 
        	                                                        send_data=(byte[])sendqueue.remove();
                	                                        byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
                        	                                if(receive_data_fromserver.length>0 && (receive_data_fromserver !=null) ) {
                                	                        	LinkedList chat_wb=utilobject.getReceiveQueue("Chat_Wb_Data");
                                        	                        chat_wb.addLast(receive_data_fromserver);
                                                	        }
                                                        } catch(Exception e) { System.out.println("Exception in http Client in Audio_Data "+e);}
	                                        } else if(type.equals("UserList_Data")) {
							try {
                	                                        LinkedList sendqueue=utilobject.getSendQueue("UserList_Data");
                        	                                byte[] send_data=null;
                                	                        if(sendqueue.size()>0)  
                                        	                        send_data=(byte[])sendqueue.remove();
                                                	        byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
                                                        	if(receive_data_fromserver.length>0 && (receive_data_fromserver !=null) ) {
                                                        		LinkedList chat_wb=utilobject.getReceiveQueue("UserList_Data");
	                                                                chat_wb.addLast(receive_data_fromserver);
        	                                        	}
                	                                } catch(Exception e) { System.out.println("Exception in http Client in Audio_Data "+e);}
						} else if(type.equals("Audio_Data")) {	
						    	try {	
						        	LinkedList sendqueue=utilobject.getSendQueue("Audio_Data");
							   	byte[] send_data=null;
						   		if(sendqueue.size()>0)
							   		send_data=(byte[])sendqueue.remove();
							   	byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
								if(receive_data_fromserver.length>36 && (receive_data_fromserver !=null) ) {
									LinkedList audio_queue=utilobject.getReceiveQueue("Audio_Data");
        	                	               	       		audio_queue.addLast(receive_data_fromserver);
							   	} 
					    		} catch(Exception e) { System.out.println("Exception in http Client in Audio_Data "+e);}
						
						} else if(type.equals("Student_Audio_Data")) {
                                                        try {
                                                                LinkedList sendqueue=utilobject.getSendQueue("Student_Audio_Data");
                                                                byte[] send_data=null;
                                                                if(sendqueue.size()>0)
                                                                        send_data=(byte[])sendqueue.remove();
                                                                //if((AudioClient.getController().getpostAudio())&&(!((ClientObject.getUserRole()).equals("instructor"))) )
                                                                  //      type ="Student_"+type;
                                                                byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
                                                                if(receive_data_fromserver.length>36 && (receive_data_fromserver !=null) ) {
                                                                        LinkedList audio_queue=utilobject.getReceiveQueue("Audio_Data");
                                                                        audio_queue.addLast(receive_data_fromserver);
                                                                }
                                                        } catch(Exception e) { System.out.println("Exception in http Client in Student_Audio_Data "+e);}

						} else if(type.equals("Desktop_Data")) {
							try {
								long l=System.currentTimeMillis();
								LinkedList sendqueue=utilobject.getSendQueue("Desktop_Data");
								byte[] send_data=null;
								if(sendqueue.size() >1 ) 
	                                        	                send_data=(byte[])sendqueue.remove();
                       		                        	byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
								if(receive_data_fromserver.length>0 && (receive_data_fromserver !=null) ) {
		                               		        	LinkedList audio_queue=utilobject.getReceiveQueue("Desktop_Data");
									audio_queue.addLast(receive_data_fromserver);
								}
								long cur_time=System.currentTimeMillis()-l;
	                                	                NetworkController.Hashtable(type,cur_time);
							}catch(Exception e){ System.out.println("Exception in http Client in Desktop_Data "+e); }
						}else if(type.equals("ins_video")) {
							try {
								long l=System.currentTimeMillis();
								LinkedList sendqueue=utilobject.getSendQueue("ins_video");
								byte[] send_data=null;
								if(sendqueue.size() >1 ) 
	                                        	                send_data=(byte[])sendqueue.remove();
                       	                                	byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
								if(receive_data_fromserver.length>0 && (receive_data_fromserver !=null) ) {
        	       	        	                        	LinkedList audio_queue=utilobject.getReceiveQueue("ins_video");
                	       	        	                	audio_queue.addLast(receive_data_fromserver);
								}
								long cur_time=System.currentTimeMillis()-l;
                                        	                NetworkController.Hashtable(type,cur_time);
							}catch(Exception e) { System.out.println("Exception in SinglePortClient in instructor video "+e.getMessage());}
						} else if(type.equals("stud_video")) {
							try {
								long l=System.currentTimeMillis();
								LinkedList sendqueue=utilobject.getSendQueue("stud_video");
								byte[] send_data=null;
								if(sendqueue.size() >1 ) 
	                                        	                send_data=(byte[])sendqueue.remove();
                 						byte[] receive_data_fromserver=sendDataToReflector(send_data,type);		
								if(receive_data_fromserver.length>0 && (receive_data_fromserver !=null) ) {
		                       	                        	LinkedList audio_queue=utilobject.getReceiveQueue("stud_video");
        		                       	                        audio_queue.addLast(receive_data_fromserver);
								}	
								long cur_time=System.currentTimeMillis()-l;
                                        	                NetworkController.Hashtable(type,cur_time);
							} catch(Exception e){ System.out.println("Exception in SinglePortClient in Student video "+e.getMessage());}
						}
					}
					org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("yes");
				} else
					org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("no");
				runner.yield();
				//runner.sleep(10);
				System.gc();
			}catch(Exception ep) { 
				System.out.println(this.getClass()+" Exception  "+ep.getMessage()); 
				org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("no");
			}
		}
		try {
                        runner = null;
			org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("no");
                }catch(Exception e){}
	}
	
	/**
	 * This method is used to send the data client to reflector .
	 */
	private byte[] sendDataToReflector(byte[] send_data,String type) {
		try {
                        PostMethod postMethod = new PostMethod("http://"+ClientObject.getReflectorIP()+":"+port);
			if(send_data != null)	
				postMethod.setRequestBody(new java.io.ByteArrayInputStream(send_data));
                        postMethod.setRequestHeader("session",ClientObject.getLectureID()+","+ClientObject.getUserName()+","+type);
			//if(client == null ) {
                                client = new HttpClient();
                                client.setConnectionTimeout(800000);
				// Http Proxy Handler
				if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
        	                	HostConfiguration config = client.getHostConfiguration();
                	                config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                        	        Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                	AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
	                                client.getState().setProxyCredentials(authScope, credentials);
        	            	}
			//}
			int statusCode = client.executeMethod(postMethod);
			byte[] receive_data_fromserver=postMethod.getResponseBody();
                        postMethod.releaseConnection();
			org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("yes");	
			ThreadController.setReflectorStatusThreadFlag(true);
			return receive_data_fromserver;
		}catch(Exception e) { 
			client=null;
			org.bss.brihaspatisync.gui.StatusPanel.getController().setStatus("Reflector Stopped Abruptly");
			org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("no");
			ThreadController.setReflectorStatusThreadFlag(false);
			System.out.println(this.getClass()+" in Send data from client to reflector "+e.getMessage());
		} 
		return null;
	}
}
