var terminal;

user1={};
user2={};
user3={};
users=[user1, user2, user3];
user1.lista=[];
user2.lista=[];
user3.lista=[];
id=0;



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
       console.log('Se aregó una terminal');
       terminal='terminalGabo1';
     });


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



   //SCRIPTS PARA ADMIN.JSP



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
