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
    	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    	<meta name="path" content="${pageContext.request.contextPath}"/>
	</head>
	<body>
		<%@ include file="gestion-cabecera.jsp" %>
		<div class="container-fluid">
			<div class="contenedor">
				<h1>${titulo}</h1>
				<c:if test="${tarea=='Nuevo'}">
					<div class="marginItem">
						<!-- Formulario de envio del título nuevo o a editar // envio por ajax -->
						<form action="guardaTitulo" method="post">
							<div class="form-row">
								<div class="form-group col-md-12">
									<label for="nombreArt">Nombre del artículo:</label>
									<input type="text" class="form-control" id="formNombreTitArt" placeholder="Nombre" value="${articulo.nombre}">
								</div>
							</div>
							<div id="formTitMsg" class="formError"></div>
							<div class="submit-group-btn" id="formTitBtn">
								<input id="sendNombreArt" class="btn btn-primary" type="submit" value="Guardar">
							</div>
						</form>
					</div>
				</c:if>
				<div <c:if test="${tarea=='Nuevo'}">class="marginItem formHidden"</c:if><c:if test="${tarea=='Editar'}">class="marginItem"</c:if>>
					<!-- Formulario completo // Envio por post -->
					<form:form id="formularioArticulo" modelAttribute="articulo" action="guardaArticulo" method="post" accept-charset="UTF-8">
						<input type="hidden" name="tarea" value="${tarea}">
						<form:hidden path="idarticulo" id="idarticulo"/>
						<form:hidden id="nombre_opt" path="nombre_opt"/>
						<form:hidden id="fecha" path="fecha" />
						<c:if test="${tarea=='Nuevo'}">
							<form:hidden id="nombre" path="nombre"/>
						</c:if>
						<c:if test="${tarea=='Editar'}">
							<div class="form-row">
								<div class="form-group col-md-12">
									<label for="nombre">Nombre del artículo:</label>
									<form:input type="text" class="form-control" path="nombre" id="nombre" placeholder="Nombre" value="${articulo.nombre}"/>
									<c:if test="${not empty validacion.nombreArticuloError}">
   										<div class="alert alert-danger formError">${validacion.nombreArticuloError}</div>   
									</c:if>
								</div>
								
							</div>
						</c:if>
						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="producto">Producto referenciado:</label>
								<form:input type="text" class="form-control" path="producto" id="producto" placeholder="Producto" value="${articulo.producto}"/>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 form-group">
								<label for="categoria">Categoría:</label>
								<form:select id="categoria" path="categoria.idcategoria" class="form-control">
									<option value="0">--Selecciona--</option>
									<form:options items="${categorias}" itemValue="idcategoria"	itemLabel="nombre" />
								</form:select>
							</div>	
							<div class="col-md-6 form-group">
								<label for="tipo">Tipo:</label>
								<form:select id="tipo" path="tipo.idtipo" class="form-control">
									<option value="0">--Selecciona--</option>
									<form:options items="${tipos}" itemValue="idtipo" itemLabel="nombre" />
								</form:select>
							</div>
						</div> <!-- form-row -->
						<div class="custom-row">
							<div class="custom-label">
								Tags:
							</div>
							<div class="form-row">
								<div class="form-groum col-md-9">
									<ul class="tag-group" id="listaTags">
										<c:forEach var="tag" items="${articulo.tags}">			
											<li class="tag" data-id="${tag.idtag}">${tag.nombre}</li>
										</c:forEach>
									</ul>
								</div>
								<div class="form-group join-in-butt flex-right col-md-3">
									<input class="form-control" type="text" id="addTagText">
									<button type="button" class="btn btn-primary" id="addTag">Añadir Tag</button>
								</div>
							</div>
						</div> <!-- custom-row -->
						<div class="form-row">
							<div class="col-md-6">
								<div class="custom-label">Imagen principal:</div>
								<div class="form-group flex-center join-in-butt align-center">
									<form:input type="text" path="imagen" id="imagen" readonly="readonly" class="form-control"/>
									<button type="button" class="btn btn-primary" id="uploadImgPrincial" value="bannerImg">Imagen principal</button>
								</div>
								<c:if test="${not empty validacion.imagenError}">
   										<div class="alert alert-danger formError">${validacion.imagenError}</div>   
									</c:if>
							</div>
							<div class="col-md-6">
								<div class="custom-label">Publicar artículo:</div>
								<div class="custom-group-check">
									<span>
										<form:checkbox path="publicar" name="publicar" id="publicar"/>
										<label for="publicar">PUBLICAR</label>
	      							</span>
	    						</div>
							</div>
						</div>
						<div class="editor-texto">
							<label for="tiny">Artículo: </label>
							<form:textarea path="texto" id="tiny"/>
							<input name="image" type="file" id="uploadImg" class="hidden" onchange="">	
						</div>
						<div class="submit-group-btn">
							<input id="enviarFormularioArticulo" type="submit" value="Guardar cambios" class="btn btn-primary"/>
						</div>
					</form:form>
				</div> <!-- formHidden -->
			</div> <!-- contenedor -->
		</div><!-- container-fluid -->
		<!-- Modal upload img -->
		<div class="modal fade" tabindex="-1" aria-labelledby="modalFileUpload" aria-hidden="true" id="modalFileUpload">
  			<div class="modal-dialog modal-lg">
    			<div class="modal-content">
      				<div class="modal-header">
      					<h4 class="modal-title">Subir imagen principal</h4>
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			</div>
      				<form action="file/upload" id="fileUploadForm" method="post" enctype="multipart/form-data">
      					<div class="modal-body">
      						<div class="custom-file">
      							<input type="file" name="file" class="custom-file-input" id="file" lang="es">
  								<label class="custom-file-label" for="file">Seleccionar Archivo</label>
      						</div>							
							<div class="progress">
								<div id="progressBar" class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
							</div>
							<div id="alertMsg"></div>
						</div>
						<div class="modal-footer">
	        				<button type="submit" class="btn btn-outline-success" id="uploadConfirmar">Guardar</button>
	        				<button type="button" class="btn btn-outline-danger" id="modalCerrar">Cerrar</button>
	      				</div>
	      			</form>
	    		</div>
	  		</div>
		</div> <!-- Modal upload img -->
	</body>
	<script>
		$(document).ready(function(){
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var path = $("meta[name='path']").attr("content");
			var d = new Date();
			$(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
			
			$(".menu-lat-list a").each(function(index) {
				if ($(this).attr("href")==(window.location.pathname).split('?')[0]) $(this).addClass("active");
			});
			
			$(".container-fluid").css("padding-left",$(".menu-lat-list").width());
			
			$(".tag").on("click",function(){
				$(this).remove();
			})
			
			$("#uploadImgPrincial").on("click", function(){
				$("#modalFileUpload").modal('show');
			});
			
			$("#modalCerrar").on("click", function(){
				$("#modalFileUpload").modal('hide');
			});
			
			$("#fecha").val((d.getDate()<10?"0":"")+d.getDate()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+d.getFullYear());
		
			/*ENVIO TÍTULO DEL NUEVO ARTÍCULO
			/*Formulario para guardar el título del articulo y recuperar el ID generado*/
			$("#sendNombreArt").on("click", function(e){
				event.preventDefault();
				$(this).prop("disabled",true);
				var formData = new FormData();
				formData.append("nombre",$("#formNombreTitArt").val());
				// Llamada ajax
				var ajaxReq = $.ajax({
					url : "guardaTitulo",
					type : 'POST',
					data : formData,
					cache : false,
					contentType : false,
					processData : false,
					xhr: function(){
						var xhr = $.ajaxSettings.xhr() ;
						return xhr;
					},
					beforeSend: function(xhr) {
						$('#formTitMsg').text('').removeClass("alert alert-success alert-danger");
					}
				});
				// Ajax success
				ajaxReq.done(function(data) {
					$("#nombre").val($("#formNombreTitArt").val());
					var parsedJson = $.parseJSON(data);
					$("#idarticulo").val(data);
				  	$("#formTitMsg").text("Nombre guardado correctamente").addClass("alert alert-success");						    	
					$("#formTitBtn").html("");
					$(".formHidden").addClass("mostrar");
					$("#formNombreTitArt").attr("disabled");
				});
				//Ajax fail
				ajaxReq.fail(function(jqXHR) {
					$("#formTitMsg").text(jqXHR.responseText).addClass("alert alert-danger");
					$('#sendNombreArt').prop('disabled',false);
				});
			});
			
			$('form input').on('keypress', function(e) {
			    return e.which !== 13;
			});
			
			/*ENVIO FORMULARIO COMPLETO		
			/*Al enviar el formulario, convierte los tags en un string con los id's de cada uno para poder gestionarlos desde el controlador*/	 
			$("#enviarFormularioArticulo").on("click",function(){
				event.preventDefault();
				var tempString="";
				$('.tag').each(function(i, tag) {
				    tempString+=$(tag).attr("data-id")+",";
				});
				$("#formularioArticulo").append("<input type='hidden' name='listadetags' value='"+tempString+"'>");
				$("#formularioArticulo").submit();				
			})
			
			//Para añadir tags a la base de datos
			$("#addTag").on("click",function(){
				if ($("#addTagText").val()!="") {
					var formData = new FormData();
			    	formData.append("nombretag",$("#addTagText").val());
			    	var ajaxReq = $.ajax({
						url : "../tag/guarda",
						type : 'POST',
			      		data : formData,
			      		cache : false,
			      		contentType : false,
			      		processData : false,
			      		xhr: function(){
					    	var xhr = $.ajaxSettings.xhr() ;
					    	return xhr ;
					   	}
					});
					//Ajax success
					ajaxReq.done(function(msg) {
						$("#listaTags").append("<li class='tag' data-id="+msg+">"+$("#addTagText").val()+"</li>");
					  	$("#addTagText").val("").focus();
					});    
					//Ajax fail
					ajaxReq.fail(function(jqXHR) {
						$("#alertMsg").text(jqXHR.responseText+"("+jqXHR.status+" - "+jqXHR.statusText+")");
					});
				};			
			});
			
			//FORMULARIO UPLOAD IMAGEN PRINCIPAL  
			$("#uploadConfirmar").on("click", function(e){
				event.preventDefault();
				$(this).prop("disabled",true);
				var form = $("#fileUploadForm")[0];
				var formData = new FormData(form);
				formData.append("idarticulo",$("#idarticulo").val());
								
				//Llamada Ajax
				var ajaxReq = $.ajax({
				    url : "../../file/uploadImgPrincipal",
				    type : 'POST',
				    data : formData,
				    cache : false,
				    contentType : false,
				    processData : false,
				    xhr: function(){
				    	var xhr = $.ajaxSettings.xhr() ;
				      	//Barra de progreso 
				    	xhr.upload.onprogress = function(event){
					       	var perc = Math.round((event.loaded / event.total) * 100);
					       	$("#progressBar").text(perc + "%");
					       	$("#progressBar").css("width",perc + "%");
			    		};
			       		return xhr ;
			  		},
			  		beforeSend: function( xhr ) {
				  		$("#alertMsg").text("").removeClass("alert alert-success alert-danger");
				  		$("#progressBar").text("");
				  		$("#progressBar").css("width","0%");
					}
			  	});

			    //Ajax success
			    ajaxReq.done(function(nombreImagen) {
				    $('#alertMsg').text("Imagen principal guardada correctamente").addClass("alert alert-success");
				    $("#imagen").val(nombreImagen.split("\\").pop().split("/").pop());
				    $("#file").val("");
				    $("#uploadConfirmar").prop("disabled",false);
			    });
			    
			    //Ajax fail
			    ajaxReq.fail(function(jqXHR) {
				    $("#alertMsg").text(jqXHR.responseText+"("+jqXHR.status+" - "+jqXHR.statusText+")");
				    $("#uploadConfirmar").prop("disabled",false);
			    });
			});	//FORMULARIO IMAGEN PRINCIPAL
			
			//CONFIGURACIÓN TINYMVC		
			tinymce.init({
				selector: "#tiny",
				entity_encoding : "raw",
				plugins: [
					"advlist autolink lists link image charmap print preview hr anchor pagebreak",
					"searchreplace wordcount visualblocks visualchars code fullscreen",
					"insertdatetime media nonbreaking save table directionality",
					"emoticons template paste textpattern"
				],
				toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
				toolbar2: "print preview media | forecolor backcolor emoticons",
				image_title: true,
				height: "800",
				file_picker_types: 'image',
				/* Custom upload image */
				file_picker_callback: function (cb, value, meta) {
					
					if (meta.filetype == 'image') {
						//Trigger del botón de file upload oculto
						$('#uploadImg').trigger('click');
						$("#uploadImg").unbind('change');
					    //Una vez selecciondo el archivo a subir
					    $("#uploadImg").on("change", function() {
					    	
					  		var file = this.files[0];
					        var reader = new FileReader();
							var formData = new FormData();
					     	formData.append("file", file);
					     	formData.append("idarticulo",$("#idarticulo").val());
					     	
					     	var uploadedFile="";
					     	
					     	$.ajax({
					     		url : "../../file/uploadImagen",
								type : 'POST',
								data : formData,
								contentType : false,
								processData : false,
								async: false,
								success: function(respuesta) {
									uploadedFile = respuesta;
							    }				     		
					     	});
					     			     
						    reader.onload = function (e) {
						    	cb(uploadedFile);
						    };
						    reader.readAsDataURL(file);
					  	});	     	
					}
				}			
			}) //tinymce.init		
		});
	</script>
</html>
