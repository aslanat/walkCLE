package com.techelevator.dao;

import com.techelevator.model.UserQuest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserQuestDao implements UserQuestDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserQuestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserQuest> getUserQuestsByCategory(int questCategory) {
        List<UserQuest> userQuests = new ArrayList<>();
        String sql = "SELECT user_id, quest_category, completed, date_achieved FROM user_quest WHERE quest_category = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, questCategory);
        while (results.next()) {
            UserQuest userQuest = mapRowToUserQuest(results);
            userQuests.add(userQuest);
        }
        return userQuests;
    }

    @Override
    public List<UserQuest> getUserQuestsByUserId(int userId) {
        List<UserQuest> userQuests = new ArrayList<>();
        String sql = "SELECT user_id, quest_category, completed, date_achieved FROM user_quest WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            UserQuest userQuest = mapRowToUserQuest(results);
            userQuests.add(userQuest);
        }
        return userQuests;
    }

    @Override
    public void updateUserQuest(UserQuest userQuest) {
        String sql = "UPDATE user_quest SET completed = ?, date_achieved = ? WHERE user_id = ? AND quest_category = ?";
        jdbcTemplate.update(sql, userQuest.getAchieved(), userQuest.getDateAchieved(), userQuest.getUserId(), userQuest.getQuestCategory());
    }

    @Override
    public UserQuest createUserQuest(UserQuest userQuest) {
        String sql = "INSERT INTO user_quest (user_id, quest_category, completed, date_achieved) VALUES (?, ?, ?, ?) RETURNING *";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userQuest.getUserId(), userQuest.getQuestCategory(), userQuest.getAchieved(), userQuest.getDateAchieved());
        if (results.next()) {
            return mapRowToUserQuest(results);
        }
        throw new RuntimeException("Error creating user quest, no return data.");
    }

    private UserQuest mapRowToUserQuest(SqlRowSet rs) {
        UserQuest userQuest = new UserQuest();
        userQuest.setUserId(rs.getInt("user_id"));
        userQuest.setQuestCategory(rs.getInt("quest_category"));
        userQuest.setAchieved(rs.getBoolean("completed"));
        userQuest.setDateAchieved(rs.getTimestamp("date_achieved").toLocalDateTime());
        return userQuest;
    }
}
