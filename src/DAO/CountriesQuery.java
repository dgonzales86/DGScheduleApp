package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesQuery {

//    TABLE_COUNTRIES = "countries";
//    COLUMN_COUNTRY_ID = "Country_ID";
//    COLUMN_COUNTRY = "Country";

    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    private static ObservableList<Countries> sortedCountries = FXCollections.observableArrayList();

    public static ObservableList<Countries> populateCountries() throws SQLException {
        String sql = "SELECT * FROM Countries";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int countryID = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                allCountries.add(new Countries(countryID,country));

            }
        return allCountries;
    }




    public static ObservableList<Countries>getAllCountries(){
        return allCountries;
    }


    public static ObservableList<Countries>populateSortedCountries(int countryID) throws SQLException {
        String sql = "SELECT * FROM Countries WHERE Country_ID =?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1,countryID);
        ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int countID = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                sortedCountries.add(new Countries(countID,country));
            }
            return sortedCountries;
        }

        public static ObservableList<Countries> getSortedCountries(){
        return sortedCountries;
        }


}
