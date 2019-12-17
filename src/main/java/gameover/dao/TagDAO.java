package gameover.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntTagDAO;
import gameover.models.Tag;

@Repository
public class TagDAO implements IntTagDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Tag getTag(int idtag) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.get(Tag.class, idtag);
	}

	@Override
	public Tag getTagByNombre(String nombre_opt) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Tag t where t.nombre_opt=:nombre_opt",Tag.class).setParameter("nombre_opt", nombre_opt).uniqueResult();
	}
	
	@Override
	public List<Tag> getTags() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Tag> tags=sesion.createQuery("from Tag",Tag.class).list();
		return tags;
	}

	@Override
	public List<Tag> getLastXTags(int lastX) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Tag order by idtag DESC",Tag.class).setMaxResults(lastX).list();
	}
	
	@Override
	public void saveTag(Tag tag) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(tag);
	}

	@Override
	public Tag saveAndRetrieveTag(Tag tag) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(tag);
		return tag;
	}
	
	@Override
	public void deleteTag(Tag tag) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.delete(tag);
	}
}