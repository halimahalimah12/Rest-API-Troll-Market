package com.indocyber.RestAPITrollMarket.documentation;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI appDocumentation(){
        var info = new Info()
                .title("Rest API Troll Market")
                .description("Latihan pakai Rest API")
                .contact(new Contact().name("Halimah").email("halimahalimah12@gmail.com"))
                .version("v 0.1.0");
        return  new OpenAPI().info(info);
    }
}
