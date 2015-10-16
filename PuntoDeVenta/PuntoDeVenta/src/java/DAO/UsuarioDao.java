/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author kevin
 */
public class UsuarioDao {
    public static void insertar(Usuario U){
        ConnectionPool pool =ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
        CallableStatement cs=null;
        
        
        try {
            
            
        } catch (Exception e) {
        }
        
        
        
    }
    
}
