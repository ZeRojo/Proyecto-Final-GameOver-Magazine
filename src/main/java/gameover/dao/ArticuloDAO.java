package gameover.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntArticuloDAO;
import gameover.models.Articulo;

@Repository
public class ArticuloDAO implements IntArticuloDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean checkArticuloExists(String nombre_opt) {
		Session sesion=sessionFactory.getCurrentSession();
		if (((Long) sesion.createQuery("Select count(*) from Articulo where nombre_opt=:nombre_opt").setParameter("nombre_opt", nombre_opt).uniqueResult()).intValue()==0) return false;
		else return true;
	}
	
	@Override
	public Articulo getArticulo(int idarticulo) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.get(Articulo.class, idarticulo);
	}
	
	@Override
	public Articulo getArticuloByNombreP(String nombre_opt, boolean publicado) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Articulo where nombre_opt=:nombre_opt and publicar=:publicado",Articulo.class).setParameter("nombre_opt", nombre_opt).setParameter("publicado", publicado).uniqueResult();	
	}

	@Override
	public Articulo getArticuloWithTags(int idarticulo) {
		Session sesion=sessionFactory.getCurrentSession();
		Articulo articulo=sesion.createQuery("from Articulo a left join fetch a.tags WHERE a.idarticulo=:idarticulo",Articulo.class).setParameter("idarticulo",idarticulo).uniqueResult();
		return articulo;
	}
	
	@Override
	public List<Articulo> getArticulos() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Articulo> articulos = sesion.createQuery("from Articulo",Articulo.class).list();
		return articulos;
	}
	
	@Override
	public List<Articulo> getXLastArticulos(int lastX) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Integer> ids=sesion.createQuery("select a.idarticulo from Articulo a inner join a.tipo t where a.publicar=1 and t.idtipo in (2,3,4) ORDER BY a.fecha DESC",Integer.class).setMaxResults(lastX).getResultList();
		return sesion.createQuery("select distinct a from Articulo a left join fetch a.tags where a.idarticulo in (:ids)",Articulo.class).setParameter("ids", ids).getResultList();
	}
	
	@Override
	public List<Articulo> getLastArticulosRango(int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Articulo ORDER BY fecha DESC",Articulo.class).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
	}
		
	@Override
	public List<Articulo> getArticulosByCategoria(int idcategoria) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Articulo> articulos = sesion.createQuery("from Articulo where idcategoria=:idcategoria",Articulo.class).setParameter("idcategoria", idcategoria).list();
		return articulos;
	}
	
	@Override
	public List<Articulo> getLastArticulosByCategoriaRango(int idcategoria, int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Articulo> articulos = sesion.createQuery("from Articulo where idcategoria=:idcategoria and idtipo in(1,2,3,4) order by fecha desc",Articulo.class).setParameter("idcategoria", idcategoria).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
		return articulos;
	}
	
	@Override
	public List<Articulo> getArticulosByTipo(int idtipo) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Articulo> articulos = sesion.createQuery("from Articulo where idtipo=:idtipo",Articulo.class).setParameter("idtipo", idtipo).list();
		return articulos;
	}
	
	@Override
	public List<Articulo> getLastArticulosByTipoRango(int idtipo, int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Articulo where idtipo=:idtipo and publicar=1 ORDER BY fecha DESC",Articulo.class).setParameter("idtipo", idtipo).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
	}
	
	@Override
	public List<Articulo> getLastArticulosByTipoRangoId(int idtipo, int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Articulo where idtipo=:idtipo and publicar=1 ORDER BY idarticulo DESC",Articulo.class).setParameter("idtipo", idtipo).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
	}
	
	@Override
	public List<Articulo> getLastArticulosByCatYTipo(int idcategoria, int idtipo) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Articulo where idcategoria=:idcategoria and idtipo=:idtipo",Articulo.class).setParameter("idcategoria", idcategoria).setParameter("idtipo", idtipo).list();
	}
	
	@Override
	public List<Articulo> getLastArticulosByCatYTipoRango(int idcategoria, int idtipo, int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Articulo where idcategoria=:idcategoria and idtipo=:idtipo order by fecha DESC",Articulo.class).setParameter("idcategoria", idcategoria).setParameter("idtipo", idtipo).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
	}
	
	@Override
	public List<Articulo> getLastArticulosByTagRango(int idtag, int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("SELECT a from Articulo a INNER JOIN a.tags t where t.idtag=:idtag order by a.fecha DESC",Articulo.class).setParameter("idtag",idtag).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
	}
	
	@Override
	public List<Articulo> getArticulosByUsuario(int idusuario) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Articulo> articulos = sesion.createQuery("from Articulo where idusuario=:idusuario",Articulo.class).setParameter("idusuario", idusuario).list();
		return articulos;
	}
	
	@Override
	public List<Articulo> getArticulosByUsuarioRango(int idusuario, int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Articulo> articulos=sesion.createQuery("FROM Articulo where idusuario=:idusuario ORDER BY fecha desc",Articulo.class).setParameter("idusuario", idusuario).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
		return articulos;
	}
	
	@Override
	public int getTotalArticulos() {
		Session sesion=sessionFactory.getCurrentSession();
		return ((Long) sesion.createQuery("Select count(*) from Articulo").uniqueResult()).intValue();
	}
	
	@Override
	public int getTotalArticulosByUsuario(int idusuario) {
		Session sesion=sessionFactory.getCurrentSession();
		return ((Long) sesion.createQuery("Select count(*) from Articulo where idusuario=:idusuario and idtipo in(1,2,3,4)").setParameter("idusuario",idusuario).uniqueResult()).intValue();
	}
	
	@Override
	public int getTotalArticulosByCategoria(int idcategoria) {
		Session sesion=sessionFactory.getCurrentSession();
		return ((Long) sesion.createQuery("Select count(*) from Articulo where publicar=1 and idcategoria=:idcategoria and idtipo in(1,2,3,4)").setParameter("idcategoria",idcategoria).uniqueResult()).intValue();
	}

	@Override
	public int getTotalArticulosByTipo(int idtipo) {
		Session sesion=sessionFactory.getCurrentSession();
		return ((Long) sesion.createQuery("Select count(*) from Articulo where publicar=1 and idtipo=:idtipo").setParameter("idtipo",idtipo).uniqueResult()).intValue();
	}

	@Override
	public int getTotalArticulosByCatYTipo(int idcategoria, int idtipo) {
		Session sesion=sessionFactory.getCurrentSession();
		return ((Long) sesion.createQuery("Select count(*) from Articulo where publicar=1 and idcategoria=:idcategoria and idtipo=:idtipo").setParameter("idcategoria",idcategoria).setParameter("idtipo", idtipo).uniqueResult()).intValue();
	}
			
	@Override
	public int getTotalArticulosByTag(int idtag) {
		Session sesion=sessionFactory.getCurrentSession();	
		return ((Long) sesion.createQuery("Select count(*) from Articulo a INNER JOIN a.tags t INNER JOIN a.tipo tip where a.publicar=1 and t.idtag=:idtag and tip.idtipo in(1,2,3,4)").setParameter("idtag",idtag).uniqueResult()).intValue();
	}
	
	
	@Override
	public void saveArticulo(Articulo articulo) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(articulo);
	}
	
	@Override
	public Articulo saveAndRetrieveArticulo(Articulo articulo) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(articulo);
		return articulo;
	}

	@Override
	public void deleteArticulo(Articulo articulo) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.delete(articulo);
	}
}