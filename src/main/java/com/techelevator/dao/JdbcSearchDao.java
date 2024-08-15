package com.techelevator.dao;
import com.techelevator.model.SearchResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcSearchDao implements SearchDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSearchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SearchResult> search(String query) {
        String sql = "SELECT * FROM search_results WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + query + "%"}, new SearchResultRowMapper());
    }

    // Additional methods can be implemented here

    private static class SearchResultRowMapper implements RowMapper<SearchResult> {
        @Override
        public SearchResult mapRow(ResultSet rs, int rowNum) throws SQLException {
            SearchResult searchResult = new SearchResult();
            searchResult.setId(rs.getLong("id"));
            searchResult.setName(rs.getString("name"));
            // Map other fields as needed
            return searchResult;
        }
    }
}