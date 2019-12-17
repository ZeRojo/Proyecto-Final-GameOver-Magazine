package gameover.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntUsuarioDetallesDAO;
import gameover.models.Usuario;
import gameover.models.UsuarioDetalles;

@Repository
public class UsuarioDetallesDAO implements IntUsuarioDetallesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override 
	public UsuarioDetalles getUsuarioDetalles(int idusuario) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.get(UsuarioDetalles.class, idusuario);
	}
	
	@Override
	public UsuarioDetalles getUsuarioDetalles(Usuario usuario) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.get(UsuarioDetalles.class, usuario.getIdusuario());
	}
	
	@Override
	public void saveUsuarioDetalles(UsuarioDetalles usuarioDetalles) {
		Session sesion=sessionFactory.getCurrentSession();
		if (!usuarioDetalles.getUsuario().getPassword().equals("")) {
			usuarioDetalles.getUsuario().setPassword(passwordEncoder.encode(usuarioDetalles.getUsuario().getPassword()));
		}
		sesion.saveOrUpdate(usuarioDetalles);
	}
	
	@Override
	public void saveUsuarioDetallesNoCrypt(UsuarioDetalles usuarioDetalles) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(usuarioDetalles);
	}
}