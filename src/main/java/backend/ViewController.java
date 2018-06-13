package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import ui.*;

/**
 * A single controller which controls all view and a single bot configuration.
 *
 * @author Jiajun Chen
 */
public class ViewController {

    private List<View> viewList = new ArrayList<>();
    private UserInfoView userInfoView;
    private CrnView crnView;
    private SubmissionView submissionView;
    private TermView termView;

    public ViewController(UserInfoView userInfoView, CrnView crnView, TermView termView, SubmissionView submissionView) {
        this.userInfoView = userInfoView;
        this.crnView = crnView;
        this.termView = termView;
        this.submissionView = submissionView;

        crnView.addMoustEventListener(new CrnsSaveEvent());
        termView.addChangeListener(new TermToggleListener());
        submissionView.addRegistrationEvent(new RegisterCourseEvent());
        submissionView.addClearCrnEvent(new ClearCrnEvent());
        submissionView.addClearAllEvent(new ClearAllEvent());

        viewList.add(this.userInfoView);
        viewList.add(this.crnView);
        viewList.add(this.termView);
        viewList.add(this.submissionView);
    }

    public void updateUserInfo(String email, String password) {
        BotConfiguration.setEmail(email);
        BotConfiguration.setPassword(password);
    }

    public void updateCrns(List<TextField> crnFieldList) {
        BotConfiguration.getCRNs().clear();
        for (TextField crn : crnFieldList) {
            if (crn.getText().length() > 0) {
                BotConfiguration.addCRN(crn.getText());
            }
        }
    }

    private void notifyAllView() {
        for(View view: viewList) {
            view.updateView();
        }
    }

    class CrnsSaveEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            updateUserInfo(userInfoView.getEmail(), userInfoView.getPassword());
            updateCrns(crnView.getCrnFields());
            notifyAllView();
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
        }
    }

    class RegisterCourseEvent implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if(!BotConfiguration.infoIsComplete()) {
                String message = "Errors in email address, password or CRNs, please verify";
                Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
                alert.showAndWait();
                return;
            }
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
                String message = "RegistrationWorker Failed. Please verify your McGill email, password and CRNs!";
                Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
                alert.showAndWait();
            });
            new Thread(registrationTask).start();
        }
    }

    class ClearCrnEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            updateCrns(new ArrayList<>());
            notifyAllView();
        }
    }

    class ClearAllEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            updateUserInfo("", "");
            updateCrns(new ArrayList<>());
            notifyAllView();
        }
    }
}
