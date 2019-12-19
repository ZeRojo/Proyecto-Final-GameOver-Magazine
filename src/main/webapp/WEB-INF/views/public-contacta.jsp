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
		<title>Game Over Magazine - Formulario de contacto</title>
		<meta name="description" content="Artículo">
		<meta name="robots" content="noindex nofollow">
		<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
		<script src="https://kit.fontawesome.com/19d4302e93.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/scripts/ie10-viewport-bug-workaround.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/scripts/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/slick.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery.custom-scrollbar.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery.jscroll.min.js" type="text/javascript"></script>
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
	        <div id="formulari-contacte">
			<div class="container">
			    <form class="well form-horizontal" action="javascript:enviaForm ()" method="post"  id="formulari" onsubmit="return verificaFormulari(this);">
					<fieldset>
						<legend>Contacta amb nosaltres!</legend>
						<small>Els camps marcats amb <span class="vermell"><i class="fa fa-asterisk" aria-hidden="true"></i></span> són obligatoris.</small>
						<!-- Nom -->
						<div class="form-group">
  							<label for="nom" class="col-md-4 control-label">Nom <span class="vermell"><i class="fa fa-asterisk" aria-hidden="true"></i></span></label>  
  							<div class="col-md-4 inputGroupContainer">
  								<div class="input-group">
  									<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
  									<input  name="nom" id="nom" placeholder="Nom" class="form-control" type="text" onblur="verificaNom(this);">
    							</div>
  							</div>
						</div>
						<!-- Cognom -->
						<div class="form-group">
  							<label for="cognom" class="col-md-4 control-label">Cognom <span class="vermell"><i class="fa fa-asterisk" aria-hidden="true"></i></span></label> 
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
									<input name="cognom" id="cognom" placeholder="Cognom" class="form-control" type="text" onblur="verificaCognom(this);">
								</div>
							</div>
						</div>
						<!-- E-Mail -->
						<div class="form-group">
							<label for="correu" class="col-md-4 control-label">Correu electrònic <span class="vermell"><i class="fa fa-asterisk" aria-hidden="true"></i></span></label>  
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
									<input name="correu" id="correu" placeholder="Correu electrònic" class="form-control" type="email" onblur="verificaCorreu(this);">
								</div>
							</div>
						</div>
						<!-- Telèfon mòbil -->
						<div class="form-group">
							<label for="mobil" class="col-md-4 control-label">Telèfon mòbil</label>  
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
									<input name="mobil" id="mobil" placeholder="Telèfon mòbil" class="form-control" type="text" onblur="verificaTelf(this);" onkeypress="return validarNums(event);">
								</div>
							</div>
						</div>
						<!-- Adreça -->
						<div class="form-group">
							<label for="direccio" class="col-md-4 control-label">Adreça</label>  
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
									<input name="direccio" id="direccio" placeholder="Adreça" class="form-control" type="text">
								</div>
							</div>
						</div>
						<!-- Població -->
						<div class="form-group">
							<label for="poblacio" class="col-md-4 control-label">Població</label>  
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
									<input name="poblacio" id="poblacio" placeholder="Població" class="form-control" type="text" onblur="verificaPoblacio(this);">
								</div>
							</div>
						</div>
						<!-- Provincia -->
						<div class="form-group"> 
							<label for="provincia" class="col-md-4 control-label">Provincia</label>
							<div class="col-md-4 selectContainer">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
										<select name="provincia" id="provincia" class="form-control selectpicker">
										<option value=" " >Sel·lecciona...</option>
										<option value='Àlava'>Àlava</option>
										<option value='Alacant'>Alacant</option>
										<option value='Albacete'>Albacete</option>
										<option value='Almeria'>Almeria</option>
										<option value='Astúries'>Astúries</option>
										<option value='Àvila'>Àvila</option>
										<option value='Badajoz'>Badajoz</option>
										<option value='Illes Balears'>Illes Balears</option>
										<option value='Barcelona'>Barcelona</option>
										<option value='Biscaia'>Biscaia</option>
										<option value='Burgos'>Burgos</option>
										<option value='Càceres'>Càceres</option>
										<option value='Cadis'>Cadis</option>
										<option value='Cantàbria'>Cantàbria</option>
										<option value='Castelló'>Castelló</option>
										<option value='Ceuta'>Ceuta</option>
										<option value='Ciudad Real'>Ciudad Real</option>
										<option value='Conca'>Conca</option>
										<option value='Còrdova'>Còrdova</option>
										<option value='La Corunya'>La Corunya</option>
										<option value='Girona'>Girona</option>
										<option value='Granada'>Granada</option>
										<option value='Guadalajara'>Guadalajara</option>
										<option value='Guipúscoa'>Guipúscoa</option>
										<option value='Huelva'>Huelva</option>										
										<option value='Jaén'>Jaén</option>										
										<option value='Lleida'>Lleida</option>
										<option value='Lleó'>Lleó</option>
										<option value='Lugo'>Lugo</option>
										<option value='Madrid'>Madrid</option>
										<option value='Màlaga'>Màlaga</option>
										<option value='Melilla'>Melilla</option>
										<option value='Múrcia'>Múrcia</option>
										<option value='Navarra'>Navarra</option>
										<option value='Osca'>Osca</option>
										<option value='Ourense'>Ourense</option>
										<option value='Palència'>Palència</option>
										<option value='Las Palmas'>Las Palmas</option>
										<option value='Pontevedra'>Pontevedra</option>
										<option value='La Rioja'>La Rioja</option>
										<option value='Salamanca'>Salamanca</option>
										<option value='Santa Cruz de Tenerife'>Santa Cruz de Tenerife</option>
										<option value='Saragossa'>Saragossa</option>
										<option value='Segòvia'>Segòvia</option>
										<option value='Sevilla'>Sevilla</option>
										<option value='Sòria'>Sòria</option>
										<option value='Tarragona'>Tarragona</option>										
										<option value='Terol'>Terol</option>
										<option value='Toledo'>Toledo</option>
										<option value='València'>València</option>
										<option value='Valladolid'>Valladolid</option>									
										<option value='Zamora'>Zamora</option>
									</select>
								</div>
							</div>
						</div>
						<!-- Codi postal -->
						<div class="form-group">
							<label for="codipostal" class="col-md-4 control-label">Codi Postal <span class="vermell"><i class="fa fa-asterisk" aria-hidden="true"></i></span></label>  
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
									<input name="codipostal" id="codipostal" placeholder="Codi Postal" class="form-control" type="text" onblur="verificaCodiPostal(this);" onkeypress="return validarNums(event);">
								</div>
							</div>
						</div>
						<!-- Missatge -->		
						<div class="form-group">
							<label for="missatge" class="col-md-4 control-label">Missatge <span class="vermell"><i class="fa fa-asterisk" aria-hidden="true"></i></span><br><span class="vermell" id="errmsg"></span></label>
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
									<textarea class="form-control" name="missatge" id="missatge" placeholder="Missatge" onblur="verificaMissatge(this);"></textarea>
								</div>
							</div>
						</div>
 						<div class="form-group pdades-check">
                        	<input name="condicions" type="checkbox" id="condicions" value="accepta">
                        	<label for="condicions" class="enlinia"><a href="#" class="osx">En enviar les seves dades declara haver llegit i accepta les condicions de la nostra política de privadesa i de protecció de dades de caràcter personal</a></label>
 						</div>    
						<!-- Alerta d'èxit -->
						<div class="alert alert-success" role="alert" id="missatge-exit">Èxit <i class="glyphicon glyphicon-thumbs-up"></i> Gràcies per contactar amb nosaltres, et contestarem en breu.</div>
						<!-- Alerta d'Error -->
						<div class="alert alert-danger" role="alert" id="missatge-error">Error <i class="glyphicon glyphicon-thumbs-down"></i> Hi ha hagut algún problema, torna a provar-ho passats uns minuts.</div> 
						<!-- Botó enviar/reset -->
						<div class="form-group">
							<label class="col-md-4 control-label"></label>
							<div class="col-md-4 botons-form">
								<button type="submit" class="btn btn-pers">Enviar <span class="glyphicon glyphicon-send"></span></button>
								<button type="reset" class="btn btn-pers">Reset <span class="glyphicon glyphicon-trash"></span></button>
							</div>
						</div>
					</fieldset>
				</form>
 			<div class="push"></div>
		</div>
 		<%@ include file="public-footer.jsp" %>
	</body>
</html>