package gameover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntCategoriaDAO;
import gameover.models.Categoria;
import gameover.service.iface.IntCategoriaService;

@Service("categoriaService")
public class CategoriaService implements IntCategoriaService {

	@Autowired
	private IntCategoriaDAO categoriaDAO;

	@Override
	@Transactional
	public boolean checkCategoriaExists(String nombre_opt) {
		return categoriaDAO.checkCategoriaExists(nombre_opt);
	}
	
	
	@Override
	@Transactional
	public Categoria getCategoria(int idcategoria) {
		return categoriaDAO.getCategoria(idcategoria);
	}

	@Override
	@Transactional
	public Categoria getCategoriaByNombre(String nombre_opt) {
		return categoriaDAO.getCategoriaByNombre(nombre_opt);
	}
	
	@Override
	@Transactional
	public List<Categoria> getCategorias() {
		return categoriaDAO.getCategorias();
	}

	@Override
	@Transactional
	public void saveCategoria(Categoria categoria) {
		categoriaDAO.saveCategoria(categoria);
	}

	@Override
	@Transactional
	public void deleteCategoria(Categoria categoria) {
		categoriaDAO.deleteCategoria(categoria);
	}
}