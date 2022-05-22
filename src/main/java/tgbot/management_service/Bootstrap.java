package tgbot.management_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tgbot.management_service.service.UserClient;
import tgbot.users_service.web_service.wsdl.GetUserResponse;


@Component
public class Bootstrap implements CommandLineRunner {


    @Override
    public void run(String... args) {
        UserClient userClient = new UserClient();
        GetUserResponse response = userClient.getUserById(1L);
        System.out.println(response.getUserDTO().getFirstName());
    }
}
