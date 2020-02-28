package edu.smith.cs.csc212.spooky;
/**
 * Creates a secret exit with the use of the hidden boolean.
 * Used with the Exit object.
 * @param target - where the exits goes
 * @param description - describes the exit
 * @author bgutierrez
 *
 */
public class SecretExit extends Exit {
	//Exit exit;
	private boolean hidden = true;
	
	
	public SecretExit(String target, String description) {
		super(target, description);
	}
	/*
	 * returns hidden, which is originally true
	 */
	@Override 
	public boolean isSecret() {
		return hidden;
	}
	/*sets the hidden boolean to false so that the exit is not secret anymore
	 * 
	 */
	@Override
	public void search() {
		//makes sure that the exit is not secret anymore
		hidden = false;
	}

}

