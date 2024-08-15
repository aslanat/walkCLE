package com.techelevator.dao;

import com.techelevator.model.CheckIn;
import com.techelevator.model.UserLocation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcUserLocationDao implements UserLocationDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserLocationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserLocation> getUserLocationsByUserId(int userId) {
        List<UserLocation> userLocations = new ArrayList<>();
        String sql = "SELECT user_id, location_id, timestamp, achieved FROM user_location WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            UserLocation userLocation = mapRowToUserLocation(results);
            userLocations.add(userLocation);
        }
        return userLocations;
    }

    @Override
    public UserLocation createUserLocation(UserLocation userLocation) {
        String sql = "INSERT INTO user_location (user_id, location_id, timestamp, achieved) VALUES (?, ?, ?, ?) RETURNING *";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userLocation.getUserId(), userLocation.getLocationId(), userLocation.getTimestamp(), userLocation.isAchieved());
        if (results.next()) {
            return mapRowToUserLocation(results);
        }
        throw new RuntimeException("Error creating user location, no return data.");
    }

    @Override
    public void updateUserLocation(int userId, int locationId, UserLocation userLocation) {
        String sql = "UPDATE user_location SET timestamp = ?, achieved = ? WHERE user_id = ? AND location_id = ?";
        jdbcTemplate.update(sql, userLocation.getTimestamp(), userLocation.isAchieved(), userId, locationId);
    }

    @Override
    public CheckIn getCheckIn(int userId, int locationId) {
        String sql = "SELECT user_id, location_id, timestamp, achieved FROM user_location WHERE user_id = ? AND location_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, locationId);
        if (results.next()) {
            CheckIn checkIn = new CheckIn();
            checkIn.setUserId(results.getInt("user_id"));
            checkIn.setLocationId(results.getInt("location_id"));
            checkIn.setTimestamp(results.getTimestamp("timestamp").toLocalDateTime());
            return checkIn;
        } else {
            return null; // Or throw an exception if you prefer
        }
    }


    private UserLocation mapRowToUserLocation(SqlRowSet rs) {
        UserLocation userLocation = new UserLocation();
        userLocation.setUserId(rs.getInt("user_id"));
        userLocation.setLocationId(rs.getInt("location_id"));
        userLocation.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        userLocation.setAchieved(rs.getBoolean("achieved")); // Works with setAchieved(boolean)
        return userLocation;
    }
}
