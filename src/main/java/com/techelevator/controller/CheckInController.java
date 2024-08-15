package com.techelevator.controller;

import com.techelevator.model.CheckIn;
import com.techelevator.dao.CheckInDao;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/checkins")
public class CheckInController {
    private final CheckInDao checkInDao;

    public CheckInController(CheckInDao checkInDao) {
        this.checkInDao = checkInDao;
    }

    @PostMapping
    public CheckIn addCheckIn(@RequestBody CheckIn checkIn) {
        return checkInDao.addCheckIn(checkIn);
    }

    @GetMapping("/{userId}/{locationId}")
    public CheckIn getCheckIn(@PathVariable int userId, @PathVariable int locationId) {
        return checkInDao.getCheckIn(userId, locationId);
    }
}

