package com.techelevator.controller;

import com.techelevator.dao.UserLocationDao;
import com.techelevator.model.CheckIn;
import com.techelevator.model.UserLocation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/user-locations")
public class UserLocationController {
    private final UserLocationDao userLocationDao;

    public UserLocationController(UserLocationDao userLocationDao) {
        this.userLocationDao = userLocationDao;
    }

    @GetMapping("/user/{userId}")
    public List<UserLocation> getUserLocationsByUserId(@PathVariable int userId) {
        return userLocationDao.getUserLocationsByUserId(userId);
    }

    @GetMapping("/checkin/{userId}/{locationId}")
    public ResponseEntity<CheckIn> getCheckIn(@PathVariable int userId, @PathVariable int locationId) {
        CheckIn checkIn = userLocationDao.getCheckIn(userId, locationId);
        if (checkIn != null) {
            return new ResponseEntity<>(checkIn, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //    @GetMapping("/{userId}/{locationId}")
//    public CheckIn getCheckIn(@PathVariable int userId, @PathVariable int locationId) {
//        return userLocationDao.getCheckIn(userId, locationId);
//    }
    @GetMapping("/{userId}/{locationId}")
    public ResponseEntity<UserLocation> getUserLocation(@PathVariable int userId, @PathVariable int locationId) {
        UserLocation userLocation = userLocationDao.getUserLocationsByUserId(userId).stream()
                .filter(loc -> loc.getLocationId() == locationId)
                .findFirst()
                .orElse(null);
        if (userLocation != null) {
            return new ResponseEntity<>(userLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserLocation> createUserLocation(@RequestBody UserLocation userLocation) {
        UserLocation createdUserLocation = userLocationDao.createUserLocation(userLocation);
        return new ResponseEntity<>(createdUserLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{locationId}")
    public ResponseEntity<Void> updateUserLocation(@PathVariable int userId, @PathVariable int locationId, @RequestBody UserLocation userLocation) {
        userLocationDao.updateUserLocation(userId, locationId, userLocation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
