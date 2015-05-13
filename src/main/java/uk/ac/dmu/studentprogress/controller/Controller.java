package uk.ac.dmu.studentprogress.controller;

import uk.ac.dmu.studentprogress.controller.listener.AboutDialogListener;
import uk.ac.dmu.studentprogress.controller.listener.ClearMarksListener;
import uk.ac.dmu.studentprogress.controller.listener.CreateProfileListener;
import uk.ac.dmu.studentprogress.controller.listener.ExitListener;
import uk.ac.dmu.studentprogress.controller.listener.LoadStudentDataListener;
import uk.ac.dmu.studentprogress.controller.listener.RestoreFromSaveListener;
import uk.ac.dmu.studentprogress.controller.listener.SaveResultsListener;
import uk.ac.dmu.studentprogress.controller.listener.SaveStudentDataListener;
import uk.ac.dmu.studentprogress.controller.listener.SubmitMarksListener;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.model.MarkType;
import uk.ac.dmu.studentprogress.model.Module;
import uk.ac.dmu.studentprogress.model.StudentProfile;
import uk.ac.dmu.studentprogress.view.SwingView;
import uk.ac.dmu.studentprogress.view.menu.MenuBar;
import uk.ac.dmu.studentprogress.view.tab.Tab;
import uk.ac.dmu.studentprogress.view.tab.CreateProfileTab;
import uk.ac.dmu.studentprogress.view.tab.InputMarksTab;
import uk.ac.dmu.studentprogress.view.tab.OverviewResultsTab;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents the controller in the 'model-view-controller' pattern. The controller operates the program
 * by defining how certain events are handled as well as providing helper methods such as creating
 * text for the 'about dialog' and registering action listeners.
 */
public final class Controller {

	/**
	 * A list of highlighted {@link JComponent}s.
	 */
	private final List<JComponent> highlightedComponents = new ArrayList<>();

	/**
	 * The border to use on highlighted {@link JComponent}s
	 */
	private final Border highlightBorder = BorderFactory.createLineBorder(Color.RED);

	/**
	 * Creates a new {@link Controller}
	 * @param model The model.
	 * @param view The view.
	 */
	public Controller(Model model, SwingView view) {
		File currentDirectory = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents");

		FileFilter studentProfileFilter = new FileNameExtensionFilter("Student Profile file (.stud)", "stud");
		JFileChooser studentProfileFileChooser = new JFileChooser(currentDirectory);
		studentProfileFileChooser.setAcceptAllFileFilterUsed(false);
		studentProfileFileChooser.setFileFilter(studentProfileFilter);

		MenuBar menuBar = view.getMenuBar();
		menuBar.getLoadStudentMenuItem().addActionListener(new LoadStudentDataListener(model, view, this, studentProfileFileChooser));
		menuBar.getSaveStudentMenuItem().addActionListener(new SaveStudentDataListener(model, view, studentProfileFileChooser));
		menuBar.getExitMenuItem().addActionListener(new ExitListener(model, view));
		menuBar.getAboutMenuItem().addActionListener(new AboutDialogListener(view));

		view.getCreateProfileTab().getCreateProfileButton().addActionListener(new CreateProfileListener(model, view, this));

		InputMarksTab inputMarksTab = view.getInputMarksTab();
		inputMarksTab.getRestoreButton().addActionListener(new RestoreFromSaveListener(model, view, this));
		inputMarksTab.getClearButton().addActionListener(new ClearMarksListener(model, view, inputMarksTab));
		inputMarksTab.getSubmitButton().addActionListener(new SubmitMarksListener(model, view, this));


		FileFilter textFileFilter = new FileNameExtensionFilter("Text file (.txt)", "txt");
		JFileChooser textFileChooser = new JFileChooser(currentDirectory);
		textFileChooser.setAcceptAllFileFilterUsed(false);
		textFileChooser.setFileFilter(textFileFilter);

		OverviewResultsTab overviewResultsTab = view.getOverviewResultsTab();
		overviewResultsTab.getSaveResultsButton().addActionListener(new SaveResultsListener(view, textFileChooser, overviewResultsTab.getOverviewTextArea()));
	}

	/**
	 * Creates the text to be used on the overview tab.
	 * @return The text to be used on the overview tab.
	 */
	public String createOverviewText(StudentProfile profile) {
		StringBuilder message = new StringBuilder();

		message.append("Name:").append("\t").append(profile.getFullName()).append("\n");
		message.append("PNo:").append("\t").append(profile.getPNumber()).append("\n");
		message.append("Course").append("\t").append(profile.getCourse().getName()).append("\n\n");
		message.append("2nd Year average").append("\n");
		message.append("\t").append("Credits passed: ").append(profile.creditsPassed()).append("\n");
		message.append("\t").append("Year average mark: ").append(profile.yearAverageMark()).append("\n\n");
		message.append("=================").append("\n");

		for (Module module : profile.getCourse().getModules()) {
			message.append("[").append(module.getCode()).append("]").append("\t").append(module.getName()).append("\n\n\n");
			message.append("\t").append("Cwk Mark:").append("\t").append(profile.getMark(module, MarkType.COURSEWORK)).append("\n");
			message.append("\t").append("Exam Mark:").append("\t").append(profile.getMark(module, MarkType.EXAM)).append("\n");
			message.append("\t").append("---------------").append("\n");
			message.append("\t").append("Overall Mark:").append("\t").append(profile.getOverallMark(module)).append("\n\n");
			message.append("\t").append(profile.moduleResitRequired(module) ? "FAILED" : "PASSED").append("\n");
			message.append("=================").append("\n");
		}

		return message.toString();
	}

