<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.thesneakerbox.model.Zapatilla" %>

<!DOCTYPE html>
<html>
<head>
    <title>TheSneakerBox - Zapatillas</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-dark text-white">

<div class="container mt-5">

    <h1 class="mb-4">Listado de Zapatillas</h1>

    <a href="marcas"
       class="btn btn-primary mb-4">

        Ver Marcas

    </a>

    <table class="table table-dark table-striped">

        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Color</th>
            <th>Marca ID</th>
        </tr>
        </thead>

        <tbody>

        <%
            List<Zapatilla> zapatillas =
                    (List<Zapatilla>) request.getAttribute("zapatillas");

            if (zapatillas != null) {

                for (Zapatilla zapatilla : zapatillas) {
        %>

        <tr>

            <td><%= zapatilla.getId() %></td>

            <td><%= zapatilla.getNombre() %></td>

            <td><%= zapatilla.getPrecio() %></td>

            <td><%= zapatilla.getStock() %></td>

            <td><%= zapatilla.getColor() %></td>

            <td><%= zapatilla.getMarcaId() %></td>

        </tr>

        <%
                }
            }
        %>

        </tbody>

    </table>

</div>

</body>
</html>