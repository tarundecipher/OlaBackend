package com.decipher.olaBackend.consumers;


import com.decipher.olaBackend.DataSource.Connections;
import com.decipher.olaBackend.models.LocationRequestModel;
import com.decipher.olaBackend.services.DriverService;
import com.decipher.olaBackend.util.RedisRepoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
@Slf4j
public class DriverLocationConsumer {

    @Autowired
    private Connections map;

    @Autowired
    private RedisRepoUtil redisRepoUtil;

    @Autowired
    private DriverService driverService;

    @KafkaListener(topics = "driverLocation", groupId = "group-id")
    void locationListener(String message) throws IOException {
        log.info(message+" in consumer");
        LocationRequestModel location = new ObjectMapper().readValue(message, LocationRequestModel.class);
        WebSocketSession session = (WebSocketSession) map.riderConnections.get(location.getUserId());
        driverService.notifyAcceptRequest(location);
    }
}
