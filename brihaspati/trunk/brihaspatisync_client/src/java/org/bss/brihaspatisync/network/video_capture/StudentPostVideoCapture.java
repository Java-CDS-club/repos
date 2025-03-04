package org.bss.brihaspatisync.network.video_capture;

/**
 * StudentPostVideoCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012,2013  ETRG, IIT Kanpur.
 */

import javax.imageio.ImageIO;
import javax.imageio.IIOImage;
import javax.imageio.ImageWriter;
import javax.imageio.ImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import java.util.Iterator;
import java.util.LinkedList;

import java.awt.image.BufferedImage;

import org.bss.brihaspatisync.gui.StatusPanel;
import org.bss.brihaspatisync.gui.VideoPanel;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 * @author <a href="mailto: pradeepmca30@gmail.com" > Pradeep Kumar Pal</a>
 */

public class StudentPostVideoCapture implements Runnable {
	
	private Thread runner=null;
	private boolean flag=false;
	private boolean getflag=false;
	private static StudentPostVideoCapture post_capture=null;
	private UtilObject utilobject=UtilObject.getController();
	private java.io.ByteArrayOutputStream os=new java.io.ByteArrayOutputStream();

	/**
 	 * Controller for the class.
 	 */ 
	public static StudentPostVideoCapture getController(){
		if(post_capture==null)
			post_capture=new StudentPostVideoCapture();
		return post_capture;
	}

	public StudentPostVideoCapture(){}

	/**
 	 * Start Thread
 	 */  
	public void startStdVideoCapture(boolean getscreen){
                if (runner == null) {
			flag=true;
			getflag=getscreen;
                        runner = new Thread(this);
                        runner.start();
			VideoPanel.getController().addStudentPanel();
			utilobject.addType("stud_video");
			System.out.println("Student Post Video Capture  start successfully !!");
		}
        }

        /**
         * Stop Thread.
         */
        
	public void stopStdVideoCapture() {
                if (runner != null) {
			flag=false;	
			getflag=false;
                        runner = null;
			VideoPanel.getController().removeStudentPanel();
			utilobject.removeType("stud_video");
			utilobject.removeReceiveQueue("stud_video");
                        utilobject.removeSendQueue("stud_video");
			System.out.println("Student Post Video Capture  stop successfully !!");
                }
        }

	public void run() {
		while( flag && ThreadController.getThreadFlag()) {
			try {
				if(ThreadController.getReflectorStatusThreadFlag()) {
					/****   send student video image to reflector ****/
					if(!getflag) {	
						if(BufferImage.getController().bufferSize()>0) {
							BufferedImage image=BufferImage.getController().get(0);
							BufferImage.getController().remove();
							encode(image);	
							LinkedList send_queue=utilobject.getSendQueue("stud_video");
                                                        if(send_queue.size()==0 ){
                                                                send_queue.addLast(os.toByteArray());
                                                        }else {
                                                                int k=compare(os.toByteArray(),(byte[])send_queue.get((send_queue.size())-1));
                                                                if(k!=0)
                                                                        send_queue.addLast(os.toByteArray());
                                                        }
							os.flush();
							os.reset();
						}	
					}else {
						/****   receive student video image from reflector ****/
                        	                LinkedList desktop_queue=utilobject.getReceiveQueue("stud_video");
                                	        if(desktop_queue.size()>0) {
                                        	        byte[] bytes1=(byte[])desktop_queue.remove();
		                                        BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(bytes1));
                	                        	if(image!=null)
                        	                        	org.bss.brihaspatisync.gui.VideoPanel.getController().runStudentVidio(image);
						}
                              		}
				}
                       		runner.yield(); runner.sleep(3000);
			}catch(Exception e){System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());}
		}
		try {
                        stopStdVideoCapture();
                }catch(Exception e){}
	}
	
	/**
	 * This method is used to differeciate between two images .
	 **/
	public int compare(byte[] left, byte[] right) {
                for (int i = 0, j = 0; i < left.length && j < right.length; i++, j++) {
                        int a = (left[i] & 0xff);
                        int b = (right[j] & 0xff);
                        if (a != b) {
                                return a - b;
                        }
                }
                return left.length - right.length;
        }
	
	private void encode(BufferedImage image) {
                try {
                        // get all image writers for JPG format
                        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
                        ImageWriter writer = (ImageWriter) writers.next();
                        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
                        writer.setOutput(ios);
                        ImageWriteParam param = writer.getDefaultWriteParam();
                        // compress to a given quality
                        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                        param.setCompressionQuality(ClientObject.getStdImageQuality());
                        // appends a complete image stream containing a single image and
                        // associated stream and image metadata and thumbnails to the output
                        writer.write(null, new IIOImage(image, null, null), param);
                        // close all streams
                        os.close();
                        ios.close();
                        writer.dispose();
                } catch(Exception e) { System.out.println("Exception in StudentPostVideoCapture class at encode method  ");  }
        }
}
