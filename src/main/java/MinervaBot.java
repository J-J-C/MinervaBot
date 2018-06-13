import backend.BotConfiguration;
import backend.ViewController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.CrnView;
import ui.SubmissionView;
import ui.TermView;
import ui.UserInfoView;

public class MinervaBot extends Application {

    // abstract the variable for future development
    static {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver-win.exe");
//        System.setProperty("webdriver.chrome.driver", "chromedriver");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeUIComponent(primaryStage);
    }

    private void initializeUIComponent(Stage primaryStage) {

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: #d6fffe;");
        root.setHgap(BotConfiguration.MARGIN_OUTER);
        root.setVgap(BotConfiguration.MARGIN_OUTER);
        root.setPadding(new Insets(BotConfiguration.MARGIN_OUTER));

        UserInfoView userInfoView = new UserInfoView();
        CrnView crnView = new CrnView();
        TermView termView = new TermView();
        SubmissionView submissionView = new SubmissionView();
        ViewController controller = new ViewController(userInfoView, crnView, termView, submissionView);
        root.add(userInfoView, 0, 0);
        root.add(crnView, 0, 1);
        root.add(termView, 0, 2);
        root.add(submissionView, 0, 3);
        Scene mainBoard = new Scene(root, 500, 330);
        primaryStage.setScene(mainBoard);
        primaryStage.setTitle("McGill Course RegistrationWorker Bot");
        primaryStage.show();
    }
}

