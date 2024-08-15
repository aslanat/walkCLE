package com.techelevator.model;

public class Quest {
    private int questId;
    private int badgeId;
    private int questCategory;
    private String questName;
    private String questDescription;


    public Quest() {}

    // Parameterized constructor
    public Quest(int questId, int questCategory, String questName, String questDescription) {
        this.questId = questId;
        this.questCategory = questCategory;
        this.questName = questName;
        this.questDescription = questDescription;
    }


    public int getQuestId() { return questId; }
    public void setQuestId(int questId) { this.questId = questId; }


    public String getQuestName() { return questName; }
    public void setQuestName(String questName) { this.questName = questName; }

    public int getBadgeId() { return badgeId; }
    public void setBadgeId(int badgeId) { this.badgeId = badgeId; }

    public String getQuestDescription() { return questDescription; }
    public void setQuestDescription(String questDescription) { this.questDescription = questDescription; }

    public int getQuestCategory() { return questCategory; }
    public void setQuestCategory(int questCategory) { this.questCategory = questCategory; }
}
