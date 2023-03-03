package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import static DAO.DBConnection.*;

/**
 * Main Class
 */
public class Main extends Application {

    /**
     * Loads login screen
     * @param primaryStage sets stage
     * @throws Exception thrown by getClass().getResource();
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("/view/loginScreen.fxml"));
        primaryStage.setTitle("Scheduling Application");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();
    }

    /**
     * Main method, establishes a database connection and closes connection with exit.
     * @param args main arguments
     * @throws SQLException via dbConnect() and closeConnection() methods
     */
    public static void main(String[] args) throws SQLException {
        //Locale.setDefault(new Locale("fr","CA"));      // Sets default to french to test log-in form

        dbConnect();
        launch(args);
        closeConnection();

    }
}
