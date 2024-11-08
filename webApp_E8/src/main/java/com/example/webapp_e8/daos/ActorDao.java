package com.example.webapp_e8.daos;

import com.example.webapp_e8.beans.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDao {

    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String username = "root";
    private String password = "MrKaguya_10";

    public List<Actor> obtenerActoresPorPelicula(int idPelicula) {
        List<Actor> actores = new ArrayList<>();

        String sql = "SELECT a.idActor, a.nombre, a.apellido, a.anoNacimiento, a.ganadorOscar " +
                "FROM Actor a " +
                "JOIN Protagonistas p ON a.idActor = p.idActor " +
                "WHERE p.idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPelicula);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Actor actor = new Actor();
                    actor.setIdActor(rs.getInt("idActor"));
                    actor.setNombre(rs.getString("nombre"));
                    actor.setApellido(rs.getString("apellido"));
                    actor.setAnoNacimiento(rs.getInt("anoNacimiento"));
                    actor.setPremioOscar(rs.getBoolean("ganadorOscar"));
                    actores.add(actor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return actores;
    }
}
