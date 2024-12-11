package geography;

import java.util.*;

/**
 * A geographic object representing a river
 */
 
public class River extends GeographicObject {
	
    /**
     * length holds the length of the river
     */
	 
    public double length;

    /**
     * Creates a new River object.
     */
	 
    public River() {
        super();
    }

    /**
     * Returns the length of the river.
     *
     * @return the length of the river.
     */
	 
    public double getLength() {
        return length;
    }
}