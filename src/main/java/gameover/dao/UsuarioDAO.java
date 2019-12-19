package gameover.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntUsuarioDAO;
import gameover.models.Usuario;

@Repository
public class UsuarioDAO implements IntUsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
 
	@Override
	public boolean checkUsuarioExists(String nombre, int id) {
		Session sesion=sessionFactory.getCurrentSession();
		if (((Long) sesion.createQuery("Select count(*) from Usuario where nombre=:nombre and idusuario<>:idusuario").setParameter("nombre", nombre).setParameter("idusuario", id).uniqueResult()).intValue()==0) return false;
		else return true;
	}
	
	
	@Override
	public Usuario getUsuario(int idusuario) {
		Session sesion=sessionFactory.getCurrentSession();
		Usuario usuario=sesion.createQuery("from Usuario u left join fetch u.rangos where u.idusuario=:idusuario",Usuario.class).setParameter("idusuario", idusuario).uniqueResult();
		return usuario;
	}
	
    @Override
	public Usuario findUserByUserName(String nombre) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Usuario u left join fetch u.rangos where nombre=:nombre",Usuario.class).setParameter("nombre", nombre).uniqueResult();
	}
	
	@Override
	public List<Usuario> getUsuarios() {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Usuario",Usuario.class).list();
	}
	
	@Override
	public List<Usuario> getUsuariosRango(int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();		
		List<Usuario> usuarioslist=sesion.createQuery("FROM Usuario ORDER BY nombre",Usuario.class).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
		for (Usuario usuario:usuarioslist) {
			usuario.getRangos().size();
		}
		return usuarioslist;
	}
	
	@Override
	public int getNumeroUsuarios() {
		Session sesion=sessionFactory.getCurrentSession();
		return ((Long) sesion.createQuery("Select count(*) from Usuario").uniqueResult()).intValue();
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		Session sesion=sessionFactory.getCurrentSession();
		if (usuario.getPassword().equals("")) {
			Usuario tempUsuario=sesion.get(Usuario.class, usuario.getIdusuario());
			tempUsuario.setNombre(usuario.getNombre());
			tempUsuario.setRangos(usuario.getRangos());
			tempUsuario.setActivo(usuario.isActivo());
			tempUsuario.setUsuarioDetalles(usuario.getUsuarioDetalles());
			sesion.saveOrUpdate(tempUsuario);
		} else {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			sesion.saveOrUpdate(usuario);
		}
	}
	
	@Override
	public void saveUsuarioCrypt(Usuario usuario) {
		Session sesion=sessionFactory.getCurrentSession();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		sesion.saveOrUpdate(usuario);
	}
	
	@Override
	public void saveUsuarioNoCrypt(Usuario usuario) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(usuario);
	}
	
	@Override
	public Usuario saveAndRetrieveUsuario(Usuario usuario) {
		Session sesion=sessionFactory.getCurrentSession();
		if (usuario.getPassword()!=null) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		}
		sesion.saveOrUpdate(usuario);
		sesion.saveOrUpdate(usuario.getUsuarioDetalles());
		return usuario;
	}
	
	@Override
	public void deleteUsuario(Usuario usuario) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.delete(usuario);
	}
}