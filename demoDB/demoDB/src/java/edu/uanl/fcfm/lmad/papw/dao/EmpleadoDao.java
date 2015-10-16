/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uanl.fcfm.lmad.papw.dao;

import edu.uanl.fcfm.lmad.papw.model.Departamento;
import edu.uanl.fcfm.lmad.papw.model.Empleado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class EmpleadoDao {
 
    public static List<Empleado> buscarEmpleados() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            List<Empleado> empleados = new ArrayList();
            cs = connection.prepareCall("{ call buscar_empleados() }");
            rs = cs.executeQuery();
            while (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getInt("id"), 
                        rs.getString("nombre"), 
                        rs.getString("ape_paterno"), 
                        rs.getString("ape_materno"),
                        rs.getByte("sexo"));
                
                Departamento depto = new Departamento(
                        rs.getInt("departamento_id"), 
                        rs.getString("depto"));
                emp.setDepartamento(depto);
                empleados.add(emp);
            }
            return empleados;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(cs);
            pool.freeConnection(connection);
        }
    }
    
    public static void insertar(Empleado e) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{ call insertar_empleado(?, ?, ?, ?, ?) }");
            cs.setString(1, e.getNombre());
            cs.setString(2, e.getApePaterno());
            cs.setString(3, e.getApeMaterno());
            cs.setByte(4, e.getSexo());
            cs.setInt(5, e.getDepartamento().getId());
            cs.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        } finally {
            DBUtil.closeStatement(cs);
            pool.freeConnection(connection);
        }
    }
    
    public static void borrar(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{ call borrar_empleado(?, ?) }");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.BIT);
            cs.execute();
            byte res = cs.getByte(2);
            System.out.println("[Borrar] Resultado=" + res);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.closeStatement(cs);
            pool.freeConnection(connection);
        }
    }
    
    public static Empleado buscarEmpleado(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = connection.prepareCall("{ call buscar_empleado(?) }");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getInt("id"), 
                        rs.getString("nombre"), 
                        rs.getString("ape_paterno"), 
                        rs.getString("ape_materno"),
                        rs.getByte("sexo"));
                Departamento d = new Departamento(rs.getInt("departamento_id"));
                emp.setDepartamento(d);
                return emp;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(cs);
            pool.freeConnection(connection);
        }
    }
    
    public static void actualizar(Empleado e) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{ call actualizar_empleado(?, ?, ?, ?, ?, ?) }");
            cs.setString(1, e.getNombre());
            cs.setString(2, e.getApePaterno());
            cs.setString(3, e.getApeMaterno());
            cs.setByte(4, e.getSexo());
            cs.setInt(5, e.getDepartamento().getId());
            cs.setInt(6, e.getId());
            cs.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        } finally {
            DBUtil.closeStatement(cs);
            pool.freeConnection(connection);
        }
    }
}