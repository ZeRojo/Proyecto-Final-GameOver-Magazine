package gameover.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import gameover.models.Articulo;
import gameover.models.Rango;
import gameover.models.Usuario;
import gameover.resources.iface.IntCustomValidations;
import gameover.resources.iface.IntGlobalVariables;
import gameover.service.iface.IntArticuloService;
import gameover.service.iface.IntCategoriaService;
import gameover.service.iface.IntTipoService;
import gameover.service.iface.IntUsuarioService;

@Service
@Scope("prototype")
public class CustomValidations implements IntCustomValidations {

	@Autowired
	private IntUsuarioService usuarioService;
	
	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
	private IntArticuloService articuloService;
	
	@Autowired
	private IntCategoriaService categoriaService;
	
	@Autowired
	private IntTipoService tipoService;
	
	private ValidationErrors validationErrors=new ValidationErrors();
	
	public void reset() {
		validationErrors.reset();
	}
	
	public ValidationErrors getValidationErrors() {
		return validationErrors;
	}
		
	public boolean validaNombreUsuario(String nombre_usuario, int id) {
		if (nombre_usuario.equals(""))  {
			validationErrors.setNombreError("El nombre de usuario es obligatorio.");
			return true;
		} else {
			if (nombre_usuario.length()>25) {
				validationErrors.setNombreError("El nombre de usuario es demasiado largo. Máximo 25 caracteres.");
				return true;
			} else {
				final Pattern pattern = Pattern.compile("[a-z]{1}[a-z0-9_.]{3,}");
				if (!pattern.matcher(nombre_usuario).matches()) {
					validationErrors.setNombreError("Mínimo 4 caracteres, todo minúsculas y números excepto el primer carácter.");
					return true;
				} else {
					if (usuarioService.checkUsuarioExists(nombre_usuario, id)) {
						validationErrors.setNombreError("Ya existe un usuario con este nombre.");
						return true;
					} else return false;	
				}
			}
		}
	}
	
	public boolean validaNickName(String nickname) {
		if (nickname.equals("")) {
			validationErrors.setNicknameError("Este campo es obligatorio.");
			return true;
		} else {
			if (nickname.length()>25) {
				validationErrors.setNicknameError("El NickName es demasiado largo. Máximo 25 caracteres.");
				return true;
			} else {
				final Pattern pattern = Pattern.compile("[A-Za-z0-9\\d@$!%*?&]{4,}");
				if (!pattern.matcher(nickname).matches()) {
					validationErrors.setNicknameError("Mínimo 4 caracteres, cualquier letra, número y los caracteres @$!%*?&");
					return true;
				} else return false;
			}
		}
	}
	
	public boolean validaPasswordsIguales(String password1, String password2) {
		if (!password1.equals(password2)) {
			validationErrors.setPasswordError("Las contraseñas no coinciden.");
			return true;
		} else return false;
	}
	
	public boolean validaPassword(String password) {
		if (password.equals("")) {
			validationErrors.setPasswordError("La contraseña es obligatoria.");
			return true;
		} else {
			final Pattern pattern = Pattern.compile("((?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,})");
			if (!pattern.matcher(password).matches()) {
				validationErrors.setPasswordError("Mínimo 8 caracteres, 1 mayúscula, 1 minúscula, 1 número y 1 caracter especial.");
				return true;
			} else return false;
		}
	}
	
	public boolean validaRoles(Set<Rango> rangos) {
		if (rangos.size()==0) {		
			validationErrors.setRangoError("Ha de seleccionar como mínimo 1 rango.");
			return true;
		} else return false;
	}
	
	public boolean validaNombreArticulo(String nombre_articulo) {
		if (nombre_articulo.equals("")) {
			validationErrors.setNombreArticuloError("Este campo es obligatorio.");
			return true;
		} else {
			if (nombre_articulo.length()>255) {
				validationErrors.setNombreArticuloError("El campo tiene que tener como máximo 255 caracteres.");
				return true;
			} else {
				if (articuloService.checkArticuloExists(variables.sanearCadena(nombre_articulo))) {
					validationErrors.setNombreArticuloError("Ya existe un artículo con este nombre.");
					return true;
				} else return false;
			}
		}
	}
	
	public boolean validaNombreArticuloNoCheck(String nombre_articulo) {
		if (nombre_articulo.equals("")) {
			validationErrors.setNombreArticuloError("Este campo es obligatorio.");
			return true;
		} else {
			if (nombre_articulo.length()>255) {
				validationErrors.setNombreArticuloError("El campo tiene que tener como máximo 255 caracteres.");
				return true;
			} else return false;
		}
	}
	
	public boolean validaImagen(String imagen) {
		if (imagen.equals("")) {
			validationErrors.setImagenError("Este campo es obligatorio.");
			return true;
		} else return false;
	}
	
