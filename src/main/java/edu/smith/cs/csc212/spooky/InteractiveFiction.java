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
		
		Player player = new Player(game.getStart()); 

		//infinite loop
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(player.getPlace());
			
			System.out.println();
			System.out.println("... --- ...");
			//System.out.println("Type 'h' for help on how to play and how to quit the game!");
			//above = commented instructions
			
			System.out.println(here.getDescription());
			if(player.hasBeenHere() ) { //need to know where currently are and where been
				System.out.println("Hm...Somehow, this place feels familiar...");
			} 
			if (here.isTerminalState()) {
				break; 
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits(); 

			for (int i=0; i<exits.size(); i++) {
				Exit e = exits.get(i);
				System.out.println(" "+i+". " + e.getDescription()); //number.desc and goes to that exit
			} 
			

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords("?"); //gives back words and splits for punct etc
			if (words.size() > 1) {
				System.out.println("Only give the system 1 word at a time!");
				continue; //brings back to top of loop to desc again
			}

			// Get the word they typed as lowercase, and no spaces.

			String action = words.get(0).toLowerCase().trim(); //trim no space 

			if (action.equals("q")) {  //word = trigger
				if (input.confirm("Are you sure you want to quit?")) { 
					// quit!
					break;
				} else { // go to the top of the game loop!
					continue; 
				
				}
			}
			if (action.equals("h")) {  //word = trigger
				System.out.println("To navigate the mansion, press the number of the room or area you want to enter.\n" 
					+ "If you want to quit the game, press 'q'."
					+ "If you want to see your items, type 'items'." );
				continue;
			}
			//if "items", print items through stuff()
			if (action.equals("items")){
				here.stuff();
			}
			
			//searches through the exits if the user types search
			if (action.equals("search")) {
				here.searchAllExits();
				continue;
				//here =for the place object even though looking at the location of the player
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
			Exit destination = exits.get(exitNum); 
			if (destination.canOpen(player)) { 
				player.moveTo(destination.getTarget());
			} else {
				System.out.println("This is locked right now. Maybe you can open it with a key?");
			}
		} //while loop of game
		//rungame returns where player got to
		return player.getPlace();
	} //rungame method

	
	/**
	 * This is where we play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();
		// Actually play the game.
		
		runGame(input, game);

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println("\n\n>>> GAME OVER <<<");
	}

}

