package controller;

import DAO.CountriesQuery;
import DAO.FirstLevelDivQuery;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Countries;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static DAO.CustomersQuery.*;
import static DAO.FirstLevelDivQuery.*;


public class CustomersController implements Initializable {

    public TableView<model.Customers> customerTableView;
    public ComboBox<model.Countries> countryComboBox;
    public ComboBox<model.FirstLevelDiv> regionComboBox;
    public TextField cusIDTxtField;
    public TextField cusNameTxtField;
    public TextField cusAddressTxtField;
    public TextField cusPostalCodeTxtField;
    public TextField cusPhoneTxtField;
    public TextField cusDivIdTxtField;
    public Button addCustomerBtn;
    public Button modifyCusBtn;
    public TableColumn cusIdCol;
    public TableColumn cusNameCol;
    public TableColumn cusAddressCol;
    public TableColumn cusPostalCodeCol;
    public TableColumn cusPhoneCol;
    public TableColumn cusDivIdCol;
    public Button deleteCusBtn;
    public Button navigateBackBtn;
    public Button clearFormBtn;
    public TableColumn cusDivCol;
    public Text instructionTxt;

    Stage stage;


    /**
     * Sets selection of regions in regionComboBox based on first level divisions.
     * When a country selection is made, the regionComboBox is enabled and selections are populated
     * based on selected country.
     * @param actionEvent - countryComboBox selection
     * @throws SQLException via populateSortedFirstLevelDivs();
     */
    public void onActionSetCountry(ActionEvent actionEvent) throws SQLException {

        if (countryComboBox.getValue() != null) {
            try {
                FirstLevelDivQuery.getSortedFirstLevelDivs().removeAll(populateSortedFirstLevelDivs
                        (countryComboBox.getSelectionModel().getSelectedItem().getCountryID()));

                populateSortedFirstLevelDivs(countryComboBox.getSelectionModel().getSelectedItem().getCountryID());
                getSortedFirstLevelDivs();
                regionComboBox.setItems(FirstLevelDivQuery.getSortedFirstLevelDivs());
                regionComboBox.setDisable(false);
            } catch (NullPointerException e) {
                throw new NullPointerException("Country Combo Is Null; Please Make A Selection");
            }
        }
    }

    /**
     * Checks for null/ empty text fields, and combo boxes.
     * if all fields are completed, uses DAO.CustomersQuery.updateCustomer() method to update a customer
     * with the same customer ID. Customer ID is automatically populated when a customer is selected.
     * @param actionEvent - "Modify Customer" button click
     * @throws SQLException
     */
    public void onActionModifyCus(ActionEvent actionEvent) throws SQLException {
        if(cusIDTxtField.getText() != null && cusAddressTxtField.getText() != null && cusPostalCodeTxtField.getText() != null && cusPhoneTxtField.getText() != null && regionComboBox.getSelectionModel().getSelectedItem() != null){


            int cusID = Integer.parseInt(cusIDTxtField.getText());
            String cusName = cusNameTxtField.getText();
            String cusAddress = cusAddressTxtField.getText();
            String cusPostCode = cusPostalCodeTxtField.getText();
            String cusPhone = cusPhoneTxtField.getText();
            int cusDivId = regionComboBox.getSelectionModel().getSelectedItem().getDivisionID();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to make changes to this customer's record?", ButtonType.YES, ButtonType.NO);
            alert.setResizable(true);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                updateCustomer(cusName, cusAddress, cusPhone, cusPostCode, cusDivId, cusID);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Text fields cannot be blank!\nPlease select a record from the table.\nEnsure that all fields are complete and try again!" );
            alert.showAndWait();
        }
        refreshCustomerTable();
    }

