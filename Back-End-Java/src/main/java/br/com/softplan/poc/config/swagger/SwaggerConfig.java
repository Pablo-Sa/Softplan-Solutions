package br.com.softplan.poc.config.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.softplan.poc.dto.accesscredentials.AccessCredentialsDto;
import br.com.softplan.poc.dto.token.TokenDto;
import br.com.softplan.poc.entity.AccessCredentials;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.softplan.poc"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(AccessCredentialsDto.class)
				.apiInfo(metaInfo())
				.globalOperationParameters(
						Arrays.asList(new ParameterBuilder()
								.name("Authorization")
								.description("Header para token JWT")
								.modelRef(new ModelRef("string"))
								.parameterType("header")
								.required(false)
								.build()));
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Back-End - POC Softplan", "POC SoftPlan.", "1.0", "Terms of Service",
				new Contact("Pablo Vinícius", "http://ondeployment.com.br/",
						"pablov.pereira12@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/license.html", new ArrayList<VendorExtension>());
		return apiInfo;
	}

}
