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

<button class="icon-login close-sesion" type="button" name="button" onclick="cerrarSesion()" >Cerrar Sesion</button>
<div class="welcome">
	<h1>Bienvenido a la terminal de búsqueda</h1>
</div>

	<div class="busqueda col-lg-12 col-md-12 col-sm-12 col-xs-12">

			<div class="">
					<h1>BUSQUEDA</h1>
			</div>

			<div class="first-block col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h2>Criterio de búsqueda</h2>

					<p>	</p>

					<div id="content" class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
						<input id="searchPoi" class="dataSearch" type="text" name="name" value="">
					</div>

					<button  type="button" name="button" onclick="addInput()" >Agregar</button>
					<button  type="button" name="button" onclick="search()">Buscar</button>





			</div>

			<div class="second-block col-lg-12 col-md-12 col-sm-12 col-xs-12">

					<h2>Resultado</h2>

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div   class="col-lg-4 col-md-4 col-sm-4 col-xs-4 nombre">
										<p class=" namecol col-lg-12 col-md-12 col-sm-12 col-xs-12">
											Ícono
										</p>
										<div id="testName" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

										</div>

								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 direccion">
										<p class="namecol col-lg-12 col-md-12 col-sm-12 col-xs-12">
											Información
										</p>

										<div id="testInfo" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

										</div>

								</div>

					</div>






			</div>

	</div>


<div id="modal" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 modal">

	<div class="modal-inner">
		<p>
			Los datos se ingresaron incorrectamente o el usuario no existe
		</p>

	</div>

</div>

 <!-- <footer> P.O.I Todos los derechos reservados. 2016 ®</footer> -->
</body>

<script>

$( document ).ready(function() {
   ejecutarControllers();
});


</script>

</html>
