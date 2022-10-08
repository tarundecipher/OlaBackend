package com.decipher.olaBackend.DataSource;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Match {
    public Map<String,String> match;

    Match() {
        match = new HashMap<>();

    }
}
