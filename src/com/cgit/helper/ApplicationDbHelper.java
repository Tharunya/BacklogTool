package com.cgit.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.cgit.helper.BaseDbHelper;
import com.cgit.model.Project;
import com.cgit.model.Resource;
import com.cgit.model.Sprint;
import com.cgit.model.Task;
import com.cgit.model.User;

public class ApplicationDbHelper extends BaseDbHelper {

    /*
     * Constructor - takes the context to allow the database to be
     * opened/created.
     * 
     * @param ctx the Context within which to work
     */
    public ApplicationDbHelper(Context ctx) {
	super(ctx);
    }

    // User Table Column keys
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CONFIRM_PASSWORD = "confirm_password";
    private static final String KEY_EMAIL_ID = "email_id";

    // Project Table Column keys
    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_PROJECT_DESCRIPTION = "project_description";
    private static final String KEY_PROJECT_NAME = "project_name";
    private static final String KEY_PROJECT_START_DATE = "project_start_date";
    private static final String KEY_PROJECT_END_DATE = "project_end_date";
    private static final String KEY_SPRINT_DURATION = "sprint_duration";

    // Resource Table Column keys
    private static final String KEY_RESOURCE_ID = "resource_id";
    private static final String KEY_RESOURCE_NAME = "resource_name";

    // Sprint Table Column keys
    private static final String KEY_SPRINT_ID = "sprint_id";
    // private static final String KEY_PROJECT_ID = "project_id"; Foreign Key
    private static final String KEY_SPRINT_NAME = "sprint_name";
    private static final String KEY_SPRINT_DESCRIPTION = "sprint_description";
    private static final String KEY_SPRINT_START_DATE = "sprint_start_date";
    private static final String KEY_SPRINT_END_DATE = "sprint_end_date";

    // Task Table Column keys
    private static final String KEY_TASK_ID = "task_id";
    //private static final String KEY_SPRINT_ID = "sprint_id"; Foreign Key
    private static final String KEY_TASK_DESCRIPTION = "task_description";
    private static final String KEY_TASK_PRIORITY = "task_priority";
    private static final String KEY_TASK_STATUS = "task_status";
    private static final String KEY_TASK_ACTUAL_HOURS = "task_actual_hours";   
    //private static final String KEY_RESOURCE_NAME = "resource_name"; Foreign key
    private static final String KEY_TASK_ACCEPTANCE_CRITERIA = "task_acceptance_criteria";
    private static final String KEY_TASK_ESTIMATED_HOURS = "task_estimated_hours";
    private static final String KEY_TASK_COMMENTS = "task_comments";

    //Project & Resource Map Column keys
    private static final String KEY_PROJECT_RESOURCE_MAP_ID = "project_resource_map_id";
    //private static final String KEY_PROJECT_ID = "project_id"; Foreign key
    //private static final String KEY_RESOURCE_ID = "resource_id"; Foreign key

    //Task & Resource Map Column keys
    private static final String KEY_TASK_RESOURCE_MAP = "task_resource_map";
    //private static final String KEY_TASK_RESOURCE_MAP = "task_resource_map"; Foreign key
    //private static final String KEY_TASK_RESOURCE_MAP = "task_resource_map";


    static final String TAG = "ApplicationsDbAdapter";

    // Tables
    static final String USER_TABLE = "user";
    static final String PROJECT_TABLE = "project";
    static final String RESOURCE_TABLE = "resource";
    static final String SPRINT_TABLE = "sprint";
    static final String TASK_TABLE = "task";

    // Table Columns
    static final String[] USER_COLUMNS = new String[] { KEY_USER_ID, KEY_FIRST_NAME, KEY_LAST_NAME, KEY_USER_NAME, KEY_PASSWORD, KEY_CONFIRM_PASSWORD, KEY_EMAIL_ID };
    static final String[] PROJECT_COLUMNS = new String[] { KEY_PROJECT_ID, KEY_PROJECT_NAME, KEY_PROJECT_DESCRIPTION, KEY_PROJECT_START_DATE,
	KEY_PROJECT_END_DATE, KEY_SPRINT_DURATION };
    static final String[] RESOURCE_COLUMNS = new String[] { KEY_RESOURCE_ID, KEY_RESOURCE_NAME };
    static final String[] SPRINT_COLUMNS = new String[] { KEY_SPRINT_ID, KEY_PROJECT_ID, KEY_SPRINT_NAME, KEY_SPRINT_DESCRIPTION,
	KEY_SPRINT_START_DATE, KEY_SPRINT_END_DATE };
    static final String[] TASK_COLUMNS = new String[] { KEY_TASK_ID, KEY_SPRINT_ID, KEY_TASK_DESCRIPTION, KEY_TASK_STATUS, KEY_TASK_ACTUAL_HOURS, KEY_TASK_PRIORITY, KEY_RESOURCE_NAME,
	KEY_TASK_ACCEPTANCE_CRITERIA, KEY_TASK_ESTIMATED_HOURS, KEY_TASK_COMMENTS };

