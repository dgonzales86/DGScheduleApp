package DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerQuery {

    // TABLE_CUSTOMERS = "Customers";
    // COLUMN_CUSTOMER_ID = "Customer_ID";
    // COLUMN_CUSTOMER_NAME = "Customer_Name";
    // COLUMN_CUSTOMER_ADDRESS = "Address";
    // COLUMN_CUSTOMER_POSTAL_CODE = "Postal_Code";
    // COLUMN_CUSTOMER_DIVISION_ID = "Division_ID";


    public static int insertCustomer(String Customer_Name, String Address, String Postal_Code, String Division_ID) throws SQLException {
        String sql = "INSERT INTO Customers (Customer_Name,Address,Postal_Code,Division_ID) VALUES(?,?,?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Customer_Name);
        ps.setString(2,Address);
        ps.setString(3,Postal_Code);
        ps.setString(4,Division_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Insert Sucessful!");
        }else System.out.println("Insert Failed!");
        return rowsAffected;
    }

    public static int updateCustomer(String Customer_Name, String Address, String Postal_Code, String Division_ID) throws SQLException {
        String sql = "UPDATE Customers SET Customer_Name = ?, Address = ?,Postal_Code=?, Division_ID= ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Customer_Name);
        ps.setString(2,Address);
        ps.setString(3,Postal_Code);
        ps.setString(4,Division_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Insert Sucessful!");
        }else System.out.println("Insert Failed!");
        return rowsAffected;
    }

    public static int deleteCustomer(String User_Name, int User_ID) throws SQLException {
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