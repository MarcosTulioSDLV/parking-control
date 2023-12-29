package com.api.parkingcontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.parkingcontrol.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Parking Control Rest API")
                .description("This API is to manage Parking Spots in a Condominium, providing all CRUD (Create, read, update and delete) operations.")
                .version("1.0")
                .contact(new Contact("Marcos Soto", "https://ar.linkedin.com/in/marcos-tulio-soto-de-la-vega-8a6b9668", "mtsotodelavega@gmail.com"))
                .license("GNU General Public License")
                .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.html")
                .build();
    }

}
