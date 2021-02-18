<?php
$usuario = "";
$contrasenya = "";
$confirmarContrasenya = "";
$errorUsuario = "";
$errorContrasenya = "";
$errorConfirmarContrasenya = "";
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    if (empty(trim($_POST["usuario"]))) {
        $errorUsuario = "Introduce un usuario";
    } else {
        $sql = "SELECT id FROM users WHERE username = ?";

        if ($stmt = mysqli_prepare($link, $sql)) {
            mysqli_stmt_bind_param($stmt, "s", $param_username);


            $param_username = trim($_POST["usuario"]);

            if (mysqli_stmt_execute($stmt)) {
                mysqli_stmt_store_result($stmt);
                if (mysqli_stmt_num_rows($stmt) == 1) {
                    $errorUsuario = "Eso usuario ya existe";
                } else {
                    $usuario = trim($_POST["usuario"]);
                }
            } else {
                echo "Hubo un error.";
            }
            mysqli_stmt_close($stmt);
        }
    }

    if (empty(trim($_POST["contrasenya"]))) {
        $errorContrasenya = "Introduce una contraseña.";
    } elseif (strlen(trim($_POST["contrasenya"])) < 6) {
        $errorContrasenya = "Debe tener al menos 6 caracteres";
    } else {
        $contrasenya = trim($_POST["password"]);
    }

    if (empty(trim($_POST["confirm_password"]))) {
        $errorConfirmarContrasenya = "Confirma la contraseña ";
    } else {
        $confirmarContrasenya = trim($_POST["confirm_password"]);
        if (empty($errorContrasenya) && ($contrasenya != $confirmarContrasenya)) {
            $confirmarContrasenya = "No coinciden las contraseñas.";
        }
    }

    if (empty($errorUsuario) && empty($errorContrasenya) && empty($errorConfirmarContrasenya)) {

        $sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        if ($stmt = mysqli_prepare($link, $sql)) {
            mysqli_stmt_bind_param($stmt, "ss", $param_username, $param_password);
            $param_username = $usuario;
            $param_password = password_hash($contrasenya, PASSWORD_DEFAULT);
            if (mysqli_stmt_execute($stmt)) {
                header("location: login.php");
            } else {
                echo "Hubo un error";
            }
            mysqli_stmt_close($stmt);
        }
    }
    mysqli_close($link);
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="css/estilo.css">
</head>

<body>
    <div class="wrapper">
        <h2>Regístrate</h2>
        <form action='<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>' method="post">
            <div class="form-group <?php echo (!empty($errorUsuario)) ? 'has-error' : ''; ?>">
                <label>Usuario</label>
                <input type="text" name="username" class="form-control" value="">
                <span class="help-block"><?php echo $errorUsuario; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($errorContrasenya)) ? 'has-error' : ''; ?>">
                <label>Contraseña</label>
                <input type="password" name="contrasenya" class="form-control" value="">
                <span class="help-block"><?php echo $errorContrasenya; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($errorConfirmarContrasenya)) ? 'has-error' : ''; ?>">
                <label>Confirmación contraseña</label>
                <input type="password" name="confirmarContrasenya" class="form-control" value="">
                <span class="help-block"><?php echo $errorConfirmarContrasenya; ?></span>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Submit">
                <input type="reset" class="btn btn-default" value="Reset">
            </div>
            <p>Si ya tienes cuenta pulsa <a href="index.php">aquí</a>.</p>
        </form>
    </div>
</body>

</html>