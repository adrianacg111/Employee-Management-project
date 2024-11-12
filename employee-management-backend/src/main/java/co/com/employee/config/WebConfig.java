package co.com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

 @Override
 public void addCorsMappings(CorsRegistry registry) {
     registry.addMapping("/**") //CORS 
             .allowedOrigins("http://localhost:3000") // Frontend dominio
             .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed methods
             .allowedHeaders("*"); // Allow all headers
 }
}
