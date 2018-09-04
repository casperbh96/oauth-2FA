package com.mycompany;

import org.apache.commons.mail.EmailException;
import org.jooby.Jooby;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends Jooby {
    private String username;
    private String password;
    String strSql = "SELECT username, password, emailcode FROM users";
    String strUsername;
    String strPassword;
    EmailAuth email;

    /**
     * This method takes the username and password, and see if it is already in the database.
     * If they are in the database the method will return true otherwise it will return false.
     *
     * @param db Takes the input username and password checks if it is in the Database.
     * @return It will return if the username and password is correct or not.
     * @throws SQLException
     * @throws EmailException
     * @version 1.0
     * @author Casper Bøgeskov Hansen
     * @author Danial Virk
     * @author Morten Allen Jensen
     */
    public boolean isAuthenticated(DataSource db) throws SQLException, EmailException {
        Statement cs = db.getConnection().createStatement();
        ResultSet rs = cs.executeQuery(strSql);

        while (rs.next()) {
            strUsername = rs.getString("username");
            strPassword = rs.getString("password");
            email = new EmailAuth();

            if (getUsername().equals(strUsername) && getPassword().equals(strPassword)) {
                email.SendMail(strUsername);

                cs.executeUpdate("UPDATE users SET emailcode=" + email.emailAuth + " WHERE username='" + strUsername + "'");

                return true;
            }
        }
        System.out.println("User not authenticated");
        return false;
    }

    public static boolean emailAuthenticated(DataSource db, String strEmail, String strEmailPass) throws SQLException {
        Statement cs = db.getConnection().createStatement();
        ResultSet rs = cs.executeQuery("SELECT username, emailcode FROM users");

        while (rs.next()) {
            String strUsername = rs.getString("username");
            String strEmailcode = rs.getString("emailcode");

            if (strEmail.equals(strUsername) && strEmailPass.equals(strEmailcode)) {
                return true;
            }
        }
        System.out.println("Something went wrong");
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
