
package controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Appointments;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayScheduleController implements Initializable {

    @FXML
    private Button addModAppts;

    @FXML
    private Button addModCustomer;

    @FXML
    private TableColumn<?, ?> appointmentContactCol;

    @FXML
    private TableColumn<?, ?> appointmentCustomerCol;

    @FXML
    private TableColumn<?, ?> appointmentDescriptionCol;

    @FXML
    private TableColumn<?, ?> appointmentEndCol;

    @FXML
    private TableColumn<?, ?> appointmentIDCol;

    @FXML
    private TableColumn<?, ?> appointmentLocationCol;

    @FXML
    private TableColumn<?, ?> appointmentStartCol;

    @FXML
    private TableView<?> appointmentTableView;

    @FXML
    private TableColumn<?, ?> appointmentTitleCol;

    @FXML
    private TableColumn<?, ?> appointmentTypeCol;

    @FXML
    private TableColumn<?, ?> appointmentUserCol;

    @FXML
    private Button exitApp;

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
    private RadioButton viewApptByWeek;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

