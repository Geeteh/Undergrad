package geography;

import java.util.*;

/**
 * A geographic object representing a city within a region
 */
 
public class City extends State {
	
    /**
     * city holds the region that a city belongs to.
     */
	 
    private City city;
	private List<City> cities;
	private City capital;

    /**
     * Creates a new City object
     */
	 
    public City() {
        super();
    }
	
	/**
     * Returns the capital city of a region.
     *
     * @return the capital city of a region
     */
	 
	public City capital() {
        return capital;
    }

    /**
     * Returns the list of cities in a region.
     *
     * @return the list of cities in a region.
     */
	 
    public List<City> getCities() {
        return cities;
    }
}