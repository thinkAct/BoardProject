package com.mmventures.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ConnectionReleaseMode;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan("com.mmventures.study")
@Import(DBConfig.class)
public class ApplicationContextConfig extends WebMvcConfigurerAdapter  {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("spring");
    }
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.favorPathExtension(true)
                    .useJaf(false)
                    .ignoreAcceptHeader(true)
                    .mediaType("html", MediaType.TEXT_HTML)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .defaultContentType(MediaType.TEXT_HTML);
    }

    @Bean
    public ViewResolver viewResolver(ContentNegotiationManager contentNegotiationManager){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        
        Map<String, String> mediaTypes = new HashMap<>();
        mediaTypes.put("html", "text/html");
        mediaTypes.put("json", "application/json");
        
        List<ViewResolver> viewResolvers = new ArrayList<>();
        viewResolvers.add(internalResourceViewResolver());
        viewResolvers.add(beanNameViewResolver());
        
        List<View> defaultViews = new ArrayList<>();
        defaultViews.add(mappingJackson2JsonView());
        
        resolver.setViewResolvers(viewResolvers);
        resolver.setDefaultViews(defaultViews);
        resolver.setContentNegotiationManager(contentNegotiationManager);
        
        return resolver;
    }
    
    @Bean
    public ViewResolver internalResourceViewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public ViewResolver beanNameViewResolver(){
        return new BeanNameViewResolver();
    }
    
    @Bean 
    public View mappingJackson2JsonView(){
        return new MappingJackson2JsonView();
    }
    
}
