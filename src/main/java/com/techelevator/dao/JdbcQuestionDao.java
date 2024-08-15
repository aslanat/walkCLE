
package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Question;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcQuestionDao implements QuestionDao {

   private final JdbcTemplate jdbcTemplate;

   public JdbcQuestionDao(JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate = jdbcTemplate;
   }

   @Override
   public Question getQuestionById(int questionId) {
       Question question = null;
       String sql = "SELECT question_id, question_name, quest_id, question_text, correct_answer FROM questions WHERE question_id = ?";
       try {
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql, questionId);
           if (results.next()) {
               question = mapRowToQuestion(results);
           }
       } catch (CannotGetJdbcConnectionException e) {
           throw new DaoException("Unable to connect to server or database", e);
       }
       return question;
   }
   @Override
   public List<Question> getAllQuestions() {
       List<Question> questions = new ArrayList<>();
       String sql = "SELECT question_id, question_name, quest_id, question_text, correct_answer FROM questions";
       try {
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
           while (results.next()) {
               Question question = mapRowToQuestion(results);
               questions.add(question);
           }
       } catch (CannotGetJdbcConnectionException e) {
           throw new DaoException("Unable to connect to server or database", e);
       }
       return questions;
   }

   @Override
   public Question getQuestionByQuestionName(String questionName) {
       if (questionName == null) throw new IllegalArgumentException("Question name cannot be null");
       Question question = null;
       String sql = "SELECT question_id, question_name, quest_id, question_text, correct_answer FROM questions WHERE LOWER(TRIM(question_name)) = LOWER(TRIM(?))";
       try {
           SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, questionName);
           if (rowSet.next()) {
               question = mapRowToQuestion(rowSet);
           }
       } catch (CannotGetJdbcConnectionException e) {
           throw new DaoException("Unable to connect to server or database", e);
       }
       return question;
   }

   @Override
   public Question createQuestion(Question question) {
       Question newQuestion = null;
       String insertQuestSql = "INSERT INTO questions (question_name, quest_id, question_text, correct_answer) values (LOWER(TRIM(?)), ?, ?) RETURNING question_id";
       try {
           int newQuestionId = jdbcTemplate.queryForObject(insertQuestSql, new Object[]{ question.getQuestionName(), question.getQuestCategory(), question.getQuestionText(), question.getCorrectAnswer()}, int.class);
           newQuestion = getQuestionById(newQuestionId);
       } catch (CannotGetJdbcConnectionException e) {
           throw new DaoException("Unable to connect to server or database", e);
       } catch (DataIntegrityViolationException e) {
           throw new DaoException("Data integrity violation", e);
       }
       return newQuestion;
   }
   @Override
   public void updateQuestion(Question question) {
       String sql = "UPDATE questions SET question_name = ?, quest_id = ?, question_text = ?, correct_answer WHERE question_id = ?";
       jdbcTemplate.update(sql, question.getQuestionName(), question.getQuestCategory(), question.getQuestionText(), question.getCorrectAnswer());
   }

   @Override
   public void deleteQuestion(int questionId) {
       String sql = "DELETE FROM questions WHERE question_id = ?";
       jdbcTemplate.update(sql, questionId);
   }
    @Override
   public void updateUserAnswer (int userId, int questionId, String answer, boolean isCorrect){
       String sql = "INSERT INTO userQuestion (user_id, question_id, quest_id, answer, is_correct) " +
               "VALUES (?, ?, ?, ?, ?) " +
               "ON CONFLICT (user_id, question_id) " +
               "DO UPDATE SET answer = EXCLUDED.answer, is_correct = EXCLUDED.is_correct";
       try {
           jdbcTemplate.update(sql, userId, questionId, getQuestId(questionId), answer, isCorrect);
       } catch (CannotGetJdbcConnectionException e) {
           throw new DaoException("Unable to connect to server or database", e);
       } catch (DataIntegrityViolationException e) {
           throw new DaoException("Data integrity violation", e);
       }
   }

    private Object getQuestId(int questionId) {
        String sql = "SELECT quest_id FROM questions WHERE question_id = ?";
        Integer questId = null;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, questionId);
            if (results.next()) {
                questId = results.getInt("quest_id");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return questId;
    }
    @Override
    public void isAnswerCorrect(int questionId, int userId, String answer) {
        String sql = "SELECT correct_answer FROM questions WHERE question_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, questionId);
            if (results.next()) {
                String correctAnswer = results.getString("correct_answer");
                boolean isCorrect = correctAnswer != null && correctAnswer.equalsIgnoreCase(answer);
                String upsertSql = "INSERT INTO userQuestion (user_id, question_id, quest_id, answer, is_correct) " +
                        "VALUES (?, ?, (SELECT quest_id FROM questions WHERE question_id = ?), ?, ?) " +
                        "ON CONFLICT (user_id, question_id) " +
                        "DO UPDATE SET answer = EXCLUDED.answer, is_correct = EXCLUDED.is_correct";
                jdbcTemplate.update(upsertSql, userId, questionId, questionId, answer, isCorrect);
            } else {
                throw new DaoException("No question found with ID: " + questionId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<Question> getQuestionsByQuestId(int questId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT question_id, question_name, question_text, correct_answer FROM questions WHERE quest_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, questId);
            while (results.next()) {
                Question question = mapRowToQuestion(results);
                questions.add(question);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return questions;
    }


    private Question mapRowToQuestion(SqlRowSet rs) {
       Question question = new Question();
       question.setQuestionId(rs.getInt("question_id"));
       question.setQuestionName(rs.getString("question_name"));
       question.setQuestCategory(rs.getInt("quest_category"));
       question.setQuestionText(rs.getString("question_text"));
       question.setCorrectAnswer(rs.getString("correct_answer"));
       return question;
   }

}

