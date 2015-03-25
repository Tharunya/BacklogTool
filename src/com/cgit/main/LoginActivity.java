package com.cgit.main;

import com.cgit.helper.ApplicationDbHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText username = null;
    private EditText password = null;
    private ApplicationDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.login_layout);

	username = (EditText) findViewById(R.id.enter_uname);
	password = (EditText) findViewById(R.id.password);
    }

    public void signUp(View view) {
	Intent signUpBacklogActivity = new Intent(this, SignUpBacklogActivity.class);
	startActivity(signUpBacklogActivity);
    }

    public void signIn(View view) {

	String userNameStr = getText(username);
	String passwordStr = getText(password);

	if ((userNameStr.length() >= 5) && (passwordStr.length() >= 5)) {

	    openDBCon();
	    Boolean dbCheck = mDbHelper.validateUser(userNameStr, passwordStr);
	    closeDbCon();

	    if (dbCheck) {

		Toast.makeText(getApplicationContext(), "Logging In...", Toast.LENGTH_SHORT).show();
		Intent selectBacklogActivity = new Intent(this, HomeActivity.class);
		startActivity(selectBacklogActivity);
	    } else {
		Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
	    }
	} else {
	    Toast.makeText(getApplicationContext(), "Username/Password must be atleast 6 characters!", Toast.LENGTH_SHORT).show();
	}
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
