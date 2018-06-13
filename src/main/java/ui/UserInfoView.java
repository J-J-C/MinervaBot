package ui;

import backend.BotConfiguration;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInfoView extends HBox {



    private Label emailLabel = new Label("McGill Email");
    private Label passwordLabel = new Label("Password");
    private TextField emailField = new TextField();
    private PasswordField passwordField  = new PasswordField ();

    private VBox emailView = new VBox(emailLabel, emailField);
    private VBox passwordView = new VBox(passwordLabel, passwordField);

    public UserInfoView() {
        this.setSpacing(BotConfiguration.SPACEING);
        emailField.setMinWidth(BotConfiguration.INFO_TEXT_FIELD_WIDTH);
        passwordField.setMinWidth(BotConfiguration.INFO_TEXT_FIELD_WIDTH);

        this.getChildren().add(emailView);
        this.getChildren().add(passwordView);
        this.setAlignment(Pos.CENTER_LEFT);
    }

	public String getEmail() {
		return emailField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
}
