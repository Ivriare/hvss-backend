package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Controller
public class MvcConfig implements WebMvcConfigurer {

    @Value("${application.drives.resources}")
    private String resourcePath;

    @Value("${application.drives.contentPic}")
    private String resourceContentPath;

    @Value("${application.drives.userPic}")
    private String resourceUserPath;

    @Value("${application.drives.snapshotPic}")
    private String resourceSnapshotPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/content/**")    .addResourceLocations("file:///" + resourceContentPath + "/");
        registry.addResourceHandler("/resources/user/**")    .addResourceLocations("file:/// "+ resourceUserPath+"/");
        registry.addResourceHandler("/resources/snapshot/**")    .addResourceLocations("file:/// " + resourceSnapshotPath+"/");
    }
}