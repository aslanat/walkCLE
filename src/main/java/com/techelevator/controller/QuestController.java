package com.techelevator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.Quest;
import com.techelevator.dao.QuestDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/quests")

public class QuestController {
        private QuestDao questDao;

        public QuestController(QuestDao questDao) {
            this.questDao = questDao;
        }

        @GetMapping
        public List<Quest> getAllQuests() {
            return questDao.getAllQuests();
        }

        @GetMapping("/{category}")
        public ResponseEntity<Quest> getQuestById(@PathVariable int category) {
            Quest quest = questDao.getQuestByCategory(category);
            if (quest != null) {
                return new ResponseEntity<>(quest, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping
        public ResponseEntity<Quest> createQuest(@RequestBody Quest quest) {
            Quest createdQuest = questDao.createQuest(quest);
            return new ResponseEntity<>(createdQuest, HttpStatus.CREATED);
        }

        @PutMapping("/{category}")
        public ResponseEntity<Void> updateQuest(@PathVariable int category, @RequestBody Quest quest) {
            quest.setQuestCategory(category);
            questDao.updateQuest(quest);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/{category}")
        public ResponseEntity<Void> deleteQuest(@PathVariable int category) {
            questDao.deleteQuest(category);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    @GetMapping("/search")
    public ResponseEntity<List<Quest>> searchQuests(@RequestParam String query) {
        List<Quest> quests = questDao.searchQuests(query);
        if (!quests.isEmpty()) {
            return new ResponseEntity<>(quests, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }

