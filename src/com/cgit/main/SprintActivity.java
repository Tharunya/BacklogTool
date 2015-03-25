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

public class SprintActivity extends BaseActivity {

    ListView listView;

    private ApplicationDbHelper mDbHelper;
    private List<String> sprints = new ArrayList<String>(); 
    private int projectID;

    private int sprintID;

    private String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.sprints_layout);

	listView = (ListView) findViewById(R.id.listView2);

	Intent projectItem = getIntent();
	Bundle bundle = projectItem.getExtras();
	if(bundle != null) {
	    projectID = (Integer) bundle.get("projectID");
	}
	getProjectSprints();
    }

    private void getProjectSprints() {
	openDBCon();
	sprints = mDbHelper.getProjectSprints(projectID);
	closeDbCon();

	if(!sprints.isEmpty()) {
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sprints);
	    listView.setAdapter(adapter);    
	}  else {
	    Toast.makeText(getApplicationContext(), "Sprints does not exist!", Toast.LENGTH_SHORT).show();
	}

	listView.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {		
		item = (String) parent.getItemAtPosition(position);
		getSprintID();
		Intent taskActivity = new Intent(SprintActivity.this, TaskActivity.class);
		taskActivity.putExtra("sprintID", sprintID);
		startActivity(taskActivity);
	    }
	}); 
    }

    public void addSprint(View view) {
	Intent addSprintDetailsActivity = new Intent(SprintActivity.this, AddSprintDetailsActivity.class);
	addSprintDetailsActivity.putExtra("projectID", projectID);
	startActivity(addSprintDetailsActivity);
	
	finish();
    }

    private void getSprintID() {
	openDBCon();
	sprintID = mDbHelper.getSprintId(item);
	closeDbCon();
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
