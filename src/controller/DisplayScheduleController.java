
package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import static DAO.AppointmentsQuery.populateAppointments;
import static DAO.AppointmentsQuery.getAllAppointments;




public class DisplayScheduleController implements Initializable {

    public TextField aptIdTxtField;
    public Button submitChangeBtn;
    public TextField aptContactTxtField;
    public TextField aptCustomerTxtField;
    public TextField aptUserTxtField;
    Stage stage;
    private LocalTime startTime;
    private LocalTime endTime;

    @FXML
    public TextField aptTitleTxtField;
    @FXML
    public TextField aptDescTxtField;
    @FXML
    public TextField aptLocationTxtField;
    @FXML
    public TextField aptTypeTxtField;
    @FXML
    public DatePicker startDatePicker;
    @FXML
    public Button reportsBtn;
    @FXML
    public ComboBox startTimeCombo;
    @FXML
    public ComboBox endTimeCombo;
    @FXML
    public RadioButton apptByMonthRadioBtn;
    @FXML
    public RadioButton apptsByWeekRadioBtn;
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
    private TableView<Appointments> appointmentTableView;
    @FXML
    private TableColumn<?, ?> appointmentTitleCol;
    @FXML
    private TableColumn<?, ?> appointmentTypeCol;
    @FXML
    private TableColumn<?, ?> appointmentUserCol;
    @FXML
    private Button customersBtn;
    @FXML
    private Button exitBtn;

    @FXML
    void onActionCustomers(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customers.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionExit(ActionEvent event) {


        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you would like\nto exit the applicaiton?\nUnsaved changes will not be stored!", ButtonType.YES, ButtonType.NO);
        alert.setResizable(true);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            System.exit(0);
        }


    }

    @FXML
    void onActionViewApptsByMonth(ActionEvent event) {

    }

    @FXML
    void onActionViewApptsByWeek(ActionEvent event) {

    }

    public void onActionViewReports(ActionEvent actionEvent) {
    }

    public void onActionSubmitChanges(ActionEvent actionEvent) {
    }

    public void popStartTimeCombo() {

        startTime = LocalTime.of(8, 0);
        endTime = LocalTime.of(22, 0);
        while (startTime.isBefore(endTime)) {
            startTimeCombo.getItems().add(startTime);
            startTime = startTime.plusMinutes(15);
        }
        startTimeCombo.getSelectionModel().select(LocalTime.of(8, 0));
    }

    public void popEndTimeCombo() {

        startTime = LocalTime.of(8, 15);
        endTime = LocalTime.of(22, 0);
        while (startTime.isBefore(endTime.plusSeconds(1))) {
            endTimeCombo.getItems().add(startTime);
            startTime = startTime.plusMinutes(15);
        }
        endTimeCombo.getSelectionModel().select(LocalTime.of(8, 15));
    }

    public void refreshAppointmentsTable() {
        try {
            populateAppointments().removeAll(populateAppointments());
            FXCollections.copy(populateAppointments(), getAllAppointments());
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

    public void popTableView() {

        appointmentTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                aptIdTxtField.setText(String.valueOf(newValue.getAppointmentID()));
                aptTitleTxtField.setText(String.valueOf(newValue.getAppointmentTitle()));
                aptDescTxtField.setText(String.valueOf(newValue.getAppointmentDesc()));
                aptLocationTxtField.setText(String.valueOf(newValue.getAppointmentLocation()));
                aptTypeTxtField.setText(String.valueOf(newValue.getAppointmentType()));
                aptContactTxtField.setText(String.valueOf(newValue.getContactID()));
                aptCustomerTxtField.setText(String.valueOf(newValue.getCustomerID()));
                aptUserTxtField.setText(String.valueOf(newValue.getUserID()));




                //startDatePicker.setDayCellFactory(newValue.getStart().getDayOfMonth());


            }
        });

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshAppointmentsTable();
        popTableView();
        popStartTimeCombo();
        popEndTimeCombo();


    }



}



