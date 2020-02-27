package edu.smith.cs.csc212.spooky;

import java.util.List;

/**
 * This is our main class for SpookyMansion.
 * It interacts with a GameWorld and handles user-input.
 * It can play any game, really.
 *
 * @author jfoley
 *
 */
public class InteractiveFiction {
	
	/**
	 * This method actually plays the game.
	 * @param input - a helper object to ask the user questions.
	 * @param game - the places and exits that make up the game we're playing.
	 * @return where - the place the player finished.
	 */
	static String runGame(TextInput input, GameWorld game) {
		// This is the current location of the player (initialize as start).
		Player player = new Player(game.getStart()); //start at gamestart where you set it!
		//can put system ln if wanna know how to play game type help and press enter
		//and do some axn thing for help
		
		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(player.getPlace());
			//Place place = new Place();
			System.out.println();
			System.out.println("... --- ...");
			System.out.println("Type 'h' for help on how to play and how to quit the game!");
			//do if place statement so that doesn't appear on every screen
			
			System.out.println(here.getDescription());
			if(player.hasBeenHere() ) { //need to know where currently are and where been
				System.out.println("Hm.....This place feels familiar...");
			} //make stand out w >>> or smth 
			// Game over after print!
			player.rememberThisPlace(); //adding to set
			if (here.isTerminalState()) {
				break; //while true loops = used in data structures! tho style might not be for everyone
				//break = game over :)
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits(); //make sec class can't see unless search

			for (int i=0; i<exits.size(); i++) {
				Exit e = exits.get(i);
				System.out.println(" "+i+". " + e.getDescription()); //number.desc and goes to that exit
			} 
			List<SecretExit> secretExits = here.getInvisibleExits(); //make sec class can't see unless search

			for (int i=0; i<secretExits.size(); i++) {
				SecretExit e = secretExits.get(i);
				System.out.println(" "+(i+2)+". " + e.getDescription()); //number.desc and goes to that exit
			} 

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords("?"); //gives back words and splits for punct etc
			if (words.size() > 1) { //can't handle >1 word for secret code thing, put before this if statement
				System.out.println("Only give the system 1 word at a time!");
				continue; //brings back to top of loop to desc again
			}

			// Get the word they typed as lowercase, and no spaces.
			// Do not uppercase action -- I have lowercased it.
			String action = words.get(0).toLowerCase().trim(); //trim no space 

			if (action.equals("q")) {  //word = trigger
				if (input.confirm("Are you sure you want to quit?")) { //abt confirm not action so don't get tripped up here lol
					// quit!
					break;
				} else { // go to the top of the game loop!
					continue; 
					//don't put if action.equals help, in action.equals quit so can't both be true
				 //put help outside if statem.
					//don't forget cont to return to top of loop bc might want word to be number or smth
				}
			}
			if (action.equals("h")) {  //word = trigger
				System.out.println("To navigate the mansion, press the number of the room or area you want to enter.\n" 
					+ "If you want to quit the game, press 'q'.");
				continue;
			}
//			if (action.equals("graveyard")) {  //word = trigger
//				game = new SpookyGraveyard();
//				runGame(input, game);
//				continue;
//			}
			if (action.equals("search")) {
				here.searchAllExits();
				continue;
				//here for the place object even though looking at the location of the player
			}
			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try { //useful to label } 
				exitNum = Integer.parseInt(action); //turn into int; if not num below or not good num lol
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = exits.get(exitNum); //get exit num like 0 1 2 etc of list
			if (destination.canOpen(player)) { //if player cn open it move there
				player.moveTo(destination.getTarget());
			} else {
				// TODO: some kind of message about it being locked?
			}
		} //while loop of game
		//rungame returns where player got to
		return player.getPlace();
	} //rungame method
	//there are ways to break it up bc not good style to have sup long meth but useful here; gets confus tho
	
	/**
	 * This is where we play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();
		//GameWorld game2 = new SpookyGraveyard();
		// Actually play the game.
		runGame(input, game);
		

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println("\n\n>>> GAME OVER <<<");
	}

}
//closing stuff top right of running (won't notice, goes when close ecl. press red square then x all them)

