package com.techelevator.dao;

import com.techelevator.model.Quest;
import com.techelevator.model.Question;

import java.util.List;

public interface QuestionDao {

    Question getQuestionById(int questionId);

    List<Question> getAllQuestions();

    Question getQuestionByQuestionName(String questionName);

    Question createQuestion(Question question);

    void updateQuestion(Question question);
    
    void deleteQuestion(int questionId);
    void updateUserAnswer(int userId, int questionId, String answer, boolean isCorrect);
    void isAnswerCorrect(int questionId, int UserId, String answer);
    List<Question> getQuestionsByQuestId(int questId);

    }


