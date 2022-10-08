package com.decipher.olaBackend.services;

import com.decipher.olaBackend.DataSource.Connections;
import com.decipher.olaBackend.enums.HandlerRequest;
import com.decipher.olaBackend.models.BookRequestModel;
import com.decipher.olaBackend.models.WebSocketRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


@Component
public class BroadCastingService {

    @Autowired
    private Connections connections;

    public void broadCast(BookRequestModel bookRequestModel){
        connections.driverConnections.forEach((userId,session)->{
            WebSocketSession castedSession = (WebSocketSession) session;
            try {
                WebSocketRequestModel webSocketRequest = new WebSocketRequestModel();
                webSocketRequest.setType(HandlerRequest.BOOKREQUEST.getName());
                webSocketRequest.setRequest(bookRequestModel);
                String message = new ObjectMapper().writeValueAsString(webSocketRequest);
                castedSession.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
