package com.m1gl.soap.DAO;

import com.m1gl.soap.entities.Classe;
import com.m1gl.soap.entities.Sector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasseDAO {

    public void insert(Classe c) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO classes(class_name, description, sector_id) VALUES (?, ?, ?)");
        ps.setString(1, c.getClassName());
        ps.setString(2, c.getDescription());
        ps.setLong(3, c.getSector().getId());
        ps.executeUpdate();
    }

    public List<Classe> getAll() throws Exception {
        List<Classe> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT c.*, s.id AS sid, s.name AS sname FROM classes c " +
            "JOIN sectors s ON c.sector_id = s.id");
        while (rs.next()) {
            Sector sector = new Sector(rs.getLong("sid"), rs.getString("sname"));
            Classe c = new Classe(
                rs.getLong("id"),
                rs.getString("class_name"),
                rs.getString("description"),
                sector
            );
            list.add(c);
        }
        return list;
    }

    public Classe getById(Long id) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "SELECT c.*, s.id AS sid, s.name AS sname FROM classes c " +
                "JOIN sectors s ON c.sector_id = s.id WHERE c.id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Sector sector = new Sector(rs.getLong("sid"), rs.getString("sname"));
            return new Classe(
                rs.getLong("id"),
                rs.getString("class_name"),
                rs.getString("description"),
                sector
            );
        }
        return null;
    }

    public void update(Classe c) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE classes SET class_name=?, description=?, sector_id=? WHERE id=?");
        ps.setString(1, c.getClassName());
        ps.setString(2, c.getDescription());
        ps.setLong(3, c.getSector().getId());
        ps.setLong(4, c.getId());
        ps.executeUpdate();
    }

    public void delete(Long id) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM classes WHERE id=?");
        ps.setLong(1, id);
        ps.executeUpdate();
    }

    public List<Classe> getBySectorId(Long sectorId) throws Exception {
        List<Classe> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM classes WHERE sector_id = ?");
        ps.setLong(1, sectorId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Classe(
                rs.getLong("id"),
                rs.getString("class_name"),
                rs.getString("description"),
                new Sector(sectorId, null)
            ));
        }
        return list;
    }
}
