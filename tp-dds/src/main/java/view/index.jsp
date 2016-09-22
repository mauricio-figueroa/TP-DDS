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
											Nombre
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

 <footer> P.O.I Todos los derechos reservados. 2016 ®</footer>
</body>

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


function log2(){
$.getJSON("http://localhost:8080/diseno-de-sistemas/crearUsuario?user="+$('#userid').val()+"pw="+ $('#psw').val() , function (data) {
     response($.map(data, function (item) {
         return {
           label: item.description,
           value: item.code
         };
     }));
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
				 //deberia hacer la magia dependiendo de lo q muestre.
				 for (var i = 0; i < dataReceived.length; i++) {
								 if(dataReceived.type="Banco"){
											//dataReceived[i].name;
											// dataReceived[i].icono;
											// dataReceived[i].servicios;
											// dataReceived[i].sucursal;
											var d = document.getElementById("testName");
											d.innerHTML += '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">' +dataReceived[i].name+'</p>';
											var e = document.getElementById("testInfo");
											// e.innerHTML += '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Icono:' +dataReceived[i].icono+
											// 												'<br /> Zona:'+dataReceived[i].sucursal;+ '<br /> Servicios:'+dataReceived[i].servicios;+ '
											// 													</p>';

										e.innerHTML += '<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Zona: Almagro </br> Calle: 123</p>';

								 };
				 }


			 });




		 //http://localhost:8080/diseno-de-sistemas/search-poi-from?searchName=parad&terminalNAME=terminal1

		};



 </script>



</html>


<!--

tipo banco =  [{"name":"unBanco","type":"Bank","range_of_atention":{"schedules":[{"hour_max":1474567200531,"hour_min":1474549200531}],"days_of_attention":[0,1,2,3,4,5,6]},"commune_radius":0.0,"number_bus_station":0}]
 console.log(hola[0]);

http://localhost:8080/diseno-de-sistemas/poi-addBank?name=unBanco&type=banco&mainStreet=Medrano&lat=1231&lon=324&services=melania,miranda

-->
