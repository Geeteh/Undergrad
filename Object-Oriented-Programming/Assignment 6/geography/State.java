package geography;

import java.util.*;

/**
 * A geographic object representing a state within a region
 */
 
public class State extends Country {
	
    /**
     * state holds the region that the state belongs to.
     */
	 
    public State state;

    /**
     * Creates a new State object
     */
	 
    public State() {
        super();
    }

	/**
     * Returns the state that a region belongs to
     *
     * @return the state that a region belongs to
     */
	
    public State getState() {
        return state;
    }
}