package com.example.webapp_e8.servlets;

import com.example.webapp_e8.beans.Genero;
import com.example.webapp_e8.beans.Pelicula;
import com.example.webapp_e8.daos.PeliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "detallesServlet", value = "/detallesServlet")
public class detallesServlet extends HttpServlet {

    private PeliculaDao peliculaDao = new PeliculaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int idPelicula;

        try {
            idPelicula = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/PeliculaServlet?action=listar");
            return;
        }

        Pelicula pelicula = peliculaDao.buscarPorId(idPelicula);

        if (pelicula != null) {
            request.setAttribute("pelicula", pelicula);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewPelicula.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/PeliculaServlet?action=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
            String titulo = request.getParameter("titulo");
            String director = request.getParameter("director");
            int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
            double rating = Double.parseDouble(request.getParameter("rating"));
            double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));
            int idGenero = Integer.parseInt(request.getParameter("idGenero"));
            String nombreGenero = request.getParameter("nombreGenero");

            Pelicula pelicula = new Pelicula();
            pelicula.setIdPelicula(idPelicula);
            pelicula.setTitulo(titulo);
            pelicula.setDirector(director);
            pelicula.setAnoPublicacion(anoPublicacion);
            pelicula.setRating(rating);
            pelicula.setBoxOffice(boxOffice);

            // Asignar género si no es nulo
            Genero genero = new Genero();
            genero.setIdGenero(idGenero);
            genero.setNombre(nombreGenero);
            pelicula.setGenero(genero);

            peliculaDao.actualizar(pelicula);

            response.sendRedirect(request.getContextPath() + "/PeliculaServlet?action=listar");


    }
}
