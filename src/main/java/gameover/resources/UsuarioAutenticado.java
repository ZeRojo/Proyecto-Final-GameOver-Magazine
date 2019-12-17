package gameover.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntUsuarioDAO;
import gameover.models.Usuario;
import gameover.models.UsuarioDetalles;
import gameover.resources.iface.IntUsuarioAutenticado;

@Service("servicioUsuarioAutenticado")
public class UsuarioAutenticado implements IntUsuarioAutenticado {

	@Autowired
	private IntUsuarioDAO usuarioDAO;
	
	private String usuario;
	private Usuario usuarioLogeado;
	private UsuarioDetalles usuarioDetalles;
	private String[] rangos;
	
	public UsuarioAutenticado() {}
	
	@Override
	public void reset() {
		this.usuario=null;
		this.usuarioLogeado=null;
		this.usuarioDetalles=null;
		this.rangos=null;
	}
	
	@Override
	public boolean isEmparejado() {
		if (usuario==null) return false;
		else return true;
	}
	
	@Override
	public int getUsuarioAutenticadoId() {
		return usuarioLogeado.getIdusuario();
	}
	
	@Override
	public Usuario getUsuarioAutenticado() {
		return usuarioLogeado;
	}
	
	@Override
	@Transactional
	public void setUsuarioAutenticado() {
		usuario=getAuthentication().getName();
		usuarioLogeado=usuarioDAO.findUserByUserName(usuario);
		rangos=usuarioLogeado.getRangos().stream().map(a -> a.getRango()).toArray(String[]::new);
		usuarioDetalles=usuarioLogeado.getUsuarioDetalles();	
	}
	
	@Override
	public boolean isAuthenticated() {
		if (getAuthentication().getName().equals("anonymousUser")) return false;
		else return true;
	}
	
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@Override
	public String getUsuarioAutenticadoNickname() {
		return usuarioDetalles.getNombre();
	}
	
	@Override
	public String getUsuarioAutenticadoAvatar() {
		return usuarioDetalles.getAvatar();
	}
	
	@Override
	public String[] getRangos() {
		return rangos;
	}
	
	@Override
	public boolean isRango(String rango) {
		for (String rng : rangos) {
			if (rng.equals(rango)) return true;
		}
		return false;
	}
}