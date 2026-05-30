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

<div class="container">

    <div class="text-center mt-5 mb-5">

        <h1 class="display-3 fw-bold">
            👟 TheSneakerBox
        </h1>

        <p class="lead text-light">
            Gestión de marcas, zapatillas y usuarios
        </p>

        <h5 class="mt-4">
            Bienvenido
            <span class="text-warning">
                <%= usuario.getNombre() %>
            </span>

            <% if (esAdmin) { %>

                <span class="badge bg-danger ms-2">
                    ADMIN
                </span>

            <% } else { %>

                <span class="badge bg-primary ms-2">
                    USER
                </span>

            <% } %>
        </h5>

    </div>

    <div class="row justify-content-center g-4">

        <div class="col-md-4">

            <div class="card text-center shadow-lg h-100">

                <div class="card-body">

                    <h1>🏷️</h1>

                    <h3 class="card-title">
                        Marcas
                    </h3>

                    <p class="card-text">
                        Gestiona todas las marcas disponibles.
                    </p>

                    <a href="marcas"
                       class="btn btn-primary">

                        Acceder

                    </a>

                </div>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card text-center shadow-lg h-100">

                <div class="card-body">

                    <h1>👟</h1>

                    <h3 class="card-title">
                        Zapatillas
                    </h3>

                    <p class="card-text">
                        Consulta y administra el catálogo.
                    </p>

                    <a href="zapatillas"
                       class="btn btn-success">

                        Acceder

                    </a>

                </div>

            </div>

        </div>

        <% if (esAdmin) { %>

        <div class="col-md-4">

            <div class="card text-center shadow-lg h-100">

                <div class="card-body">

                    <h1>👤</h1>

                    <h3 class="card-title">
                        Usuarios
                    </h3>

                    <p class="card-text">
                        Administración de usuarios del sistema.
                    </p>

                    <a href="usuarios"
                       class="btn btn-warning">

                        Acceder

                    </a>

                </div>

            </div>

        </div>

        <% } %>

    </div>

    <div class="text-center mt-5">

        <a href="logout"
           class="btn btn-danger btn-lg">

            Cerrar sesión

        </a>

    </div>

</div>

</body>
</html>