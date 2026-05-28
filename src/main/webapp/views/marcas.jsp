<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.thesneakerbox.model.Marca" %>

<!DOCTYPE html>
<html>
<head>
    <title>TheSneakerBox - Marcas</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">

    <h1 class="mb-4">Listado de Marcas</h1>

    <table class="table table-dark table-striped">

        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>País</th>
            <th>Premium</th>
        </tr>
        </thead>

        <tbody>

        <%
            List<Marca> marcas =
                    (List<Marca>) request.getAttribute("marcas");

            for (Marca marca : marcas) {
        %>

        <tr>
            <td><%= marca.getId() %></td>
            <td><%= marca.getNombre() %></td>
            <td><%= marca.getPais() %></td>
            <td>
                <%= marca.isPremium() ? "Sí" : "No" %>
            </td>
        </tr>

        <%
            }
        %>

        </tbody>

    </table>

</div>

</body>
</html>