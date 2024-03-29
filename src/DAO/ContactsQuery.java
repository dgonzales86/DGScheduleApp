package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ContactsQuery Class
 * Implements contacts related methods to query database for contact related functionality
 */
public class ContactsQuery {

//    TABLE_CONTACTS = "Contacts";
//    COLUMN_CONTACT_ID = "Contact_ID";
//    COLUMN_CONTACT_NAME = "Contact_Name";
//    COLUMN_CONTACT_EMAIL = "Email";

    /**
     * Observable array list to store all contacts retrieved from database
     */
    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /**
     * Queries database for contacts and populates an ObservableList with returned data.
     * @return returns allContacts ObservableList
     * @throws SQLException
     */
    public static ObservableList<Contacts> populateContacts() throws SQLException{
        if (allContacts.size() == 0){
            String sql = "SELECT * FROM Contacts";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                allContacts.add(new Contacts(contactID,contactName,contactEmail));
            }
        }
        return allContacts;
    }

    /**
     * Getter for allContacts ObservableList
     * @return returns allContacts
     */
    public static ObservableList<Contacts>getAllContacts(){
        return allContacts;
    }

    /**
     * Queries database for all appointments that match the contactID
     * @param contactID represents contactID in database
     * @return null
     * @throws SQLException via database query
     */
    public static Contacts getContact(int contactID) throws SQLException {
        String sql = "SELECT * FROM Contacts WHERE Contact_ID =?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1,contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Email");
            return  new Contacts(contactID,contactName,contactEmail);
        }
        return null;
    }

}
