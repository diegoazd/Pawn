<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
	<title>PAW-N</title>
	<!--css-->
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/styles.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/jquery.mmenu.css">
	<!--jquery & js-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery.mmenu.min.js"></script>
	
	<script type="text/javascript">
	  function postFeed() {
		  $.ajax({
			  url: "http://localhost:8080/Pawn/rest/feed/getall",
			  type: "GET",
			  contentType: "application/json"	  
			})
			  .done(function( data ) {
				if(data.code == 200){
					console.log(data.collection);
					var feedList = "";
					for(var i=0; i<data.collection.length;i++){
						
						var user = data.collection[i].user;
						var feed = data.collection[i].feed;
						
						feedList = feedList+"<div class='itemBox'>"+
						    "<div class='itemHeader'>"+
						    "<div class='profilePic'>"+
						    "<img alt='' src='data:image/png;base64,"+user.avatar+"' /></body>"+
						    "</div>"+
							"<div class='headlineText'>"+
							"<span class='title'>"+feed.title+"</span>"+
							"<div class='leUser'>"+user.username+"</div>"+
							"<div class='leDate'>"+feed.date+"</div>"+
							"</div>"+
							"<div class='petitionPic'>"+
							"<div class='actionBar'>"+
							"<div class='needed'>"+
							"<small>Necesitamos:</small>"+
							"<div class='amount'>"+feed.amountRequired+"</div>"+
							"</div>"+
							"<div class='doPaw'>"+
							"<a href='/Pawn/detalle.jsp'>"+
							"<img src='/Pawn/img/home/paw.png' alt='deja huella'> <span class='huella'>dejar huella> </span>"+
							"</a>"+
							"</div>"+
							"</div>"+
						    "<img alt='' src='data:image/png;base64,"+feed.imgPrincipal+"' />"+
							"</div>"+
							"</div>"+
						    "</div>";	
					}					
					$("#feed").html(feedList);
				  
				}  
			    
			  });	  
	  }	 
	</script>
	
</head>
<body>
	<!-- pantalla para no mobiles -->
	<div class="noMobile"><span class="noMobIcon icon-tool blue"></span><h1>PÃ¡gina ex-clu-si-va para smartphones. <br>No luce cool en desktop, ni en aiPad. :(</h1><p> Tip: Entra desde tu telÃ©fono. ;).</p></div>
	<!-- header y menÃº -->
	<div class="header">
		<a class="btnMenu" href="#menu">
			<span></span>
		</a>
		<div class="logo"><a href="#"><img class="logoImg" src="${pageContext.request.contextPath}/img/logo.svg" alt="dream-it logo"></a></div>
	</div>
	<!-- Contenedor Principal -->
	<div id="mainContainer">
		<!-- divs contenido -->
		<div class="contenido" id="feed">
			<%-- <div class="itemBox">
				<div class="itemHeader">
					<div class="profilePic">
						<img src="${pageContext.request.contextPath}/img/home/profile1.png">
					</div>
					<div class="headlineText">
						<span class="title">Cody necesita apoyo para combatir cancer de riÃ±on.</span>
						<div class="leUser">sofiAhimsa</div>
						<div class="leDate">28/09/2014</div>
					</div>
					<div class="petitionPic">
						<div class="actionBar">
							<div class="needed">
								<small>Necesitamos:</small>
								<div class="amount">$1,200.00</div>
							</div>
						</div>
						<img src="${pageContext.request.contextPath}/img/home/profile1-case.jpg" alt="">
					</div>
				</div>
			</div>

			<div class="itemBox">
				<div class="itemHeader">
					<div class="profilePic">
						<img src="${pageContext.request.contextPath}/img/home/profile1.png">
					</div>
					<div class="headlineText">
						<span class="title">Cody necesita apoyo para combatir cancer de riÃ±on.</span>
						<div class="leUser">sofiAhimsa</div>
						<div class="leDate">28/09/2014</div>
					</div>
					<div class="petitionPic">
						<div class="actionBar">
							<div class="needed">
								<small>Necesitamos:</small>
								<div class="amount">$1,200.00</div>
							</div>
						</div>
						<img src="${pageContext.request.contextPath}/img/home/profile1-case.jpg" alt="">
					</div>
				</div>
			</div>

			<div class="itemBox">
				<div class="itemHeader">
					<div class="profilePic">
						<img src="${pageContext.request.contextPath}/img/home/profile1.png">
					</div>
					<div class="headlineText">
						<span class="title">Cody necesita apoyo para combatir cancer de riÃ±on.</span>
						<div class="leUser">sofiAhimsa</div>
						<div class="leDate">28/09/2014</div>
					</div>
					<div class="petitionPic">
						<div class="actionBar">
							<div class="needed">
								<small>Necesitamos:</small>
								<div class="amount">$1,200.00</div>
							</div>
						</div>
						<img src="${pageContext.request.contextPath}/img/home/profile1-case.jpg" alt="">
					</div>
				</div>
			</div>

			<div class="itemBox">
				<div class="itemHeader">
					<div class="profilePic">
						<img src="${pageContext.request.contextPath}/img/home/profile1.png">
					</div>
					<div class="headlineText">
						<span class="title">Cody necesita apoyo para combatir cancer de riÃ±on.</span>
						<div class="leUser">sofiAhimsa</div>
						<div class="leDate">28/09/2014</div>
					</div>
					<div class="petitionPic">
						<div class="actionBar">
							<div class="needed">
								<small>Necesitamos:</small>
								<div class="amount">$1,200.00</div>
							</div>
						</div>
						<img src="${pageContext.request.contextPath}/img/home/profile1-case.jpg" alt="">
					</div>
				</div>
			</div>

			<div class="itemBox">
				<div class="itemHeader">
					<div class="profilePic">
						<img src="${pageContext.request.contextPath}/img/home/profile1.png">
					</div>
					<div class="headlineText">
						<span class="title">Cody necesita apoyo para combatir cancer de riÃ±on.</span>
						<div class="leUser">sofiAhimsa</div>
						<div class="leDate">28/09/2014</div>
					</div>
					<div class="petitionPic">
						<div class="actionBar">
							<div class="needed">
								<small>Necesitamos:</small>
								<div class="amount">$1,200.00</div>
							</div>
						</div>
						<img src="${pageContext.request.contextPath}/img/home/profile1-case.jpg" alt="">
					</div>
				</div>
			</div> --%>
		</div>



	<!-- fin contenedor principal -->
	</div>

	<!-- Navigation -->
	<nav id="menu">
		<ul>
			<li><a href="#">Deja Huella</a></li>
			<li class="sep"><a href="#">Crear PeticiÃ³n</a></li>
			<li class="sep"><a href="#">Adopta</a></li>
			<li class="sep"><a href="#">Crear Perfil AdopciÃ³n</a></li>
			<li class="sep"><a href="#">Mi Perfil</a></li>
		</ul>
	</nav>

	<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/scripts.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){		
			postFeed();
			$('#menu').mmenu();
		});	
	</script>
</body>
</html>