<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.thesneakerbox.model.Usuario" %>

<%
    Usuario usuario =
            (Usuario) request.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
<head>

    <title>Detalle Usuario</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">

    <h1>Detalle Usuario</h1>

    <div class="card mt-4">

        <div class="card-body text-dark">

            <p><strong>ID:</strong> <%= usuario.getId() %></p>

            <p><strong>Nombre:</strong> <%= usuario.getNombre() %></p>

            <p><strong>Email:</strong> <%= usuario.getEmail() %></p>

            <p><strong>Rol:</strong> <%= usuario.getRol() %></p>

            <p><strong>Activo:</strong>
                <%= usuario.isActivo() ? "Sí" : "No" %>
            </p>

            <a href="usuarios"
               class="btn btn-primary">

                Volver

            </a>

        </div>

    </div>

</div>

</body>
</html>