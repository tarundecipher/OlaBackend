package com.decipher.olaBackend.DataSource;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Connections {
    public Map<String,Object> riderConnections;
    public Map<String,Object> driverConnections;
    Connections() {
         riderConnections = new HashMap<>();
         driverConnections = new HashMap<>();
    }
}
