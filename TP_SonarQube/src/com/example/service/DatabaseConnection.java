package com.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger LOGGER = Logger.getLogger( DatabaseConnection.class.getName() );


    private String user;
    private String pass;
    private String jdcDriver;
    private String dbUrl;

    public DatabaseConnection(String user, String pass, String jdcDriver, String dbUrl) {
        this.user = user;
        this.pass = pass;
        this.jdcDriver = jdcDriver;
        this.dbUrl = dbUrl;
    }

    public  Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, user, pass);
            Class.forName(jdcDriver);
        } catch (SQLException|ClassNotFoundException e) {
            LOGGER.log(Level.FINE, e.getMessage());
        }
        return conn;
    }

    public  void disconnect(Connection conn ) {
        try {
            conn.close();
        } catch (SQLException e) {
            LOGGER.log(Level.FINE, e.getMessage());
        }
    }

    public void createDb(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE   City " +
                    "(idCity INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " touristNumber INTEGER, " +
                    " description VARCHAR(255), " +
                    " PRIMARY KEY ( idCity ))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch(SQLException se) {
            LOGGER.log(Level.FINE, se.getMessage());
        } catch(Exception e) {
            LOGGER.log(Level.FINE, e.getMessage());
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                LOGGER.log(Level.FINE, se2.getMessage());
            }
        }
    }

}
