package uk.ac.dmu.studentprogress.view.tab;

import uk.ac.dmu.studentprogress.model.Course;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/**
 * The 'input marks' tab.
 */
public final class InputMarksTab extends Tab {
	/**
	 * The module title labels.
	 */
	private JLabel[] moduleTitleLabels = new JLabel[Course.values().length];

	/**
	 * The coursework marks text fields.
	 */
	private JTextField[] courseworkMarkTextFields = new JTextField[Course.values().length];

	/**
	 * The exam marks text fields.
	 */
	private JTextField[] examMarkTextFields = new JTextField[Course.values().length];

	/**
	 * The module warning labels.
	 */
	private JLabel[] moduleWarningLabels = new JLabel[Course.values().length];

	/**
	 * The restore from save button.
	 */
	private final JButton restoreButton = new JButton("Restore From Save");

	/**
	 * The clear marks button.
	 */
	private final JButton clearButton = new JButton("Clear");

	/**
	 * The submit marks button.
	 */
	private final JButton submitButton = new JButton("Submit");

	/**
	 * Creates a new {@link InputMarksTab}.
	 */
	public InputMarksTab() {
		super("Input Marks", "Input the coursework and exam marks for each module");

		add(new JLabel("<html><b>Module</b></html>", SwingConstants.CENTER), constraints);

		constraints.gridx++;
		add(new JLabel("<html><b>Cwk Mark</b></html>", SwingConstants.CENTER), constraints);

		constraints.gridx++;
		add(new JLabel("<html><b>Exam Mark</b></html>", SwingConstants.CENTER), constraints);

		for (int i = 0; i < Course.values().length; i++) {
			moduleTitleLabels[i] = new JLabel("Profile not created", SwingConstants.CENTER);
			courseworkMarkTextFields[i] = new JTextField("0", 2);
			courseworkMarkTextFields[i].setHorizontalAlignment(JTextField.CENTER);
			examMarkTextFields[i] = new JTextField("0", 2);
			examMarkTextFields[i].setHorizontalAlignment(JTextField.CENTER);
			moduleWarningLabels[i] = new JLabel("!");
			moduleWarningLabels[i].setFont(new Font(moduleWarningLabels[i].getFont().getName(), Font.PLAIN, 28));
			moduleWarningLabels[i].setForeground(Color.RED);
			moduleWarningLabels[i].setToolTipText("Missing mark!");
			moduleWarningLabels[i].setVisible(false);
		}

		constraints.gridx = 0;
		constraints.gridy++;
		for (int i = 0; i < Course.values().length; i++) {
			add(moduleTitleLabels[i], constraints);

			constraints.gridx++;
			add(courseworkMarkTextFields[i], constraints);

			constraints.gridx++;
			add(examMarkTextFields[i], constraints);

			constraints.gridx++;
			add(moduleWarningLabels[i], constraints);

			constraints.gridx = 0;
			constraints.gridy++;
		}

		constraints.gridx = 0;
		constraints.gridy++;
		restoreButton.setToolTipText("Restore marks from the last saved student profile");
		add(restoreButton, constraints);

		constraints.gridx++;
		clearButton.setToolTipText("Reset all marks to 0");
		add(clearButton, constraints);

		constraints.gridx++;
		submitButton.setToolTipText("Submit the marks provided to the student's profile");
		add(submitButton, constraints);
	}

	/**
	 * Gets the module title labels.
	 * @return The module title labels.
	 */
	public JLabel[] getModuleTitleLabels() {
		return moduleTitleLabels;
	}

	/**
	 * Gets a module title label.
	 * @param index The index.
	 * @return The module title label.
	 */
	public JLabel getModuleTitleLabel(int index) {
		return moduleTitleLabels[index];
	}

	/**
	 * Sets the module title labels.
	 * @param moduleTitleLabels The labels to set.
	 */
	public void setModuleTitleLabels(JLabel[] moduleTitleLabels) {
		this.moduleTitleLabels = moduleTitleLabels;
	}

	/**
	 * Sets a module title label.
	 * @param index The index.
	 * @param moduleTitleLabel The label to set.
	 */
	public void setModuleTitleLabel(int index, JLabel moduleTitleLabel) {
		this.moduleTitleLabels[index] = moduleTitleLabel;
	}

	/**
	 * Gets the coursework mark text fields.
	 * @return The coursework mark text fields.
	 */
	public JTextField[] getCourseworkMarkTextFields() {
		return courseworkMarkTextFields;
	}

	/**
	 * Gets a coursework mark text field.
	 * @param index The index.
	 * @return The coursework mark text field.
	 */
	public JTextField getCourseworkMarkTextField(int index) {
		return courseworkMarkTextFields[index];
	}

	/**
	 * Sets the coursework mark text fields.
	 * @param courseworkMarkTextFields The text fields to set.
	 */
	public void setCourseworkMarkTextFields(JTextField[] courseworkMarkTextFields) {
		this.courseworkMarkTextFields = courseworkMarkTextFields;
	}

	/**
	 * Sets a coursework mark text field.
	 * @param index The index.
	 * @param courseworkMarkTextField The text field to set.
	 */
	public void setCourseworkMarkTextField(int index, JTextField courseworkMarkTextField) {
		this.courseworkMarkTextFields[index] = courseworkMarkTextField;
	}

	/**
	 * Gets the exam mark text fields.
	 * @return The exam mark text fields.
	 */
	public JTextField[] getExamMarkTextFields() {
		return examMarkTextFields;
	}

	/**
	 * Gets an exam mark text field.
	 * @return The exam mark text field.
	 */
	public JTextField getExamMarkTextField(int index) {
		return examMarkTextFields[index];
	}

	/**
	 * Sets the exam mark text fields.
	 * @param examMarkTextFields The text fields to set.
	 */
	public void setExamMarkTextFields(JTextField[] examMarkTextFields) {
		this.examMarkTextFields = examMarkTextFields;
	}

	/**
	 * Sets an exam mark text field.
	 * @param index The index.
	 * @param examMarkTextField The text field to set.
	 */
	public void setExamMarkTextField(int index, JTextField examMarkTextField) {
		this.examMarkTextFields[index] = examMarkTextField;
	}

	/**
	 * Gets the module warning labels.
	 * @return The module warning labels.
	 */
	public JLabel[] getModuleWarningLabels() {
		return moduleWarningLabels;
	}

	/**
	 * Gets a module warning label.
	 * @param index The index.
	 * @return The module warning label.
	 */
	public JLabel getModuleWarningLabel(int index) {
		return moduleWarningLabels[index];
	}

	/**
	 * Sets the module warning labels.
	 * @param moduleWarningLabels The labels to set.
	 */
	public void setModuleWarningLabels(JLabel[] moduleWarningLabels) {
		this.moduleWarningLabels = moduleWarningLabels;
	}

	/**
	 * Sets a module warning label.
	 * @param index The index.
	 * @param moduleWarningLabel The label to set.
	 */
	public void setModuleWarningLabel(int index, JLabel moduleWarningLabel) {
		this.moduleWarningLabels[index] = moduleWarningLabel;
	}

	/**
	 * Gets the restore from save button.
	 * @return The restore from save button.
	 */
	public JButton getRestoreButton() {
		return restoreButton;
	}

	/**
	 * Gets the clear marks button.
	 * @return The clear marks button.
	 */
	public JButton getClearButton() {
		return clearButton;
	}

	/**
	 * Gets the submit marks button.
	 * @return The submit marks button.
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}
}
