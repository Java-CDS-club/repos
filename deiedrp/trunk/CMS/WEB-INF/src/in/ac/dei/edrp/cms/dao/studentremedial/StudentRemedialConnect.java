/*
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
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

package in.ac.dei.edrp.cms.dao.studentremedial;

/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/



import in.ac.dei.edrp.cms.domain.studentremedial.StudentRemedialInfoGetter;

/**
 * This interface consist the list of methods used
 * in StudentRemedialImpl file.
 * @author Rohit
 * @date 7 April 2011
 * @version 1.0
 */

import java.util.List;

public interface StudentRemedialConnect {
	
	
    /**
	* Method to populate student's Current details to show on Remedial interface
	* @param input contains university code and roll number of student who login
	* @return list contains student's current details
	*/
	List<StudentRemedialInfoGetter> getStudentDetails(StudentRemedialInfoGetter input) throws Exception;
	
    /**
	* Method to populate student's Remedial details to show on remedials interface
	* @param input contains roll number,program of student
	* @return list contains student's Remedial details
	*/
	
	List<StudentRemedialInfoGetter> getRemedialDetails(StudentRemedialInfoGetter input) throws Exception;
	
    /**
     * Method for inserting student Remedial details into database for which student applying to take remedial exam
     * @param input contains progDetails contains remedial's program_course_key  of student
     * @param input contains courseDetails contains course_code,course_status of course applied by student
     * @return string
     * @throws Exception
     */
	String insertRemedialDetails(StudentRemedialInfoGetter input) throws Exception;
	
}
