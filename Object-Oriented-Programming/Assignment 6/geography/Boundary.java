package geography;

import java.util.*;

/**
 * A Boundary represents a line that separates two geographic objects, such as countries or states.
 * It has a length, and is bordered by one or more countries.
 */
 
public class Boundary extends GeographicObject {
	
    /**
     * The two neighboring geographic objects that are separated by the boundary.
     */
	 
    private GeographicObject neighbor1;
    private GeographicObject neighbor2;

    /**
     * boundaryLength holds the length of the boundary.
	 * borders holds the bordering regions
     */
	 
    private double boundaryLength;
    private List<Country> borders;

	/**
	  * Creates a new boundary object
	  */

    public Boundary() {
        super();
    }

    /**
     * Returns a list of the neighboring geographic objects that are separated by this boundary.
     *
     * @param neighbor1 the first neighboring geographic object
     * @param neighbor2 the second neighboring geographic object
     * @return a list of the neighboring geographic objects.
     */
	 
    public List<GeographicObject> neighbors(GeographicObject neighbor1, GeographicObject neighbor2) {
        return Arrays.asList(neighbor1, neighbor2);
    }

    /**
     * Returns a list of the countries that border the boundary.
     *
     * @return a list of the countries that border the boundary.
     */
	 
    public List<Country> borderOf() {
        return borders;
    }

    /**
     * Returns the length of the boundary.
     *
     * @return the length of the boundary.
     */
	 
    public double boundaryLength() {
        return boundaryLength;
    }
}