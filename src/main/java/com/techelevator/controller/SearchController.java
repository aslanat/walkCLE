package com.techelevator.controller;

import com.techelevator.dao.QuestDao;
import com.techelevator.model.SearchResult;
import com.techelevator.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final QuestDao questDao;
   // private final OtherDao otherDao; // Add other DAOs as needed

    public SearchController(QuestDao questDao) {//OtherDao otherDao
        this.questDao = questDao;
        //this.otherDao = otherDao;
    }

    @GetMapping
    public ResponseEntity<List<?>> search(
            @RequestParam("table") String table,
            @RequestParam("query") String query) {
        List<?> results;

        switch (table.toLowerCase()) {
            case "quests":
                results = questDao.searchQuests(query);
                break;
            // Add cases for other tables
            default:
                return ResponseEntity.badRequest().body(Collections.singletonList("Table not supported"));
        }

        return ResponseEntity.ok(results);
    }
}//^^^^^^^^^^^^^^^^^^^^changed this!!!!^^^^^^^^^^^^^^^^^^^