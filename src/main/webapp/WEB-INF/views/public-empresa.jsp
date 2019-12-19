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
		<title>Game Over Magazine - Acerca de nosotros</title>
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
				<div id="empresa">
					<section id="quisom">
						<h2>Qui sóm?</h2>
						<figure>
							<img src="${pageContext.request.contextPath}/img/seugo.jpg" alt="Fotorgrafia de la seu de Game Over Magazine" class="img-responsive">
						</figure>
						<p><cite lang="en">Game Over Magazine</cite> neix a partir del projecte final del curs de disseny i publicació de pàgines web impartit per la <a href="http://www.balmesinnova.com/" target="_blank">Balmes Innova</a> del <a href="http://www.jaumebalmes.com/es/" target="_blank">Centre d'Estudis Jaume Balmes</a>. L'objectiu principal era incloure en un mateix lloc web tota l'actualitat del món dels jocs en general, això incloïa videojocs de totes les plataformes, els anomenats <span lang="en">Wargames</span>, des dels més comercials com els de <cite lang="en">Games Workshop</cite> o <cite lang="en">Corvus Belli</cite>, als menys coneguts com ara <cite lang="en">Black Powder</cite>, qualsevol tipus de jocs de taula i el joc de cartes col·leccionables <cite lang="en">Magic : The Gathering</cite>. Sabem que és un món pràcticament infinit i que cada dia s'urgeixen empreses noves intentant fer-se un lloc al mercat, com en el cas dels <abbr title="Trading Card Games">TGC</abbr> ho estan intentant <cite>Guerra de Mitos</cite> o <cite lang="en">Force of Will</cite>, per això aquest només és el punt de partida i esperem que els continguts del lloc Web segueixin creixent a mida amb la qual augmenta el mercat. Tanmateix volem, en un futur, incloure un apartat per als Jocs de Rol, que després d'una època dura, sembla que tornen amb noves iniciatives de <span lang="en">Crowfunding</span>, noves distribuïdores i nous materials i campanyes per als jocs clàssics.</p>
					</section>
					<section id="pagament">
						<h2>Servei gratuït?</h2>
						<figure>
							<img src="${pageContext.request.contextPath}/img/free.png" class="img-responsive" alt="Imatge es gratis">
						</figure>
						<p>Ara per ara tots els continguts disponibles a la web són gratuïts i el registre al lloc web és completament opcional i ens serveix per monitorar el nombre d'usuaris habituals que accedeixen a la web i quines són les seccions més visitades. Dintre de poc afegirem l'opció "<span lang="en">Premium</span>" amb una petita quota l'any on hi haurà contingut exclusiu per aquells que així ho desitgin. El contingut exclusiu serà principalment d'accés als campionats <span lang="en">on-line</span> organitzats per la revista i als seus respectius premis, <span lang="en">Battle Reports</span> exclusius creats pel nostre staff així com tutorials de pintura, campanyes i molt més.<br>Volem implementar el servei <span lang="en">Premium</span> incloent-hi un període de prova gratuït de 2 mesos amb opció de cancel·lació sense compromís un cop hagin passat perquè tots els usuaris tinguin l'oportunitat de provar el servei. Quan s'implementi el servei tindreu a la vostra disposició un apartat amb tota la informació necessària.</p>
					</section>
					<section id="onsom">
						<h2>On sóm?</h2>
						<p>Si voleu fer un tour guiat per les nostres oficines, podeu trobar-nos a l'antiga residència de <span lang="en">Hugh Hefner</span>, al 10236 del carrer <span lang="en">Charing Cross</span> de <span lang="en">Holmby Hills</span>, <span lang="es">Los Ángeles</span>, Califòrnia.</p>
						<figure>
							<img src="${pageContext.request.contextPath}/img/location.jpg" class="img-responsive" alt="Localització">
						</figure>
					</section>
					<section id="feina">
						<h2>Alguna cosa més?</h2>
						<p>Si teniu qualsevol suggeriment o comentari sobre el nostre lloc web, podeu posar-vos en contacte amb nosaltres mitjançant el nostre <a href="contacta.html">formulari</a> de contacte. Sí que el que desitgeu és treballar o col·laborar amb el nostre projecte, podeu fer server també el formulari de contacte i nosaltres ús contactarem al més aviat possible.</p> 
					</section>
					<section id="disclaimer">
						<h2>Disclaimer</h2>
						<p>Si heu arribat a aquest lloc web navegant per Internet, heu de saber que és totalment fictici i és el projecte final d'un curs de disseny i publicació de pàgines web. Aquest lloc no té cap contingut, no emmagatzema cap dada ni té operatius els formularis de contacte o similar. Qualsevol imatge del lloc web ha sigut obtinguda dels llocs oficials dels productes i tenen el seu corresponent copyright. La ubicació de l'empresa tampoc és verídica, no crec que em trobeu mai a la mansió <span lang="en">PlayBoy</span>. Els titulars i possibles articles trobats aquí, han sigut extrets d'altres publicacions gratuïtes, i en aquest últim cas s'inclou l'autor original i els enllaços corresponents a l'article original.</p>
					</section>
				</div> <!--empresa-->
			</div><!--container-->
         	<div class="bcktop">
				<a href="#allinone" title="Tornar al Top" class="page-scroll"></a>
			</div>
 			<div class="push"></div>
		</div>
 		<%@ include file="public-footer.jsp" %>
	</body>
</html>