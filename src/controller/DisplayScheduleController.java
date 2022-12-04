
package controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Appointments;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayScheduleController implements Initializable {

    private final ObservableList<Appointments> currentAppointments = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Appointments, ?> appointmentContactCol;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }
}
