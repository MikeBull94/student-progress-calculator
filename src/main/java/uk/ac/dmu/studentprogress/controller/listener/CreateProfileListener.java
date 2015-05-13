package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.controller.Controller;
import uk.ac.dmu.studentprogress.model.Course;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.model.StudentProfile;
import uk.ac.dmu.studentprogress.view.SwingView;
import uk.ac.dmu.studentprogress.view.tab.CreateProfileTab;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@link ActionListener} for the 'create profile' button.
 */
public final class CreateProfileListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(CreateProfileListener.class);

	/**
	 * The model to create the profile in.
	 */
	private final Model model;

	/**
	 * The view to display any errors/information in.
	 */
	private final SwingView view;

	/**
	 * The controller to update the tabs.
	 */
	private final Controller controller;

	/**
	 * Creates a new {@link CreateProfileListener}.
	 * @param model The model to create the profile in.
	 * @param view The view to display any errors/information in.
	 * @param controller The controller to update the tabs.
	 */
	public CreateProfileListener(Model model, SwingView view, Controller controller) {
		this.model = model;
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CreateProfileTab createProfileTab = view.getCreateProfileTab();
		JTextField fullNameTextField = createProfileTab.getFullNameTextField();
		JTextField pNumberTextField = createProfileTab.getPNumberTextField();
		JComboBox<Course> courseOptionsComboBox = createProfileTab.getCourseOptionsComboBox();

		String pNumber = pNumberTextField.getText();
		String studentName = fullNameTextField.getText();
		Course course = (Course) courseOptionsComboBox.getSelectedItem();

		if (studentName.length() < 1) {
			controller.highlight(fullNameTextField);
			createProfileTab.getFullNameWarningLabel().setVisible(true);
			view.displayError("Username length must be at least 1.");
			return;
		}
		createProfileTab.getFullNameWarningLabel().setVisible(false);
		controller.removeHighlight(fullNameTextField);

		if (pNumber.length() < 1) {
			controller.highlight(pNumberTextField);
			createProfileTab.getPNumberWarningLabel().setVisible(true);
			view.displayError("P Number length must be at least 1.");
			return;
		}
		createProfileTab.getPNumberWarningLabel().setVisible(false);
		controller.removeHighlight(pNumberTextField);

		if (course == null) {
			controller.highlight(courseOptionsComboBox);
			createProfileTab.getCourseOptionsWarningLabel().setVisible(true);
			view.displayError("Must select a course.");
			return;
		}
		createProfileTab.getCourseOptionsWarningLabel().setVisible(false);
		controller.removeHighlight(courseOptionsComboBox);

		StudentProfile profile = new StudentProfile(studentName, pNumber, course);
		model.setProfile(profile);
		model.setLastLoadedProfile(new StudentProfile(profile));
		controller.updateTabs(view, profile);
		controller.selectTab(view, view.getInputMarksTab());
		logger.info("Created new profile " + profile + ".");
		view.displayInformationDialog("Profile successfully created.", "Profile Created");
	}
}
