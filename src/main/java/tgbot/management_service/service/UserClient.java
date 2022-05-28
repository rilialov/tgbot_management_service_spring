package tgbot.management_service.service;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import tgbot.users.service.*;

public class UserClient extends WebServiceGatewaySupport {

    public GetUserResponse getUserById(long chatId) {
        GetUserByIdRequest getUserByIdRequest = new GetUserByIdRequest();
        getUserByIdRequest.setChatId(chatId);
        return (GetUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/users", getUserByIdRequest,
                        new SoapActionCallback("http://users.tgbot/service/GetUserByIdRequest"));
    }

    public GetUserResponse getUserByNick(String nickname) {
        GetUserByNickRequest getUserByNickRequest = new GetUserByNickRequest();
        getUserByNickRequest.setNickname(nickname);
        return (GetUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/users", getUserByNickRequest,
                        new SoapActionCallback("http://users.tgbot/service/GetUserByNickRequest"));
    }

    public GetUsersResponse getAllUsers() {
        GetUsersRequest getUsersRequest = new GetUsersRequest();
        return (GetUsersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/users", getUsersRequest,
                        new SoapActionCallback("http://users.tgbot/service/GetUsersRequest"));
    }
}
