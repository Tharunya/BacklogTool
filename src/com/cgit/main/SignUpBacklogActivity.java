package com.cgit.main;

import com.cgit.helper.ApplicationDbHelper;
import com.cgit.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpBacklogActivity extends BaseActivity {

    private EditText firstName = null;
    private EditText lastName = null;
    private EditText userName = null;
    private EditText password = null;
    private EditText confirmPassword = null;
    private EditText emailID = null;
    private Boolean isValid;

    private ApplicationDbHelper mDbHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.signup_backlog_layout);

	firstName = (EditText) findViewById(R.id.enter_fname);
	firstName.addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		IsValidPersonName(firstName);
	    }
	});
	// ===============================================================================================

	lastName = (EditText) findViewById(R.id.enter_lname);
	lastName.addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		IsValidPersonName(lastName);
	    }
	});
	// ===============================================================================================

	userName = (EditText) findViewById(R.id.enter_uname);
	userName.addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		IsValidUserName(userName); 
	    }
	});
	// ===============================================================================================

	password = (EditText) findViewById(R.id.enter_pwd);
	password.addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		IsValidUserName(password);
	    }
	});
	// ===============================================================================================

	confirmPassword = (EditText) findViewById(R.id.enter_confirm_pwd);
	confirmPassword.addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		isPasswordSame(confirmPassword);
	    }
	});
	// ===============================================================================================

	emailID = (EditText) findViewById(R.id.enter_email_id);
	emailID.addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		IsValidEmail(emailID); 
	    }
	});
    }
    // ===============================================================================================

    @Override
    protected void onPause() {
	super.onPause();
	finish();
    }

    public void signUpSubmit(View view) {

	isValid = true;

	IsValidPersonName(firstName);
	IsValidPersonName(lastName);
	IsValidUserName(userName);
	IsValidUserName(password);
	isPasswordSame(confirmPassword);
	IsValidEmail(emailID);

	if (isValid) {

	    String fName = getText(firstName);
	    String lName = getText(lastName);
	    String uName = getText(userName);
	    String pwd = getText(password);
	    String cPwd = getText(confirmPassword);
	    String eMail = getText(emailID);

	    openDBCon();

	    Boolean dbCheck = mDbHelper.addUser(new User(fName, lName, uName, pwd, cPwd, eMail));

	    closeDbCon();

	    if(dbCheck) {
		Toast.makeText(getApplicationContext(), "Registration Completed...", Toast.LENGTH_SHORT).show();
		Intent mainActivity = new Intent(this, LoginActivity.class);
		startActivity(mainActivity);
	    } else {
		Toast.makeText(getApplicationContext(), "User already exists...", Toast.LENGTH_SHORT).show();
	    }
	}
    }

    public void signUpCancel(View view) {
	finish();
    }

    // ===============================================================================================

    public void isPasswordSame(EditText edt) {
	if (!getText(edt).equals(getText(password))) {
	    confirmPassword.setError("Password Must Match!");
	    isValid = false;
	}
    }

    public void IsValidPersonName(EditText edt) throws NumberFormatException {
	if (getText(edt).length() <= 2) {
	    edt.setError("Must be atleast 3 characters!");
	    isValid = false;
	} else if (!getText(edt).matches("[a-zA-Z ]+")) {
	    edt.setError("Enter Alphabets Only!");
	    isValid = false;
	}
    }

    public void IsValidUserName(EditText edt) throws NumberFormatException {
	if (getText(edt).length() <= 5) {
	    edt.setError("Must be atleast 6 characters!");
	    isValid = false;
	} else if (getText(edt).length() <= 0) {
	    edt.setError("Mandatory Field!");
	    isValid = false;
	}
    }

    public void IsValidEmail(EditText edt) {
	if (getText(edt) == null) {
	    edt.setError("Mandatory Field!");
	    isValid = false;
	} else if (isEmailValid(getText(edt)) == false || getText(edt).length() <= 11) {
	    edt.setError("Invalid Email Address!");
	    isValid = false;
	}
    }

    boolean isEmailValid(CharSequence email) {
	return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onStart() {
	super.onStart();
	openDBCon();
    }

    @Override
    protected void onStop() {
	super.onStop();
	closeDbCon();
    }

    protected void openDBCon() {
	if (mDbHelper == null) {
	    mDbHelper = new ApplicationDbHelper(this);
	    mDbHelper.open();
	} else if (!mDbHelper.isOpen()) {
	    mDbHelper.open();
	}
    }

    protected void closeDbCon() {
	if (mDbHelper != null && mDbHelper.isOpen()) {
	    mDbHelper.close();
	}
    }
}