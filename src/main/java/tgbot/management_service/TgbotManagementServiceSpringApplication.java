package tgbot.management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TgbotManagementServiceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgbotManagementServiceSpringApplication.class, args);
	}

}
