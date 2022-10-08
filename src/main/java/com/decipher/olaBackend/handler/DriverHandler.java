package com.decipher.olaBackend.handler;

import com.decipher.olaBackend.DataSource.Connections;
import com.decipher.olaBackend.enums.HandlerRequest;
import com.decipher.olaBackend.models.WebSocketRequestModel;
import com.decipher.olaBackend.services.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
@Slf4j
public class DriverHandler extends AbstractWebSocketHandler {

    @Autowired
    private Connections connections;

    @Autowired
    private DriverService driverService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        WebSocketRequestModel webSocketRequestModel = new ObjectMapper().readValue(message.getPayload(), WebSocketRequestModel.class);
        if(HandlerRequest.LOCATIONREQUEST.getName().equals(webSocketRequestModel.getType())){
            driverService.handleLocationEvent(new ObjectMapper().writeValueAsString(webSocketRequestModel.getRequest()));
        }
        else if(HandlerRequest.PINGREQUEST.getName().equals(webSocketRequestModel.getType())){
            driverService.handlePingRequest(new ObjectMapper().writeValueAsString(webSocketRequestModel.getRequest()),session);
        }
        else if(HandlerRequest.ACCEPTREQUEST.getName().equals(webSocketRequestModel.getType())){
            driverService.handleAcceptRequest(new ObjectMapper().writeValueAsString(webSocketRequestModel.getRequest()));
        }
    }
}
