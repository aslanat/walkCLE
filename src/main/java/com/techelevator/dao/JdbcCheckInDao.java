package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CheckIn;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class JdbcCheckInDao implements CheckInDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcCheckInDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CheckIn addCheckIn(CheckIn checkIn) {
        CheckIn newCheckIn = null;
        String sql = "INSERT INTO user_location (user_id, location_id, timestamp) " +
                     "VALUES (?, ?, ?) ";
        try {
            int newCheckInId = jdbcTemplate.update(sql, checkIn.getUserId(), checkIn.getLocationId(), Timestamp.valueOf(checkIn.getTimestamp()));
            newCheckIn = getCheckIn(checkIn.getUserId(), checkIn.getLocationId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCheckIn;
    }

    @Override
    public CheckIn getCheckIn(int userId, int locationId){
        String sql = "SELECT user_id, location_id, timestamp FROM user_location " +
                     "WHERE user_id = ? AND location_id = ?";
        CheckIn checkIn = null;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, locationId);
            if (results.next()) {
                checkIn = mapRowToCheckIn(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return checkIn;
    }

    private CheckIn mapRowToCheckIn(SqlRowSet rs){
        CheckIn checkIn = new CheckIn();
        checkIn.setUserId(rs.getInt("user_id"));
        checkIn.setLocationId(rs.getInt("location_id"));

        Timestamp ts = rs.getTimestamp("timestamp");


        if(ts != null){
            checkIn.setTimestamp(ts.toLocalDateTime());
        }
        return checkIn;
    }
}