package com.techelevator.model;

public class Question {
    public Object getCorrectAnswer;
    private int questionId;
    private int questCategory;
    private String questionName;
    private String questionText;
    private String correctAnswer;
    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public int getQuestCategory() { return questCategory; }
    public void setQuestCategory(int questCategory) { this.questCategory = questCategory; }

    public String getQuestionName() { return questionName; }
    public void setQuestionName(String questionName) { this.questionName = questionName; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }


    
}
