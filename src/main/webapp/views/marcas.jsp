<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.thesneakerbox.model.Marca" %>
<%@ page import="com.thesneakerbox.model.Usuario" %>

<%
    Marca marcaEditar =
            (Marca) request.getAttribute("marcaEditar");

    Usuario usuario =
            (Usuario) session.getAttribute("usuario");

    boolean esAdmin =
            usuario != null &&
            "ADMIN".equals(usuario.getRol());
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

  <h1 class="display-4 fw-bold mb-4">
      🏷️ Gestión de Marcas
  </h1>

    <form method="get"
          action="marcas"
          class="row g-2 mb-4">

        <div class="col-md-4">

            <input type="text"
                   name="nombre"
                   class="form-control"
                   placeholder="Buscar por nombre">

        </div>

        <div class="col-md-4">

            <input type="text"
                   name="pais"
                   class="form-control"
                   placeholder="Buscar por país">

        </div>

        <div class="col-md-4">

            <button type="submit"
                    class="btn btn-info">

                Buscar

            </button>

            <a href="marcas"
               class="btn btn-secondary">

                Limpiar

            </a>

        </div>

    </form>

    <div class="d-flex justify-content-between mb-4">

        <div>

            Bienvenido,
            <strong><%= usuario.getNombre() %></strong>

            <% if (esAdmin) { %>

                <span class="badge bg-danger ms-2">
                    ADMIN
                </span>

            <% } else { %>

                <span class="badge bg-primary ms-2">
                    USER
                </span>

            <% } %>

        </div>

        <div>
            <a href="home"
               class="btn btn-secondary btn-sm">

                Inicio

            </a>

            <a href="zapatillas"
               class="btn btn-primary btn-sm">

                Zapatillas

            </a>

            <a href="logout"
               class="btn btn-danger btn-sm">

                Cerrar sesión

            </a>

        </div>

    </div>

    <% if (esAdmin) { %>

  <div class="card bg-secondary bg-opacity-25 border-light mb-4">
  <div class="card-body p-3">

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

        <div class="form-check form-switch mb-3">

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
  </div>
  </div>
    <% } %>

    <table class="table table-dark table-hover table-striped align-middle shadow-lg">

        <thead>
        <tr>
            <th>ID</th>
            <th>Logo</th>
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

            <td>

                <% if (marca.getLogo() != null && !marca.getLogo().isEmpty()) { %>

                    <img src="<%= marca.getLogo() %>"
                         width="70"
                         height="70"
                         class="rounded-circle border border-light"
                         style="object-fit: cover;"
                         onerror="this.src='https://via.placeholder.com/70';">

                <% } else { %>

                    <span class="badge bg-secondary">
                        Sin logo
                    </span>

                <% } %>

            </td>

            <td><%= marca.getNombre() %></td>

            <td><%= marca.getPais() %></td>

            <td>

                <% if (marca.isPremium()) { %>

                    <span class="badge bg-warning text-dark">
                        Premium
                    </span>

                <% } else { %>

                    <span class="badge bg-secondary">
                        Estándar
                    </span>

                <% } %>

            </td>

            <td>

                <a href="marcas?action=detail&id=<%= marca.getId() %>"
                   class="btn btn-info btn-sm">

                    Detalle

                </a>

                <% if (esAdmin) { %>

                <a href="marcas?action=edit&id=<%= marca.getId() %>"
                   class="btn btn-warning btn-sm">

                    Editar

                </a>

                <a href="marcas?action=delete&id=<%= marca.getId() %>"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('¿Eliminar marca?')">

                    Eliminar

                </a>

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