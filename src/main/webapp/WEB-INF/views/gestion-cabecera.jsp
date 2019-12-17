<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="sticky-top">
	<div>
		<img src="${pageContext.request.contextPath}/img/logoblanco.png">
	</div>
	<div class="zonausuario">
		<div>
			<img src="${variables.rutaImagenes}/avatar/${usuarioAvatar}"/>
			<span>${usuarioNickname}</span>		
		</div>
		<form:form action="${pageContext.request.contextPath}/userLogout" method="GET">
			<input type="hidden" name="gestion" value="true">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Logout</button>
		</form:form>
	</div>
</header>
<nav class="menu-lat-list">
	<sec:authorize access="hasRole('ADMIN')">
		<div class="list-group">
			<a href="#" class="list-group-item list-group-item-action disabled">Usuarios</a>
			<a href="${pageContext.request.contextPath}/gestion/usuarios/nuevo" class="list-group-item list-group-item-action">Crear usuario</a>
			<a href="${pageContext.request.contextPath}/gestion/usuarios/listado" class="list-group-item list-group-item-action">Listar usuarios</a>
		</div>
	</sec:authorize>
	<div class="list-group">
		<a href="#" class="list-group-item list-group-item-action disabled">Artículos</a>
		<a href="${pageContext.request.contextPath}/gestion/articulo/nuevo" class="list-group-item list-group-item-action">Crear artículo</a>
		<a href="${pageContext.request.contextPath}/gestion/articulo/listado" class="list-group-item list-group-item-action">Listar artículos</a>
	</div>
	<div class="list-group">
		<a href="#" class="list-group-item list-group-item-action disabled">Videos</a>
		<a href="${pageContext.request.contextPath}/gestion/videos/nuevo" class="list-group-item list-group-item-action">Añadir video</a>
		<a href="${pageContext.request.contextPath}/gestion/videos/listado" class="list-group-item list-group-item-action">Listar videos</a>
	</div>
	<div class="list-group">
		<a href="#" class="list-group-item list-group-item-action disabled">Estrenos</a>
		<a href="${pageContext.request.contextPath}/gestion/estrenos/nuevo" class="list-group-item list-group-item-action">Añadir estrenos</a>
		<a href="${pageContext.request.contextPath}/gestion/estrenos/listado" class="list-group-item list-group-item-action">Listar estrenos</a>
	</div>
	<sec:authorize access="hasRole('ADMIN')">
		<div class="list-group">
			<a href="#" class="list-group-item list-group-item-action disabled">Categorias</a>
			<a href="${pageContext.request.contextPath}/gestion/categoria/" class="list-group-item list-group-item-action">Gestión de categorias</a>
		</div>
		<div class="list-group">
			<a href="#" class="list-group-item list-group-item-action disabled">Tipos</a>
			<a href="${pageContext.request.contextPath}/gestion/tipo/" class="list-group-item list-group-item-action">Gestión de tipos</a>
		</div>
	</sec:authorize>
</nav>