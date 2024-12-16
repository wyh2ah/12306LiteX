package com.wxy.javafxfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class GptController {

    @FXML
    private VBox chatContainer;

    @FXML
    private TextField userInputField;

    private int userId;
    private String username;
    private String initialMessage;
    private HttpClient httpClient = HttpClient.newHttpClient();

    public void setInitialMessage(int userId, String username, String message) {
        this.userId = userId;
        this.username = username;
        this.initialMessage = message;
        addUserMessage(initialMessage);
        getGptResponse(initialMessage);
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(loader.load(), Settings.get_x(), Settings.get_y());
        HomeController controller = loader.getController();
        controller.setParameters(userId, username);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSend() {
        String userMessage = userInputField.getText().trim();
        if (!userMessage.isEmpty()) {
            userInputField.clear();
            addUserMessage(userMessage);
            getGptResponse(userMessage);
        }
    }

    private void addUserMessage(String msg) {
        HBox messageBox = new HBox();
        messageBox.getStyleClass().add("message-box-user");

        Label messageLabel = new Label(msg);
        messageLabel.getStyleClass().add("user-message");
        messageLabel.setWrapText(true);

        messageBox.getChildren().add(messageLabel);
        chatContainer.getChildren().add(messageBox);
    }

    private void addGptMessage(String msg) {
        HBox messageBox = new HBox();
        messageBox.getStyleClass().add("message-box-gpt");

        Label messageLabel = new Label(msg);
        messageLabel.getStyleClass().add("gpt-message");
        messageLabel.setWrapText(true);

        messageBox.getChildren().add(messageLabel);
        chatContainer.getChildren().add(messageBox);
    }

    private void getGptResponse(String userMessage) {
        // 调用GPT接口，假设有一个HTTP接口: POST http://localhost:8088/api/gpt
        // 请求体包含 { "message": "..."} 并返回 { "response": "..." }
        // 根据实际API调整

        try {
            String requestBody = "{\"message\":\"" + userMessage.replace("\"", "\\\"") + "\"}";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8088/api/chat"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        // 假设返回json: {"response":"GPT的回答..."}
                        String responseBody = response.body();
                        String gptReply = parseGptResponse(responseBody);
                        javafx.application.Platform.runLater(() -> addGptMessage(gptReply));
                    });
        } catch (Exception e) {
            e.printStackTrace();
            javafx.application.Platform.runLater(() -> addGptMessage("Error getting response."));
        }
    }

    private String parseGptResponse(String jsonResponse) {

        // 简单解析字符串中的response字段
        // 实际应使用JSON解析库
        // 假设返回格式: {"response":"GPT的回答..."}
        int index = jsonResponse.indexOf("\"response\"");
        if (index != -1) {
            int start = jsonResponse.indexOf(":", index) + 1;
            int end = jsonResponse.lastIndexOf("\"");
            if (start > 0 && end > start) {
                String trimmed = jsonResponse.substring(start, end).trim();
                trimmed = trimmed.replaceAll("^\"|\"$", "");
                return trimmed;
            }
        }
        return "No response from GPT.";
    }
}
