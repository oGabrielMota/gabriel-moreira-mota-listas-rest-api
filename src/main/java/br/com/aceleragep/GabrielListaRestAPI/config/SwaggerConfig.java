package br.com.aceleragep.GabrielListaRestAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;


@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpenApi() {
		OpenAPI openAPI = new OpenAPI();

		Info info = new Info();
		info.title("Gabriel Moreira Mota REST API");
		info.version("v0.0.1");
		info.description("<div>"
				+ " <h1>Seja Bem vindo(a) :<h1>"
				+ "<h4>Este é o Documentação de uma REST API de Listas e Itens.</h4>"
				+ "<h4>Ao decorrer a esse documento será possivel executar os testes de todos os HTTP Methods disponíveis</h4>"
				+ "	</div>");
		openAPI.info(info);
		
		openAPI.addTagsItem(new Tag().name("Listas").description("Gerencia as listas") );
		openAPI.addTagsItem(new Tag().name("Itens").description("Gerencia os itens ") );

		return openAPI;
	}
}
