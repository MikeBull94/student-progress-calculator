package uk.ac.dmu.studentprogress.view.tab;

import uk.ac.dmu.studentprogress.model.Course;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

/**
 * The 'create profile' tab.
 */
public final class CreateProfileTab extends Tab {
	/**
	 * The full name text field.
	 */
	private final JTextField fullNameTextField = new JTextField(14);

	/**
	 * The full name warning label.
	 */
	private final JLabel fullNameWarningLabel = new JLabel("!");

	/**
	 * The p number text field.
	 */
	private final JTextField pNumberTextField = new JTextField(14);

	/**
	 * The p number warning label.
	 */
	private final JLabel pNumberWarningLabel = new JLabel("!");

	/**
	 * The course options combo box.
	 */
	private final JComboBox<Course> courseOptionsComboBox = new JComboBox<>();

	/**
	 * The course options warning label.
	 */
	private final JLabel courseOptionsWarningLabel = new JLabel("!");

	/**
	 * The create profile button.
	 */
	private final JButton createProfileButton = new JButton("Create Profile");

	/**
	 * Creates a new {@link CreateProfileTab}.
	 */
	public CreateProfileTab() {
		super("Create Profile", "Create a student's profile with their credentials and course information");

		constraints.anchor = GridBagConstraints.EAST;
		add(new JLabel("Select course:"), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.WEST;
		for (Course c : Course.values()) {
			courseOptionsComboBox.addItem(c);
		}
		courseOptionsComboBox.setToolTipText("Choose a course to enroll the student on");
		add(courseOptionsComboBox, constraints);

		constraints.gridx++;
		courseOptionsWarningLabel.setFont(new Font(courseOptionsWarningLabel.getFont().getName(), Font.PLAIN, 28));
		courseOptionsWarningLabel.setForeground(Color.RED);
		courseOptionsWarningLabel.setToolTipText("Missing course options choice!");
		courseOptionsWarningLabel.setVisible(false);
		add(courseOptionsWarningLabel, constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		constraints.anchor = GridBagConstraints.EAST;
		add(new JLabel("Input full name:"), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.WEST;
		fullNameTextField.setToolTipText("Enter the student's full name");
		add(fullNameTextField, constraints);

		constraints.gridx++;
		fullNameWarningLabel.setFont(new Font(fullNameWarningLabel.getFont().getName(), Font.PLAIN, 28));
		fullNameWarningLabel.setForeground(Color.RED);
		fullNameWarningLabel.setToolTipText("Missing username!");
		fullNameWarningLabel.setVisible(false);
		add(fullNameWarningLabel, constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		constraints.anchor = GridBagConstraints.EAST;
		add(new JLabel("Input P number:"), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.WEST;
		pNumberTextField.setToolTipText("Enter the student's p number");
		add(pNumberTextField, constraints);

		constraints.gridx++;
		pNumberWarningLabel.setFont(new Font(pNumberWarningLabel.getFont().getName(), Font.PLAIN, 28));
		pNumberWarningLabel.setForeground(Color.RED);
		pNumberWarningLabel.setToolTipText("Missing p number!");
		pNumberWarningLabel.setVisible(false);
		add(pNumberWarningLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy++;
		createProfileButton.setToolTipText("Create a student profile based on the information provided above");
		add(createProfileButton, constraints);
	}

	/**
	 * Gets the full name text field.
	 * @return The full name text field.
	 */
	public JTextField getFullNameTextField() {
		return fullNameTextField;
	}

	/**
	 * Gets the full name warning label.
	 * @return The full name warning label.
	 */
	public JLabel getFullNameWarningLabel() {
		return fullNameWarningLabel;
	}

	/**
	 * Gets the p number text field.
	 * @return The p number text field.
	 */
	public JTextField getPNumberTextField() {
		return pNumberTextField;
	}

	/**
	 * Gets the p number warning label.
	 * @return The p number warning label.
	 */
	public JLabel getPNumberWarningLabel() {
		return pNumberWarningLabel;
	}

	/**
	 * Gets the course options combo box.
	 * @return The course options combo box.
	 */
	public JComboBox<Course> getCourseOptionsComboBox() {
		return courseOptionsComboBox;
	}

	/**
	 * Gets the course options warning label.
	 * @return The course options warning label.
	 */
	public JLabel getCourseOptionsWarningLabel() {
		return courseOptionsWarningLabel;
	}

	/**
	 * Gets the create profile button.
	 * @return The create profile button.
	 */
	public JButton getCreateProfileButton() {
		return createProfileButton;
	}
}
