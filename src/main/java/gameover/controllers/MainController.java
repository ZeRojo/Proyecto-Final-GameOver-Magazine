package gameover.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gameover.models.Articulo;
import gameover.models.Categoria;
import gameover.models.Rango;
import gameover.models.Tag;
import gameover.models.Tipo;
import gameover.models.Usuario;
import gameover.models.UsuarioDetalles;
import gameover.resources.ValidationErrors;
import gameover.resources.iface.IntCustomValidations;
import gameover.resources.iface.IntGlobalVariables;
import gameover.resources.iface.IntUsuarioAutenticado;
import gameover.service.iface.IntArticuloService;
import gameover.service.iface.IntCategoriaService;
import gameover.service.iface.IntEstrenoService;
import gameover.service.iface.IntRangoService;
import gameover.service.iface.IntTagService;
import gameover.service.iface.IntTipoService;
import gameover.service.iface.IntUsuarioService;
import gameover.service.iface.IntVideoService;


@Controller
public class MainController {

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
	private IntVideoService videoService;
	
	@Autowired
	private IntEstrenoService estrenoService;
	
	@Autowired
	private IntUsuarioService usuarioService;
	
	@Autowired
	private IntRangoService rangoService;
	
	@Autowired
	private IntCustomValidations customValidations;
	
	@Autowired
	ValidationErrors validacion;
	
