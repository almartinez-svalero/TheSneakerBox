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

    <h3 class="mt-5">Nueva Marca</h3>

    <form method="post" action="marcas" class="mb-4">

        <div class="mb-3">
            <input type="text"
                   name="nombre"
                   class="form-control"
                   placeholder="Nombre"
                   required>
        </div>

        <div class="mb-3">
            <input type="text"
                   name="pais"
                   class="form-control"
                   placeholder="País"
                   required>
        </div>

        <div class="form-check mb-3">
            <input type="checkbox"
                   name="premium"
                   class="form-check-input"
                   id="premium">

            <label class="form-check-label" for="premium">
                Premium
            </label>
        </div>

        <div class="mb-3">
            <input type="text"
                   name="logo"
                   class="form-control"
                   placeholder="Logo">
        </div>

        <button type="submit"
                class="btn btn-success">
            Guardar Marca
        </button>

    </form>

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

            if (marcas != null) {

                for (Marca marca : marcas) {
        %>

        <tr>
            <td><%= marca.getId() %></td>
            <td><%= marca.getNombre() %></td>
            <td><%= marca.getPais() %></td>
            <td><%= marca.isPremium() ? "Sí" : "No" %></td>
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