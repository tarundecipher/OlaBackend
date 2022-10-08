package com.decipher.olaBackend.handler;


import com.decipher.olaBackend.DataSource.Connections;
import com.decipher.olaBackend.enums.HandlerRequest;
import com.decipher.olaBackend.models.WebSocketRequestModel;
import com.decipher.olaBackend.services.RiderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
@Slf4j
public class RiderHandler extends AbstractWebSocketHandler {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private Connections map;

    @Autowired
    private RiderService riderService;
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        WebSocketRequestModel webSocketRequestModel = new ObjectMapper().readValue(message.getPayload(), WebSocketRequestModel.class);
        if(HandlerRequest.BOOKREQUEST.getName().equals(webSocketRequestModel.getType())){
            kafkaTemplate.send("bookRequest",new ObjectMapper().writeValueAsString(webSocketRequestModel.getRequest()));
        }
        else if(HandlerRequest.PINGREQUEST.getName().equals(webSocketRequestModel.getType())){
            riderService.handlePingRequest(new ObjectMapper().writeValueAsString(webSocketRequestModel.getRequest()),session);
        }

    }
}
