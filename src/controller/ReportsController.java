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

    @FXML
    public void onActionNavigateBack(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displaySchedule.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }
    @FXML
    public void onActionReportThree(ActionEvent actionEvent) throws SQLException {
        userCountReportTxt.setText(String.valueOf(DAO.UserQuery.userCount()) + " available users!");
    }
    @FXML
    public void onActionReportOne(ActionEvent actionEvent) throws SQLException {
        generateAppointmentCount();
    }
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
    public void setContactCombo(){
        contactCombo.setItems(DAO.ContactsQuery.getAllContacts());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAptTypeCombo();
        setMonthCombo();
        setContactCombo();


    }

}
