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

import gameover.models.Video;
import gameover.resources.iface.IntGlobalVariables;
import gameover.resources.iface.IntUsuarioAutenticado;
import gameover.service.iface.IntVideoService;

@Controller
@RequestMapping("/gestion/videos")
public class VideosController {

	@Autowired
	private IntUsuarioAutenticado usuarioAutenticado;
		
	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
	private IntVideoService videoService;
	
	@GetMapping("/listado")
	public String listadoVideos(@RequestParam(value="pag",required=false) Integer pagina_destino, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		if (pagina_destino!=null) {
			variables.setPaginaActual(pagina_destino);
		}
		variables.setTotalElementos(videoService.getTotalVideos());
		modelo.addAttribute("videos",videoService.getLastVideosRango(variables.getElementoInicial(), variables.getElementosPagina()));
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-listado-videos";
	}
	
	@GetMapping("/nuevo")
	public String nuevoVideo(Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		modelo.addAttribute("video", new Video());
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("titulo","Nuevo vídeo");
		modelo.addAttribute("tarea","Nuevo");
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-video";
	}
	
	@PostMapping("/editar")
	public String editarVideo(HttpServletRequest request, Model modelo) {
		if (!usuarioAutenticado.isEmparejado()) usuarioAutenticado.setUsuarioAutenticado();
		int idvideo=Integer.parseInt(request.getParameter("idvideo"));
		Video video=videoService.getVideo(idvideo);
		modelo.addAttribute("video", video);
		modelo.addAttribute("variables", variables);
		modelo.addAttribute("titulo","Editar vídeo");
		modelo.addAttribute("tarea","Editar");
		modelo.addAttribute("usuarioNickname",usuarioAutenticado.getUsuarioAutenticadoNickname());
		modelo.addAttribute("usuarioAvatar",usuarioAutenticado.getUsuarioAutenticadoAvatar());
		return "gestion-formulario-video";
	}
	
	@PostMapping("/guardar")
	public String guardarVideo(@ModelAttribute("video") Video video) {
		videoService.saveVideo(video);
		return "redirect:/gestion/videos/listado";
	}
	
	@PostMapping("/eliminar")
	public String eliminarVideo(HttpServletRequest request) {
		int idvideo=Integer.parseInt(request.getParameter("idvideo"));
		Video video=videoService.getVideo(idvideo);
		videoService.deleteVideo(video);
		return "redirect:/gestion/videos/listado";
	}
}
