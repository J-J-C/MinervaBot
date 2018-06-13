package backend;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import ui.CrnView;
import ui.SubmissionView;
import ui.TermView;
import ui.UserInfoView;

/**
 * A single controller which controls all view and a single bot configuration.
 *
 * @author Jiajun Chen
 */
public class ViewController {

    private UserInfoView userInfoView;
    private CrnView crnView;
    private SubmissionView submissionView;
    private TermView termView;

    public ViewController(UserInfoView userInfoView, CrnView crnView, TermView termView, SubmissionView submissionView) {
        this.userInfoView = userInfoView;
        this.crnView = crnView;
        this.termView = termView;
        this.submissionView = submissionView;

        userInfoView.addMoustEventListener(new UserInfoSaveEvent());
        crnView.addMoustEventListener(new CrnsSaveEvent());
        termView.addChangeListener(new TermToggleListener());
        submissionView.addRegistrationEvent(new RegisterCourseEvent());
        submissionView.addClearCrnEvent(new ClearCrnEvent());
        submissionView.addClearAllEvent(new ClearAllEvent());

    }

    public void updateUserInfo(String email, String password) {
        BotConfiguration.setEmail(email);
        BotConfiguration.setPassword(password);
        submissionView.updateView();
    }

    public void updateCrns(List<TextField> crnFieldList) {
        BotConfiguration.getCRNs().clear();
        for (TextField crn : crnFieldList) {
            if (crn.getText().length() > 0) {
                BotConfiguration.addCRN(crn.getText());
            }
        }
        submissionView.updateView();
    }

    class CrnsSaveEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            updateUserInfo(userInfoView.getEmail(), userInfoView.getPassword());
            updateCrns(crnView.getCrnFields());
        }
    }

    class UserInfoSaveEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            updateUserInfo(userInfoView.getEmail(), userInfoView.getPassword());
        }
    }

    class TermToggleListener implements ChangeListener<Toggle> {

        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
            String value = ((RadioButton) oldValue.getToggleGroup().getSelectedToggle()).getText(); // Cast object to radio button
            if (value.contains("Winter")) {
                BotConfiguration.setTerm("201901");
            } else {
                BotConfiguration.setTerm("201809");
            }
            submissionView.updateView();
        }
    }

    class RegisterCourseEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            Task<Void> registrationTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    RegistrationWorker.getInstance().startRegistration();
                    return null;
                }
            };
            registrationTask.setOnSucceeded(e -> {
                String message = "All is done! :) Please verify your registration on Minerva";
                Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
                alert.showAndWait();
            });
            registrationTask.setOnFailed(e -> {
                System.out.println(e.toString());
                String message = "RegistrationWorker Failed. Please check your McGill Email and password!";
                Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
                alert.showAndWait();
            });
            new Thread(registrationTask).start();
        }
    }

    class ClearCrnEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            BotConfiguration.getCRNs().clear();
            updateCrns(crnView.getCrnFields());
            submissionView.updateView();
        }
    }

    class ClearAllEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            BotConfiguration.setEmail("");
            BotConfiguration.setPassword("");
            BotConfiguration.getCRNs().clear();
            updateUserInfo(userInfoView.getEmail(), userInfoView.getPassword());
            updateCrns(crnView.getCrnFields());
            submissionView.updateView();
        }
    }


}
