package com.techelevator.dao;


import com.techelevator.model.UserQuest;
import java.util.List;

public interface UserQuestDao {
    List<UserQuest> getUserQuestsByCategory(int questCategory);
    List<UserQuest> getUserQuestsByUserId(int userId);
    void updateUserQuest(UserQuest userQuest);
    UserQuest createUserQuest(UserQuest userQuest);  // Changed to return UserQuest
}


