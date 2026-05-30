<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.thesneakerbox.model.Usuario" %>

<%
    Usuario usuario =
            (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("login");
        return;
    }

    boolean esAdmin =
            "ADMIN".equals(usuario.getRol());
%>

<!DOCTYPE html>
<html>
<head>

    <title>TheSneakerBox</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container text-center mt-5">

    <h1 class="mb-3">
        👟 TheSneakerBox
    </h1>

    <h4 class="mb-5">
        Bienvenido <%= usuario.getNombre() %>
        (<%= usuario.getRol() %>)
    </h4>

    <div class="d-grid gap-3 col-6 mx-auto">

        <a href="marcas"
           class="btn btn-primary btn-lg">

            Marcas

        </a>

        <a href="zapatillas"
           class="btn btn-success btn-lg">

            Zapatillas

        </a>

        <% if (esAdmin) { %>

        <a href="usuarios"
           class="btn btn-warning btn-lg">

            Usuarios

        </a>

        <% } %>

        <a href="logout"
           class="btn btn-danger btn-lg">

            Cerrar sesión

        </a>

    </div>

</div>

</body>
</html>