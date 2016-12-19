<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>P.O.I </title>
<link rel="shortcut icon" href="icon.png" type="image/png">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap-theme.min.css">
	<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="script.js"></script>

</script>

<link rel="stylesheet" href="style.css">
</head>

<body class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bg-fit">

<div class="welcome">
			<img id="icono" src="icon.png" alt="icono" />
			<h1>Bienvenido a P.O.I</h1>
</div>

<div class="eleccion">
	<h2>Ingrese el usuario y contraseña para iniciar sesión</h2>
	<input id="userid" class="log-input" type="text" name="name" value="" placeholder="Usuario"><br>

<input  id="psw"  class="log-input"  type="password" name="name" value="" placeholder="Contraseña"><br>
	<button class="log-button icon-login" type="button" name="button" onclick="log()">Iniciar Sesión</button>
</div>


<div id="modal" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 modal">

</div>
 <!-- <footer> P.O.I Todos los derechos reservados. 2016 ®</footer> -->
</body>

<script>

$( document ).ready(function() {
closeModal();
});

</script>




</html>
