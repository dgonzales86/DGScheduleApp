package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBQuery;

import java.sql.SQLException;

import static utils.DBConnection.*;


public class Main  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginScreen.fxml"));
        primaryStage.setTitle("Scheduling Application");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();
    }
    public static void main(String[] args) throws SQLException {

       //Locale.setDefault(new Locale("fr"));      // Sets default to french to test log-in form

        dbConnect();


        DBQuery.setStatement(connection);  // Creates Statement Object



     //   DBQuery.setStatement(connection);














        launch(args);
        closeConnection();



    }

    public static void lambdaTest(){

        System.out.println("lambdaWorks");

    }

}
