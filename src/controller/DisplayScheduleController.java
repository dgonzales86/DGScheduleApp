
package controller;

import DAO.AppointmentsQuery;
import DAO.ContactsQuery;
import DAO.CustomersQuery;
import DAO.UserQuery;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.User;
import util.CheckForOverlapApt;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import static DAO.AppointmentsQuery.populateAppointments;
import static DAO.AppointmentsQuery.getAllAppointments;


/**
 * DisplayScheduleController
 * Implements methods to control functions within the GUI
 * Implements majority of features
 */
public class DisplayScheduleController implements Initializable {
    Stage stage;
    /**
     * Toggle group for appointment filter
     */
    @FXML
    public ToggleGroup sortDate;
    /**
     * toggle button to view all appointments
     */
    @FXML
    public RadioButton allAppointments;
    /**
     * text to provide use instructions
     */
    @FXML
    public Text buttonHelperTxt;
    /**
     * text to provide use instructions
     */
    @FXML
    public Text addModifyAptHelperTxt;
    /**
     * Customers comboBox to select a customer
     */
    @FXML
    public ComboBox <Customers> customerCombo;
    /**
     * user combo box to select a user
     */
    @FXML
    public ComboBox <User> userCombo;
    /**
     * contact combo box to select a contact
      */
    @FXML
    public ComboBox <Contacts> contactComboBox;
    /**
     * delete appointment button to delete an appointment
     */
    @FXML
    public Button deleteAptBtn;
    /**
     * clear form button clears form to add a new appointment
     */
    @FXML
    public Button clearFormBtn;
    /**
     * modify appointment button to submit modifications to appointment
     */
    @FXML
    public Button modifyAptBtn;
    /**
     * LocalDate variable to store a LocalDate
     */

    private LocalDate localDate;

    /**
     * Stores a start time
     */
    private LocalTime startTime;
    /**
     * stores an end time
     */
    private LocalTime endTime;
    /**
     * stores appointment start time
     */
    private LocalDateTime appointmentStart;
    /**
     * stores appointment end time
     */
    private LocalDateTime appointmentEnd;
    /**
     * used as appointment id label
     */
    @FXML
    public TextField aptIdTxtField;
    /**
     * submit chages button to modify appointments.
     * currently unused outside fxml file
     */
    @FXML
    public Button submitChangeBtn;
    /**
     * Appointment title text field
     * used to enter appointment title
     */
    @FXML
    public TextField aptTitleTxtField;
    /**
     * appointment description text field
     * used for entering appointment description
     */
    @FXML
    public TextField aptDescTxtField;
    /**
     * appointment location text-field
     * used to enter appointment location
     */
    @FXML
    public TextField aptLocationTxtField;
    /**
     * appointment type text field
     * used for entering appointment type
     */
    @FXML
    public TextField aptTypeTxtField;
    /**
     * appointment date picker
     * used to select a date for an appointment
     */
    @FXML
    public DatePicker startDatePicker;
    /**
     * reports button
     * navigates to reports screen
     * currently implemented by onAction method
     */
    @FXML
    public Button reportsBtn;
    /**
     * start time combo box
     * used to select appointment start time
     */
    @FXML
    public ComboBox startTimeCombo;
    /**
     * end time combo box
     * used to select appointment end time
     */
    @FXML
    public ComboBox endTimeCombo;
    /**
     * used to filter appointment by month
     * onAction method used
     */
    @FXML
    public RadioButton apptByMonthRadioBtn;
    /**
     * used to filter appointment by month
     * onAction method used
     */
    @FXML
    public RadioButton apptsByWeekRadioBtn;
    /**
     * Tableview column: for displaying contact id
     */
    @FXML
    private TableColumn<?, ?> appointmentContactCol;
    /**
     * Tableview column: for displaying customer id
     */
    @FXML
    private TableColumn<?, ?> appointmentCustomerCol;
    /**
     * Tableview column: used for displaying appointment description
     */
    @FXML
    private TableColumn<?, ?> appointmentDescriptionCol;
    /**
     * Tableview column: used to display appointment end date/time
     */
    @FXML
    private TableColumn<?, ?> appointmentEndCol;
    /**
     * Tableview column: used to display appointment id
     */
    @FXML
    private TableColumn<?, ?> appointmentIDCol;
    /**
     * Tableview column: used to display appointment location information
     */
    @FXML
    private TableColumn<?, ?> appointmentLocationCol;
    /**
     * Tableview column: displays appointment start date/time
     */
    @FXML
    private TableColumn<?, ?> appointmentStartCol;
    /**
     * Tableview used to display all appointments
     */
    @FXML
    private TableView<Appointments> appointmentTableView;
    /**
     * Tableview column: used to display appointment title information
     */
    @FXML
    private TableColumn<?, ?> appointmentTitleCol;
    /**
     * Tableview column: used to display appointment type information
     */
    @FXML
    private TableColumn<?, ?> appointmentTypeCol;
    /**
     * Tableview column: used to display appointment user id
     */
    @FXML
    private TableColumn<?, ?> appointmentUserCol;
    /**
     * customers button
     * onAction method used to implement method
     * provides no function
     */
    @FXML
    private Button customersBtn;
    /**
     * currently provides no function
     * onAction method used
     */
    @FXML
    private Button exitBtn;

