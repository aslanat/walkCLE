package com.techelevator.controller;

import com.techelevator.dao.UserQuestDao;
import com.techelevator.model.UserQuest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-quests")
public class UserQuestController {
    private final UserQuestDao userQuestDao;

    public UserQuestController(UserQuestDao userQuestDao) {
        this.userQuestDao = userQuestDao;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserQuest>> getUserQuestsByUserId(@PathVariable int userId) {
        List<UserQuest> quests = userQuestDao.getUserQuestsByUserId(userId);
        return new ResponseEntity<>(quests, HttpStatus.OK);
    }

//    @GetMapping"/{userId}/{questCategoryId}"
//
    @GetMapping("/category/{questCategory}")
    public ResponseEntity<List<UserQuest>> getUserQuestsByCategory(@PathVariable int questCategory) {
        List<UserQuest> quests = userQuestDao.getUserQuestsByCategory(questCategory);
        return new ResponseEntity<>(quests, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserQuest> createUserQuest(@RequestBody UserQuest userQuest) {
        UserQuest createdQuest = userQuestDao.createUserQuest(userQuest);
        return new ResponseEntity<>(createdQuest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserQuest(@PathVariable int id, @RequestBody UserQuest userQuest) {
        userQuest.setUserId(id);
        userQuestDao.updateUserQuest(userQuest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/award-badge")
    public ResponseEntity<Void> awardBadge(@RequestBody UserQuest userQuest) {
        if (userQuest.getAchieved()) {
            // Logic to award badge, possibly involving another DAO or service method call
            // For example, badgeService.awardBadge(userQuest.getUserId(), userQuest.getQuestCategory());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
