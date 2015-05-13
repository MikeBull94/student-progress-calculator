package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.model.StudentProfile;
import uk.ac.dmu.studentprogress.view.View;
import uk.ac.dmu.studentprogress.view.tab.InputMarksTab;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@link ActionListener} for the 'clear marks' button.
 */
public final class ClearMarksListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ClearMarksListener.class);

	/**
	 * The model to clear marks for.
	 */
	private final Model model;

	/**
	 * The view to display any errors/information in.
	 */
	private final View view;

	/**
	 * The input marks tab.
	 */
	private final InputMarksTab inputMarksTab;

	/**
	 * Creates a new {@link ClearMarksListener}.
	 * @param model The model to clear marks for.
	 * @param view The view to display any errors in.
	 * @param inputMarksTab The input marks tab.
	 */
	public ClearMarksListener(Model model, View view, InputMarksTab inputMarksTab) {
		this.model = model;
		this.view = view;
		this.inputMarksTab = inputMarksTab;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StudentProfile profile = model.getProfile();

		if (profile == null) {
			view.displayError("You must load an existing profile or create a new profile before clearing marks.");
			return;
		}

		for (JTextField textField : inputMarksTab.getCourseworkMarkTextFields()) {
			textField.setText("0");
		}

		for (JTextField textField : inputMarksTab.getExamMarkTextFields()) {
			textField.setText("0");
		}

		model.getProfile().resetModuleMarks();

		logger.info("Cleared marks.");
		view.displayInformationDialog("Marks cleared.", "Marks Submitted");
	}
}
