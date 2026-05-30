<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

    <title>Login - TheSneakerBox</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-4">

            <div class="card">

                <div class="card-body">

                    <h3 class="mb-4 text-dark">
                        Iniciar Sesión
                    </h3>

                    <form method="post"
                          action="login">

                        <div class="mb-3">

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   placeholder="Email"
                                   required>

                        </div>

                        <div class="mb-3">

                            <input type="password"
                                   name="password"
                                   class="form-control"
                                   placeholder="Contraseña"
                                   required>

                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">

                            Entrar

                        </button>

                    </form>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>