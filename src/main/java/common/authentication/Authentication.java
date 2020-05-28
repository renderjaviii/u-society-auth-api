package common.authentication;

import static springfox.documentation.builders.PathSelectors.regex;

import java.time.Clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Authentication {

    private static final String BASE_PACKAGE = Authentication.class.getPackage().getName();

    public static void main(String[] args) {
        SpringApplication.run(Authentication.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public Docket swaggerUsers() {
        final String title = "Users v.1";
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(title)
                .select()
                .paths(regex("/v1/*.*"))
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build()
                .apiInfo(buildApiInfo(title))
                .pathMapping("/");
    }

    private ApiInfo buildApiInfo(String title) {
        return new ApiInfoBuilder()
                .title(String.format("Rest Authentication Base - %s.", title))
                .description("REST Authentication API Base.")
                .version("1.0")
                .build();
    }

}

