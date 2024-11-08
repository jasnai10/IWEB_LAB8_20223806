<%@ page import="com.example.webapp_e8.beans.Pelicula" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean type="com.example.webapp_e8.beans.Pelicula" scope="request" id="pelicula"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Editar película</title>
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
<h1>Película Numero <%= pelicula.getIdPelicula() %></h1>

<form action="<%= request.getContextPath() %>/detallesServlet" method="post">

    <!-- Campo oculto para el ID de la película -->
    <input type="hidden" name="idPelicula" value="<%= pelicula.getIdPelicula() %>">

    <button type="submit">Guardar Película</button>

    <table>
        <tr>
            <td><label for="titulo">Título</label></td>
            <td><input type="text" name="titulo" id="titulo" value="<%= pelicula.getTitulo() %>"></td>
        </tr>
        <tr>
            <td><label for="director">Director</label></td>
            <td><input type="text" name="director" id="director" value="<%= pelicula.getDirector() %>"></td>
        </tr>
        <tr>
            <td><label for="anoPublicacion">Año Publicación</label></td>
            <td><input type="number" name="anoPublicacion" id="anoPublicacion" value="<%= pelicula.getAnoPublicacion() %>"></td>
        </tr>
        <tr>
            <td><label for="rating">Rating</label></td>
            <td><input type="number" step="0.1" name="rating" id="rating" value="<%= pelicula.getRating() %>" required/></td>
        </tr>
        <tr>
            <td><label for="boxOffice">Box Office</label></td>
            <td><input type="number" step="0.01" name="boxOffice" id="boxOffice" value="<%= pelicula.getBoxOffice() %>" required/></td>
        </tr>
        <input type="hidden" name="idGenero" id="idGenero" value="<%= pelicula.getGenero().getIdGenero()%>">
        <input type="hidden" name="idPelicula" id="idPelicula" value="<%= pelicula.getIdPelicula()%>">
        <tr>
            <td>Actores</td>
            <td><a href="<%=request.getContextPath()%>/ActorServlet?id=<%=pelicula.getIdPelicula()%>">Ver actores</a></td>
        </tr>

    </table>
</form>
</body>
</html>
