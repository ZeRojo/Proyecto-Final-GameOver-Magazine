<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Game Over Magazine - ${errorImg}</title>
		<meta name="description" content="Página no encontrada">
		<meta name="robots" content="noindex nofollow">
		<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
		<script src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/scripts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/scripts/ie10-viewport-bug-workaround.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Gafata' rel='stylesheet' type='text/css'>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css"/>
		<link href="${pageContext.request.contextPath}/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	</head>
	<body>
		<div class="page-wrap d-flex flex-row align-items-center">
    		<div class="container">
        		<div class="row justify-content-center">
            		<div class="col-md-12 text-center">
                		<span class="display-1 d-block"><img src="${pageContext.request.contextPath}/img/${errorImg}.png" alt="404"></span>
                		<div class="mb-4 lead">${errorMsg1}</div>
                		<div class="mb-4 lead">${errorMsg2}</div>
                		<a href="${pageContext.request.contextPath}/" class="btn btn-link">Ir a la página principal</a>
            		</div>
        		</div>
    		</div>
		</div>
	</body>
</html>
