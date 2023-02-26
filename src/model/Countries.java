package model;

/**
 * Countries Class
 */
public class Countries {

    private int countryID;
    private String country;

    /**
     * Countries constructor for new Countries object
     * @param countryID - adds a country id for a new Country object
     * @param country - adds a country to a new Country object
     */
    public Countries(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    /**
     * Overrides default toString() method
     * @return - returns String value of countryID and country
     */
    @Override
    public String toString(){
        return (Integer.toString(countryID) + " " + country);
    }

    /**
     * Getter for Countries countryID
     * @return - returns int countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Setter for Countries countryID
     * @param countryID - sets this.countryID to countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Getter for Countries country
     * @return - returns String country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter for Countries country
     * @param country - sets this.country to country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
