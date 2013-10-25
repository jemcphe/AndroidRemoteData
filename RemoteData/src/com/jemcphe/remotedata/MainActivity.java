package com.jemcphe.remotedata;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends Activity implements OnClickListener{

	//Declare Local Variables
	ListView listview;
	Context context;
	ParseQuery<ParseObject> query;
	Associate associate;
	CustomAdapter adapter;
	List<ParseObject> object;
	List<ParseObject> singleObject;
	List<ParseObject> statusObject;

	private List<Associate> associateList = null;
	private List<Associate> allAssociateList = null;
	private List<Associate> singleAssociateList = null;
	private List<Associate> statusList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Add your initialization code here
		Parse.initialize(this, "qSFj5qCyU0gGWmsFGyLIxXDrI6XL61gyqH7WCqc1", "FJY2bnB3hc8Wa5LDOLmCzEr2Jl4yrC1qMzGVWb4y");
		//Set Context
		context = this;

		//Create Buttons, setOnClickListener, & setId
		//Single Associate Button
		Button singleAssociateButton = (Button) this.findViewById(R.id.single_associate_button);
		singleAssociateButton.setOnClickListener(this);
		singleAssociateButton.setId(0);
		//All Associates Button
		Button allAssociatesButton = (Button) this.findViewById(R.id.all_associates_button);
		allAssociatesButton.setOnClickListener(this);
		allAssociatesButton.setId(1);
		//Status Button
		Button statusButton = (Button) this.findViewById(R.id.status_button);
		statusButton.setOnClickListener(this);
		statusButton.setId(2);

		//Initiate remote data pull
		new RemoteDataTask().execute();


	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		//Switch statement for menu items
		switch (item.getItemId()) {
		case R.id.action_add:  //Add Associate Button
			//Create Intent to launch AddActivity class
			Intent addIntent = new Intent(context, AddActivity.class);
			startActivity(addIntent);
			return true;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	// RemoteDataTask AsyncTask
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// Create the array
			associateList = new ArrayList<Associate>();
			try {
				// Locate the class table named "Associate" in Parse.com
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"Associate");
				// Set Cache Policy for when device goes offline
				query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
				//populate List with query results
				object = query.find();
				//Loop through all query results
				for (ParseObject associate : object) {
					//Create new instance of associate
					Associate map = new Associate();
					//set items accordingly
					map.setId((String) associate.getObjectId());
					map.setFirstName((String) associate.get("firstName"));
					map.setLastName((String) associate.get("lastName"));
					map.setPhone((String) associate.get("phoneNumber"));
					map.setEmail((String) associate.get("email"));
					map.setCompId((String) associate.get("compId"));
					map.setStatus((String) associate.get("status"));
					// Add items to associateList
					associateList.add(map);
				}
			} catch (ParseException e) {
				// If there be errors, log'em
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case 0:
			//Log Button Selected
			Log.i("REMOTE DATA", "Justin Selected");
			//Create List to hold associate objects
			singleAssociateList = new ArrayList<Associate>();
			// try/catch parse query that pulls single object data
			try {
				// Locate the class table named "Associate" in Parse.com
				ParseQuery<ParseObject> singleQuery = new ParseQuery<ParseObject>(
						"Associate");
				//set cache policy for when offline
				singleQuery.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
				// query for object rows that have firstName == Justin
				singleQuery.whereEqualTo("firstName", "Justin");
				// set singleObject list to query results
				singleObject = singleQuery.find();
				//Loop through results
				for (ParseObject associate : singleObject) {
					//create new instance of associate
					Associate map = new Associate();
					map.setId((String) associate.getObjectId());
					map.setFirstName((String) associate.get("firstName"));
					map.setLastName((String) associate.get("lastName"));
					map.setPhone((String) associate.get("phoneNumber"));
					map.setEmail((String) associate.get("email"));
					map.setCompId((String) associate.get("compId"));
					map.setStatus((String) associate.get("status"));
					// add objects to singleAssociateList
					singleAssociateList.add(map);
				}
			} catch (ParseException e) {
				// Log dem errors
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

			//Set Custom Adapter
			listview = (ListView) MainActivity.this.findViewById(R.id.listView);
			adapter = new CustomAdapter(context, singleAssociateList);
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);

			break;
		case 1:
			//Log Button Selected
			Log.i("REMOTE DATA", "All Associates Selected");
			//create arrayList to hold objects
			allAssociateList = new ArrayList<Associate>();
			// try/catch query results
			try {
				// Locate the class table named "Associate" in Parse.com
				ParseQuery<ParseObject> allQuery = new ParseQuery<ParseObject>(
						"Associate");
				// Set Cache policy so that data is available offline
				allQuery.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
				// allocate query results to List
				object = allQuery.find();
				// Loop through query results
				for (ParseObject associate : object) {
					// Create new instance of Associate
					Associate map = new Associate();
					map.setId((String) associate.getObjectId());
					map.setFirstName((String) associate.get("firstName"));
					map.setLastName((String) associate.get("lastName"));
					map.setPhone((String) associate.get("phoneNumber"));
					map.setEmail((String) associate.get("email"));
					map.setCompId((String) associate.get("compId"));
					map.setStatus((String) associate.get("status"));
					// add items to allAssociateList
					allAssociateList.add(map);
				}
			} catch (ParseException e) {
				// Those errors need some Loggin'
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

			//Set Custom Adapter
			listview = (ListView) MainActivity.this.findViewById(R.id.listView);
			adapter = new CustomAdapter(context, allAssociateList);
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);

			break;
		case 2:
			// Log Button Selected
			Log.i("REMOTE DATA", "Part Time Selected");
			// Create list to hold queried data
			statusList = new ArrayList<Associate>();
			// try/catch query results
			try {
				// Locate the class table named "Associate" in Parse.com
				ParseQuery<ParseObject> statusQuery = new ParseQuery<ParseObject>(
						"Associate");
				// cache policy for retrieving data offline
				statusQuery.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
				// Loog for objects with status == Part Time
				statusQuery.whereEqualTo("status", "Part Time");
				// place query results in List
				statusObject = statusQuery.find();
				// Loop through objects
				for (ParseObject associate : statusObject) {
					// New instance of Associate
					Associate map = new Associate();
					map.setId((String) associate.getObjectId());
					map.setFirstName((String) associate.get("firstName"));
					map.setLastName((String) associate.get("lastName"));
					map.setPhone((String) associate.get("phoneNumber"));
					map.setEmail((String) associate.get("email"));
					map.setCompId((String) associate.get("compId"));
					map.setStatus((String) associate.get("status"));
					// Place items into statusList
					statusList.add(map);
				}
			} catch (ParseException e) {
				// Log the errors
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

			//Set Custom Adapter
			listview = (ListView) MainActivity.this.findViewById(R.id.listView);
			adapter = new CustomAdapter(context, statusList);
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);

			break;
		default:
			break;
		}
	}
}
