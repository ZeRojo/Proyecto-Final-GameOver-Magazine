// Verificació del formulari

function verificaNom(element) {
	if(/^[a-z][a-z0-9]{4,8}$/g.test(element.value)) {
		return true;
	} else {
		element.value="";
		$("#form-error-nombre").html("Min.4,Max.9, sólo minúsculas y numeros menos la primera letra");
		element.focus();
		return false;
	}
};

function verificaNick(element) {
	if(/^[a-zA-Z][a-zA-Z0-9\d!@#$%^&*]{4,8}$/g.test(element.value)) {
		return true;
	} else {
		element.value="";
		$("#form-error-nombre").html("Min.4,Max.9, sólo minúsculas y numeros menos la primera letra");
		element.focus();
		return false;
	}
};

function verificaPasswordDoble(element1,element2) {
	if (/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{7,19}$/g.test(element1.value)) {
		if (element1.value!=element2.value) {
			$("#form-error-passw").html("Las contraseñas no coinciden");
			element1.focus();
			return false;
		} else {
			return true;
		}		
	} else {
		$("#form-error-passw").html("Min.8, Max.19, 1 mayus, 1 minus, 1 numero y 1 especial");
		element1.focus();
		return false;
	}
}

function verificaFormulariRegistre(formulari) {
	$(".error-form").html("");
	if (verificaNom(formulari.username)) {
		if (verificaPasswordDoble(formulari.password,formulari.password2)) {
			return true;
		} else return false;
	} else return false;
}

function verificaFormulariPerfil(formulari) {
	$(".error-form").html("");
	if (verificaNom(formulari.username)) {
		if (verificaNick(formulari.nickname)) {
			if (formulari.password.value!="" && formulari.password2.value!="") {
				if (verificaPasswordDoble(formulari.password,formulari.password2)) {
					return true;
				} else return false;
			} else return true;
		} else return false;
	} else return false;
}