package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {


    public void start(Stage primaryStage) throws IOException {
        Text text1 = new Text("Hi how are you");
        text1.setFont(Font.font("verdana", FontWeight.BOLD,
                FontPosture.REGULAR, 20));
        text1.setX(50);
        text1.setY(75);
        text1.setStrikethrough(true);

        Text text2 = new Text();
        text2.setText("Welcome to Tutorialspoint");
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text2.setUnderline(true);
        text2.setX(100); text2.setY(130);

        Group root = new Group();
        Scene scene = new Scene(root, 480, 320);
        root.getChildren().add(text1);
        root.getChildren().add(text2);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sample Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}