package com.desitsa.admin;

import java.sql.*;

public class DBAccess {

    public static DBAccess instance;

    private String url = "jdbc:mysql://localhost/desitadmin?autoReconnect=true&useSSL=false";
    private String user = "root";
    private String pass = "253614";

    private Connection connection;

    /**
     * Obtiene la instancia de la clase que accede a la base de datos
     */
    public static DBAccess getInstance() {
        if (instance == null) instance = new DBAccess();
        return instance;
    }

    private DBAccess() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Connection open() throws SQLException {
        connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

    /**
     * Indica al motor que se inicia una transacción
     */
    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    /**
     * Indica al motor que se termina una transacción
     */
    public void endTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
}
