package com.decipher.olaBackend.consumers;


import com.decipher.olaBackend.models.BookRequestModel;
import com.decipher.olaBackend.services.BroadCastingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class BookingConsumer {

    @Autowired
    private BroadCastingService broadCastingService;

    @KafkaListener(topics = "bookRequest", groupId = "group-id")
    void bookingListener(String message) throws IOException {
        log.info(message+" in consumer");
        BookRequestModel bookRequestModel = new ObjectMapper().readValue(message, BookRequestModel.class);
        broadCastingService.broadCast(bookRequestModel);
    }
}
