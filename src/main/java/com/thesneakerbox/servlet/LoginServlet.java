package com.thesneakerbox.servlet;

import com.thesneakerbox.dao.impl.UsuarioDAOImpl;
import com.thesneakerbox.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UsuarioDAOImpl usuarioDAO =
            new UsuarioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        Usuario usuario =
                usuarioDAO.login(email, password);

        if (usuario != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "usuario",
                    usuario
            );

            response.sendRedirect("marcas");

        } else {

            response.sendRedirect(
                    "login?error=true"
            );
        }
    }
}