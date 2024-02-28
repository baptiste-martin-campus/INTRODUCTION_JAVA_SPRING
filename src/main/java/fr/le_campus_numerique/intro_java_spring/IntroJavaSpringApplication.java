package fr.le_campus_numerique.intro_java_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IntroJavaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroJavaSpringApplication.class, args);
	}

}
