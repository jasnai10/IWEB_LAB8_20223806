<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webapp_e8.beans.Pelicula" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaPeliculas" scope="request" type="java.util.ArrayList<Pelicula>" />

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Películas</title>
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
<div class="container">
    <div class="clearfix mt-3">
        <h1 class="float-start link-dark">Lista de Películas</h1>
    </div>

    <form method="get" action="<%= request.getContextPath() %>/PeliculaServlet?action=s">
        <div class="form-floating mt-3">
            <input type="text" class="form-control" id="floatingInput"
                   placeholder="Buscar película" name="textoBuscar" value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
            <button for="floatingInput" type="submit">Buscar</button>
        </div>
    </form>
    <br>
    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>Título</th>
            <th>Director</th>
            <th>Año Publicacion</th>
            <th>Rating</th>
            <th>BoxOffice</th>
            <th>Género</th>
            <th>Actores</th>
            <th>Accionable</th>



        <% if (listaPeliculas == null || listaPeliculas.isEmpty()) { %>
        <tr>
            <td colspan="8" class="text-center">No se encontraron películas</td>
        </tr>
        <% } else { %>
        <% for (Pelicula pelicula : listaPeliculas) { %>
        <tr>
            <td><a href="<%=request.getContextPath()%>/detallesServlet?id=<%=pelicula.getIdPelicula()%>"><%=pelicula.getTitulo()%></a></td>
            <td><%= pelicula.getDirector() %></td>
            <td><%= pelicula.getAnoPublicacion() %></td>
            <td><%= pelicula.getRating() %>/10</td>
            <td>$ <%= pelicula.getBoxOffice() %></td>
            <td><%= pelicula.getGenero().getNombre() %></td>
            <td><a href="<%=request.getContextPath()%>/ActorServlet?id=<%=pelicula.getIdPelicula()%>">Ver actores</a></td>
            <td><a onclick="return confirm('¿Está seguro de eliminar esta película?')" class="btn btn-danger" href="<%= request.getContextPath() %>/PeliculaServlet?action=del&id=<%= pelicula.getIdPelicula() %>">Eliminar</a></td>
        </tr>
        <% } %>
        <% } %>

    </table>
</div>
</body>
</html>
