package com.cgit.util;

import android.widget.EditText;

public class InputFieldsValidation {

    Boolean isValid = true;    
    
    public Boolean IsValidPersonName(EditText edt) throws NumberFormatException {
	if (edt.getText().toString().length() <= 2) {
	    edt.setError("Must be atleast 3 characters!");
	    isValid = false;
	} else if (!edt.getText().toString().matches("[a-zA-Z ]+")) {
	    edt.setError("Enter Alphabets Only!");
	    isValid = false;
	}
	return isValid;
    }
    
    public Boolean IsValidUserName(EditText edt) throws NumberFormatException {
	if (edt.getText().toString().length() <= 5) {
	    edt.setError("Must be atleast 6 characters!");
	    isValid = false;
	} else if (edt.getText().toString().length() <= 0) {
	    edt.setError("Mandatory Field!");
	    isValid = false;
	}
	return isValid;
    }
    
    public Boolean IsValidEmail(EditText edt) {
	if (edt.getText().toString() == null) {
	    edt.setError("Mandatory Field!");
	    isValid = false;
	} else if (isEmailValid(edt.getText().toString()) == false || edt.getText().toString().length() <= 12) {
	    edt.setError("Invalid Email Address!");
	    isValid = false;
	}
	return isValid;
    }
    
    boolean isEmailValid(CharSequence email) {
	return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
