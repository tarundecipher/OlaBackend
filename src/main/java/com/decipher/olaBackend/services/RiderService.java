package com.decipher.olaBackend.services;

import com.decipher.olaBackend.DataSource.Connections;
import com.decipher.olaBackend.models.PingMessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class RiderService {
    @Autowired
    private Connections connections;
    public void handlePingRequest(String message, WebSocketSession session) throws JsonProcessingException {
            PingMessageModel pingMessage = new ObjectMapper().readValue(message, PingMessageModel.class);
            connections.riderConnections.put(pingMessage.getUserId(),session);
    }
}
