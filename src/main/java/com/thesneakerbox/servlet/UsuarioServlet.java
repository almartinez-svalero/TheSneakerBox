package com.thesneakerbox.servlet;

import com.thesneakerbox.dao.impl.UsuarioDAOImpl;
import com.thesneakerbox.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAOImpl usuarioDAO =
            new UsuarioDAOImpl();

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

            usuarioDAO.delete(id);

            response.sendRedirect("usuarios");

            return;
        }

        if ("edit".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            Usuario usuarioEditar =
                    usuarioDAO.findById(id);

            request.setAttribute(
                    "usuarioEditar",
                    usuarioEditar
            );
        }

        if ("detail".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            Usuario usuario =
                    usuarioDAO.findById(id);

            request.setAttribute(
                    "usuario",
                    usuario
            );

            request.getRequestDispatcher(
                    "/views/detalle-usuario.jsp"
            ).forward(request, response);

            return;
        }

        String nombreBusqueda =
                request.getParameter("nombre");

        String emailBusqueda =
                request.getParameter("email");

        List<Usuario> usuarios;

        if ((nombreBusqueda != null && !nombreBusqueda.isBlank())
                || (emailBusqueda != null && !emailBusqueda.isBlank())) {

            usuarios =
                    usuarioDAO.buscar(
                            nombreBusqueda != null ? nombreBusqueda : "",
                            emailBusqueda != null ? emailBusqueda : ""
                    );

        } else {

            usuarios =
                    usuarioDAO.findAll();
        }

        request.setAttribute(
                "usuarios",
                usuarios
        );

        request.getRequestDispatcher(
                "/views/usuarios.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String idStr =
                request.getParameter("id");

        Usuario usuario =
                new Usuario();

        usuario.setNombre(
                request.getParameter("nombre")
        );

        usuario.setEmail(
                request.getParameter("email")
        );

        usuario.setPassword(
                request.getParameter("password")
        );

        usuario.setRol(
                request.getParameter("rol")
        );

        usuario.setActivo(
                request.getParameter("activo") != null
        );

        if (idStr != null && !idStr.isEmpty()) {

            usuario.setId(
                    Integer.parseInt(idStr)
            );

            usuarioDAO.update(usuario);

        } else {

            usuarioDAO.save(usuario);
        }

        response.sendRedirect("usuarios");
    }
}