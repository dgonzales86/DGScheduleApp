package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentsQuery {

//    TABLE_APPOINTMENTS = "appointments";
//    COLUMN_APPOINTMENT_ID = "Appointment_ID";
//    COLUMN_APPOINTMENT_TITLE = "Title";
//    COLUMN_APPOINTMENT_DESC = "Description";
//    COLUMN_APPOINTMENT_LOCATION = "Location";
//    COLUMN_APPOINTMENT_TYPE = "Type";
//    COLUMN_APPOINTMENT_START = "Start";
//    COLUMN_APPOINTMENT_END = "End";



    public static int insertAppointment(String Title, String Description, String Location, String Type, java.sql.Timestamp Start, java.sql.Timestamp End) throws SQLException {
        String sql = "INSERT INTO appointments () VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1,Title);
        ps.setString(2,Description);
        ps.setString(3,Location);
        ps.setString(4,Type);
        ps.setTimestamp(5,Start);
        ps.setTimestamp(6,End);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Insert Sucessful!");
        }else System.out.println("Insert Failed!");
        return rowsAffected;

    }
    public static int updateAppointment(){

        return 1;

    }
    public static int deleteAppointment(){

        return 1;

    }




}
