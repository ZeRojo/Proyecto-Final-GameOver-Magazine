package gameover.dao.iface;

import java.util.List;

import gameover.models.Articulo;

public interface IntArticuloDAO {

	boolean checkArticuloExists(String nombre_opt);
	
	Articulo getArticulo(int idarticulo);
	
	Articulo getArticuloByNombreP(String nombre_opt, boolean publicado);

	Articulo getArticuloWithTags(int idarticulo);
	
	List<Articulo> getArticulos();
	
	List<Articulo> getXLastArticulos(int lastX);
	
	List<Articulo> getLastArticulosRango(int elementoInicial, int numeroElementos);
	
	List<Articulo> getArticulosByCategoria(int idcategoria);
	
	List<Articulo> getLastArticulosByCategoriaRango(int idcategoria, int elementoInicial, int numeroElementos);
	
	List<Articulo> getArticulosByTipo(int idtipo);
	
	List<Articulo> getLastArticulosByTipoRango(int idtipo, int elementoInicial, int numeroElementos);
	
	List<Articulo> getLastArticulosByTipoRangoId(int idtipo, int elementoInicial, int numeroElementos);
	
	List<Articulo> getLastArticulosByCatYTipo(int idcategoria, int idtipo);
	
	List<Articulo> getLastArticulosByCatYTipoRango(int idcategoria, int idtipo, int elementoInicial, int numeroElementos);
	
	List<Articulo> getLastArticulosByTagRango(int idtag, int elementoInicial, int numeroElementos);
	
	List<Articulo> getArticulosByUsuario(int idusuario);
	
	List<Articulo> getArticulosByUsuarioRango(int idusuario, int elementoInicial, int numeroElementos);
	
	int getTotalArticulos();
	
	int getTotalArticulosByUsuario(int idusuario);
	
	int getTotalArticulosByCategoria(int idcategoria);
	
	int getTotalArticulosByTipo(int idtipo);
	
	int getTotalArticulosByCatYTipo(int idcategoria, int idtipo);
	
	int getTotalArticulosByTag(int idtag);
	
	void saveArticulo(Articulo articulo);
	
	Articulo saveAndRetrieveArticulo(Articulo articulo);
	
	void deleteArticulo(Articulo articulo);
}
