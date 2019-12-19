package gameover.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gameover.models.Articulo;
import gameover.models.Categoria;
import gameover.models.Tag;
import gameover.models.Tipo;
import gameover.models.Usuario;
import gameover.resources.ValidationErrors;
import gameover.resources.iface.IntCustomValidations;
import gameover.resources.iface.IntGlobalVariables;
import gameover.resources.iface.IntUsuarioAutenticado;
import gameover.service.iface.IntArticuloService;
import gameover.service.iface.IntCategoriaService;
import gameover.service.iface.IntTagService;
import gameover.service.iface.IntTipoService;

@Controller
@RequestMapping("/gestion/articulo")
public class ArticulosController {

	@Autowired
	private IntUsuarioAutenticado usuarioAutenticado;
		
	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
	private IntArticuloService articuloService;
	
	@Autowired
	private IntCategoriaService categoriaService;
	
	@Autowired
	private IntTipoService tipoService;
	
	@Autowired
	private IntTagService tagService;
	
	@Autowired
	private IntCustomValidations customValidations;
	
	@Autowired
	ValidationErrors validacion;
	
	@GetMapping("/listado")
	public String listadoArticulos(@RequestParam(value="pag",required=false) Integer pagina_destino, Model modelo) {
		List<Articulo> articulos;
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		}
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		if (usuarioAutenticado.isRango("ROLE_EDITOR")) {
			variables.setTotalElementos(articuloService.getTotalArticulosByUsuario(usuarioAutenticado.getUsuarioAutenticadoId()));
			articulos=articuloService.getArticulosByUsuarioRango(usuarioAutenticado.getUsuarioAutenticadoId(), variables.getElementoInicial(), variables.getElementosPagina());
		} else {
			variables.setTotalElementos(articuloService.getTotalArticulos());
			articulos=articuloService.getLastArticulosRango(variables.getElementoInicial(), variables.getElementosPagina());
		}
		modelo.addAttribute("articulos", articulos);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-listado-articulos";
	}
	
	@PostMapping("/editar")
	public String editaArticulo(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		int idarticulo=Integer.parseInt(request.getParameter("idarticulo"));
		Articulo articulo=articuloService.getArticuloWithTags(idarticulo);
		modelo.addAttribute("articulo", articulo);
		List<Categoria> categorias=categoriaService.getCategorias();
		List<Tipo> tipos=tipoService.getTipos();
		modelo.addAttribute("categorias",categorias);
		modelo.addAttribute("tipos",tipos);
		modelo.addAttribute("titulo","Edición del artículo");
		modelo.addAttribute("tarea","Editar");
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-articulo";
	}
	
	@GetMapping("/nuevo")
	public String nuevoArticulo(Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		Articulo articulo=new Articulo();
		modelo.addAttribute("articulo",articulo);
		List<Categoria> categorias=categoriaService.getCategorias();
		List<Tipo> tipos=tipoService.getTipos();
		modelo.addAttribute("categorias",categorias);
		modelo.addAttribute("tipos",tipos);
		modelo.addAttribute("titulo","Nuevo artículo");
		modelo.addAttribute("tarea","Nuevo");
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-articulo";
	}
	
	@PostMapping("/guardaTitulo")
	public ResponseEntity<Object> guardaTituloArticulo(@RequestParam("nombre") String nombre) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		customValidations.reset();	
		if (customValidations.validaNombreArticulo(nombre)==false) {
			Articulo articulo=new Articulo();
			Usuario usuario=usuarioAutenticado.getUsuarioAutenticado();
			articulo.setNombre(nombre);
			articulo.setNombre_opt(variables.sanearCadena(nombre));
			articulo.setUsuario(usuario);
			articulo=articuloService.saveAndRetrieveArticulo(articulo);
			return new ResponseEntity<>(String.valueOf(articulo.getIdarticulo()),HttpStatus.OK);
		} else return new ResponseEntity<>(customValidations.getValidationErrors().getNombreArticuloError(),HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/guardaArticulo")
	public String guardaArticulo(@ModelAttribute("articulo") Articulo articulo, HttpServletRequest request, Model modelo) {
		if (!request.getParameter("listadetags").equals("")) {
			String ids[]=(request.getParameter("listadetags")).split(",");	
			for (String id:ids) {
				Tag tag=tagService.getTag(Integer.parseInt(id));
				articulo.addTag(tag);
			}
		}
		customValidations.reset();	
		validacion=customValidations.validateArticulo(articulo);
		if (validacion.hasErrors()) {
			modelo.addAttribute("articulo", articulo);
			List<Categoria> categorias=categoriaService.getCategorias();
			List<Tipo> tipos=tipoService.getTipos();
			modelo.addAttribute("categorias",categorias);
			modelo.addAttribute("tipos",tipos);
			modelo.addAttribute("tarea","Editar");
			if (request.getParameter("tarea").equals("Editar")) {
				modelo.addAttribute("titulo","Edición del artículo");
			} else {
				modelo.addAttribute("titulo","Nuevo artículo");
			}
			modelo.addAttribute("variables", variables);
			modelo.addAttribute("validacion",validacion);
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
			return "gestion-formulario-articulo";
		} else {
			articulo.setNombre_opt(variables.sanearCadena(articulo.getNombre()));
			articulo.setUsuario((articuloService.getArticulo(articulo.getIdarticulo())).getUsuario());
			if (articulo.getCategoria().getIdcategoria()==0) articulo.setCategoria(null);
			if (articulo.getTipo().getIdtipo()==0) articulo.setTipo(null);
			articuloService.saveArticulo(articulo);
			return "redirect:/gestion/articulo/listado";
		}
	}
	
	@PostMapping("/eliminar")
	public String eliminaArticulo(HttpServletRequest request) {
		Articulo articulo=articuloService.getArticulo(Integer.parseInt(request.getParameter("idarticulo")));
		articuloService.deleteArticulo(articulo);
		return "redirect:/gestion/articulo/listado";
	}
}