package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersQuery {

    // TABLE_CUSTOMERS = "Customers";
    // COLUMN_CUSTOMER_ID = "Customer_ID";
    // COLUMN_CUSTOMER_NAME = "Customer_Name";
    // COLUMN_CUSTOMER_ADDRESS = "Address";
    // COLUMN_CUSTOMER_PHONE = "Phone"
    // COLUMN_CUSTOMER_POSTAL_CODE = "Postal_Code";
    // COLUMN_CUSTOMER_DIVISION_ID = "Division_ID";

    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    public static ObservableList<Customers> populateCustomers() throws SQLException{
        String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division  FROM customers INNER JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPhone = rs.getString("Phone" );
                String customerPostalCode = rs.getString("Postal_Code");
                int customerDivId = rs.getInt("Division_ID");
                String customerDiv = rs.getString("Division");
                allCustomers.add(new Customers(customerID,customerName,customerAddress,customerPostalCode,customerPhone,customerDivId,customerDiv));

            }
            return allCustomers;
    }

    public static ObservableList<Customers> getAllCustomers(){
        return allCustomers;
    }


    public static int insertCustomer(String Customer_Name, String Address, String Phone, String Postal_Code, int Division_ID) throws SQLException {
        String sql = "INSERT INTO Customers (Customer_Name,Address,Phone,Postal_Code,Division_ID) VALUES(?,?,?,?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Customer_Name);
        ps.setString(2,Address);
        ps.setString(3,Phone);
        ps.setString(4,Postal_Code);
        ps.setInt(5,Division_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Insert Sucessful!");
        }else System.out.println("Insert Failed!");
        return rowsAffected;
    }

    public static int updateCustomer(String Customer_Name, String Address, String Phone, String Postal_Code, int Division_ID, int Customer_ID) throws SQLException {
        String sql = "UPDATE Customers SET Customer_Name = ?, Address = ?, Phone = ?, Postal_Code=?, Division_ID= ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Customer_Name);
        ps.setString(2,Address);
        ps.setString(3,Phone);
        ps.setString(4,Postal_Code);
        ps.setInt(5,Division_ID);
        ps.setInt(6,Customer_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Update Successful!");
        }else System.out.println("Update Failed!");
        return rowsAffected;
    }

    public static int deleteCustomer(String Customer_Name, int Customer_ID) throws SQLException {
        String sql = "DELETE FROM Customers WHERE Customer_Name = ? AND Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Customer_Name);
        ps.setInt(2,Customer_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Delete Successful!");
        }else System.out.println("Delete Failed!");
        return rowsAffected;
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM Customers";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPhone = rs.getString("Phone");
            String customerPostalCode = rs.getString("Postal_Code");
            int customerDivisionID = rs.getInt("Division_ID");
            System.out.print(customerID + " | ");
            System.out.print(customerName + " | ");
            System.out.print(customerAddress + " | ");
            System.out.print(customerPostalCode + " | ");
            System.out.print(customerDivisionID + "\n");



        }
    }


}
