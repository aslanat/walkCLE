package com.techelevator.controller;

import com.techelevator.model.Location;
import com.techelevator.dao.LocationDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/location")

public class LocationController {
    private LocationDao locationDao;

    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @GetMapping
        public List<Location> getLocations() {
            return locationDao.getLocations();
        }
    
        @GetMapping("/{locationId}")
        public ResponseEntity<Location> getLocationById(@PathVariable int locationId) {
            Location location = locationDao.getLocationById(locationId);
            if (location != null) {
                return new ResponseEntity<>(location, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }    

        @PostMapping
        public ResponseEntity<Location> createLocation(@RequestBody Location location) {
            Location createdLocation = locationDao.createLocation(location);
            return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
            locationDao.deleteLocation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
     @GetMapping("/quest/{questCategory}")
    public ResponseEntity<List<Location>> getLocationByQuestCategory(@PathVariable int questCategory) {
        List<Location> locations = locationDao.getLocationByQuestCategory(questCategory);
        if (locations != null) {
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/hidden-gems")
//    public ResponseEntity<List<Location>> getHiddenGems() {
//        List<Location> hiddenGems = locationDao.getHiddenGems();
//        if (hiddenGems.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(hiddenGems, HttpStatus.OK);
//        }
//    }
}
