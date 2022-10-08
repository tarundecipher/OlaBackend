package com.decipher.olaBackend.services;

import com.decipher.olaBackend.DataSource.Connections;
import com.decipher.olaBackend.models.BookRequestModel;
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
                String message = new ObjectMapper().writeValueAsString(bookRequestModel);
                castedSession.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
