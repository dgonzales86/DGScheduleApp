
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Appointments;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static DAO.AppointmentsQuery.addAppointments;
import static DAO.AppointmentsQuery.getAllAppointments;


public class DisplayScheduleController implements Initializable {


    @FXML
    private Button addModAppts;

    @FXML
    private Button addModCustomer;

    @FXML
    private TableColumn<Appointments, Integer> appointmentContactCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentCustomerCol;

    @FXML
    private TableColumn<Appointments, String> appointmentDescriptionCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appointmentEndCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDCol;

    @FXML
    private TableColumn<Appointments, String> appointmentLocationCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appointmentStartCol;

    @FXML
    private TableView<Appointments> appointmentTableView;

    @FXML
    private TableColumn<Appointments, String> appointmentTitleCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTypeCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentUserCol;

    @FXML
    private Button logOut;

    @FXML
    private Text reportOneTxt;

    @FXML
    private Text reportThreeTxt;

    @FXML
    private Text reportTwoTxt;

    @FXML
    private DatePicker selectDatePicker;

    @FXML
    private Text selectScheduleDateText;

    @FXML
    private RadioButton viewAllAppts;

    @FXML
    private RadioButton viewApptByMonth;

    @FXML
    private RadioButton viewApptsByWeek;

    @FXML
    void onActionAddModAppts(ActionEvent event) {

    }

    @FXML
    void onActionAddModCustomer(ActionEvent event) {

    }

    @FXML
    void onActionLogOut(ActionEvent event) {

    }

    @FXML
    void onActionViewAllAppts(ActionEvent event) {

    }

    @FXML
    void onActionViewApptsByMonth(ActionEvent event) {

    }

    @FXML
    void onActionViewApptsByWeek(ActionEvent event) {

    }



    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            addAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        appointmentTableView.setItems(getAllAppointments());
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDesc"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentUserCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));

    }
}

