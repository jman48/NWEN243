package com.example.nwen243;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnItemSelectedListener{

	public final static String MODE = "com.example.NWEN243.MODE";
	public final static String ROUTE = "com.example.NWEN243.ROUTE";
	public final static String DIRECTION = "com.example.NWEN243.DIRECTION";
	
	private Intent intent;
	private boolean[] userInput = new boolean[4];
	private ArrayList<String[]> favorites = new ArrayList<String[]>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Initalize intent
		intent = new Intent(this, DisplayMessageActivity.class);
				
		//Spinner for selecting the mode
		Spinner mode_spinner = (Spinner) findViewById(R.id.mode_spinner);
		mode_spinner.setOnItemSelectedListener(this);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> mode_adapter = ArrayAdapter.createFromResource(this,
		        R.array.mode_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		mode_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mode_spinner.setAdapter(mode_adapter);
		
		//Spinner for selecting the route
		Spinner route_spinner = (Spinner) findViewById(R.id.route_spinner);
		route_spinner.setOnItemSelectedListener(this);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> route_adapter = ArrayAdapter.createFromResource(this,
		        R.array.route_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		route_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		route_spinner.setAdapter(route_adapter);
		
		//Spinner for selecting the mode
		Spinner dir_spinner = (Spinner) findViewById(R.id.direction_spinner);
		dir_spinner.setOnItemSelectedListener(this);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> dir_adapter = ArrayAdapter.createFromResource(this,
		        R.array.direction_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		dir_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		dir_spinner.setAdapter(dir_adapter);
		
		//Spinner for selecting favorite route
		Spinner fav_spinner = (Spinner) findViewById(R.id.fav_spinner);
		fav_spinner.setOnItemSelectedListener(this);
		
		
		//Receive a favorite route
		Intent intentFav = getIntent();
		String[] routeInfo = intentFav.getStringArrayExtra(DisplayMessageActivity.ROUTEINFO);
		if(routeInfo != null)
			favorites.add(routeInfo);
		if(favorites.size() > 0) {
			// Create an ArrayAdapter using the string array and a default spinner layout
			ArrayAdapter<String> fav_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, setUpFavs());
			// Specify the layout to use when the list of choices appears
			fav_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			fav_spinner.setAdapter(fav_adapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		Spinner spinner = (Spinner)parent;
		String text = (String)parent.getItemAtPosition(pos);
		if(spinner.getId() == R.id.mode_spinner && !text.equals("**Please Select**")) {
			//Is mode spinner
			intent.putExtra(MODE, text);
			userInput[0] = true;
		}
		if(spinner.getId() == R.id.route_spinner && !text.equals("**Please Select**")) {
			//Is route spinner
			intent.putExtra(ROUTE, text);
			userInput[1] = true;
		}
		if(spinner.getId() == R.id.direction_spinner && !text.equals("**Please Select**")) {
			//Is direction spinner
			intent.putExtra(DIRECTION, text);
			userInput[2] = true;
		}
		if(spinner.getId() == R.id.fav_spinner && text != null) {			
			for(String[] str : favorites) {
				if(str[0].equals(text)) {
					intent.putExtra(MODE, str[1]);
					intent.putExtra(ROUTE, str[2]);
					intent.putExtra(DIRECTION, str[3]);
					userInput[3] = true;
				}
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * If all selectors have a value then search for route
	 */
	public void findRoute(View view) {
		//Check that all fields have a selection
		if(userInput[0]  && userInput[1] && userInput[2])
			startActivity(intent);
	}
	
	public String[] setUpFavs() {
		String[] array_spinner = new String[favorites.size()];
		for(int i=0;i<favorites.size();i++) {
			array_spinner[i] = favorites.get(i)[0];
		}
		return array_spinner;
	}
	
	public void findFavRoute(View view) {
		if(userInput[3])
			startActivity(intent);
	}

}
