package com.example.webapp_e8.servlets;

import com.example.webapp_e8.beans.Actor;
import com.example.webapp_e8.daos.ActorDao;
import com.example.webapp_e8.beans.Pelicula;
import com.example.webapp_e8.daos.PeliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ActorServlet", value = "/ActorServlet")
public class ActorServlet extends HttpServlet {

    private ActorDao actorDao = new ActorDao();
    private PeliculaDao peliculaDao = new PeliculaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPeliculaStr = request.getParameter("id");
        int idPelicula;

        try {
            idPelicula = Integer.parseInt(idPeliculaStr);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/PeliculaServlet?action=listar");
            return;
        }

        Pelicula pelicula = peliculaDao.buscarPorId(idPelicula);
        List<Actor> actores = actorDao.obtenerActoresPorPelicula(idPelicula);

        request.setAttribute("pelicula", pelicula);
        request.setAttribute("actores", actores);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaActores.jsp");
        dispatcher.forward(request, response);
    }
}
