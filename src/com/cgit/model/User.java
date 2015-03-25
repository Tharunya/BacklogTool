package com.cgit.model;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private String emailID;

    public User() {

    }

    public User(String firstName, String lastName, String userName, String password, String confirmPassword, String emailID) {
	super();

	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.password = password;
	this.confirmPassword = confirmPassword;
	this.emailID = emailID;
    }

    public String getFirstName() {
	return firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public String getUserName() {
	return userName;
    }

    public String getPassword() {
	return password;
    }

    public String getConfirmPassword() {
	return confirmPassword;
    }

    public String getEmailID() {
	return emailID;
    }

    public String toString() {
	return "User [First Name = " + firstName + "  Last Name = " + lastName
		+ "  User Name = " + userName + "  EMail ID = " + emailID + "]";
    }
}