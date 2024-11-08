package com.example.webapp_e8.daos;

import com.example.webapp_e8.beans.Genero;
import com.example.webapp_e8.beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;

public class PeliculaDao {

    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String username = "root";
    private String password = "MrKaguya_10";

    public ArrayList<Pelicula> listar(String filtroBusqueda) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre " +
                "FROM Pelicula p " +
                "JOIN Genero g ON p.idGenero = g.idGenero ";

        // Agregar filtro de búsqueda si se proporciona
        if (filtroBusqueda != null && !filtroBusqueda.isEmpty()) {
            sql += "WHERE p.titulo LIKE ? ";
        }

        // Ordenar por rating descendente y luego por Box Office descendente
        sql += "ORDER BY p.rating DESC, p.boxOffice DESC";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setear el filtro de búsqueda si se proporciona
            if (filtroBusqueda != null && !filtroBusqueda.isEmpty()) {
                pstmt.setString(1, "%" + filtroBusqueda + "%");
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("idPelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                    pelicula.setRating(rs.getDouble("rating"));
                    pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                    Genero genero = new Genero();
                    genero.setNombre(rs.getString("nombre"));
                    pelicula.setGenero(genero);

                    peliculas.add(pelicula);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return peliculas;
    }

    public void eliminar(int peliculaId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM Pelicula WHERE idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, peliculaId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pelicula buscarPorId(int idPelicula) {
        Pelicula pelicula = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.idGenero, g.nombre " +
                "FROM Pelicula p " +
                "JOIN Genero g ON p.idGenero = g.idGenero " +
                "WHERE p.idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPelicula);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("idPelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                    pelicula.setRating(rs.getDouble("rating"));
                    pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                    Genero genero = new Genero();
                    genero.setIdGenero(rs.getInt("idGenero"));
                    genero.setNombre(rs.getString("nombre"));
                    pelicula.setGenero(genero);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pelicula;
    }


    public void actualizar(Pelicula pelicula) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE Pelicula SET titulo = ?, director = ?, anoPublicacion = ?, rating = ?, boxOffice = ?, idGenero = ? " +
                "WHERE idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pelicula.getTitulo());
            pstmt.setString(2, pelicula.getDirector());
            pstmt.setInt(3, pelicula.getAnoPublicacion());
            pstmt.setDouble(4, pelicula.getRating());
            pstmt.setDouble(5, pelicula.getBoxOffice());
            pstmt.setInt(6, pelicula.getGenero().getIdGenero());
            pstmt.setInt(7, pelicula.getIdPelicula());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
