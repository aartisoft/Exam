package com.essam.microprocess.dressamdaher.JsonModel;

/**
 * Created by microprocess on 2018-10-16.
 */

public class WorngQestion {

    String questionID , question , correctAnswer , studentAnswer ;

    public WorngQestion() {
    }

    public WorngQestion(String questionID, String question, String correctAnswer, String studentAnswer) {
        this.questionID = questionID;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }


}
