package gameover.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntRangoDAO;
import gameover.models.Rango;

@Repository
public class RangoDAO implements IntRangoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Rango> getRangos() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Rango> rangos=sesion.createQuery("from Rango",Rango.class).getResultList();
		return rangos;
	}
	
	@Override
	public Map<String,String> getRangosMap() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Rango> rangos=sesion.createQuery("from Rango",Rango.class).getResultList();
		Map<String,String> mapaRangos=new HashMap<String,String>();
		for(Rango rango: rangos) {
			mapaRangos.put(String.valueOf(rango.getIdrango()), rango.getRango());
		}
		return mapaRangos; 
	}
	
	@Override
	public Rango getRangoByName(String rango) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Rango r where r.rango=:rango",Rango.class).setParameter("rango", rango).uniqueResult();	
	}
}