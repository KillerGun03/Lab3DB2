package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String user = "root";
    private static final String pass = "admin";

    public static Connection getConnection() {

        Connection conexion = null;

        try {
            conexion = (Connection) DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }

        return conexion;
    }
}
