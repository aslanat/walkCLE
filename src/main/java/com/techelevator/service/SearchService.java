package com.techelevator.service;

import com.techelevator.dao.SearchDao;
import com.techelevator.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchDao searchDao;

    @Autowired
    public SearchService(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    public List<SearchResult> search(String query) {
        return searchDao.search(query);
    }

    // Additional business logic methods can be added here
}