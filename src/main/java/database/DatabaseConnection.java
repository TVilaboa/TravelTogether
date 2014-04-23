package database;


import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Trav
 * Date: 26/03/14
 * Time: 20:35
 */
public class DatabaseConnection {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void establishConnection(String server, String user, String password) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection(server, user, password);
        } catch (Exception e) {
            System.out.println("Connection failed");
            JOptionPane.showMessageDialog(null,"Connection failed");
            e.printStackTrace();
        }
    }
}
