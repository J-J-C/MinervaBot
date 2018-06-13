package ui;

import backend.BotConfiguration;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// View displays mcgill email, password, crns before final submission
public class SubmissionView extends VBox {

	private Label mcgillInfoDisplay = new Label("");
	private Label crnDisplay = new Label("");

	private Button registerButton = new Button("Register");
	private Button clearButton = new Button("Clear");

	public SubmissionView() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER_LEFT);
		this.getChildren().addAll(mcgillInfoDisplay, crnDisplay, registerButton, clearButton);
	}

	public void updateView() {
		mcgillInfoDisplay.setText(
				"McGill Email: " + BotConfiguration.getEmail() + ", Password: " + BotConfiguration.getPassword());
		String text = "CRNS: ";
		for (String crn : BotConfiguration.getCRNs()) {
			if (crn.length() > 0) {
				text = text + crn + ", ";
			}
		}
		crnDisplay.setText(text);
	}
}
