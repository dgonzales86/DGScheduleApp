package model;

/**
 * User Class
 * shell used to create and retrieve User objects
 */
public class User {
    /**
     * user id of User Object
     */
    private int userID;
    /**
     * username of User object
     */
    private String userName;
    /**
     * user password of User object
     */
    private String userPasswd;

    /**
     * Constructor for new User Object
     * @param userID - adds a user id to a new User object
     * @param userName - adds a username to a new User object
     * @param userPasswd - adds a password to a new User object
     */
    public User(int userID, String userName, String userPasswd) {
        this.userID = userID;
        this.userName = userName;
        this.userPasswd = userPasswd;
    }

    /**
     * Overrides default toString() method
     * @return - returns string value of userName
     */
    @Override
    public String toString() {
        return (userName);
    }

    /**
     * Getter for User userID
     * @return - returns int userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for User userID
     * @param userID - sets this.userID to userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for User userName
     * @return - returns String userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for User userName
     * @param userName - sets this.userName to userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for User userPasswd
     * @return - returns String userPasswd
     */
    public String getUserPasswd() {
        return userPasswd;
    }

    /**
     * Setter for User userPasswd
     * @param userPasswd sets this.userPasswd to userPasswd
     */
    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }
}
