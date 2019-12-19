package gameover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntArticuloDAO;
import gameover.models.Articulo;
import gameover.service.iface.IntArticuloService;

@Service("articuloService")
public class ArticuloService implements IntArticuloService {

	@Autowired
	private IntArticuloDAO articuloDAO;

	@Override
	@Transactional
	public boolean checkArticuloExists(String nombre_opt) {
		return articuloDAO.checkArticuloExists(nombre_opt);
	}
	
	@Override
	@Transactional
	public Articulo getArticulo(int idarticulo) {
		return articuloDAO.getArticulo(idarticulo);
	}
	
	@Override
	@Transactional
	public Articulo getArticuloByNombreP(String nombre_opt, boolean publicado) {
		return articuloDAO.getArticuloByNombreP(nombre_opt, publicado);
	}
	
	@Override
	@Transactional
	public Articulo getArticuloWithTags(int idarticulo) {
		return articuloDAO.getArticuloWithTags(idarticulo);
	}

	@Override
	@Transactional
	public List<Articulo> getArticulos() {
		return articuloDAO.getArticulos();
	}
	
	@Override
	@Transactional
	public List<Articulo> getXLastArticulos(int lastX) {
		return articuloDAO.getXLastArticulos(lastX);
	}
	
	@Override
	@Transactional
	public List<Articulo> getLastArticulosRango(int elementoInicial, int numeroElementos) {
		return articuloDAO.getLastArticulosRango(elementoInicial, numeroElementos); 
	}

	@Override
	@Transactional
	public List<Articulo> getArticulosByCategoria(int idcategoria) {
		return articuloDAO.getArticulosByCategoria(idcategoria);
	}
	
	@Override
	@Transactional
	public List<Articulo> getLastArticulosByCategoriaRango(int idcategoria, int elementoInicial, int numeroElementos) {
		return articuloDAO.getLastArticulosByCategoriaRango(idcategoria, elementoInicial, numeroElementos);
	}
	
	@Override
	@Transactional
	public List<Articulo> getArticulosByTipo(int idtipo) {
		return articuloDAO.getArticulosByTipo(idtipo);
	}
	
	@Override
	@Transactional
	public List<Articulo> getLastArticulosByTipoRango(int idtipo, int elementoInicial, int numeroElementos) {
		return articuloDAO.getLastArticulosByTipoRango(idtipo, elementoInicial, numeroElementos);
	}
	
	@Override
	@Transactional
	public List<Articulo> getLastArticulosByTipoRangoId(int idtipo, int elementoInicial, int numeroElementos) {
		return articuloDAO.getLastArticulosByTipoRango(idtipo, elementoInicial, numeroElementos);
	}
	
	@Override
	@Transactional
	public List<Articulo> getLastArticulosByCatYTipo(int idcategoria, int idtipo) {
		return articuloDAO.getLastArticulosByCatYTipo(idcategoria, idtipo);
	}
	
	@Override
	@Transactional
	public List<Articulo> getLastArticulosByCatYTipoRango(int idcategoria, int idtipo, int elementoInicial, int numeroElementos) {
		return articuloDAO.getLastArticulosByCatYTipoRango(idcategoria, idtipo, elementoInicial, numeroElementos);
	}
		
	@Override
	@Transactional
	public List<Articulo> getLastArticulosByTagRango(int idtag, int elementoInicial, int numeroElementos) {
		return articuloDAO.getLastArticulosByTagRango(idtag, elementoInicial, numeroElementos);
	}
		
	@Override
	@Transactional
	public List<Articulo> getArticulosByUsuario(int idusuario) {
		return articuloDAO.getArticulosByUsuario(idusuario);
	}

	@Override
	@Transactional
	public List<Articulo> getArticulosByUsuarioRango(int idusuario, int elementoInicial, int numeroElementos) {
		return articuloDAO.getArticulosByUsuarioRango(idusuario, elementoInicial, numeroElementos);
	}
	
	@Override
	@Transactional
	public int getTotalArticulos() {
		return articuloDAO.getTotalArticulos();
	}	
	
	@Override
	@Transactional
	public int getTotalArticulosByUsuario(int idusuario) {
		return articuloDAO.getTotalArticulosByUsuario(idusuario);
	}
	
	@Override
	@Transactional
	public int getTotalArticulosByCategoria(int idcategoria) {
		return articuloDAO.getTotalArticulosByCategoria(idcategoria);
	}
		
	@Override
	@Transactional
	public int getTotalArticulosByTipo(int idtipo) {
		return articuloDAO.getTotalArticulosByTipo(idtipo);
	}

	@Override
	@Transactional
	public int getTotalArticulosByCatYTipo(int idcategoria, int idtipo) {
		return articuloDAO.getTotalArticulosByCatYTipo(idcategoria,idtipo);
	}

	@Override
	@Transactional
	public int getTotalArticulosByTag(int idtag) {
		return articuloDAO.getTotalArticulosByTag(idtag);
	}
	
	@Override
	@Transactional
	public void saveArticulo(Articulo articulo) {
		articuloDAO.saveArticulo(articulo);
	}

	@Override
	@Transactional
	public Articulo saveAndRetrieveArticulo(Articulo articulo) {
		return articuloDAO.saveAndRetrieveArticulo(articulo);
	}
	
	@Override
	@Transactional
	public void deleteArticulo(Articulo articulo) {
		articuloDAO.deleteArticulo(articulo);
	}
}