package gameover.controllers;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gameover.models.Rango;
import gameover.models.Usuario;
import gameover.resources.ValidationErrors;
import gameover.resources.iface.IntCustomValidations;
import gameover.resources.iface.IntGlobalVariables;
import gameover.resources.iface.IntUsuarioAutenticado;
import gameover.service.iface.IntRangoService;
import gameover.service.iface.IntUsuarioService;

@Controller
@RequestMapping("/gestion/usuarios")
public class UsuariosController {

	@Autowired
	private IntUsuarioAutenticado usuarioAutenticado;
	
	@Autowired
	private IntUsuarioService usuarioService;
	
	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
	private IntRangoService rangoService;

	@Autowired
	private IntCustomValidations customValidations;
	
	@Autowired
	ValidationErrors validacion;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Rango.class, "rangos", new PropertyEditorSupport() {		
			@Override
			public void setAsText(String nombre) {
				Rango rango = rangoService.getRangoByName(nombre);
				setValue(rango);
			}
		});
	}
	
	@GetMapping("/listado")
	public String inicio(@RequestParam(value="pag",required=false) Integer pagina_destino,Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		}
		variables.setTotalElementos(usuarioService.getNumeroUsuarios());
		List<Usuario> usuarios=usuarioService.getUsuariosRango(variables.getElementoInicial(), variables.getElementosPagina());	
		modelo.addAttribute("usuarios", usuarios);
		modelo.addAttribute("variables",variables);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-listado-usuarios";
	}
	
	@GetMapping("/nuevo")
	public String nuevoUsuario(Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		Usuario usuario=new Usuario();
		modelo.addAttribute("usuario",usuario);
		modelo.addAttribute("rangos", rangoService.getRangos());
		modelo.addAttribute("titulo","Nuevo usuario");
		modelo.addAttribute("formtipo","nuevo");
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-usuario";
	}
	
	@PostMapping("/editar")
	public String editarUsuario(HttpServletRequest request,Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		int idusuario=Integer.parseInt(request.getParameter("idusuario"));
		Usuario usuario=usuarioService.getUsuario(idusuario);
		usuario.setPassword("");
		modelo.addAttribute("usuario",usuario);
		modelo.addAttribute("titulo","Editar usuario");
		modelo.addAttribute("formtipo","editar");
		modelo.addAttribute("rangos", rangoService.getRangos());
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-usuario";
	}
	
	@PostMapping("/guardar")
	public String guardarDatosUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request,Model modelo) {	
		if (usuario.getUsuarioDetalles().getNombre()==null) usuario.getUsuarioDetalles().setNombre(usuario.getNombre());
		usuario.getUsuarioDetalles().setUsuario(usuario);
		usuario.setUsuarioDetalles(usuario.getUsuarioDetalles());
		customValidations.reset();
		if (request.getParameter("formtipo").equals("editar") && usuario.getPassword().equals("")) validacion=customValidations.validateUsuarioEmptyPass(usuario); 
		else validacion=customValidations.validateUsuario(usuario, request.getParameter("password2"));
		if (validacion.hasErrors()) {
			modelo.addAttribute("usuario",usuario);
			modelo.addAttribute("formtipo",request.getParameter("formtipo"));
			modelo.addAttribute("validacion",validacion);
			modelo.addAttribute("titulo","Errores en el formulario de usuario");
			modelo.addAttribute("rangos", rangoService.getRangos());
			modelo.addAttribute("variables", variables);
			modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
			modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());			
			return "gestion-formulario-usuario";
		} else {
			if (usuario.getIdusuario()!=0) {
				usuarioService.saveUsuario(usuario);
			}
			usuarioService.saveUsuarioDetalles(usuario.getUsuarioDetalles());
			if (usuario.getIdusuario()==usuarioAutenticado.getUsuarioAutenticado().getIdusuario()) {
				return "redirect:/userLogout?gestion=true";
			} else {
				return "redirect:/gestion/usuarios/listado";
			}
		}
	}

	@PostMapping("/eliminar")
	public String eliminarUsuario(HttpServletRequest request) {
		int idusuario=Integer.parseInt(request.getParameter("idusuario"));
		Usuario usuario=usuarioService.getUsuario(idusuario);
		usuarioService.deleteUsuario(usuario);
		return "redirect:/gestion/usuarios/listado";
	}
}