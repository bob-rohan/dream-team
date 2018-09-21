package rohan.dreamteam.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = { "rohan.dreamteam.restserver", "rohan.dreamteam.services", "rohan.dreamteam.transformers" })
@EnableScheduling
@EnableMongoRepositories("rohan.dreamteam.services.dao")
public class DreamTeamRestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DreamTeamRestApplication.class, args);
	}

	/**
	 * https://spring.io/guides/gs/rest-service-cors/
	 * 
	 * @return WebMvcConfigurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
