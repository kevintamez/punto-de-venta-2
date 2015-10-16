
<%@page import="edu.uanl.fcfm.lmad.papw.model.Usuario"%>
<%@page import="edu.uanl.fcfm.lmad.papw.model.Departamento"%>
<%@page import="java.util.List"%>
<%@page import="edu.uanl.fcfm.lmad.papw.model.Empleado"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::::DETALLE DE EMPLEADO:::::</title>
    </head>
    <body>
        <h1>Detalle del empleado</h1>
        
        <div style="color: blue">
            <% Usuario user = (Usuario) session.getAttribute("user"); %>
            Nombre usuario: <%= user.getEmail() %>  
        </div>
        
        <%
            Empleado emp = (Empleado) request.getAttribute("empleado");
            int id = 0;
            String nombre = "";
            String apePaterno = "";
            String apeMaterno = "";
            byte sexo = 1;
            int deptoId = 0;
            
            if (emp != null) {
                id = emp.getId();
                nombre = emp.getNombre() != null ? emp.getNombre() : "";
                apePaterno = emp.getApePaterno() != null ? emp.getApePaterno() : "";
                apeMaterno = emp.getApeMaterno() != null ? emp.getApeMaterno() : "";
                sexo = emp.getSexo();
                deptoId = emp.getDepartamento() != null ? emp.getDepartamento().getId() : 0;
            }
        %>
        
        <form action="detalle" method="post">
            <table border="1">
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" value="<%= nombre %>"/></td>
                </tr>
                <tr>
                    <td>Ape. Paterno:</td>
                    <td><input type="text" name="apePaterno" value="<%= apePaterno %>"/></td>
                </tr>
                <tr>
                    <td>Ape. Materno:</td>
                    <td><input type="text" name="apeMaterno" value="<%= apeMaterno %>"/></td>
                </tr>
                <tr>
                    <td>Sexo:</td>
                    <td>
                        <% if (sexo == 1) { %>
                            <input type="radio" value="1" name="sexo" checked/>Hombre
                            <input type="radio" value="0" name="sexo"  />Mujer
                        <% } else { %>
                            <input type="radio" value="1" name="sexo" />Hombre
                            <input type="radio" value="0" name="sexo"  checked/>Mujer
                        <%}%>
                        
                        <%-- 
                        O tambien se puede usar un codigo como el de abajo
                        <input type="radio" value="1" name="sexo" <%= sexo == 1 ? "checked" : "" %>/>Hombre
                        <input type="radio" value="0" name="sexo" <%= sexo == 0 ? "checked" : "" %>/>Mujer
                        --%>
                    </td>
                </tr>
                <tr>
                    <td>Departamento:</td>
                    <td>
                        <select name="departamento">                            
                            <%
                                List<Departamento> departamentos = (List<Departamento>) request.getAttribute("departamentos");
                                if (departamentos != null) {
                                    for (Departamento depto : departamentos) {
                            %>
                                        <option value="<%= depto.getId() %>"
                                                <%= emp != null && emp.getDepartamento().getId() == depto.getId() ? "selected" : "" %>
                                                ><%= depto.getNombre() %>
                                        </option>
                            <%
                                    }
                                }
                            %>
                            <!--<option value="1">Mantenimiento</option>
                            <option value="2">Tesorería</option>
                            <option value="3">Dirección</option>
                            <option value="4">Recursos humanos</option>
                            <option value="5">CSI</option>
                            <option value="6">Escolar</option>
                            <option value="7">Compras</option>-->
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="id" value="<%= id %>"/>
                        <input type="submit" value="Guardar"/>
                    </td>
                </tr>
            </table>
        </form>
        
        <br />
        <br />
        <a href="<%= request.getServletContext().getContextPath()%>/lista">Regresar a la lista</a>
    </body>
</html>