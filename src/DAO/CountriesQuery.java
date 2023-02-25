package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.FirstLevelDiv;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesQuery {
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Countries> sortedCountries = FXCollections.observableArrayList();
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
