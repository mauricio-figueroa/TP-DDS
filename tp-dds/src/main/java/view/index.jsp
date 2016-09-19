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

<button class="icon-login" type="button" name="button" onclick="openModal()" >Iniciar Sesión</button>



	<!-- <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 map">

			<div id="map" class="mapSites"></div>

	</div>


	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 selector">


		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 searchPoi">
			<form action="">
				<p>INGRESE POI POR DISPONIBILIDAD</p>
				<input id="pac-input" class="functionBox" class="controls" type="text" placeholder="Buscar"><br>


				<button type="submit" value="Submit">BUSCAR</button>
			</form>
		</div>

		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 searchPoi">
			<form action="">
				<p>INGRESE POI POR CERCANIA</p>
				<input type="text" name="" value=""><br>

				<button type="submit" value="Submit">BUSCAR</button>
			</form>

		</div>

		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 searchPoi">
			<form action="">
				<p>INGRESE POI</p>
				<input type="text" name="" value=""><br>

				<button type="submit" value="Submit">BUSCAR</button>
			</form>
		</div>


	</div>

	-->

	<div class="busqueda col-lg-12 col-md-12 col-sm-12 col-xs-12">

			<div class="">
					<h1>BUSQUEDA</h1>
			</div>

			<div class="first-block col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h2>Criterio de búsqueda</h2>

					<p>	</p>

					<div id="content" class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
						<input id="searchPoi" type="text" name="name" value="">
					</div>

					<button  type="button" name="button" onclick="addInput()" >Agregar</button>
					<button  type="button" name="button">Buscar</button>





			</div>

			<div class="second-block col-lg-12 col-md-12 col-sm-12 col-xs-12">

					<h2>Resultado</h2>

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 nombre">
										<p class=" namecol col-lg-12 col-md-12 col-sm-12 col-xs-12">
											Nombre
										</p>
										<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											saraza
										</p>
										<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											saraza
										</p>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 direccion">
										<p class="namecol col-lg-12 col-md-12 col-sm-12 col-xs-12">
											Direcciòn
										</p>
										<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											saraza
										</p>
										<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											saraza
										</p>
								</div>

					</div>






			</div>

	</div>


<div id="modal" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 modal">

	<div class="modal-inner">
		<p>
			Modo Administrador
		</p>
		<form class="" action="" method="post">
			<p>
				Usuario
			</p>
			<input id="userid" class="log-input" type="text" name="name" value="">
				<p>
					Contraseña
				</p>
		<input  id="psw"  class="log-input"  type="text" name="name" value="">
			<button class="log-button" type="button" name="button" onclick="log()">Enviar</button>
		</form>

		<div class="modal-footer">
						 <button type="button" class="btn btn-default" onclick="closeModal()" data-dismiss="modal">X</button>
		</div>



	</div>

</div>

 <footer> P.O.I Todos los derechos reservados. 2016 ®</footer>
</body>

	<!--

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDbx3ox75shr0gNruHyXzmV6CHOP275qIE&libraries=places&callback=initAutocomplete"
         async defer>

 </script>

 <script>
 	function initAutocomplete() {
  var map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -34.616606, lng: -58.416803},
    zoom: 13,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });


  var input = document.getElementById('pac-input');
  var searchBox = new google.maps.places.SearchBox(input);


  map.addListener('bounds_changed', function() {
    searchBox.setBounds(map.getBounds());
  });

  var markers = [];

  searchBox.addListener('places_changed', function() {
    var places = searchBox.getPlaces();

    if (places.length == 0) {
      return;
    }


    markers.forEach(function(marker) {
      marker.setMap(null);
    });
    markers = [];

    var bounds = new google.maps.LatLngBounds();
    places.forEach(function(place) {
      var icon = {
        url: place.icon,
        size: new google.maps.Size(71, 71),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(17, 34),
        scaledSize: new google.maps.Size(25, 25)
      };


      markers.push(new google.maps.Marker({
        map: map,
        icon: icon,
        title: place.name,
        position: place.geometry.location
      }));

      if (place.geometry.viewport) {

        bounds.union(place.geometry.viewport);
      } else {
        bounds.extend(place.geometry.location);
      }
    });
    map.fitBounds(bounds);
  });
}
 </script>

	-->
 <script >
 function openModal(){
 var modal = document.getElementById("modal");
	 modal.style.display = "block";

 }

 function closeModal(){
	 var modalC = document.getElementById("modal");
	 modalC.style.display="none";

 }




 function addInput(){
	 var d = document.getElementById("content");

	 d.innerHTML += '<br /><input type="text" name="name" value="">';
}


function log(){

	var user= $('#userid').val();
	var psw= $('#psw').val();

    $.post("URL_PARA_EL_LOGUEO",
    {
        name: user,
        city: psw
    },
    openAdminMode());


//http://localhost:8080/diseno-de-sistemas/reportByTerminal?name=hola

 console.log(name);
}

function openAdminMode(){
	window.location = "file:///usr/local/Tomcat/work/TP-DDS/tp-dds/src/main/java/view/admin.jsp"
}

 </script>



</html>