	@RequestMapping("/")
	public String paginaInicio(Model modelo) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		List<Articulo> articulos=articuloService.getXLastArticulos(variables.getNumElementosCarrousel());
		for (Articulo articulo:articulos) {
			if (!articulo.getTexto().equals("")) {
				String[] textos=StringUtils.substringsBetween(articulo.getTexto(),"<p>","</p>");
				articulo.setTexto(textos[0]);
			}
		}		
		modelo.addAttribute("articulosCarrousel",articulos);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("ultimasNoticias",articuloService.getLastArticulosByTipoRango(1, 0, variables.getNumElementosNoticias()));
		modelo.addAttribute("videos",videoService.getLastXVideos(variables.getNumElementosVideos()));
		modelo.addAttribute("estrenos",estrenoService.getNextXEstrenos(variables.getNumElementosEstrenos()));
		modelo.addAttribute("novedades",articuloService.getLastArticulosByTipoRangoId(5,0,variables.getNumElementosNovedades()));
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-index";
	}
	
	@RequestMapping("/categoria/{categoria}")
	public String paginaListadoXCategoria(Model modelo, @PathVariable("categoria") String nombre_opt, @RequestParam(value="pag",required=false) Integer pagina_destino) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		} else variables.setPaginaActual(1);
		Categoria categoria=categoriaService.getCategoriaByNombre(nombre_opt);	
		variables.setTotalElementos(articuloService.getTotalArticulosByCategoria(categoria.getIdcategoria()));
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		modelo.addAttribute("tipoFiltro","categoria");
		modelo.addAttribute("nombreFiltro",categoria.getNombre());
		modelo.addAttribute("articulos", articuloService.getLastArticulosByCategoriaRango(categoria.getIdcategoria(), variables.getElementoInicial(), variables.getElementosPagina()));
		modelo.addAttribute("variables", variables);
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-listado";
	}
	
	@RequestMapping("/tipo/{tipo}")
	public String paginaListadoXTipo(Model modelo, @PathVariable("tipo") String nombre_opt, @RequestParam(value="pag",required=false) Integer pagina_destino) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		} else variables.setPaginaActual(1);
		Tipo tipo=tipoService.getTipoByNombre(nombre_opt);
		variables.setTotalElementos(articuloService.getTotalArticulosByTipo(tipo.getIdtipo()));
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		modelo.addAttribute("tipoFiltro","tipo");
		modelo.addAttribute("nombreFiltro",tipo.getNombre());
		modelo.addAttribute("articulos", articuloService.getLastArticulosByTipoRango(tipo.getIdtipo(), variables.getElementoInicial(), variables.getElementosPagina()));
		modelo.addAttribute("variables", variables);
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-listado";
	}
	
	@RequestMapping("/catytipo/{categoria}/{tipo}")
	public String paginaListadoXCatYTipo(Model modelo, @PathVariable("categoria") String nombre_cat_opt, @PathVariable("tipo") String nombre_tip_opt, @RequestParam(value="pag",required=false) Integer pagina_destino) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		} else variables.setPaginaActual(1);
		Categoria categoria=categoriaService.getCategoriaByNombre(nombre_cat_opt);
		Tipo tipo=tipoService.getTipoByNombre(nombre_tip_opt);
		variables.setTotalElementos(articuloService.getTotalArticulosByCatYTipo(categoria.getIdcategoria(),tipo.getIdtipo()));
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		modelo.addAttribute("tipoFiltro","categoria y tipo");
		modelo.addAttribute("nombreFiltro1",categoria.getNombre());
		modelo.addAttribute("nombreFiltro2",tipo.getNombre());
		modelo.addAttribute("articulos", articuloService.getLastArticulosByCatYTipoRango(categoria.getIdcategoria(),tipo.getIdtipo(), variables.getElementoInicial(), variables.getElementosPagina()));
		modelo.addAttribute("variables", variables);
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-listado";
	}
	
	@RequestMapping("/tag/{tag}")
	public String paginaListadoXTag(Model modelo, @PathVariable("tag") String nombre_opt, @RequestParam(value="pag",required=false) Integer pagina_destino) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		} else variables.setPaginaActual(1);
		Tag tag=tagService.getTagByNombre(nombre_opt);
		variables.setTotalElementos(articuloService.getTotalArticulosByTag(tag.getIdtag()));
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		modelo.addAttribute("tipoFiltro","tag");
		modelo.addAttribute("nombreFiltro",tag.getNombre());
		modelo.addAttribute("articulos", articuloService.getLastArticulosByTagRango(tag.getIdtag(), variables.getElementoInicial(), variables.getElementosPagina()));
		modelo.addAttribute("variables", variables);
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-listado";
	}
	
	@RequestMapping("/autor/{idautor}/")
	public String paginaListadoXAutor(Model modelo, @PathVariable("idautor") int idusuario, @RequestParam(value="pag",required=false) Integer pagina_destino) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		} else variables.setPaginaActual(1);
		UsuarioDetalles usuarioDetalles=usuarioService.getUsuarioDetalles(idusuario);
		variables.setTotalElementos(articuloService.getTotalArticulosByUsuario(idusuario));
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		modelo.addAttribute("tipoFiltro","autor");
		modelo.addAttribute("nombreFiltro",usuarioDetalles.getNombre());
		modelo.addAttribute("articulos", articuloService.getArticulosByUsuarioRango(idusuario, variables.getElementoInicial(), variables.getElementosPagina()));
		modelo.addAttribute("variables", variables);
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-listado";
	}
	
	@RequestMapping("/videos")
	public String paginaListadoVideos(Model modelo,@RequestParam(value="pag",required=false) Integer pagina_destino) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		} else variables.setPaginaActual(1);
		variables.setTotalElementos(videoService.getTotalVideos());
		variables.setElementosPagina(6);
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		modelo.addAttribute("tipoFiltro","videos");
		modelo.addAttribute("videos",videoService.getLastVideosRango(variables.getElementoInicial(), variables.getElementosPagina()));
		modelo.addAttribute("variables", variables);
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-listado";
	}
	
	
	@RequestMapping("/articulo/{articulo}")
	public String paginaArticulo(Model modelo, @PathVariable("articulo") String nombre_opt) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		Articulo articulo=articuloService.getArticuloByNombreP(nombre_opt, true);
		modelo.addAttribute("usuarioDetalles", usuarioService.getUsuarioDetalles(articulo.getUsuario().getIdusuario()));
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		modelo.addAttribute("articulo", articulo);
		modelo.addAttribute("variables", variables);
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-articulo";
	}
	
	@GetMapping("/gestion")
	public String indexGestion(Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-index";
	}
	
	@PostMapping("/checkNuevoUsuario")
	public ResponseEntity<Object> checkNuevoUsuario(HttpServletRequest request) {
		customValidations.reset();	
		if (customValidations.validaNombreUsuario(request.getParameter("nombre"),0)==false) {
			if (customValidations.validaPasswordsIguales(request.getParameter("password1"), request.getParameter("password2"))==false) {
				if (customValidations.validaPassword(request.getParameter("password1"))==false) {
					return new ResponseEntity<>("success",HttpStatus.OK);
				} else return new ResponseEntity<>(customValidations.getValidationErrors().getPasswordError(),HttpStatus.BAD_REQUEST);
			} else return new ResponseEntity<>(customValidations.getValidationErrors().getPasswordError(),HttpStatus.BAD_REQUEST);
		} else return new ResponseEntity<>(customValidations.getValidationErrors().getNombreError(),HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/checkEditarUsuario")
	public ResponseEntity<Object> checkEditarUsuario(HttpServletRequest request) {
		int idusuario=usuarioAutenticado.getUsuarioAutenticadoId();		
		customValidations.reset();	
		if (!request.getParameter("password1").equals("") || !request.getParameter("password2").equals("")) {
			if (customValidations.validaPasswordsIguales(request.getParameter("password1"), request.getParameter("password2"))==false) {
				if (customValidations.validaPassword(request.getParameter("password1"))==false) {
					if (customValidations.validaNombreUsuario(request.getParameter("nombre"),idusuario)==false) {
						return new ResponseEntity<>("success",HttpStatus.OK);
					} else return new ResponseEntity<>(customValidations.getValidationErrors().getNombreError(),HttpStatus.BAD_REQUEST);
				} else return new ResponseEntity<>(customValidations.getValidationErrors().getPasswordError(),HttpStatus.BAD_REQUEST);
			} else return new ResponseEntity<>(customValidations.getValidationErrors().getPasswordError(),HttpStatus.BAD_REQUEST);
		} else {
			if (customValidations.validaNombreUsuario(request.getParameter("nombre"),idusuario)==false) {
				return new ResponseEntity<>("success",HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(customValidations.getValidationErrors().getNombreError(),HttpStatus.BAD_REQUEST);
			}
		}
	}
		
	@PostMapping("/nuevoUsuario")
	public String nuevoUsuario(HttpServletRequest request) {
		Usuario usuario=new Usuario();
		usuario.setNombre(request.getParameter("username"));
		usuario.setPassword(request.getParameter("password"));
		usuario.setActivo(true);
		Rango rango= rangoService.getRangoByName("ROLE_USER");
		usuario.addRango(rango);
		UsuarioDetalles usuarioDetalles=new UsuarioDetalles();
		usuarioDetalles.setNombre(request.getParameter("username"));
		usuario.setUsuarioDetalles(usuarioDetalles);
		usuarioDetalles.setUsuario(usuario);
		usuarioService.saveUsuarioDetalles(usuario.getUsuarioDetalles());				
		String redirectUrl=request.getParameter("url");
    	return "redirect:"+redirectUrl;
	}
	
	@PostMapping("/editarUsuario")
	public String editarUsuario(HttpServletRequest request,  HttpServletResponse response) {
		Usuario usuario=usuarioService.findUserByUserName(usuarioAutenticado.getUsuarioAutenticado().getNombre());
		UsuarioDetalles usuarioDetalles=usuario.getUsuarioDetalles();
		usuario.setNombre(request.getParameter("username"));
		usuarioDetalles.setNombre(request.getParameter("nickname"));
		usuarioDetalles.setAvatar(request.getParameter("avatar"));
		usuario.setUsuarioDetalles(usuarioDetalles);
		usuarioDetalles.setUsuario(usuario);
		if (request.getParameter("password").equals("")) {
			usuarioService.saveUsuarioNoCrypt(usuario);
			usuarioService.saveUsuarioDetallesNoCrypt(usuarioDetalles);
		} else {
			usuario.setPassword(request.getParameter("password"));
			usuarioService.saveUsuarioCrypt(usuario);
			usuarioService.saveUsuarioDetallesNoCrypt(usuarioDetalles);
		}		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}    
		String redirectUrl=request.getParameter("url");
		return "redirect:"+redirectUrl;
	}
	
	@GetMapping("/accesibilidad")
	public String accesibilidad(Model modelo) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-accesibilidad";
	}
	
	@GetMapping("/contacta")
	public String contacta(Model modelo) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-contacta";
	}
	
	@GetMapping("/empresa")
	public String empresa(Model modelo) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-empresa";
	}
	
	@GetMapping("privacidad")
	public String privacidad(Model modelo) {
		if (usuarioAutenticado.isAuthenticated()) {
			usuarioAutenticado.setUsuarioAutenticado();
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		}
		modelo.addAttribute("categorias",categoriaService.getCategorias());
		modelo.addAttribute("tipos",tipoService.getTipos());
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("tags",tagService.getLastXTags(20));
		List<Articulo> reportajes=articuloService.getLastArticulosByTipoRango(4,0,2);
		for (Articulo reportaje:reportajes) {
			if (!reportaje.getTexto().equals("")) {
				reportaje.setTexto(reportaje.getTexto().substring(0, 175));
			}
		}
		modelo.addAttribute("reportajes",reportajes);
		return "public-privacidad";
	}
	
    @GetMapping("/prohibido")
    public String accesoDenegado(Model modelo) {
    	modelo.addAttribute("errorImg","403");
    	modelo.addAttribute("errorMsg1","ACCESO DENEGADO");
    	modelo.addAttribute("errorMsg2","Ops, no tienes permisos para entrar en esta página.");
    	return "error";
    }
}