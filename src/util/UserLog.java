package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UserLog {

    static LocalDateTime localDateTime = LocalDateTime.now();
    static ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

    static String filename = "Login_Attempts.txt";
    public static void attemptedLoginActivity(String userName) throws IOException {
        FileWriter fileWriter = new FileWriter(filename,true);
        PrintWriter logInAttempt = new PrintWriter(fileWriter);
        logInAttempt.println("User: " + userName + ", has attempted to log in at " + zonedDateTime);
        logInAttempt.close();

    }
    public static void sucessfulLoginActivity(String userName) throws IOException{
        FileWriter fileWriter = new FileWriter(filename,true);
        PrintWriter logInAttempt = new PrintWriter(fileWriter);
        logInAttempt.println("User: " + userName + ", has logged in at " + zonedDateTime);
        logInAttempt.close();
    }

}
