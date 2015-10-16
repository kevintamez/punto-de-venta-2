/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author kevin
 */
public class ConnectionPool {
     private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) 
                    ic.lookup("java:/comp/env/jdbc/PuntoDeVenta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
