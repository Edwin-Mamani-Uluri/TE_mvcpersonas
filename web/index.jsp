<%-- 
    Document   : index
    Created on : 04-oct-2020, 15:20:58
    Author     : kr1pt0n
--%>

<%@page import="com.emergentes.modelo.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de personas</title>
    </head>
    <body>
        <%
            if(session.getAttribute("lista_estudiantes")==null){
                ArrayList<Persona> lista_aux = new ArrayList<Persona>();
                session.setAttribute("lista_estudiantes", lista_aux);
            }
            ArrayList<Persona> lista = (ArrayList<Persona>)session.getAttribute("lista_estudiantes");
        %>
        <h1>Lista de personas</h1>
        <a href="Controlador?op=1">Nuevo</a>
        <table>
            <tr>
                <th>id</th>
                <th>nombres</th>
                <th>apellidos</th>
                <th>edad</th>
                <th>accion</th>
                <th>accion</th>
            </tr>
            
           
                <%
                    if(lista != null){
                        for(Persona item:lista){
                %>
                 <tr>
                <td>ID: <%= item.getId()%></td>
                <td>Nombres: <%= item.getNombres()%></td>
                <td>Apellidos: <%= item.getApellidos()%></td>
                <td>Edad: <%= item.getEdad()%></td>
                <td><a href="Controlador?op=2&id=<%=item.getId()%>">Modificar</a></td>
                <td><a href="Controlador?op=3&id=<%=item.getId()%>"
                       onclick="return confirm('esta seguro de eliminar');">Eliminar</a></td>
                </tr>
                       <%
                        }
                    }
                %>
            
            
        </table>
    </body>
</html>
