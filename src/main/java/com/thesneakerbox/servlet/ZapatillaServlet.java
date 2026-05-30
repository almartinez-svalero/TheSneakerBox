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

        String action =
                request.getParameter("action");

        if ("delete".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            zapatillaDAO.delete(id);

            response.sendRedirect("zapatillas");

            return;
        }

        if ("edit".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            Zapatilla zapatillaEditar =
                    zapatillaDAO.findById(id);

            request.setAttribute(
                    "zapatillaEditar",
                    zapatillaEditar
            );
        }
        if ("detail".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            Zapatilla zapatilla =
                    zapatillaDAO.findById(id);

            request.setAttribute(
                    "zapatilla",
                    zapatilla
            );

            request.getRequestDispatcher(
                    "/views/detalle-zapatilla.jsp"
            ).forward(request, response);

            return;
        }
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

        String idStr =
                request.getParameter("id");

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

        if (idStr != null && !idStr.isEmpty()) {

            zapatilla.setId(
                    Integer.parseInt(idStr)
            );

            zapatillaDAO.update(zapatilla);

        } else {

            zapatillaDAO.save(zapatilla);
        }

        response.sendRedirect("zapatillas");
    }
}