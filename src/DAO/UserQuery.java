package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserQuery Class
 */
public class UserQuery {

    public static ObservableList<User> allUsers = FXCollections.observableArrayList();

    /**
     * Queries database for users and adds results to an ObservableList
     * @return allUsers
     * @throws SQLException via database query
     */
    public static ObservableList<User> populateUsers() throws SQLException {
        if (allUsers.size() == 0){
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPass = rs.getString("Password");
                allUsers.add(new User(userID,userName,userPass));
            }
        }
        return allUsers;
    }

    /**
     * Counts users in database
     * @return integer count
     * @throws SQLException via db query
     */
    public static int userCount() throws SQLException {
        int count = 0;
        String sql = "SELECT count(User_Name) FROM users";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            count = rs.getInt(1);
        }
        return count;
    }

    /**
     * Queries database for users with a user id matching the passed User_ID variable
     * @param User_ID int variable representing User_ID column in database
     * @return returns found user
     * @throws SQLException via db Query
     */
    public static User getUser(int User_ID) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1,User_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String userPass = rs.getString("Password");
            return new User(userID,userName,userPass);
        }
        return null;
    }

}
