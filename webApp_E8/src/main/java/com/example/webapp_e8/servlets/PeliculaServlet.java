package com.example.webapp_e8.servlets;

import com.example.webapp_e8.beans.Pelicula;
import com.example.webapp_e8.daos.PeliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PeliculaServlet", value = "/PeliculaServlet")
public class PeliculaServlet extends HttpServlet {

    private PeliculaDao peliculaDao = new PeliculaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");

        switch (action) {
            case "listar":
                listarPeliculas(request, response);
                break;
            case "del":
                eliminarPelicula(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/listaPeliculas.jsp");
                break;
        }
    }

    private void listarPeliculas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filtroBusqueda = request.getParameter("textoBuscar");

        ArrayList<Pelicula> listaPeliculas = peliculaDao.listar(filtroBusqueda);
        request.setAttribute("listaPeliculas", listaPeliculas);
        request.setAttribute("busqueda", filtroBusqueda);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaPeliculas.jsp");
        dispatcher.forward(request, response);
    }

    private void eliminarPelicula(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int peliculaId = Integer.parseInt(request.getParameter("id"));
        peliculaDao.eliminar(peliculaId);

        response.sendRedirect(request.getContextPath() + "/PeliculaServlet?action=listar");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Redirigir a doGet para manejar las acciones
    }
}
