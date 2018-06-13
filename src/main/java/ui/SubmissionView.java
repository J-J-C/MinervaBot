package ui;

import backend.BotConfiguration;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Collections;

// View displays mcgill email, password, crns before final submission
public class SubmissionView extends VBox implements View {

    private Label isGoodToRegister = new Label("Please fill in your McGill Email, Password and CRNs");
    private Label mcgillInfoDisplay = new Label("");
    private Label crnDisplay = new Label("");

    private Button registerButton = new Button("Register");
    private Button clearCrnButton = new Button("Clear CRNs");
    private Button clearAll = new Button("Clear All");

    private HBox buttonView = new HBox(registerButton, clearCrnButton, clearAll);

    public SubmissionView() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER_LEFT);
        buttonView.setSpacing(10);
        buttonView.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(mcgillInfoDisplay, crnDisplay, isGoodToRegister, buttonView);
        mcgillInfoDisplay.setFont(Font.font(16));
        crnDisplay.setFont(Font.font(16));
        isGoodToRegister.setTextFill(Color.web("#e22d2d"));
        isGoodToRegister.setFont(Font.font(20));
    }

    @Override
    public void updateView() {
        mcgillInfoDisplay.setText(
                "McGill Email: " + BotConfiguration.getEmail() + ", Password: " + String.join("", Collections.nCopies(BotConfiguration.getPassword().length(), "*")));

        String term = BotConfiguration.getTerm().equals("201809") ? "Fall 2018" : "Winter 2019";
        String text = term + " - CRNS: ";
        for (String crn : BotConfiguration.getCRNs()) {
            if (crn.length() > 0) {
                text = text + crn + ", ";
            }
        }
        crnDisplay.setText(text);

        StringBuilder info = new StringBuilder();
        boolean allSet = true;
        if (BotConfiguration.getEmail().length() < 2) {
            info.append("Missing McGill Email.");
            allSet = false;
        }
        if (BotConfiguration.getPassword().length() < 2) {
            info.append("Missing Password. ");
            allSet = false;
        }
        if (BotConfiguration.getCRNs().size() == 0) {
            info.append("Missing CRNs. ");
            allSet = false;
        }
        if (allSet) {
            isGoodToRegister.setText("Click register to register your course");
            isGoodToRegister.setTextFill(Color.web("#27a361"));

        } else {
            isGoodToRegister.setText(info.toString());
            isGoodToRegister.setTextFill(Color.web("#e22d2d"));
        }
    }

    public void addRegistrationEvent(EventHandler<MouseEvent> onClickEvent) {
        registerButton.setOnMouseClicked(onClickEvent);
    }

    public void addClearCrnEvent(EventHandler<MouseEvent> onClickEvent) {
        clearCrnButton.setOnMouseClicked(onClickEvent);
    }

    public void addClearAllEvent(EventHandler<MouseEvent> onClickEvent) {
        clearAll.setOnMouseClicked(onClickEvent);
    }
}
