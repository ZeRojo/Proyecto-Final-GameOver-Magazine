<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>GameOver - ${titulo}</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link href='https://fonts.googleapis.com/css?family=Gafata' rel='stylesheet' type='text/css'>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos_gestion.css"/>
		<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
		<script src="https://kit.fontawesome.com/19d4302e93.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/scripts/tinymce/jquery.tinymce.min.js"></script>
		<script src="${pageContext.request.contextPath}/scripts/tinymce/tinymce.min.js"></script>
		<meta name="_csrf" content="${_csrf.token}"/>
    	<!-- default header name is X-CSRF-TOKEN -->
    	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	</head>
	<body>
		<%@ include file="gestion-cabecera.jsp" %>
		<div class="container-fluid">
			<div class="contenedor">
				<h1>${titulo}</h1>
				<form:form id="guardarUsuario" modelAttribute="usuario" action="guardar" method="post" class="marginItem">
					<form:hidden path="idusuario"/>
					<form:hidden path="usuarioDetalles.idusuario"/>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="nombreUsuario">Nombre:</label>
							<form:input path="nombre" name="nombreUsuario" class="form-control" required="true"/>
						</div>
						<div class="form-group col-md-6">
							<label for="nick">Nickname:</label>
							<form:input path="usuarioDetalles.nombre" name="nick" class="form-control"/>
						</div>
					</div>	
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="password">Password:</label>
							<form:password id="passwordfield" path="password" name="password" class="form-control"/>
							<span id="passError"></span>
						</div>
						<div class="form-group col-md-6">
							<label for="password2">Repita Password:</label>
							<input  id="passwordfield2" type="password" name="password2" class="form-control">
						</div>
					</div>
					<div class="custom-row">
						<div class="custom-label">
							Rango de usuario:
						</div>
						<div class="custom-group-sp">
							<form:checkboxes items="${rangos}" path="rangos"/>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="custom-label">
								Avatar:
							</div>
							<div class="img-btn-grp">
								<img id="imgAvatar" src="${variables.rutaImagenes}/avatar/${usuario.usuarioDetalles.avatar}"/>
								<form:hidden path="usuarioDetalles.avatar" name="avatar" id="inputAvatar"/>
								<button type="button" id="uploadAvatar" class="form-control btn btn-outline-primary">Subir avatar</button>
							</div>
						</div>
						<div class="col-md-6">
							<div class="custom-label">
								Usuario activo:
							</div>
							<div class="custom-group-check">
								<span>
									<form:checkbox path="activo" name="activo"/>
			      					<label for="activo">ACTIVO</label>
		      					</span>
		      				</div>
	      				</div>					
					</div>
					<div class="submit-group-btn">
						<input id="submitForm" type="submit" class="btn btn-primary" value="Guardar usuario">
					</div>
				</form:form>
			</div>
		</div>
		<!-- Modal upload Avatar -->
		<div class="modal fade" tabindex="-1" aria-labelledby="modalUploadAvatar" aria-hidden="true" id="modalUploadAvatar">
  			<div class="modal-dialog modal-lg">
    			<div class="modal-content">
      				<div class="modal-header">
      					<h4 class="modal-title">Subir un archivo</h4>
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      				</div>
      				<form action="file/upload" id="avatarUploadForm" method="post" enctype="multipart/form-data">
      					<div class="modal-body">
      						<div class="custom-file">
  								<input type="file" name="file" class="custom-file-input" id="avatarFile" lang="es">
  								<label class="custom-file-label" for="file">Seleccionar Archivo</label>
							</div>
							<div class="progress">
								<div id="progressBar" class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
							</div>
							<div id="alertMsg"></div>
						</div>
						<div class="modal-footer">
	        				<button type="submit" class="btn btn-outline-success" id="uploadAvatarConfirmar">Guardar</button>
	        				<button type="button" class="btn btn-outline-danger" id="modalCerrar">Cerrar</button>
	      				</div>
	      			</form>
	    		</div>
	  		</div>
	  	</div> <!-- Modal upload Avatar -->
	</body>
	<script>
		$(document).ready(function(){
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
			
			$(".menu-lat-list a").each(function(index) {
				if ($(this).attr("href")==(window.location.pathname).split('?')[0]) $(this).addClass("active");
			})
			
			$(".container-fluid").css("padding-left",$(".menu-lat-list").width());
						
			$("#uploadAvatar").on("click",function(){
				$("#alertMsg").html("").removeClass("alert alert-success alert-danger");
	    		$("#progressBar").text("");
	    		$("#progressBar").css("width","0%");
	    		$("#uploadAvatarConfirmar").prop("disabled","false");
				$("#modalUploadAvatar").modal("show");
			});
			
			$("#modalCerrar").on("click", function(){
		    	$("#modalUploadAvatar").modal("hide");
			});
			
			$("#submitForm").on("click",function() {
				event.preventDefault();
				if ($("#passwordfield").val()!="") {
					if ($("#passwordfield").val()==$("#passwordfield2").val()) $("#guardarUsuario").submit(); 
					else {
						$("#passError").text("Las contraseñas no coinciden").addClass("alert alert-danger box");
						$("#passwordfield").focus();
					}
				} else {
					$("#passError").text("Las contraseñas no pueden estar vacias").addClass("alert alert-danger box");
				}
			})
			
			$("#passwordfield2").focusout(function() {
				if ($("#passwordfield").val()!=$("#passwordfield2").val()) {
					$("#passError").text("Las contraseñas no coinciden").addClass("alert alert-danger box");
					$("#passwordfield").focus();
				} else {
					$("#passError").text("").removeClass("alert alert-danger box");
				}
			})
			
			$("#uploadAvatarConfirmar").on("click",function(){
				event.preventDefault();
				//Desabilitamos el boton temporalmente
				$(this).prop("disabled","true");
				//var formulario=$("#avatarUploadForm")[0];
				var formData=new FormData($("#avatarUploadForm")[0]);
				formData.append("ruta","/avatar/")
				//Llamada ajax para el upload
				var ajaxReq=$.ajax({
					url: "../../file/uploadAvatar",
					type: "POST",
					data: formData,
					cache: false,
					contentType: false,
					processData: false,
					xhr: function() {
						var xhr = $.ajaxSettings.xhr();
						//Barra de progreso
				         xhr.upload.onprogress = function(event){
				          	var perc = Math.round((event.loaded / event.total) * 100);
				          	$("#progressBar").text(perc + "%");
				          	$("#progressBar").css("width",perc + "%");
				         };
				         return xhr ;
					},
					beforeSend: function(xhr) {
						//Resetea los mensajes de alerta y la barra de progreso
						$("#alertMsg").html("").removeClass("alert alert-success alert-danger");
			    		$("#progressBar").text("");
			    		$("#progressBar").css("width","0%");
					}				
				}); //ajaxRequest
				
				//Upload correcto
				ajaxReq.done(function(nombreArchivo) {
					$("#alertMsg").html("Avatar guardado correctamente").addClass("alert alert-success");
					$("#inputAvatar").val(nombreArchivo.split("\\").pop().split("/").pop());
					$("#imgAvatar").attr("src",nombreArchivo);
					$("#avatarFile").val("");
					$("#uploadAvatarConfirmar").prop("disabled","false");
				});
				
				//Upload fallido
				ajaxReq.fail(function(jqXHR) {
					$("#alertMsg").text(jqXHR.responseText+"("+jqXHR.status+" - "+jqXHR.statusText+")").addClass("alert alert-danger");
					$("#uploadAvatarConfirmar").prop("disabled","false");
				});				
			}); //uploadAvatarConfirmar onclick
		});
	</script>
</html>