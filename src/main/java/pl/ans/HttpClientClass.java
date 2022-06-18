package pl.ans;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pl.ans.model.Answer;
import pl.ans.model.Question;
import org.json.*;


import java.io.IOException;

public class HttpClientClass {

    public static Question httpClientGetMethod(int questionID) {

        final HttpClient client = HttpClientBuilder.create().build();
        final HttpGet request = new HttpGet("http://localhost:8080/quiz/question/" + questionID);
        Question question = new Question();

        try {

            final HttpResponse response = client.execute(request);
            final HttpEntity entity = response.getEntity();

            final String json = EntityUtils.toString(entity);
            
            JSONObject jsonObject = new JSONObject(json);

            String questionField = jsonObject.getString("question");
            JSONArray answersField = jsonObject.getJSONArray("answers");
            int points = jsonObject.getInt("points");
            boolean ostatnie = jsonObject.getBoolean("lastQuestion");

            String[] answersArray = new String[answersField.length()];

            for (int i = 0; i < answersField.length(); i++) {
                answersArray[i]= answersField.getString(i);
            }

            question.setQuestion(questionField);
            question.setAnswers(answersArray);
            question.setPoints(points);
            question.setLastQuestion(ostatnie);

            System.out.println("Kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

            if(response.getStatusLine().getStatusCode() == 404) {

                System.out.println("Brak danych do wyswietlenia!");
            } else if(response.getStatusLine().getStatusCode() == 200) {

                System.out.println("Wynik: " + question);
            }

        } catch (Exception e) {
            System.out.println("Problem with GET");
            e.printStackTrace();
        }
        return question;
    }

    public static void httpClientPutMethod(Answer answer) throws JsonProcessingException {
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/quiz/calculate");

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(answer);

        try{
            final StringEntity stringEntity = new StringEntity(jsonInString);
            httpPut.setEntity(stringEntity);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");

            final CloseableHttpResponse response = client.execute(httpPut);
            System.out.println("Kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public static void httpClientPostMethod() throws IOException {
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/quiz/report");

        final CloseableHttpResponse response = client.execute(httpPost);

        System.out.println("Kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());
        if(response.getStatusLine().getStatusCode() == 404 || response.getStatusLine().getStatusCode() == 400) {
            System.out.println("Nie zaliczono testu.");
        } else System.out.println("Gratulacje! Zaliczono.");
    }

}
