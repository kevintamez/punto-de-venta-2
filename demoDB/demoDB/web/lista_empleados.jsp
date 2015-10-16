
<%@page import="edu.uanl.fcfm.lmad.papw.model.Usuario"%>
<%@page import="edu.uanl.fcfm.lmad.papw.model.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::::LISTA DE EMPLEADOS:::::</title>
    </head>
    <body>
        <h1>Lista de empleados</h1>
        
        <div style="color: blue">
            <% Usuario user = (Usuario) session.getAttribute("user"); %>
            Nombre usuario: <%= user.getEmail() %>  
        </div>
        
        <a href="<%= request.getServletContext().getContextPath()%>/lista?accion=agregar">Agregar empleado</a>
        <br />
        <br />
        <table style="width: 600px;" border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Ape. Paterno</th>
                <th>Ape. Materno</th>
                <th>Departamento</th>
                <th>Borrar</th>
                <th>Editar</th>
            </tr>
            <%
                List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
                if (empleados != null) {
                    for (Empleado emp : empleados) {
            %>            
                        <tr>
                            <td><%= emp.getId() %></td>
                            <td><%= emp.getNombre() %></td>
                            <td><%= emp.getApePaterno() %></td>
                            <td><%= emp.getApeMaterno() %></td>
                            <td><%= emp.getDepartamento().getNombre() %></td>
                            <td>
                                <a href="<%= request.getServletContext().getContextPath()%>/lista?accion=borrar&id=<%= emp.getId() %>">
                                    Borrar
                                </a>
                            </td>
                            <td>
                                <a href="<%= request.getServletContext().getContextPath()%>/lista?accion=editar&id=<%= emp.getId() %>">
                                    Editar
                                </a>
                            </td>
                        </tr>
            <%      }
                }
            %>
        </table>
        
        <% 
            String mensaje = (String) request.getAttribute("mensaje"); 
            if (mensaje != null) {
        %>
            <br />
            <br />
            <div style="color: blue; width: 100%;"><%= request.getAttribute("mensaje") %></div>
        <% } %>
        <br />
        <br />
        <a href="<%= request.getServletContext().getContextPath()%>/index?accion=borrar">Cerrar sesión</a>
    </body>
</html>