package com.thesneakerbox.servlet;

import com.thesneakerbox.dao.impl.ZapatillaDAOImpl;
import com.thesneakerbox.model.Zapatilla;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/zapatillas")
public class ZapatillaServlet extends HttpServlet {

    private final ZapatillaDAOImpl zapatillaDAO =
            new ZapatillaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Zapatilla> zapatillas =
                zapatillaDAO.findAll();

        request.setAttribute(
                "zapatillas",
                zapatillas
        );

        request.getRequestDispatcher(
                "/views/zapatillas.jsp"
        ).forward(request, response);
    }
}