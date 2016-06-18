/*************************************************************************
 *
 * @description
 * The following code manage the triggering event handlers and call via AJAX the
 * functions of the API through http protocol.
 * The getCalendario function load the html of the record calendar page
 * The getExpediente function load the html of the record page
 * @author
 * Jorge de Castro
 *
 *************************************************************************/

$(document).ready(function() {

    var elementos = {
        expediente: "",
        horario: ""
    };

    $('#bodyExpediente').hide();

    $(document).on('click', '#botonExpediente', function() {
        $('#tituloCabecera').text("Tu expediente");
        $('#subtituloCabecera').text("Expediente");
        $('#descripcionSubtitulo').text("Tu expediente muestra el conjunto de asignaturas que conforma tu plan de estudios con las notas que has ido sacando.");


        getExpediente();
    });

    $(document).on('click', '#botonCalendario', function() {
        $('#tituloCabecera').text("Tu horario académico");
        $('#subtituloCabecera').text("Horario");
        $('#descripcionSubtitulo').text("Tu horario académico te muestra las asignaturas en las que estás matriculado este trimestre.");


        getCalendario();
    });


    $(document).on('click', '#submitLogin', function() {

        if (document.getElementById('ui_login_remember').checked) {
            alert("checked");
            var inputUsername = $('#ui_login_username').val();
            var inputPassword = $('#ui_login_password').val();

            localStorage.setItem("username", inputUsername);
            localStorage.setItem("password", inputPassword);

            localStorage.setItem("sessionUsername", inputUsername);
            localStorage.setItem("sessionPassword", inputPassword);

            getExpediente();


        } else {
            var inputUsername = $('#ui_login_username').val();
            var inputPassword = $('#ui_login_password').val();
            localStorage.setItem("sessionUsername", inputUsername);
            localStorage.setItem("sessionPassword", inputPassword);

            getExpediente();
        }
    });

    $(document).ajaxStart(function() {
        $("#loading").show();
    });

    $(document).ajaxStop(function() {
        $('#loading').hide(); // hide loading indicator
    });

    function getCalendario() {

        $('#bodyLogin').hide();
        $('#bodyExpediente').show();
        $('#scroller').empty();

        if (elementos.horario == "") {
            $.ajax({
                type: 'GET',
                url: 'http://192.168.1.37:8080/horario',
                data: {
                    username: localStorage.getItem("sessionUsername"),
                    password: localStorage.getItem("sessionPassword")
                }, //Especifica los datos que se enviarán al servidor
                async: true, //Cuidado con el true! esto es asíncrono puede generar problemas con otros fragmentos de código. Hace que el código se ejecute de manera concurrente
                beforeSend: function(xhr) {
                    if (xhr && xhr.overrideMimeType) {
                        xhr.overrideMimeType('application/json;charset=utf-8');
                    }
                },
                dataType: 'html',
                /*contentType: "text/html;charset=UTF-8",*/
                success: function(data, status) {

                    //Do stuff with the JSON data
                    if (status == "success") {
                        /*$('iframe').html(data);*/
                        elementos.horario = data;
                        $('#scroller').html(elementos.horario);
                    }
                },
                error: function(status) {
                    /*$('iframe').contents().find('body').html("Error con el servidor de datos");*/
                    $('#scroller').html(data);
                }
            });
        } else {
            $('#scroller').html(elementos.horario);
        }
    }

    function getExpediente() {

        $('#bodyLogin').hide();
        $('#bodyExpediente').show();
        $('#scroller').empty();

        if (elementos.expediente == "") {
            $.ajax({
                type: 'GET',
                url: 'http://192.168.1.37:8080/expedientePlano',
                data: {
                    username: localStorage.getItem("sessionUsername"),
                    password: localStorage.getItem("sessionPassword")
                }, //Especifica los datos que se enviarán al servidor
                async: true, //Cuidado con el true! esto es asíncrono puede generar problemas con otros fragmentos de código. Hace que el código se ejecute de manera concurrente
                beforeSend: function(xhr) {
                    if (xhr && xhr.overrideMimeType) {
                        xhr.overrideMimeType('application/json;charset=utf-8');
                    }
                },
                dataType: 'html',
                /*contentType: "text/html;charset=UTF-8",*/
                success: function(data, status) {

                    //Do stuff with the JSON data
                    if (status == "success") {
                        /*$('iframe').html(data);*/
                        elementos.expediente = data;
                        $('#scroller').html(elementos.expediente);
                    }
                },
                error: function(status) {
                    /*$('iframe').contents().find('body').html("Error con el servidor de datos");*/
                    $('#scroller').html(data);
                }
            });
        } else {
            $('#scroller').html(elementos.expediente);
        }
    }

});
