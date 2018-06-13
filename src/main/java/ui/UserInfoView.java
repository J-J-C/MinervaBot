package ui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInfoView extends HBox {



    private Label emailLabel = new Label("McGill Email");
    private Label passwordLabel = new Label("Password");
    private TextField emailField = new TextField();
    private TextField passwordField = new TextField();
    private Button saveButton = new Button("Save");

    private VBox emailView = new VBox(emailLabel, emailField);
    private VBox passwordView = new VBox(passwordLabel, passwordField);

    public UserInfoView() {
        this.setSpacing(10);
        emailField.setMinWidth(200);
        passwordField.setMinWidth(200);

        this.getChildren().add(emailView);
        this.getChildren().add(passwordView);
        this.getChildren().add(saveButton);
        this.setAlignment(Pos.CENTER_LEFT);
    }

	public String getEmail() {
		return emailField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
	
	public void addMoustEventListener(EventHandler<MouseEvent> onClickEvent) {
		saveButton.setOnMouseClicked(onClickEvent);
	}
}
