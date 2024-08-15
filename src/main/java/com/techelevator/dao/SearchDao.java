package com.techelevator.dao;

import com.techelevator.model.SearchResult;

import java.util.List;

public interface SearchDao {

    List<SearchResult> search(String query);

    // Additional DAO methods can be defined here
}