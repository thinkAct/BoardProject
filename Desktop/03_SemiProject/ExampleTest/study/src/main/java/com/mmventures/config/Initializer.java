package com.mmventures.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
 
        this.addDispatcherServlet(servletContext);
        this.addUtf8CharacterEncodingFilter(servletContext);
	}
	
    private void addDispatcherServlet(ServletContext servletContext)
    {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.getEnvironment().addActiveProfile("production");
        applicationContext.register(ApplicationContextConfig.class);
 
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setInitParameter("dispatchOptionsRequest", "true"); // CORS 를 위해서 option request 도 받아들인다.
    }
 
    private void addUtf8CharacterEncodingFilter(ServletContext servletContext)
    {
        FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER", CharacterEncodingFilter.class);
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

}
