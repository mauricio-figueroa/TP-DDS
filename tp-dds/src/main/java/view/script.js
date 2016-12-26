var termimal;
var users;
var usuarios=[];


var remaining;

ejecutarControllers();

permisosObj = {};
permisosObj["1"] = "Buscar poi";
permisosObj["2"] = "Consultar cercanía";
permisosObj["3"] = "Consultar disponibilidad";
permisosObj["4"] = "Agregar Poi";
permisosObj["5"] = "Agregar Terminal";
permisosObj["6"] = "Modificar Poi";
permisosObj["7"] = "Remover Terminal";
permisosObj["8"] = "Remover Poi";


$(document).ready(function () {
    fitBackground();
});

$(window).resize(function () {
    fitBackground();
});

function openModal() {
    var wd = parseInt($(window).width());
    var wh = parseInt($(window).height());
    $('.modal-inner').css('margin-left', wd / 2 - 150 + 'px');
    $('.modal-inner').css('margin-top', wh / 2 - 60 + 'px');
    $('.modal').fadeIn();

}

function closeModal() {

    $('.modal').click(function () {
        $(this).fadeOut();
    })

}

function addInput() {
    var d = document.getElementById("content");
    d.innerHTML += '<br />	<input id="searchPoi" class="dataSearch" type="text" name="name" value="">';
}


function log() {


    url = "http://localhost:8080/diseno-de-sistemas/validarUsuario?user=" + $('#userid').val() + "&pw=" + $('#psw').val();

    localStorage["terminal"] = $('#userid').val();

    terminal = localStorage["terminal"];

//    console.log(userSelected);

    $.get(url, function (dataReceived) {

        if (dataReceived == 2) {
            // $(location).attr('href', 'file:///usr/local/Tomcat/work/TP-DDS/tp-dds/src/main/java/view/admin.jsp');
            $(location).attr('href', 'C:/Users/Mauricio/Desktop/TRABAJO-DISENIO/TP-DDS/tp-dds/src/main/java/view/admin.jsp');

            // $(location).attr('href', '/home/gabrieldyck/diseñoDeSistemas/TPIntegrador/TP-DDS/tp-dds/src/main/java/view/admin.jsp');

        } else if (dataReceived == 1) {
            // $(location).attr('href', 'file:///usr/local/Tomcat/work/TP-DDS/tp-dds/src/main/java/view/terminal.jsp');
            $(location).attr('href', 'C:/Users/Mauricio/Desktop/TRABAJO-DISENIO/TP-DDS/tp-dds/src/main/java/view/terminal.jsp');

            // $(location).attr('href', '/home/gabrieldyck/diseñoDeSistemas/TPIntegrador/TP-DDS/tp-dds/src/main/java/view/terminal.jsp');

        } else {
            openModal();
        }
        ;


    });

}


function search() {


    $("#testName").empty();
    $("#testInfo").empty();


    infoArray = [];
    $('.dataSearch').each(function () {
        if ($(this).val() != "") {
            infoArray.push($(this).val());
        }
    });

    console.log(infoArray);

//var url='http://localhost:8080/diseno-de-sistemas/poi-show';
    var searchName = infoArray.join(",");

    var url = 'http://localhost:8080/diseno-de-sistemas/search-poi-from?searchName=' + searchName + '&terminalName=' + localStorage["terminal"];


    console.log(url);
    $.get(url, function (dataReceived) {


        for (var i = 0; i < dataReceived.length; i++) {

            switch (dataReceived[i].type) {
                case "CGP":

                    $("#testName").append("<br /> <img class='img-poi' src='img/" + dataReceived[i].icon + ".png' alt='' />");
                    $("#testInfo").append('<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Dirección:' + dataReceived[i].direccion +
                        '<br /> Zona:' + dataReceived[i].zone + '<br /> Servicios:' + dataReceived[i].cgp_services + '</p>');

                    break;//1

                case "Bank":
                    var x = Math.floor((Math.random() * 4) + 1);
                    $("#testName").append('<br /><img class="img-poi" src="img/' + dataReceived[i].icon + '-' + x + '.png" alt="" />');
                    $("#testInfo").append('<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Dirección:' + dataReceived[i].direccion +
                        '<br /> Zona:' + dataReceived[i].zone + '<br /> Servicios:' + dataReceived[i].bank_services + '</p>');
                    break;//2

                case "BusStation":
                    var x = Math.floor((Math.random() * 4) + 1);
                    $("#testName").append('<br /><img class="img-poi" src="img/' + dataReceived[i].icon + '-' + x + '.png" alt="" />');
                    $("#testInfo").append('<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Número de línea:' + dataReceived[i].number_line + '</p>');
                    break;//3

                case "ComercialShop":
                    var x = Math.floor((Math.random() * 3) + 1);
                    $("#testName").append('<br /><img class="img-poi" src="img/' + dataReceived[i].icon + '-' + x + '.png" alt="" />');
                    $("#testInfo").append('<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">Dirección:' + dataReceived[i].direccion +
                        '<br /> Nombre:' + dataReceived[i].name + '<br /> Rubro:' + dataReceived[i].activity + '</p>');
                    break;//4

                default:

                    $("#testName").append('<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12">No hay información</p>');
                    $("#testInfo").append('<br />	<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> No hay información</p>');


            }
            ; //termina switch
        }


    });//termina get
    //termina for


};


