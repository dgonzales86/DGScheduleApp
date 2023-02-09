package DAO;

import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDiv;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivQuery {

//    TABLE_FIRST_LEVEL_DIVISIONS = "first_level_divisions";
//    COLUMN_DIVISION_ID = "Division_ID";
//    COLUMN_DIVISION = "Division";
//    COLUMN_COUNTRY_ID = "Country_ID";

    private static ObservableList<FirstLevelDiv> allFirstLevelDivs = FXCollections.observableArrayList();
    private static ObservableList<FirstLevelDiv> sortedFirstLevelDivs = FXCollections.observableArrayList();

// populate first level div should populate country id
    // SELECT statement should have WHERE clause for countryID
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

    public static ObservableList<FirstLevelDiv> getAllFirstLevelDivs() throws SQLException {

        return populateFirstLevelDivs();
    }

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
    public static ObservableList<FirstLevelDiv> getSortedFirstLevelDivs(){
        return sortedFirstLevelDivs;
    }

    public static FirstLevelDiv getDivision(int divID) throws SQLException {
        for(FirstLevelDiv div: getAllFirstLevelDivs()){
            if(div.getDivisionID() == divID){
                return div;
            }
        }
        return null;
    }

}
