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


	<div class="busqueda col-lg-12 col-md-12 col-sm-12 col-xs-12">

			<div class="">
					<h1>BUSQUEDA</h1>
			</div>

			<div class="first-block col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h2>Criterio de búsqueda</h2>

					<p>	</p>

					<div id="content" class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
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
			Modo Administrador
		</p>
		<form class="" action="" method="post">

			<input id="userid" class="log-input" type="text" name="name" value="" placeholder="Usuario">

		<input  id="psw"  class="log-input"  type="text" name="name" value="" placeholder="Contraseña">
			<button class="log-button" type="button" name="button" onclick="log()">Enviar</button>
		</form>

		<div class="modal-footer">
						 <button type="button" class="btn btn-default" onclick="closeModal()" data-dismiss="modal">X</button>
		</div>




	</div>

</div>

 <!-- <footer> P.O.I Todos los derechos reservados. 2016 ®</footer> -->
</body>

 <script >

 $( document ).ready(function() {
    ejecutarControllers();
});


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
 	d.innerHTML += '<br />	<input id="searchPoi" class="dataSearch" type="text" name="name" value="">';
}


function log(){
	var dataSend={};

	 dataSend["user"]=$('#userid').val();

     dataSend["pw"]= $('#psw').val();

	url="http://localhost:8080/diseno-de-sistemas/validarUsuario?user="+$('#userid').val()+"&pw="+ $('#psw').val();

	$.get(url, function(dataReceived){
		console.log(dataReceived);
		if(dataReceived){
			//$(location).attr('href', 'file:///usr/local/Tomcat/work/TP-DDS/tp-dds/src/main/java/view/admin.jsp');
			$(location).attr('href', 'C:/Users/Mauricio/Desktop/tpDDS/TP-DDS/tp-dds/src/main/java/view/admin.jsp');
		}else{
			alert("Datos incorrectos");
		};

	});

}



function search(){



	$( "#testName" ).empty();
	$( "#testInfo" ).empty();


	 infoArray=[];
			$( '.dataSearch' ).each(function() {
		  infoArray.push($( this ).val());

		});

var url='http://localhost:8080/diseno-de-sistemas/poi-show';


			$.get(url, function(dataReceived){
				 console.log(dataReceived);

				 for (var i = 0; i < dataReceived.length; i++) {

								  switch (dataReceived[i].type) {
								  	case "CGP":
										$( "#testName" ).append( "<br /> <img class='img-poi' src='"+dataReceived[i].icon+"' alt='' />");
										$( "#testInfo" ).append( '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Dirección:' +dataReceived[i].direccion+
										 																			'<br /> Zona:'+dataReceived[i].zone+ '<br /> Servicios:'+dataReceived[i].cgp_services+ '</p>' );

								  	break;//1

										case "Bank":

										$( "#testName" ).append( '<br /><img class="img-poi" src="'+dataReceived[i].icon+'" alt="" />' );
										$( "#testInfo" ).append( '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Dirección:' +dataReceived[i].direccion+
																													'<br /> Zona:'+dataReceived[i].zone+ '<br /> Servicios:'+dataReceived[i].bank_services+ '</p>' );
										break;//2

										case "BusStation":

										$( "#testName" ).append( '<br /><img class="img-poi" src="'+dataReceived[i].icon+'" alt="" />' );
										$( "#testInfo" ).append( '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Número de línea:' +dataReceived[i].number_line+'</p>' );
										break;//3

										case "ComercialShop":

										$( "#testName" ).append( '<br /><img class="img-poi" src="'+dataReceived[i].icon+'" alt="" />' );
										$( "#testInfo" ).append( '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Dirección:' +dataReceived[i].direccion+
																													'<br /> Nombre:'+dataReceived[i].name+ '<br /> Rubro:'+dataReceived[i].activity+ '</p>' );
										break;//4

								  	default:

										$( "#testName" ).append( '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">No hay información</p>' );
										$( "#testInfo" ).append( '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> No hay información</p>' );


								  }; //termina switch
				 }


			 });
		};


		function ejecutarControllers(){

			$.get('http://localhost:8080/diseno-de-sistemas/terminal-add?name=terminalGabo1&lat=-34.638800&lon=-58.393426&action=ADDTERMINAL', function(dataReceived){
				console.log('Se aregó una terminal');});


		$.get('http://localhost:8080/diseno-de-sistemas/poi-addBusStation?name=114Parada&type=busStation&mainStreet=Straccia%201231&lat=-34.638473&lon=-58.391618&busNumber=114',
		 function(dataReceived){
			console.log('Se agregó un BusStation');});


		$.get('http://localhost:8080/diseno-de-sistemas/poi-addCGP?name=CGPGab0&type=CGP&mainStreet=Straccia%201231&lat=-34.638473&lon=-58.391618&communeRadius=%2043.56', function(dataReceived){
			console.log('Se agregó un CGP');});


		$.get('http://localhost:8080/diseno-de-sistemas/poi-addComercial?name=gab0Library&type=library&mainStreet=Straccia%201231&lat=-34.638473&lon=-58.391618&rubro=Library&maxDistance=500', function(dataReceived){
			console.log('Se agregó un Local Comercial');});


		$.get('http://localhost:8080/diseno-de-sistemas/poi-addBank?name=bank1&type=bank&mainStreet=Straccia%201231&lat=-34.638473&lon=-58.391618&services=aa', function(dataReceived){
			console.log('Se agregó un Banco');});

		//
		// $.get('', function(dataReceived){console.log(dataReceived);});
		//
		// $.get('', function(dataReceived){console.log(dataReceived);});
		//
		// $.get('', function(dataReceived){console.log(dataReceived);});

		}




 </script>



</html>
