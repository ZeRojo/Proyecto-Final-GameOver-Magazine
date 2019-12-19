<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" tabindex="-1" role="dialog" id="login-modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Acceso de usuario</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			<form:form action="${pageContext.request.contextPath}/authUsuario" method="post">
				<input type="hidden" name="gestion" value="false">
				<input type="hidden" name="url" id="redirectUrl" value="">
				<div class="modal-body">
	 				<div class="form-group">
	 					<label for="username">Nombre de usuario:</label>
						<input type="text" name="username" class="form-control" id="username" placeholder="Nombre de usuario">
					</div>
					<div class="form-group">
						<label for="password">Contraseña:</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="Contraseña">
					</div>
					<c:if test="${param.error!=null}">
						<div class="alert alert-danger">Nombre o contraseña incorrectos.</div>
					</c:if>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
					<input type="submit" class="btn btn-primary" value="Acceder">
				</div>
			</form:form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" tabindex="-1" role="dialog" id="regin-modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Registro de usuario</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			<form:form id="formRegistroUsuario" action="${pageContext.request.contextPath}/nuevoUsuario" method="post">
				<input type="hidden" name="url" id="redirectUrl2" value="">
				<div class="modal-body">
	 				<div class="form-group">
	 					<label for="username">Nombre de usuario:</label>
						<input type="text" name="username" class="form-control" id="nombreRegistro" placeholder="Nombre de usuario">
					</div>
					<div id="form-error-nombre" class="error-form bg-danger"></div>
					<div class="form-group">
						<label for="password">Contraseña:</label>
						<input type="password" class="form-control" id="passwordRegistro" name="password" placeholder="Contraseña">
					</div>
					<div class="form-group">
						<label for="password2">Repita contraseña:</label>
						<input type="password" class="form-control" id="password2Registro" name="password2" placeholder="Repita	contraseña">
					</div>
					<div id="regFormMsg" class="error-form bg-danger formError"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
					<input id="sendRegistroUsuario" type="submit" class="btn btn-primary" value="Registrarse">
				</div>
			</form:form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	});
	var urlParams = new URLSearchParams(window.location.search);
	$(document).ready(function(){
		var redirectUrl = (window.location.pathname).replace("/gameover", "");
		$("#redirectUrl").val(redirectUrl);
		$("#redirectUrl2").val(redirectUrl);
		if (urlParams.has('error')) $("#login-modal").modal('show');
		$('#login-modal').on('shown.bs.modal', function () {
 			$('#username').trigger('focus');
		});
		
		/*ENVIO REGISTRO NUEVO USUARIO*/
		$("#sendRegistroUsuario").on("click", function(e){
			event.preventDefault();
			var formData = new FormData();
			formData.append("nombre",$("#nombreRegistro").val());
			formData.append("password1",$("#passwordRegistro").val());
			formData.append("password2",$("#password2Registro").val());
			// Llamada ajax
			var ajaxReq = $.ajax({
				url : "/gameover/checkNuevoUsuario",
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
					$('#regFormMsg').text('').removeClass("alert alert-success alert-danger formError");
				}
			});
			// Ajax success
			ajaxReq.done(function(data) {
				$("#formRegistroUsuario").submit();
			});
			//Ajax fail
			ajaxReq.fail(function(jqXHR) {
				$("#regFormMsg").text(jqXHR.responseText).addClass("alert alert-danger formError");
			});
		});
	});
</script>