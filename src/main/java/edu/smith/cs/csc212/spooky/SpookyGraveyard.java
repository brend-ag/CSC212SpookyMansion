package edu.smith.cs.csc212.spooky;

import java.util.HashMap;
import java.util.Map;

public class SpookyGraveyard implements GameWorld { 
	private Map<String, Place> placesG = new HashMap<>();
	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "entranceGraveyard";
	}

	/**
	 * This constructor builds our SpookyMansion game.
	 */
	public SpookyGraveyard() {
		Place entranceGraveyard = insert(
				Place.create("entranceGraveyard", "You mysteriously awaken in seems to be a desolate graveyard. \n" +
		" Barely seeing through the heavy fog and formidable darkness, you notice a tall gate behind you is closed shut. Definitely too tall to climb. It's length also seems neverending.  \n"
						+ "Guess the only direction to go is forward."));
			
		entranceGraveyard.addExit(new Exit("altar tomb", "Wander to your right.")); 
		entranceGraveyard.addExit(new Exit("gravestone", "Walk forward."));
		entranceGraveyard.addExit(new Exit("family plot", "Head towards your left. "));


		Place altarTomb = insert(
				Place.create("altar tomb", "You walk to what appears to be an altar tomb. \n" + "It is decorated with detailed drawings too distrubing to describe.\n"
						+ "You decide to look away.\n" +
						"You do notice something glinting behind the tomb."));
		altarTomb.addExit(new Exit("sacrophagus", "Look behind it."));
		altarTomb.addExit(new Exit("entranceGraveyard", "Walk left."));
		

		Place sacrophagus = insert(
				Place.create("sacrophagus", "Huh? Although having lost its color and definitely in need of a cleaning, you see a sacrophagus.\n" +
		"Somehow, the situation you find yourself in has become more unusual. You notice it slightly open."));
		sacrophagus.addExit(new Exit("sacrophagusNote", "Look closer."));
		sacrophagus.addExit(new Exit("attic", "You suddenly see what might be a ledge you can grab unto. Reach out."));
		sacrophagus.addExit(new Exit("altarTomb", "Go around back to the altar tomb."));
		
		//riddle borrowed from: https://www.riddles.nu/topics/darkness/
		Place sacrophagusNote = insert(Place.create("sacrophagusNote",
				"If you squint, you can see an inscription that reads:\n+"
				+ "'It can not be seen whenver it's there.\n'" 
				+ "'It fills up a room, much like air.\n'"
				+ "'It cannot be touched, and there's nothing to hear.\n'"
				+ "'Yet, it is quite harmless, so there's nothing to fear.\n'"
				+ "You're not sure what this eerie riddle means, but you decide to keep it in mind."));
		sacrophagusNote.addExit(new Exit("sacrophagus", "Return to the sacrophagus."));
		
		
		Place gravestone = insert(Place.create("gravestone", "You find a cracked, old gravestone with decayed flowers that reads: \n"
				+ "'Silvia De Los Cadaveres\n" 
				+ "To move forward is to go into territory in which you are not welcome.\n"
				+ "Choose wisely.' \n"
	           + "Well that's a warm message."));
		gravestone.addExit(new Exit("darkness0", "Continue forward past the gravestone."));
		gravestone.addExit(new Exit("entranceGraveyard", "Go back."));

		
		int darknessDepth = 3; //3 choices = 3 for depth
		int lastDarkPart = darknessDepth - 1; //so that when go back = knows how far you have to go (self)
		int darknessVisited = 0;
		for (int i = 0; i < darknessDepth; i++) { 
			Place darkness = insert(Place.create("darkness" + i, "Somehow, the darkness seems to intensify. You start to feel as if some ominous force surrounds you."));
					//"This is a very long hallway."));
			if (i == 0) { //for loop w i==0 like constructor (so 1st hallway connects w secretrm
				darkness.addExit(new Exit("gravestone", "Go back."));
			} else {
				darkness.addExit(new Exit("darkness" + (i - 1), "Go back."));
			}
			if (i != lastDarkPart) {
					darkness.addExit(new Exit("darkness" + (i + 1), 
						"To your left, a mysterious " + darknessVisited  + 
						" appears to be scratched on the wall. " 
						+ "Go forward."));
				
				darknessVisited+=1;
				
			} else {
				darkness.addExit(new Exit("death", "You walk towards nothingness as the night grows completely dark. Not even the moonlight guides your way."));
			}  
		
		}
        
		Place death = insert(Place.terminal("death", "You feel the ominous force swiftly moving towards you.\n"
				+ "You quickly turn around, but it's too late. \n" + 
				"A figure of nothingness grabs you and you suddenly lose consciousness."));
	
		
		Place familyPlot = insert(
				Place.create("family plot", "An arrangement of tombstones appear in your vision. You notice the names all end in Esqueleto and the dates of death are the same.\n"
						+ "You look down in reverence to the tragedy that must have taken this lives of this family."));
	
		familyPlot.addExit(new Exit("mausoleum", "Move forward."));
		familyPlot.addExit(new Exit("entranceGraveyard", "Walk right."));

		
		Place mausoleum = insert(Place.create("mausoleum", "You find a run-down mausoleum. Its rotting wooden door lies uselessly on the ground."));
		mausoleum.addExit(new Exit("enterM", "Walk inside."));
		mausoleum.addExit(new Exit("family plot", "Walk back."));

		
		Place enterM = insert(Place.create("enterM", "You enter the mausoleum. Scattered bones and ashes are littered across the room\n."
				+ "As you walk into the darkness, you step on what sounds like metal doors. Cellar doors?"));
		enterM.addExit(new Exit("catacombs", "Try to open the doors."));
		enterM.addExit(new Exit("mausoleum", "Go back."));
		
		Place catacombs = insert(Place.terminal("catacombs", "You wipe off the dirt from the rusty doors with your feet and pry the cellar doors open.\n"
				+ "You descend. Thankfully, a single lamp still burns, lighting a part of the area. \n" + 
				"You accidentally step on a piece of paper. It's a map!\n" 
				+ "Using the bit of light you have, you read that you are near an exit that leads out the graveyard.\n"
				+"Finally. A means to get back home."));
	
		// Make sure your graph makes sense!
	//	checkAllExitsGoSomewhereG();  //checks that exits exist! 
	}

	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private Place insert(Place p) {
		placesG.put(p.getId(), p);
		return p;
	}

	/**
	 * I like this method for checking to make sure that my graph makes sense!
	 */
	private void checkAllExitsGoSomewhereG() {
		boolean missing = false;
		// For every place:
		for (Place p : placesG.values()) {
			// For every exit from that place:
			for (Exit x : p.getVisibleExits()) {
				// That exit goes to somewhere that exists!
				if (!placesG.containsKey(x.getTarget())) {
					// Don't leave immediately, but check everything all at once.
					missing = true;
					// Print every exit with a missing place:
					System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
				}
			}
		}

		// Now that we've checked every exit for every place, crash if we printed any
		// errors.
		if (missing) {
			throw new RuntimeException("You have some exits to nowhere!");
		}
	}

	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		return this.placesG.get(id);
	}
}