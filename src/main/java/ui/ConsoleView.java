package ui;

import java.lang.module.Configuration;
import java.util.Arrays;

import backend.BotConfiguration;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// View displays mcgill email, password, crns before final submission
public class ConsoleView extends VBox{

	private Label mcgillInfoDisplay = new Label("");
	private Label crnDisplay = new Label("");

	
	public ConsoleView() {
		this.setPadding(new Insets(BotConfiguration.MARGIN_OUTER));
		this.getChildren().addAll(mcgillInfoDisplay, crnDisplay );
	}
	
	
	public void updateView() {
		mcgillInfoDisplay.setText(BotConfiguration.getEmail() + " - " + BotConfiguration.getPassword());
		crnDisplay.setText(Arrays.toString(BotConfiguration.getCRNs().toArray()));
	}
}
