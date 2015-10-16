<%-- 
    Document   : index
    Created on : 09-oct-2012, 22:23:53
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::::LOGIN:::::</title>
    </head>
    <body>
        <h1>Bienvenido!!!</h1>
        <form action="index" method="post">
            <table border="1">
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" value=""/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value=""/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Entrar"/>
                    </td>
                </tr>
            </table>
        </form>
        
        <% if (request.getAttribute("mensaje") != null) { %>
            <div style="color: red"><%= request.getAttribute("mensaje") %> </div>
        <% } %>
    </body>
</html>
