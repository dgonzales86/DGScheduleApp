
package controller;

import DAO.AppointmentsQuery;
import DAO.ContactsQuery;
import DAO.CustomersQuery;
import DAO.UserQuery;
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
import model.Contacts;
import model.Customers;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

import static DAO.AppointmentsQuery.populateAppointments;
import static DAO.AppointmentsQuery.getAllAppointments;




public class DisplayScheduleController implements Initializable {

    Stage stage;
    @FXML
    public ComboBox <Customers> customerCombo;
    @FXML
    public ComboBox <User> userCombo;
    @FXML

    public ComboBox <Contacts> contactComboBox;
    @FXML
    public Button deleteAptBtn;
    @FXML
    public Button clearFormBtn;
    @FXML
    public Button modifyAptBtn;

    private LocalDate localDate;

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    @FXML
    public TextField aptIdTxtField;
    @FXML
    public Button submitChangeBtn;

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

    public void onActionDeleteAppointment(ActionEvent actionEvent) throws SQLException {

        String title = aptTitleTxtField.getText();
        int aptID = Integer.parseInt(aptIdTxtField.getText());
        AppointmentsQuery.deleteAppointment(title,aptID);
        refreshAppointmentsTable();
        appointmentTableView.refresh();

    }

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


        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you would like\nto exit the application?\nUnsaved changes will not be stored!", ButtonType.YES, ButtonType.NO);
        alert.setResizable(true);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            System.exit(0);
        }
    }
    public void onActionStartTime(ActionEvent actionEvent) {
        startTime = (LocalTime) startTimeCombo.getValue();
        System.out.println(startTime);
    }

    public void onActionEndTime(ActionEvent actionEvent) {
        endTime = (LocalTime) endTimeCombo.getValue();
        System.out.println(endTime);
    }
    public void onActionSelectDate(ActionEvent actionEvent) {
        localDate = startDatePicker.getValue();
        System.out.println(localDate);
    }
    @FXML
    void onActionViewApptsByMonth(ActionEvent event) {

    }

    @FXML
    void onActionViewApptsByWeek(ActionEvent event) {

    }

    public void onActionViewReports(ActionEvent actionEvent) {
    }

    public void onActionAddAppointment(ActionEvent actionEvent) throws SQLException {


        appointmentStart = LocalDateTime.of(localDate,startTime);
        appointmentEnd = LocalDateTime.of(localDate,endTime);

        String aptTitle = aptTitleTxtField.getText();
        String aptDesc = aptDescTxtField.getText();
        String aptLoc = aptLocationTxtField.getText();
        String aptType = aptTypeTxtField.getText();

        Contacts contact = contactComboBox.getSelectionModel().getSelectedItem();
        int aptContact = contact.getContactID();

        Customers customers = customerCombo.getSelectionModel().getSelectedItem();
        int aptCustomer = customers.getCustomerID();

        User users = userCombo.getSelectionModel().getSelectedItem();
        int aptUser = users.getUserID();

        if (appointmentEnd.isAfter(appointmentStart)){
            AppointmentsQuery.insertAppointment(aptTitle,aptDesc,aptLoc,aptType,appointmentStart,appointmentEnd,aptCustomer,aptUser,aptContact);
            appointmentTableView.refresh();
            refreshAppointmentsTable();
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"End time cannot be before start time!");
            alert.showAndWait();
        }

    }
    public void onActionModifyApt(ActionEvent actionEvent) throws SQLException {

        appointmentStart = LocalDateTime.of(localDate,startTime);
        appointmentEnd = LocalDateTime.of(localDate,endTime);
        String aptTitle = aptTitleTxtField.getText();
        String aptDesc = aptDescTxtField.getText();
        String aptLoc = aptLocationTxtField.getText();
        String aptType = aptTypeTxtField.getText();

        Contacts contact = contactComboBox.getSelectionModel().getSelectedItem();
        int aptContact;
        if(contact != null){
            aptContact = contact.getContactID();
        }else {
            aptContact = Integer.parseInt(contactComboBox.getSelectionModel().getSelectedItem().toString());
        }

        Customers customer = customerCombo.getSelectionModel().getSelectedItem();
        int aptCustomer;
        if (customer != null){
            aptCustomer = customer.getCustomerID();
        }else {
            aptCustomer = Integer.parseInt(customerCombo.getSelectionModel().getSelectedItem().toString());
        }

        User users = userCombo.getSelectionModel().getSelectedItem();
        int aptUser;
        if (users != null){
            aptUser = users.getUserID();
        }else {
            aptUser = Integer.parseInt(userCombo.getSelectionModel().getSelectedItem().toString());
        }

        int aptID = Integer.parseInt(aptIdTxtField.getText());

        if (appointmentEnd.isAfter(appointmentStart)){
            AppointmentsQuery.updateAppointment(aptTitle,aptDesc,aptLoc,aptType,appointmentStart,appointmentEnd,aptCustomer,aptUser,aptContact,aptID);
            refreshAppointmentsTable();
            appointmentTableView.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"End time cannot be before start time!");
            alert.showAndWait();
        }


    }

    public void popStartTimeCombo() {

        localDate = LocalDate.now();
        startTime = LocalTime.of(8, 0);
        endTime = LocalTime.of(22, 0);
        LocalDateTime startLocalDateTime = LocalDateTime.of(localDate,startTime);
        ZonedDateTime startZonedDateTime = startLocalDateTime.atZone(ZoneId.of("America/New_York"));
        startZonedDateTime = startZonedDateTime.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime endLocalDateTime = LocalDateTime.of(localDate,endTime);
        ZonedDateTime endZonedDateTime = endLocalDateTime.atZone(ZoneId.of("America/New_York"));
        endZonedDateTime = endZonedDateTime.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime startComboTime = startZonedDateTime.toLocalTime();
        LocalTime endComboTime = endZonedDateTime.toLocalTime();

        while (startComboTime.isBefore(endComboTime)) {
            startTimeCombo.getItems().add(startComboTime);
            startComboTime = startComboTime.plusMinutes(15);
        }
        startTimeCombo.getSelectionModel().select(startZonedDateTime.toLocalTime());
    }

    public void popEndTimeCombo() {


        startTime = LocalTime.of(8, 15);
        endTime = LocalTime.of(22, 0);
        LocalDateTime startLocalDateTime = LocalDateTime.of(localDate,startTime);
        ZonedDateTime startZonedDateTime = startLocalDateTime.atZone(ZoneId.of("America/New_York"));
        startZonedDateTime = startZonedDateTime.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime endLocalDateTime = LocalDateTime.of(localDate,endTime);
        ZonedDateTime endZonedDateTime = endLocalDateTime.atZone(ZoneId.of("America/New_York"));
        endZonedDateTime = endZonedDateTime.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime startComboTime = startZonedDateTime.toLocalTime();
        LocalTime endComboTime = endZonedDateTime.toLocalTime();

        while (startComboTime.isBefore(endComboTime.plusSeconds(1))) {
            endTimeCombo.getItems().add(startComboTime);
            startComboTime = startComboTime.plusMinutes(15);
        }
        endTimeCombo.getSelectionModel().select(startZonedDateTime.toLocalTime());
    }
    public void setComboBoxes() throws SQLException {

        contactComboBox.setItems(ContactsQuery.populateContacts());

        customerCombo.setItems(CustomersQuery.populateCustomers());

        userCombo.setItems(UserQuery.populateUsers());

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

    public void onActionContactCombo(ActionEvent actionEvent) {
    }


    public void onActionClearForm(ActionEvent actionEvent) {

        aptTitleTxtField.setText(null);
        aptDescTxtField.setText(null);
        aptLocationTxtField.setText(null);
        aptTypeTxtField.setText(null);
        startDatePicker.setValue(null);
        startTimeCombo.getSelectionModel().clearSelection();
        endTimeCombo.getSelectionModel().clearSelection();
        contactComboBox.getSelectionModel().clearSelection();
        contactComboBox.setValue(null);
        customerCombo.getSelectionModel().clearSelection();
        customerCombo.setValue(null);
        userCombo.getSelectionModel().clearSelection();
        userCombo.setValue(null);
        aptIdTxtField.setText(null);



    }


    public void getAppointmentSelection() {

        appointmentTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {




                aptIdTxtField.setText(String.valueOf(newValue.getAppointmentID()));
                aptTitleTxtField.setText(String.valueOf(newValue.getAppointmentTitle()));
                aptDescTxtField.setText(String.valueOf(newValue.getAppointmentDesc()));
                aptLocationTxtField.setText(String.valueOf(newValue.getAppointmentLocation()));
                aptTypeTxtField.setText(String.valueOf(newValue.getAppointmentType()));
                startDatePicker.setValue(newValue.getStart().toLocalDate());
                startTimeCombo.setValue(newValue.getStart().toLocalTime());
                endTimeCombo.setValue(newValue.getEnd().toLocalTime());

                try {
                    customerCombo.setValue(CustomersQuery.getCustomer(newValue.getCustomerID()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    userCombo.setValue(UserQuery.getUser(newValue.getUserID()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    contactComboBox.setValue(ContactsQuery.getContact(newValue.getContactID()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setComboBoxes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        refreshAppointmentsTable();
        getAppointmentSelection();
        popStartTimeCombo();
        popEndTimeCombo();
        aptIdTxtField.setDisable(true);


    }


}



