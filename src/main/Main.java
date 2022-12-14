package main;


import DBConnection.UserQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.UserIDCounter;


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
       //Locale.setDefault(new Locale("fr","FR"));      // Sets default to french to test log-in form


        // Lambda Practice with GeneralInterface
//        GeneralInterface square = n -> n * n;
//        System.out.println(square.computeInt(5));
//        GeneralInterface sum = n -> n + 25;
//        System.out.println(sum.computeInt(5));

        dbConnect();

        /** inserts user into database */
     //  int rowsAffected = UserQuery.insertUser("nflict","Nflict5");

        UserQuery.selectUser("test",1); //does nothing
        /** deletes user from database */
        //int rowsAffected = UserQuery.deleteUser("newUsr1",5);

























        launch(args);
        closeConnection();

    }


}
