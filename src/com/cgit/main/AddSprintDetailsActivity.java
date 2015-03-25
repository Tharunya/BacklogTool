package com.cgit.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cgit.helper.ApplicationDbHelper;
import com.cgit.model.Sprint;

public class AddSprintDetailsActivity extends BaseActivity {

    private ApplicationDbHelper mDbHelper;

    private EditText sprintName = null;
    private EditText sprintDescription = null;
    private EditText sprintStartDate = null;
    private EditText sprintEndDate = null;

    private int projectID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.add_sprint_details_layout);

	sprintName = (EditText) findViewById(R.id.enter_sprint_no);
	sprintDescription = (EditText) findViewById(R.id.enter_sprint_description);
	sprintStartDate = (EditText) findViewById(R.id.enter_start_date);
	sprintEndDate = (EditText) findViewById(R.id.enter_end_date);

	Intent myIntent = getIntent();
	projectID = myIntent.getIntExtra("projectID", 0);
    }

    public void createSprint(View view) {

	String sNameStr = getText(sprintName);
	String sDescriptionStr = getText(sprintDescription);
	String sStartDateStr = getText(sprintStartDate);
	String sEndDateStr = getText(sprintEndDate);

	if (sNameStr.length() >= 2) {

	    openDBCon();
	    Boolean dbCheck = mDbHelper.addSprint(new Sprint(projectID, sNameStr, sDescriptionStr, sStartDateStr, sEndDateStr));
	    closeDbCon();	    

	    if(dbCheck) {
		Toast.makeText(getApplicationContext(), "Added Successfully...", Toast.LENGTH_SHORT).show();

		Intent sprintActivity = new Intent(AddSprintDetailsActivity.this, SprintActivity.class);
		sprintActivity.putExtra("projectID", projectID);
		startActivity(sprintActivity);
	    } else {
		Toast.makeText(getApplicationContext(), "dbCheck false!", Toast.LENGTH_SHORT).show();
	    }
	} else {
	    Toast.makeText(getApplicationContext(), "Please provide valid data!", Toast.LENGTH_SHORT).show();
	}
    }

    public void cancelSprint(View view) {
	finish();
    }

    @Override
    protected void onStart() {
	super.onStart();
	openDBCon();
    }

    @Override
    protected void onPause() {
	super.onPause();
	finish();
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
