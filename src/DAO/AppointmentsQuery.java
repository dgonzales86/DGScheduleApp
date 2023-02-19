package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;



public class AppointmentsQuery {

//    TABLE_APPOINTMENTS = "appointments";
//    COLUMN_APPOINTMENT_ID = "Appointment_ID";
//    COLUMN_APPOINTMENT_TITLE = "Title";
//    COLUMN_APPOINTMENT_DESC = "Description";
//    COLUMN_APPOINTMENT_LOCATION = "Location";
//    COLUMN_APPOINTMENT_TYPE = "Type";
//    COLUMN_APPOINTMENT_START = "Start";
//    COLUMN_APPOINTMENT_END = "End";
//    COLUMN_CUSTOMER_ID = "Customer_ID"
//    COLUMN_USER_ID = "User_ID";
//    CONTACT_ID = "Contact_ID";


    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

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

    public static ObservableList<Appointments> getAllAppointments(){
        return allAppointments;
    }

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


    public static void select() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
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
            System.out.print(appointmentId + " | ");
            System.out.print(appointmentTitle + " | ");
            System.out.print(appointmentDesc + " | ");
            System.out.print(appointmentLocation + " | ");
            System.out.print(appointmentType + " | ");
            System.out.print(appointmentStart + " | ");
            System.out.print(appointmentEnd + " | ");
            System.out.print(customerID + " | ");
            System.out.print(userID + " | ");
            System.out.print(contactID + "\n");

        }
    }

}
