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

    <h1 class="display-4 fw-bold text-center mb-5">
        👟 Detalle de Zapatilla
    </h1>

    <div class="row justify-content-center">

        <div class="col-md-8">

            <div class="card shadow-lg border-0 rounded-4">

                <img src="<%= zapatilla.getImagen() %>"
                     class="card-img-top"
                     style="height: 400px; object-fit: contain; background-color: #f8f9fa;"
                     onerror="this.src='https://via.placeholder.com/400';">

                <div class="card-body text-dark">

                    <h2 class="card-title">
                        <%= zapatilla.getNombre() %>
                    </h2>

                    <hr>

                    <h3 class="text-success fw-bold mb-4">
                        💰 <%= zapatilla.getPrecio() %> €
                    </h3>

                    <p>

                        <% if (zapatilla.getStock() > 10) { %>

                            <span class="badge bg-success">
                                Disponible
                            </span>

                        <% } else if (zapatilla.getStock() > 0) { %>

                            <span class="badge bg-warning text-dark">
                                Últimas unidades
                            </span>

                        <% } else { %>

                            <span class="badge bg-danger">
                                Sin stock
                            </span>

                        <% } %>

                    </p>

                    <p>
                        <strong>🎨 Color:</strong>
                        <%= zapatilla.getColor() %>
                    </p>

                   <p>
                       <strong>🏷️ Marca:</strong>
                       <%= zapatilla.getNombreMarca() %>
                   </p>

                    <div class="mt-4">

                        <a href="zapatillas"
                           class="btn btn-primary">

                            Volver al catálogo

                        </a>

                    </div>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>