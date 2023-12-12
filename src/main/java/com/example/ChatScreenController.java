package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


public class ChatScreenController {
    @FXML
    private Label userNameLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Button audioCallBtn;
    @FXML
    private Button videoCallBtn;
    @FXML
    private ListView<Message> chatList;
    @FXML
    private Button imgUploadBtn;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private Button sendButton;
    @FXML
    private Button voiceChatBtn;

    private int secondsElapsed;
    private Timeline timeline;

    public void executeSend() {
        if (timeline != null) timeline.stop();
        String message = inputTextArea.getText(); // Lấy nội dung từ TextArea
        if (message == null || message.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Input from chatbox can't be empty. Please enter the message");
            ButtonType buttonTypeOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(buttonTypeOk);
            alert.showAndWait();
        } else {
            Message userMessage = new Message(message, true);
            chatList.getItems().add(userMessage); // Thêm tin nhắn người dùng vào ListView
            chatList.scrollTo(chatList.getItems().size());

            inputTextArea.clear(); // Xóa nội dung trong TextArea
            autoChat();
        }
        inputTextArea.clear();
    }

    public void run() {
        chatList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Message item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label messageLabel = new Label(item.getContent());
                    messageLabel.getStyleClass().add("message");
                    setGraphic(messageLabel);

                    if (item.isUserMessage()) {
                        getStyleClass().add("right-message");
                    } else {
                        getStyleClass().add("left-message");
                    }
                }
            }
        });

        inputTextArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                executeSend();
            } 
        });

        sendButton.setOnAction(event -> {
            executeSend();
        });
    }

    public void executeAudioCall() {

    }

    public void executeVideoCall() {

    }

    public void executeImgUpload() {

    }

    public void executeVoiceChat() {

    }

    public void autoChat() {
        AtomicInteger botMessageCount = new AtomicInteger();

        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            // Gửi tin nhắn tự động từ "bot"
            String message = "A sample message from arbitrarily person";
            Message userMessage = new Message(message, false);
            chatList.getItems().add(userMessage);

            chatList.scrollTo(chatList.getItems().size());

            botMessageCount.getAndIncrement();

            if (botMessageCount.get() >= 3) {
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}