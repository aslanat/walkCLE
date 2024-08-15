package com.techelevator.service;
import com.techelevator.dao.QuestDao;
import com.techelevator.model.Quest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public class QuestService {
    private final QuestDao questDao;

    @Autowired
    public QuestService(QuestDao questDao) {
        this.questDao = questDao;
    }

    public List<Quest> search(String query) {
        return questDao.search(query);
    }

    // Additional business logic methods can be added here
}