    // Create Tables
    static final String CREATE_USER = "create table user(user_id integer primary key autoincrement, "
	    + "first_name varchar(32), last_name varchar(32), user_name varchar(32) unique, password varchar(32), confirm_password varchar(32), email_id varchar(32) unique);";
    static final String CREATE_PROJECT = "create table project(project_id integer primary key autoincrement, "
	    + "project_name varchar(32) unique, project_description varchar(32), project_start_date date, project_end_date date, sprint_duration integer);";
    static final String CREATE_RESOURCE = "create table resource(resource_id integer primary key autoincrement, resource_name varchar(32) unique);";
    static final String CREATE_SPRINT = "create table sprint(sprint_id integer primary key autoincrement, "
	    + "project_id integer not null, sprint_name varchar(32), sprint_description varchar(32), sprint_start_date date, sprint_end_date date, foreign key (project_id) references project (project_id));";
    static final String CREATE_TASK = "create table task(task_id integer primary key autoincrement, sprint_id integer, task_description varchar(32), task_priority varchar(32),  "
	    +"task_status varchar(32), resource_name varchar(32), task_acceptance_criteria varchar(32), task_actual_hours integer, task_estimated_hours integer, task_comments varchar(32), foreign key (resource_name) references resource (resource_name), foreign key (sprint_id) references sprint (sprint_id));";

    public boolean validateUser(String username, String password) {
	Cursor cursor = null;
	Boolean retVal = null;
	try {
	    cursor = mDb.rawQuery("SELECT * FROM  user  WHERE  user_name = '"
		    + username + "' AND  password = '" + password + "'", null);
	    if (cursor.getCount() == 1)
		retVal = true;
	    else
		retVal = false;
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (cursor != null) {
		cursor.close();
	    }
	}
	return retVal;
    }

    public boolean addResource(Resource resource) {
	Log.d("addResource", resource.toString());
	Boolean retVal;
	try {
	    ContentValues values = new ContentValues();
	    values.put(KEY_RESOURCE_NAME, resource.getResourceName());
	    mDb.insertOrThrow(RESOURCE_TABLE, null, values);
	    retVal = true;
	} catch (Exception e) {
	    e.printStackTrace();
	    retVal = false;
	}
	return retVal;
    }

    public boolean addProject(Project project) {
	Log.d("addProject", project.toString());
	Boolean retVal;
	try {
	    ContentValues values = new ContentValues();
	    values.put(KEY_PROJECT_NAME, project.getProjectName());
	    values.put(KEY_PROJECT_DESCRIPTION, project.getProjectDescription());
	    values.put(KEY_PROJECT_START_DATE, project.getProjectStartDate());
	    values.put(KEY_PROJECT_END_DATE, project.getProjectEndDate());
	    values.put(KEY_SPRINT_DURATION, project.getSprintDuration());

	    mDb.insertOrThrow(PROJECT_TABLE, null, values);
	    retVal = true;
	} catch (Exception e) {
	    e.printStackTrace();
	    retVal = false;
	}
	return retVal;
    }

    public boolean addSprint(Sprint sprint) {

	Log.d("addSprint", sprint.toString());
	Boolean retVal;
	try {
	    ContentValues values = new ContentValues();
	    values.put(KEY_PROJECT_ID, sprint.getProjectId());
	    values.put(KEY_SPRINT_NAME, sprint.getSprintName());
	    values.put(KEY_SPRINT_DESCRIPTION, sprint.getSprintDescription());
	    values.put(KEY_SPRINT_START_DATE, sprint.getSprintStartdate());
	    values.put(KEY_SPRINT_END_DATE, sprint.getSprintEndDate());

	    mDb.insertOrThrow(SPRINT_TABLE, null, values);
	    retVal = true;
	} catch (Exception e) {
	    e.printStackTrace();
	    retVal = false;
	}
	return retVal;
    }

