package com.techelevator.dao;

import com.techelevator.model.CheckIn;

import java.sql.SQLException;

public interface CheckInDao {
    CheckIn addCheckIn(CheckIn checkIn);
    CheckIn getCheckIn(int userId, int locationId);
}