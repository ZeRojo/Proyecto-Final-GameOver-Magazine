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
						<input type="text" name="username" class="form-control" id="username" placeholder="Nombre de usuario" required>
					</div>
					<div class="form-group">
						<label for="password">Contraseña:</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" required>
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
			<form:form id="formRegistroUsuario" action="${pageContext.request.contextPath}/nuevoUsuario" method="post" onsubmit="return verificaFormulariRegistre(this);">
				<input type="hidden" name="url" id="redirectUrl2" value="">
				<div class="modal-body">
	 				<div class="form-group">
	 					<label for="username">Nombre de usuario:</label>
						<input type="text" name="username" class="form-control" id="username" placeholder="Nombre de usuario" required>
					</div>
					<div id="form-error-nombre" class="error-form bg-danger"></div>
					<div class="form-group">
						<label for="password">Contraseña:</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" required>
					</div>
					<div class="form-group">
						<label for="password2">Repita contraseña:</label>
						<input type="password" class="form-control" id="password2" name="password2" placeholder="Repita	contraseña" required>
					</div>
					<div id="form-error-passw" class="error-form bg-danger"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
					<input type="submit" class="btn btn-primary" value="Registrarse">
				</div>
			</form:form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	var urlParams = new URLSearchParams(window.location.search);
	$(document).ready(function(){
		var redirectUrl = (window.location.pathname).replace("/gameover", "");
		$("#redirectUrl").val(redirectUrl);
		$("#redirectUrl2").val(redirectUrl);
		if (urlParams.has('error')) $("#login-modal").modal('show');
		$('#login-modal').on('shown.bs.modal', function () {
 			$('#username').trigger('focus');
		});
	});
</script>