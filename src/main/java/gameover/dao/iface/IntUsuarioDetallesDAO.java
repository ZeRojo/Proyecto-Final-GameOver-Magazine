package gameover.dao.iface;

import gameover.models.Usuario;
import gameover.models.UsuarioDetalles;

public interface IntUsuarioDetallesDAO {

	UsuarioDetalles getUsuarioDetalles(int idusuario);
	
	UsuarioDetalles getUsuarioDetalles(Usuario usuario);
	
	void saveUsuarioDetalles(UsuarioDetalles usuarioDetalles);

	void saveUsuarioDetallesNoCrypt(UsuarioDetalles usuarioDetalles);
}