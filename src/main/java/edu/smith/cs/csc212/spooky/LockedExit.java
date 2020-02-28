package edu.smith.cs.csc212.spooky;
/**
 * Creates a locked exit.
 * @param target - where the exit goes
 * @param description - description of the exit
 * @author bgutierrez
 *
 */
public class LockedExit extends Exit{
	public LockedExit(String target, String description) {
		super(target, description);
	}
	// boolean returns true so that the exit can be opened
	@Override
	public boolean canOpen(Player player) {
		// DO NOT CHANGE THIS METHOD. ONLY OVERRIDE IN A SUBCLASS.
		return true;
	}
}
