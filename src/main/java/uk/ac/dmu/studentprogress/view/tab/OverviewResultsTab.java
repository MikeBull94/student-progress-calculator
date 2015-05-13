package uk.ac.dmu.studentprogress.view.tab;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

/**
 * The 'overview results' tab.
 */
public final class OverviewResultsTab extends Tab {
	/**
	 * The overview text area.
	 */
	private final JTextArea overviewTextArea = new JTextArea("", 25, 50);

	/**
	 * The save results button.
	 */
	private final JButton saveResultsButton = new JButton("Save results to text file");

	/**
	 * Creates a new {@link OverviewResultsTab}.
	 */
	public OverviewResultsTab() {
		super("Overview Results", "<html>An overview of the student's details and results based on their<br />submitted profile and module marks from the previous two tabs</html>");

		BorderLayout layout = new BorderLayout();
		layout.setVgap(20);
		setLayout(layout);

		overviewTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(overviewTextArea);
		add(scrollPane, BorderLayout.CENTER);

		saveResultsButton.setSize(50, 50);
		saveResultsButton.setToolTipText("Save the overview above to a text file for later viewing");
		add(saveResultsButton, BorderLayout.PAGE_END);
	}

	/**
	 * Gets the overview text area.
	 * @return The overview text area.
	 */
	public JTextArea getOverviewTextArea() {
		return overviewTextArea;
	}

	/**
	 * Gets the save results button.
	 * @return The save results button.
	 */
	public JButton getSaveResultsButton() {
		return saveResultsButton;
	}
}
