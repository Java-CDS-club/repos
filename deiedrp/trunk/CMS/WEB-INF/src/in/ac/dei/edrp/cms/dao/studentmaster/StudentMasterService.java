/**
 * @(#) StudentMasterService.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.dao.studentmaster;

import in.ac.dei.edrp.api.StudentMasterBeanAPI;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.*;

/**
 * The client interface for StudentMaster
 *
 * @version 1.0 24 MAR 2011
 * @author MOHD AMIR
 */
public interface StudentMasterService{
	//Add student into student master and addresses master
	Boolean addStudentMaster(StudentMasterInfoBean studentMasterInfoBean);
	//check that whether entered enrollment number exist in student master and return details if exist
	StudentMasterInfoBean checkExistanceOfEnroll(StudentMasterInfoBean input);
	//updated stduentMasterAddress
	Boolean updateStudentMaster(StudentMasterInfoBean studentMasterInfoBean);
	//check that whether entered enrollment number exist in student master or not
	Boolean checkDuplicateRollNumber(String enrollmentNumber);
	/**
	 * The method retrieves the records from student tracking
	 * and displays them on the interface
	 * @param studentBean
	 * @return List of records of the students
	 */
	List<StudentInfoGetter> getStudentTrackingRecords(StudentInfoGetter studentBean);
	
	//check that whether entered emailId(userName) exist in student master and return details if exist
	String checkExistanceOfEmailId(StudentMasterBeanAPI input);
	List<StudentMasterBeanAPI> getStudentInfo(StudentMasterBeanAPI input) throws Exception;
	List<StudentMasterBeanAPI> getContactInfo(StudentMasterBeanAPI input) throws Exception;
	String updateStudentPersonalInfo(StudentMasterBeanAPI input) throws Exception;
	String updateContactInfo(StudentMasterBeanAPI input) throws Exception;
	List<StudentMasterBeanAPI> getParentInfo(StudentMasterBeanAPI input) throws Exception;
    List<StudentMasterBeanAPI> getAcademicInfo(StudentMasterBeanAPI input) throws Exception;
	List<StudentMasterBeanAPI> getRegistrationInfo(StudentMasterBeanAPI input) throws Exception;
}
