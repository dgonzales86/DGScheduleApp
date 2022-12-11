package DBConnection;

import model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserQuery {
    // TABLE_USERS = "users"
    // COLUMN_USER_ID = "User_ID"
    // COLUMN_USER_NAME = "User_Name"
    // COLUMN_USER_PASSWORD ="Password"

    public static int insert(int User_ID,String User_Name, String Password) throws SQLException {
        String sql = "INSERT INTO users (User_ID,User_Name,Password) VALUES(?,?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1,User_ID);
        ps.setString(2,User_Name);
        ps.setString(3,Password);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Insert Sucessful!");
        }else System.out.println("Insert Failed!");
        return rowsAffected;
    }
    public static int updateUserName(String User_Name, int User_ID) throws SQLException {
        String sql = "UPDATE users SET User_Name = ? WHERE User_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(2,User_Name);
        ps.setInt(1,User_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Update Sucessful!");
        }else System.out.println("Update Failed!");
        return rowsAffected;
    }
//    public static int updateUserPassword(String User_Password,  User_ID) throws SQLException {
//        String sql = "UPDATE users SET User_Password = ? WHERE User_ID = ?";
//        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
//        ps.setString(2,User_Name);
//        ps.setInt(1,User_ID);
//        int rowsAffected = ps.executeUpdate();
//        if(rowsAffected > 0){
//            System.out.println("Update Sucessful!");
//        }else System.out.println("Update Failed!");
//        return rowsAffected;
//    }
}
