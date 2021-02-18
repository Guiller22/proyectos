<?php
session_start();
$usuario =""; 
$contrasenya = "";
$error_usuario = "";
$error_contrasenya = "";

if($_SERVER["REQUEST_METHOD"] == "POST"){
 
    if(empty(trim($_POST["username"]))){
        $error_usuario = "Inserte su usuario";
    } else{
        $usuario = trim($_POST["username"]);
    }
    
    if(empty(trim($_POST["password"]))){
        $password_err = "Please enter your password.";
    } else{
        $password = trim($_POST["password"]);
    }
    
    if(empty($error_usuario) && empty($error_contrasenya)){
        $sql = "SELECT id, username, password FROM users WHERE username = ?";
        
        if($stmt = mysqli_prepare($link, $sql)){
            mysqli_stmt_bind_param($stmt, "s", $param_username);
            $param_username = $usuario;
            
            if(mysqli_stmt_execute($stmt)){
                mysqli_stmt_store_result($stmt);
                if(mysqli_stmt_num_rows($stmt) == 1){                    
                    mysqli_stmt_bind_result($stmt, $id, $usuario, $hashed_password);
                    if(mysqli_stmt_fetch($stmt)){
                        if(password_verify($contrasenya, $hashed_password)){
                            session_start();
                            $_SESSION["loggedin"] = true;
                            $_SESSION["id"] = $id;
                            $_SESSION["username"] = $username;                            
                            header("location: welcome.php");
                        } else{
                            $password_err = "The password you entered was not valid.";
                        }
                    }
                } else{
                    $username_err = "No account found with that username.";
                }
            } else{
                echo "Oops! Something went wrong. Please try again later.";
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
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <div class="wrapper">
        <h2>Login</h2>
        <form action='<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>' method="post">
            <div class="form-group <?php echo (!empty($error_usuario)) ? 'has-error' : ''; ?>">
                <label>Usuario</label>
                <input type="text" name="username" class="form-control" value="">
                <span class="help-block"><?php echo $error_usuario; ?></span>
            </div>    
            <div class="form-group <?php echo (!empty($error_contrasenya)) ? 'has-error' : ''; ?>">
                <label>Contraseña</label>
                <input type="password" name="password" class="form-control">
                <span class="help-block"><?php echo $error_contrasenya; ?></span>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Login">
            </div>
            <p>Si no tienes cuenta registrate<a href="registrar.php" class="enlace"> aquí</a>.</p>
        </form>
    </div>    
</body>
</html>