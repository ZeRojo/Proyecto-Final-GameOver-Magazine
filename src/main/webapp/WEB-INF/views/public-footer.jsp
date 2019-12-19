<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer>
	<div class="row footer-links">
		<div class="container">
			<div class="col-sm-12 col-md-6">
				<div class="row">
					<a href="${pageContext.request.contextPath}"><img class="logo" src="${pageContext.request.contextPath}/img/logoblanco.png" alt="Logo Game Over Magazine"></a>
				</div>
				<div class="row">
					<div class="col-xs-3">
						<ul>
							<c:forEach var="categoria" items="${categorias}">
								<li><a href="${pageContext.request.contextPath}/categoria/${categoria.nombre_opt}">${categoria.nombre}</a></li>
							</c:forEach>
							<li><a href="${pageContext.request.contextPath}/empresa">Empresa</a></li>
							<li><a href="${pageContext.request.contextPath}/contacta">Contacta</a></li>
							<li><a href="${pageContext.request.contextPath}/privacidad">Privacidad</a></li>
							<li><a href="${pageContext.request.contextPath}/accesibilidad">Accesibilidad</a></li>
						</ul>
					</div>
					<div class="col-xs-9">
						<ul class="enllacos-footer">
							<c:forEach var="tag" items="${tags}">
								<li><a class="botofosc" href="${pageContext.request.contextPath}/tag/${tag.nombre_opt}">${tag.nombre}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-6 hidden-sm hidden-xs">
				<div class="row">
					<h2>ÚLTIMOS REPORTAJES</h2>
				</div>
				<div class="row reportajes">
					<c:forEach var="reportaje" items="${reportajes}">
						<div class="col-md-6 peuart">
							<h3><a href="${pageContext.request.contextPath}/articulo/${reportaje.nombre_opt}">${reportaje.producto}</a></h3>
							<img src="${variables.rutaImagenes}/articulos/articulo_${reportaje.idarticulo}/${reportaje.imagen}">
							<p>${reportaje.texto}...</p>
						</div>
					</c:forEach>
				</div>
			</div>
		</div> <!--container-->
	</div> <!--footer-links-->
	<div class="row footer-info">
		<div class="container">
			<div class="col-md-6 col-xs-12">
				<ul class="footer-icso">
					<li><a href="#"><span class="sr-only">Enlace a Facebook</span><span class="fab fa-facebook-square" aria-hidden="true"></span></a></li>
					<li><a href="#"><span class="sr-only">Enlace a Twitter</span><span class="fab fa-twitter-square" aria-hidden="true"></span></a></li>
					<li><a href="#"><span class="sr-only">Enlace a Instagram</span><span class="fab fa-instagram" aria-hidden="true"></span></a></li>
					<li><a href="#"><span class="sr-only">Enlace a Twitch</span><span class="fab fa-twitch" aria-hidden="true"></span></a></li>
					<li><a href="#"><span class="sr-only">Enlace a Youtube</span><span class="fab fa-youtube-square" aria-hidden="true"></span></a></li>		
				</ul>
			</div>
			<div class="col-md-6 col-xs-12">
				<p><span lang="en">Copyright</span> &COPY; 2016-2019 Carles Rojo. Todos los derechos reservados.</p>
			</div>
		</div> <!--container-->
   	</div> <!--footer-info-->
</footer>
<!-- Alerta de cookies si es el primer cop que visites el lloc -->
<div id="cookieAlert">
	<p>Per proporcinar la millor experiència als nostres usuaris aquesta pàgina utilitza cookies. <a href="privacitat.html">Fes clic aqui per a més informació</a>.</p>
	<div>
		<span onclick="ocultaCookie()" class="botofosc">Tanca</span>
	</div>
</div>
<!-- LogIn usuari -->
<sec:authorize access="!isAuthenticated()">
	<%@ include file="public-modals-logreg.jsp" %>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<%@ include file="public-modals-perfil.jsp" %>
</sec:authorize>
<script>



$("#menu-navegacio-principal li").each(function(index) {
	if ($(this).find("a").attr("href")==(window.location.pathname).split('?')[0]) $(this).addClass("active");
});

$("#menu-navegacio-principal li").on("click",function() {
	if ($(this).hasClass("active")) {
		$(this).first("a").on("click",function(){
			event.preventDefault();
		})
		
		
	}
	
})

</script>