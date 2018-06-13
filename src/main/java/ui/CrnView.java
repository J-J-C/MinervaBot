package ui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import backend.BotConfiguration;

public class CrnView extends VBox implements View {

    private Label crnLabel = new Label("CRN:");
    private List<TextField> crnFieldList = new ArrayList<>();
    private Button saveButton = new Button("Add");

    public CrnView() {
        this.setSpacing(BotConfiguration.SPACEING);
        this.getChildren().add(crnLabel);
        for(int i = 0; i < 8; i++) {
            crnFieldList.add(new TextField());
        }
        HBox crnListView = new HBox();
        crnListView.getChildren().addAll(crnFieldList);
        this.getChildren().addAll(crnListView);

        this.getChildren().add(saveButton);
        this.setAlignment(Pos.CENTER_LEFT);
    }
    
    public List<TextField> getCrnFields() {
    	return crnFieldList;
    }

	public void addMoustEventListener(EventHandler<MouseEvent> onClickEvent) {
		saveButton.setOnMouseClicked(onClickEvent);
	}

    @Override
    public void updateView() {
        for(TextField crnField: crnFieldList) {
            crnField.clear();
        }
        int counter = 0;
        for(String crn: BotConfiguration.getCRNs()){
            System.out.println(crn);
            crnFieldList.get(counter).setText(crn);
            counter++;
        }
    }
}
