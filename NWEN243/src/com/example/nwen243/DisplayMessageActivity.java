package com.example.nwen243;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class DisplayMessageActivity extends Activity {
	
	public final static String ROUTEINFO = "com.example.myfirstapp.ROUTEINFO";
	String mode;
	String route;
	String direction;
	
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		
		Intent intent = getIntent();
		mode = intent.getStringExtra(MainActivity.MODE);
		route = intent.getStringExtra(MainActivity.ROUTE);
		direction = intent.getStringExtra(MainActivity.DIRECTION);
		
		//Construct the url
		String urlEnd = mode + "/" + route + "/" + direction;
		 
		WebView myWebView = (WebView) findViewById(R.id.webview); 
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.loadUrl("http://m.metlink.org.nz/timetables/" + urlEnd); 

		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addToFav(View view) {
		//Construct a list containing route information
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String routeName = editText.getText().toString();
		
		String[] routeInfo = new String[4];
		routeInfo[0] = routeName;
		routeInfo[1] = mode;
		routeInfo[2] = route;
		routeInfo[3] = direction;
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(ROUTEINFO, routeInfo);
		startActivity(intent);
	}

}