    public int getProjectId(String projectName) {
	Cursor cursor = null;
	int projectId = 0;
	try {
	    String query = "SELECT " + KEY_PROJECT_ID + " FROM "
		    + PROJECT_TABLE + " WHERE project_name = '" + projectName
		    + "'";
	    cursor = mDb.rawQuery(query, null);
	    if (cursor != null) {
		if (cursor.moveToFirst()) {
		    projectId = cursor.getInt(cursor
			    .getColumnIndex(KEY_PROJECT_ID));
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (cursor != null) {
		cursor.close();
	    }
	}
	return projectId;
    }

    public int getSprintId(String sprintName) {
	Cursor cursor = null;
	int sprintId = 0;
	try {
	    String query = "SELECT " + KEY_SPRINT_ID + " FROM " + SPRINT_TABLE + " WHERE sprint_name = '" + sprintName + "'";
	    cursor = mDb.rawQuery(query, null);
	    if(cursor != null) {
		if(cursor.moveToFirst()) {
		    sprintId = cursor.getInt(cursor.getColumnIndex(KEY_SPRINT_ID));
		}
	    }
	} catch(Exception e) {
	    e.printStackTrace();
	} finally {
	    if(cursor != null) {
		cursor.close();
	    }
	}
	return sprintId;
    }

    public int getTaskId(String taskName) {
	Cursor cursor = null;
	int taskId = 0;
	try {
	    String query = "SELECT " + KEY_TASK_ID + " FROM " + TASK_TABLE + " WHERE task_description = '" + taskName + "'";
	    cursor = mDb.rawQuery(query, null);
	    if(cursor != null) {
		if(cursor.moveToFirst()) {
		    taskId = cursor.getInt(cursor.getColumnIndex(KEY_TASK_ID));
		}
	    }
	} catch(Exception e) {
	    e.printStackTrace();
	} finally {
	    if(cursor != null) {
		cursor.close();
	    }
	}
	return taskId;
    }

    public List<String> getAllResources() {
	Cursor cursor = null;
	List<String> resourcesList = new ArrayList<String>();
	try {
	    String query = "SELECT " + KEY_RESOURCE_NAME + " FROM "
		    + RESOURCE_TABLE;
	    cursor = mDb.rawQuery(query, null);
	    if (cursor != null) {
		if (cursor.moveToFirst()) {
		    do {
			resourcesList.add(cursor.getString(cursor
				.getColumnIndex(KEY_RESOURCE_NAME)));
		    } while (cursor.moveToNext());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (cursor != null) {
		cursor.close();
	    }
	}
	return resourcesList;
    }

    public List<Project> getAllProjects() {
	Cursor cursor = null;
	List<Project> projectsList = new ArrayList<Project>();
	try {
	    String query = "SELECT * FROM " + PROJECT_TABLE;
	    cursor = mDb.rawQuery(query, null);
	    if (cursor != null) {
		if (cursor.moveToFirst()) {
		    do {
			Project project = new Project();
			project.setProjectId(cursor.getInt(cursor.getColumnIndex(KEY_PROJECT_ID)));
			project.setProjectName(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
			project.setProjectDescription(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_DESCRIPTION)));
			project.setProjectStartDate(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_START_DATE)));
			project.setProjectEndDate(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_END_DATE)));
			project.setSprintDuration(cursor.getInt(cursor.getColumnIndex(KEY_SPRINT_DURATION)));
			projectsList.add(project);
		    } while (cursor.moveToNext());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (cursor != null) {
		cursor.close();
	    }
	}
	return projectsList;
    }

    public List<String> getAllProjectNames() {
	Cursor cursor = null;
	List<String> projectNamesList = new ArrayList<String>();
	try {
	    String query = "SELECT " + KEY_PROJECT_NAME + " FROM "
		    + PROJECT_TABLE;
	    cursor = mDb.rawQuery(query, null);
	    if (cursor != null) {
		if (cursor.moveToFirst()) {
		    do {
			projectNamesList.add(cursor.getString(cursor
				.getColumnIndex(KEY_PROJECT_NAME)));
		    } while (cursor.moveToNext());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (cursor != null) {
		cursor.close();
	    }
	}
	return projectNamesList;
    }

    public List<String> getProjectSprints(int projectID) {
	Cursor cursor = null;
	List<String> projectSprintsList = new ArrayList<String>();
	try {
	    String query = "SELECT sprint_name FROM sprint WHERE project_id = '"
		    + projectID + "';";
	    cursor = mDb.rawQuery(query, null);
	    if (cursor != null) {
		if (cursor.moveToFirst()) {
		    do {
			projectSprintsList.add(cursor.getString(cursor
				.getColumnIndex(KEY_SPRINT_NAME)));
		    } while (cursor.moveToNext());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (cursor != null) {
		cursor.close();
	    }
	}
	return projectSprintsList;
    }

    public List<String> getSprintTasks(int sprintID) {
	Cursor cursor = null;
	List<String> sprintTasksList = new ArrayList<String>();
	try {
	    String query = "SELECT task_description FROM task WHERE sprint_id = '"
		    + sprintID + "';";
	    cursor = mDb.rawQuery(query, null);
	    if (cursor != null) {
		if (cursor.moveToFirst()) {
		    do {
			sprintTasksList.add(cursor.getString(cursor.getColumnIndex(KEY_TASK_DESCRIPTION)));
		    } while (cursor.moveToNext());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (cursor != null) {
		cursor.close();
	    }
	}
	return sprintTasksList;
    }

    public Boolean addUser(User user) {
	Log.d("addUser", user.toString());
	Boolean retVal;
	try {
	    ContentValues values = new ContentValues();
	    values.put(KEY_FIRST_NAME, user.getFirstName());
	    values.put(KEY_LAST_NAME, user.getLastName());
	    values.put(KEY_USER_NAME, user.getUserName());
	    values.put(KEY_PASSWORD, user.getPassword());
	    values.put(KEY_CONFIRM_PASSWORD, user.getConfirmPassword());
	    values.put(KEY_EMAIL_ID, user.getEmailID());

	    mDb.insertOrThrow(USER_TABLE, null, values);
	    retVal = true;

	} catch (Exception e) {
	    e.printStackTrace();
	    retVal = false;
	}
	return retVal;
    }

    public Boolean addTask(Task task) {

	Log.d("addTask", task.toString());
	Boolean retVal;
	try {
	    ContentValues values = new ContentValues();
	    values.put(KEY_SPRINT_ID, task.getSprintId());
	    values.put(KEY_TASK_DESCRIPTION, task.getTaskDescription());	    
	    values.put(KEY_TASK_PRIORITY, task.getPriority());
	    values.put(KEY_TASK_STATUS, "Open");
	    values.put(KEY_TASK_ACTUAL_HOURS, "0");
	    values.put(KEY_RESOURCE_NAME, task.getResourceName());
	    values.put(KEY_TASK_ACCEPTANCE_CRITERIA, task.getAcceptanceCriteria());
	    values.put(KEY_TASK_ESTIMATED_HOURS, task.getEstimatedHours());
	    values.put(KEY_TASK_COMMENTS, task.getComments());

	    mDb.insertOrThrow(TASK_TABLE, null, values);
	    retVal = true;
	} catch (Exception e) {
	    e.printStackTrace();
	    retVal = false;
	}
	return retVal;
    }

    public Task getTaskDetails(Integer taskID) {
	Cursor cursor = null;
	Task task = null;
	try {
	    String query = "SELECT * FROM task WHERE task_id = "+taskID+";";
	    cursor = mDb.rawQuery(query, null);
	    if (cursor != null) {
		if (cursor.moveToFirst()) {
		    do {
			task = new Task();
			task.setTaskId(cursor.getInt(cursor.getColumnIndex(KEY_TASK_ID)));
			task.setSprintId(cursor.getInt(cursor.getColumnIndex(KEY_SPRINT_ID)));
			task.setTaskDescription(cursor.getString(cursor.getColumnIndex(KEY_TASK_DESCRIPTION)));			
			task.setPriority(cursor.getString(cursor.getColumnIndex(KEY_TASK_PRIORITY)));	
			task.setActualHours(cursor.getInt(cursor.getColumnIndex(KEY_TASK_ACTUAL_HOURS)));
			task.setStatus(cursor.getString(cursor.getColumnIndex(KEY_TASK_STATUS)));
			task.setResourceName(cursor.getString(cursor.getColumnIndex(KEY_RESOURCE_NAME)));
			task.setAcceptanceCriteria(cursor.getString(cursor.getColumnIndex(KEY_TASK_ACCEPTANCE_CRITERIA)));			
			task.setEstimatedHours(cursor.getInt(cursor.getColumnIndex(KEY_TASK_ESTIMATED_HOURS)));
			task.setComments(cursor.getString(cursor.getColumnIndex(KEY_TASK_COMMENTS)));
		    } while (cursor.moveToNext());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (cursor != null) {
		cursor.close();
	    }
	}
	return task;
    }

}