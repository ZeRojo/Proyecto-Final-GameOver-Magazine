package gameover.resources.iface;

import java.util.Set;

import gameover.models.Articulo;
import gameover.models.Rango;
import gameover.models.Usuario;
import gameover.resources.ValidationErrors;

public interface IntCustomValidations {
	
	void reset();
	
	ValidationErrors getValidationErrors();
	
	boolean validaNombreUsuario(String nombre_usuario, int id);
	
	boolean validaNickName(String nickname);
	
	boolean validaPasswordsIguales(String password1, String password2);
	
	boolean validaPassword(String password);
	
	boolean validaRoles(Set<Rango> rangos);
			
	boolean validaNombreArticulo(String nombre_articulo);
	
	boolean validaNombreArticuloNoCheck(String nombre_articulo);
	
	boolean validaImagen(String imagen);
	
	boolean validaNombreCategoria(String nombre_categoria);
	
	boolean validaSelectCategoria(int idcategoria);
	
	boolean validaNombreTipo(String nombre_tipo);
	
	boolean validaSelectTipo(int idtipo);
	
	ValidationErrors validateUsuario(Usuario usuario, String password2);
	
	ValidationErrors validateUsuarioEmptyPass(Usuario usuario);
	
	ValidationErrors validateArticulo(Articulo articulo);
	
	ValidationErrors validateCategoria(String nombre, int id);
	
	ValidationErrors validateTipo(String nombre, int id);
}
