package edu.smith.cs.csc212.spooky;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * This class represents all of the game state needed to understand the player.
 * At the beginning of this project, it is just the ID or name of a place.
 * 
 * @author jfoley
 *
 */
public class Player {
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private String place;
	private Set<String> visited = new HashSet<>();
	private List<String> items = new ArrayList<>();
	//set = set of id's the player has seen before

	/**
	 * A player is created at the start of a game with just an initial place.
	 * @param initialPlace - where do we start?
	 */
	public Player(String initialPlace) { // place is a fancy string lol
		this.place = initialPlace;
		this.visited = new HashSet<>();
	}
	
	/**
	 * Get access to the place instance variable from outside this class.
	 * @return the id of the current location.
	 */
	public String getPlace() {
		return place;
	}
	
	/**
	 * Call this method when the player moves to a new place.
	 * @param place - the place we are now located at.
	 */
	public void moveTo(String place) {
		this.place = place;
		 //visited will grow as go
	}
	public void rememberThisPlace() {
		this.visited.add(place); 
	}
	/*
	 * boolean tracks if the current location is in the hashset of the visited places
	 *of the player
	 */
	public boolean hasBeenHere() {
		return this.visited.contains(this.place);
		
	} 

}


