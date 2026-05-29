package com.thesneakerbox.servlet;

import com.thesneakerbox.dao.impl.MarcaDAOImpl;
import com.thesneakerbox.model.Marca;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/marcas")
public class MarcaServlet extends HttpServlet {

    private final MarcaDAOImpl marcaDAO =
            new MarcaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Marca> marcas = marcaDAO.findAll();

        request.setAttribute("marcas", marcas);

        request.getRequestDispatcher("/views/marcas.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String nombre =
                request.getParameter("nombre");

        String pais =
                request.getParameter("pais");

        boolean premium =
                request.getParameter("premium") != null;

        String logo =
                request.getParameter("logo");

        Marca marca = new Marca();

        marca.setNombre(nombre);
        marca.setPais(pais);
        marca.setPremium(premium);
        marca.setLogo(logo);

        marcaDAO.save(marca);

        response.sendRedirect("marcas");
    }
}