package gameover.service.iface;

import java.util.List;

import gameover.models.Usuario;
import gameover.models.UsuarioDetalles;

public interface IntUsuarioService {
	
	boolean checkUsuarioExists(String nombre, int id);
	
	Usuario getUsuario(int idusuario);
	
	Usuario findUserByUserName(String nombre);
	
	List<Usuario> getUsuarios();
	
	List<Usuario> getUsuariosRango(int elementoInicial, int numeroElementos);
	
	int getNumeroUsuarios();
	
	void saveUsuario(Usuario usuario);
	
	void saveUsuarioCrypt(Usuario usuario);
	
	void saveUsuarioNoCrypt(Usuario usuario);
	
	Usuario saveAndRetrieveUsuario(Usuario usuario);
	
	void deleteUsuario(Usuario usuario);
	
	UsuarioDetalles getUsuarioDetalles(int idusuario);
	
	void saveUsuarioDetalles(UsuarioDetalles usuarioDetalles);
	
	void saveUsuarioDetallesNoCrypt(UsuarioDetalles usuarioDetalles);
}