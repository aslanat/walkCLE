package com.techelevator.model;

import java.math.BigDecimal;

public class Location {
    private String questName;

    private String locationName;
    private String locationPic;
    private String locationLink;
    private int locationId;
    private boolean wheelchairAccessible;
    private boolean kidFriendly;
    private boolean publicRestroom;
    private String cost;
    private String address;
    private String description;
    private int questCategory;
    private BigDecimal latitude;
    private BigDecimal longitude;
    public String getLocationName() {
        return locationName;
    }
//    private boolean isHiddenGem; //added for hidden gems in the location table

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public String getLocationPic() {
        return locationPic;
    }

    public void setLocationPic(String locationPic) {
        this.locationPic = locationPic;
    }
    public String getLocationLink() {
        return locationLink;
    }

    public void setLocationLink(String locationLink) {
        this.locationLink = locationLink;
    }
    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public boolean isKidFriendly() {
        return kidFriendly;
    }

    public void setKidFriendly(boolean kidFriendly) {
        this.kidFriendly = kidFriendly;
    }

    public boolean isPublicRestroom() {
        return publicRestroom;
    }

    public void setPublicRestroom(boolean publicRestroom) {
        this.publicRestroom = publicRestroom;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuestCategory() {
        return questCategory;
    }

    public void setQuestCategory(int questCategory) {
        this.questCategory = questCategory;
    }
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    //Getters and Setters for the hidden gems
//    public boolean isHiddenGem() {
//        return isHiddenGem;
//    }
//
//    public void setHiddenGem(boolean hiddenGem) {
//        isHiddenGem = hiddenGem;
//    }
}
