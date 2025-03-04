/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
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
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.DAO.LoginDao;
import org.IGNOU.ePortfolio.DAO.UserProgrammeDao;
import org.IGNOU.ePortfolio.Model.Logs;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author IGNOU Team
 */
public class LoginAction extends ActionSupport implements Serializable, SessionAware {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private Map session = ActionContext.getContext().getSession();
    private String email_id, password, pwd, role, fname, lname, msg, uName, clientIP;
    private LoginDao ldao = new LoginDao();
    private List<User> userList;
    private UserProgrammeDao updao = new UserProgrammeDao();
    private String recordNotFound = getText("msg.user.notFound");
    private List<Logs> logInfo;

    @SuppressWarnings("unchecked")
    public String LoginCheck() throws IOException {
        logger.warn(this + " " + session + " Accessed from IP: " + clientIP);
        userList = updao.UserListByUserId(email_id);
        if (userList.isEmpty()) {
            logger.warn("User Name Not Found:- Entered Email Id is " + getEmail_id());
            msg = recordNotFound + "for " + getEmail_id();
            return ERROR;
        } else {
            role = userList.iterator().next().getRole();
        }
        try {
            pwd = DigestUtils.md5Hex(password);
            if (getLdao().FindUser(email_id, pwd, role)) {
                logInfo = ldao.logList(email_id, clientIP);
                session.put("user_id", getEmail_id());
                session.put("role", getRole());
                session.put("name", userList.iterator().next().getFname() + " " + userList.iterator().next().getLname());
                session.put("password", password);
                session.put("clientIP", clientIP);
                session.put("logId", logInfo.iterator().next().getLogId());
                logger.warn("user logged in " + getEmail_id() + " Access from IP: " + clientIP);
                return SUCCESS;
            } else {
                logger.warn("User Name and Password not matched:- Entered Email Id is " + getEmail_id() + " and Password is " + password);
                msg = "User Name and Password Not Matched";
                return ERROR;
            }
        } catch (Exception e) {
            logger.warn("login error:-" + e);
            return ERROR;
        }
    }

    /**
     * @return the email_id
     */
    public String getEmail_id() {
        return email_id;
    }

    /**
     * @param email_id the email_id to set
     */
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the session
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the ldao
     */
    public LoginDao getLdao() {
        return ldao;
    }

    /**
     * @param ldao the ldao to set
     */
    public void setLdao(LoginDao ldao) {
        this.ldao = ldao;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return the updao
     */
    public UserProgrammeDao getUpdao() {
        return updao;
    }

    /**
     * @param updao the updao to set
     */
    public void setUpdao(UserProgrammeDao updao) {
        this.updao = updao;
    }

    /**
     * @return the uName
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName the uName to set
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return the recordNotFound
     */
    public String getRecordNotFound() {
        return recordNotFound;
    }

    /**
     * @param recordNotFound the recordNotFound to set
     */
    public void setRecordNotFound(String recordNotFound) {
        this.recordNotFound = recordNotFound;
    }

    /**
     * @return the clientIP
     */
    public String getClientIP() {
        return clientIP;
    }

    /**
     * @param clientIP the clientIP to set
     */
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    /**
     * @return the logInfo
     */
    public List<Logs> getLogInfo() {
        return logInfo;
    }

    /**
     * @param logInfo the logInfo to set
     */
    public void setLogInfo(List<Logs> logInfo) {
        this.logInfo = logInfo;
    }
}
