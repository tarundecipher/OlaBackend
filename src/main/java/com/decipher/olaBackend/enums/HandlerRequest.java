package com.decipher.olaBackend.enums;

public enum HandlerRequest {
    BOOKREQUEST("BOOKREQUEST"),
    PINGREQUEST("PINGREQUEST"),
    ACCEPTREQUEST("ACCEPTREQUEST"),

    LOCATIONREQUEST("LOCATIONREQUEST");

    String name;
    HandlerRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
