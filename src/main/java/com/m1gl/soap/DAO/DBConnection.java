package com.m1gl.soap.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = DBConnection.class.getClassLoader()
            .getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
