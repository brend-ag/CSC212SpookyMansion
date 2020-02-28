package edu.smith.cs.csc212.spooky;
/*
 * Sets the game time, returns it, and increases it 
 * @param hour: takes in a certain int hour
 */
public class GameTime {
	int hour;

	public GameTime(int hour) {
		this.hour = hour;
	}
/*
 * Returns the hour
 */
	private int getHour() {
		return hour;
	}
/*
 * increases the hour by 1
 */
	private void increaseHour() {
		hour+=1;
	}
	
}
