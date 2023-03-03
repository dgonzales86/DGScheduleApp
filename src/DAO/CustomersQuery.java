package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CustomersQuery Class
 * Implements methods that query the database for customers related operations.
 */
public class CustomersQuery {
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /**
     * Queries database for customers and first level divisions.
     * the returned customers are added to allCustomers ObservableList as a Customer object
     * @return allCustomers
     * @throws SQLException via DB query
     */
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

    /**
     * Getter for allCustomers ObservableList
     * @return allCustomers
     */
    public static ObservableList<Customers> getAllCustomers(){
        return allCustomers;
    }

    /**
     * INSERT customer into database by passing variables as a PreparedStatement into query
     * @param Customer_Name represents customer name column in database
     * @param Address represents address column in database
     * @param Phone represents phone column in database
     * @param Postal_Code represents postal code column in database
     * @param Division_ID represents division id column in database
     * @return rowsAffected
     * @throws SQLException
     */
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

    /**
     * Database Query that returns a count of appointments that match a type and month
     * @param aptType represents appointment type in database
     * @param monthName represents month of appointment in database
     * @return count
     * @throws SQLException via database query SELECT statement
     */
    public static int countAppointments(String aptType, String monthName) throws SQLException {
        String sql = "SELECT count(*) FROM  appointments WHERE Type=? AND monthname(Start) =? ";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,aptType);
        ps.setString(2,monthName);
        int count = 0;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            count= rs.getInt(1);
        }
        return count;

    }

    /**
     * Selects customers where Customer_ID column matches passed variable customerID
     * @param customerID represents customer id column in database
     * @return new customer or null
     * @throws SQLException
     */

    public static Customers getCustomer(int customerID) throws SQLException {

        String sql = "SELECT * FROM customers WHERE Customer_ID =?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1,customerID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int cusID = rs.getInt("Customer_ID");
            String cusName = rs.getString("Customer_Name");
            String cusAddress = rs.getString("Address");
            String cusPostalCode = rs.getString("Postal_Code");
            String cusPhone = rs.getString("Phone");
            int cusDivId = rs.getInt("Division_ID");
            return new Customers(cusID,cusName,cusAddress,cusPostalCode,cusPhone,cusDivId,null);
        }
        return null;

    }

    /**
     * Performs UPDATE query on a customer record where customer id matches passed Customer_ID variable
     * @param Customer_Name represents customer name in database to be updated
     * @param Address represents customer address in database to be updated
     * @param Phone represents customer phone number in database to be updated
     * @param Postal_Code represents customer postal code in database to be updated
     * @param Division_ID represents customer division id in database to be updated
     * @param Customer_ID represents customer id in database to be updated
     * @return returns rows affected
     * @throws SQLException via database SELECT Query
     */
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

    /**
     * Deletes a customer from the database where customer name and customer id match passed variables
     * Customer_Name and Customer_ID
     * @param Customer_Name represents customer name in database to be deleted
     * @param Customer_ID represents customer id in database to be deleted
     * @return rowsAffected
     * @throws SQLException via DELETE Database Query
     */
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

}
