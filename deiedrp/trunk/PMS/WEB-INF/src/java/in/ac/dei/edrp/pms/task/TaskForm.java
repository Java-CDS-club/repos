package in.ac.dei.edrp.pms.task;

/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 05-May-2010
 * 
 * XDoclet definition:
 * @struts.form name="taskform"
 */
public class TaskForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	private static final long serialVersionUID = 1L;

	/** fields property */
	private String projectName;//for holding project name
	private String taskName;//for holding task name
	private String taskname2;
	private String assignedTo;//for holding task user name
	private int no_of_days;//for holding number of days
	private String taskStartDate;//for holding schedule start date of a task
	private String taskEndDate;//for holding schedule end date of a task
	private String gantt_chart_color;//for holding gantt chart colour of a task
	private String taskDependency;//for holding the task dependency
	private String remark;//for holding description of a task
	private String projSStartDate;
	private String projSEndDate;
	
	/*
	 * Generated Methods
	 */

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.assignedTo="--Select--";
		this.projectName="";
	}

	public String getTaskname2() {
		return taskname2;
	}

	public void setTaskname2(String taskname2) {
		this.taskname2 = taskname2;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public String getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public String getGantt_chart_color() {
		return gantt_chart_color;
	}

	public void setGantt_chart_color(String gantt_chart_color) {
		this.gantt_chart_color = gantt_chart_color;
	}

	public String getTaskDependency() {
		return taskDependency;
	}

	public void setTaskDependency(String taskDependency) {
		this.taskDependency = taskDependency;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProjSStartDate() {
		return projSStartDate;
	}

	public void setProjSStartDate(String projSStartDate) {
		this.projSStartDate = projSStartDate;
	}

	public String getProjSEndDate() {
		return projSEndDate;
	}

	public void setProjSEndDate(String projSEndDate) {
		this.projSEndDate = projSEndDate;
	}

	public int getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}

}