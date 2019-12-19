package gameover.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntTipoDAO;
import gameover.models.Tipo;

@Repository
public class TipoDAO implements IntTipoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean checkTipoExists(String nombre_opt) {
		Session sesion=sessionFactory.getCurrentSession();
		if (((Long) sesion.createQuery("Select count(*) from Tipo where nombre_opt=:nombre_opt").setParameter("nombre_opt", nombre_opt).uniqueResult()).intValue()==0) return false;
		else return true;
	}
	
	@Override
	public Tipo getTipo(int idtipo) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.get(Tipo.class, idtipo);
	}
	
	@Override
	public Tipo getTipoByNombre(String nombre_opt) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Tipo where nombre_opt=:nombre_opt",Tipo.class).setParameter("nombre_opt", nombre_opt).uniqueResult();
	}

	@Override
	public List<Tipo> getTipos() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Tipo> tipos=sesion.createQuery("from Tipo",Tipo.class).list();
		return tipos;
	}

	@Override
	public void saveTipo(Tipo tipo) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(tipo);
	}

	@Override
	public void deleteTipo(Tipo tipo) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.delete(tipo);
	}
}