package com.cgit.model;

public class Resource {
    private String resourceName ;
    private int resourceId;

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getResourceName() {
	return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Resource(){

    }

    public Resource(String resourceName){
	this.resourceName = resourceName;
    }

    public String toString() {
	return "Resource [Resource Name = " + resourceName +  "]";
    }


}
