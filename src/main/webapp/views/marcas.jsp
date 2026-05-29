<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.thesneakerbox.model.Marca" %>

<%
    Marca marcaEditar =
            (Marca) request.getAttribute("marcaEditar");
%>

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

    <h3 class="mt-5">

        <%= marcaEditar != null
                ? "Editar Marca"
                : "Nueva Marca" %>

    </h3>

    <form method="post" action="marcas" class="mb-4">

        <input type="hidden"
               name="id"
               value="<%= marcaEditar != null ? marcaEditar.getId() : "" %>">

        <div class="mb-3">
            <input type="text"
                   name="nombre"
                   class="form-control"
                   placeholder="Nombre"
                   value="<%= marcaEditar != null ? marcaEditar.getNombre() : "" %>"
                   required>
        </div>

        <div class="mb-3">
            <input type="text"
                   name="pais"
                   class="form-control"
                   placeholder="País"
                   value="<%= marcaEditar != null ? marcaEditar.getPais() : "" %>"
                   required>
        </div>

        <div class="form-check mb-3">

            <input type="checkbox"
                   name="premium"
                   class="form-check-input"
                   id="premium"
                   <%= marcaEditar != null && marcaEditar.isPremium()
                           ? "checked"
                           : "" %>>

            <label class="form-check-label" for="premium">
                Premium
            </label>

        </div>

        <div class="mb-3">
            <input type="text"
                   name="logo"
                   class="form-control"
                   placeholder="Logo"
                   value="<%= marcaEditar != null ? marcaEditar.getLogo() : "" %>">
        </div>

        <button type="submit"
                class="btn btn-success">

            <%= marcaEditar != null
                    ? "Actualizar Marca"
                    : "Guardar Marca" %>

        </button>

    </form>

    <table class="table table-dark table-striped">

        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>País</th>
            <th>Premium</th>
            <th>Acciones</th>
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

            <td>
                <%= marca.isPremium() ? "Sí" : "No" %>
            </td>

            <td>

                <a href="marcas?action=edit&id=<%= marca.getId() %>"
                   class="btn btn-warning btn-sm">

                    Editar

                </a>

                <a href="marcas?action=delete&id=<%= marca.getId() %>"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('¿Eliminar marca?')">

                    Eliminar

                </a>

            </td>

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