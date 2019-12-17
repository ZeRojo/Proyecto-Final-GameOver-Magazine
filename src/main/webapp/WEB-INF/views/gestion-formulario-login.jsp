<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>GameOver - Acceso de usuario</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link href='https://fonts.googleapis.com/css?family=Gafata' rel='stylesheet' type='text/css'>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos_gestion.css"/>
		<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
		<script src="https://kit.fontawesome.com/19d4302e93.js" crossorigin="anonymous"></script>
	</head>
	<body>
		<header>
			<div>
				<img src="${pageContext.request.contextPath}/img/logoblanco.png">
			</div>
		</header>
		<div class="contenedor">
			<div class="row flex-center">
            	<div class="col-sm-6 col-sm-offset-3 form-box">
                	<div class="form-top">
                    	<div class="form-top-left">
                        	<h1>Acceso de usuario</h1>
                        </div>
                        <div class="form-top-right">
                        	<i class="fas fa-lock fa-x2"></i>
                        </div>
					</div>
                    <div class="form-bottom">
			        	<form:form action="${pageContext.request.contextPath}/authUsuario" method="post" class="login-form">
	                    	<div class="form-group">
	                    		<input type="hidden" name="gestion" value="true">
	                    		<label class="sr-only" for="username">Usuario</label>
	                        	<input type="text" id="username" name="username" placeholder="Usuario..." class="form-username form-control" required>
	                        </div>
	                        <div class="form-group">
	                        	<label class="sr-only" for="password">Contraseña</label>
	                        	<input type="password" name="password" placeholder="Contraseña..." class="form-password form-control" required>
	                        </div>
	                        <c:if test="${param.error!=null}">
	                        	<div class="alert alert-danger center-text ngr">Nombre de usuario o contraseña incorrectos</div>
	                        </c:if>
	                        <c:if test="${param.userLogout!=null}">
	                        	<div class="alert alert-warning center-text ngr">Ha salido del sistema</div>
	                        </c:if>
	                        <button type="submit" class="btn">Acceder</button>
	                    </form:form>
                    </div>
                 </div>
            </div>
		</div>
	</body>
	<script>
		$(document).ready(function(){
			$("#username").focus();
		});
	</script>
</html>