    /**
     * Displays instructions for radio button selection
     * @param mouseEvent - mouseEvent mouse hover over radio button "View Appointments by Week"
     */
    public void allAptsOnMouseEnter(MouseEvent mouseEvent) {
        buttonHelperTxt.setText("Select to view All Appointments (Default View)");
    }

    /**
     * Displays instructions for button selection
     * @param mouseEvent - mouseEvent mouse hover over button "Cancel Appointment"
     */
    public void deleteAptOnMouseEnter(MouseEvent mouseEvent) {
        buttonHelperTxt.setText("Select to cancel an existing appointment");
    }

    /**
     * Displays instructions for radio button selection
     * @param mouseEvent - mouseEvent mouse hover over radio button " View Appointments by Week"
     */
    public void aptByWeekOnMouseEnter(MouseEvent mouseEvent) {
        buttonHelperTxt.setText("Select to view most recent weeks appointments");
    }

    /**
     * Displays instructions for radio button selection
     * @param mouseEvent - mouseEvent mouse hover over radio button "View Appointments by Month"
     */
    public void aptByMonthOnMouseEnter(MouseEvent mouseEvent) {
        buttonHelperTxt.setText("Select to view current month's appointments");
    }

    /**
     * Displays instructions for radio button selection
     * @param mouseEvent - mouseEvent, mouse hover over button "Exit Application"
     */
    public void exitOnMouseEnter(MouseEvent mouseEvent) {
        buttonHelperTxt.setText("Select to exit application");
    }

    /**
     * Displays instructions for button selection
     * @param mouseEvent - mouseEvent, mouse hover over button "Customers"
     */
    public void customersOnMouseEnter(MouseEvent mouseEvent) {
        buttonHelperTxt.setText("Select to add or modify customers");
    }

    /**
     * Displays instructions for button selection
     * @param mouseEvent mouseEvent, mouse hover over button "Reports"
     */
    public void reportsOnMouseEnter(MouseEvent mouseEvent) {
        buttonHelperTxt.setText("Select to view reports");
    }

    /**
     * Displays instructions for button selection
     * @param mouseEvent mouseEvent, mouse hover over button "Clear Form"
     */
    public void clearFormOnMouseEnter(MouseEvent mouseEvent) {
        buttonHelperTxt.setText("Select to clear form prior to adding a new appointment");
    }

    /**
     * Displays instructions for button selection
     * @param mouseEvent mouseEvent, mouse hover over button "Submit Modify Appointment"
     */
    public void modifyAptOnMouseEnter(MouseEvent mouseEvent) {
        addModifyAptHelperTxt.setText("Select when ready to save modifications to appointment");
    }

