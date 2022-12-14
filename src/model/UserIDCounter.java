package model;

import DBConnection.DBConnection;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserIDCounter {

    private static int idCounter = 0;

    LinkedList<Integer> idCount = new LinkedList<>();

    public static int getIdCounter() {
        return idCounter;
    }

    public UserIDCounter() {
    }

    public static void setIdCounter(int idCounter) {
        UserIDCounter.idCounter = idCounter;
    }






    public static int checkAndIncrementID(int idCounter) {
        int newId = idCounter;
        for (int i = newId; i <= idCounter; i++) {
            newId++;
        }
        return newId;
    }

    }


