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
	  function postDetail() {
		  $.ajax({
			  url: "http://localhost:8080/Pawn/rest/feed/getDetail",
			  type: "GET",
			  contentType: "application/json"	  
			})
			  .done(function( data ) {
				if(data.code == 200){
					console.log(data);
				
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
		<div class="logo"><a href="#"><img class="logoImg" src="img/logo.svg" alt="dream-it logo"></a></div>
	</div>
	<!-- Contenedor Principal -->
	<div id="mainContainer">
		<!-- divs contenido -->
		<div class="contenido" id="feed">

			<div class="pleaBox">

				<div class="swiper-container">
					<div class="swiper-wrapper">

				        <!--Primer Slide-->
						<div class="swiper-slide">
							<img src="${pageContext.request.contextPath}/img/home/profile1-case.jpg" alt="">
						</div>

						<div class="swiper-slide"> 
							<img src="${pageContext.request.contextPath}/img/home/profile1-case.jpg" alt="">
						</div>

						<div class="swiper-slide"> 
							<img src="${pageContext.request.contextPath}/img/home/profile1-case.jpg" alt="">
				    	</div>
					</div>
				</div>

				
				<div class="itemHeader">
					
					<div class="headlineText">
						<span class="title">Cody necesita apoyo para combatir cancer de riñon.</span>
						<span class="needed">$ 1,200.00</span>
						<span><a href="#" class="btn btnDetalle btnFavs"><img src="${pageContext.request.contextPath}/img/secciones/favIcon.png" alt=""></a></span>
						<span><a href="#" class="btn btnDetalle btnMail"><img src="${pageContext.request.contextPath}/img/secciones/maiIcon.png" alt=""></a></span>
					</div>	


				</div>
				<div class="offer">
					<div class="contentOffer">
						<img src="${pageContext.request.contextPath}/img/home/profile1.png">
						<span> Ofrezco: Curso de elaboración de cupcakes con fondant.</span>
					</div>
					<div class="offerDesc">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nulla dolor, porta id sodales et, hendrerit in nisl. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam iaculis hendrerit ex a aliquam. Suspendisse dignissim vel enim a ultrices. Aliquam ac sem dui.</p>
					</div>
					<div class="actionButton">

						<a href="#" class="btn btnBlack "><img src="${pageContext.request.contextPath}/img/secciones/paypalIcon.png" alt="">  Apoyar y dejar huella.</a>
					</div>
				</div>
			</div>

		</div>



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