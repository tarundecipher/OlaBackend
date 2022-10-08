package com.decipher.olaBackend.config;
import com.decipher.olaBackend.handler.DriverHandler;
import com.decipher.olaBackend.handler.RiderHandler;
import com.decipher.olaBackend.services.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import java.sql.Driver;

@Configuration
@EnableWebSocket
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private DriverHandler driverHandler;

    @Autowired
    private RiderHandler riderHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(riderHandler,"/rider").setAllowedOrigins("*");
        registry.addHandler(driverHandler,"/driver").setAllowedOrigins("*");

    }

}
