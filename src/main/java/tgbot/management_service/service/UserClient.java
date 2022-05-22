package tgbot.management_service.service;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import tgbot.users_service.web_service.wsdl.GetUserByIdRequest;
import tgbot.users_service.web_service.wsdl.GetUserResponse;


public class UserClient extends WebServiceGatewaySupport {

    public GetUserResponse getUserById(long chatId) {
        GetUserByIdRequest getUserByIdRequest = new GetUserByIdRequest();
        getUserByIdRequest.setChatId(chatId);
        GetUserResponse getUserResponse = (GetUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/users", getUserByIdRequest,
                        new SoapActionCallback(
                                "http://users_service.tgbot/web-service/GetUserByIdRequest"));
        return getUserResponse;
    }

}
