package com.cgit.main;

import com.cgit.helper.ApplicationDbHelper;
import com.cgit.model.Task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewTaskDetailsActivity extends BaseActivity {

    private ApplicationDbHelper mDbHelper;

    TextView task;
    TextView status;
    TextView priority;
    TextView resource;
    TextView acceptanceCriteria;
    TextView estimatedHours;
    TextView actualHours;
    TextView remainingHours;
    TextView comments;
    
    private Integer taskID;

    private Task taskDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.view_task_details_layout);

	/*task = (TextView) findViewById(R.id.task_value);
	status = (TextView) findViewById(R.id.status_value);
	priority = (TextView) findViewById(R.id.priority_value);
	resource = (TextView) findViewById(R.id.resource_value);
	acceptanceCriteria = (TextView) findViewById(R.id.acceptance_criteria_value);
	estimatedHours = (TextView) findViewById(R.id.estimated_hours_value);
	actualHours = (TextView) findViewById(R.id.actual_hours_value);
	remainingHours = (TextView) findViewById(R.id.remaining_hours_value);
	comments = (TextView) findViewById(R.id.comments_value);*/	

	Intent taskItem = getIntent();
	Bundle bundle = taskItem.getExtras();
	if (bundle != null) {
	    taskID = (Integer) bundle.get("taskID");
	}
	getTaskView();
    }

    private void getTaskView() {
	openDBCon();
	taskDetails = mDbHelper.getTaskDetails(taskID);
	closeDbCon();	

	taskDetails.getTaskId();
	taskDetails.getSprintId();

	task.setText(taskDetails.getTaskDescription());
	priority.setText(taskDetails.getPriority());
	status.setText(taskDetails.getStatus());
	resource.setText(taskDetails.getResourceName());
	acceptanceCriteria.setText(taskDetails.getAcceptanceCriteria());
	estimatedHours.setText(taskDetails.getEstimatedHours());
	actualHours.setText(taskDetails.getActualHours());
	comments.setText(taskDetails.getComments());

    }

    public void updateTask(View view) {

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
