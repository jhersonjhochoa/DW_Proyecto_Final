/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jhonatan
 */
public class ConnectionDB {
    private static ConnectionDB instance = null;
    private static Connection con;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/dw_proyecto_final";
    private static final String USER = "root";
    private static final String PASS = "";
    
    
    public ConnectionDB() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Conexión fallida");
        }
    }
    
    public synchronized static ConnectionDB newInstanceDB() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }
    
    public Connection getCon() {
        return con;
    }
    
    public void close() {
        
    }
}