	public boolean validaNombreCategoria(String nombre_categoria) {
		if (nombre_categoria.equals("")) {
			validationErrors.setNombreCategoriaError("Este campo es obligatorio.");
			return true;
		} else {
			if (nombre_categoria.length()>20) {
				validationErrors.setNombreCategoriaError("El nombre es demasiado largo. Máximo 20 caracteres.");
				return true;
			} else {
				final Pattern pattern = Pattern.compile("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]{3,}+$");
				if (!pattern.matcher(nombre_categoria).matches()) {
					validationErrors.setNombreCategoriaError("Mínimo 4 caracteres y cualquier letra con o sin tílde");
					return true;
				} else {
					if (categoriaService.checkCategoriaExists(variables.sanearCadena(nombre_categoria))) {
						validationErrors.setNombreCategoriaError("Ya existe una categoría con este nombre.");
						return true;
					} else return false;
				}
			}
		}
	}
	
	public boolean validaSelectCategoria(int idcategoria) {
		if (idcategoria==0) {
			validationErrors.setSelectCategoriaError("Por favor seleccione una categoria.");
			return true;
		} else return false;
	}
	
	public boolean validaNombreTipo(String nombre_tipo) {
		if (nombre_tipo.equals("")) {
			validationErrors.setNombreTipoError("Este campo es obligatorio.");
			return true;
		} else {
			if (nombre_tipo.length()>15) {
				validationErrors.setNombreTipoError("El nombre es demasiado largo. Máximo 15 caracteres.");
				return true;
			} else {
				final Pattern pattern = Pattern.compile("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]{3,}+$");
				if (!pattern.matcher(nombre_tipo).matches()) {
					validationErrors.setNombreTipoError("Mínimo 4 caracteres y cualquier letra con o sin tílde");
					return true;
				} else {
					if (tipoService.checkTipoExists(variables.sanearCadena(nombre_tipo))) {
						validationErrors.setNombreTipoError("Ya existe un Tipo con este nombre.");
						return true;
					} else return false;
				}
			}
		}
	}
	
	public boolean validaSelectTipo(int idtipo) {
		if (idtipo==0) {
			validationErrors.setSelectTipoError("Por favor seleccione un tipo.");
			return true;
		} else return false;
	}
		
	public ValidationErrors validateUsuario(Usuario usuario, String password2) {
		List<Boolean> validaciones=new ArrayList<Boolean>();
		validaciones.add(validaNombreUsuario(usuario.getNombre(),usuario.getIdusuario()));
		validaciones.add(validaPasswordsIguales(usuario.getPassword(),password2));
		validaciones.add(validaPassword(usuario.getPassword()));
		validaciones.add(validaNickName(usuario.getUsuarioDetalles().getNombre()));
		validaciones.add(validaRoles(usuario.getRangos()));
		for (Boolean haserror:validaciones) {
			if (haserror) validationErrors.setErrors(true); 
		}
		return validationErrors;
	}
	
	public ValidationErrors validateUsuarioEmptyPass(Usuario usuario) {
		List<Boolean> validaciones=new ArrayList<Boolean>();
		validaciones.add(validaNombreUsuario(usuario.getNombre(),usuario.getIdusuario()));
		validaciones.add(validaNickName(usuario.getUsuarioDetalles().getNombre()));
		validaciones.add(validaRoles(usuario.getRangos()));
		for (Boolean haserror:validaciones) {
			if (haserror) validationErrors.setErrors(true); 
		}
		return validationErrors;
		
	}
	
	public ValidationErrors validateUsuarioPublic(String nombre, String password1, String password2) {
		List<Boolean> validaciones=new ArrayList<Boolean>();
		validaciones.add(validaNombreUsuario(nombre,0));
		validaciones.add(validaPasswordsIguales(password1,password2));
		validaciones.add(validaPassword(password1));
		for (Boolean haserror:validaciones) {
			if (haserror) validationErrors.setErrors(true); 
		}
		return validationErrors;
	}
	
	public ValidationErrors validateArticulo(Articulo articulo) {
		List<Boolean> validaciones=new ArrayList<Boolean>();
		validaciones.add(validaNombreArticuloNoCheck(articulo.getNombre()));
		validaciones.add(validaImagen(articulo.getImagen()));
		for (Boolean haserror:validaciones) {
			if (haserror) validationErrors.setErrors(true); 
		}
		return validationErrors;
	}
	
	public ValidationErrors validateCategoria(String nombre, int id) {
		List<Boolean> validaciones=new ArrayList<Boolean>();
		validaciones.add(validaNombreCategoria(nombre));
		validaciones.add(validaSelectCategoria(id));
		for (Boolean haserror:validaciones) if (haserror) validationErrors.setErrors(true); 
		return validationErrors;
	}
	
	public ValidationErrors validateTipo(String nombre, int id) {
		List<Boolean> validaciones=new ArrayList<Boolean>();
		validaciones.add(validaNombreTipo(nombre));
		validaciones.add(validaSelectTipo(id));
		for (Boolean haserror:validaciones) if (haserror) validationErrors.setErrors(true); 
		return validationErrors;
	}
}