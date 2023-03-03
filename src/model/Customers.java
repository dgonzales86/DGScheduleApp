package model;

/**
 * Customers Class
 * shell used to create and retrieve Customers objects
 */
public class Customers {
    /**
     * customer id of Customers Objects
     */
    private int customerID;
    /**
     * customer name of Customers Objects
     */
    private String customerName;
    /**
     * customer address of Customers Objects
     */
    private String customerAddress;
    /**
     * customer postal code of Customers Objects
     */
    private String customerPostalCode;
    /**
     * customer phone number of Customers Objects
     */
    private String customerPhone;
    /**
     * customer division id of Customers Objects
     */
    private int customerDivID;
    /**
     * customer division of Customers Objects
     */
    private String customerDiv;

    /**
     * Customers constructor for a new Customers object
     * @param customerID - sets customer id for a new Customers object
     * @param customerName - sets customer name for a new Customers object
     * @param customerAddress - sets customer address for a new Customers object
     * @param customerPostalCode - sets customer postal code for a new Customers object
     * @param customerPhone - sets customer phone number for a new Customers object
     * @param customerDivID - sets customer division id for a new Customers object
     * @param customerDiv - sets customer division for a new Customers object
     */
    public Customers(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivID, String customerDiv) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivID = customerDivID;
        this.customerDiv = customerDiv;
    }

    /**
     * Overrides default toString() method
     * @return - returns String value of customerName
     */
    @Override
    public String toString() {
        return (customerName);
    }

    /**
     * Getter for Customers customerID
     * @return - returns int customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Setter for Customers customerID
     * @param customerID - sets this.customerID to customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Getter for Customers customerName
     * @return - returns String customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter for Customers customerName
     * @param customerName - sets this.customerName to customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter for Customers customerAddress
     * @return - returns String customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *  Setter for Customers customerAddress
     * @param customerAddress - sets this.customerAddress to customerAddress
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Getter for Customers customerPostalCode
     * @return - returns String customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * Setter for Customers customerPostalCode
     * @param customerPostalCode - sets this.customerPostalCode to customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Getter for Customers customerPhone
     * @return - returns String customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Setter for Customers customerPhone
     * @param customerPhone - sets this.customerPhone to customerPhone
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Getter for Customers customerDivID
     * @return - returns int customerDivID
     */
    public int getCustomerDivID() {
        return customerDivID;
    }

    /**
     * Setter for Customers customerDivID
     * @param customerDivID - sets this.CustomerDivID to customerDivID
     */
    public void setCustomerDivID(int customerDivID) {
        this.customerDivID = customerDivID;
    }

    /**
     * Getter for Customers customerDiv
     * @return - returns String customerDiv
     */
    public String getCustomerDiv() {
        return customerDiv;
    }

    /**
     * Setter for Customers customerDiv
     * @param customerDiv - sets this.customerDiv to customerDiv
     */
    public void setCustomerDiv(String customerDiv) {
        this.customerDiv = customerDiv;
    }
}
