package com.cgit.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.home_layout);
    }

    @Override
    public void onBackPressed() {
	Intent selectBacklogActivity = new Intent(this, HomeActivity.class);
	startActivity(selectBacklogActivity);
    }

    public void projects(View view) {
	Intent projectsActivity = new Intent(this, ProjectsActivity.class);
	startActivity(projectsActivity);
    }

    public void resources(View view) {
	Intent resourcesActivity = new Intent(this, ResourcesActivity.class);
	startActivity(resourcesActivity);
    }

    public void emailID(View view) {
	Intent emailActivity = new Intent(this, EmailActivity.class);
	startActivity(emailActivity);
    }

    public void changePassword(View view) {
	Intent changePasswordActivity = new Intent(this, ChangePasswordActivity.class);
	startActivity(changePasswordActivity);
    }
}