function ejecutarControllers() {

    $.get('http://localhost:8080/diseno-de-sistemas/terminal-add?name=terminalGabi&password=1231&lat=-34.638800&lon=-58.393426&action=ADDTERMINAL', function (dataReceived) {
        console.log('Se agregó una terminal');
    });

    $.get('http://localhost:8080/diseno-de-sistemas/terminal-add?name=terminalMel&password=123&lat=-34.638800&lon=-58.393426&action=ADDTERMINAL', function (dataReceived) {
        console.log('Se agregó una terminal');
    });

    $.get('http://localhost:8080/diseno-de-sistemas/terminal-add?name=terminalMau&password=123&lat=-34.638800&lon=-58.393426&action=ADDTERMINAL', function (dataReceived) {
        console.log('Se agregó una terminal');
    });


    $.get('http://localhost:8080/diseno-de-sistemas/poi-addBusStation?name=114Parada&type=busStation&mainStreet=Avenida Siempre Viva%201231&lat=-34.638473&lon=-58.391618&busNumber=114',
        function (dataReceived) {
            console.log('Se agregó un BusStation');
        });


    $.get('http://localhost:8080/diseno-de-sistemas/poi-addCGP?name=CGPGab0&type=CGP&mainStreet=Avenida Siempre Viva%201231&lat=-34.638473&lon=-58.391618&communeRadius=%2043.56', function (dataReceived) {
        console.log('Se agregó un CGP');
    });


    $.get('http://localhost:8080/diseno-de-sistemas/poi-addComercial?name=gab0Library&type=library&mainStreet=Avenida Siempre Viva%201231&lat=-34.638473&lon=-58.391618&rubro=Library&maxDistance=500', function (dataReceived) {
        console.log('Se agregó un Local Comercial');
    });


    $.get('http://localhost:8080/diseno-de-sistemas/poi-addBank?name=bank1&type=bank&mainStreet=Avenida Siempre Viva%201231&lat=-34.638473&lon=-58.391618&services=aa', function (dataReceived) {
        console.log('Se agregó un Banco');
    });


    $.get('http://localhost:8080/diseno-de-sistemas/crearUsuario?user=mauri&pw=mauri', function (dataReceived) {
        console.log("Se creo un usuario administrador: User-> mauri, Psw -> mauri");
    });


    //
    // $.get('', function(dataReceived){console.log(dataReceived);});
    //
    // $.get('', function(dataReceived){console.log(dataReceived);});

}


//SCRIPTS PARA ADMIN.JSP


function displayHistorial() {
    $('.button-admin').hide();
    $('#acciones').show();
    $('.volver').show();

}

function displayAcciones() {

    $('.button-admin').hide();
    $('#historial').show();
    $('.volver').show();
}

function volver() {
    $('.button-admin').show();
    $('#acciones').hide();
    $('#historial').hide();
    $('.volver').hide();
}


///historial

