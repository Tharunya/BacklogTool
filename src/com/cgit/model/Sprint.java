package com.cgit.model;

public class Sprint {

    private int sprintId;
    private int projectId;
    private String sprintName;
    private String sprintDescription;
    private String sprintStartdate;
    private String sprintEndDate;

    public Sprint() {

    }

    public Sprint(int projectId, String sprintName, String sprintDescription, String sprintStartdate, String sprintEndDate) {
	super();
	this.projectId = projectId;
	this.sprintName = sprintName;
	this.sprintDescription = sprintDescription;
	this.sprintStartdate = sprintStartdate;
	this.sprintEndDate = sprintEndDate;
    }

    public int getSprintId() {
	return sprintId;
    }

    public int getProjectId() {
	return projectId;
    }

    public void setProjectId(int projectId) {
	this.projectId = projectId;
    }

    public String getSprintName() {
	return sprintName;
    }

    public void setSprintName(String sprintName) {
	this.sprintName = sprintName;
    }

    public String getSprintDescription() {
	return sprintDescription;
    }

    public void setSprintDescription(String sprintDescription) {
	this.sprintDescription = sprintDescription;
    }

    public String getSprintStartdate() {
	return sprintStartdate;
    }

    public void setSprintStartdate(String sprintStartdate) {
	this.sprintStartdate = sprintStartdate;
    }

    public String getSprintEndDate() {
	return sprintEndDate;
    }

    public void setSprintEndDate(String sprintEndDate) {
	this.sprintEndDate = sprintEndDate;
    }

    public String toString() {
	return "Sprint [Project ID = " + projectId + "Sprint Name = " + sprintName + "  Sprint Description = " + sprintDescription
		+ "  Sprint Start Date = " + sprintStartdate + "  Sprint End Date = " + sprintEndDate + "]";
    }
}
