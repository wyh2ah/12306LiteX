package com.wxy.javafxfrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        try {
            String response = callBackendApi();
            welcomeText.setText("Response from backend: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // welcomeText.setText("Welcome to JavaFX Application!");
    }

    private String callBackendApi() throws Exception {
        URL url = new URL("http://localhost:8088/test/hello");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }
}