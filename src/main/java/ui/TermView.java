package ui;

import backend.BotConfiguration;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class TermView extends HBox implements View {
	
	private RadioButton fallTerm = new RadioButton();
	private RadioButton winterTerm = new RadioButton("Calendar");
	private ToggleGroup group = new ToggleGroup();
	
	public TermView() {
		fallTerm.setText("Fall 2018");
		fallTerm.setToggleGroup(group);
		winterTerm.setText("Winter 2019");
		winterTerm.setToggleGroup(group);
		fallTerm.setSelected(true);
		BotConfiguration.setTerm("201809");
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER_LEFT);
		this.getChildren().addAll(fallTerm, winterTerm);
	}
	
	public void addChangeListener(ChangeListener<Toggle> listener) {
		group.selectedToggleProperty().addListener(listener);
	}

	@Override
	public void updateView() {
		// this method is empty on purpose
	}
}
