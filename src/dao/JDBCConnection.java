package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnection {

public static void jdbcConnect(){
    try{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule","test","test");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from appointments");


        while (resultSet.next()){
            System.out.println(resultSet.getString("appointment_id"));
        }
    }catch (Exception e){

        System.out.println("");

    }
}


}
