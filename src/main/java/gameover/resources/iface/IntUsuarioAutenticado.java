package gameover.resources.iface;

import org.springframework.security.core.Authentication;

import gameover.models.Usuario;

public interface IntUsuarioAutenticado {

	void reset();
	
	boolean isEmparejado();
	
	int getUsuarioAutenticadoId();

	Usuario getUsuarioAutenticado();
	
	void setUsuarioAutenticado();
	
	boolean isAuthenticated();
	
	Authentication getAuthentication();
	
	String getUsuarioAutenticadoNickname();
	
	String getUsuarioAutenticadoAvatar();
		
	String[] getRangos();
	
	boolean isRango(String rango);
}