package com.techelevator.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.Question;
import com.techelevator.dao.QuestionDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("/questions")

public class QuestionController {
    private QuestionDao questionDao;

    public QuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
        Question question = questionDao.getQuestionById(id);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionDao.createQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateQuestion(@PathVariable int id, @RequestBody Question question) {
        question.setQuestionId(id);
        questionDao.updateQuestion(question);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int id) {
        questionDao.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/submitAnswer")
    public ResponseEntity<Void> submitAnswer(@RequestParam int questionId, @RequestParam int userId, @RequestParam String answer) {
        questionDao.isAnswerCorrect(questionId, userId, answer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/quest/{questId}")
    public List<Question> getQuestionsByQuestId(@PathVariable("id") int questId) {
        return questionDao.getQuestionsByQuestId(questId);
    }
}



