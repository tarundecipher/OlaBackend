package com.decipher.olaBackend.services;

import com.decipher.olaBackend.DataSource.Connections;
import com.decipher.olaBackend.DataSource.Match;
import com.decipher.olaBackend.models.AcceptRequestModel;
import com.decipher.olaBackend.models.LocationRequestModel;
import com.decipher.olaBackend.models.PingMessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Objects;

@Component
public class DriverService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private Connections connections;

    @Autowired
    Match match;

    public void handleLocationEvent(String message) throws JsonProcessingException {
        LocationRequestModel location = new ObjectMapper().readValue(message, LocationRequestModel.class);
        kafkaTemplate.send("driverLocation",message);
    }

    public void handlePingRequest(String message, WebSocketSession session) throws JsonProcessingException {
        PingMessageModel pingMessage = new ObjectMapper().readValue(message, PingMessageModel.class);
        connections.driverConnections.put(pingMessage.getUserId(),session);
    }

    public void handleAcceptRequest(String message) throws JsonProcessingException {
        AcceptRequestModel acceptRequestModel = new ObjectMapper().readValue(message, AcceptRequestModel.class);
        match.match.put(acceptRequestModel.getDriverId(), acceptRequestModel.getClientId());
    }

    public void notifyAcceptRequest(LocationRequestModel location) throws IOException {
       WebSocketSession webSocketSession = (WebSocketSession) connections.riderConnections.get(match.match.get(location.getUserId()));
       if(!Objects.isNull(webSocketSession)) {
           webSocketSession.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(location)));
       }
    }

}
