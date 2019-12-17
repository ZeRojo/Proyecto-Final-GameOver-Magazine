<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>GameOver - Listado de vídeos</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link href='https://fonts.googleapis.com/css?family=Gafata' rel='stylesheet' type='text/css'>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos_gestion.css"/>
		<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
		<script src="https://kit.fontawesome.com/19d4302e93.js" crossorigin="anonymous"></script>
	</head>
	<body>
		<%@ include file="gestion-cabecera.jsp" %>
		<div class="container-fluid">
			<div class="contenedor">
				<h1>Listado de vídeos</h1>
				<div class="marginItem">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th scope="col">ID</th>
								<th scope="col"></th>
								<th scope="col">Nombre</th>
								<th scope="col">Tipo</th>
								<th scope="col" id="nuevo"><i class="far fa-plus-square fa-2x green" title="Nuevo usuario"></i></th>	
							</tr>
						</thead>
						<tbody>
							<c:forEach var="video" items="${videos}">
								<tr>
									<th scope="row">${video.idvideo}</th>
									<td><img src="${variables.rutaImagenes}/videos/${video.thumbnail}"/></td>
									<td>${video.nombre}</td>
									<td>${video.tipo}</td>
									<td>
										<form:form action="editar" method="post">
											<input type="hidden" name="idvideo" value="${video.idvideo}">
											<div class="button-group">
												<input type="submit" class="btn btn-primary" value="Editar"/>
												<sec:authorize access="hasRole('ADMIN')">				
													<button type="button" class="eliminar btn btn-danger" value="${video.idvideo}">Eliminar</button>
												</sec:authorize>
											</div>
										</form:form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<nav aria-label="Paginación usuarios">
	  				<ul id="paginacion" class="pagination flex-center" data-numpaginas="${variables.numeroPaginas}" data-pagactual="${variables.paginaActual}" data-numelementos="${variables.elementosPagina}">
	  				</ul>
				</nav>
			</div> <!-- contenedor -->						
		</div><!-- container-fluid -->
		<!-- Modal eliminar video -->
		<div class="modal fade" tabindex="-1" aria-labelledby="modalEliminarVideo" aria-hidden="true" id="modalEliminarVideo">
  			<div class="modal-dialog modal-lg">
    			<div class="modal-content">
      				<div class="modal-header">
      					<h4 class="modal-title">Confirmar eliminación</h4>
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      				</div>
      				<div class="modal-body">
      					<p>Seguro que quiere eliminar este vídeo ?</p>
      					<small>Este proceso no se puede desacer</small>	
					</div>
					<div class="modal-footer">
						<form:form action="eliminar" method="post">
							<input type="hidden" id="idvideo" name="idvideo" value="">
							<button type="submit" class="btn btn-outline-success">Eliminar</button>
							<button type="button" class="btn btn-outline-danger" id="modalCerrar">Cancelar</button>
						</form:form>       				
      				</div>
	    		</div>
	  		</div>
	  	</div> <!-- Modal eliminar video -->
	</body>
	<script>
		$(document).ready(function(){
			var ruta = window.location.pathname;
			
			$(".menu-lat-list a").each(function(index) {
				if ($(this).attr("href")==(window.location.pathname).split('?')[0]) $(this).addClass("active");
			});
			
			$(".container-fluid").css("padding-left",$(".menu-lat-list").width());
							
			$("#nuevo").click(function(){
				window.location.href =  "../videos/nuevo";
			});
			
			$(".eliminar").click(function(){
				$("#idvideo").val($(this).val());
				$("#modalEliminarVideo").modal("show");
			});
			
			$("#modalCerrar").click(function(){
				$("#idvideo").val("");
				$("#modalEliminarVideo").modal("hide");
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
					window.location.href = ruta+"?pag="+$(this).val();
				});
			};		
		});	
	</script>
</html>