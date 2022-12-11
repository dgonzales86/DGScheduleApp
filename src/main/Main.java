package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import static DBConnection.DBConnection.*;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("/view/loginScreen.fxml"));
        primaryStage.setTitle("Scheduling Application");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();
    }
    public static void main(String[] args) throws SQLException {
        Locale.setDefault(new Locale("fr","FR"));      // Sets default to french to test log-in form



        dbConnect();

        /** inserts user into database */
       // int rowsAffected = UserQuery.insert(3,"Nflict2","Nflict3");

























        launch(args);
        closeConnection();

    }


}
