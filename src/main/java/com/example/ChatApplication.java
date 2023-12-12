package com.example;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ChatApplication extends Application {
    @FXML
    private ChatScreenController chatScreenController;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/chatScreen.fxml"));
            scene = new Scene(fxmlLoader.load());
            chatScreenController = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        chatScreenController.run();

        stage.setScene(scene);
        stage.setHeight(600);
        stage.setWidth(400);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Sample Chat Screen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}