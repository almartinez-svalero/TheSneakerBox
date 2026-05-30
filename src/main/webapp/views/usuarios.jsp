<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.thesneakerbox.model.Usuario" %>

<%
    Usuario usuarioEditar =
            (Usuario) request.getAttribute("usuarioEditar");

    Usuario usuarioSesion =
            (Usuario) session.getAttribute("usuario");

    boolean esAdmin =
            usuarioSesion != null &&
            "ADMIN".equals(usuarioSesion.getRol());
%>

<!DOCTYPE html>
<html>
<head>

    <title>TheSneakerBox - Usuarios</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">

    <h1 class="mb-4">Listado de Usuarios</h1>

    <form method="get"
          action="usuarios"
          class="row g-2 mb-4">

        <div class="col-md-4">

            <input type="text"
                   name="nombre"
                   class="form-control"
                   placeholder="Buscar por nombre">

        </div>

        <div class="col-md-4">

            <input type="text"
                   name="email"
                   class="form-control"
                   placeholder="Buscar por email">

        </div>

        <div class="col-md-4">

            <button type="submit"
                    class="btn btn-info">

                Buscar

            </button>

            <a href="usuarios"
               class="btn btn-secondary">

                Limpiar

            </a>

        </div>

    </form>

    <div class="d-flex justify-content-between mb-4">

        <div>

            Bienvenido,
            <strong><%= usuarioSesion.getNombre() %></strong>
            (<%= usuarioSesion.getRol() %>)

        </div>

        <div>
            <a href="home"
               class="btn btn-secondary btn-sm">

                Inicio

            </a>

            <a href="marcas"
               class="btn btn-primary btn-sm">

                Marcas

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

    <h3 class="mt-5">

        <%= usuarioEditar != null
                ? "Editar Usuario"
                : "Nuevo Usuario" %>

    </h3>

    <form method="post"
          action="usuarios"
          class="mb-5">

        <input type="hidden"
               name="id"
               value="<%= usuarioEditar != null ? usuarioEditar.getId() : "" %>">

        <div class="mb-3">

            <input type="text"
                   name="nombre"
                   class="form-control"
                   placeholder="Nombre"
                   value="<%= usuarioEditar != null ? usuarioEditar.getNombre() : "" %>"
                   required>

        </div>

        <div class="mb-3">

            <input type="email"
                   name="email"
                   class="form-control"
                   placeholder="Email"
                   value="<%= usuarioEditar != null ? usuarioEditar.getEmail() : "" %>"
                   required>

        </div>

        <div class="mb-3">

            <input type="text"
                   name="password"
                   class="form-control"
                   placeholder="Contraseña"
                   value="<%= usuarioEditar != null ? usuarioEditar.getPassword() : "" %>"
                   required>

        </div>

        <div class="mb-3">

            <select name="rol"
                    class="form-select">

                <option value="USER"
                    <%= usuarioEditar != null && "USER".equals(usuarioEditar.getRol()) ? "selected" : "" %>>

                    USER

                </option>

                <option value="ADMIN"
                    <%= usuarioEditar != null && "ADMIN".equals(usuarioEditar.getRol()) ? "selected" : "" %>>

                    ADMIN

                </option>

            </select>

        </div>

        <div class="form-check mb-3">

            <input type="checkbox"
                   name="activo"
                   class="form-check-input"
                   id="activo"
                   <%= usuarioEditar != null && usuarioEditar.isActivo() ? "checked" : "" %>>

            <label class="form-check-label"
                   for="activo">

                Activo

            </label>

        </div>

        <button type="submit"
                class="btn btn-success">

            <%= usuarioEditar != null
                    ? "Actualizar Usuario"
                    : "Guardar Usuario" %>

        </button>

    </form>

    <% } %>

    <table class="table table-dark table-striped">

        <thead>

        <tr>

            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Rol</th>
            <th>Activo</th>
            <th>Acciones</th>

        </tr>

        </thead>

        <tbody>

        <%
            List<Usuario> usuarios =
                    (List<Usuario>) request.getAttribute("usuarios");

            if (usuarios != null) {

                for (Usuario usuario : usuarios) {
        %>

        <tr>

            <td><%= usuario.getId() %></td>
            <td><%= usuario.getNombre() %></td>
            <td><%= usuario.getEmail() %></td>
            <td><%= usuario.getRol() %></td>
            <td><%= usuario.isActivo() ? "Sí" : "No" %></td>

            <td>

                <a href="usuarios?action=detail&id=<%= usuario.getId() %>"
                   class="btn btn-info btn-sm">

                    Detalle

                </a>

                <% if (esAdmin) { %>

                <a href="usuarios?action=edit&id=<%= usuario.getId() %>"
                   class="btn btn-warning btn-sm">

                    Editar

                </a>

                <a href="usuarios?action=delete&id=<%= usuario.getId() %>"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('¿Eliminar usuario?')">

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