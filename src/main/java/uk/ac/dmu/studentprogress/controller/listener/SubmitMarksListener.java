package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.controller.Controller;
import uk.ac.dmu.studentprogress.model.MarkType;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.model.Module;
import uk.ac.dmu.studentprogress.model.StudentProfile;
import uk.ac.dmu.studentprogress.view.SwingView;
import uk.ac.dmu.studentprogress.view.tab.InputMarksTab;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@link ActionListener} for the 'submit marks' button.
 */
public final class SubmitMarksListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SubmitMarksListener.class);

	/**
	 * The model to submit marks for.
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
	 * Creates a new {@link SubmitMarksListener}.
	 * @param model The model to submit marks for.
	 * @param view The view to display any errors in.
	 * @param controller The controller to update the tabs.
	 */
	public SubmitMarksListener(Model model, SwingView view, Controller controller) {
		this.model = model;
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		StudentProfile profile = model.getProfile();

		if (profile == null) {
			view.displayError("You must load an existing profile or create a new profile before submitting marks.");
			return;
		}

		InputMarksTab inputMarksTab = view.getInputMarksTab();

		int i = 0;
		for (Module module : profile.getCourse().getModules()) {
			JTextField cwkTextField = inputMarksTab.getCourseworkMarkTextField(i);
			String cwkText = cwkTextField.getText();
			if (cwkText.length() < 1) {
				controller.highlight(cwkTextField);
				inputMarksTab.getModuleWarningLabel(i).setVisible(true);
				view.displayError("Missing number for coursework mark in module " + module.getCode() + ".");
				return;
			}

			int cwkMark;
			try {
				cwkMark = Integer.parseInt(cwkText);
			} catch (NumberFormatException e) {
				controller.highlight(cwkTextField);
				inputMarksTab.getModuleWarningLabel(i).setVisible(true);
				view.displayError(e, "Invalid number \'" + cwkText + "\' for coursework mark in module " + module.getCode() + ".");
				return;
			}

			controller.removeHighlight(cwkTextField);
			profile.setMark(module, MarkType.COURSEWORK, cwkMark);

			JTextField examTextField = inputMarksTab.getExamMarkTextField(i);
			String examText = examTextField.getText();
			if (examText.length() < 1) {
				controller.highlight(examTextField);
				inputMarksTab.getModuleWarningLabel(i).setVisible(true);
				view.displayError("Missing number for exam mark in module " + module.getCode() + ".");
				return;
			}

			int examMark;
			try {
				examMark = Integer.parseInt(examText);
			} catch (NumberFormatException e) {
				controller.highlight(examTextField);
				inputMarksTab.getModuleWarningLabel(i).setVisible(true);
				view.displayError(e, "Invalid number \'" + examText + "\' for exam mark in module " + module.getCode() + ".");
				return;
			}

			controller.removeHighlight(examTextField);
			profile.setMark(module, MarkType.EXAM, examMark);

			inputMarksTab.getModuleWarningLabel(i).setVisible(false);
			i++;
		}

		if (!model.unsavedChanges()) {
			logger.info("No changes to be submitted.");
			view.displayError("No changes to be submitted.", "Submit Error");
			return;
		}

		controller.updateTabs(view, profile);
		controller.selectTab(view, view.getOverviewResultsTab());
		logger.info("Submitted marks: " + profile.getModuleMarks());
		view.displayInformationDialog("Marks submitted. Don't forget to save the students data!", "Marks Submitted");
	}
}
