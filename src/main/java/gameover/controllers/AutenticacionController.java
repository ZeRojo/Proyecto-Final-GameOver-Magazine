package gameover.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import gameover.resources.iface.IntUsuarioAutenticado;

@Controller
public class AutenticacionController {

	@Autowired
	private IntUsuarioAutenticado usuarioAutenticado;
	
	@GetMapping("/userLogin")
	public String loginForm() {
		return "gestion-formulario-login";
	}
	
	@GetMapping("/userLogout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    usuarioAutenticado.reset();
	    if (request.getParameter("gestion").equals("true")) return "redirect:/gestion";
	    else {
	    	String redirectUrl=request.getParameter("url"); 
	    	return "redirect:"+redirectUrl;
	    }   
	}
}
