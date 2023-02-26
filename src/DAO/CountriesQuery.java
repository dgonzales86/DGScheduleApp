package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.FirstLevelDiv;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CountriesQuery Class
 */
public class CountriesQuery {
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Countries> sortedCountries = FXCollections.observableArrayList();

    /**
     * Queries database for all countries
     * Adds countries to ObservableList allCountries
     * @return allCountries
     * @throws SQLException via database query
     */
    public static ObservableList<Countries> populateCountries() throws SQLException {
        if(allCountries.size()==0) {
            String sql = "SELECT * FROM Countries";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                allCountries.add(new Countries(countryID, country));

            }
        }
        return allCountries;
    }

    /**
     * Getter for ObservableList allCountries
     * @return allCountries
     */
    public static ObservableList<Countries>getAllCountries(){
        return allCountries;
    }

    /**
     * Queries database for countries that have a Country_ID matching the passed divID variable
     * @param divID int variable representing division id
     * @return if a match found, returns countries, else returns null
     * @throws SQLException via database query
     */
    public static Countries getCountryByDiv(int divID) throws SQLException {

        FirstLevelDiv div = FirstLevelDivQuery.getDivision(divID);

        for (Countries countries: getAllCountries()){
            if(countries.getCountryID() == div.getCountryID()){
                return countries;
            }
        }
        return null;

    }
}
