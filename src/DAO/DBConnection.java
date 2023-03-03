package DAO;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 DataConnection class to provide connection to MySQL DB.
 */
public class DBConnection {

    public static final String protocol = "jdbc";
    public static final String vendor = ":mysql:";
    public static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String db_URL = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; //LOCAL
    private static final String userName = "sqlUser";
    private static final String userPassword = "Passw0rd!";
    public static Connection connection;

    /**
     * Opens a database connection
     */
    public static void dbConnect(){
        try{

            connection = DriverManager.getConnection(db_URL,userName,userPassword);
            Statement statement = connection.createStatement();
            System.out.println("Connection Opened");

        }catch (SQLException se){
            System.out.println("Something didn't go quite right: " + se.getMessage());
            se.printStackTrace();
        }
    }

    /**
     * Getter for DB connection, returns connection
     * @return connection
     */
    public static Connection getConnection(){
        return connection;
    }

    /**
     * Closes DB connection.
     * Executed from main upon close of program.
     */
    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection Closed");
        }catch (SQLException se){
            System.out.println("Error! Connection failed to close: " + se.getMessage());
        }
    }

}
