<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Game Over Magazine - Inicio</title>
		<meta name="description" content="Página principal de Game Over Magazine">
		<meta name="robots" content="noindex nofollow">
		<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
		<script src="https://kit.fontawesome.com/19d4302e93.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/scripts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/scripts/ie10-viewport-bug-workaround.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/scripts/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/slick.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery.custom-scrollbar.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery.matchHeight.min.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Gafata' rel='stylesheet' type='text/css'>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css"/>
		<link href="${pageContext.request.contextPath}/css/jquery.custom-scrollbar.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/slick.css" rel="stylesheet" type="text/css">
		<meta name="_csrf" content="${_csrf.token}"/>
    	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	</head>
	<body>
        <%@ include file="public-cabecera.jsp" %>
	    <div class="container">
			<div class="col-sm-8 col-xs-12">
                <section id="noticies" class="col-xs-12">
					<h2>Últimas notícias</h2>
					<div id="not-top" class="col-xs-12">
						<div>
							<a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[0].nombre_opt}/"><img src="${variables.rutaImagenes}/articulos/articulo_${ultimasNoticias[0].idarticulo}/${ultimasNoticias[0].imagen}"></a>
						</div>
						<a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[0].nombre_opt}/"><h3>${ultimasNoticias[0].nombre}</h3></a>
					</div>
					<div id="not-sec" class="col-xs-6">
						<div>
							<a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[1].nombre_opt}/"><img src="${variables.rutaImagenes}/articulos/articulo_${ultimasNoticias[1].idarticulo}/${ultimasNoticias[1].imagen}"></a>
						</div>
						<a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[1].nombre_opt}/"><h4>${ultimasNoticias[1].nombre}</h4></a>
					</div>
					<div id="not-ter" class="col-xs-6">
						<div>
							<a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[2].nombre_opt}/"><img src="${variables.rutaImagenes}/articulos/articulo_${ultimasNoticias[2].idarticulo}/${ultimasNoticias[2].imagen}"></a>
						</div>
						<a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[2].nombre_opt}/"><h4>${ultimasNoticias[2].nombre}</h4></a>
					</div>
					<div id="not-llista" class="col-xs-12">
						<ul>
							<li><a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[3].nombre_opt}">${ultimasNoticias[3].nombre}</a></li>
							<li><a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[4].nombre_opt}">${ultimasNoticias[4].nombre}</a></li>
							<li><a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[5].nombre_opt}">${ultimasNoticias[5].nombre}</a></li>
							<li><a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[6].nombre_opt}">${ultimasNoticias[6].nombre}</a></li>
							<li><a href="${pageContext.request.contextPath}/articulo/${ultimasNoticias[7].nombre_opt}">${ultimasNoticias[7].nombre}</a></li>
						</ul>
					</div>
					<div class="peu-seccio col-xs-12">
						<a href="${pageContext.request.contextPath}/tipo/noticias"><i class="fa fa-plus" aria-hidden="true"></i> Noticias</a>
					</div>
				</section>
                <section id="panel-videos" class="col-xs-12">
					<h2>Vídeos más recientes</h2>
					<div class="col-sm-2 col-md-4 col-xs-12 scrollbar fila">
						<ul>
							<c:forEach var="video" items="${videos}">
								<li data-link="${video.link}" onclick="canviaActiu(this)">
									<img src="${variables.rutaImagenes}/videos/${video.thumbnail}" alt="${video.nombre}">
									<p>${video.nombre}</p>
									<span class="small">${video.tipo}</span>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="col-sm-10 col-md-8 col-xs-12 fila nopadds">
						<div id="video" class="hs-responsive-embed-youtube">
							<iframe class="video" src="${videos[0].link}" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						</div>
					</div>
					<div class="peu-seccio col-xs-12">
						<a href="${pageContext.request.contextPath}/videos"><i class="fa fa-plus" aria-hidden="true"></i> Vídeos</a>
					</div>
				</section> <!--panel-videos-->
    		</div> <!--col esquerra-->
	    	<div class="col-sm-3 col-sm-offset-1 col-xs-12"><!--col dreta-->
                <section id="series" class="col-xs-12">
					<h2>Estrenos</h2>
					<ul>
						<c:forEach var="estreno" items="${estrenos}">
							<li>
								<img src="${variables.rutaImagenes}/estrenos/${estreno.thumbnail}">
								<p>${estreno.nombre}</p>
								<span class="small">Temporada ${estreno.temporada}</span>
								<span class="small">- ${estreno.plataforma} -</span>
								<span class="small"><fmt:formatDate value="${estreno.fecha}" type="date" pattern="dd-MM-yyyy"/></span>
							</li>
						</c:forEach>
					</ul>
				</section> <!--Top guies-->
                <section id="manga" class="col-xs-12">
					<h2>Novedades</h2>
					<ul>
						<c:forEach var="novedad" items="${novedades}">
							<li>
								<p><a href="${pageContext.request.contextPath}/articulo/${novedad.nombre_opt}">${novedad.nombre}</a></p>
							</li>
						</c:forEach>
					</ul>
				</section>
				<section id="newsletter" class="col-xs-12">
					<h2>Newsletter</h2>
					<form class="form-inline form-news" onsubmit="return verificaFormulari(this)" action="javascript:noDisponible()" method="post" id="formulari">
						<label for="correu" class="hidden"></label>
	                    <input type="email" id="correu" class="form-control" name="correu" placeholder="Correu Electrònic" onblur="verificaCorreu(this)">
                        <button type="submit" class="btn btn-pers">Enviar <span class="glyphicon glyphicon-send"></span></button>
                    </form>
				</section>
		    </div><!--col dreta-->
		</div> <!--container-->
        <div class="neteja">
	</div>	
    <%@ include file="public-footer.jsp" %>
	</body>
	<script>
		$(document).ready(function(){			
			$(".car-art-img").each(function(index) {
				$(this).css('background-image', 'url('+ $(this).attr("data-image")+')');
			});
			
			$("#panel-videos li:first").addClass("vActive");
			
			$("#panel-videos li").on("click",function(){
				$('.vActive').removeClass('vActive');
				$(this).addClass("vActive");
				$("#video iframe").attr("src",$(this).attr("data-link"));	
			});
			
			$('.carousel').slick({
				autoplay: true,
				arrows: false
			});
			
			$('.fila').matchHeight({
				target: $('.video')
			});
			
			if($(window).width() >= 768){
				$(".scrollbar").customScrollbar({
					skin: "modern-skin",
					updateOnWindowResize: true
				});
			} else {
				$(".scrollbar").customScrollbar({
					skin: "modern-skin",
					updateOnWindowResize: true,
					hScroll: true,
					vScroll: false
				});
			};
			$(window).resize(function(){
				if( $(this).width() >= 768 ) {
					$(".scrollbar").customScrollbar({
						skin: "modern-skin",
						updateOnWindowResize: true
					});
				} else {
					$(".scrollbar").customScrollbar({
						skin: "modern-skin",
						hScroll: false,
						vScroll: false
					});
				};
			});
		});
	</script>
</html>