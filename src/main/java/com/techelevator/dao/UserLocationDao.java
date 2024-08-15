package com.techelevator.dao;

import com.techelevator.model.CheckIn;
import com.techelevator.model.UserLocation;

import java.util.List;

public interface UserLocationDao {
    List<UserLocation> getUserLocationsByUserId(int userId);

    UserLocation createUserLocation(UserLocation userLocation);

    void updateUserLocation(int userId, int locationId, UserLocation userLocation);

    CheckIn getCheckIn(int userId, int locationId);
}

