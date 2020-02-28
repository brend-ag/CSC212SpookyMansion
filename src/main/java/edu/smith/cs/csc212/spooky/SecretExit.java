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

	//static String secretExits
}

//exits aren't secret but secretexits are 
//until you search for them thru search method (self)
//issec 
// secretexit should have priv boolean hidden
//when user types search; 
//searches if there's a secretexit in that rm
//and should be made visible
// void search meth @ place that calls search for all exits
//override void search on sec so that it's not hidden anymore
//put secretexit fr basem to secretrm OR a secretexit in own custom game
//should practice w fr basement to sec and then implem in my own game
//basically ignoring secretexit bc implemented as exit; so all that coding we did for secretexit = can just refer to it as exit obj, not even list needed
//bc still an exit but secr = just slipped-in class that allows for the visibility of an exit to be "flipped"
