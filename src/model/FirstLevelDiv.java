package model;

/**
 * FirstLevelDiv Class
 */
public class FirstLevelDiv {
    private int divisionID;
    private String division;
    private int countryID;

    /**
     * Constructor for new FirstLevelDiv objects
     * @param divisionID - sets division id for new FirstLevelDiv objects
     * @param division - sets division for new FirstLevelDiv objects
     * @param countryID - sets country id for new FirstLevelDiv objects
     */
    public FirstLevelDiv(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * Overrides default toString() method
     * @return - returns String value of division and divisionID
     */
    @Override
    public String toString() {
        return (division + " " + divisionID);
    }

    /**
     * Getter for FirstLevelDiv divisionID
     * @return - returns int divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Setter for FirstLevelDiv divisionID
     * @param divisionID - sets this.divisionID to divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Getter for FirstLevelDiv division
     * @return - returns String division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Setter for FirstLevelDiv division
     * @param division - sets this.division to division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Getter for FirstLevelDiv countryID
     * @return - returns int countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Setter for FirstLevelDiv countryID
     * @param countryID - sets this.countryID to countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
