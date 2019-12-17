package gameover.dao.iface;

import java.util.List;

import gameover.models.Usuario;

public interface IntUsuarioDAO {

	Usuario getUsuario(int idusuario);
	
	Usuario findUserByUserName(String nombre);
	
	List<Usuario> getUsuarios();
	
	List<Usuario> getUsuariosRango(int elementoInicial, int numeroElementos);
	
	int getNumeroUsuarios();
	
	void saveUsuario(Usuario usuario);
	
	void saveUsuarioCrypt(Usuario usuario);
	
	void saveUsuarioNoCrypt(Usuario usuario);
	
	Usuario saveAndRetrieveUsuario (Usuario usuario);
	
	void deleteUsuario(Usuario usuario);
}