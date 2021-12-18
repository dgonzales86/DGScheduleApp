package utils;

import java.sql.*;



/**
 DataConnection class to provide connection to MySQL DB.
 */
public class DBConnection {

    public static final String databaseName = "client_schedule";
    public static final String db_URL = "jdbc:mysql://localhost:3306/"+databaseName;
    public static final String userName = "test";
    public static final String userPassword = "test";
    public static Connection connection;

    // Start Connection
    public static void dbConnect(){
    try{

        connection = DriverManager.getConnection(db_URL,userName,userPassword);
        Statement statement = connection.createStatement();
        System.out.println("Connection Opened");

    }catch (SQLException se){

//        System.out.println("Something didn't go quite right: " + se.getMessage());
        se.printStackTrace();

    }
}

public static Connection getConnection(){
        return connection;
}

    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection Closed");
        }catch (SQLException se){
            System.out.println("Error! Connection failed to close: " + se.getMessage());
        }
    }

}
