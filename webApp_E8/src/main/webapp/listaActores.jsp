<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webapp_e8.beans.Actor" %>
<%@ page import="com.example.webapp_e8.beans.Pelicula" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean type="java.util.ArrayList<com.example.webapp_e8.beans.Actor>" scope="request" id="listaActores"/>
<jsp:useBean type="com.example.webapp_e8.beans.Pelicula" scope="request" id="pelicula"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= pelicula.getTitulo() %></title>
    <style>
        th, td,table {
            border-left: 1px solid #000;
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
            border-top: 1px solid #000;
        }
    </style>
</head>
<body>
<h1><%= pelicula.getTitulo() %></h1>

<table>
    <tr>
        <th>idActor</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Año Nacimiento</th>
        <th>Ganador Premio Oscar</th>
    </tr>
    <%
        for (Actor actor : listaActores) {
    %>
    <tr>
        <td><%= actor.getIdActor() %></td>
        <td><%= actor.getNombre() %></td>
        <td><%= actor.getApellido() %></td>
        <td><%= actor.getAnoNacimiento() %></td>
        <td><%= actor.isPremioOscar() %></td>
    </tr>
    <%
        }
    %>
</table>

<form action="<%= request.getContextPath() %>/crearActor.jsp?idPelicula=<%= pelicula.getIdPelicula() %>" method="get">
    <button type="submit">Crear Actor</button>
</form>
</body>
</html>
