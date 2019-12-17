package gameover.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	 @Override
     protected Filter[] getServletFilters() {
       CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
       characterEncodingFilter.setEncoding("UTF-8");
       characterEncodingFilter.setForceRequestEncoding(true);
       characterEncodingFilter.setForceEncoding(true);
       characterEncodingFilter.setForceResponseEncoding(true);
       return new Filter[] { characterEncodingFilter};
     }
	 
	@Override
	 public void onStartup(ServletContext servletContext) throws ServletException {
		 FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("characterEncoding",
				 new CharacterEncodingFilter("UTF-8", true, true));
		 filterRegistration.addMappingForUrlPatterns(null, false, "/*");
	     filterRegistration = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter() );
	     filterRegistration.addMappingForUrlPatterns(null, false, "/*");
		super.onStartup(servletContext);
	}
	
	
	@Override
	protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		final DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		return dispatcherServlet;
    }
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	

}