package gameover.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gameover.models.Estreno;
import gameover.resources.iface.IntGlobalVariables;
import gameover.resources.iface.IntUsuarioAutenticado;
import gameover.service.iface.IntEstrenoService;

@Controller
@RequestMapping("/gestion/estrenos")
public class EstrenosController {
	
	@Autowired
	private IntUsuarioAutenticado usuarioAutenticado;
		
	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
	private IntEstrenoService estrenoService;
	
	@GetMapping("/listado")
	public String listadoEstrenos(@RequestParam(value="pag",required=false) Integer pagina_destino, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		}
		variables.setTotalElementos(estrenoService.getTotalEstrenos());
		modelo.addAttribute("estrenos",estrenoService.getLastEstrenosRango(variables.getElementoInicial(), variables.getElementosPagina()));
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-listado-estrenos";
	}
	
	@GetMapping("/nuevo")
	public String nuevoEstreno(Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		modelo.addAttribute("estreno", new Estreno());
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("titulo","Nuevo estreno");
		modelo.addAttribute("tarea","Nuevo");
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-estreno";
	}
	
	@PostMapping("/editar")
	public String editarEstreno(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		int idestreno=Integer.parseInt(request.getParameter("idestreno"));
		Estreno estreno=estrenoService.getEstreno(idestreno);
		modelo.addAttribute("estreno", estreno);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("titulo","Editar estreno");
		modelo.addAttribute("tarea","Editar");
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-estreno";
	}
	
	@PostMapping("/guardar")
	public String guardarEstreno(@ModelAttribute("estreno") Estreno estreno) {
		estrenoService.saveEstreno(estreno);
		return "redirect:/gestion/estrenos/listado";
	}
	
	@PostMapping("/eliminar")
	public String eliminarEstreno(HttpServletRequest request) {
		int idestreno=Integer.parseInt(request.getParameter("idestreno"));
		Estreno estreno=estrenoService.getEstreno(idestreno);
		estrenoService.deleteEstreno(estreno);
		return "redirect:/gestion/estrenos/listado";
	}
}