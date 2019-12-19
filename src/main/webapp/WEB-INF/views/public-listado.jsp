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
		<title>Game Over Magazine - Listado de Artículos</title>
		<meta name="description" content="Listado de artículos">
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
		<div id="allinone">
	        <%@ include file="public-cabecera.jsp" %>
		    <div class="container">
		    	<c:if test="${tipoFiltro=='videos'}">
		    		<h2>Listado de videos publicados</h2>
		    	</c:if>
		    	<c:if test="${tipoFiltro!='videos'}">
			    	<h2>Listado de artículos por ${tipoFiltro}</h2>
			    </c:if>
		    	<nav id="breadcrumb">
		 			<ol>
		   				<li class="breadcrumb-item active"><a href="${pageContext.request.contextPath}">Inicio</a></li>
		 					<li>/</li>
		 				<c:if test="${tipoFiltro=='videos'}">
							<li>Videos</li>
						</c:if>
						<c:if test="${tipoFiltro=='categoria'}">
							<li>Categoria /</li>
							<li class="breadcrumb-item">${nombreFiltro}</li>
						</c:if>
						
						<c:if test="${tipoFiltro=='tipo'}">
							<li>Tipo /</li>
							<li class="breadcrumb-item">${nombreFiltro}</li>
						</c:if>
						<c:if test="${tipoFiltro=='tag'}">
							<li>Tag /</li>
							<li class="breadcrumb-item">${nombreFiltro}</li>
						</c:if>
						<c:if test="${tipoFiltro=='categoria y tipo'}">
							<li class="breadcrumb-item">${nombreFiltro1}</li>
							<li>/</li>
							<li class="breadcrumb-item">${nombreFiltro2}</li>
						</c:if>
						<c:if test="${tipoFiltro=='autor'}">
							<li>Autor /</li>
							<li class="breadcrumb-item">${nombreFiltro}</li>
						</c:if>
					</ol>
				</nav>
				<div id="listadoArticulos">
					<c:if test="${tipoFiltro=='videos'}">
						<div class="articulo video-list">
							<c:forEach var="video" items="${videos}">
								<div class="col-6 video-list-item">
									<iframe class="video" src="${video.link}" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
								</div>						
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${tipoFiltro!='videos'}">
						<c:forEach var="articulo" items="${articulos}">
							<div class="articulo">
								<div class="col-4 img" data-img="${variables.rutaImagenes}/articulos/articulo_${articulo.idarticulo}/${articulo.imagen}">							
								</div>
								<div class="col-8">
									<h3><a href="${pageContext.request.contextPath}/articulo/${articulo.nombre_opt}">${articulo.nombre}</a></h3>
									<div>${articulo.texto}</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<nav aria-label="Paginación artículos">
		  			<ul id="paginacion" class="pagination flex-center" data-numpaginas="${variables.numeroPaginas}" data-pagactual="${variables.paginaActual}" data-numelementos="${variables.elementosPagina}">
		  			</ul>
				</nav>
		    </div>
		    <div class="push"></div>
		 </div>
	     <%@ include file="public-footer.jsp" %>
	</body>
	<script>
		$(document).ready(function(){
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
			//Paginación
			var numpaginas=$("#paginacion").attr("data-numpaginas");
			var pagactual=$("#paginacion").attr("data-pagactual");
			var numelementos=$("#paginacion").attr("data-numelementos");
			if (numpaginas>1) {
				$("#paginacion").append("<li class='page-item'><button class='page-link' type='button' value='1'>&laquo;</button></li>");
				if (pagactual==1) $("#paginacion li").last().addClass("disabled");
				for (i=1;i<=numpaginas;i++) {
					$("#paginacion").append("<li class='page-item'><button class='page-link' type='button' value='"+i+"'>"+i+"</button></li>");
					if (i==pagactual) {
						$("#paginacion li").last().addClass("disabled blue");
					};
				};
				$("#paginacion").append("<li class='page-item'><button class='page-link' type='button' value='"+numpaginas+"'>&raquo;</button></li>");
				if (pagactual==numpaginas) $("#paginacion li").last().addClass("disabled");
				
				$(".page-link").click(function() {
					window.location.href = window.location.pathname+"?pag="+$(this).val();
				});
			};
			$(".img").each(function(){
				$(this).css("background-image","url("+$(this).attr("data-img")+")");
			})
		});
	</script>
</html>