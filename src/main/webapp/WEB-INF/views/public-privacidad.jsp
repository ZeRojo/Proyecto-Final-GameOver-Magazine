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
		<title>Game Over Magazine - Privacidad</title>
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
		<div id="allinone">
	        <%@ include file="public-cabecera.jsp" %>
		        <div class="container">
					<div id="privacitat">
						<section id="pprivcook">
							<h2>Política de privacitat i <span lang="en">cookies</span></h2>
							<p>Game Over Magazine (nosaltres) es compromet a respectar els teus drets de privacitat. Ens prenem la privacitat dels nostres clients i usuaris molt seriosament i prenem totes les mesures possibles per protegir la teva informació personal.</p>
							<p>Si us plau, llegeix la següent política acuradament per entendre quina informació personal podem recollir, com la farem servir i quins són els teus drets respecte al nostre ús. Al fer servir aquesta pàgina web i registrar-te, acceptes els termes d'aquesta Política de privacitat i <span lang="en">cookies</span>.</p>
							<h3>Quina informació de l'usuari s'emmagatzema?</h3>
							<p>Game Over Magazine no requereix que els usuaris revelin cap informació que els identifiqui per poder accedir a la nostra pàgina web principal, encara que per participar en determinades activitats sí que es requereix certa informació, com per exemple:</p>
							<ul>
								<li>Informació que ens proporciones en omplir formularis de la nostra pàgina web. Això inclou informació proporcionada en el moment de subscriure't a la nostra <span lang="en">newsletter</span>, de contactar amb nosaltres, de registrar-te a la web o de subscriure't als nostres serveis. Podem, per exemple, guardar el teu nom, adreça, adreça de correu electrònic i número de telèfon.</li>
								<li>Tindrem detalls de la transacció en realitzar alguna subscripció a la nostra pàgina web.</li>
								<li>Podem demanar-te informació addicional quan et registris a una competició <span lang="en">on-line</span> que s'organitzi a través de la nostra pàgina web o en una promoció patrocinada per nosaltres.</li>
								<li>Podem recopilar detalls de les teves visites a la nostra pàgina web, com per exemple els recursos als quals accedeixes.</li>
							</ul>
							<h3>Com utilitzarem la teva informació?</h3>
							<p>Podem utilitzar la teva informació de les següents maneres:</p>
							<ul>
								<li>Per crear un perfil en qualitat de membre per ajudar-nos a proporcionar un servei més personalitzat que encaixi amb les teves preferències.</li>
								<li>Per assegurar que el contingut de la nostra pàgina web es presenta de la manera més eficaç, tant per tu, com per al teu dispositiu de navegació.</li>
								<li>Per enviar-te <span lang="en">newsletters</span> o proporcionar-te informació o serveis que ens demanis o que creiem que et podrien interessar i que hagis donat el teu consentiment per aquests propòsits.</li>
								<li>Per dur a terme les nostres obligacions que s'urgeixin de qualsevol contracte que hi hagi entre tu i nosaltres.</li>
								<li>Per permetre't participar en les nostres seccions interactives, quant tu les triïs.</li>
								<li>Per notificar-te de qualsevol canvi en el nostre servei.</li>
								<li>Per analitzar dades.</li>
								<li>Per facilitar la millora i l'optimització dels nostres serveis per a tu.</li>
							</ul>
							<h3>Direccions <abbr title="Internet Protocol">IP</abbr> i <span lang="en">Cookies</span></h3>
							<p>Podem recopilar informació sobre el teu ordinador, com la teva adreça <abbr title="Internet Protocol">IP</abbr>, el teu sistema operatiu i el teu tipus de navegador, per a l'administració de sistemes. Aquestes són dades estadístiques sobre les nostres accions i patrons per a la cerca d'usuaris, i no identifica a cap individu. Per la mateixa raó, podem obtenir informació sobre el teu ús general d'Internet utilitzant un arxiu de <span lang="en">cookies</span> que s'emmagatzema en el disc d'ur del teu ordinador. Aquestes <span lang="en">cookies</span> ens ajuden a millorar la nostra pàgina web i a donar un servei millor i més personalitzat. Les <span lang="en">cookies</span> ens permeten:</p>
							<ul>
								<li>Fer un càlcul de la quantitat d'usuaris general i com utilitzen la nostra pàgina web.</li>
								<li>Reconèixer quan tornis a la nostra pàgina web.</li>
								<li>Emmagatzemar informació sobre les teves preferències que ens permeten personalitzar la nostra pàgina web segons els teus interessos individuals.</li>
								<li>Agilitzar les teves cerques.</li>
							</ul>
							<h3>Quin tipus de <span lang="en">cookies</span> utilitzem?</h3>
							<p>En la fase en la qual es troba el lloc web, actualment, les pàgines del lloc no generen cap tipus de <span lang="en">cookie</span>. Tanmateix en la construcció de la pàgina, s'hi han inclòs elements que sí que les generen.</p>
							<h4><span lang="en">Cookies</span> del lloc</h4>
							<p>Només guardem 2 cookies en aquest lloc, una per comprobar si el lloc ha estat visitat anteriorment i una altre que enmagatzema el nom d'usuari, que només es un recurs visual, sense enmagatzemar dades. Aquestes <span lang="en">cookies</span> són <code>visitat</code> i <code>usuari</code>.</p>
							<h4><span lang="en">Cookies</span> de <cite lang="en">Youtube</cite></h4>
							<p>Tenim un apartat on incrustem a la web vídeos de <cite lang="en">Youtube</cite>. Aquesta manera de fer, pot deixar <span lang="en">cookies</span> al teu ordinador, però <cite lang="en">Youtube</cite> no emmagatzemarà informació personal o identificable en aquestes <span lang="en">cookies</span>. Per a saber més consulta la pàgina d'informació de <cite lang="en">Youtube</cite> sobre la incrustació de vídeos <a href="https://support.google.com/youtube/answer/171780?hl=en-GB" target="_blank" hreflang="en">aquí</a>.</p>
							<p lang="en">Cookies:</p>
							<ul class="llistaCookies">
								<li><code>VISITOR_INFO1_LIVE</code>: S'utilitza per a la mesura de l'amplada de banda de <cite lang="en">Youtube</cite>.</li>
								<li><code>PREF</code>: S'utilitza per emmagatzemar les preferències de l'usuari en els vídeos incrustats de <cite lang="en">Youtube</cite>.</li>
								<li><code>YSC</code>: Guarden les preferències de la sessió de <cite lang="en">Youtube</cite>. Un cop tancat l'explorador, s'eliminen.</li>
							</ul>
							<h4><span lang="en">Cookies</span> de mikle.com</h4>
							<p>En la nostra pàgina principal tenim un petit apartat on tenim incrustades les últimes novetats d'altres pàgines o blogs mitjançant <abbr title="Really Simple Syndication">RSS</abbr>. Això està realitzat mitjançant un <span lang="en">widget</span>, el qual deixa un parell de <span lang="en">cookies</span> de <span lang="en">Google Analytics</span>.</p>
							<p lang="en">Cookies:</p>
							<ul class="llistaCookies">
								<li><code>_ga</code>: S'utilitza per a diferenciar els usuaris.</li>
								<li><code>_gat</code>: S'utilitza per limitar el percentatge de sol·licituds.</li>
							</ul>
							<h4><span lang="en">Cookies</span> de tabletopgamingnews.com</h4>
							<p>Tenim incrustades les últimes novetats de tabletopgamingnews.com. Aquesta web crea una <span lang="en">cookie</span> que no emmagatzema cap tipus d'informació personal o identificable.</p>
							<p lang="en">Cookies:</p>
							<ul class="llistaCookies">
								<li><code>__cfduid</code>: S'utilitza per fer un seguiment del teu accés a la nostra pàgina web, que inclou saber si ets un visitant habitual. Per a més informació visita:
								<a href="https://support.cloudflare.com/hc/en-us/articles/200170156-What-does-the-CloudFlare-cfduid-cookie-do" target="_blank">https://support.cloudflare.com/hc/en-us/articles/200170156-What-does-the-CloudFlare-cfduid-cookie-do</a></li>
							</ul>
							<p>Per conèixer més detalls sobre les <span lang="en">cookies</span> i sobre com limiten o bloquegen l'ús d'aquesta pàgina, visita <a href="http://www.aboutcookies.org" target="_blank">www.aboutcookies.org</a>.</p>
							<p><strong>Tingues en compte que</strong> si limites o bloqueges les <span lang="en">cookies</span> que utilitza aquesta pàgina web, pot ser que redueixis el funcionament i la utilitat d'aquesta.</p>
							<h3>Actualitzacions i canvis en la política de privacitat / <span lang="en">cookies</span></h3>
							<p>La nostra web, pot modificar aquesta Política de <span lang="en">cookies</span> en funció d'exigències legislatives, reglamentaries, o amb la finalitat d'adaptar aquesta política a les instruccions dictades per l'Agència Espanyola de Protecció de Dades, per això s'aconsella als usuaris, que la visitin periòdicament.</p>
							<p>Quan es produeixin canvis significatius en aquesta Política de <span lang="en">cookies</span>, es comunicaran als usuaris mitjançant la web o a través del correu electrònic als usuaris registrats, que a més, podran fer ús dels seus drets de protecció de dades consultant l'Agència Espanyola de Protecció de Dades.</p>
						</section>
					</div><!--privacitat-->
				</div><!--container-->
         	<div class="bcktop">
				<a href="#allinone" title="Tornar al Top" class="page-scroll"></a>
			</div>
 			<div class="push"></div>
		</div>
 		<%@ include file="public-footer.jsp" %>
	</body>
</html>