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

</script>

<link rel="stylesheet" href="stylesheet_java.css">
</head>

<body class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

  <div class="button-admin">

        <button type="button" name="button" onclick="displayAcciones()"> ACCIONES</button>

        <button type="button" name="button" onclick="displayHistorial()" >HISTORIAL</button>


  </div>

  <button class="volver" type="button" name="button" onclick="volver()">Volver</button>

<div id="historial" class="">
        <div class="">
           <h1>ACCIONES ANTE LA BÚSQUEDA</h1>
        </div>
</div>

<div id="acciones" class="">
        <div class="">
           <h1>HISTORIAL DE BÚSQUEDAS REALIZADAS</h1>
        </div>
</div>



<footer> P.O.I Todos los derechos reservados. 2016 ®</footer>
</body>


<script>
  function displayHistorial(){
    $('.button-admin').hide();
    $('#historial').show();
    $('.volver').show();
  }

  function displayAcciones(){
      $('.button-admin').hide();
      $('#acciones').show();
      $('.volver').show();

  }

  function volver(){
    $('.button-admin').show();
    $('#acciones').hide();
    $('#historial').hide();
    $('.volver').hide();
  }

</script>


</html>
