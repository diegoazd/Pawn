function isMobile(){
	if(navigator && navigator.userAgent && navigator.userAgent != null) {
		var strUserAgent = navigator.userAgent.toLowerCase(),
			arrMatches = strUserAgent.match(/(iphone|ipod|ipad|android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i);
		if(arrMatches) 
			return true;
	}
	return false;
}
//::::::::::::::::::::::::::::::::::::::::::::::::::::::://
// Función para activar los botones de scroll en el home //
//::::::::::::::::::::::::::::::::::::::::::::::::::::::://

function scrollTo(obj) {
	var mobile=isMobile();
	var tope = 0;
	if(mobile==false){
		tope = 50;
	}
	if($(obj).length){
		$(obj).click(function(){
			var id = $(this.hash);
			if(id.length){
				$('html, body').animate({
					scrollTop: id.offset().top - tope
				}, 1000);
				$(this).parent().addClass('current').siblings().removeClass('current');
				return false;
			}
		});
	}
}


//::::::::::::::::::::::::::::::::::::::::::::::::::::::://
//         Función para activar Swiper en el home        //
//::::::::::::::::::::::::::::::::::::::::::::::::::::::://

function swipeHome(swiper){
	var mySwiper = $(swiper).swiper({
		mode: 'horizontal',
		grabCursor: true,
		calculateHeight: true,
		calculateWidth: true,
		autoplay: 5000
		
	});
	$('.btnPrev').click(function(e) {
		e.preventDefault();
		mySwiper.swipePrev();
	});
	$('.btnNext').click(function(e) {
		e.preventDefault();
		mySwiper.swipeNext();
	});
}

//::::::::::::::::::::::::::::::::::::::::::::::::::::::://
//                 Envía el Formulario                   //
//::::::::::::::::::::::::::::::::::::::::::::::::::::::://
function sendForm(){
	// Recolectamos los campos del formulario
	var prop = $('select[name=propuesta]').val();
	var proy = $('select[name=proyecto]').val();
	var leMail = $('input[name=email]').val();
	var leName = $('input[name=name]').val();
	var formMessages = $('#form-messages');
	var formIcon = $('#form-icon');
	var formResponse = $('#form-response');
	//Iniciamos flag para envío | True = Envío / False = No envío
	var todoOk = true;

	//Revisamos que el correo esté bien escrito
	function validarEmail( email ) {
		expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if ( !expr.test(email) ){
			// Cambiamos flag
			todoOk=false;
			// Enviamos mensaje de errorssss
			$(formMessages).removeClass('success');
			$(formMessages).addClass('error');
			$(formIcon).addClass('icon-warn');
			$(formResponse).text('¡Enciendan las alarmas! ¿Puedes revisar tu dirección de correo? :D');
		}
	}

	validarEmail(leMail)
	//
	if(leName==''){
		todoOk=false;
		$(formMessages).removeClass('success');
		$(formMessages).addClass('error');
		$(formIcon).addClass('icon-warn');
		$(formResponse).text('Nos gusta conocer a nuestros clientes. ¿Puedes escribir tu nombre? ;)');
	}

	if( todoOk ){
		console.log("mando correo");
		//Construimos el correo usando HTML :)
		var leMessage = "<p>¡Hola! Hay una nueva solicitud de contacto en la página de DreamIT.</p><p>" + leName + " desea " + prop + " y compartir sus " + proy + " con nosotros.</p><p>Los datos del contacto son:</p><p>Nombre: " + leName + "</p><p>Email: " + leMail + "</p><p>Que tengas un gran día y no se te olvide contactar de vuelta. :D</p><p>Dream-IT</p>";
		// Conectamos con el servicio usando Ajax
		$.ajax({
			type: "POST",
			url: "https://mandrillapp.com/api/1.0/messages/send.json",
			data: {
				"key": "tKaypZ19VJBI0_OGARsjxg",
				"message": {
					"from_email": "contacto@dream-it.com.mx",
					"from_name": "Dream Web",
					"to": [
						{
							"email": "alejandro@dream-it.com.mx",
							"name": "Alejandro Flores",
							"type": "to"
						},
					],
					"autotext": "true",
					"subject": "Nuevo mensaje de contacto | dream-it.com.mx",
					"html": leMessage
				}
			},
			success: function (Result) {
				//Quitamos clases de error y las vestimos de OK! :D
				$(formMessages).removeClass('error');
				$(formMessages).addClass('success');
				$(formIcon).removeClass('icon-warn');
				$(formIcon).addClass('icon-like');
				// Mandamos mensajito de éxito
				$(formResponse).text('Chuck Approves! Ya enviamos tu mensaje, en breve te contactamos de vuelta. :D');
			},
			error: function (Request, Status, Error) {

				
				}
		});
		//End Ajax
	} else {
		// Si algo no salió bien, avisamos
		$(formMessages).removeClass('success');
		$(formMessages).addClass('error');
		$(formIcon).addClass('icon-warn');
		// Escribímos mensaje de error
		$(formResponse).text('Algo está incompleto. ¿Podrías revisar si tu correo está bien escrito, o si falta tu nombre? :D');
	}
}

//::::::::::::::::::::::::::::::::::::::::::::::::::::::://
//                     Carga del DOM                     //
//::::::::::::::::::::::::::::::::::::::::::::::::::::::://
$(document).ready(function(){
	// Iniciar ScrollTo
	scrollTo('.btnScroll a');

	$('#sendBtn').click(function(e){
		e.preventDefault();
		sendForm();
	});

});