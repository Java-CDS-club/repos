package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA
/**
 *
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2016
 */ 

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import utils.ExceptionLogUtil;

public class Edrpusers  implements java.io.Serializable {


	private Integer edrpuId;
	private Erpmusers erpmusers;
	private String edrpuName;
	private String edrpuPassword;
	private String edrpuEmail;
	private String componentreg;
	private String mobile;
	private String status;

	public Edrpusers() {
	}

	public Edrpusers(String erpmuName, String erpmuPassword, String edrpuEmail) {
        	this.edrpuName = edrpuName;
        	this.edrpuPassword = edrpuPassword;
        	this.edrpuEmail = edrpuEmail;
    	}
	public Edrpusers(Erpmusers erpmusers, String edrpuName, String edrpuPassword, String componentreg, String mobile, String status) {
		this.erpmusers = erpmusers;
       		this.edrpuName = edrpuName;
		this.edrpuPassword = edrpuPassword;
		this.componentreg = componentreg;
		this.mobile = mobile;
		this.status = status;
    }
   
    public Integer getEdrpuId() {
        return this.edrpuId;
    }
    public void setEdrpuId(Integer edrpuId) {
        this.edrpuId = edrpuId;
    }
    public String getEdrpuName() {
        return this.edrpuName;
    }
    
    public void setEdrpuName(String edrpuName) {
        this.edrpuName = edrpuName;
    }
    public String getEdrpuPassword() {
        return this.edrpuPassword;
    }
    
    public void setEdrpuPassword(String edrpuPassword) {
        this.edrpuPassword = edrpuPassword;
    }
    public String getEdrpuEmail() {
        return this.edrpuEmail;
    }
    
    public void setEdrpuEmail(String edrpuEmail) {
        this.edrpuEmail = edrpuEmail;
    }

    public String getComponentreg() {
        return this.componentreg;
    }
    
    public void setComponentreg(String componentreg) {
        this.componentreg = componentreg;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}


