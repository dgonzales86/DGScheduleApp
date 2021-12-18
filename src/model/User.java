package model;

public class User {
    private int userID;
    private String userName;
    private String userPasswd;

    public User(int userID, String userName, String userPasswd) {
        this.userID = userID;
        this.userName = userName;
        this.userPasswd = userPasswd;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }


}
