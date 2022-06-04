package pl.ans.model;

import java.io.Serializable;

public class Question implements Serializable {
    private String question;
    private String[] answers;
    private int points;
    private boolean lastQuestion;

    public Question() {}


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

     public String[] getAnswers() {
        return answers;
    }

    public int getPoints() {
        return points;
    }

    public boolean isLastQuestion() {
        return lastQuestion;
    }


    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setLastQuestion(boolean lastQuestion) {
        this.lastQuestion = lastQuestion;
    }

    @Override
    public String toString() {
        return
                question + "\n" +
                answers[0]  + "\n" +
                answers[1] + "\n" +
                answers[2] + "\n" +
                answers[3] + "\n" +
                "punkty do uzyskania: " + points;
    }
}
