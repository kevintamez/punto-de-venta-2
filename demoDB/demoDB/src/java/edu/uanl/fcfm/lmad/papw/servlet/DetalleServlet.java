/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uanl.fcfm.lmad.papw.servlet;

import edu.uanl.fcfm.lmad.papw.model.Departamento;
import edu.uanl.fcfm.lmad.papw.model.Empleado;
import edu.uanl.fcfm.lmad.papw.dao.EmpleadoDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class DetalleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) { 
            String strId = request.getParameter("id");
            int id = 0;
            if (strId != null && !strId.equals("")) {
                id = Integer.parseInt(strId);
            }

            String nombre = request.getParameter("nombre");
            String apePaterno = request.getParameter("apePaterno");
            String apeMaterno = request.getParameter("apeMaterno");

            String strSexo = request.getParameter("sexo");
            byte sexo = (byte) 0;
            if (strSexo != null && !strSexo.equals("")) {
                sexo = Byte.parseByte(strSexo);
            }

            String strDepartamento = request.getParameter("departamento");
            int departamentoId = 0;
            if (strDepartamento != null && !strDepartamento.equals("")) {
                departamentoId = Integer.parseInt(strDepartamento);
            }

            Empleado e = new Empleado(nombre, apePaterno, apeMaterno, sexo);
            e.setId(id);
            Departamento depto = new Departamento(departamentoId);
            e.setDepartamento(depto);

            if (id > 0) {
                EmpleadoDao.actualizar(e);
            } else {
                EmpleadoDao.insertar(e);
            }

            RequestDispatcher disp = getServletContext().getRequestDispatcher("/lista");
            disp.forward(request, response);
        } else {
            RequestDispatcher disp = getServletContext().
                    getRequestDispatcher("/index.jsp");
                disp.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}