package com.cgit.main;

import java.util.ArrayList;
import java.util.List;

import com.cgit.helper.ApplicationDbHelper;
import com.cgit.model.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddProjectDetailsActivity extends BaseActivity {

    @Override
    protected void onPause() {
	super.onPause();
	finish();
    }

    private ApplicationDbHelper mDbHelper;
    private List<String> resources = new ArrayList<String>(); 

    private EditText projectName = null;
    private EditText projectDescription = null;
    private EditText projectStartDate = null;
    private EditText projectEndDate = null;
    private EditText sprintDuration = null;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.add_project_details_layout);

	projectName = (EditText) findViewById(R.id.enter_project_name);
	projectDescription = (EditText) findViewById(R.id.editText1);
	projectStartDate = (EditText) findViewById(R.id.enter_start_date);
	projectEndDate  = (EditText) findViewById(R.id.enter_end_date);
	sprintDuration = (EditText) findViewById(R.id.editText2);
	listView = (ListView) findViewById(R.id.listview_select_resources);
	
	getResourceList();	
    }

    private void getResourceList() {
	openDBCon();
	resources = mDbHelper.getAllResources();
	closeDbCon();

	if(!resources.isEmpty()) {
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, resources);
	listView.setAdapter(adapter); 
/*	getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);*/
	
/*	listView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
		    // When clicked, show a toast with the TextView text
		    Toast.makeText(getApplicationContext(), ((EditText) view).getText(), Toast.LENGTH_SHORT).show();
		}
	});*/
	} else {
	    Toast.makeText(getApplicationContext(), "Resources does not exist!", Toast.LENGTH_SHORT).show();
	}
    }


    public void createProject(View view) {

	String pNameStr = getText(projectName);
	String pDescriptionStr = getText(projectDescription);
	String pStartDateStr = getText(projectStartDate);
	String pEndDateStr = getText(projectEndDate);
	int sprintDurationInt = Integer.parseInt(getText(sprintDuration));

	if (pNameStr.length() >= 2) {

	    openDBCon();
	    Boolean dbCheck = mDbHelper.addProject(new Project(pNameStr, pDescriptionStr, pStartDateStr, pEndDateStr, sprintDurationInt));
	    closeDbCon();	    

	    if(dbCheck) {
		Toast.makeText(getApplicationContext(), "Added Successfully...", Toast.LENGTH_SHORT).show();

		Intent projectsActivity = new Intent(this, ProjectsActivity.class);
		startActivity(projectsActivity);
	    } else {
		Toast.makeText(getApplicationContext(), "Name already exists!", Toast.LENGTH_SHORT).show();
	    }
	} else {
	    Toast.makeText(getApplicationContext(), "Please provide valid data!", Toast.LENGTH_SHORT).show();
	}
    }

    public void cancelProject(View view) {
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
