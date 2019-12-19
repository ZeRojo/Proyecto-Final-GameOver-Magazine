<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="siUsuari">
	<form:form id="formlogout" action="${pageContext.request.contextPath}/userLogout" method="GET">
		<input type="hidden" name="gestion" value="false">
		<input type="hidden" id="redirectUrl" name="url" value="">
	</form:form>
</div>
<div class="modal fade" tabindex="-1" role="dialog" id="perfil-modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Perfil de usuario</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			<div class="modal-body">
				<form:form id="formPerfilUsuario" action="${pageContext.request.contextPath}/editarUsuario" method="post">
					<input type="hidden" name="url" id="redirectUrl2" value="">
					<input type="hidden" name="avatar" id="inputAvatar" value="${usuarioAvatar}"/>
	 				<div class="form-group">
	 					<label for="username">Nombre de usuario:</label>
						<input type="text" name="username" class="form-control" id="username" placeholder="Nombre de usuario" value="<sec:authentication property='name'/>">
					</div>
					<div class="form-group">
	 					<label for="nickname">Nickname:</label>
						<input type="text" name="nickname" class="form-control" id="nickname" placeholder="Nickname" value="${usuarioNickname}">
					</div>
					<div class="form-group">
						<label for="password">Contraseña:</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="Contraseña">
					</div>
					<div class="form-group">
						<label for="password2">Repita contraseña:</label>
						<input type="password" class="form-control" id="password2" name="password2" placeholder="Contraseña">
					</div>
					<div id="regFormMsg" class="error-form bg-danger"></div>
				</form:form>
				<div class="img-btn-grp">
					<img id="imgAvatar" src="${variables.rutaImagenes}/avatar/${usuarioAvatar}"/>
					<form action="file/upload" id="avatarUploadForm" method="post" enctype="multipart/form-data"> 
						<input type="file" name="file" class="custom-file-input" id="avatarFile">
						<button type="button" id="uploadAvatar" class="form-control btn btn-primary">Subir avatar</button>
					</form>
				</div>
				<div id="avatarMsg"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
				<input type="button" id="sendPerfilForm" class="btn btn-primary" value="Guardar">
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->	
<script>
	$(document).ready(function(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		});
		$("#username").val($("#username").val().toLowerCase());
		var redirectUrl = (window.location.pathname).replace("/gameover", "");
		$("#redirectUrl").val(redirectUrl);
		$("#redirectUrl2").val(redirectUrl);
		$("#logout").on("click", function(){
			$("#formlogout").submit();
		});
		$("#editperfil").on("click",function(){
			$("#perfil-modal").modal("show");
		});
		
		/*ENVIO CAMBIO PERFIL USUARIO*/
		$("#sendPerfilForm").on("click", function(){
			event.preventDefault();
			var formData = new FormData();
			formData.append("nombre",$("#username").val());
			formData.append("password1",$("#password").val());
			formData.append("password2",$("#password2").val());
			// Llamada ajax
			var ajaxReq = $.ajax({
				url : "/gameover/checkEditarUsuario",
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
				$("#formPerfilUsuario").submit();
			});
			//Ajax fail
			ajaxReq.fail(function(jqXHR) {
				$("#regFormMsg").text(jqXHR.responseText).addClass("alert alert-danger formError");
			});
		});
			
		$("#uploadAvatar").on("click",function(){
			//Desabilitamos el boton temporalmente
			$("#avatarFile").trigger("click");
		})
		
		$("#avatarFile").on("change",function(){
			$("#uploadConfirmar").prop("disabled",true);
			var formData=new FormData($("#avatarUploadForm")[0]);
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
			         return xhr ;
				}				
			}); //ajaxRequest
			
			//Upload correcto
			ajaxReq.done(function(nombreArchivo) {
				$("#avatarMsg").text("Avatar guardado correctamente").addClass("bg-success");
				$("#inputAvatar").val(nombreArchivo.split("\\").pop().split("/").pop());
				$("#imgAvatar").attr("src",nombreArchivo);
				$("#uploadConfirmar").prop("disabled",false);
			});
			
			//Upload fallido
			ajaxReq.fail(function(jqXHR) {
				$("#alertMsg").text(jqXHR.responseText+"("+jqXHR.status+" - "+jqXHR.statusText+")").addClass("alert alert-danger");
				$('#uploadConfirmar').prop('disabled',false);
			});				
		}); //uploadAvatarConfirmar onclick			
	})

</script>