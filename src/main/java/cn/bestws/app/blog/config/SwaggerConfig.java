package cn.bestws.app.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.bestws.app.blog"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("blog api")
//                .license("MIT")
//                .licenseUrl("http://opensource.org/licenses/MIT")
//                .contact(new Contact("姚晨峰", "https://github.com/yaochenfeng/", "282696845@qq.com"))
//                .version("0.1")
                .build();

        return apiInfo;

    }
}
