package sahak.sahakyan.maxmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sahak.sahakyan.maxmi.dao.ProductRepository;
import sahak.sahakyan.maxmi.entity.Product;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebMvc
public class MaxMiApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(MaxMiApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/assets/**")) {
            registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        }
    }
}
