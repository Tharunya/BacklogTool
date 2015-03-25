package com.cgit.main;

import java.util.ArrayList;
import java.util.List;

import com.cgit.helper.ApplicationDbHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProjectsActivity extends BaseActivity {

    ListView listView;

    private ApplicationDbHelper mDbHelper; 
    private List<String> projects = new ArrayList<String>(); 
    int projectID;

    @Override
    public void onCreate(Bundle savedInstance) {
	super.onCreate(savedInstance);
	setContentView(R.layout.projects_layout);

	listView = (ListView) findViewById(R.id.listView1);

	getProjects();	
    }

    private void getProjects() {
	openDBCon();
	projects = mDbHelper.getAllProjectNames();
	closeDbCon();

	if(!projects.isEmpty()) {
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, projects);
	    listView.setAdapter(adapter); 
	} else {
	    Toast.makeText(getApplicationContext(), "Projects does not exist!", Toast.LENGTH_SHORT).show();
	}

	listView.setOnItemClickListener(new OnItemClickListener() {
	    String projectName;
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {		
		projectName = (String) parent.getItemAtPosition(position);
		getProjectID();
		Intent sprintActivity = new Intent(ProjectsActivity.this, SprintActivity.class);
		sprintActivity.putExtra("projectID", projectID);
		startActivity(sprintActivity);
	    }

	    private void getProjectID() {
		openDBCon();
		projectID = mDbHelper.getProjectId(projectName);
		closeDbCon();
	    }

	}); 
    }

    public void addProject(View view) {
	Intent addProjectDetailsActivity = new Intent(this, AddProjectDetailsActivity.class);
	startActivity(addProjectDetailsActivity);

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
	}
	else if(!mDbHelper.isOpen()) {
	    mDbHelper.open();
	}	
    }

    protected void closeDbCon() {
	if (mDbHelper != null && mDbHelper.isOpen()){
	    mDbHelper.close();
	}
    }
}