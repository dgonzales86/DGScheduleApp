package controller;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static DAO.CustomersQuery.*;

public class CustomersController implements Initializable {
    public TableView<Customers> customerTableView;
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
        int cusDivId = Integer.parseInt(cusDivIdTxtField.getText());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshCustomerTable();



        customerTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                cusIDTxtField.setText(String.valueOf(newValue.getCustomerID()));
                cusNameTxtField.setText(newValue.getCustomerName());
                cusAddressTxtField.setText(newValue.getCustomerAddress());
                cusPostalCodeTxtField.setText(newValue.getCustomerPostalCode());
                cusPhoneTxtField.setText(newValue.getCustomerPhone());
                cusDivIdTxtField.setText(String.valueOf(newValue.getCustomerDivID()));

            }
        });


    }


}