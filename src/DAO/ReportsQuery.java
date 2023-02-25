package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportsQuery {
    public static ObservableList<model.Appointments> contactAppointments = FXCollections.observableArrayList();

    public static void querySchedule(String contName) throws SQLException {

        String sql = "select contacts.Contact_Name, contacts.Email, appointments.Contact_ID, appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description," +
                " appointments.Start, appointments.End, appointments.Customer_ID, appointments.Location, appointments.User_ID FROM contacts INNER JOIN appointments ON" +
                " appointments.Contact_ID = contacts.Contact_ID WHERE contacts.Contact_Name = ? ORDER BY appointments.Start";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, contName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){

            int contID = rs.getInt("Contact_ID");
            int aptID = rs.getInt("Appointment_ID");
            String aptTitle = rs.getString("Title");
            String aptType = rs.getString("Type");
            String aptDesc = rs.getString("Description");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int cusID = rs.getInt("Customer_ID");
            String aptLoc = rs.getString("Location");
            int aptUserID = rs.getInt("User_ID");
            contactAppointments.add(new Appointments(aptID,aptTitle,aptDesc,aptLoc, aptType,start,end,cusID,aptUserID, contID));

        }
    }
    public static ObservableList<Appointments> getContactAppointments() {
        return contactAppointments;
    }

}
