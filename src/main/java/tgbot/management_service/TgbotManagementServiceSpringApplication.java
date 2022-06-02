package tgbot.management_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tgbot.management_service.service.TeamClient;
import tgbot.management_service.service.UserClient;
import tgbot.users.service.*;

@SpringBootApplication
public class TgbotManagementServiceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgbotManagementServiceSpringApplication.class, args);
	}

//	@Bean
//	CommandLineRunner lookup(UserClient userClient, TeamClient teamClient) {
//		return args -> {
//			long id = 2L;
//			String nick = "ultricies";
//
//			if (args.length > 0) {
//				id = Long.parseLong(args[0]);
//				nick = args[1];
//			}
//			GetUserResponse response = userClient.getUserById(id);
//			System.err.println(response.getUserDTO().getFirstName());
//			GetUserResponse response1 = userClient.getUserByNick(nick);
//			System.err.println(response1.getUserDTO().getFirstName());
//
//			GetAllUsersResponse usersResponse = userClient.getAllUsers();
//			for (UserDTO userDTO : usersResponse.getUsersList()) {
//				System.out.println(userDTO.getFirstName());
//			}
//
//			GetTeamResponse teamResponse = teamClient.getTeamById(id);
//			System.err.println(teamResponse.getTeamDTO().getTeamName());
//
//			GetAllTeamsResponse allTeamsResponse = teamClient.getAllTeams();
//			for (TeamDTO teamDTO : allTeamsResponse.getTeamsList()) {
//				System.out.println(teamDTO.getTeamName());
//			}
//
////			TeamDTO teamDTO = teamResponse.getTeamDTO();
////			teamDTO.setTeamName("Test team");
////			GetTeamResponse getTeamResponse = teamClient.saveTeam(teamDTO);
////			System.err.println(getTeamResponse.getTeamDTO().getTeamName());
////			GetBooleanResponse booleanResponse = teamClient.deleteTeamById(9L);
////			if (booleanResponse.isDeleted()) {
////				System.err.println("Deleted");
////			}
//
////			UserDTO userDTO = response1.getUserDTO();
////			userDTO.setFirstName("Potter");
////			GetUserResponse response2 = userClient.saveUser(userDTO);
////			System.err.println(response2.getUserDTO().getFirstName());
////
////			GetBooleanResponse booleanResponse = userClient.deleteUser(11L);
////			if (booleanResponse.isDeleted()) {
////				System.err.println("Deleted");
////			}
//
//		};
//	}
}
