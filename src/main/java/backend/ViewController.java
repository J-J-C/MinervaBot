package backend;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ui.CrnView;
import ui.SubmissionView;
import ui.UserInfoView;

/**
 * A single controller which controls all view and a single bot configuration.
 * @author Jiajun Chen
 *
 */
public class ViewController {
	
	 private UserInfoView userInfoView;
	 private CrnView crnView;
	 private SubmissionView submissionView;
	
	 public ViewController(UserInfoView userInfoView, CrnView crnView, SubmissionView submissionView) {
		 this.userInfoView = userInfoView;
		 this.crnView = crnView;
		 this.submissionView = submissionView;
		 
		 userInfoView.addMoustEventListener(new UserInfoSaveEvent());
		 crnView.addMoustEventListener(new CrnsSaveEvent());
	 }
	 
	 public void updateUserInfo(String email, String password) {
		 BotConfiguration.setEmail(email);
		 BotConfiguration.setPassword(password);
		 System.out.println("called" + BotConfiguration.getEmail() + BotConfiguration.getPassword());
		 submissionView.updateView();
	 }
	 
	 public void updateCrns(List<TextField> crnFieldList) {
		 for(TextField crn: crnFieldList) {
			 BotConfiguration.addCRN(crn.getText());
		 }
		 submissionView.updateView();
	 }
	 
	 
	 class CrnsSaveEvent implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			updateCrns(crnView.getCrnFields());
		}
	 }
	 
	 class UserInfoSaveEvent implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			updateUserInfo(userInfoView.getEmail(), userInfoView.getPassword());
		}
	 }
}
