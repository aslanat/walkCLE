package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Quest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcQuestDao implements QuestDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcQuestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Quest getQuestByCategory(int questCategory) {
        Quest quest = null;
        String sql = "SELECT quest_name, badge_id, quest_description FROM quests WHERE quest_category = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, questCategory);
            if (results.next()) {
                quest = mapRowToQuest(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return quest;
    }

    @Override
    public List<Quest> getAllQuests() {
        List<Quest> quests = new ArrayList<>();
        String sql = "SELECT quest_name, badge_id, quest_description, quest_category FROM quests";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Quest quest = mapRowToQuest(results);
                quests.add(quest);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return quests;
    }

    @Override
    public Quest getQuestByQuestName(String questName) {
        if (questName == null) throw new IllegalArgumentException("Quest name cannot be null");
        Quest quest = null;
        String sql = "SELECT quest_category, quest_name, badge_id, quest_description FROM quests WHERE quest_name = LOWER(TRIM(?));";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, questName);
            if (rowSet.next()) {
                quest = mapRowToQuest(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return quest;
    }

    @Override
    public Quest createQuest(Quest quest) {
        Quest newQuest = null;
        String insertQuestSql = "INSERT INTO quests (quest_name, badge_id, quest_description, quest_category) values (LOWER(TRIM(?)), ?, ?) RETURNING quest_category";
        try {
            int newQuestCategory = jdbcTemplate.queryForObject(insertQuestSql, new Object[]{ quest.getQuestName(), quest.getBadgeId(), quest.getQuestDescription()}, int.class);
            newQuest = getQuestByCategory(newQuestCategory);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newQuest;
    }
    @Override
    public void updateQuest(Quest quest) {
        String sql = "UPDATE quests SET quest_name = ?, badge_id = ?, quest_description = ? WHERE quest_category = ?";
        jdbcTemplate.update(sql, quest.getQuestName(), quest.getQuestDescription(), quest.getQuestCategory());
    }

    @Override
    public void deleteQuest(int category) {
        String sql = "DELETE FROM quests WHERE quest_category = ?";
        jdbcTemplate.update(sql, category);
    }

    @Override
    public List<Quest> search(String query) {
        return null;
    }

    @Override
    public List<Quest> searchQuests(String query) {
        List<Quest> quests = new ArrayList<>();
        String sql = "SELECT quest_name, quest_description, quest_category " +
                "FROM quests " +
                "WHERE quest_name ILIKE ? OR quest_description ILIKE ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + query + "%", "%" + query + "%");
            while (results.next()) {
                Quest quest = mapRowToQuest(results);
                quests.add(quest);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return quests;
    }



    private Quest mapRowToQuest(SqlRowSet rs) {
        Quest quest = new Quest();
        quest.setQuestCategory(rs.getInt("quest_category"));
        quest.setQuestName(rs.getString("quest_name"));
        quest.setBadgeId(rs.getInt("badge_id"));
        quest.setQuestDescription(rs.getString("quest_description"));
        return quest;
    }
    
}

