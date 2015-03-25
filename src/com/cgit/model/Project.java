package com.cgit.model;

public class Project {

    private int projectId;
    private String projectName;
    private String projectDescription;
    private String projectStartDate;
    private String projectEndDate;
    private int sprintDuration;
    
    public Project() {
	
    }
    
    
    public Project(String projectName, String projectDescription, String projectStartDate,
	    String projectEndDate, int sprintDuration) {
	super();
	this.projectName = projectName;
	this.projectDescription = projectDescription;
	this.projectStartDate = projectStartDate;
	this.projectEndDate = projectEndDate;
	this.sprintDuration = sprintDuration;
    }


    public int getProjectId() {
        return projectId;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectDescription() {
        return projectDescription;
    }
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    public String getProjectStartDate() {
        return projectStartDate;
    }
    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
    public String getProjectEndDate() {
        return projectEndDate;
    }
    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }
    public int getSprintDuration() {
        return sprintDuration;
    }
    public void setSprintDuration(int sprintDuration) {
        this.sprintDuration = sprintDuration;
    }


    @Override
    public String toString() {
	return "Project [Project ID = " + projectId + ", Project Name = "
		+ projectName + ", Project Description = " + projectDescription
		+ ", Project Start Date = " + projectStartDate
		+ ", Project End Date = " + projectEndDate + ", Sprint Duration = "
		+ sprintDuration + "]";
    }
    
    
}
