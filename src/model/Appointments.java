package model;

import java.time.LocalDateTime;

/**
 *  Appointments class
 *  provides shell to create and retrieve Appointments objects
 */
public class Appointments{
    /**
     * appointment id of Appointments Objects
     */
    private int appointmentID;
    /**
     * appointment title of Appointments Objects
     */
    private String appointmentTitle;
    /**
     * appointment description of Appointments Objects
     */
    private String appointmentDesc;
    /**
     * appointment location of Appointments Objects
     */
    private String appointmentLocation;
    /**
     * appointment type of Appointments Objects
     */
    private String appointmentType;
    /**
     * appointment start time of Appointments Objects
     */
    private LocalDateTime start;
    /**
     * appointment end time of Appointments Objects
     */
    private LocalDateTime end;
    /**
     * appointment customer id of Appointments Objects
     */
    private int customerID;
    /**
     * appointment user id of Appointments Objects
     */
    private int userID;
    /**
     * appointment contact id of Appointments Objects
     */
    private int contactID;

    /**
     *  Appointments constructor:
     * @param appointmentID - adds appointment id for a new Appointment object
     * @param appointmentTitle - adds the title for a new Appointment object
     * @param appointmentDesc - adds a description for a new Appointment object
     * @param appointmentLocation - adds a location for a new Appointment object
     * @param appointmentType - adds a type for a new Appointment object
     * @param start - adds start time for a new Appointment object
     * @param end - adds end time for a new Appointment object
     * @param customerID - adds customer id for a new Appointment object
     * @param userID - adds user id for a new Appointment object
     * @param contactID - adds contact id for a new Appointment object
     */
    public Appointments(int appointmentID, String appointmentTitle, String appointmentDesc, String appointmentLocation, String appointmentType,
                        LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDesc = appointmentDesc;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * Getter for Appointments appointmentID
     * @return - returns int appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Setter for Appointments appointmentID
     * @param appointmentID - sets this.appointmentID to appointmentID.
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Getter for Appointments appointmentTitle
     * @return - returns String appointmentTitle
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * Setter for Appointments appointmentTitle
     * @param appointmentTitle - sets this.appointmentTitle to appointmentTitle
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * Getter for Appointments appointDesc
     * @return - returns String appointmentDesc
     */
    public String getAppointmentDesc() {
        return appointmentDesc;
    }

    /**
     * Setter for Appointments appointmentDesc
     * @param appointmentDesc - sets this.appointmentDesc to appointmentDesc
     */
    public void setAppointmentDesc(String appointmentDesc) {
        this.appointmentDesc = appointmentDesc;
    }

    /**
     * Getter for Appointments appointmentLocation
     * @return - returns String appointmentLocation
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     * Setter for Appointments appointmentLocation
     * @param appointmentLocation - sets this.appointmentLocation to appointmentLocation
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * Getter for Appointments appointmentType
     * @return - returns String appointmentType
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Setter for Appointments appointmentType
     * @param appointmentType - sets this.appointmentType to appointmentType
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Getter for Appointments start
     * @return - returns LocalDateTime start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter for Appointments start
     * @param start - sets this.start to start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter for Appointments end
     * @return - returns LocalDateTime end.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter for Appointments end
     * @param end - sets LocalDateTime this.end to end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Getter for Appointments customerID
     * @return - returns int customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Setter for Appointments customerID
     * @param customerID - sets this.customerID to customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Getter for Appointments userID
     * @return - returns int userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for Appointments userID
     * @param userID - sets this.userId to userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for Appointments contactID
     * @return - returns int contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Setter for Appointments contactID
     * @param contactID - sets this.contactID to contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
