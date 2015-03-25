package com.cgit.helper;

import com.cgit.helper.DatabaseHelper;
import com.cgit.helper.BaseDbHelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BaseDbHelper {
    
	private DatabaseHelper mDbHelper;
	protected SQLiteDatabase mDb;
	protected final Context mCtx;

    public BaseDbHelper(Context ctx) {
	this.mCtx = ctx;
    }
    
	public BaseDbHelper open() throws SQLException {
		mDbHelper =  new DatabaseHelper(mCtx);
		mDb =  mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		mDbHelper.close();
	}

	public boolean isOpen() {
		if (mDb != null && mDb.isOpen())
			return true;
		return false;
	}

}
