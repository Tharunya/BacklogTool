package com.cgit.main;

import java.util.ArrayList;
import java.util.List;

import com.cgit.helper.ApplicationDbHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TaskActivity extends BaseActivity {

    ListView listView;

    private ApplicationDbHelper mDbHelper;
    private List<String> tasks = new ArrayList<String>();
    private int sprintID;
    private int taskID;

    protected String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.tasks_layout);

	listView = (ListView) findViewById(R.id.listView3);

	Intent projectItem = getIntent();
	Bundle bundle = projectItem.getExtras();
	if (bundle != null) {
	    sprintID = (Integer) bundle.get("sprintID");
	}
	getSprintTasks();
    }

    private void getSprintTasks() {
	openDBCon();
	tasks = mDbHelper.getSprintTasks(sprintID);
	closeDbCon();

	if (!tasks.isEmpty()) {
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tasks);
	    listView.setAdapter(adapter);
	} else {
	    Toast.makeText(getApplicationContext(), "Tasks does not exist!", Toast.LENGTH_SHORT).show();
	}

	listView.setOnItemClickListener(new OnItemClickListener() {	  

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		item = (String) parent.getItemAtPosition(position);
		getTaskID();
		Intent viewTaskDetailsActivity = new Intent(TaskActivity.this, ViewTaskDetailsActivity.class);		
		viewTaskDetailsActivity.putExtra("taskID", taskID);
		startActivity(viewTaskDetailsActivity);
	    }
	});
    }

    protected void getTaskID() {
	openDBCon();
	taskID = mDbHelper.getTaskId(item);
	closeDbCon();
    }

    public void addTask(View view) {
	Intent addTaskDetailsActivity = new Intent(TaskActivity.this, AddTaskDetailsActivity.class);
	addTaskDetailsActivity.putExtra("sprintID", sprintID);
	startActivity(addTaskDetailsActivity);

	finish();
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
