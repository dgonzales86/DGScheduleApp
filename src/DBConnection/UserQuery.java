package DBConnection;

import model.User;
import model.UserIDCounter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserQuery {
    // TABLE_USERS = "users"
    // COLUMN_USER_ID = "User_ID"
    // COLUMN_USER_NAME = "User_Name"
    // COLUMN_USER_PASSWORD ="Password"



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


    public static int updateUserName(String User_Name, int User_ID) throws SQLException {
        String sql = "UPDATE users SET User_Name = ? WHERE User_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,User_Name);
        ps.setInt(2,User_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Update Sucessful!");
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
            System.out.println("Delete Sucessful!");
        }else System.out.println("Delete Failed!");
        return rowsAffected;
    }
}
