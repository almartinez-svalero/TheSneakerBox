<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.thesneakerbox.model.Zapatilla" %>
<%@ page import="com.thesneakerbox.model.Marca" %>

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

    <div class="mb-4">

        <a href="marcas"
           class="btn btn-primary">
            Ver Marcas
        </a>

        <a href="zapatillas"
           class="btn btn-success">
            Ver Zapatillas
        </a>

    </div>

    <h3 class="mt-5">Nueva Zapatilla</h3>

    <form method="post" action="zapatillas" class="mb-5">

        <div class="mb-3">
            <input type="text"
                   name="nombre"
                   class="form-control"
                   placeholder="Nombre"
                   required>
        </div>

        <div class="mb-3">
            <input type="number"
                   step="0.01"
                   name="precio"
                   class="form-control"
                   placeholder="Precio"
                   required>
        </div>

        <div class="mb-3">
            <input type="number"
                   name="stock"
                   class="form-control"
                   placeholder="Stock"
                   required>
        </div>

        <div class="mb-3">
            <input type="text"
                   name="color"
                   class="form-control"
                   placeholder="Color">
        </div>

        <div class="mb-3">

            <select name="marcaId"
                    class="form-select"
                    required>

                <option value="">
                    Selecciona una marca
                </option>

                <%
                    List<Marca> marcas =
                            (List<Marca>) request.getAttribute("marcas");

                    if (marcas != null) {

                        for (Marca marca : marcas) {
                %>

                <option value="<%= marca.getId() %>">
                    <%= marca.getNombre() %>
                </option>

                <%
                        }
                    }
                %>

            </select>

        </div>

        <button type="submit"
                class="btn btn-success">

            Guardar Zapatilla

        </button>

    </form>

    <table class="table table-dark table-striped">

        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Color</th>
            <th>Marca</th>
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

            <td><%= zapatilla.getNombreMarca() %></td>

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