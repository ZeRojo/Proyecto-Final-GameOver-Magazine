<%@  page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>GameOver - Gestión de tipos</title>
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
				<h1>Gestión de Tipos</h1>
				<div class="row marginItem">
					<div class="col-12">
						<h2>Editar</h2>
						<form:form action="editar" method="post" class="marginItem">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="idEditTipo">Tipo:</label>
									<select name="idEditTipo" class="form-control">
										<option value="0">--Selecciona--</option>
										<c:forEach var="tipo" items="${tipos}">
											<option value="${tipo.idtipo}">${tipo.nombre}</option>
										</c:forEach>
									</select>
									<c:if test="${not empty validacion.selectTipoError}">
   										<div class="alert alert-danger formError">${validacion.selectTipoError}</div>
									</c:if>
								</div>
								<div class="form-group col-md-6">					
									<label for="nuevoNombreTipo">Nuevo nombre:</label>
									<input type="text" name="nuevoNombreTipo" class="form-control">
									<c:if test="${not empty validacion.nombreTipoError}">
										<c:if test="${tipoForm=='editar'}"> 
	   										<div class="alert alert-danger formError">${validacion.nombreTipoError}</div>
	   									</c:if>
									</c:if>
								</div>
							</div>
							<div class="submit-group-btn">
								<input type="submit" class="btn btn-primary" value="Guardar">
							</div>
						</form:form>
					</div>
				</div>
				<div class="row marginItem">
					<div class="col-6">	
						<h2>Nuevo</h2>
						<form:form action="nuevo" method="post" class="marginItem">
							<div class="form-group">
								<label for="nombreNuevoTipo">Nombre:</label>
								<input type="text" name="nombreNuevoTipo" class="form-control">
								<c:if test="${not empty validacion.nombreTipoError}">
									<c:if test="${tipoForm=='nuevo'}"> 
	   									<div class="alert alert-danger formError">${validacion.nombreTipoError}</div>
	   								</c:if>
								</c:if>
							</div>
							<div class="submit-group-btn">
								<input type="submit" class="btn btn-primary" value="Guardar">
							</div>
						</form:form>
					</div>
					<div class="col-6">
						<h2>Eliminar</h2>
						<div class="marginItem">
							<div class="form-group">
								<label for="idElimTipo">Tipo:</label>
								<select name="idElimTipo" id="idElimTipo" class="form-control">
									<option value="0">--Selecciona--</option>
									<c:forEach var="tipo" items="${tipos}">
										<option value="${tipo.idtipo}">${tipo.nombre}</option>
									</c:forEach>
								</select>
							</div>
							<div class="submit-group-btn">
								<button type="button" id="eliminarTipo" class="btn btn-primary">Eliminar</button>
							</div>
						</div>
					</div>
				</div>
			</div> <!-- contenedor -->
		</div> <!-- container-fluid -->	
		<!-- Modal eliminar tipo -->
		<div class="modal fade" tabindex="-1" aria-labelledby="modalEliminarTipo" aria-hidden="true" id="modalEliminarTipo">
  			<div class="modal-dialog modal-lg">
    			<div class="modal-content">
      				<div class="modal-header">
      					<h4 class="modal-title">Confirmar eliminación</h4>
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      				</div>
      				<div class="modal-body">
      					<p>Seguro que quiere eliminar este Tipo ?</p>
      					<small>Este proceso no se puede desacer</small>	
					</div>
					<div class="modal-footer">
						<form:form action="eliminar" method="post">
							<input type="hidden" id="idtipo" name="idtipo" value="">
							<button type="submit" class="btn btn-outline-success">Eliminar</button>
							<button type="button" class="btn btn-outline-danger" id="modalCerrar">Cancelar</button>
						</form:form>       				
      				</div>
	    		</div>
	  		</div>
	  	</div> <!-- Modal eliminar tipo -->
	</body>
	<script>
		$(document).ready(function(){
			var temp=(window.location.pathname).split('?')[0];
			$(".menu-lat-list a").each(function(index) {
				var temp2=$(this).attr("href");
				if ((temp.substring(0,temp.lastIndexOf('/') + 1))==(temp2.substring(0,temp2.lastIndexOf('/') + 1))) $(this).addClass("active");
			});
			
			$(".container-fluid").css("padding-left",$(".menu-lat-list").width());
			
			$("#eliminarTipo").click(function(){
				if ($("#idElimTipo").val()!=0) {
					$("#idtipo").val($("#idElimTipo").val());
					$("#modalEliminarTipo").modal("show");
				};
			});
			
			$("#modalCerrar").click(function(){
				$("#idtipo").val("");
				$("#modalEliminarTipo").modal("hide");
			});			
		});
	</script>
</html>