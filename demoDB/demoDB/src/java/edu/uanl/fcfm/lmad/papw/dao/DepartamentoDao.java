/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uanl.fcfm.lmad.papw.dao;

import edu.uanl.fcfm.lmad.papw.model.Departamento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class DepartamentoDao {
 
    public static List<Departamento> buscarDepartamentos() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            List<Departamento> departamentos = new ArrayList<Departamento>();
            cs = connection.prepareCall("{ call buscar_departamentos() }");
            rs = cs.executeQuery();
            while (rs.next()) {
                Departamento depto = new Departamento(
                        rs.getInt("id"), 
                        rs.getString("nombre"));
                departamentos.add(depto);
            }
            return departamentos;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(cs);
            pool.freeConnection(connection);
        }
    }
}