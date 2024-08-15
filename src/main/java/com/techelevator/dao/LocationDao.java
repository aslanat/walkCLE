package com.techelevator.dao;


import java.util.List;
import com.techelevator.model.Location;

public interface LocationDao {
    
     Location getLocationName(String locationName);

     Location getLocationById(int locationId);


     List<Location> getLocations();

     List <Location> getLocationByWheelchairAccessible();

     List <Location> getLocationByKidFriendly();

     List <Location> getLocationByPublicRestroom();

     List <Location> getLocationByQuestCategory(int questCategory);
     
     void deleteLocation(int locationId);

     Location createLocation(Location location);

//     List<Location> getHiddenGems(); // Added for the hidden gem field

}
