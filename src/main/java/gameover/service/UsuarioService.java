package gameover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntUsuarioDAO;
import gameover.dao.iface.IntUsuarioDetallesDAO;
import gameover.models.Usuario;
import gameover.models.UsuarioDetalles;
import gameover.service.iface.IntUsuarioService;

@Service("usuarioService")
public class UsuarioService implements IntUsuarioService {

	@Autowired
	private IntUsuarioDAO usuarioDAO;
	
	@Autowired
	private IntUsuarioDetallesDAO usuarioDetallesDAO;
	
	@Override
	@Transactional
	public Usuario getUsuario(int idusuario) {
		return usuarioDAO.getUsuario(idusuario);
	}
	
	@Override
	@Transactional
	public Usuario findUserByUserName(String nombre) {
		Usuario tempusuario=usuarioDAO.findUserByUserName(nombre);
		System.out.println("service "+tempusuario);
		return tempusuario;
	}
	
	@Override	
	@Transactional
	public List<Usuario> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}
	
	@Override
	@Transactional
	public List<Usuario> getUsuariosRango(int elementoInicial, int numeroElementos) {
		return usuarioDAO.getUsuariosRango(elementoInicial, numeroElementos);
	}
	
	@Override
	@Transactional
	public int getNumeroUsuarios() {
		return usuarioDAO.getNumeroUsuarios();
	}
		
	@Override
	@Transactional
	public void saveUsuario(Usuario usuario) {
		usuarioDAO.saveUsuario(usuario);
	}
	
	@Override
	@Transactional
	public void saveUsuarioCrypt(Usuario usuario) {
		usuarioDAO.saveUsuarioCrypt(usuario);
	}
	
	@Override
	@Transactional
	public void saveUsuarioNoCrypt(Usuario usuario) {
		usuarioDAO.saveUsuarioNoCrypt(usuario);
	}
	
	@Override
	@Transactional
	public Usuario saveAndRetrieveUsuario(Usuario usuario) {
		return usuarioDAO.saveAndRetrieveUsuario(usuario);
	}
	
	@Override
	@Transactional
	public void deleteUsuario(Usuario usuario) {
		usuarioDAO.deleteUsuario(usuario);
	}
	
	@Override
	@Transactional
	public UsuarioDetalles getUsuarioDetalles(int idusuario) {
		return usuarioDetallesDAO.getUsuarioDetalles(idusuario);
	}
		
	@Override
	@Transactional
	public void saveUsuarioDetalles(UsuarioDetalles usuarioDetalles) {
		usuarioDetallesDAO.saveUsuarioDetalles(usuarioDetalles);
	}
	
	@Override
	@Transactional
	public void saveUsuarioDetallesNoCrypt(UsuarioDetalles usuarioDetalles) {
		usuarioDetallesDAO.saveUsuarioDetallesNoCrypt(usuarioDetalles);
	}
}