package com.decipher.olaBackend.models;

import lombok.Data;

@Data
public class BookRequestModel {
    private String userId;
    private LocationModel pickupLocation;
    private LocationModel destinationLocation;
}
