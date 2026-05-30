<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.thesneakerbox.model.Zapatilla" %>

<%
    Zapatilla zapatilla =
            (Zapatilla) request.getAttribute("zapatilla");
%>

<!DOCTYPE html>
<html>
<head>

    <title>Detalle Zapatilla</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">

    <h1>Detalle de Zapatilla</h1>

    <div class="card mt-4">

        <div class="card-body text-dark">

            <p><strong>ID:</strong> <%= zapatilla.getId() %></p>

            <p><strong>Nombre:</strong> <%= zapatilla.getNombre() %></p>

            <p><strong>Precio:</strong> <%= zapatilla.getPrecio() %> €</p>

            <p><strong>Stock:</strong> <%= zapatilla.getStock() %></p>

            <p><strong>Color:</strong> <%= zapatilla.getColor() %></p>

            <p><strong>Marca ID:</strong> <%= zapatilla.getMarcaId() %></p>

            <a href="zapatillas"
               class="btn btn-primary">

                Volver

            </a>

        </div>

    </div>

</div>

</body>
</html>