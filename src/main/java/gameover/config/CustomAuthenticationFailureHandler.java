package gameover.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
		String location=httpServletRequest.getContextPath();
    	String gestion=httpServletRequest.getParameter("gestion");
    	if (gestion.equals("true")) {
    		location+="/userLogin?error";
    	} else {
    		location+="/?error";
    	}
    	httpServletResponse.sendRedirect(location);
    }
}