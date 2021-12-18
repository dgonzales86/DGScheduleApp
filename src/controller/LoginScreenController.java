package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBConnection;
import utils.DBQuery;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable
{
    Stage stage;
    Parent scene;

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


    public void loadSchedule() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("src/view/loginScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }


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

            JOptionPane.showMessageDialog(null,"Welcome!");
            loadSchedule();



            }else if (rowCount == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Login Credentials");
                alert.setContentText("You have entered an invalid username and/or password. Try again!");
                alert.showAndWait();
            }

        }catch (NullPointerException | IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static void verifyLogin(int userID, String userPassword){
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        locationIdLbl.setText(String.valueOf(ZoneId.systemDefault()));


    }
}
