package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * AppointmentsQuery Class
 * Implements methods to query database
 */
public class AppointmentsQuery {

    /**
     * Observable array list to store all appointments retrieved from database
     */
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /**
     * Queries database for appointments
     * Adds all appointments to an ObservableList
     * @return allAppointments
     * @throws SQLException
     */
    public static ObservableList<Appointments> populateAppointments() throws SQLException {

        String sql = "SELECT * FROM appointments";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDesc = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            allAppointments.add(new Appointments(appointmentId,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,
                    appointmentEnd,customerID,userID,contactID));


        }
        return allAppointments;
    }

    /**
     * Getter for allAppointments ObservableList
     * @return allAppointments
     */
    public static ObservableList<Appointments> getAllAppointments(){
        return allAppointments;
    }

    /**
     * Inserts new appointments into database using INSERT statement
     * @param Title String variable represents title column in appointments table
     * @param Description String variable represents description column in appointments table
     * @param Location String variable represents description column in appointments table
     * @param Type String variable represents type column in appointments table
     * @param Start LocalDateTime variable representing start in appointments table
     * @param End LocalDateTime variable representing end in appointments table
     * @param Customer_ID int variable representing customer id in appointments table
     * @param User_ID int variable representing user id column in appointments table
     * @param Contact_ID int varibale representing contact id column in appointments table
     * @return rowsAffected
     * @throws SQLException via INSERT db query
     */
    public static int insertAppointment(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID,
                                        int User_ID, int Contact_ID) throws SQLException {
        String sql = "INSERT INTO appointments (Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Title);
        ps.setString(2,Description);
        ps.setString(3,Location);
        ps.setString(4,Type);
        ps.setTimestamp(5, Timestamp.valueOf(Start));
        ps.setTimestamp(6, Timestamp.valueOf(End));
        ps.setInt(7,Customer_ID);
        ps.setInt(8,User_ID);
        ps.setInt(9,Contact_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Insert Successful!");
        }else System.out.println("Insert Failed!");
        return rowsAffected;

    }

    /**
     * Queries DB for an appointment match and updates the following columns where the appointment ID matches
     * @param Title String variable represents title of appointment being updated
     * @param Description String variable represents description of appointment being updated
     * @param Location String variable representing location of appointment being updated
     * @param Type String variable representing type of appointment being updated
     * @param Start LocalDateTime variable representing start time of appointment being updated
     * @param End LocalDateTime variable representing end time of appointment being updated
     * @param Customer_ID int variable represents customer id of appointment being updated
     * @param User_ID int variable represents user id of appointment being updated
     * @param Contact_ID int variable represents contact id of appointment being updated
     * @param Apt_ID int variable represents appointment id of appointment being updated
     * @return returns integer of rows affected.
     * @throws SQLException via db query
     */

    public static int updateAppointment(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID, int User_ID, int Contact_ID, int Apt_ID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location=?, Type= ?, Start =?, End =?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Title);
        ps.setString(2,Description);
        ps.setString(3,Location);
        ps.setString(4,Type);
        ps.setTimestamp(5, Timestamp.valueOf(Start));
        ps.setTimestamp(6, Timestamp.valueOf(End));
        ps.setInt(7, Customer_ID);
        ps.setInt(8,User_ID);
        ps.setInt(9,Contact_ID);
        ps.setInt(10, Apt_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Update Successful!");
        }else System.out.println("Update Failed!");
        return rowsAffected;
    }

    /**
     * Deletes appointment where Title and Appointment match the passed variables
     * @param Title represents title of appointment being deleted
     * @param Appointment_ID represents appointment id of appointment being deleted
     * @return int rows affected
     * @throws SQLException via db query
     */
    public static int deleteAppointment(String Title, int Appointment_ID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Title = ? AND Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Title);
        ps.setInt(2,Appointment_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Delete Successful!");
        }else System.out.println("Delete Failed!");
        return rowsAffected;
    }

    /**
     * Deletes all appointments for an existing customer
     * Used to first delete appointments before removing a customer from the database
     * @param Customer_ID represents customer id of appointments to be deleted
     * @return integer of number of rows affected by query
     * @throws SQLException via db query
     */
    public static int deleteCustomerAppointments(int Customer_ID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, Customer_ID);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Delete Successful!");
        }else System.out.println("Delete Failed!");
        return rowsAffected;
    }

}
