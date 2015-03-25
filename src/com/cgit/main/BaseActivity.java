package com.cgit.main;

import android.app.Activity;
import android.widget.EditText;

public class BaseActivity extends Activity {

    protected String getText(EditText text) {
	String retVal = text.getText().toString();
	if (retVal != null)
	    retVal = retVal.trim();

	return retVal;
    }

}
