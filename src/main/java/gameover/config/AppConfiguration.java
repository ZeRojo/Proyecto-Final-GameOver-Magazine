package gameover.config;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import gameover.models.Articulo;
import gameover.models.Categoria;
import gameover.models.Estreno;
import gameover.models.Rango;
import gameover.models.Tag;
import gameover.models.Tipo;
import gameover.models.Usuario;
import gameover.models.UsuarioDetalles;
import gameover.models.Video;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="gameover")
@PropertySource("classpath:gameover.properties")
@EnableTransactionManagement
public class AppConfiguration implements WebMvcConfigurer {

	@Autowired
	Environment environment;
	
	@Bean(name="com.cloudinary.cloud_name")
	public String getCloudinaryCloudName() {
		return environment.getProperty("com.cloudinary.cloud_name");
	}
	
	@Bean(name="com.cloudinary.api_key")
	public String getCloudinaryApiJey() {
		return environment.getProperty("com.cloudinary.api_key");
	}
	
	@Bean(name="com.cloudinary.api_secret")
	public String getCloudinaryApiSecret() {
		return environment.getProperty("com.cloudinary.api_secret");
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties properties = new Properties();
		//JDBC properties
		properties.put(DRIVER, environment.getProperty("mysql.driver"));
		properties.put(URL, environment.getProperty("mysql.jdbcUrl"));		
		properties.put(USER, environment.getProperty("mysql.username"));
		properties.put(PASS, environment.getProperty("mysql.password"));
		//Hibernate properties
		properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
		properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
		//C3P0 properties
		properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
		properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
		properties.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.acquire_increment"));
		properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
		properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));
		factoryBean.setHibernateProperties(properties);
		factoryBean.setAnnotatedClasses(Usuario.class,Rango.class,UsuarioDetalles.class,Articulo.class,Categoria.class,Tag.class,Tipo.class,Video.class,Estreno.class);
		return factoryBean;
	}
		
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 10MB
		multipartResolver.setMaxUploadSizePerFile(1048576); // 1MB
		return multipartResolver;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/scripts/**").addResourceLocations("/resources/scripts/").setCachePeriod(31556926);
        registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/").setCachePeriod(31556926);
	}
}