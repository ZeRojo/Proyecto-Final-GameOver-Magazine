package gameover.service.iface;

import java.util.List;

import gameover.models.Categoria;

public interface IntCategoriaService {

	boolean checkCategoriaExists(String nombre_opt);
	
	Categoria getCategoria(int idcategoria);
	
	Categoria getCategoriaByNombre(String nombre_opt);
	
	List<Categoria> getCategorias();
	
	void saveCategoria(Categoria categoria);
	
	void deleteCategoria(Categoria categoria);
}