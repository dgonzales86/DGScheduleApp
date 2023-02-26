package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDiv;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FirstLevelDivQuery Class
 */
public class FirstLevelDivQuery {
    private static ObservableList<FirstLevelDiv> allFirstLevelDivs = FXCollections.observableArrayList();
    private static ObservableList<FirstLevelDiv> sortedFirstLevelDivs = FXCollections.observableArrayList();

    /**
     * Queries database for all first level divisions and orders by division.
     * Adds results to ObservableList allFirstLevelDivs
     * @return allFirstLevelDivs
     * @throws SQLException via SELECT database query statement
     */
    public static ObservableList<FirstLevelDiv> populateFirstLevelDivs() throws SQLException{

        if(allFirstLevelDivs.size() == 0){
            String sql = "SELECT * FROM first_level_divisions ORDER BY Division";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int divID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                allFirstLevelDivs.add(new FirstLevelDiv(divID,division,countryID));

            }

        }
        return allFirstLevelDivs;
    }

    /**
     * Returns populateFirstLevelDivs();
     * @return populateFirstLevelDivs
     * @throws SQLException via populateFirstLevelDivs
     */
    public static ObservableList<FirstLevelDiv> getAllFirstLevelDivs() throws SQLException {

        return populateFirstLevelDivs();
    }

    /**
     * Populates a sorted ObservableList with division that match the passed countryID variable
     * @param countryID int variable represents countryID in database
     * @return sortedFirstLevelDivs
     * @throws SQLException via SELECT database query
     */
    public static ObservableList<FirstLevelDiv> populateSortedFirstLevelDivs(int countryID) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID =?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1,countryID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int divID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countID = rs.getInt("Country_ID");
            sortedFirstLevelDivs.add(new FirstLevelDiv(divID,division,countID));
        }
        return sortedFirstLevelDivs;
    }

    /**
     * Getter for sortedFirstLevelDivs
     * @return sortedFirstLevelDivs
     */
    public static ObservableList<FirstLevelDiv> getSortedFirstLevelDivs(){
        return sortedFirstLevelDivs;
    }

    /**
     * Checks divs in getAllFirstLevelDivs(); if  div.getDivisionID() matches passed
     * int variable divID, div is returned
     * @param divID int variable used to check for a match
     * @return returns div
     * @throws SQLException via getAllFirstLevelDivs()
     */
    public static FirstLevelDiv getDivision(int divID) throws SQLException {
        for(FirstLevelDiv div: getAllFirstLevelDivs()){
            if(div.getDivisionID() == divID){
                return div;
            }
        }
        return null;
    }

}
