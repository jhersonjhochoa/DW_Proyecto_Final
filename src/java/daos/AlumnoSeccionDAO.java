/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.IAlumnoSeccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Seccion;
import models.Usuario;
import models_relation.AlumnoSeccion;

/**
 *
 * @author Jhonatan
 */
public class AlumnoSeccionDAO implements IAlumnoSeccion {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    SeccionDAO sDAO = new SeccionDAO();
    UsuarioDAO uDAO = new UsuarioDAO();

    @Override
    public boolean insert(AlumnoSeccion as) {
        try {
            String sql = "insert into alumno_seccion(alumno, seccion, promedio, orden_mertio) values(?, ?, ?, ?)";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, as.getAlumno().getId());
            ps.setInt(2, as.getSeccion().getId());
            ps.setDouble(3, as.getPromedio());
            ps.setInt(4, as.getOrden_merito());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(AlumnoSeccion as) {
        try {
            String sql = "update alumno_seccion set alumno = ?, seccion = ?, promedio = ?, orden_mertio = ? where id = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, as.getAlumno().getId());
            ps.setInt(2, as.getSeccion().getId());
            ps.setDouble(3, as.getPromedio());
            ps.setInt(3, as.getOrden_merito());
            ps.setInt(5, as.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            String sql = "delete from alumno_seccion where id=?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public AlumnoSeccion selectById(int id) {
        try {
            String sql = "select * from alumno_seccion where id = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            AlumnoSeccion as = new AlumnoSeccion();
            while (rs.next()) {
                as.setId(rs.getInt("id"));
                Usuario alumno = uDAO.selectById(rs.getInt("alumno"));
                as.setAlumno(alumno);
                Seccion s = sDAO.selectById(rs.getInt("seccion"));
                as.setSeccion(s);
                as.setAlumno(alumno);
                as.setPromedio(rs.getDouble("promedio"));
                as.setOrden_merito(rs.getInt("orden_mertio"));
            }
            return as;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<AlumnoSeccion> selectAll() {
        ArrayList<AlumnoSeccion> lista = new ArrayList<>();
        try {
            String sql = "select * from alumno_seccion";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            AlumnoSeccion as;
            while (rs.next()) {
                as = new AlumnoSeccion();
                as.setId(rs.getInt("id"));
                Usuario alumno = uDAO.selectById(rs.getInt("alumno"));
                as.setAlumno(alumno);
                Seccion s = sDAO.selectById(rs.getInt("seccion"));
                as.setSeccion(s);
                as.setAlumno(alumno);
                as.setPromedio(rs.getDouble("promedio"));
                as.setOrden_merito(rs.getInt("orden_mertio"));
                lista.add(as);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    @Override
    public ArrayList<AlumnoSeccion> selectBySeccion(int id_sec) {
        ArrayList<AlumnoSeccion> lista = new ArrayList<>();
        try {
            String sql = "select * from alumno_seccion where seccion = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_sec);
            rs = ps.executeQuery();
            Seccion s = sDAO.selectById(id_sec);
            AlumnoSeccion as;
            while (rs.next()) {
                as = new AlumnoSeccion();
                as.setId(rs.getInt("id"));
                Usuario alumno = uDAO.selectById(rs.getInt("alumno"));
                as.setAlumno(alumno);
                as.setSeccion(s);
                as.setAlumno(alumno);
                as.setPromedio(rs.getDouble("promedio"));
                as.setOrden_merito(rs.getInt("orden_mertio"));
                lista.add(as);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public ArrayList<AlumnoSeccion> selectByAlumno(int id_alumno) {
    ArrayList<AlumnoSeccion> lista = new ArrayList<>();
        try {
            String sql = "select * from alumno_seccion where alumno = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            rs = ps.executeQuery();
            Seccion s = sDAO.selectById(id_alumno);
            AlumnoSeccion as;
            while (rs.next()) {
                as = new AlumnoSeccion();
                as.setId(rs.getInt("id"));
                Usuario alumno = uDAO.selectById(rs.getInt("alumno"));
                as.setAlumno(alumno);
                as.setSeccion(s);
                as.setAlumno(alumno);
                as.setPromedio(rs.getDouble("promedio"));
                as.setOrden_merito(rs.getInt("orden_mertio"));
                lista.add(as);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
