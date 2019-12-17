<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
	<div id="z-header" role="banner">
		<div id="z-header-banner">
			<div class="container">
				<div class="col-xs-12 col-sm-6 col-md-6 centra-xs">
					<h1 title="Logo Game Over Magazine"><a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo Game Over Magazine - Salta fins al contingut"></a></h1>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6 centra-xs">
					<p class="icones-socials">Síguenos para actualizaciones diarias: <a href="#"><span class="sr-only">Enlace a Facebook</span><span class="fab fa-facebook" aria-hidden="true"></span></a> <a href="#"><span class="sr-only">Enlace a Twitter</span><span class="fab fa-twitter" aria-hidden="true"></span></a> <a href="#"><span class="sr-only">Enlace a Instagram</span> <span class="fab fa-instagram" aria-hidden="true"></span></a> <a href="#" ><span class="sr-only">Enlace a Twitch</span><span class="fab fa-twitch" aria-hidden="true"></span></a> <a href="#"><span class="sr-only">Enlace a Youtube</span> <span class="fab fa-youtube" aria-hidden="true"></span></a> </p>
				</div>
				</div> <!--./container-->
		</div> <!--./z-header-banner-->
		<div id="z-header-menu">
			<div class="container">
				<nav class="navbar navbar-inverse menu-principal">
					<!-- Botó desplegable del menu en dispositius mòbils -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu-navegacio-principal" aria-expanded="false">
							<span class="sr-only">Desplegable de navegación</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
					<!-- Cuandre de cerca en dispositius petits -->
					<div class="navbar-more visible-xs flota-dreta">
						<div class="input-group">
							<label for="cerca1" class="sr-only">Campo de búsqueda</label>
		                        		<input name="cerca1" id="cerca1" type="text" placeholder="Cerca..." class="form-control">
		                        		<span class="input-group-btn">
								<button class="btn btn-default" type="button"><i class="fa fa-search" aria-hidden="true"></i></button>
		                        		</span>
		                    		</div>
					</div>
					<!-- Menú de navegació principal -->
					<div class="collapse navbar-collapse" id="menu-navegacio-principal">
						<ul class="nav navbar-nav">
							<!-- Botó inici (home)-->
		   					<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home" aria-hidden="true"></i> <span class="sr-only">(actual)</span></a></li>
							<!-- Menú por categoria -->
							<c:forEach var="categoria" items="${categorias}">
								<c:if test="${categoria.nombre_opt!='manga'}">
									<li class="dropdown">
										<a href="${pageContext.request.contextPath}/categoria/${categoria.nombre_opt}" class="dropdown-toggle" data-hover="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categoria.nombre}<span class="caret"></span></a>										
										<ul class="dropdown-menu vora-sup despnormal">
											<c:forEach var="tipo" items="${tipos}">
												<li><a href="${pageContext.request.contextPath}/catytipo/${categoria.nombre_opt}/${tipo.nombre_opt}">${tipo.nombre}</a></li>
											</c:forEach>
										</ul>
									</li>
								</c:if>
								<c:if test="${categoria.nombre_opt=='manga'}">
									<li><a href="${pageContext.request.contextPath}/categoria/${categoria.nombre_opt}">${categoria.nombre}</a>
								</c:if>
							</c:forEach>
							<li><a href="${pageContext.request.contextPath}/empresa">Empresa</a></li>
							<li><a href="${pageContext.request.contextPath}/contacta">Contacta</a></li>
							<!-- Menú desplegable d'usuari -->
							<sec:authorize access="!isAuthenticated()">
								<li><a href="#" data-toggle="modal" data-target="#login-modal">Acceder</a></li>
								<li><a href="#" data-toggle="modal" data-target="#regin-modal">Registrarse</a></li>			      	         				
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<li class="dropdown">
									<a id="boto-usuari" href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="sr-only">Menú desplegable d'usuari</span><span id="usuari">${usuarioNickname}</span> <span class="caret"></span></a>
									<ul id="despUsuari" class="dropdown-menu vora-sup despnormal">
										<li id="perfil">
											<div class="filacab">
												<div id="avatar">
													<img src="${variables.rutaImagenes}/avatar/${usuarioAvatar}">
												</div>
												<div id="info">
													<p>Nickname: ${usuarioNickname}</p>
													<p>Usuario: <sec:authentication property="name"/></p>
												</div>
											</div>
											<div class="filacab btn">
												<button id="editperfil" type="button" class="botofosc">Editar perfil</button>
												<button id="logout" type="button" class="botofosc">Cerrar sesión</button>
											</div>										
										</li>
									</ul>
								</li>
								<sec:authorize access="hasAnyRole('ADMIN','EDITOR')">
									<li><a href="${pageContext.request.contextPath}/gestion">Zona de Gestión</a></li>
								</sec:authorize>
							</sec:authorize>
						</ul>
					</div><!--navbar-collapse-->
				</nav>
			</div> <!--container-->
		</div> <!--z-header-menu-->
	</div> <!--z-header-->
</header>
<div class="carousel" id="carousel">
	<c:forEach var="articulo" items="${articulosCarrousel}">
		<div class="car-slide">
			<div class="car-art-img" data-image="${variables.rutaImagenes}/articulos/articulo_${articulo.idarticulo}/${articulo.imagen}">
				<div class="info-sup-xs">
				</div>
				<div class="info-inf-xs">
					<h2><a href="${pageContext.request.contextPath}/articulo/${articulo.nombre_opt}">${articulo.producto}</a></h2>
				</div>
				<div class="car-art-info">
					<div class="info-sup">
						<h2><a href="${pageContext.request.contextPath}/articulo/${articulo.nombre_opt}">${articulo.producto}</a></h2>
						<p>${articulo.texto }</p>
					</div>
					<div class="info-inf">
						<p class="plat">
							<c:forEach var="tag" items="${articulo.tags}">
								<a class="botofosc" href="${pageContext.request.contextPath}/tag/${tag.nombre_opt}">${tag.nombre}</a>
							</c:forEach>
						</p>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div> <!--carousel-->