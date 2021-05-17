/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.IRolUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.RolUsuario;

/**
 *
 * @author Jhonatan
 */
public class RolUsuarioDAO implements IRolUsuario{
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean insert(RolUsuario ru) {
        try {
            String sql = "insert into rol_usuario(descripcion) values(?)";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ru.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(RolUsuario ru) {
        try {
            String sql = "update rol_usuario set descripcion=?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, ru.getDescripcion());
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
            String sql = "delete from rol_usuario where id=?";
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
    public RolUsuario selectById(int id) {
        try {
            String sql = "select * from rol_usuario where id=?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            RolUsuario ru = new RolUsuario(id);
            while (rs.next()) {
                ru.setDescripcion(rs.getString("descripcion"));
                ru.setCod(rs.getString("cod"));
            }
            return ru;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<RolUsuario> selectAll() {
        ArrayList<RolUsuario> lista = new ArrayList<>();
        try {
            String sql = "select * from rol_usuario";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            RolUsuario ru;
            while (rs.next()) {
                int id = rs.getInt("id");
                String desc = rs.getString("descripcion");
                ru = new RolUsuario(id, desc);
                ru.setCod(rs.getString("cod"));
                lista.add(ru);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public RolUsuario selectByCod(String cod) {
        try {
            String sql = "select * from rol_usuario where cod=?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            RolUsuario ru = new RolUsuario();
            while (rs.next()) {
                ru.setId(rs.getInt("id"));
                ru.setDescripcion(rs.getString("descripcion"));
                ru.setCod(cod);
            }
            return ru;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
