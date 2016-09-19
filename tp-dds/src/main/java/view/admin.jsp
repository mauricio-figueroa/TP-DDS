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


<!-- Pantalla de historial -->
<div id="acciones" class="">
        <div class="">
           <h1>ACCIONES ANTE LA BÚSQUEDA</h1>
        </div>

        <h2>Criterio de Busqueda</h2>
        <div class="">
          <p>Usuario</p>
          <input type="text" name="name" value="">

            <p>Fecha</p>
            <input type="text" name="name" value="">
            <input type="text" name="name" value="">

            <button type="button" name="button">Buscar</button>



          <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
            <h2>Resultado</h2>
            <div class="">
                  <p class="col-lg-4 col-md-4 col-sm-4 col-xs-4">Fecha</p>
                  <p class="col-lg-2 col-md-2 col-sm-2 col-xs-2">Usuario</p>
                  <p class="col-lg-4 col-md-4 col-sm-4 col-xs-4">Parámetros</p>
                  <p class="col-lg-2 col-md-2 col-sm-2 col-xs-2">POIs</p>
            </div>


          </div>

          <div class="">
                <h2>Detalle de POIS</h2>
          </div>
        </div>


</div>
<!-- Termina pantalla de historial -->

<!-- Pantalla de acciones -->
<div id="historial" class="">
        <div class="">
           <h1>HISTORIAL DE BÚSQUEDAS REALIZADAS</h1>
        </div>

        <select>
              <option value="" selected disabled>Elegir permisos</option>
              <option value="">Option2</option>
        </select>

        <button id="addPermiso" type="button" name="button">Agregar</button>

        <div class="">
          <h2>Acciones</h2>
          <div class="">
            <p>Totalizar por Fecha</p><button id="deletePermiso" type="button" name="button"> Eliminar</button>
          </div>
        </div>

        <button id="submitHis" type="button" name="button">OK</button>

        <button id="cancelHis" type="button" name="button">Cancelar</button>

</div>

<!-- Termina pantalla de acciones -->



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
