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
		<title>Game Over Magazine - Accesibilidad</title>
		<meta name="description" content="Artículo">
		<meta name="robots" content="noindex nofollow">
		<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
		<script src="https://kit.fontawesome.com/19d4302e93.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/bootstrap.min.js" type="text/javascript"></script>
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
		<div id="allinone">
	        <%@ include file="public-cabecera.jsp" %>
	        <div class="container">
				<div id="accessibilitat">
					<section id="accessArt">
						<h2>El compromís de <cite lang="en">Game Over Magazine</cite> per l'accessibilitat</h2>
						<p>El web <cite lang="en">Game Over Magazine</cite> s'ha construït amb l'ànim de facilitar l'accés universal a totes les persones que el consulten independentment de les seves capacitats, físiques, sensorials o intel·lectuals, i del context tècnic d'ús amb què ho fan (tipus de dispositiu, programari, velocitat de la connexió, condicions ambientals, etc.). No obstant això només ha aconseguit un nivell AA en la pàgina principal per falta de tems, si aquest lloc web tingués finalitats comercials, ens comprometem a cumplir la normativa vigent en totes les pàgines del nostre lloc web.</p>
						<div>
							<div class="col-sm-8 col-xs-12 compromis">
								<p>Per això, es treballa amb l'objectiu d'adaptar tot el web a les recomanacions del <a href="http://www.w3.org/" target="_blank">Consorci World Wide Web W3C</a> per tal d'eliminar les barreres que dificulten l'accés a la informació i la comunicació. Aquestes recomanacions es concreten en les pautes d'accessibilitat del web <strong>WCAG 2.0</strong>. Aquestes pautes <a href="http://www.w3.org/TR/WCAG/" target="_blank">WCAG 2.0</a> es referencien a la <a href="http://administracionelectronica.gob.es/pae_Home/pae_Estrategias/pae_Accesibilidad/pae_normativa/pae_eInclusion_Normas_Accesibilidad.html" target="_blank">Norma UNE  139803:2012</a>, que actualitza i substitueix l’anterior, de 2004, que feia referència a les normes WCAG 1.0.</p>
								<p>El compromís de Game Over Magazine és arribar a complir el nivell de conformitat ‘Doble-A’ (AA), tal com les legislacions europea, espanyola (Reial decret 1494/2007, de 12 de novembre) i catalana (Llei 13/2014, del 30 d'octubre, d’accessibilitat), exigeixen a les administracions públiques.</p>
							</div>
							<div class="col-sm-4 col-xs-12 desc">
								<h3>Destaquem</h3>
								<a target="_blank" href="http://www.boe.es/buscar/doc.php?id=BOE-A-2007-19968"><span class="fa fa-arrow-circle-o-down" aria-hidden="true"></span> Reial decret 1494/2007, de 12 de novembre</a>
								<a target="_blank" href="http://portaldogc.gencat.cat/utilsEADOP/PDF/6742/1379017.pdf"><span class="fa fa-arrow-circle-o-down" aria-hidden="true"></span> Llei 13/2014, del 30 d'octubre, d'accessibilitat</a>
							</div>
						</div>
					</section>
				</div><!--accessibilitat-->
			</div><!--container-->
 			<div class="push"></div>
		</div>
 		<%@ include file="public-footer.jsp" %>
	</body>
</html>