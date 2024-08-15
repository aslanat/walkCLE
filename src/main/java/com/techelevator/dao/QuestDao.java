package com.techelevator.dao;
import com.techelevator.model.Quest;
import java.util.List;

public interface QuestDao {
    Quest getQuestByCategory(int category);
    Quest getQuestByQuestName(String questName);
    List<Quest> getAllQuests();
    Quest createQuest(Quest quest);
    void updateQuest(Quest quest);
    void deleteQuest(int id);


    //quest search
    List<Quest> search(String query);

    List<Quest> searchQuests(String query);
}
