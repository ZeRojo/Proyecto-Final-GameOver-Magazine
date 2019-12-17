package gameover.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gameover.models.Categoria;
import gameover.resources.iface.IntGlobalVariables;
import gameover.resources.iface.IntUsuarioAutenticado;
import gameover.service.iface.IntCategoriaService;

@Controller
@RequestMapping("/gestion/categoria")
public class CategoriasController {
	
	@Autowired
	private IntUsuarioAutenticado usuarioAutenticado;
		
	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
	private IntCategoriaService categoriaService;
	
	@GetMapping("/")
	public String operacionesCategoria(Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		List<Categoria> categorias=categoriaService.getCategorias();
		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-categoria";
	}

	@PostMapping("/nueva")
	public String nuevaCategoria(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		Categoria categoria=new Categoria();
		categoria.setNombre(request.getParameter("nombreNuevaCat"));
		categoria.setNombre_opt(variables.sanearCadena(request.getParameter("nombreNuevaCat")));
		categoriaService.saveCategoria(categoria);
		List<Categoria> categorias=categoriaService.getCategorias();
		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-categoria";
	}
	
	@PostMapping("/editar")
	public String editarCategoria(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		Categoria categoria=categoriaService.getCategoria(Integer.parseInt(request.getParameter("idEditCat")));
		categoria.setNombre(request.getParameter("nuevoNombreCat"));
		categoria.setNombre_opt(variables.sanearCadena(request.getParameter("nuevoNombreCat")));
		categoriaService.saveCategoria(categoria);
		List<Categoria> categorias=categoriaService.getCategorias();
		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-categoria";
	}
	
	@PostMapping("/eliminar")
	public String eliminarCategoria(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		Categoria categoria=categoriaService.getCategoria(Integer.parseInt(request.getParameter("idcategoria")));
		categoriaService.deleteCategoria(categoria);
		List<Categoria> categorias=categoriaService.getCategorias();
		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-categoria";
	}
}