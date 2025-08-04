package com.m1gl.soap.DAO;

import com.m1gl.soap.entities.Sector;
import java.sql.*;
import java.util.*;

public class SectorDAO {

    public List<Sector> getAll() throws Exception {
        List<Sector> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM sectors");

        while (rs.next()) {
            list.add(new Sector(rs.getLong("id"), rs.getString("name")));
        }
        return list;
    }

    public void insert(Sector s) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO sectors(name) VALUES (?)");
        ps.setString(1, s.getName());
        ps.executeUpdate();
    }

    public Sector getById(Long id) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM sectors WHERE id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Sector(rs.getLong("id"), rs.getString("name"));
        }
        return null;
    }

    public void update(Sector s) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE sectors SET name=? WHERE id=?");
        ps.setString(1, s.getName());
        ps.setLong(2, s.getId());
        ps.executeUpdate();
    }

    public void delete(Long id) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM sectors WHERE id=?");
        ps.setLong(1, id);
        ps.executeUpdate();
    }

}

