package gameover.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntEstrenoDAO;
import gameover.models.Estreno;

@Repository
public class EstrenoDAO implements IntEstrenoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Estreno getEstreno(int idestreno) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.get(Estreno.class, idestreno);
	}

	@Override
	public List<Estreno> getEstrenos() {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Estreno",Estreno.class).list();
	}

	@Override
	public List<Estreno> getNextXEstrenos(int nextX) {
		Session sesion=sessionFactory.getCurrentSession();
		Date hoy = new Date();
		return sesion.createQuery("from Estreno e where e.fecha>:hoy order by e.fecha asc",Estreno.class).setParameter("hoy",hoy).setMaxResults(nextX).getResultList();
	}

	@Override
	public List<Estreno> getLastEstrenosRango(int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Estreno ORDER BY idestreno DESC",Estreno.class).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
	}
	
	@Override
	public int getTotalEstrenos() {
		Session sesion=sessionFactory.getCurrentSession();
		return ((Long) sesion.createQuery("Select count(*) from Estreno").uniqueResult()).intValue();
	}
	
	@Override
	public void saveEstreno(Estreno estreno) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(estreno);

	}

	@Override
	public void deleteEstreno(Estreno estreno) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.delete(estreno);
	}
}