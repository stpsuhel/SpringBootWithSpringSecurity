package com.priyangona.priangona_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.priyangona.priangona_backend.util.Constant.ROOT_PATH;

@SpringBootApplication
public class PriyangonaBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(PriyangonaBackendApplication.class, args);
    }
}

//@EnableWebMvc
@Configuration
class MvcConfig implements WebMvcConfigurer {
    private final Path ROOT = Paths.get("src/main/resources/static").toAbsolutePath();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/priyangona/**").addResourceLocations("file:/" + ROOT_PATH + "/priyangona/");

        registry.addResourceHandler("/css/**").addResourceLocations("file:/" + ROOT + "/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("file:/" + ROOT + "/fonts/");
        registry.addResourceHandler("/img/**").addResourceLocations("file:/" + ROOT + "/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("file:/" + ROOT + "/js/");
        registry.addResourceHandler("/page/**").addResourceLocations("file:/" + ROOT + "/page/");
    }
}
