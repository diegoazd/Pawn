<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no"><title>Insert title here</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/styles.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/jquery.mmenu.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/idangerous.swiper.css">
	<!--jquery & js-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery.mmenu.min.js"></script>
	<title>PAW-N</title>
	
<script type="text/javascript">
	  function paypal_payment(){
		  $('#paypalPayment').submit();
		  
	  }	
	  function postDetail() {
		  $.ajax({
			  url: "http://localhost:8080/Pawn/rest/feed/getDetail",
			  type: "GET",
			  contentType: "application/json"	  
			})
			  .done(function( data ) {
				  var feedDetail = "";
				if(data.code == 200){
					console.log(data);
					var feed = data.single.feed;
					var user = data.single.user;
					
					feedDetail = "<div class='pleaBox'>"+
						"<div class='swiper-container'>"+
						"<div class='swiper-wrapper'>";
						for(var i=0;i<feed.image.length;i++){
							feedDetail = feedDetail +"<div class='swiper-slide'>"+
						    "<img alt='' src='data:image/png;base64,"+feed.image[i]+"' />"+
							"</div>";
						}
 						feedDetail= feedDetail + "<img alt='' src='data:image/png;base64,"+user.avatar+"' />"+
					    "</div>"+
					"</div>"+					
					"<div class='itemHeader'>"+
					"<div class='headlineText'>"+
					"<span class='title'>"+feed.title+"</span>"+
					"<span class='needed'>"+feed.amountRequired+"</span>"+
					"<span><a href=''#' class='btn btnDetalle btnFavs'><img src='/Pawn/img/secciones/favIcon.png' alt=''></a></span>"+
					"<span><a href=''#' class='btn btnDetalle btnMail'><img src='/Pawn/img/secciones/maiIcon.png' alt=''></a></span>"+
					"</div>"+	
					"</div>"+
					"<div class='offer'>"+
					"<div class='contentOffer'>"+
					"<img alt='' src='data:image/png;base64,"+user.avatar+"' />"+
					"<span> "+user.servicio+"</span>"+
					"</div>"+
					"<div class='offerDesc'>"+
					"<p>"+feed.description+"</p>"+
					"</div>"+
					"<div class='actionButton'>"+
		            "<form class='form' id='paypalPayment' action='ExpressCheckout' method='POST'>"+
		            "<input type='hidden' name='L_PAYMENTREQUEST_FIRSTNAME' value='Alegra'></input>"+
		            "<input type='hidden' name='L_PAYMENTREQUEST_LASTNAME' value='Valava'></input>"+
		            "<input type=\"hidden\" name=\"PAYMENTREQUEST_0_SHIPTOSTREET\" value=\"55 East 52nd Street\"></input>\n"+
		            "\t\t <input type=\"hidden\" name=\"PAYMENTREQUEST_0_SHIPTOSTREET2\" value=\"21st Floor\"></input>\n"+
		            "\t\t <input type=\"hidden\" name=\"PAYMENTREQUEST_0_SHIPTOCITY\" value=\"New York\" ></input>\n"+
		            "\t\t <input type=\"hidden\" name=\"PAYMENTREQUEST_0_SHIPTOSTATE\" value=\"NY\" ></input>\n"+
		            "\t\t <input type=\"hidden\" name=\"PAYMENTREQUEST_0_SHIPTOZIP\" value=\"10022\" ></input>\n"+
		            "\t\t <input type=\"hidden\" id=\"PAYMENTREQUEST_0_SHIPTOZIP\" name=\"PAYMENTREQUEST_0_SHIPTOCOUNTRY\" value=\"MX\"></input>\n"+
		            "\t\t <input type=\"hidden\" name=\"shipping_method\" id=\"shipping_method\" value=\"2.00\"></input>\n"+
		            "<input type=\"hidden\" name='PAYMENTREQUEST_0_AMT' value='300.00'></input>"+
		            "<input type=\"hidden\" name='PAYMENTREQUEST_0_ITEMAMT' value='300.00'></input>"+
		            "<input type=\"hidden\" name='L_PAYMENTREQUEST_0_QTY0' value='1'></input>"+
		            "<input type=\"hidden\" name='L_PAYMENTREQUEST_0_AMT0' value='300.00'></input>"+
		            "<input type=\"hidden\" name='L_PAYMENTREQUEST_0_NAME0' value='FIRSTitem'></input"+
		            "<input type=\"hidden\" name='L_PAYMENTREQUEST_0_NUMBER0' value='0'></input>"+
		            "\t\t <input type=\"hidden\" id=\"p_method_paypal_express\" value=\"paypal_express\" name=\"payment\" title=\"PayPal Express Check out\"></input>"+
					"<a href='#' class='btn btnBlack' onclick='paypal_payment()'><img src='/Pawn/img/secciones/paypalIcon.png' alt=''>  Apoyar y dejar huella." +
					//"<input type='submit' value='Apoyar y dejar huella' class='btn btnBlack' name='Confirm' src='/Pawn/img/secciones/paypalIcon.png' alt=''/>"+
					"</a>"+
					"</form>"+
					"</div>"+
					"</div>"+
				"</div>"+
				"</div>";				
					$("#feed").html(feedDetail);				
				}  
			    
			  });	  
	  }
	  

	</script>
</head>
<body>
	<div class="header">
		<a class="btnMenu" href="#menu">
			<span></span>
		</a>
		<div class="logo"><a href="#"><img class="logoImg" src="/Pawn/img/logo.svg" alt="dream-it logo"></a></div>
	</div>
	<!-- Contenedor Principal -->
	<div id="mainContainer">
		<!-- divs contenido -->
		<div class="contenido" id="feed">

	<!-- fin contenedor principal -->
	</div>

	<!-- Navigation -->
	<nav id="menu">
		<ul>
			<li><a href="#">Deja Huella</a></li>
			<li class="sep"><a href="#">Crear Petición</a></li>
			<li class="sep"><a href="#">Adopta</a></li>
			<li class="sep"><a href="#">Crear Perfil Adopción</a></li>
			<li class="sep"><a href="#">Mi Perfil</a></li>
		</ul>
	</nav>
	<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/idangerous.swiper-2.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/scripts.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			// Iniciar Swiper
			swipeHome('.swiper-container');
			//Iniciar Menu
			postDetail();
			$('#menu').mmenu();
		});	
	</script>
</body>
</html>