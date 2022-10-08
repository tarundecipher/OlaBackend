package com.decipher.olaBackend.models;

import lombok.Data;

@Data
public class WebSocketRequestModel {
    String type;
    Object request;
}
