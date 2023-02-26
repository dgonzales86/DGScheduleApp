package model;

/**
 * Contacts Class
 */
public class Contacts {
    private int contactID;
    private String contactName;
    private String contactEmail;

    /**
     * Constructor for new Contacts
     * @param contactID - adds contact id for a new contact
     * @param contactName - adds contact name for a new Contact object
     * @param contactEmail - adds contactEmail for a new Contact object
     */
    public Contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * Overrides default toString() method
     * @return - returns the String value of contactName
     */
    @Override
    public String toString() {
        return (contactName);
    }

    /**
     *  Getter for Contacts contactID
     * @return - returns int contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Setter for Contacts contactID
     * @param contactID - sets this.contactID to contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Getter for Contacts contactName
     * @return - returns String contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter for Contacts contactName
     * @param contactName - sets this.contactName to contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Getter for Contacts contactEmail;
     * @return - returns String contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Setter for Contacts contactEmail
     * @param contactEmail - sets this.contactEmail to contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
