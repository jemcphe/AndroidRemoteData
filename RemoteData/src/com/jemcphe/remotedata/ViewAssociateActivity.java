package com.jemcphe.remotedata;

import java.util.List;

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
import android.widget.TextView;

public class ViewAssociateActivity extends Activity implements OnClickListener {

	// Declare TextViews
	TextView name;
	TextView phone;
	TextView email;
	TextView compId;
	TextView status;

	// Declare Strings
	String objectIdString;
	String firstNameString;
	String lastNameString;
	String phoneString;
	String emailString;
	String compString;
	String statusString;

	// Declare Buttons
	Button editButton;
	Button deleteButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_associate);

		// Locate TextViews
		name = (TextView) this.findViewById(R.id.view_Name);
		phone = (TextView) this.findViewById(R.id.view_phone);
		email = (TextView) this.findViewById(R.id.view_email);
		compId = (TextView) this.findViewById(R.id.view_comp);
		status = (TextView) this.findViewById(R.id.view_status);

		// retrieve data from MainActivity Item Selection
		Intent intent = getIntent();
		// Set Strings with data pulled
		objectIdString = intent.getStringExtra("objectId");
		firstNameString = intent.getStringExtra("firstName");
		lastNameString = intent.getStringExtra("lastName");
		phoneString = intent.getStringExtra("phone");
		emailString = intent.getStringExtra("email");
		compString = intent.getStringExtra("compId");
		statusString = intent.getStringExtra("status");
		
		// Set TextViews with pulled data
		name.setText(firstNameString + " " + lastNameString);
		phone.setText(phoneString);
		email.setText(emailString);
		compId.setText(compString);
		status.setText(statusString);

		// Locate editButton, setId, & onClickListener
		editButton = (Button) this.findViewById(R.id.view_edit_button);
		editButton.setId(0);
		editButton.setOnClickListener(this);

		// Locate deleteButton, setId, & onClickListener
		deleteButton = (Button) this.findViewById(R.id.view_delete_button);
		deleteButton.setId(1);
		deleteButton.setOnClickListener(this);
	}

	/*
	 * onDelete() method is for deleting individual objects. First we Query through
	 * the Associate Database, looking for our objectId's to match.  If they do, the object
	 * gets deleted from the database.  If not, we get an error stating why.
	 */
	public void onDelete(){
		// try/catch to query through Associate Database
		try {
			// query via Associate Table in database
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Associate");
			// filter objects where objectId == objectIdString
			query.whereEqualTo("objectId", objectIdString);
			// Create list of query results
			List<ParseObject> associate = query.find();
			// Loop through query results
			for (ParseObject object : associate) {
				// Create Log to see what Associate has been filtered
				Log.i("Remote Data", object.getString("firstName").toString());
				// Delete the object, if offline, then eventually (when data is present, again)
				object.deleteEventually();
			}
		} catch (ParseException e) {
			// Log Errors
			e.printStackTrace();
		}

	}
	
	/*
	 * onEdit() method allows user to edit an Associate object.  Method is designed
	 * to create an intent that we put key/value data into and pass to our
	 * receiving activity. In this case, EditActivity.class.
	 */
	public void onEdit(){
		//Create an intent that captures data and sends to EditActivity
		Intent intent = new Intent(ViewAssociateActivity.this, EditActivity.class);
		// Placing values into intent using key/value pairs
		intent.putExtra("objectId", objectIdString);
		intent.putExtra("firstName", firstNameString);
		intent.putExtra("lastName", lastNameString);
		intent.putExtra("phone", phone.getText().toString());
		intent.putExtra("email", email.getText().toString());
		intent.putExtra("compId", compId.getText().toString());
		intent.putExtra("status", status.getText().toString());
		// Start the intent to launch EditActivity & pass data
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		// Switch using button id's created earlier
		switch (v.getId()) {
		case 0: // Edit Button
			// Launch onEdit
			onEdit();
			// Resign current activity when finished
			finish();
			break;
		case 1: // Delete Button
			// Launch onDelete
			onDelete();
			// Resign current activity when finished
			finish();
			break;
		default:
			break;
		}
	}

}
