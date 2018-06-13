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

public class CrnView extends VBox {



    private Label crnLabel = new Label("CRN:");
    private List<TextField> crnFieldList = new ArrayList<>();
    private Button saveButton = new Button("Add");


    public CrnView() {
        this.setSpacing(10);
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
}
