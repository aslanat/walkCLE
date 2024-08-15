package com.techelevator.model;

import java.time.LocalDateTime;

public class UserQuest {
    private int userId;
    private int questCategory;
    private LocalDateTime dateAchieved;

    private boolean achieved;
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getQuestCategory() { return questCategory; }
    public void setQuestCategory(int questCategory ) { this.questCategory = questCategory; }

    public LocalDateTime getDateAchieved() { return dateAchieved; }
    public void setDateAchieved(LocalDateTime dateAchieved) { this.dateAchieved = dateAchieved; }


    public boolean getAchieved() { return achieved; }
    public void setAchieved(boolean completed) {this.achieved = achieved;}


}
