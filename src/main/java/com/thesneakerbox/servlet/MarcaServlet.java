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

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("PROYECTO NUEVO FUNCIONANDO");

        MarcaDAOImpl marcaDAO = new MarcaDAOImpl();

        List<Marca> marcas = marcaDAO.findAll();

        System.out.println("TOTAL MARCAS: " + marcas.size());

        request.setAttribute("marcas", marcas);

        request.getRequestDispatcher("/views/marcas.jsp")
                .forward(request, response);
    }
}