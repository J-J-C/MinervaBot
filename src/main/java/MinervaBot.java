import backend.BotConfiguration;
import backend.ViewController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.CrnView;
import ui.SubmissionView;
import ui.UserInfoView;

public class MinervaBot extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeUIComponent(primaryStage);
    }

    private void initializeUIComponent(Stage primaryStage) {

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: #ffad99;");
        root.setHgap(BotConfiguration.MARGIN_OUTER);
        root.setVgap(BotConfiguration.MARGIN_OUTER);
        root.setPadding(new Insets(BotConfiguration.MARGIN_OUTER));

        UserInfoView userInfoView = new UserInfoView();
        CrnView crnView = new CrnView();
        SubmissionView submissionView = new SubmissionView();
        ViewController controller = new ViewController(userInfoView, crnView, submissionView);
        root.add(userInfoView, 0, 0);
        root.add(crnView, 0, 1);
        root.add(submissionView, 0, 2);

        Scene mainBoard = new Scene(root, 500, 400);
        primaryStage.setScene(mainBoard);
        primaryStage.show();
    }
}

