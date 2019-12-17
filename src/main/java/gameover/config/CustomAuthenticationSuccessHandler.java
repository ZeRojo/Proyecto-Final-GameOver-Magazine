package gameover.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		String location=httpServletRequest.getContextPath();
    	String gestion=httpServletRequest.getParameter("gestion");
    	if (gestion.equals("true")) {
    		location+="/gestion";
    	} else {
    		String redirectUrl=httpServletRequest.getParameter("url"); 
    		System.out.println(httpServletRequest.getParameter("url"));
    		location+=redirectUrl;
    	}
    	httpServletResponse.sendRedirect(location);
    }
}