	/**
	 * Updates all the tabs in a view with new student profile information.
	 * @param view The view.
	 * @param profile The new student profile.
	 */
	public void updateTabs(SwingView view, StudentProfile profile) {
		CreateProfileTab createProfileTab = view.getCreateProfileTab();

		createProfileTab.getFullNameTextField().setText(profile.getFullName());
		removeHighlight(createProfileTab.getFullNameTextField());
		createProfileTab.getFullNameWarningLabel().setVisible(false);

		createProfileTab.getPNumberTextField().setText(profile.getPNumber());
		removeHighlight(createProfileTab.getPNumberTextField());
		createProfileTab.getPNumberWarningLabel().setVisible(false);

		createProfileTab.getCourseOptionsComboBox().setSelectedItem(profile.getCourse());
		removeHighlight(createProfileTab.getCourseOptionsComboBox());
		createProfileTab.getCourseOptionsWarningLabel().setVisible(false);


		InputMarksTab inputMarksTab = view.getInputMarksTab();
		Collection<Module> modules = profile.getCourse().getModules();

		for (int i = 0; i < inputMarksTab.getModuleTitleLabels().length; i++) {
			removeHighlight(inputMarksTab.getModuleTitleLabel(i));
			inputMarksTab.remove(inputMarksTab.getModuleTitleLabel(i));

			removeHighlight(inputMarksTab.getCourseworkMarkTextField(i));
			inputMarksTab.remove(inputMarksTab.getCourseworkMarkTextField(i));

			removeHighlight(inputMarksTab.getExamMarkTextField(i));
			inputMarksTab.remove(inputMarksTab.getExamMarkTextField(i));

			inputMarksTab.remove(inputMarksTab.getModuleWarningLabel(i));
		}

		int size = modules.size();
		inputMarksTab.setModuleTitleLabels(new JLabel[size]);
		inputMarksTab.setCourseworkMarkTextFields(new JTextField[size]);
		inputMarksTab.setExamMarkTextFields(new JTextField[size]);

		GridBagConstraints constraints = inputMarksTab.getConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridx = 0;
		constraints.gridy = 1;

		int i = 0;
		for (Module module : modules) {
			inputMarksTab.setModuleTitleLabel(i, new JLabel("[" + module.getCode() + "] " + module.getName()));

			int courseWorkMark = profile.getMark(module, MarkType.COURSEWORK);
			JTextField cwkMarkTextField = new JTextField(String.valueOf(courseWorkMark), 2);
			cwkMarkTextField.setHorizontalAlignment(JTextField.CENTER);
			inputMarksTab.setCourseworkMarkTextField(i, cwkMarkTextField);

			int examMark = profile.getMark(module, MarkType.EXAM);
			JTextField examMarkTextField = new JTextField(String.valueOf(examMark), 2);
			examMarkTextField.setHorizontalAlignment(JTextField.CENTER);
			inputMarksTab.setExamMarkTextField(i, examMarkTextField);

			JLabel warningLabel = new JLabel("!");
			warningLabel.setFont(new Font(warningLabel.getFont().getName(), Font.PLAIN, 28));
			warningLabel.setForeground(Color.RED);
			warningLabel.setToolTipText("Missing mark!");
			warningLabel.setVisible(false);
			inputMarksTab.setModuleWarningLabel(i, warningLabel);

			constraints.anchor = GridBagConstraints.WEST;
			inputMarksTab.add(inputMarksTab.getModuleTitleLabel(i), constraints);

			constraints.gridx++;
			constraints.anchor = GridBagConstraints.CENTER;
			inputMarksTab.add(inputMarksTab.getCourseworkMarkTextField(i), constraints);

			constraints.gridx++;
			inputMarksTab.add(inputMarksTab.getExamMarkTextField(i), constraints);

			constraints.gridx++;
			inputMarksTab.add(inputMarksTab.getModuleWarningLabel(i), constraints);

			constraints.gridx = 0;
			constraints.gridy++;
			i++;
		}

		OverviewResultsTab overviewResultsTab = view.getOverviewResultsTab();
		overviewResultsTab.getOverviewTextArea().setText(createOverviewText(profile));
		overviewResultsTab.getOverviewTextArea().setCaretPosition(0);
	}

	/**
	 * Selects a tab in a view.
	 * @param view The view.
	 * @param tab The index of the tab.
	 */
	public void selectTab(SwingView view, Tab tab) {
		view.getTabbedPane().setSelectedComponent(tab);
	}

	/**
	 * Highlights a {@link JComponent} with a red border.
	 * @param jComponent The {@link JComponent} to highlight.
	 */
	public void highlight(JComponent jComponent) {
		if (!highlightedComponents.contains(jComponent)) {
			highlightedComponents.add(jComponent);
			jComponent.setBorder(new CompoundBorder(highlightBorder, jComponent.getBorder()));
		}
	}

	/**
	 * Removes the highlight of a {@link JComponent}.
	 * @param jComponent The {@link JComponent} to remove the highlight from.
	 */
	public void removeHighlight(JComponent jComponent) {
		if (highlightedComponents.contains(jComponent)) {
			highlightedComponents.remove(jComponent);
			jComponent.setBorder(((CompoundBorder) jComponent.getBorder()).getInsideBorder());
		}
	}
}