function searchHistorial() {

    $("#fecha").empty();
    $("#palabra_buscada").empty();
    $("#cant_pois").empty();
    $("#usuarioH").empty();


    if ($("#nameUser").val() && !$("#initFecha").val() && !$("#finFecha").val()) {

        var url = 'http://localhost:8080/diseno-de-sistemas/reportePorNombreTerminal?name=' + $("#nameUser").val();

    } else if ($("#nameUser").val() && $("#initFecha").val() && !$("#finFecha").val()) {

        var url = 'http://localhost:8080/diseno-de-sistemas/reportePorNombreTerminal?name=' + $("#nameUser").val() + '&desde=' + $("#initFecha").val();

        if (!checkDateFormat($("#initFecha").val())) {
            alert("datos mal ingresados");
            return;
        }


    } else if ($("#nameUser").val() && $("#initFecha").val() && $("#finFecha").val()) {

        var url = 'http://localhost:8080/diseno-de-sistemas/reportePorNombreTerminal?name=' + $("#nameUser").val() + '&desde=' + $("#initFecha").val() + '&hasta=' + $("#finFecha").val();
        if (!checkDateFormat($("#initFecha").val()) && !checkDateFormat($("#finFecha").val())) {
            alert("datos mal ingresados");
            return;
        }


    } else if (!$("#nameUser").val() && $("#initFecha").val() && $("#finFecha").val()) {

        var url = 'http://localhost:8080/diseno-de-sistemas/reportePorNombreTerminal?desde=' + $("#initFecha").val() + '&hasta=' + $("#finFecha").val();
        //  var 	url='http://localhost:8080/diseno-de-sistemas/reportePorNombreTerminal?name='+terminal+'&desde='+$( "#initFecha"  ).val()+'&hasta='+$( "#finFecha" ).val();

        if (!checkDateFormat($("#initFecha").val()) && !checkDateFormat($("#finFecha").val())) {
            alert("datos mal ingresados");
            return;
        }


    } else if ($("#nameUser").val() && !$("#initFecha").val() && $("#finFecha").val()) {

        var url = 'http://localhost:8080/diseno-de-sistemas/reportePorNombreTerminal?name=' + $("#nameUser").val() + '&hasta=' + $("#finFecha").val();

        if (!checkDateFormat($("#finFecha").val())) {
            alert("datos mal ingresados");
            return;
        }

    } else {
        alert("Datos ingresados incorrectamente");
    }
    ;

    //	url='http://localhost:8080/diseno-de-sistemas/search-poi-from?searchName='+$( "#nameUser" ).text()+'&terminalName='+terminal;

    console.log(url);

    $.get(url, function (dataReceived) {
        console.log(dataReceived);

        for (var i = 0; i < dataReceived.length; i++) {

            $("#fecha").append('<p> ' + dataReceived[i].date + '<p/>');
            $("#palabra_buscada").append('<p class="poiToShow">' + dataReceived[i].palabraBuscada + '<p/>');
            $("#cant_pois").append('<p >' + dataReceived[i].cantPoisEncontrados + '<p/>');
            $("#usuarioH").append('<p >' + dataReceived[i].user + '<p/>');

            mostrarPoi();
        }


    });


};



function addPermiso() {
    if (typeof userSelected == "undefined") {
        alert("Debe elegir un usuario");
        return;
    }


    var id = $("#listPermisos option:selected").text();

    console.log(id);


    if (jQuery.inArray(id, users[userSelected].lista) != -1) {

        alert("Ya existe dicho permiso");
    } else {

        $("#permisos").append(' </br><p id="' + id + '" class="permisoAdded">' + $("#listPermisos option:selected").text() + '</p><button id="deletePermiso" type="button" name="button" onclick="eliminarPermiso(' + id + ')"> Eliminar</button>');

        var accion=[];
        accion.action=id;
        users[userSelected].actions.push(accion);



        remaining.push(id);

    }


}


function eliminarPermiso(target) {

    console.log(target);

    $('#permisos > #' + target).empty();

    //var ind = target.toString();

    var index = users[userSelected].lista.indexOf(target);

    //console.log(ind);

    users[userSelected].lista.splice(index, 1);

    console.log(users[userSelected].lista);


}

function cerrarSesion() {
    $(location).attr('href', '/Users/Mauricio/Desktop/TRABAJO-DISENIO/TP-DDS/tp-dds/src/main/java/view/index.jsp');

    //  $(location).attr('href', 'file:///usr/local/Tomcat/work/TP-DDS/tp-dds/src/main/java/view/index.jsp');
}

function cancel() {

    for (var i = 0; i <= remaining.length; i++) {
        var ind = users[userSelected].lista.indexOf(remaining[i]);
        users[userSelected].lista.splice(1, ind);

    }
    $('#permisos').empty();


    // volver();


}

function Ok() {

    $('#permisos').empty();
    var fulltext="";
    var sizeActions=  users[userSelected].actions.length;
    var i;
    var text;
    var res='';


    for(i=0;i<sizeActions;i++){
        text=users[userSelected].actions[i].action;
        res=res+fulltext.concat(text);
        if(i!=sizeActions-1){
           res=res+ fulltext.concat(",")
        }
    }


    var urltoPost = 'http://localhost:8080/diseno-de-sistemas/addActionToUser?adminName='+localStorage["terminal"]+"&user="+users[userSelected].nombre+"&actions="+res;

        console.log(urltoPost);

    $.get(urltoPost, function (dataReceived) {
        })



}

