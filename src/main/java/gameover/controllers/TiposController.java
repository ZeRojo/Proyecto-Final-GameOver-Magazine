package gameover.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gameover.models.Tipo;
import gameover.resources.iface.IntGlobalVariables;
import gameover.resources.iface.IntUsuarioAutenticado;
import gameover.service.iface.IntTipoService;

@Controller
@RequestMapping("/gestion/tipo")
public class TiposController {
	
	@Autowired
	private IntUsuarioAutenticado usuarioAutenticado;
		
	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
	private IntTipoService tipoService;
	
	@GetMapping("/")
	public String operacionesTipos(Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		List<Tipo> tipos=tipoService.getTipos();
		modelo.addAttribute("tipos", tipos);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-tipo";
	}
	
	@PostMapping("/nuevo")
	public String nuevoTipo(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		Tipo tipo=new Tipo();
		tipo.setNombre(request.getParameter("nombreNuevoTipo"));
		tipo.setNombre_opt(variables.sanearCadena(request.getParameter("nombreNuevoTipo")));
		tipoService.saveTipo(tipo);
		List<Tipo> tipos=tipoService.getTipos();
		modelo.addAttribute("tipos", tipos);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-tipo";
	}
	
	@PostMapping("/editar")
	public String editarTipo(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		Tipo tipo=tipoService.getTipo(Integer.parseInt(request.getParameter("idEditTipo")));
		tipo.setNombre(request.getParameter("nuevoNombreTipo"));
		tipo.setNombre_opt(variables.sanearCadena(request.getParameter("nuevoNombreTipo")));
		tipoService.saveTipo(tipo);
		List<Tipo> tipos=tipoService.getTipos();
		modelo.addAttribute("tipos", tipos);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-tipo";
	}
	
	@PostMapping("/eliminar")
	public String eliminarTipo(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		Tipo tipo=tipoService.getTipo(Integer.parseInt(request.getParameter("idtipo")));
		tipoService.deleteTipo(tipo);
		List<Tipo> tipos=tipoService.getTipos();
		modelo.addAttribute("tipos", tipos);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-tipo";
	}
}