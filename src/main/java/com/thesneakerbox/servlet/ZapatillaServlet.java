package com.thesneakerbox.servlet;

import com.thesneakerbox.dao.impl.MarcaDAOImpl;
import com.thesneakerbox.dao.impl.ZapatillaDAOImpl;
import com.thesneakerbox.model.Marca;
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

    private final MarcaDAOImpl marcaDAO =
            new MarcaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Zapatilla> zapatillas =
                zapatillaDAO.findAll();

        List<Marca> marcas =
                marcaDAO.findAll();

        request.setAttribute(
                "zapatillas",
                zapatillas
        );

        request.setAttribute(
                "marcas",
                marcas
        );

        request.getRequestDispatcher(
                "/views/zapatillas.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String nombre =
                request.getParameter("nombre");

        double precio =
                Double.parseDouble(
                        request.getParameter("precio")
                );

        int stock =
                Integer.parseInt(
                        request.getParameter("stock")
                );

        String color =
                request.getParameter("color");

        int marcaId =
                Integer.parseInt(
                        request.getParameter("marcaId")
                );

        Zapatilla zapatilla = new Zapatilla();

        zapatilla.setNombre(nombre);
        zapatilla.setPrecio(precio);
        zapatilla.setStock(stock);
        zapatilla.setColor(color);
        zapatilla.setMarcaId(marcaId);

        zapatillaDAO.save(zapatilla);

        response.sendRedirect("zapatillas");
    }
}