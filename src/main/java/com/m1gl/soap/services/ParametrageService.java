package com.m1gl.soap.services;

import com.m1gl.soap.DAO.DBInitializer;
import com.m1gl.soap.DAO.SectorDAO;
import com.m1gl.soap.entities.Sector;
import com.m1gl.soap.DAO.ClasseDAO;
import com.m1gl.soap.entities.Classe;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ParametrageService {

    public ParametrageService() {
        DBInitializer.initialize();
    }

    @WebMethod
    public List<Sector> getSectors() {
        try {
            return new SectorDAO().getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public void addSector(String name) {
        try {
            new SectorDAO().insert(new Sector(null, name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public void addClasse(String className, String description, Long sectorId) {
        try {
            Sector sector = new SectorDAO().getById(sectorId);
            if (sector != null) {
                new ClasseDAO().insert(new Classe(null, className, description, sector));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public List<Classe> getClasses() {
        try {
            return new ClasseDAO().getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public void updateClasse(Long id, String className, String description, Long sectorId) {
        try {
            Sector sector = new SectorDAO().getById(sectorId);
            if (sector != null) {
                new ClasseDAO().update(new Classe(id, className, description, sector));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public void deleteClasse(Long id) {
        try {
            new ClasseDAO().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public Sector getSectorById(Long id) {
        try {
            return new SectorDAO().getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public void updateSector(Long id, String name) {
        try {
            SectorDAO dao = new SectorDAO();
            Sector sector = dao.getById(id);
            if (sector != null) {
                sector.setName(name);
                dao.update(sector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public void deleteSector(Long id) {
        try {
            new SectorDAO().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
