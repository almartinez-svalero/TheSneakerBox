<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.thesneakerbox.model.Zapatilla" %>
<%@ page import="com.thesneakerbox.model.Marca" %>
<%@ page import="com.thesneakerbox.model.Usuario" %>

<%
    Zapatilla zapatillaEditar =
            (Zapatilla) request.getAttribute("zapatillaEditar");

    Usuario usuario =
            (Usuario) session.getAttribute("usuario");

    boolean esAdmin =
            usuario != null &&
            "ADMIN".equals(usuario.getRol());
%>

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

    <div class="d-flex justify-content-between mb-4">

        <div>

            Bienvenido,
            <strong><%= usuario.getNombre() %></strong>
            (<%= usuario.getRol() %>)

        </div>

        <div>

            <a href="marcas"
               class="btn btn-primary btn-sm">

                Marcas

            </a>

            <a href="logout"
               class="btn btn-danger btn-sm">

                Cerrar sesión

            </a>

        </div>

    </div>

    <% if (esAdmin) { %>

    <h3 class="mt-5">

        <%= zapatillaEditar != null
                ? "Editar Zapatilla"
                : "Nueva Zapatilla" %>

    </h3>

    <form method="post" action="zapatillas" class="mb-5">

        <input type="hidden"
               name="id"
               value="<%= zapatillaEditar != null ? zapatillaEditar.getId() : "" %>">

        <div class="mb-3">
            <input type="text"
                   name="nombre"
                   class="form-control"
                   placeholder="Nombre"
                   value="<%= zapatillaEditar != null ? zapatillaEditar.getNombre() : "" %>"
                   required>
        </div>

        <div class="mb-3">
            <input type="number"
                   step="0.01"
                   name="precio"
                   class="form-control"
                   placeholder="Precio"
                   value="<%= zapatillaEditar != null ? zapatillaEditar.getPrecio() : "" %>"
                   required>
        </div>

        <div class="mb-3">
            <input type="number"
                   name="stock"
                   class="form-control"
                   placeholder="Stock"
                   value="<%= zapatillaEditar != null ? zapatillaEditar.getStock() : "" %>"
                   required>
        </div>

        <div class="mb-3">
            <input type="text"
                   name="color"
                   class="form-control"
                   placeholder="Color"
                   value="<%= zapatillaEditar != null ? zapatillaEditar.getColor() : "" %>">
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

                <option value="<%= marca.getId() %>"
                    <%= zapatillaEditar != null
                            && zapatillaEditar.getMarcaId() == marca.getId()
                            ? "selected"
                            : "" %>>

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

            <%= zapatillaEditar != null
                    ? "Actualizar Zapatilla"
                    : "Guardar Zapatilla" %>

        </button>

    </form>

    <% } %>

    <table class="table table-dark table-striped">

        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Color</th>
            <th>Marca</th>
            <th>Acciones</th>
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

            <td>

                <% if (esAdmin) { %>

                <a href="zapatillas?action=edit&id=<%= zapatilla.getId() %>"
                   class="btn btn-warning btn-sm">

                    Editar

                </a>

                <a href="zapatillas?action=delete&id=<%= zapatilla.getId() %>"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('¿Eliminar zapatilla?')">

                    Eliminar

                </a>

                <% } else { %>

                    Solo lectura

                <% } %>

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