package com.techelevator.model;

import java.time.LocalDateTime;

public class CheckIn {
    private int userId;
    private int locationId;
    private LocalDateTime timestamp;

    public CheckIn() {
    }

    public CheckIn(int userId, int locationId, LocalDateTime timestamp) {
        this.userId = userId;
        this.locationId = locationId;
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}