package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsQuery {

//    TABLE_CONTACTS = "Contacts";
//    COLUMN_CONTACT_ID = "Contact_ID";
//    COLUMN_CONTACT_NAME = "Contact_Name";
//    COLUMN_CONTACT_EMAIL = "Email";


    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
    private static ObservableList<Contacts> sortedContacts = FXCollections.observableArrayList();


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
    public static ObservableList<Contacts>getAllContacts(){
        return allContacts;
    }

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

    public static ObservableList<Contacts> getAllSortedContacts(){
        return sortedContacts;
    }

}
