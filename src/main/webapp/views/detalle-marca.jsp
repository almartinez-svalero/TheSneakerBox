<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.thesneakerbox.model.Marca" %>

<%
    Marca marca =
            (Marca) request.getAttribute("marca");
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

    <h1>Detalle de Marca</h1>

    <div class="card mt-4">

        <div class="card-body text-dark">

            <p><strong>ID:</strong> <%= marca.getId() %></p>

            <p><strong>Nombre:</strong> <%= marca.getNombre() %></p>

            <p><strong>País:</strong> <%= marca.getPais() %></p>

            <p><strong>Premium:</strong>
                <%= marca.isPremium() ? "Sí" : "No" %>
            </p>

            <p><strong>Logo:</strong>
                <%= marca.getLogo() %>
            </p>

            <a href="marcas"
               class="btn btn-primary">

                Volver

            </a>

        </div>

    </div>

</div>

</body>
</html>