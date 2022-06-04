package pl.ans.model;

import java.io.Serializable;
import java.util.Arrays;


public class Answer implements Serializable {
    private Long questionId;
    private Integer[] selectedAnswers;

    public Answer() {}

    public Answer(Long questionId, Integer[] selectedAnswers) {
        this.questionId = questionId;
        this.selectedAnswers = selectedAnswers;
    }

    public void setSelectedAnswers(Integer[] selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer[] getSelectedAnswers() {
        return selectedAnswers;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "questionId=" + questionId +
                ", selectedAnswers=" + Arrays.toString(selectedAnswers) +
                '}';
    }
}
