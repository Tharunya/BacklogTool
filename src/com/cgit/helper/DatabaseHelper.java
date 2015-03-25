package com.cgit.helper;

import com.cgit.helper.ApplicationDbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
    private static final String DATABASE_NAME = "backlogtooldb";
    private static final int DATABASE_VERSION = 25;

    public DatabaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
	db.execSQL(ApplicationDbHelper.CREATE_USER);
	db.execSQL(ApplicationDbHelper.CREATE_RESOURCE);
	db.execSQL(ApplicationDbHelper.CREATE_PROJECT);
	db.execSQL(ApplicationDbHelper.CREATE_SPRINT);
	db.execSQL(ApplicationDbHelper.CREATE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	Log.w(ApplicationDbHelper.TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
	db.execSQL(DROP_TABLE_IF_EXISTS + ApplicationDbHelper.USER_TABLE);
	db.execSQL(DROP_TABLE_IF_EXISTS + ApplicationDbHelper.PROJECT_TABLE);
	db.execSQL(DROP_TABLE_IF_EXISTS + ApplicationDbHelper.RESOURCE_TABLE);
	db.execSQL(DROP_TABLE_IF_EXISTS + ApplicationDbHelper.SPRINT_TABLE);
	db.execSQL(DROP_TABLE_IF_EXISTS + ApplicationDbHelper.TASK_TABLE);

	onCreate(db);
    }
}