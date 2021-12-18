package utils;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    public static final String TABLE_COUNTRIES = "countries";
    public static final String COLUMN_COUNTRY_ID = "Country_ID";
    public static final String COLUMN_COUNTRY = "Country";

    public static final String TABLE_FIRST_LEVEL_DIVISIONS = "first_level_divisions";
    public static final String COLUMN_DIVISION_ID = "Division_ID";
    public static final String COLUMN_DIVISION = "Division";
    // FK COUNTRY_ID

    public static final String TABLE_CUSTOMERS = "Customers";
    public static final String COLUMN_CUSTOMER_ID = "Customer_ID";
    public static final String COLUMN_CUSTOMER_NAME = "Customer_Name";
    public static final String COLUMN_CUSTOMER_ADDRESS = "Address";
    public static final String COLUMN_CUSTOMER_POSTAL_CODE = "Postal_Code";
    public static final String COLUMN_CUSTOMER_DIVISION_ID = "Division_ID";

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "User_ID";
    public static final String COLUMN_USER_NAME = "User_Name";
    public static final String COLUMN_USER_PASSWORD ="Password";

    public static final String TABLE_CONTACTS = "Contacts";
    public static final String COLUMN_CONTACT_ID = "Contact_ID";
    public static final String COLUMN_CONTACT_NAME = "Contact_Name";
    public static final String COLUMN_CONTACT_EMAIL = "Email";

    public static final String TABLE_APPOINTMENTS = "appointments";
    public static final String COLUMN_APPOINTMENT_ID = "Appointment_ID";
    public static final String COLUMN_APPOINTMENT_TITLE = "Title";
    public static final String COLUMN_APPOINTMENT_DESC = "Description";
    public static final String COLUMN_APPOINTMENT_LOCATION = "Location";
    public static final String COLUMN_APPOINTMENT_TYPE = "Type";
    public static final String COLUMN_APPOINTMENT_START = "Start";
    public static final String COLUMN_APPOINTMENT_END = "End";

    private static Statement statement; // Statement reference

    // Create Statement Object
    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    // Return Statement Object
    public static Statement getStatement()
    {
        return statement;
    }

}
