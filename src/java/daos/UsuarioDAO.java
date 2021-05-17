/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.IUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.RolUsuario;
import models.Seccion;
import models.Usuario;

/**
 *
 * @author Jhonatan
 */
public class UsuarioDAO implements IUsuario {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    RolUsuarioDAO ruDAO = new RolUsuarioDAO();

    @Override
    public boolean login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Usuario u) {
        try {
            String sql = "insert into usuario(rol, nombre, apellido, telefono, documento, password, user) values(?, ?, ?, ?, ?, ?, ?)";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, u.getRol().getId());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getDocumento());
            ps.setString(6, u.getDocumento());
            ps.setString(7, u.getUser());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Usuario u) {
        try {
            String sql = "update usuario set rol=?, nombre = ?, apellido = ?, telefono = ?, documento = ? where id = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, u.getRol().getId());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getDocumento());
            ps.setInt(6, u.getId());
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
            String sql = "delete from usuario where id=?";
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
    public Usuario selectById(int id) {
        try {
            String sql = "select * from usuario where id=?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Usuario u = new Usuario();
            while (rs.next()) {
                u.setId(rs.getInt("id"));
                RolUsuario ru = ruDAO.selectById(rs.getInt("rol"));
                u.setRol(ru);
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setTelefono(rs.getString("telefono"));
                u.setDocumento(rs.getString("documento"));
                u.setUser(rs.getString("user"));
                u.setPassword(rs.getString("password"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> selectAll() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            String sql = "select * from usuario";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Usuario u;
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                RolUsuario ru = ruDAO.selectById(rs.getInt("rol"));
                u.setRol(ru);
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setTelefono(rs.getString("telefono"));
                u.setDocumento(rs.getString("documento"));
                u.setUser(rs.getString("user"));
                u.setPassword(rs.getString("password"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public ArrayList<Usuario> selectAllByRol(RolUsuario ru) {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            ru = ruDAO.selectByCod(ru.getCod());
            String sql = "select * from usuario where rol = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ru.getId());
            rs = ps.executeQuery();
            Usuario u;
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setRol(ru);
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setTelefono(rs.getString("telefono"));
                u.setDocumento(rs.getString("documento"));
                u.setUser(rs.getString("user"));
                u.setPassword(rs.getString("password"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Usuario selectByDocumento(String doc) {
        try {
            String sql = "select * from usuario where documento = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, doc);
            rs = ps.executeQuery();
            Usuario u = new Usuario();
            while (rs.next()) {
                u.setId(rs.getInt("id"));
                RolUsuario ru = ruDAO.selectById(rs.getInt("rol"));
                u.setRol(ru);
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setTelefono(rs.getString("telefono"));
                u.setDocumento(rs.getString("documento"));
                u.setUser(rs.getString("user"));
                u.setPassword(rs.getString("password"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
