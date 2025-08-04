package com.m1gl.soap.DAO;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {
    public static void initialize() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sectors (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS classes (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "class_name VARCHAR(255)," +
                "description TEXT," +
                "sector_id BIGINT," +
                "FOREIGN KEY (sector_id) REFERENCES sectors(id))");

            System.out.println("Tables créées ou déjà existantes.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
