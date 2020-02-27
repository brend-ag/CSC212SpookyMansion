package edu.smith.cs.csc212.spooky;

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
//	private Set<String> visitedHallway = new HasSet<>
	//set = set of id's the player has seen before
	//set (self) bc UNIQUE! 
	//i was gonna say only add fr one side etc, makes sense for situation, but i think that's a queue
	
	//doens't matter if this one is priv or pub
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

	public boolean hasBeenHere() {
		return this.visited.contains(this.place);
		//pretend player thinks has been everywhere
	} //for visited = coded before mode ,do to moveto; as move to new place as to visited
	//boolean bc want to use in if s and pub to do in other classes 

}