    /**
     * Displays instructions for button selection
     * @param mouseEvent mouseEvent, mouse hover over button "Add Appointment"
     */
    public void addAptOnMouseEnter(MouseEvent mouseEvent) {
        addModifyAptHelperTxt.setText("Select to save appointment. No fields may be empty!");
    }

    /**
     * Deletes selected appointment, first checking for null text fields and combo boxes
     * @param actionEvent actionEvent "Cancel Appointment" button click
     * @throws SQLException via AppointmentsQuery.deleteAppointments() method
     */
    public void onActionDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        if (aptIdTxtField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You must first select an appointment to cancel!");
            alert.setResizable(true);
            alert.showAndWait();
        } else {
            String title = aptTitleTxtField.getText();
            int aptID = Integer.parseInt(aptIdTxtField.getText());
            Alert cancelAptConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to cancel this appointment?");
            cancelAptConfirmation.setResizable(true);
            Optional<ButtonType> result = cancelAptConfirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                AppointmentsQuery.deleteAppointment(title, aptID);

                String aptTitle = aptTitleTxtField.getText();
                refreshAppointmentsTable();
                appointmentTableView.refresh();
                clearForm();
                Alert cancelConfirmation = new Alert(Alert.AlertType.INFORMATION, "Appointment:  #" + aptID + " " + aptTitle + ", has been canceled!");
                cancelConfirmation.setResizable(true);
                cancelConfirmation.showAndWait();

            }else {return;}
        }
    }

    /**
     * Navigates to customers screen
     * @param event "Customers" button click
     * @throws IOException via loader.load() method
     */
    @FXML
    void onActionCustomers(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customers.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Exits application, first verifying intent
     * @param event "Exit Application" button click
     */
    @FXML
    void onActionExit(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you would like\nto exit the application?\nUnsaved changes will not be stored!", ButtonType.YES, ButtonType.NO);
        alert.setResizable(true);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            System.exit(0);
        }
    }

    /**
     * Sets startTime as a LocalTime object based on startTimeCombo combo box selection
     * @param actionEvent startTimeCombo combo box selection
     */
    public void onActionStartTime(ActionEvent actionEvent) {
        startTime = (LocalTime) startTimeCombo.getValue();
        System.out.println(startTime);
    }

    /**
     * Sets endTime as a LocalTime object based on endTimeCombo combo box selection
     * @param actionEvent endTimeCombo combo box selection
     */
    public void onActionEndTime(ActionEvent actionEvent) {
        endTime = (LocalTime) endTimeCombo.getValue();
        System.out.println(endTime);
    }

    /**
     * Sets localDate as LocalDate object based on date selection in date picker
     * @param actionEvent startDatePicker selection
     */
    public void onActionSelectDate(ActionEvent actionEvent) {
        localDate = startDatePicker.getValue();
    }

    /**
     * Calls filterAptByWeek() method to filter all but the next 7 days worth of appointments
     * @param event "View Appointments by Week" radio button
     */
    @FXML
    public void onActionViewApptsByWeek(ActionEvent event) {
        filterAptByWeek();
    }

    /**
     * Calls filterAptByMonth() method to filter appointments
     * @param actionEvent "View Appointments by Month" radio button selection
     */
    @FXML
    public void onActionViewApptsByMonth(ActionEvent actionEvent) {
        filterAptByMonth();
    }

    /**
     * Loads all appointments and refreshes appointments tableview
     * @param actionEvent "View All Appointments" radio button
     */
    public void onActionAllAppointments(ActionEvent actionEvent) {
        refreshAppointmentsTable();
    }
    /**
     * Navigates to reports screen
     * @param actionEvent "Reports" button click
     * @throws IOException via loader.load() method
     */
    public void onActionViewReports(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reports.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Lamba expression 2
     * This lambda expression, uses the functional interface CheckForOverlapApt. The lambda expression, is really three lambda expressions used to check for overlapping time conditions.
     * All three expressions are used in both onActionAddAppointment(), and onActionModifyAppointment() methods to check for overlapping appointment times.
     * This lambda expression greatly improved the code by allowing me to create only one functional interface, and use the variables to write check conditions directly in the controller.
     * Without utilizing this lambda expression, three separate methods would have to be written to achieve the same result. Additionally, each of these methods would have to be called in the
     * add and modify appointments method.
     */
    CheckForOverlapApt checkOverlapCond1 = (LocalDateTime start1,LocalDateTime end1,LocalDateTime start2,LocalDateTime end2) -> ((start2.isAfter(start1) || start2.isEqual(start1)) && (start2.isBefore(end1)));
    CheckForOverlapApt checkOverlapCond2 = (LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) ->((end2.isAfter(start1) && end2.isBefore(end1)) || end2.isEqual(end1));
    CheckForOverlapApt checkOverlapCond3 = (LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) ->((start2.isBefore(start1) || start2.isEqual(start1)) && (end2.isAfter(end1) || end2.isEqual(end1)));

    /**
     * Adds a new appointment to the database and populates it on the tableview.
     * First checks that all fields are completed, then checks the end time to make sure it is after the start time.
     * Next the appointment checks existing users, contacts, and customers for overlapping appointments during the selected timeframe
     * If an overlap is found, an alert will pop up signaling which of the three have a conflicting appointment and the method will return.
     * If no overlapping time is found AppointmentsQuery.insertAppointment() is called, adding the appointment.
     * an alert will signal notifying that the appointment was added successfully.
     * @param actionEvent "Add Appointment" button click
     * @throws SQLException via DAO.AppointmentsQuery.insertAppointment()
     */
    public void onActionAddAppointment(ActionEvent actionEvent) throws SQLException {

        if (startTimeCombo != null && startTimeCombo.getValue() != null && startTimeCombo.getSelectionModel().getSelectedItem() != null
                && endTimeCombo != null && endTimeCombo.getValue() != null
                && aptTitleTxtField != null && aptTitleTxtField.getText() != null && !aptTitleTxtField.getText().isEmpty()
                && startDatePicker != null && startDatePicker.getValue() != null
                && aptDescTxtField != null && aptDescTxtField.getText() != null && !aptDescTxtField.getText().isEmpty()
                && aptLocationTxtField != null && aptLocationTxtField.getText() != null && !aptLocationTxtField.getText().isEmpty()
                && aptTypeTxtField != null && aptTypeTxtField.getText() != null && !aptTypeTxtField.getText().isEmpty()
                && contactComboBox != null && contactComboBox.getSelectionModel().getSelectedItem() != null
                && customerCombo != null && customerCombo.getSelectionModel().getSelectedItem() != null
                && userCombo != null && userCombo.getSelectionModel().getSelectedItem() != null){



            appointmentStart = LocalDateTime.of(localDate, startTime);
            appointmentEnd = LocalDateTime.of(localDate, endTime);
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

            AtomicBoolean userOverlapFound = new AtomicBoolean(false);
            AtomicBoolean customerOverlapFound = new AtomicBoolean(false);
            AtomicBoolean contactOverlapFound = new AtomicBoolean(false);

            if (appointmentEnd.isAfter(appointmentStart)) {

                for (Appointments appointment : AppointmentsQuery.getAllAppointments()) {
                    if (appointment.getUserID() == aptUser) {
                        userOverlapFound.compareAndSet(false, checkOverlapCond1.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                || checkOverlapCond2.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                || checkOverlapCond3.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd));
                    }
                    if (appointment.getCustomerID() == aptCustomer) {
                        customerOverlapFound.compareAndSet(false, checkOverlapCond1.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                || checkOverlapCond2.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                || checkOverlapCond3.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd));

                    }
                    if (appointment.getContactID() == aptContact) {
                        contactOverlapFound.compareAndSet(false, checkOverlapCond1.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                || checkOverlapCond2.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                || checkOverlapCond3.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd));
                    }
                }

                if (userOverlapFound.get() || customerOverlapFound.get() || contactOverlapFound.get()) {
                    if (userOverlapFound.get()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The selected user has an existing appointment in this timeframe.\nSelect another user or select another user or change appointment time!");
                        alert.setResizable(true);
                        alert.getDialogPane().setMinWidth(450);
                        alert.showAndWait();

                    }
                    if (customerOverlapFound.get()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The selected customer has an existing appointment in this timeframe.\nSelect another appointment time!");
                        alert.setResizable(true);
                        alert.getDialogPane().setMinWidth(450);
                        alert.showAndWait();

                    }
                    if (contactOverlapFound.get()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The selected contact has an existing appointment in this timeframe.\nSelect another contact or change appointment time!");
                        alert.setResizable(true);
                        alert.getDialogPane().setMinWidth(450);
                        alert.showAndWait();

                    }

                }else {
                    AppointmentsQuery.insertAppointment(aptTitle, aptDesc, aptLoc, aptType, appointmentStart, appointmentEnd, aptCustomer, aptUser, aptContact);
                    appointmentTableView.refresh();
                    refreshAppointmentsTable();
                    clearRadioBtnSelection(sortDate);
                    clearForm();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Appointment added successfully!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "End time cannot be before start time!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Make sure all fields are complete and try again!");
            alert.setResizable(true);
            alert.showAndWait();
            return;
        }
    }

    /**
     * After selecting an appointment, text fields and combo boxes will populate with appointment information.
     * The "Appointment ID" field is used to call AppointmentsQuery.updateAppointment(), but first the contact, user,
     * and customers are checked for overlapping appointments; the current appointment id is excluded.
     * if no overlaps are found, the appointment is added, otherwise alerts display messages indicating which
     * user, contact, or customer, has an overlap.
     * @param actionEvent "Submit Modify Appointment"
     * @throws SQLException
     */
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

        AtomicBoolean userOverlapFound = new AtomicBoolean(false);
        AtomicBoolean customerOverlapFound = new AtomicBoolean(false);
        AtomicBoolean contactOverlapFound = new AtomicBoolean(false);

        if (startTimeCombo != null && startTimeCombo.getValue() != null && startTimeCombo.getSelectionModel().getSelectedItem() != null
                && endTimeCombo != null && endTimeCombo.getValue() != null
                && aptTitleTxtField != null && aptTitleTxtField.getText() != null && !aptTitleTxtField.getText().isEmpty()
                && startDatePicker != null && startDatePicker.getValue() != null
                && aptDescTxtField != null && aptDescTxtField.getText() != null && !aptDescTxtField.getText().isEmpty()
                && aptLocationTxtField != null && aptLocationTxtField.getText() != null && !aptLocationTxtField.getText().isEmpty()
                && aptTypeTxtField != null && aptTypeTxtField.getText() != null && !aptTypeTxtField.getText().isEmpty()
                && contactComboBox != null && contactComboBox.getSelectionModel().getSelectedItem() != null
                && customerCombo != null && customerCombo.getSelectionModel().getSelectedItem() != null
                && userCombo != null && userCombo.getSelectionModel().getSelectedItem() != null){

            //add check for end time before start time
            if (appointmentEnd.isAfter(appointmentStart)){
                for(Appointments appointment : AppointmentsQuery.getAllAppointments()) {
                    if (appointment.getAppointmentID() != aptID)
                    {
                        if (appointment.getUserID() == aptUser) {
                            userOverlapFound.compareAndSet(false, checkOverlapCond1.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                    || checkOverlapCond2.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                    || checkOverlapCond3.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd));
                        }
                        if (appointment.getCustomerID() == aptCustomer) {
                            customerOverlapFound.compareAndSet(false, checkOverlapCond1.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                    || checkOverlapCond2.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                    || checkOverlapCond3.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd));

                        }
                        if (appointment.getContactID() == aptContact) {
                            contactOverlapFound.compareAndSet(false, checkOverlapCond1.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                    || checkOverlapCond2.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd)
                                    || checkOverlapCond3.checkForOverlap(appointment.getStart(), appointment.getEnd(), appointmentStart, appointmentEnd));
                        }

                        }
                    }
                    if (userOverlapFound.get() || customerOverlapFound.get() || contactOverlapFound.get()) {
                        if(userOverlapFound.get()){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The selected user has an existing appointment in this timeframe.\nSelect another user or select another user or change appointment time!");
                            alert.setResizable(true);
                            alert.getDialogPane().setMinWidth(450);
                            alert.showAndWait();
                            return;
                        }
                        if(customerOverlapFound.get()){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The selected customer has an existing appointment in this timeframe.\nSelect another appointment time!");
                            alert.setResizable(true);
                            alert.getDialogPane().setMinWidth(450);
                            alert.showAndWait();
                            return;
                        }
                        if (contactOverlapFound.get()){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The selected contact has an existing appointment in this timeframe.\nSelect another contact or change appointment time!");
                            alert.setResizable(true);
                            alert.getDialogPane().setMinWidth(450);
                            alert.showAndWait();
                            return;
                        }
                }

                AppointmentsQuery.updateAppointment(aptTitle,aptDesc,aptLoc,aptType,appointmentStart,appointmentEnd,aptCustomer,aptUser,aptContact,aptID);
                refreshAppointmentsTable();
                appointmentTableView.refresh();
                clearRadioBtnSelection(sortDate);
                clearForm();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Appointment " + aptID + " successfully modified!");
                alert.show();

            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING,"End time cannot be before start time!");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"No fields can be left blank!");
            alert.showAndWait();
            return;
        }
    }

    /**
     * Populates the start time combo box with available appointment times for their local timezone thus satisfying the
     * business hours requirement
     */
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

    /**
     * Populates end time combo box with appropriate end times for their timezone, thus satisfying the business hours
     * requirement
     */
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

    /**
     * Sets combo boxes via observable lists. Observable lists are set through database queries.
     * @throws SQLException
     */
    public void setComboBoxes() throws SQLException {

        contactComboBox.setItems(ContactsQuery.populateContacts());

        customerCombo.setItems(CustomersQuery.populateCustomers());

        userCombo.setItems(UserQuery.populateUsers());

    }

    /**
     * Clears and repopulates observable list with updated data. The tableview is then refreshed.
     */
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

    /**
     * Calls the clearForm() method.
     * @param actionEvent "Clear Form" button click
     */
    public void onActionClearForm(ActionEvent actionEvent) {
        clearForm();
    }

    /**
     * Sets all text fields, combo boxes, and date picker to null
     */
    public void clearForm(){

        aptTitleTxtField.setText(null);
        aptDescTxtField.setText(null);
        aptLocationTxtField.setText(null);
        aptTypeTxtField.setText(null);
        startDatePicker.setValue(null);
        startTimeCombo.getSelectionModel().clearSelection();
        startTimeCombo.setValue(null);
        endTimeCombo.getSelectionModel().clearSelection();
        endTimeCombo.setValue(null);
        contactComboBox.getSelectionModel().clearSelection();
        contactComboBox.setValue(null);
        customerCombo.getSelectionModel().clearSelection();
        customerCombo.setValue(null);
        userCombo.getSelectionModel().clearSelection();
        userCombo.setValue(null);
        aptIdTxtField.setText(null);
        aptIdTxtField.setText("");
    }

    /**
     *      * Calls addListener on selected customer observable value and passes a lambda as an argument.
     *      * The lambda expression takes 3 parameters: observableValue, oldValue, and newValue: the observableValue(selection) triggers
     *      * event, oldValue is the old value of the selected item, and newValue is the new value. If values are not null, text fields
     *      * are set to the newValue.
     */
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

    /**
     * Checks appointments for an appointment within 15 minutes of current time. if an upcoming appointment exists,
     * an alert will display indicating such. Otherwise, an alert will display indicating no upcoming appointments.
     */
    public void upcomingApt() {


        if (DAO.AppointmentsQuery.getAllAppointments().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "There are no upcoming appointments!");
            alert.showAndWait();
        } else {
            boolean appointmentFound = false;
            LocalDateTime appointmentStart = LocalDateTime.now();
            int appointmentID =0;
            for (Appointments appointments : DAO.AppointmentsQuery.getAllAppointments()) {
                System.out.println(appointments.getStart());

                if (appointments.getStart().isAfter(LocalDateTime.now()) && appointments.getStart().isBefore(LocalDateTime.now().plusMinutes(15))) {
                    appointmentFound = true;
                    appointmentStart = appointments.getStart();
                    appointmentID = appointments.getAppointmentID();

                }

            }
            if (appointmentFound){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Appointment ID: " + appointmentID + ", will begin at " + appointmentStart);
                alert.setResizable(true);
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "There are no upcoming appointments!");
                alert.showAndWait();

            }
        }
    }

    /**
     * Filters appointments my month by checking the appointment month against the current month.
     * The appointments are added to a list, and the tableview is set with this list.
     */
    public void filterAptByMonth(){
        refreshAppointmentsTable();
        ObservableList<Appointments> filteredAppointmentsByMonth = FXCollections.observableArrayList();
        if (DAO.AppointmentsQuery.getAllAppointments().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"There are no existing appointments!");
        }else {
            for (Appointments appointments: AppointmentsQuery.getAllAppointments()){
                if(appointments.getStart().getMonth() == LocalDateTime.now().getMonth() && appointments.getStart().getYear() == LocalDateTime.now().getYear()){

                    filteredAppointmentsByMonth.add(appointments);

                    appointmentTableView.setItems(filteredAppointmentsByMonth);
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
                    appointmentTableView.refresh();
                }
            }
        }
    }

    /**
     * Filters appointments for appointments within the next 7 days. The appointment date is checked against the current date.
     * the appointments are added to the list and the tableview is set with these appointments
     */
    public void filterAptByWeek(){
        refreshAppointmentsTable();
        ObservableList<Appointments> filteredAppointmentsByWeek = FXCollections.observableArrayList();
        if (DAO.AppointmentsQuery.getAllAppointments().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"There are no existing appointments!");
        }else {
            for (Appointments appointments: AppointmentsQuery.getAllAppointments()){
                if(appointments.getStart().getDayOfMonth() == LocalDateTime.now().getDayOfMonth() || appointments.getStart().isBefore(LocalDateTime.now().plusDays(7))
                        && appointments.getStart().isAfter(LocalDateTime.of(LocalDateTime.now().getYear(),1,1,0,0))){

                    filteredAppointmentsByWeek.add(appointments);
                    appointmentTableView.setItems(filteredAppointmentsByWeek);
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

                    appointmentTableView.refresh();
                }
            }
        }
    }

    /**
     * Clears radio button selection.
     * Useful for after adding or modifying appointments to make a more intuitive feel.
     * When an add or modification is made, the full schedule is loaded and radio button unselected.
     * The filters can be reapplied by selecting a radio button of choice.
     * @param sortDate
     */
    public void clearRadioBtnSelection(ToggleGroup sortDate) {
        if (sortDate.getSelectedToggle() != null) {
            sortDate.getSelectedToggle().setSelected(false);
        }
    }

    /**
     * Sets combo boxes, refreshes and populates appointments tableview, disables appointment id text field,
     * and clears form. clearForm ensures that nothing is preselected.
     * upcomingApt() method is called upon start to notify of upcoming appointments.
     * @param url - displaySchedule.fxml
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setComboBoxes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            AppointmentsQuery.populateAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        refreshAppointmentsTable();
        getAppointmentSelection();
        popStartTimeCombo();
        popEndTimeCombo();
        aptIdTxtField.setDisable(true);
        clearForm();
        upcomingApt();

    }


}