    /**
     * When actionEvent activated(add customer button click), checks all fields for completeness, then adds the new customer by passing text field values
     * into DAO.CustomersQuery.insertCustomer() method. Customer ID is auto generated.
     * @param actionEvent - "Add Customer" button click
     * @throws SQLException via insertCustomer() method
     */
    public void onActionAddCus(ActionEvent actionEvent) throws SQLException {

        if(cusNameTxtField.getText() != null && cusAddressTxtField.getText() != null && cusPostalCodeTxtField.getText() !=  null
                && cusPhoneTxtField.getText() != null && regionComboBox.getValue() != null && countryComboBox.getValue() != null){
            String cusName = cusNameTxtField.getText();
            String cusAddress = cusAddressTxtField.getText();
            String cusPostCode = cusPostalCodeTxtField.getText();
            String cusPhone = cusPhoneTxtField.getText();
            int cusDivId = regionComboBox.getSelectionModel().getSelectedItem().getDivisionID();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Is the customer information entered correct?",ButtonType.YES,ButtonType.NO);
            alert.setResizable(true);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.YES) {
                insertCustomer(cusName,cusAddress,cusPhone,cusPostCode,cusDivId);
            }
        }else {
            System.out.println("invalid");
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Fields cannot be blank! Please complete form and try again!");
            alert1.showAndWait();

        }
        refreshCustomerTable();
    }

    /**
     * Populates text fields based on a customer selection. If fields all fields are complete, passes text field values into DAO deleteCustomer() method.
     * @param actionEvent - "Delete Customer" button click
     * @throws SQLException via deleteCustomerAppointments() method
     */
    public void onActionDeleteCus(ActionEvent actionEvent) throws SQLException {
        if(cusIdCol.cellFactoryProperty().getValue() == null || cusNameCol.cellFactoryProperty().getValue() == null || cusIDTxtField.getText() == null || cusNameTxtField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"You must first select a customer from the table to delete!");
            alert.showAndWait();
        }
        else {
            int cusID = Integer.parseInt(cusIDTxtField.getText());
            String cusName = cusNameTxtField.getText();

            Alert deleteCustomerWarning = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like delete this customer?\nAll associated appointments will be removed\nand changes cannot be undone!", ButtonType.YES, ButtonType.NO);
            deleteCustomerWarning.setResizable(true);
            Optional<ButtonType> result = deleteCustomerWarning.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                Alert deleteConfirmation = new Alert(Alert.AlertType.INFORMATION, "Customer: " + cusName + " has been removed!");
                deleteConfirmation.showAndWait();
                DAO.AppointmentsQuery.deleteCustomerAppointments(cusID);
                deleteCustomer(cusName, cusID);
            }
            refreshCustomerTable();
        }
    }

    /**
     * "Navigates back to schedule display". Loads displaySchedule.fxml, creates a new using Parent, and displays previous screen.
     * @param actionEvent - "Back" button click
     * @throws IOException loader.load() throws IOException
     */
    public void onActionNavigateBack(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displaySchedule.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * On actionEvent (clear form button), executes clearForm() method.
     * @param actionEvent - "Clear Form" button click
     */
    public void onActionClearForm(ActionEvent actionEvent) {
        clearForm();
    }

    /**
     * Clears observable list and reloads with updated information, then refreshes tableview.
     */
    public void refreshCustomerTable(){
        try {

            populateCustomers().removeAll(populateCustomers());
            FXCollections.copy(populateCustomers(),getAllCustomers());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        customerTableView.setItems(getAllCustomers());
        cusIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        cusNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        cusAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        cusPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        cusDivIdCol.setCellValueFactory(new PropertyValueFactory<>("customerDivID"));
        cusPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        cusDivCol.setCellValueFactory(new PropertyValueFactory<>("customerDiv"));


        customerTableView.refresh();
    }

    /**
     * Lambda Expression 1
     * Calls addListener on selected customer observable value and passes a lambda as an argument.
     * The lambda expression takes 3 parameters: observableValue, oldValue, and newValue: the observableValue(selection) triggers
     * event, oldValue is the old value of the selected item, and newValue is the new value. If values are not null, text fields
     * are set to the newValue.
     * This lambda expression improved this code by eliminating the need to create a separate listener class allowed implementation
     * of code directly within the CustomersController class rather than in the newly created listener class.
     */
    public void getCustomerSelection(){

        customerTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {

                Countries country;

                cusIDTxtField.setText(String.valueOf(newValue.getCustomerID()));
                cusNameTxtField.setText(newValue.getCustomerName());
                cusAddressTxtField.setText(newValue.getCustomerAddress());
                cusPostalCodeTxtField.setText(newValue.getCustomerPostalCode());
                cusPhoneTxtField.setText(newValue.getCustomerPhone());
                cusDivIdTxtField.setText(String.valueOf(newValue.getCustomerDivID()));

                try {
                    country = CountriesQuery.getCountryByDiv(newValue.getCustomerDivID());
                    regionComboBox.setItems(FirstLevelDivQuery.populateSortedFirstLevelDivs(country.getCountryID()));
                    countryComboBox.setValue(country);
                    regionComboBox.setValue(FirstLevelDivQuery.getDivision(newValue.getCustomerDivID()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     *
     * @param actionEvent regionComboBox. Currently, has no action. Possible use in future builds.
     */
    public void onActionSetRegion(ActionEvent actionEvent) {
    }
    /**
     * Queries database using populateCountries() method and populates countryComboBox with returned values.
     * @throws SQLException via populateCountries() method
     */
    public void setCountryCombo() throws SQLException {
        //populate country combo box with query select* from countries.
        CountriesQuery.populateCountries();
        countryComboBox.setItems(CountriesQuery.getAllCountries());
    }

    /**
     * Sets all text fields to null, and clears all combo box selections. Essentially clearing the form.
     */
    public void clearForm(){
        cusIDTxtField.setText(null);
        cusNameTxtField.setText(null);
        cusAddressTxtField.setText(null);
        cusPostalCodeTxtField.setText(null);
        cusPhoneTxtField.setText(null);
        cusDivIdTxtField.setText(null);
        countryComboBox.getSelectionModel().clearSelection();
        regionComboBox.getSelectionModel().clearSelection();
        regionComboBox.setValue(null);
        regionComboBox.setDisable(true);

        refreshCustomerTable();
    }

    /**
     * Displays use instructions when a user hovers over "Clear Form" button.
     * @param mouseEvent - "Clear Form" button hover
     */
    public void onMouseEnterClearForm(MouseEvent mouseEvent) {
        instructionTxt.setText("Clear form to enter new customer information.");
    }

    /**
     * Displays use instructions when a user hovers over "Add Customer" button
     * @param mouseEvent - "Add Customer" button hover
     */
    public void onMouseEnterAddCus(MouseEvent mouseEvent) {
        instructionTxt.setText("To add a new customer, complete all fields and click 'Add Customer'.");
    }

    /**
     * Displays use instructions when a user hovers over "Modify Customer" button
     * @param mouseEvent - "Modify Customer" button hover
     */
    public void onMouseEnterModCus(MouseEvent mouseEvent) {
        instructionTxt.setText("Select a record form the table to modify. Once changes made, click 'Modify Customer'.");
    }

    /**
     * Displays use instructions when a user hovers over "Delete Customer" button
     * @param mouseEvent - "Delete Customer" button hover
     */
    public void onMouseEnterDeleteCus(MouseEvent mouseEvent) {
        instructionTxt.setText("Select a record to delete and click 'Delete Customer'.");
    }

    /**
     * Displays use instructions when a user hovers over "Back" button
     * @param mouseEvent - "Back" button hover
     */
    public void onMouseEnterBack(MouseEvent mouseEvent) {
        instructionTxt.setText("Navigates back to appointments");
    }

    /**
     * Disables customer id, and cusDivId text fields. Then, populates customer table, executes getCustomerSelection()
     * method, populates combo boxes, and clears all modifiable fields.
     * @param url - customers.fxml
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cusIDTxtField.setDisable(true);
        cusDivIdTxtField.setDisable(true);
        refreshCustomerTable();
        getCustomerSelection();
        try {
            setCountryCombo();
            countryComboBox.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clearForm();
    }
}