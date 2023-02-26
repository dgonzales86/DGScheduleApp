package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import DAO.DBConnection;
import util.UserLog;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * LoginScreenController Class
 */
public class LoginScreenController implements Initializable
{

    Stage stage;
    Parent scene;

    @FXML
    private Label titleLbl;

    @FXML
    private Label locationIdLbl;

    @FXML
    private Button logInBtn;

    @FXML
    private Label userNameLbl;

    @FXML
    private TextField userNameTextBox;

    @FXML
    private Label userPasswordLbl;

    @FXML
    private TextField userPasswordTextBox;

    private ResourceBundle rb = ResourceBundle.getBundle("resourceBundle/LoginScreen");

    /**
     * Queries database for a username and password match. If a match is found, the login is successful and the
     * application navigates to the schedule screen.
     * @param event - "Login" Button
     * @throws SQLException directly via SQL query
     */
    @FXML
    void onActionLogIn(ActionEvent event) throws SQLException {

        try{

            String userName = String.valueOf(userNameTextBox.getText());
            String userPassword = String.valueOf(userPasswordTextBox.getText());

            System.out.println(userName + userPassword);

            // Raw SQL
            String sql = "select * from users where User_Name='"+ userName +"' and Password='"+ userPassword +"'";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int rowCount = 0;
            while (rs.next()){
                rowCount++;
            }
            if(rowCount == 1){

                //JOptionPane.showMessageDialog(null,"Welcome!");
                try {
                    UserLog.successfulLoginActivity(userNameTextBox.getText().toString());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displaySchedule.fxml"));
                    Parent root = loader.load();
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
                    stage.setScene(new Scene(scene));
                    stage.show();

                }catch (IllegalStateException e){
                    e.printStackTrace();
                    e.getMessage();

                }

            }else if (rowCount == 0){
                UserLog.attemptedLoginActivity(userNameTextBox.getText().toString());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(rb.getString("InvalidLoginTitle"));
                alert.setContentText(rb.getString("InvalidLoginMessage"));
                alert.getButtonTypes().set(0, ButtonType.OK);
                alert.setResizable(true);
                alert.showAndWait();
            }

        }catch (NullPointerException | IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Performs same action is onActionLogIn() method, however allows for the ENTER key to execute method.
     * This method was implemented for no other reason than me, the developer being tired of having to use
     * the mouse to log in while testing my application :-)
     * @param keyEvent - "ENTER" key
     */
    public void ENTER(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {

            try {

                String userName = String.valueOf(userNameTextBox.getText());
                String userPassword = String.valueOf(userPasswordTextBox.getText());

                System.out.println(userName + userPassword);

                // Raw SQL
                String sql = "select * from users where User_Name='" + userName + "' and Password='" + userPassword + "'";
                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                int rowCount = 0;
                while (rs.next()) {
                    rowCount++;
                }
                if (rowCount == 1) {

                    //JOptionPane.showMessageDialog(null,"Welcome!");
                    try {
                        UserLog.successfulLoginActivity(userNameTextBox.getText());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displaySchedule.fxml"));
                        Parent root = loader.load();
                        stage = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                        Parent scene = loader.getRoot();
                        stage.setScene(new Scene(scene));
                        stage.show();

                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        e.getMessage();
                    }

                } else if (rowCount == 0) {
                    UserLog.attemptedLoginActivity(userNameTextBox.getText());
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(rb.getString("InvalidLoginTitle"));
                    alert.setContentText(rb.getString("InvalidLoginMessage"));
                    alert.getButtonTypes().set(0, ButtonType.OK);
                    alert.setResizable(true);
                    alert.showAndWait();
                }

            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Sets resource bundle for language translation, in french, on login screen
     */
    public void setRB () {

        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr"))
            userNameLbl.setText(rb.getString("userNameLbl"));
        userPasswordLbl.setText(rb.getString("userPasswordLbl"));
        logInBtn.setText(rb.getString("logInBtn"));
        titleLbl.setText(rb.getString("titleLbl"));

    }

    /**
     * Loads resource bundle, displays ZoneId of the system default
     * @param url - "LoginScreen.fxml"
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setRB();

        locationIdLbl.setText(String.valueOf(ZoneId.systemDefault()));


    }

}
