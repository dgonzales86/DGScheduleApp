package model;

public class Customers {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int customerDivID;
    private String customerDiv;

    public Customers(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivID, String customerDiv) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivID = customerDivID;
        this.customerDiv = customerDiv;
    }
    @Override
    public String toString() {
        return (customerName);
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getCustomerDivID() {
        return customerDivID;
    }

    public void setCustomerDivID(int customerDivID) {
        this.customerDivID = customerDivID;
    }

    public String getCustomerDiv() {
        return customerDiv;
    }

    public void setCustomerDiv(String customerDiv) {
        this.customerDiv = customerDiv;
    }
}
