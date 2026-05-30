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

        String action =
                request.getParameter("action");

        if ("delete".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            marcaDAO.delete(id);

            response.sendRedirect("marcas");

            return;
        }

        if ("edit".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            Marca marcaEditar =
                    marcaDAO.findById(id);

            request.setAttribute(
                    "marcaEditar",
                    marcaEditar
            );
        }

        if ("detail".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            Marca marca =
                    marcaDAO.findById(id);

            request.setAttribute(
                    "marca",
                    marca
            );

            request.getRequestDispatcher(
                    "/views/detalle-marca.jsp"
            ).forward(request, response);

            return;
        }

        String nombreBusqueda =
                request.getParameter("nombre");

        String paisBusqueda =
                request.getParameter("pais");

        List<Marca> marcas;

        if ((nombreBusqueda != null && !nombreBusqueda.isBlank())
                || (paisBusqueda != null && !paisBusqueda.isBlank())) {

            marcas =
                    marcaDAO.buscar(
                            nombreBusqueda != null ? nombreBusqueda : "",
                            paisBusqueda != null ? paisBusqueda : ""
                    );

        } else {

            marcas =
                    marcaDAO.findAll();
        }

        request.setAttribute(
                "marcas",
                marcas
        );

        request.getRequestDispatcher(
                "/views/marcas.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String idStr =
                request.getParameter("id");

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

        if (idStr != null && !idStr.isEmpty()) {

            marca.setId(
                    Integer.parseInt(idStr)
            );

            marcaDAO.update(marca);

        } else {

            marcaDAO.save(marca);
        }

        response.sendRedirect("marcas");
    }
}