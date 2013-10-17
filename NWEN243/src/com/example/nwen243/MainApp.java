package com.example.nwen243;

import java.util.ArrayList;

import android.app.Application;

/**
 * Used to keep data that is shared between activity's
 * @author John Armstrong
 */
public class MainApp extends Application{
	//The list of all favorite routes
	private ArrayList<String[]> favorites = new ArrayList<String[]>();
	
	/**
	 * Add a favorite route
	 * @param routeInfo - The route to add
	 */
	public void addFav(String[] routeInfo) {
		favorites.add(routeInfo);
	}
	
	public ArrayList<String[]> getFavs() {
		return favorites;
	}
}
