package com.jemcphe.remotedata;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity implements OnClickListener{

	// Declare EditText Fields
	EditText editFirstName;
	EditText editLastName;
	EditText editPhone;
	EditText editEmail;
	EditText editComp;
	EditText editStatus;

	// Declare Button
	Button saveButton;

	// Declare Strings
	String objectIdString;
	String firstNameString;
	String lastNameString;
	String phoneString;
	String emailString;
	String compString;
	String statusString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_view);

		// Locate EditText fields
		editFirstName = (EditText) this.findViewById(R.id.edit_firstName);
		editLastName = (EditText) this.findViewById(R.id.edit_lastName);
		editPhone = (EditText) this.findViewById(R.id.edit_phone);
		editEmail = (EditText) this.findViewById(R.id.edit_email);
		editComp = (EditText) this.findViewById(R.id.edit_comp);
		editStatus = (EditText) this.findViewById(R.id.edit_status);

		// Locate Button & setOnClickListener
		saveButton = (Button) this.findViewById(R.id.edit_save_button);
		saveButton.setOnClickListener(this);

		//Get intent from ViewAssociateActivity, grab values, and populate EditText Fields
		Intent intent = getIntent();
		objectIdString = intent.getStringExtra("objectId");
		firstNameString = intent.getStringExtra("firstName");
		lastNameString = intent.getStringExtra("lastName");
		phoneString = intent.getStringExtra("phone");
		emailString = intent.getStringExtra("email");
		compString = intent.getStringExtra("compId");
		statusString = intent.getStringExtra("status");

		editFirstName.setText(firstNameString);
		editLastName.setText(lastNameString);
		editPhone.setText(phoneString);
		editEmail.setText(emailString);
		editComp.setText(compString);
		editStatus.setText(statusString);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// Launch onSave Method
		onSave();
		// Resign EditActivity when finished
		finish();
	}

	public void onSave(){
		// Create query that uses Associate Table from Remote Database
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Associate");

		// Retrieve the object by id
		query.getInBackground(objectIdString, new GetCallback<ParseObject>() {
			public void done(ParseObject associate, ParseException e) {
				if (e == null) {
					/*
					 * Place values from EditText field into their respective columns.
					 * saveEventually() called in case user edits offline.
					 */
					associate.put("firstName", editFirstName.getText().toString());
					associate.put("lastName", editLastName.getText().toString());
					associate.put("phoneNumber", editPhone.getText().toString());
					associate.put("email", editEmail.getText().toString());
					associate.put("compId", editComp.getText().toString());
					associate.put("status", editStatus.getText().toString());
					associate.saveEventually();
				} else {
					// Log Any Errors
					Log.e("EDIT ERROR", e.getMessage());
				}
			}
		});
	}

}
