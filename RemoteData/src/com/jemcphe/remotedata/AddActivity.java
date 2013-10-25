package com.jemcphe.remotedata;

import com.parse.ParseObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity {
	// EditText Fields
	EditText editFirstName;
	EditText editLastName;
	EditText editPhone;
	EditText editEmail;
	EditText editCompId;
	EditText editStatus;
	// Button
	Button addButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_view);
		
		// Declare EditText Fields
		editFirstName = (EditText) this.findViewById(R.id.add_firstName);
		editLastName = (EditText) this.findViewById(R.id.add_lastName);
		editPhone = (EditText) this.findViewById(R.id.add_phone);
		editEmail = (EditText) this.findViewById(R.id.add_email);
		editCompId = (EditText) this.findViewById(R.id.add_comp);
		editStatus = (EditText) this.findViewById(R.id.add_status);
		// Declare addButton
		addButton = (Button) this.findViewById(R.id.add_save_button);
		
		//Temp values for testing
//		editFirstName.setText("Margherita");
//		editLastName.setText("McPherson");
//		editPhone.setText("9189166695");
//		editEmail.setText("dolcevitacupcakes@gmail.com");
//		editCompId.setText("40988");
//		editStatus.setText("Part Time");
		
		// setOnClickListener for addButton
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Launch onSave Method
				onSave();
				// Resign this Activity when finished
				finish();
			}
		});
		
	}
	
	/*
	 * onSave is launched whenever Add Associate Button is selected.  
	 * The method creates a new Associate, grabs values from EditText fields, 
	 * and places them in their appropriate columns within the database.
	 * Also, saveEventually is used in case user decides to add associates
	 * offline.
	 */
	public void onSave(){
		ParseObject associate = new ParseObject("Associate");
		associate.put("firstName", editFirstName.getText().toString());
		associate.put("lastName", editLastName.getText().toString());
		associate.put("phoneNumber", editPhone.getText().toString());
		associate.put("email", editEmail.getText().toString());
		associate.put("compId", editCompId.getText().toString());
		associate.put("status", editStatus.getText().toString());
		associate.saveEventually();
	}

}
