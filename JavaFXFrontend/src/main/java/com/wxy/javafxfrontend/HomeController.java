package com.wxy.javafxfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HomeController {
    private int userId;
    private String username;

    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField departureField;
    @FXML
    private TextField arrivalField;
    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private Button searchButton;
    @FXML
    private Button swapButton;
    @FXML
    private Button logoutButton;

    public void setParameters(int login_userId, String login_username) {
        this.userId = login_userId;
        this.username = login_username;
    }

    @FXML
    public void initialize() throws IOException {
        departureDatePicker.setValue(LocalDate.now());

        swapButton.setOnAction(event -> swapLocations());

        searchButton.setOnAction(event -> searchTickets());
    }

    private void swapLocations() {
        String from = departureField.getText();
        String to = arrivalField.getText();
        departureField.setText(to);
        arrivalField.setText(from);
    }

    private void searchTickets() {
        String from = departureField.getText().trim();
        String to = arrivalField.getText().trim();
        LocalDate date = departureDatePicker.getValue();

        // 在这里添加搜索逻辑
        System.out.println("Searching tickets from " + from + " to " + to + " on " + date);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(loader.load(), 1920, 1080);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
