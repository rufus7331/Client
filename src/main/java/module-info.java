module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    requires httpcore;
    requires httpclient;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    requires org.json;


    exports pl.ans;
    exports pl.ans.model;
    opens pl.ans.model to javafx.fxml;
    opens pl.ans to javafx.fxml;
}