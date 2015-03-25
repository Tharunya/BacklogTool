package com.cgit.main;

import java.util.ArrayList;
import java.util.List;

import com.cgit.helper.ApplicationDbHelper;
import com.cgit.model.Resource;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ResourcesActivity extends BaseActivity {

    ListView listView;
    EditText enterResourceName = null;

    private ApplicationDbHelper mDbHelper; 
    private List<String> resources = new ArrayList<String>(); 

    @Override
    public void onCreate(Bundle savedInstance) {
	super.onCreate(savedInstance);
	setContentView(R.layout.resources_layout); 
	
	enterResourceName = (EditText) findViewById(R.id.enter_resource_name);

	listView = (ListView) findViewById(R.id.list1);

	getExistingResource();
    }

    private void getExistingResource() {
	openDBCon();
	resources = mDbHelper.getAllResources();
	closeDbCon();
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, resources);
	listView.setAdapter(adapter); 
    }


    public void addNewResource(View view) {
	String enterResourceNameStr = getText(enterResourceName); 
	if (enterResourceNameStr.length() >= 2) {

	    openDBCon();
	    Boolean dbCheck = mDbHelper.addResource(new Resource(enterResourceNameStr));
	    closeDbCon();

	    if(dbCheck) {
		Toast.makeText(getApplicationContext(), "Added Successfully...", Toast.LENGTH_SHORT).show();
		enterResourceName.setText("");
		getExistingResource();
	    } else 
		Toast.makeText(getApplicationContext(), "Name already exists!", Toast.LENGTH_SHORT).show();
	} else 
	    Toast.makeText(getApplicationContext(), "Atleast 3 characters...", Toast.LENGTH_SHORT).show();	
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
