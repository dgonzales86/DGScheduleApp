package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import DBConnection.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

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

    private ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle/LoginScreen");


    public void loadSchedule() throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displaySchedule.fxml"));
            Parent root = loader.load();
            DisplayScheduleController displayScheduleController = loader.getController();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (IllegalStateException e){
            e.printStackTrace();
            e.getMessage();
        }
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

            //JOptionPane.showMessageDialog(null,"Welcome!");
                try {

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
    // See about changing following function into a lambda...
    // Localizes login screen to either English of French.
    public void setRB(){


        if(Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr"))
            userNameLbl.setText(rb.getString("userNameLbl"));
            userPasswordLbl.setText(rb.getString("userPasswordLbl"));
            logInBtn.setText(rb.getString("logInBtn"));
            titleLbl.setText(rb.getString("titleLbl"));

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setRB();






        locationIdLbl.setText(String.valueOf(ZoneId.systemDefault()));


    }


}
