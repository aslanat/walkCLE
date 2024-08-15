package com.techelevator.model;

import java.time.LocalDateTime;

public class UserLocation {
        private int userId;
        private int locationId;
        private LocalDateTime timestamp;

        public void CheckIn() {
        }


        private boolean achieved;
        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }

        public int getLocationId() { return locationId; }
        public void setLocationId(int locationId ) { this.locationId = locationId; }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }


        public boolean getAchieved() { return achieved; }
        public void setAchieved() {this.achieved = achieved;}

        public boolean isAchieved() {  // Change the getter method name to match convention
                return achieved;
        }

        public void setAchieved(boolean achieved) {  // Update to accept a boolean argument
                this.achieved = achieved;
        }

}


