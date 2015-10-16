/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uanl.fcfm.lmad.papw.servlet;

import edu.uanl.fcfm.lmad.papw.model.Departamento;
import edu.uanl.fcfm.lmad.papw.model.Empleado;
import edu.uanl.fcfm.lmad.papw.dao.DepartamentoDao;
import edu.uanl.fcfm.lmad.papw.dao.EmpleadoDao;
import java.io.IOException;
import java.util.List;
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
public class ListaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) { 
            String accion = request.getParameter("accion");
            String strId = request.getParameter("id");
            int id = 0;
            if (strId != null && !strId.equals("")) {
                id = Integer.parseInt(strId);
            }

            if ("borrar".equals(accion) && id > 0) {
                EmpleadoDao.borrar(Integer.parseInt(strId));
                request.setAttribute("mensaje", "Registro borrado exitosamente!");

                List<Empleado> empleados = EmpleadoDao.buscarEmpleados();
                request.setAttribute("empleados", empleados);

                RequestDispatcher disp = getServletContext().getRequestDispatcher("/lista_empleados.jsp");
                disp.forward(request, response);

            } else if (("editar".equals(accion) && id > 0) || "agregar".equals(accion)) {
                List<Departamento> departamentos = DepartamentoDao.buscarDepartamentos();
                request.setAttribute("departamentos", departamentos);

                if ("editar".equals(accion)) {
                    Empleado emp = EmpleadoDao.buscarEmpleado(id);
                    request.setAttribute("empleado", emp);
                }

                RequestDispatcher disp = getServletContext().getRequestDispatcher("/detalle_empleados.jsp");
                disp.forward(request, response);

            } else {
                List<Empleado> empleados = EmpleadoDao.buscarEmpleados();
                request.setAttribute("empleados", empleados);

                RequestDispatcher disp = getServletContext().getRequestDispatcher("/lista_empleados.jsp");
                disp.forward(request, response);
            }
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