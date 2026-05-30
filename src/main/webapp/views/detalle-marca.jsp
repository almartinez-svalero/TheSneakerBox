<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.thesneakerbox.model.Marca" %>
<%@ page import="com.thesneakerbox.model.Usuario" %>

<%
    Marca marca =
            (Marca) request.getAttribute("marca");

    Usuario usuario =
            (Usuario) session.getAttribute("usuario");

    boolean esAdmin =
            usuario != null &&
            "ADMIN".equals(usuario.getRol());
%>

<!DOCTYPE html>
<html>
<head>

    <title>Detalle Marca</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">

    <h1 class="display-4 fw-bold text-center mb-5">
        🏷️ Detalle de Marca
    </h1>

    <div class="row justify-content-center">

        <div class="col-md-8">

            <div class="card shadow-lg border-0 rounded-4">

                <div class="text-center mt-4">

                    <img src="<%= marca.getLogo() %>"
                         width="180"
                         height="180"
                         class="rounded-circle border border-3 border-secondary"
                         style="object-fit: contain; background-color: white;"
                         onerror="this.src='https://via.placeholder.com/180';">

                </div>

                <div class="card-body text-dark text-center">

                    <h2 class="card-title mb-4">
                        <%= marca.getNombre() %>
                    </h2>

                    <hr>

                    <p class="fs-5">
                        🌍 <strong>País:</strong>
                        <%= marca.getPais() %>
                    </p>

                    <p>

                        <% if (marca.isPremium()) { %>

                            <span class="badge bg-warning text-dark fs-6">
                                ⭐ Premium
                            </span>

                        <% } else { %>

                            <span class="badge bg-secondary fs-6">
                                Estándar
                            </span>

                        <% } %>

                    </p>

                    <div class="mt-4">

                        <a href="marcas"
                           class="btn btn-primary">

                            Volver

                        </a>

                        <% if (esAdmin) { %>

                        <a href="marcas?action=edit&id=<%= marca.getId() %>"
                           class="btn btn-warning">

                            Editar

                        </a>

                        <% } %>

                    </div>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>