package controller;

import DAO.AppointmentsQuery;
import DAO.ReportsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static DAO.ReportsQuery.contactAppointments;
import static DAO.ReportsQuery.getContactAppointments;

/**
 * ReportsController Class
 * Implements methods to generate required reports
 */
public class ReportsController implements Initializable {
    Stage stage;

    @FXML
    public TableColumn aptcusIdColumn;
    @FXML
    public TableColumn aptEndColumn;
    @FXML
    public TableColumn aptStartColumn;
    @FXML
    public TableColumn aptDescColumn;
    @FXML
    public TableColumn aptTypeColumn;
    @FXML
    public TableColumn aptTitleColumn;
    @FXML
    public TableColumn aptIdColumn;
    @FXML
    public ComboBox contactCombo;
    @FXML
    public Text userCountReportTxt;
    @FXML
    public Button reportsThreeBtn;
    @FXML
    public Text totalCustomerAptsTxt;
    @FXML
    public ComboBox aptMonthCombo;
    @FXML
    public ComboBox aptTypeCombo;

    @FXML
    public TableView reportsTableview;
    @FXML
    public Button navigateBackBtn;

    @FXML
    public Button reportOneBtn;

    /**
     * Navigates back to main screen/ displaySchedule.fxml
     * @param actionEvent "Back" button click
     * @throws IOException via loader.load()
     */
    @FXML
    public void onActionNavigateBack(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displaySchedule.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Generates report 3 requirement, which counts all users in the database
     * @param actionEvent - "Generate Report" button click
     * @throws SQLException via userCount() db query
     */
    @FXML
    public void onActionReportThree(ActionEvent actionEvent) throws SQLException {
        userCountReportTxt.setText(String.valueOf(DAO.UserQuery.userCount()) + " available users!");
    }

    /**
     * Calls generateAppointmentCount() method, which generates report one requirement of counting customer appointments
     * for a given type and a given month.
     * @param actionEvent "Generate Report" button click
     * @throws SQLException
     */
    @FXML
    public void onActionReportOne(ActionEvent actionEvent) throws SQLException {
        generateAppointmentCount();
    }

    /**
     * Queries database for a count of customer appointments by type and month
     * @throws SQLException via countAppointments() db query
     */
    public void generateAppointmentCount() throws SQLException {
        if (aptMonthCombo.getSelectionModel().getSelectedItem() != null && aptTypeCombo.getSelectionModel().getSelectedItem() != null) {
            String monthName = aptMonthCombo.getSelectionModel().getSelectedItem().toString();
            String aptType = aptTypeCombo.getSelectionModel().getSelectedItem().toString();
            totalCustomerAptsTxt.setText(String.valueOf(DAO.CustomersQuery.countAppointments(aptType, monthName)) + " appointments for selected month!");
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Select both appointment type and month and try again!");
            alert.showAndWait();
        }
    }

    /**
     * Displays a schedule for the selected contact.
     * @param actionEvent "contactCombo" selection
     * @throws SQLException via getContactAppointments() method's DB query
     */
    @FXML
    public void onActionViewSchedule(ActionEvent actionEvent) throws SQLException {
        DAO.ReportsQuery.getContactAppointments().clear();
        String contact = contactCombo.getSelectionModel().getSelectedItem().toString();
        System.out.println(contact);
        DAO.ReportsQuery.querySchedule(contact);
        DAO.ReportsQuery.getContactAppointments();
        reportsTableview.setItems(getContactAppointments());

        aptIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        aptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        aptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        aptDescColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDesc"));
        aptStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        aptEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        aptcusIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

    }

    /**
     * Adds available appointment types to a list and populates aptTypeCombo combo box.
     */
    public void setAptTypeCombo(){
        ObservableList<String> appointmentsList = FXCollections.observableArrayList();
        String aptString;
        for (Appointments appointments: DAO.AppointmentsQuery.getAllAppointments()) {

            aptString = String.valueOf(appointments.getAppointmentType());
            if (!appointmentsList.contains(aptString)){
                appointmentsList.add(String.valueOf(appointments.getAppointmentType()));
            }
        }
        aptTypeCombo.setItems(appointmentsList);

    }

    /**
     * Adds month selections to an ObservableList of strings. Uses the list to populate month combo box
     */
    public void setMonthCombo(){

        ObservableList<String> aptMonthList = FXCollections.observableArrayList();
        aptMonthList.add("January");
        aptMonthList.add("February");
        aptMonthList.add("March");
        aptMonthList.add("April");
        aptMonthList.add("May");
        aptMonthList.add("June");
        aptMonthList.add("July");
        aptMonthList.add("July");
        aptMonthList.add("August");
        aptMonthList.add("September");
        aptMonthList.add("October");
        aptMonthList.add("November");
        aptMonthList.add("December");
        aptMonthCombo.setItems(aptMonthList);
    }

    /**
     * populates contactCombo combo box with allContacts ObservableList
     */
    public void setContactCombo(){
        contactCombo.setItems(DAO.ContactsQuery.getAllContacts());
    }

    /**
     * Sets all combo boxes
     * @param url - "reports.fxml"
     * @param resourceBundle for ResourceBundle utilization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAptTypeCombo();
        setMonthCombo();
        setContactCombo();
    }

}
