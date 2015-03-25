package com.cgit.model;

public class Task {
    private int taskId;
    private int sprintId;
    private String taskDescription;
    private String status;
    private int actualHours;
    private String priority;
    private String resourceName;
    private String acceptanceCriteria;
    private int estimatedHours;
    private String comments;

    public Task() {

    }

    public Task(int sprintId, String taskDescription, String priority, String resourceName, String acceptanceCriteria, int estimatedHours, String comments) {
	this.sprintId = sprintId;
	this.taskDescription = taskDescription;
	this.priority = priority;
	this.resourceName = resourceName;
	this.acceptanceCriteria = acceptanceCriteria;
	this. estimatedHours =  estimatedHours;
	this.comments = comments;
    }

    public int getTaskId() {
	return taskId;
    }

    public void setTaskId(int taskId) {
	this.taskId = taskId;
    }

    public int getSprintId() {
	return sprintId;
    }

    public void setSprintId(int sprintId) {
	this.sprintId = sprintId;
    }

    public String getTaskDescription() {
	return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
	this.taskDescription = taskDescription;
    }

    public String getPriority() {
	return priority;
    }

    public void setPriority(String priority) {
	this.priority = priority;
    }

    public String getResourceName() {
	return resourceName;
    }

    public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
    }

    public String getAcceptanceCriteria() {
	return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
	this.acceptanceCriteria = acceptanceCriteria;
    }

    public int getEstimatedHours() {
	return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
	this.estimatedHours = estimatedHours;
    }

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public int getActualHours() {
	return actualHours;
    }

    public void setActualHours(int actualHours) {
	this.actualHours = actualHours;
    }  

}
