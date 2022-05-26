package tgbot.management_service.service;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import tgbot.users.service.GetUserByIdRequest;
import tgbot.users.service.GetUserResponse;

public class UserClient extends WebServiceGatewaySupport {

    public GetUserResponse getUserById(long chatId) {
        GetUserByIdRequest getUserByIdRequest = new GetUserByIdRequest();
        getUserByIdRequest.setChatId(chatId);
        return (GetUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/users", getUserByIdRequest,
                        new SoapActionCallback("http://users.tgbot/service/GetUserByIdRequest"));
    }

}
