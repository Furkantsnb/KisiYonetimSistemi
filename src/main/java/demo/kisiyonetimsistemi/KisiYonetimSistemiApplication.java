package demo.kisiyonetimsistemi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
@EnableJpaAuditing // Auditing'i etkinleştir
@ComponentScan(basePackages = {"demo.kisiyonetimsistemi",
        "repository", "entity", "controller", "service", "security","mapper"})

@EntityScan(basePackages = {"entity"})  // entity paketinizin adını belirtin
@EnableJpaRepositories(basePackages = {"repository"})


public class KisiYonetimSistemiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KisiYonetimSistemiApplication.class, args);


    }
}