package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Location;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLocationDao implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcLocationDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Location getLocationName(String locationName) {
        Location location = null;
        String sql = "SELECT location_pic, location_link, location_name, address, quest_id, description, wheelchair_accessible, kid_friendly, public_restroom, cost, latitude, longitude, quests.quest_name " +
                "FROM location LEFT JOIN QUESTS  ON quest_category = quests.quest_category WHERE location_name = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, locationName);
            if (results.next()) {
                location = mapRowToLocation(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return location;
    }

    @Override
    public Location getLocationById(int locationId) {
        Location location = null;
        String sql = "SELECT location_pic, location_link, location_name, address, quest_id, description, wheelchair_accessible, kid_friendly, public_restroom, cost, latitude, longitude " +
                "FROM location WHERE location_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, locationId);
            if (results.next()) {
                location = mapRowToLocation(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return location;
    }

    @Override
    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT l.location_id, l.location_pic, l.location_link, l.location_name, l.address, l.description, " +
                "l.wheelchair_accessible, l.kid_friendly, l.public_restroom, l.cost, l.latitude, l.longitude, q.quest_name " +
                "FROM location l LEFT JOIN quests q ON l.quest_category = q.quest_category";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                locations.add(mapRowToLocation(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return locations;
    }

    @Override
    public List<Location> getLocationByWheelchairAccessible() {
        return getLocationByCriteria("wheelchair_accessible");
    }

    @Override
    public List<Location> getLocationByKidFriendly() {
        return getLocationByCriteria("kid_friendly");
    }

    @Override
    public List<Location> getLocationByPublicRestroom() {
        return getLocationByCriteria("public_restroom");
    }

    private List<Location> getLocationByCriteria(String criteriaColumn) {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT location_pic, location_link, location_name, address, description, wheelchair_accessible, kid_friendly, public_restroom, cost, latitude, longitude " +
                "FROM location WHERE " + criteriaColumn + " = true";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                locations.add(mapRowToLocation(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return locations;
    }

    @Override
    public List<Location> getLocationByQuestCategory(int questCategory) {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT location_pic, location_link, location_name, address, description, wheelchair_accessible, kid_friendly, public_restroom, cost, latitude, longitude " +
                "FROM location WHERE quest_category = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, questCategory);
            while (results.next()) {
                locations.add(mapRowToLocation(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return locations;
    }

    @Override
    public void deleteLocation(int locationId) {
        String sql = "DELETE FROM location WHERE location_id = ?";
        jdbcTemplate.update(sql, locationId);
    }

    @Override
    public Location createLocation(Location location) {
        Location newLocation = null;
        String insertSql = "INSERT INTO location (location_pic, location_link, location_name, address, description, wheelchair_accessible, kid_friendly, public_restroom, cost, latitude, longitude) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING location_id";
        try {
            int newLocationId = jdbcTemplate.queryForObject(insertSql, new Object[]{
                    location.getLocationPic(), location.getLocationLink(), location.getLocationName(), location.getAddress(),
                    location.getDescription(), location.isWheelchairAccessible(), location.isKidFriendly(),
                    location.isPublicRestroom(), location.getCost(), location.getLatitude(), location.getLongitude()}, Integer.class);
            newLocation = getLocationById(newLocationId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newLocation;
    }

//    @Override
//    public List<Location> getHiddenGems() {
//        List<Location> hiddenGems = new ArrayList<>();
//        String sql = "SELECT * FROM location WHERE is_hidden_gem = TRUE";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while (results.next()) {
//            hiddenGems.add(mapRowToLocation(results));
//        }
//        return hiddenGems;
//    }

    private Location mapRowToLocation(SqlRowSet rowSet) {
        Location location = new Location();
        location.setLocationId(rowSet.getInt("location_id"));
        location.setLocationPic(rowSet.getString("location_pic"));
        location.setLocationLink(rowSet.getString("location_link"));
        location.setLocationName(rowSet.getString("location_name"));
        location.setDescription(rowSet.getString("description"));
        location.setAddress(rowSet.getString("address"));
        location.setCost(rowSet.getString("cost"));
        location.setKidFriendly(rowSet.getBoolean("kid_friendly"));
        location.setWheelchairAccessible(rowSet.getBoolean("wheelchair_accessible"));
        location.setPublicRestroom(rowSet.getBoolean("public_restroom"));
        location.setQuestName(rowSet.getString("quest_name"));
        location.setLatitude(rowSet.getBigDecimal("latitude"));
        location.setLongitude(rowSet.getBigDecimal("longitude"));
        return location;
    }
}