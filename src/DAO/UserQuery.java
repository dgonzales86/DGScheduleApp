package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {
    // TABLE_USERS = "users"
    // COLUMN_USER_ID = "User_ID"
    // COLUMN_USER_NAME = "User_Name"
    // COLUMN_USER_PASSWORD ="Password"

public static ObservableList<User> allUsers = FXCollections.observableArrayList();


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

    public static int insertUser(String User_Name, String Password) throws SQLException {
        String sql = "INSERT INTO users (User_Name,Password) VALUES(?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,User_Name);
        ps.setString(2,Password);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Insert Sucessful!");
        }else System.out.println("Insert Failed!");
        return rowsAffected;
    }

    public static void selectUser(String User_Name, int User_ID) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ? AND User_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,User_Name);
        ps.setInt(2,User_ID);

    }

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


    public static int updateUserName(String User_Name, int User_ID) throws SQLException {
        String sql = "UPDATE users SET User_Name = ? WHERE User_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,User_Name);
        ps.setInt(2,User_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Update Successful!");
        }else System.out.println("Update Failed!");
        return rowsAffected;
    }
    public static int deleteUser(String User_Name, int User_ID) throws SQLException {
        String sql = "DELETE FROM users WHERE User_Name = ? AND User_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,User_Name);
        ps.setInt(2,User_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Delete Successful!");
        }else System.out.println("Delete Failed!");
        return rowsAffected;
    }
}