function selectUser() {

    $('#permisos').empty();

    var id = $("#usuarios option:selected").attr('id');
    userSelected = id;


    for (var i = 0; i < users[id].actions.length; i++) {
        var idn = users[userSelected].actions[i].action;
        agregarGraficaPermiso(idn, "#usuarios", "#permisos");

    }



}

function agregarGraficaPermiso(id, pathOuter, pathInner) {

    var ar = $(pathOuter + " option:selected").attr('id');

    $(pathInner).append(' </br><p id="' + ar + '" class="permisoAdded">' + id + '</p><button id="deletePermiso" type="button" name="button" onclick="eliminarPermiso(' + id + ')"> Eliminar</button>');

}



function initialize() {
    users = [];
    remaining = [];

    cargarPermisos();
    cargarUsuarios();

    console.log(usuarios);
    for (var i = 0; i < users.length; i++) {
        users[i] = {};
        users[i].lista = [];
    }
}


function mostrarPoi() {


    $(".poiToShow").click(function () {

        $("#detallePoi").empty();


        var url = 'http://localhost:8080/diseno-de-sistemas/search-poi-from?searchName=' + $(this).text() + '&terminalName=' + terminal;
        console.log(url);
        $.get(url, function (dataReceived) {

//console.log(dataReceived);
            // for (var i = 0; i < dataReceived.length; i++) {

            $(".detallePoi").append('<br /><img style="height:80px; width80px" src="' + dataReceived[0].icon + '" />');

            //}


        });//termina get

    });


}
function checkDateFormat(string) {
    if (string[2] == '/' && string[5] == '/' && string.length == 10 && $.isNumeric(string[0]) && $.isNumeric(string[1]) && $.isNumeric(string[3]) && $.isNumeric(string[4]) && $.isNumeric(string[6]) && $.isNumeric(string[7]) && $.isNumeric(string[8]) && $.isNumeric(string[9])) {
        return true;
    } else {
        return false;
    }

}


function fitBackground() {
    var img = new Image;
    try {
        img.src = $('.bg-fit').css('background-image').replace("url(", "").replace(")", "").replace("\"", "").replace("\"", "");
    } catch (err) {

    }
    $(img).load(function () {
        var bgWidth = img.width;
        var bgHeight = img.height;


        var wdHeight = $(window).height();
        var wdWidth = $(window).width();
        var docHeight = $(document).height();
        var docWidth = $(document).width();

        if ((wdWidth - wdHeight) > 0) {


            if ((wdWidth - wdHeight) > (bgWidth - bgHeight)) {


                if (docHeight > wdHeight) {

                    if (bgHeight < wdHeight) {
                        $(".bg-fit").css("background-size", "auto 100%  ");

                    } else {
                        $(".bg-fit").css("background-size", "100% auto ");

                    }
                } else {
                    $(".bg-fit").css("background-size", "100% auto");

                }
                ;


            } else {


                if (docHeight > wdHeight) {

                    if (bgHeight < wdHeight) {
                        $(".bg-fit").css("background-size", "auto 100%  ");

                    } else {
                        $(".bg-fit").css("background-size", "100% auto ");

                    }


                } else {

                    if (docHeight > bgHeight) {
                        $(".bg-fit").css("background-size", "auto  100% ");

                    } else {
                        if (bgHeight < wdHeight) {
                            $(".bg-fit").css("background-size", "auto 100%  ");

                        } else {
                            $(".bg-fit").css("background-size", "130% auto ");


                        }

                    }
                    ;

                }
                ;
            }
            ;

//  ELSE QUE CALCULA EL ALTO


        } else {


            if ((wdHeight - wdWidth) > (bgHeight - bgWidth)) {


                $(".bg-fit").css("background-size", " auto 100%");

            } else {


                $(".bg-fit").css("background-size", "100%  auto");

            }
            ;

        }

    });


};


function cargarUsuarios() {
    var url = "http://localhost:8080/diseno-de-sistemas/get-users";
    var i;

    $.get(url, function (dataReceived) {

        console.log(dataReceived);

        for (i = 0; i < dataReceived.length; i++) {

            $("#usuarios").append('<option  id="' + i + '" value="' + (i + 1) + '">' + dataReceived[i].nombre + '</option>');


            users.push(dataReceived[i]);
        }
    });
}

function cargarPermisos() {

    var url = "http://localhost:8080/diseno-de-sistemas/get-actions";

    $.get(url, function (dataReceived) {

        console.log(dataReceived);

        for (var i = 0; i < dataReceived.length; i++) {

            $("#listPermisos").append('<option  id="' + (i + 1) + '"  value="' + (i + 1) + '">' + dataReceived[i] + '</option>');

        }


    });

}
