package org.nmeict.smvdu.Beans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.MessageDigest;
import java.util.Enumeration;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nmeict.smvdu.Beans.db.UserDB;
import org.nmeict.smvdu.Beans.SpringClassFile.IOrgLoginProfile;
import org.nmeict.smvdu.Beans.SpringClassFile.OrgLoginProfileService;

//import sun.misc.BASE64Encoder;


@ManagedBean(name="orgLoginDetails")
@RequestScoped
/**
 * OrgLoginDetails generated by hbm2java
 * @author Shaista Bano
 */
public class OrgLoginDetails  implements java.io.Serializable {


     private String orgId;
     private OrgProfile orgProfile;
     private String adminId;
     private String orgPassword;
     private String id;
     private IOrgLoginProfile iOrgLoginProfile = new OrgLoginProfileService();
     private boolean error;
    public OrgLoginDetails() {
    	
    }

	
    public OrgLoginDetails(String orgId, OrgProfile orgProfile, String adminId) {
        this.orgId = orgId;
        this.orgProfile = orgProfile;
        this.adminId = adminId;
    }
    public OrgLoginDetails(String orgId, OrgProfile orgProfile, String adminId, String orgPassword) {
       this.orgId = orgId;
       this.orgProfile = orgProfile;
       this.adminId = adminId;
       this.orgPassword = orgPassword;
    }
            
    public String getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public OrgProfile getOrgProfile() {
        return this.orgProfile;
    }
    
    public void setOrgProfile(OrgProfile orgProfile) {
        this.orgProfile = orgProfile;
    }
    public String getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    public String getOrgPassword() {
        return this.orgPassword;
    }
    
    public void setOrgPassword(String orgPassword) {
        this.orgPassword = orgPassword;
    }

    

    public IOrgLoginProfile getiOrgLoginProfile() {
		return iOrgLoginProfile;
	}


	public void setiOrgLoginProfile(IOrgLoginProfile iOrgLoginProfile) {
		this.iOrgLoginProfile = iOrgLoginProfile;
	}


	
	
	


	public boolean isError() {
		return error;
	}


	public void setError(boolean error) {
		this.error = error;
	}


	public String validate()
	{
		try
		{
                    /*
                        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                        HttpSession session= request.getSession();
                        Enumeration e= request.getHeaderNames();
                        
                        while (e.hasMoreElements()) {
                            System.out.print("Element=="+e.nextElement().toString());
                            //System.out.println("\n\naction=="+request.getParameter("action"));
                        }
                        //System.out.print("\n Header="+request.getHeader("cookie")+"\nconnection=="+request.getHeader("connection"));
                        //System.out.print("\n\n request.getPathInfo()=="+request.getPathInfo()+"\n==URI"+request.getRequestURI()+"\nHeaderName="+request.getHeaderNames());
                        //System.out.print("\n\n============ request=="+((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString());
                                                               
			*/
                        String page = null;
			boolean b;
			OrgProfile op = new OrgProfile();
			//String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\");
			String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
			//System.out.println("Path : "+path); 
			 BufferedImage image = null;
			synchronized (this) 
			{ 
				image = ImageIO.read(new File(path+File.separator+"img"+File.separator+"Other298.jpg"));
				 int x = new UserDB().validate(adminId, orgPassword, orgId);

                                if(x == 2){
                                    return "AdminLogin.xhtml";
                                }
                                if(x == 3){
                                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Your Registration Request is Pending, So You Are Unable To Login.", ""));
                                    return "null";
                                }
				else{ // else 1 start
					b = getiOrgLoginProfile().validateOrgUser(this);
					if(b == true) 
					{
					
						File file = new File(path+File.separator+"img"+File.separator+orgId);
//						File file1 = new File(path+File.separator+orgId);
//						if(file1.exists() == true)
	//					{
//							file1.delete();
//						}
						file.mkdir();
						
						ImageIO.write(image, "jpg",new File(path+File.separator+"img"+File.separator+orgId+File.separator+"out.png"));
						System.err.println("DDDDDDDDDDDD");
					
						op = getiOrgLoginProfile().loadAllDetails(this.getOrgId());
						this.setOrgProfile(op);
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("org",this);
						page = "MainPage.xhtml?faces-redirect=true";
					}
					else {
						FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "User/Email ID or Password Are Not Correct", ""));
					}
				} //else 1 close
			}
			
			this.setError(b);
			return page;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	
	public static synchronized String encode(String details) throws Exception 
	{
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(details.getBytes("UTF-8"));
		byte digest[] = md.digest();
                return toHexString(digest);
		//return (new BASE64Encoder()).encode(digest);
	}
	
	public static String toHexString(byte[] byteDigest)
        {
                String temp="";
                int i,len=byteDigest.length;
                for(i=0;i<len;i++)
                {
                        String byteString=Integer.toHexString(byteDigest[i]);

                        int iniIndex=byteString.length();
                        if(iniIndex==2)
                                temp=temp+byteString;
                        else if(iniIndex==1)
                                temp=temp+"0"+byteString;
                        else if(iniIndex==0)
                                temp=temp+"00";
                        else if(iniIndex>2)
                                temp=temp + byteString.substring(iniIndex-2,iniIndex);
                }
                return temp;
        }
}


