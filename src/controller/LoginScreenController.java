package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable
{
//    Stage stage;
//    Parent scene;

    @FXML
    private Button logInBtn;

    @FXML
    private Label userIDLbl;

    @FXML
    private TextField userIDTxtbox;

    @FXML
    private Label userPasswordLbl;

    @FXML
    private TextField userPasswordTextBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
