package pl.ans;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import pl.ans.model.Answer;
import pl.ans.model.Question;

public class PrimaryController implements Initializable {
    @FXML private Label label;
    @FXML private RadioButton answer1;
    @FXML private RadioButton answer2;
    @FXML private RadioButton answer3;
    @FXML private RadioButton answer4;
    @FXML private Label points;
    @FXML private Button primaryButton;
    private int id = 0;
    private Question question;


    @FXML
    private void getQuestion() {

       question = HttpClientClass.httpClientGetMethod(id);

        label.setText(question.getQuestion());
        String[] answers = question.getAnswers();
        answer1.setText(answers[0]);
        answer2.setText(answers[1]);
        answer3.setText(answers[2]);
        answer4.setText(answers[3]);
        points.setText("Punkty do uzyskania: " + question.getPoints());

        if(question.isLastQuestion()){
            primaryButton.setText("Zakończ");

        }
    }

    @FXML
    private void answer() throws IOException {
        List<Integer> answers = new ArrayList<>();
        if(answer1.isSelected()) answers.add(1);
        if(answer2.isSelected()) answers.add(2);
        if(answer3.isSelected()) answers.add(3);
        if(answer4.isSelected()) answers.add(4);
        Integer[] ans = new Integer[answers.size()];
        answers.toArray(ans);

        Answer answer = new Answer( (long) id, ans);
        HttpClientClass.httpClientPutMethod(answer);
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);
        answer4.setSelected(false);

        id++;
        if(question.isLastQuestion()) {
            HttpClientClass.httpClientPostMethod();
            id = 0;
            App.setRoot();
        }
        else getQuestion();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getQuestion();
    }
}
