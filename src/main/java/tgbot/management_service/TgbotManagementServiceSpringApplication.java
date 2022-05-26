package tgbot.management_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tgbot.management_service.service.UserClient;
import tgbot.users.service.GetUserResponse;

@SpringBootApplication
public class TgbotManagementServiceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgbotManagementServiceSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(UserClient userClient) {
		return args -> {
			long id = 2L;

			if (args.length > 0) {
				id = Long.parseLong(args[0]);
			}
			GetUserResponse response = userClient.getUserById(id);
			System.err.println(response.getUserDTO().getFirstName());
		};
	}
}
