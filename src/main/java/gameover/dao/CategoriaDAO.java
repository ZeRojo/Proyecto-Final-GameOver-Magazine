package gameover.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntCategoriaDAO;
import gameover.models.Categoria;

@Repository
public class CategoriaDAO implements IntCategoriaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean checkCategoriaExists(String nombre_opt) {
		Session sesion=sessionFactory.getCurrentSession();
		if (((Long) sesion.createQuery("Select count(*) from Categoria where nombre_opt=:nombre_opt").setParameter("nombre_opt", nombre_opt).uniqueResult()).intValue()==0) return false;
		else return true;
	}
	
	@Override
	public Categoria getCategoria(int idcategoria) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.get(Categoria.class, idcategoria);
	}

	@Override
	public Categoria getCategoriaByNombre(String nombre_opt) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Categoria where nombre_opt=:nombre_opt",Categoria.class).setParameter("nombre_opt", nombre_opt).uniqueResult();
	}
	
	@Override
	public List<Categoria> getCategorias() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Categoria> categorias=sesion.createQuery("from Categoria",Categoria.class).list();
		return categorias;
	}

	@Override
	public void saveCategoria(Categoria categoria) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(categoria);
	}

	@Override
	public void deleteCategoria(Categoria categoria) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.delete(categoria);
	}
}