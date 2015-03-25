package com.cgit.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cgit.helper.ApplicationDbHelper;
import com.cgit.model.Task;

public class AddTaskDetailsActivity extends BaseActivity {

    private ApplicationDbHelper mDbHelper;
    private EditText taskDescription = null;
    private EditText priority = null;
    private EditText resource = null;
    private EditText acceptanceCriteria = null;
    private EditText estimatedHours = null;
    private EditText comments = null;

    private int sprintID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.add_task_details_layout);

	taskDescription = (EditText) findViewById(R.id.enter_task);
	priority = (EditText) findViewById(R.id.enter_task_priority);
	resource = (EditText) findViewById(R.id.enter_resource);
	acceptanceCriteria = (EditText) findViewById(R.id.enter_acceptance_criteria);
	estimatedHours = (EditText) findViewById(R.id.enter_estimated_hours);
	comments = (EditText) findViewById(R.id.enter_comments);

	Intent myIntent = getIntent();
	sprintID = myIntent.getIntExtra("sprintID", 0);    
    }

    public void createTask(View view) {
	int sprintIdInt = sprintID;
	String taskDescriptionStr = getText(taskDescription);
	String priorityStr = getText(priority);
	String resourceStr = getText(resource);
	String acceptanceCriteriaStr = getText(acceptanceCriteria);
	int estimatedHoursInt = Integer.parseInt(getText(estimatedHours));
	String commentsStr = getText(comments);

	if(taskDescriptionStr.length() >= 2) {
	    openDBCon();
	    Boolean dbCheck = mDbHelper.addTask(new Task(sprintIdInt, taskDescriptionStr, priorityStr, resourceStr, acceptanceCriteriaStr, estimatedHoursInt, commentsStr));
	    closeDbCon();	 

	    if(dbCheck) {
		Toast.makeText(getApplicationContext(), "Added Successfully...", Toast.LENGTH_SHORT).show();

		Intent sprintActivity = new Intent(AddTaskDetailsActivity.this, TaskActivity.class);
		sprintActivity.putExtra("sprintID", sprintID);
		startActivity(sprintActivity);
	    } else {
		Toast.makeText(getApplicationContext(), "dbCheck false!", Toast.LENGTH_SHORT).show();
	    }
	} else {
	    Toast.makeText(getApplicationContext(), "Please provide valid data!", Toast.LENGTH_SHORT).show();
	}
    }

    public void cancelTask(View view) {
	finish();
    }

    @Override
    protected void onPause() {
	super.onPause();
	finish();
    }

    private void closeDbCon() {
	if (mDbHelper != null && mDbHelper.isOpen()) {
	    mDbHelper.close();
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

    private void openDBCon() {
	if (mDbHelper == null) {
	    mDbHelper = new ApplicationDbHelper(this);
	    mDbHelper.open();
	} else if (!mDbHelper.isOpen()) {
	    mDbHelper.open();
	}
    }
}