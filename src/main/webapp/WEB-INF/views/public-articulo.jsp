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
		<title>Game Over Magazine - Artículo</title>
		<meta name="description" content="Artículo">
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
		<script src="${pageContext.request.contextPath}/scripts/jquery.jscroll.min.js" type="text/javascript"></script>
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
		<div id="indicador-dmobils"></div>
		<div id="allinone">
	        <%@ include file="public-cabecera.jsp" %>
		    <div class="container">
			    <div id="article" class="col-md-8" lang="es">
	                <h2>${articulo.nombre}</h2>
	                <p class="autor"><img src="${variables.rutaImagenes}/avatar/${usuarioDetalles.avatar}"><span><a href="${pageContext.request.contextPath}/autor/${usuarioDetalles.idusuario}/">${usuarioDetalles.nombre}</a></span> | <span> <fmt:formatDate value="${articulo.fecha}" type="date" pattern="dd-MM-yyyy"/> </span> - <span> ${articulo.tipo.nombre} </span>|</p>
					<div id="botComp">
						<div class="col-sm-3 col-sm-offset-3 col-xs-6">
							<a class="fb-xfbml-parse-ignore btn btn-block btn-social btn-facebook" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u&amp;src=sdkpreparse"><span class="fab fa-facebook"></span>Compartir</a>
						</div>
						<div class="col-sm-3 col-xs-6">
							<a href="https://twitter.com/share" class="btn btn-block btn-social btn-twitter" data-lang="es" data-size="large" data-hashtags="GameOverMgz"><span class="fab fa-twitter"></span>Compartir</a>
						</div>	
					</div>
					<hr class="neteja">
	                <figure><img src="${variables.rutaImagenes}/articulos/articulo_${articulo.idarticulo}/${articulo.imagen}"></figure>
	                <section>
	                	${articulo.texto}
	                </section>
				</div><!--article-->
				<div id="fitxa" class="col-md-4 col-xs-12 panellGeneric">
					<h2>Ficha Técnica</h2>
					<div id="headFitxa">
						<div id="titFitxa">
							<h3 lang="en">Título producto</h3>
						</div>
					</div>
					<a href="#" id="botoPagOff" class="botofosc" target="_blank">Link</a>
					<p>Fecha salida</p>
					<p lang="en">Tags...</p>
					<p>Info...</p>
					<div id="rating">
					</div>
					<hr>
					<div id="caracteristiques">
						<div class="fila">
							<div class="columna">
								Caracteristica 1
							</div>
							<div class="columna">
								Caracteristica 2
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								Caracteristica 3
							</div>
							<div class="columna">
								Caracteristica 4
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								Caracteristica 5
							</div>
							<div class="columna">
								Caracteristica 6
							</div>
						</div>
					</div>
					<hr>
					<div id="pegi">
						<p>Info extra...</p>
					</div>
				</div>
			</div><!--container-->
		    <div class="bcktop">
				<a href="#allinone" title="Tornar al Top" class="page-scroll"></a>
			</div>
   			<div class="push"></div>
		</div>
   		<%@ include file="public-footer.jsp" %>
	</body>
	<script>
		$(document).ready(function(){
			if ($('#indicador-dmobils').is(':visible')) $("#fitxa").jScroll();
			$('a.page-scroll').on("click",function(){
			   $("html, body").animate({scrollTop: 0}, 500);
			});
		})	
	</script>
</html>