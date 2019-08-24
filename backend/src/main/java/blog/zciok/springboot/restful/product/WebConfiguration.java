package blog.zciok.springboot.restful.product;

import java.util.concurrent.TimeUnit;

// import org.h2.server.web.WebServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	

	// CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }
    
    // h2 console
    // http://localhost:8080/console
//    @Bean
//    ServletRegistrationBean<WebServlet> h2servletRegistration(){
//        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>( new WebServlet());
//        registrationBean.addUrlMappings("/console/*");
//        return registrationBean;
//    }   
    
    // TODO:
    // Register resource handler for images
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/tank/**").addResourceLocations("/WEB-INF/images/tank/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
        
        registry.addResourceHandler("/images/bomber/**").addResourceLocations("/WEB-INF/images/bomber/")
        .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());    
        
        registry.addResourceHandler("/images/warship/**").addResourceLocations("/WEB-INF/images/warship/")
        .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());         
        
        registry.addResourceHandler("/images/others/**").addResourceLocations("/WEB-INF/images/others/")
        .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());        
    }  
}
