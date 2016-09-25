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

				  <button type="button" name="button" onclick="ejecutarControllers()" >EJECUTAR CONTROLLERS</button>


  </div>

  <button class="volver" type="button" name="button" onclick="volver()">Volver</button>


<!-- Pantalla de historial -->
<div id="acciones" class="">
        <div class="">
           <h1>HISTORIAL DE BÚSQUEDAS REALIZADAS</h1>
        </div>

        <h2>Criterio de Busqueda</h2>
        <div class="">
          <p>Usuario</p>
          <input id="nameUser" type="text" name="name" value="">

            <p>Fecha</p>
            <input id="initFecha" type="text" name="name" value="">
            <input id="finFecha" type="text" name="name" value="">

            <button id="searchAc" type="button" name="button">Buscar</button>



          <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
            <h2>Resultado</h2>
            <div class="">
                  <p class="namecol col-lg-4 col-md-4 col-sm-4 col-xs-4">Fecha</p>
                  <p class="namecol col-lg-2 col-md-2 col-sm-2 col-xs-2">Usuario</p>
                  <p class=" namecol col-lg-4 col-md-4 col-sm-4 col-xs-4">Parámetros</p>
                  <p class="namecol col-lg-2 col-md-2 col-sm-2 col-xs-2">POIs</p>
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
           <h1>ACCIONES ANTE LA BÚSQUEDA</h1>
        </div>

        <select id="listPermisos">
              <option value="" selected disabled>Elegir permisos</option>
              <option  id="1" class="8" value="">Buscar poi</option>
							<option id="2"value="1">Consultar cercanía</option>
							<option id="3" value="2">Consultar disponibilidad</option>
							<option id="4" value="3">Agregar Poi</option>
							<option id="5" value="4">Agregar Terminal</option>
							<option id="6" value="5">Modificar Poi</option>
							<option id="7" value="6">Remover Terminal</option>
							<option id="8" value="7">Remover Poi</option>

        </select>

        <button id="addPermiso" type="button" name="button" onclick="addPermiso()">Agregar</button>

        <div class="">
          <h2>Acciones</h2>
          <div  id="permisos" class="">

          </div>
        </div>

        <div class="accept">

                  <button id="submitHis" type="button" name="button">OK</button>

                  <button id="cancelHis" type="button" name="button">Cancelar</button>
        </div>


</div>

<!-- Termina pantalla de acciones -->



<!-- <footer> P.O.I Todos los derechos reservados. 2016 ®</footer> -->
</body>


<script>

user1={};
user2={};
user3={};
users=[user1, user2, user3];
user1.lista=[];
user2.lista=[];
user3.lista=[];
id=0;

terminal="terminal1";


  function displayHistorial(){
		$('.button-admin').hide();
		$('#acciones').show();
		$('.volver').show();

  }

  function displayAcciones(){

		    $('.button-admin').hide();
		    $('#historial').show();
		    $('.volver').show();
  }

  function volver(){
    $('.button-admin').show();
    $('#acciones').hide();
    $('#historial').hide();
    $('.volver').hide();
  }

	function searchHistorial(){
		 	var datSend={};

			dataSend["user"]=$( "#nameUser" ).val();
			dataSend["initFecha"]=$( "#initFecha"  ).val();
			dataSend["finFecha"]=$( "#finFecha" ).val();

			url='http://localhost:8080/diseno-de-sistemas/search-poi-from?searchName='+$( "#nameUser" ).val()+'&terminalName='+terminal;
			url2='http://localhost:8080/diseno-de-sistemas/reportByTerminal?name='+terminal;

			$.get(url, function(dataReceived){
				 console.log(dataReceived);

				 for (var i = 0; i < dataReceived.length; i++) {

				 }


			 });



			};


		function addPermiso(){
			var i=Math.floor((Math.random() * 2) + 0);

			console.log(users[i]);

		$( "#permisos" ).append( ' </br><p id="num'+id+'" class="permisoAdded">'+$( "#listPermisos option:selected" ).text()+'</p><button id="deletePermiso" type="button" name="button" onclick="eliminarPermiso('+id+')"> Eliminar</button>' );

		users[i].lista.push(id);

		id++;
		console.log(id);
		}



function eliminarPermiso(target){

$( '#num'+target).remove();

}

</script>


</html>
