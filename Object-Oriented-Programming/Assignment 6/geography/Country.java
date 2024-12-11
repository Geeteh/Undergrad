package geography;

/**
 * A geographic object representing a country, which includes cities and a capital city
 */
 
public class Country extends Boundary {
    /**
     * country holds a country that belongs to a certain a region
     */
    public Country country;

    /**
     * Creates a new country object
     */
    public Country() {
        super();
    }

	 /**
     * Returns the country that belongs to a certain region
     *
     * @return the country that belongs to a certain region
     */
	 
    public Country getCountry() {
        return country;
    }
}