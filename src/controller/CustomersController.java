package controller;

import DAO.CountriesQuery;
import DAO.CustomersQuery;
import DAO.FirstLevelDivQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.FirstLevelDiv;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static DAO.CustomersQuery.*;
import static DAO.FirstLevelDivQuery.getAllFirstLevelDivs;
import static DAO.FirstLevelDivQuery.populateFirstLevelDivs;


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

    Stage stage;
    




    public void onActionModifyCus(ActionEvent actionEvent) throws SQLException {



        int cusID = Integer.parseInt(cusIDTxtField.getText());
        String cusName = cusNameTxtField.getText();
        String cusAddress = cusAddressTxtField.getText();
        String cusPostCode = cusPostalCodeTxtField.getText();
        String cusPhone = cusPhoneTxtField.getText();
        int cusDivId = Integer.parseInt(cusDivIdTxtField.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you would like to make changes to this customer's record?",ButtonType.YES,ButtonType.NO);
        alert.setResizable(true);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES) {
            updateCustomer(cusName,cusAddress,cusPhone,cusPostCode,cusDivId,cusID);
        }
        refreshCustomerTable();


    }
    public void onActionAddCus(ActionEvent actionEvent) throws SQLException {



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
        refreshCustomerTable();

    }
    public void onActionDeleteCus(ActionEvent actionEvent) throws SQLException {

        int cusID = Integer.parseInt(cusIDTxtField.getText());
        String cusName = cusNameTxtField.getText();

        Alert deleteCustomerWarning = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you would like delete this customer?\nAll associated appointments will be removed\nand changes cannot be undone!",ButtonType.YES,ButtonType.NO);
        deleteCustomerWarning.setResizable(true);
        Optional<ButtonType> result = deleteCustomerWarning.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES) {
           Alert deleteConfirmation = new Alert(Alert.AlertType.INFORMATION,"Customer: " + cusName + " has been removed!");
           deleteConfirmation.showAndWait();
           DAO.AppointmentsQuery.deleteCustomerAppointments(cusID);
           deleteCustomer(cusName, cusID);
        }
        refreshCustomerTable();

    }
    public void onActionNavigateBack(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displaySchedule.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

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
        customerTableView.refresh();
    }
    public void populateCusTable(){

        customerTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                for(int i= 0; i< regionComboBox.getItems().size(); i++){
                    model.FirstLevelDiv firstLevelDiv = regionComboBox.getItems().get(i);
                    if(firstLevelDiv.getDivisionID() == newValue.getCustomerDivID()){
                        regionComboBox.setValue(firstLevelDiv);
                    }

                }

                cusIDTxtField.setText(String.valueOf(newValue.getCustomerID()));
                cusNameTxtField.setText(newValue.getCustomerName());
                cusAddressTxtField.setText(newValue.getCustomerAddress());
                cusPostalCodeTxtField.setText(newValue.getCustomerPostalCode());
                cusPhoneTxtField.setText(newValue.getCustomerPhone());
                cusDivIdTxtField.setText(String.valueOf(newValue.getCustomerDivID()));



            }
        });
    }
    public void setRegionComboBox() throws SQLException {
        FirstLevelDivQuery.populateFirstLevelDivs();
        regionComboBox.setItems(FirstLevelDivQuery.getAllFirstLevelDivs());
    }
    public void setCountryCombo() throws SQLException {
        //populate country combo box with query select* from countries.
       CountriesQuery.populateCountries();
       countryComboBox.setItems(CountriesQuery.getAllCountries());
      //populateFirstLevelDivs(countryComboBox.getSelectionModel().getSelectedItem().getDivisionID());

       //populate division combo box with observable list from line 158

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cusIDTxtField.setDisable(true);
        cusDivIdTxtField.setDisable(true);
        refreshCustomerTable();
        populateCusTable();
        try {
            setCountryCombo();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            setRegionComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}