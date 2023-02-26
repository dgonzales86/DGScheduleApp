package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * UserLog Class
 */
public class UserLog {

    static LocalDateTime localDateTime = LocalDateTime.now();
    static ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

    static String filename = "Login_Attempts.txt";

    /**
     * Logs unsuccessful login attempts to an external file using a PrintWriter Class. The PrintWriter is set to append changes by using
     * a FileWriter Class.
     * @param userName - Logs userName that is passed in the method
     * @throws IOException via FileWriter and PrintWriter as they are part of the java.io package
     */
    public static void attemptedLoginActivity(String userName) throws IOException {
        FileWriter fileWriter = new FileWriter(filename,true);
        PrintWriter logInAttempt = new PrintWriter(fileWriter);
        logInAttempt.println("User: " + userName + ", has attempted to log in at " + zonedDateTime);
        logInAttempt.close();
    }

    /**
     * Logs successful login attempts to an external file using a PrintWriter Class. The PrintWriter is set to append changes by using
     * a FileWriter Class.
     * @param userName
     * @throws IOException via FileWriter and PrintWriter as they are part of the java.io package
     */
    public static void successfulLoginActivity(String userName) throws IOException{
        FileWriter fileWriter = new FileWriter(filename,true);
        PrintWriter logInAttempt = new PrintWriter(fileWriter);
        logInAttempt.println("User: " + userName + ", has logged in at " + zonedDateTime);
        logInAttempt.close();
    }
}
