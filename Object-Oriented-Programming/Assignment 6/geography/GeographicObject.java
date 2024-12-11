package geography;

import java.util.*;

/**
  * A GeographicObject represents any entity of Geography, regional or structural.
  */

public class GeographicObject {
	/**
	  * Area holds the data for the size of a geographic object
	  * Distance holds the length for a geographic object
	  */
    public double area;
	public double distance;
	
	/**
	  * area() returns the area of geographic objects
	  * @return the area of geographic objects
	  */
	
	public double area() {
		return area;
	}
	
	/**
	  * distance() returns the distance to other geographic objects
	  * @return the distance to other geographic objects
	  */
	
	public double distance() {
		return distance;
	}
}